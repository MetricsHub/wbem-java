/*
  (C) Copyright IBM Corp. 2012

  THIS FILE IS PROVIDED UNDER THE TERMS OF THE ECLIPSE PUBLIC LICENSE
  ("AGREEMENT"). ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS FILE
  CONSTITUTES RECIPIENTS ACCEPTANCE OF THE AGREEMENT.

  You can obtain a current copy of the Eclipse Public License from
  http://www.opensource.org/licenses/eclipse-1.0.php

  @author : Alexander Wolf-Reber, IBM, a.wolf-reber@de.ibm.com
 * @author : Dave Blaschke, IBM, blaschke@us.ibm.com
 * 
 * Change History
 * Flag       Date        Prog         Description
 *------------------------------------------------------------------------------- 
 * 3477087    2012-01-23  blaschke-oss Need Access to an Indication Sender's IP Address                  
 */

package org.metricshub.wbem.sblim.cimclient;

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

import java.net.InetAddress;
import java.util.EventListener;
import org.metricshub.wbem.javax.cim.CIMInstance;

/**
 * This interface is implemented by the code that wants to create a listener for
 * indications. See the <code>WBEMListenerFactory</code> class for an example.
 *
 * The difference between this interface and <code>IndicationListener</code> is
 * that the JSR48 standard (<code>javax.wbem.listener.IndicationListener</code>)
 * does not allow for the listener to receive the IP of the indication sender
 * whereas this internal interface (
 * <code>org.sblim.cimclinet.IndicationListenerSBLIM</code>) does.
 */
public interface IndicationListenerSBLIM extends EventListener {
	/**
	 * Called when an indication has been received by the listener
	 *
	 * @param pIndicationURL
	 *            The URL to which the indication was posted. For example if the
	 *            indication was delivered over the https protocol to the
	 *            destination listener https://hostname:6111/, pIndicationURL
	 *            would be set to https://hostname:6111/.
	 * @param pIndication
	 *            The indication received.
	 * @param pSenderIP
	 *            The internet address of the indication sender.
	 */
	public void indicationOccured(String pIndicationURL, CIMInstance pIndication, InetAddress pSenderIP);
}
