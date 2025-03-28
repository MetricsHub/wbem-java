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
 * 1720707    2007-05-17  ebak         Conventional Node factory for CIM-XML SAX parser
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
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

import org.metricshub.wbem.javax.cim.CIMDataType;
import org.metricshub.wbem.sblim.cimclient.internal.cimxml.sax.SAXSession;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * ELEMENT VALUE.NULL EMPTY
 */
public class ValueNullNode extends AbstractScalarValueNode {

	/**
	 * Ctor.
	 */
	public ValueNullNode() {
		super(VALUE_NULL);
	}

	/**
	 * @param pAttribs
	 * @param pSession
	 */
	@Override
	public void init(Attributes pAttribs, SAXSession pSession) {
	// no attributes
	}

	/**
	 * @param pData
	 */
	@Override
	public void parseData(String pData) {
	// no data
	}

	/**
	 * @param pNodeNameEnum
	 */
	@Override
	public void testChild(String pNodeNameEnum) throws SAXException {
		throw new SAXException("VALUE.NULL node cannot have any child node!");
	}

	/**
	 * @param pChild
	 */
	@Override
	public void childParsed(Node pChild) {
	// no child
	}

	@Override
	public void testCompletness() {
	// nothing to test
	}

	/**
	 * @see ValueIf#getValue()
	 * @return null
	 */
	public Object getValue() {
		return null;
	}

	public CIMDataType getType() {
		// type is unknown
		return null;
	}

}
