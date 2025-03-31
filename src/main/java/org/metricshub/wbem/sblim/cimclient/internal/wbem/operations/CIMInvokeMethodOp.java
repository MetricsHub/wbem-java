/*
  CIMInvokeMethodOp.java

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
 * 2797550    2009-06-01  raman_arora  JSR48 compliance - add Java Generics
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

import org.metricshub.wbem.javax.cim.CIMArgument;
import org.metricshub.wbem.javax.cim.CIMObjectPath;

/**
 * CIMInvokeMethodOp
 *
 */
public class CIMInvokeMethodOp extends CIMOperation {
	protected String iMethodName;

	protected CIMArgument<?>[] iInParams;

	protected CIMArgument<?>[] iOutParams;

	/**
	 * Ctor.
	 *
	 * @param pObjectName
	 * @param pMethodName
	 * @param pInParams
	 * @param pOutParams
	 */
	public CIMInvokeMethodOp(
		CIMObjectPath pObjectName,
		String pMethodName,
		CIMArgument<?>[] pInParams,
		CIMArgument<?>[] pOutParams
	) {
		this.iMethodCall = "InvokeMethod";
		this.iObjectName = pObjectName;
		this.iMethodName = pMethodName;
		this.iInParams = pInParams;
		this.iOutParams = pOutParams;
	}

	/**
	 * Returns inParameters
	 *
	 * @return The value of inParameters.
	 */
	public CIMArgument<?>[] getInParams() {
		return this.iInParams;
	}

	/**
	 * Returns methodName
	 *
	 * @return The value of methodName.
	 */
	public String getMethodName() {
		return this.iMethodName;
	}

	/**
	 * Returns outParameters
	 *
	 * @return The value of outParameters.
	 */
	public CIMArgument<?>[] getOutParams() {
		return this.iOutParams;
	}
}
