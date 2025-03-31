/*
  (C) Copyright IBM Corp. 2006, 2012

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
 * 1720707    2007-05-17  ebak         Conventional Node factory for CIM-XML SAX parser
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 * 2531371    2009-02-10  raman_arora  Upgrade client to JDK 1.5 (Phase 2)
 * 3511454    2012-03-27  blaschke-oss SAX nodes not reinitialized properly
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

import org.metricshub.wbem.javax.cim.CIMClass;
import org.metricshub.wbem.javax.cim.CIMDataType;
import org.metricshub.wbem.javax.cim.CIMNamedElementInterface;
import org.metricshub.wbem.javax.cim.CIMObjectPath;
import org.metricshub.wbem.sblim.cimclient.internal.cimxml.sax.SAXSession;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * ELEMENT VALUE.OBJECTWITHPATH ((CLASSPATH, CLASS) | (INSTANCEPATH, INSTANCE))
 */
public class ValueObjectWithPathNode extends AbstractScalarValueNode {
	// ObjectPath element
	private String iPathNodeNameEnum;

	private CIMObjectPath iObjPath;

	// CIMObject element
	private String iObjNodeNameEnum;

	private CIMNamedElementInterface iCIMObj;

	/**
	 * Ctor.
	 */
	public ValueObjectWithPathNode() {
		super(VALUE_OBJECTWITHPATH);
	}

	/**
	 * @param pAttribs
	 * @param pSession
	 */
	@Override
	public void init(Attributes pAttribs, SAXSession pSession) {
		this.iPathNodeNameEnum = this.iObjNodeNameEnum = null;
		this.iObjPath = null;
		this.iCIMObj = null;
		// no attributes
	}

	/**
	 * @param pData
	 */
	@Override
	public void parseData(String pData) {
		// no data
	}

	@Override
	public void testChild(String pNodeNameEnum) throws SAXException {
		if (pNodeNameEnum == CLASSPATH) {
			if (this.iPathNodeNameEnum != null) duplicatedNode(this.iPathNodeNameEnum, CLASSPATH);
			if (this.iObjNodeNameEnum == INSTANCE) illegalChildNodePair(CLASSPATH, INSTANCE);
		} else if (pNodeNameEnum == CLASS) {
			if (this.iObjNodeNameEnum != null) duplicatedNode(this.iObjNodeNameEnum, CLASS);
			if (this.iPathNodeNameEnum == INSTANCEPATH) illegalChildNodePair(INSTANCEPATH, CLASS);
		} else if (pNodeNameEnum == INSTANCEPATH) {
			if (this.iPathNodeNameEnum != null) duplicatedNode(this.iPathNodeNameEnum, INSTANCEPATH);
			if (this.iObjNodeNameEnum == CLASS) illegalChildNodePair(INSTANCEPATH, CLASS);
		} else if (pNodeNameEnum == INSTANCE) {
			if (this.iObjNodeNameEnum != null) duplicatedNode(this.iObjNodeNameEnum, INSTANCE);
			if (this.iPathNodeNameEnum == CLASSPATH) illegalChildNodePair(CLASSPATH, INSTANCE);
		} else throw new SAXException(getNodeName() + " node cannot have " + pNodeNameEnum + " child!");
	}

	@Override
	public void childParsed(Node pChild) throws SAXException {
		if (pChild instanceof AbstractObjectPathNode) {
			this.iPathNodeNameEnum = pChild.getNodeName();
			this.iObjPath = ((AbstractObjectPathNode) pChild).getCIMObjectPath();
		} else { // ClassNode or InstanceNode, iObjPath must be available
			// here
			this.iObjNodeNameEnum = pChild.getNodeName();
			if (this.iPathNodeNameEnum == null) throw new SAXException(
				getNodeName() + " first child should contain an object path!"
			);
			if (pChild instanceof ClassNode) {
				this.iCIMObj = ((ClassNode) pChild).getCIMClass(this.iObjPath);
			} else {
				this.iCIMObj = ((InstanceNode) pChild).getCIMInstance(this.iObjPath);
			}
		}
	}

	@Override
	public void testCompletness() throws SAXException {
		if (this.iPathNodeNameEnum == null) throw new SAXException(
			getNodeName() + " node must have a CLASSPATH or a INSTANCEPATH child node!"
		);
		if (this.iObjNodeNameEnum == null) throw new SAXException(
			getNodeName() + " node must have a CLASS or INSTANCE child node!"
		);
	}

	/**
	 * @see ValueIf#getValue()
	 * @return CIMClass or CIMInstance
	 */
	public Object getValue() {
		return this.iCIMObj;
	}

	public CIMDataType getType() {
		if (this.iCIMObj instanceof CIMClass) return CIMDataType.CLASS_T;
		return CIMDataType.OBJECT_T;
	}
}
