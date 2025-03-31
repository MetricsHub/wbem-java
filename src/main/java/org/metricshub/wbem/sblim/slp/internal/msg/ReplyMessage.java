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
 * 1949918    2008-04-08  raman_arora  Malformed service URL crashes SLP discovery
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 * 2531371    2009-02-10  raman_arora  Upgrade client to JDK 1.5 (Phase 2) 
 */

package org.metricshub.wbem.sblim.slp.internal.msg;

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

import java.util.Iterator;

/**
 * ReplyMessage
 *
 */
public abstract class ReplyMessage extends SLPMessage {
	private int iErrorCode;

	/**
	 * Ctor.
	 *
	 * @param pFunctionID
	 * @param pErrorCode
	 */
	public ReplyMessage(int pFunctionID, int pErrorCode) {
		super(pFunctionID);
		this.iErrorCode = pErrorCode;
	}

	/**
	 * Ctor.
	 *
	 * @param pFunctionID
	 * @param pLangTag
	 * @param pErrorCode
	 */
	public ReplyMessage(int pFunctionID, String pLangTag, int pErrorCode) {
		super(pFunctionID, pLangTag);
		this.iErrorCode = pErrorCode;
	}

	/**
	 * Ctor.
	 *
	 * @param pHeader
	 * @param pErrorCode
	 */
	public ReplyMessage(MsgHeader pHeader, int pErrorCode) {
		super(pHeader);
		this.iErrorCode = pErrorCode;
	}

	/**
	 * getErrorCode
	 *
	 * @return int
	 */
	public int getErrorCode() {
		return this.iErrorCode;
	}

	/**
	 * getResultIterator
	 *
	 * @return Iterator
	 */
	public abstract Iterator<?> getResultIterator();

	/**
	 * getExceptionIterator
	 *
	 * @return Iterator
	 */
	public abstract Iterator<?> getExceptionIterator();
}
