/*
  CIMEnumClassesOp.java

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

/**
 * CIMEnumClassesOp
 * 
 */
public class CIMEnumClassesOp extends CIMOperation {

	protected boolean iDeep;

	protected boolean iLocalOnly;

	protected boolean iIncludeQualifiers;

	protected boolean iIncludeClassOrigin;

	/**
	 * Ctor.
	 * 
	 * @param pObjectName
	 * @param pDeep
	 * @param pLocalOnly
	 * @param pIncludeQualifiers
	 * @param pIncludeClassOrigin
	 */
	public CIMEnumClassesOp(CIMObjectPath pObjectName, boolean pDeep, boolean pLocalOnly,
			boolean pIncludeQualifiers, boolean pIncludeClassOrigin) {
		this.iMethodCall = "EnumerateClasses";
		this.iObjectName = pObjectName;
		this.iDeep = pDeep;
		this.iLocalOnly = pLocalOnly;
		this.iIncludeClassOrigin = pIncludeClassOrigin;
		this.iIncludeQualifiers = pIncludeQualifiers;
	}

	/**
	 * Returns if deep is set
	 * 
	 * @return The value of deep
	 */
	public boolean isDeep() {
		return this.iDeep;
	}

	/**
	 * Returns if includeClassOrigin is set
	 * 
	 * @return The value of includeClassOrigin
	 */
	public boolean isIncludeClassOrigin() {
		return this.iIncludeClassOrigin;
	}

	/**
	 * Returns if includeQualifiers is set
	 * 
	 * @return The value of includeQualifiers
	 */
	public boolean isIncludeQualifiers() {
		return this.iIncludeQualifiers;
	}

	/**
	 * Returns if localOnly is set
	 * 
	 * @return The value of localOnly
	 */
	public boolean isLocalOnly() {
		return this.iLocalOnly;
	}

}
