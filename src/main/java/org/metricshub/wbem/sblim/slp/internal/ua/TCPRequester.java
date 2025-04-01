/*
  (C) Copyright IBM Corp. 2007, 2009

  THIS FILE IS PROVIDED UNDER THE TERMS OF THE ECLIPSE PUBLIC LICENSE
  ("AGREEMENT"). ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS FILE
  CONSTITUTES RECIPIENTS ACCEPTANCE OF THE AGREEMENT.

  You can obtain a current copy of the Eclipse Public License from
  http://www.opensource.org/licenses/eclipse-1.0.php

  @author : Endre Bak, IBM, ebak@de.ibm.com
 * 
 * Change History
 * Flag       Date        Prog         Description
 *------------------------------------------------------------------------------- 
 * 1804402    2007-09-28  ebak         IPv6 ready SLP
 * 1892103    2008-02-12  ebak         SLP improvements
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 */

package org.metricshub.wbem.sblim.slp.internal.ua;

/*-
 * ╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲
 * WBEM Java Client
 * ჻჻჻჻჻჻
 * Copyright 2023 - 2025 MetricsHub
 * ჻჻჻჻჻჻
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱
 */

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import org.metricshub.wbem.sblim.slp.ServiceLocationException;
import org.metricshub.wbem.sblim.slp.internal.SLPConfig;
import org.metricshub.wbem.sblim.slp.internal.TRC;
import org.metricshub.wbem.sblim.slp.internal.msg.MsgFactory;
import org.metricshub.wbem.sblim.slp.internal.msg.ReplyMessage;
import org.metricshub.wbem.sblim.slp.internal.msg.RequestMessage;

/**
 * TCPRequester
 *
 */
public class TCPRequester implements Runnable {
	private InetAddress iDestination;

	private Thread iThread;

	private ResultTable iResTable;

	private RequestMessage iReqMsg;

	private byte[] iRequestBytes;

	private int iPort = SLPConfig.getGlobalCfg().getPort();

	private final int iTCPTimeOut = SLPConfig.getGlobalCfg().getTCPTimeout();

	/**
	 * Ctor.
	 *
	 * @param pResTable
	 * @param pDestination
	 * @param pReqMsg
	 * @param pAsThread
	 * @throws ServiceLocationException
	 */
	public TCPRequester(ResultTable pResTable, InetAddress pDestination, RequestMessage pReqMsg, boolean pAsThread)
		throws ServiceLocationException {
		this.iResTable = pResTable;
		this.iDestination = pDestination;
		this.iReqMsg = pReqMsg;
		this.iRequestBytes = pReqMsg.serializeWithoutResponders(false, false, true);
		// FIXME: Is it safe to omit PreviousResopnder list for TCP request?
		if (pAsThread) {
			this.iThread = new Thread(this);
			this.iThread.start();
		} else {
			this.iThread = null;
			run();
		}
	}

	/**
	 * waitFor
	 */
	public void waitFor() {
		if (this.iThread == null) return;
		try {
			this.iThread.join();
		} catch (InterruptedException e) {
			TRC.error(e);
		}
	}

	public void run() {
		Socket socket = null;
		try {
			socket = new Socket(this.iDestination, this.iPort);
			socket.setSoTimeout(this.iTCPTimeOut);
			OutputStream os = socket.getOutputStream();
			TRC.debug("sendTCP");
			os.write(this.iRequestBytes);
			os.flush();
			handleResponse(socket);
			TRC.debug("recievedOnTCP");
		} catch (Exception e) {
			TRC.error(e.getMessage());
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					TRC.error(e);
				}
			}
		}
	}

	private void handleResponse(Socket pSocket) {
		ReplyMessage replyMsg;
		try {
			replyMsg = (ReplyMessage) MsgFactory.parse(pSocket);
		} catch (Exception e) {
			this.iResTable.addException(e);
			return;
		}
		if (
			this.iReqMsg.getXID() == replyMsg.getXID() && this.iReqMsg.isAllowedResponseType(replyMsg)
		) this.iResTable.addResults(replyMsg);
	}
}
