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
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 * 2763216    2009-04-14  blaschke-oss Code cleanup: visible spelling/grammar errors
 */

package org.metricshub.wbem.sblim.slp.internal.sa;

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
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.metricshub.wbem.sblim.slp.internal.SLPConfig;
import org.metricshub.wbem.sblim.slp.internal.TRC;

/**
 * TCPThread
 * 
 */
public class TCPThread extends RecieverThread {

	private ServerSocket iListenerSocket;

	/**
	 * Ctor.
	 * 
	 * @param pSrvAgent
	 */
	public TCPThread(ServiceAgent pSrvAgent) {
		super("TCP receiver", pSrvAgent);
	}

	@Override
	protected void init() throws IOException {
		this.iListenerSocket = new ServerSocket(SLPConfig.getGlobalCfg().getPort());
		this.iListenerSocket.setReuseAddress(true);
		this.iListenerSocket.setSoTimeout(100);
	}

	@Override
	protected void mainLoop() throws IOException {
		try {
			new ConnectionThread(this.iListenerSocket.accept());
		} catch (SocketTimeoutException e) {
			// superclass will execute the mainLoop again
		}
	}

	private class ConnectionThread implements Runnable {

		private Socket iSock;

		/**
		 * Ctor.
		 * 
		 * @param pSock
		 */
		public ConnectionThread(Socket pSock) {
			this.iSock = pSock;
			new Thread(this).start();
		}

		public void run() {
			TCPThread.this.iSrvAgent.processMessage(this.iSock);
		}

	}

	@Override
	protected void close() {
		if (this.iListenerSocket == null) return;
		try {
			this.iListenerSocket.close();
		} catch (IOException e) {
			TRC.error(e);
		}
	}

}
