/*
  (C) Copyright IBM Corp. 2006, 2009

  THIS FILE IS PROVIDED UNDER THE TERMS OF THE ECLIPSE PUBLIC LICENSE
  ("AGREEMENT"). ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS FILE
  CONSTITUTES RECIPIENTS ACCEPTANCE OF THE AGREEMENT.

  You can obtain a current copy of the Eclipse Public License from
  http://www.opensource.org/licenses/eclipse-1.0.php

  @author : Endre Bak, ebak@de.ibm.com
 * 
 * Flag       Date        Prog         Description
 * -------------------------------------------------------------------------------
 * 1688273    2007-04-16  ebak         Full support of HTTP trailers
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 */

package org.metricshub.wbem.sblim.cimclient.internal.http.io;

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

import org.metricshub.wbem.javax.wbem.WBEMException;
import org.metricshub.wbem.sblim.cimclient.internal.util.WBEMConstants;

/**
 * TrailerException is thrown by ChunkedInputStream when it receives a http
 * trailer which contains the following entries: CIMStatusCode,
 * CIMStatusCodeDescription. These http trailer entries are known to be used by
 * Pegasus CIMOM.
 */
public class TrailerException extends RuntimeException {

	private static final long serialVersionUID = 4355341648542585509L;

	private WBEMException iWBEMException;

	/**
	 * Ctor.
	 * 
	 * @param pException
	 *            The contained WBEMException
	 */
	public TrailerException(WBEMException pException) {
		super(WBEMConstants.HTTP_TRAILER_STATUS_CODE + ":" + pException.getID() + " "
				+ WBEMConstants.HTTP_TRAILER_STATUS_DESCRIPTION + ":" + pException.getMessage());
		this.iWBEMException = pException;
	}

	/**
	 * getWBEMException
	 * 
	 * @return WBEMException
	 */
	public WBEMException getWBEMException() {
		return this.iWBEMException;
	}

}
