/*
  (C) Copyright IBM Corp. 2006, 2010

  THIS FILE IS PROVIDED UNDER THE TERMS OF THE ECLIPSE PUBLIC LICENSE
  ("AGREEMENT"). ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS FILE
  CONSTITUTES RECIPIENTS ACCEPTANCE OF THE AGREEMENT.

  You can obtain a current copy of the Eclipse Public License from
  http://www.opensource.org/licenses/eclipse-1.0.php

  @author : Alexander Wolf-Reber, IBM, a.wolf-reber@de.ibm.com
 * 
 * Change History
 * Flag       Date        Prog         Description
 *------------------------------------------------------------------------------- 
 * 1565892    2006-12-14  lupusalex    Make SBLIM client JSR48 compliant
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 * 2959240    2010-02-25  blaschke-oss Sync up javax.listener.* javadoc with JSR48 1.0.0
 */

package org.metricshub.wbem.javax.wbem.listener;

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

import java.util.EventListener;
import org.metricshub.wbem.javax.cim.CIMInstance;

//Sync'd against JSR48 1.0.0 javadoc (build 1.5.0_10) on Wed Jan 20 02:20:59 EST 2010
/**
 * This interface is implemented by the code that wants to create a listener for
 * indications. See the <code>WBEMListenerFactory</code> class for an example.
 */
public interface IndicationListener extends EventListener {
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
	 */
	public void indicationOccured(String pIndicationURL, CIMInstance pIndication);
}
