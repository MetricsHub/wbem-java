/*
  CIMAssociatorsOp.java

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
 * Class CIMAssociatorsOp
 *
 */
public class CIMAssociatorsOp extends CIMOperation {
	protected String iAssociationClass;

	protected String iResultClass;

	protected String iRole;

	protected String iResultRole;

	protected boolean iIncludeQualifiers;

	protected boolean iIncludeClassOrigin;

	protected String[] iPropertyList;

	/**
	 * Ctor.
	 *
	 * @param pObjectName
	 * @param pAssociationClass
	 * @param pResultClass
	 * @param pRole
	 * @param pResultRole
	 * @param pIncludeQualifiers
	 * @param pIncludeClassOrigin
	 * @param pPropertyList
	 */
	public CIMAssociatorsOp(
		CIMObjectPath pObjectName,
		String pAssociationClass,
		String pResultClass,
		String pRole,
		String pResultRole,
		boolean pIncludeQualifiers,
		boolean pIncludeClassOrigin,
		String[] pPropertyList
	) {
		this.iMethodCall = "Associators";
		this.iObjectName = pObjectName;
		this.iAssociationClass = pAssociationClass;
		this.iResultClass = pResultClass;
		this.iRole = pRole;
		this.iIncludeClassOrigin = pIncludeClassOrigin;
		this.iIncludeQualifiers = pIncludeQualifiers;
		this.iPropertyList = pPropertyList;
	}

	/**
	 * Returns the association class
	 *
	 * @return The association class
	 */
	public String getAssocClass() {
		return this.iAssociationClass;
	}

	/**
	 * Returns if includeQualifiers is set
	 *
	 * @return The value of includeClassOrigin
	 */
	public boolean isIncludeClassOrigin() {
		return this.iIncludeClassOrigin;
	}

	/**
	 * Returns if includeQualifiers is set
	 *
	 * @return The value of includeClassOrigin
	 */
	public boolean isIncludeQualifiers() {
		return this.iIncludeQualifiers;
	}

	/**
	 * Returns the property list
	 *
	 * @return The property list
	 */
	public String[] getPropertyList() {
		return this.iPropertyList;
	}

	/**
	 * Returns the result class
	 *
	 * @return The result class
	 */
	public String getResultClass() {
		return this.iResultClass;
	}

	/**
	 * Returns the result role
	 *
	 * @return The result role
	 */
	public String getResultRole() {
		return this.iResultRole;
	}

	/**
	 * Returns the role
	 *
	 * @return The role
	 */
	public String getRole() {
		return this.iRole;
	}
}
