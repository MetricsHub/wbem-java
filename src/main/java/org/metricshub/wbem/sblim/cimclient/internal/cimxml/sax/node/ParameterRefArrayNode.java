/*
  (C) Copyright IBM Corp. 2006, 2013

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
 * 1720707    2007-05-17  ebak         Conventional Node factory for CIM-XML SAX parser
 * 1735614    2007-06-12  ebak         Wrong ARRAYSIZE attribute handling in SAX/PULL
 * 1820763    2007-10-29  ebak         Supporting the EmbeddedInstance qualifier
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2433593    2008-12-18  rgummada     isArray returns true for method parameters of type reference
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 *    2605    2013-03-20  buccella     SAX parser throws wrong exception
 *    2636    2013-05-08  blaschke-oss Nested embedded instances cause CIMXMLParseException
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
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * <pre>
 * ELEMENT PARAMETER.REFARRAY (QUALIFIER*)
 * ATTLIST PARAMETER.REFARRAY
 *   %CIMName;
 *   %ReferenceClass;
 *   %ArraySize;
 * </pre>
 */
public class ParameterRefArrayNode extends AbstractParameterNode {
	private CIMDataType iType;

	/**
	 * Ctor.
	 */
	public ParameterRefArrayNode() {
		super(PARAMETER_REFARRAY);
	}

	@Override
	protected void specificInit(Attributes pAttribs) throws SAXException {
		String refClass = getReferenceClass(pAttribs);
		this.iType = new CIMDataType(refClass != null ? refClass : "", getArraySize(pAttribs));
	}

	@Override
	public void testCompletness() {
		/* */
	}

	public CIMDataType getType() {
		return this.iType;
	}
}
