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
 * 1663270    2007-02-19  ebak         Minor performance problems
 * 1686000    2007-04-20  ebak         modifyInstance() missing from WBEMClient
 * 1720707    2007-05-17  ebak         Conventional Node factory for CIM-XML SAX parser
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 *    2682    2013-10-02  blaschke-oss (I)MethodCallNode allows no LOCAL*PATH
 *    2690    2013-10-11  blaschke-oss Remove RESPONSEDESTINATION support
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

import org.xml.sax.SAXException;

/**
 * ELEMENT METHODCALL ((LOCALINSTANCEPATH | LOCALCLASSPATH), PARAMVALUE*)
 * ATTLIST METHODCALL %CIMName;
 */
public class MethodCallNode extends AbstractMethodCallNode {

	/**
	 * Ctor.
	 */
	public MethodCallNode() {
		super(METHODCALL);
	}

	@Override
	protected void testSpecChild(String pNodeNameEnum) throws SAXException {
		if (pNodeNameEnum == LOCALCLASSPATH || pNodeNameEnum == LOCALINSTANCEPATH) {
			if (this.iPath != null) throw new SAXException(getNodeName()
					+ " node can have only one LOCALINSTANCEPATH or LOCALCLASSPATH child node!");
		} else if (pNodeNameEnum != PARAMVALUE) throw new SAXException(getNodeName()
				+ " node cannot have " + pNodeNameEnum + " child node!");
	}

	@Override
	public void testCompletness() throws SAXException {
		if (this.iPath == null) throw new SAXException(
				"METHODCALL node must have a LOCALINSTANCEPATH or LOCALCLASSPATH child node!");
	}

}
