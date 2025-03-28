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
 * 1892103    2008-02-13  ebak         SLP improvements
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
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
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;

import org.metricshub.wbem.sblim.slp.internal.SLPConfig;
import org.metricshub.wbem.sblim.slp.internal.SLPDefaults;
import org.metricshub.wbem.sblim.slp.internal.TRC;

/**
 * DatagramThread
 * 
 */
public class DatagramThread extends RecieverThread {

	private static MulticastSocket cMCastSocket;

	private DatagramPacket iPacket = new DatagramPacket(new byte[SLPDefaults.MTU], SLPDefaults.MTU);

	/**
	 * Ctor.
	 * 
	 * @param pSrvAgent
	 */
	public DatagramThread(ServiceAgent pSrvAgent) {
		super("DatagramThread", pSrvAgent);
	}

	/**
	 * joinGroup
	 * 
	 * @param pGroup
	 * @throws IOException
	 */
	public synchronized void joinGroup(InetAddress pGroup) throws IOException {
		TRC.debug("join:" + pGroup);
		cMCastSocket.joinGroup(pGroup);
	}

	/**
	 * leaveGroup
	 * 
	 * @param pGroup
	 * @throws IOException
	 */
	public synchronized void leaveGroup(InetAddress pGroup) throws IOException {
		TRC.debug("leave:" + pGroup);
		cMCastSocket.leaveGroup(pGroup);
	}

	@Override
	protected void init() throws IOException {
		if (cMCastSocket == null) {
			cMCastSocket = new MulticastSocket(SLPConfig.getGlobalCfg().getPort());
			cMCastSocket.setReuseAddress(true);
			cMCastSocket.setSoTimeout(100);
		}
	}

	@Override
	protected void mainLoop() throws IOException {
		try {
			cMCastSocket.receive(this.iPacket);
			TRC.debug("Packet received");
			this.iSrvAgent.processMessage(cMCastSocket, this.iPacket);
		} catch (SocketTimeoutException e) {
			// superclass will restart this function
		}
	}

	@Override
	protected void close() {
		if (cMCastSocket == null) return;
		cMCastSocket.close();
		cMCastSocket = null;
	}

}
