/*
  CIMOperation.java

  (C) Copyright IBM Corp. 2005, 2009

  THIS FILE IS PROVIDED UNDER THE TERMS OF THE ECLIPSE PUBLIC LICENSE
  ("AGREEMENT"). ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS FILE
  CONSTITUTES RECIPIENTS ACCEPTANCE OF THE AGREEMENT.

  You can obtain a current copy of the Eclipse Public License from
  http://www.opensource.org/licenses/eclipse-1.0.php

  @author : Roberto Pineiro, IBM, roberto.pineiro@us.ibm.com
 * @author : Chung-hao Tan, IBM, chungtan@us.ibm.com
 * 
 * 
 * Change History
 * Flag       Date        Prog         Description
 *------------------------------------------------------------------------------- 
 * 1535756    2006-08-07  lupusalex    Make code warning free
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 */

package org.metricshub.wbem.sblim.cimclient.internal.wbem.operations;

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

import org.metricshub.wbem.javax.cim.CIMObjectPath;
import org.metricshub.wbem.javax.wbem.WBEMException;

/**
 * CIMOperation
 *
 */
public abstract class CIMOperation {
	protected CIMObjectPath iObjectName;

	protected String iNameSpace;

	protected String iMethodCall;

	protected Object iResult;

	/**
	 * Returns the object name
	 *
	 * @return The object name
	 */
	public CIMObjectPath getObjectName() {
		return this.iObjectName;
	}

	/**
	 * Returns the namespace
	 *
	 * @return The namespace
	 */
	public String getNameSpace() {
		return this.iNameSpace;
	}

	/**
	 * Sets the namespace
	 *
	 * @param pNamespace
	 *            The namespace
	 */
	public void setNameSpace(String pNamespace) {
		this.iNameSpace = pNamespace;
	}

	/**
	 * Returns the method call
	 *
	 * @return The method call
	 */
	public String getMethodCall() {
		return this.iMethodCall;
	}

	/**
	 * Returns if an (uncaught) exception occurred
	 *
	 * @return <code>true</code> if an (uncaught) exception occurred,
	 *         <code>false</code> otherwise
	 */
	public boolean isException() {
		return (this.iResult instanceof Exception);
	}

	/**
	 * Returns the result of the operation
	 *
	 * @return The result
	 * @throws WBEMException
	 */
	public Object getResult() throws WBEMException {
		if (this.iResult instanceof WBEMException) throw (WBEMException) this.iResult;
		return this.iResult;
	}

	/**
	 * Sets the operation result
	 *
	 * @param pResult
	 *            The result
	 */
	public void setResult(Object pResult) {
		this.iResult = pResult;
	}
}
