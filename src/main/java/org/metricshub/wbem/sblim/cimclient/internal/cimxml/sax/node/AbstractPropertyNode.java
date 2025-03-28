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
 * 1565892    2006-12-04  ebak         Make SBLIM client JSR48 compliant
 * 1663270    2007-02-19  ebak         Minor performance problems
 * 1660756    2007-02-22  ebak         Embedded object support
 * 1689085    2007-04-10  ebak         Embedded object enhancements for Pegasus
 * 1714878    2007-05-08  ebak         Empty string property values are parsed as null
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 * 2531371    2009-02-10  raman_arora  Upgrade client to JDK 1.5 (Phase 2) 
 * 2797550    2009-06-01  raman_arora  JSR48 compliance - add Java Generics
 */

package org.metricshub.wbem.sblim.cimclient.internal.cimxml.sax.node;

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

import org.metricshub.wbem.javax.cim.CIMClassProperty;
import org.metricshub.wbem.javax.cim.CIMQualifier;
import org.metricshub.wbem.sblim.cimclient.internal.cimxml.sax.SAXSession;
import org.metricshub.wbem.javax.cim.CIMProperty;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * AbstractPropertyNode is superclass of PropertyArrayNode, PropertyNode and
 * PropertyReferenceNode classes.
 */
public abstract class AbstractPropertyNode extends Node implements TypedIf, ValueIf {

	// common attributes
	private String iName;

	private String iClassOrigin;

	private boolean iPropagated;

	protected QualifiedNodeHandler iQualiHandler;

	/**
	 * Ctor.
	 * 
	 * @param pNameEnum
	 */
	public AbstractPropertyNode(String pNameEnum) {
		super(pNameEnum);
	}

	/**
	 * hasValue
	 * 
	 * @return true if it has a value child node
	 */
	protected abstract boolean hasValueNode();

	protected abstract void childValueNodeParsed(Node pChild) throws SAXException;

	protected abstract void specificInit(Attributes pAttribs, SAXSession pSession)
			throws SAXException;

	protected abstract String getChildValueNodeNameEnum();

	@Override
	public void init(Attributes pAttribs, SAXSession pSession) throws SAXException {
		this.iQualiHandler = QualifiedNodeHandler.init(this.iQualiHandler);
		this.iName = getCIMName(pAttribs);
		this.iClassOrigin = getClassOrigin(pAttribs);
		this.iPropagated = getPropagated(pAttribs);
		specificInit(pAttribs, pSession);
	}

	@Override
	public void testChild(String pNodeNameEnum) throws SAXException {
		String valueNodeNameEnum = getChildValueNodeNameEnum();
		if (pNodeNameEnum == valueNodeNameEnum) {
			if (hasValueNode()) throw new SAXException(getNodeName() + " node can have only one "
					+ valueNodeNameEnum + " child node!");
		} else if (pNodeNameEnum != QUALIFIER) throw new SAXException(getNodeName()
				+ " node cannot have " + pNodeNameEnum + " child node!");
	}

	/**
	 * @param pData
	 */
	@Override
	public void parseData(String pData) {
	// no data
	}

	@Override
	public void childParsed(Node pChild) throws SAXException {
		if (!this.iQualiHandler.addQualifierNode(pChild)) {
			childValueNodeParsed(pChild);
		}
	}

	protected CIMQualifier<?>[] getQualis() {
		// not dealing with embedded object qualifier is faster
		return this.iQualiHandler.getQualis(true);
	}

	/**
	 * getCIMProperty
	 * 
	 * @return CIMProperty
	 */
	public CIMProperty<Object> getCIMProperty() {
		/*
		 * CIMProperty( String name, CIMDataType type, Object value, boolean
		 * key, boolean propagated, String originClass )
		 */
		return new CIMProperty<Object>(this.iName, getType(), getValue(), this.iQualiHandler
				.isKeyed(), this.iPropagated, this.iClassOrigin);
	}

	/**
	 * getCIMClassProperty
	 * 
	 * @return CIMClassProperty
	 */
	public CIMClassProperty<Object> getCIMClassProperty() {
		/*
		 * CIMClassProperty( String pName, CIMDataType pType, Object pValue,
		 * CIMQualifier[] pQualifiers, boolean pKey, boolean propagated, String
		 * originClass) );
		 */
		return new CIMClassProperty<Object>(this.iName, getType(), getValue(), getQualis(),
				this.iQualiHandler.isKeyed(), this.iPropagated, this.iClassOrigin);
	}

}
