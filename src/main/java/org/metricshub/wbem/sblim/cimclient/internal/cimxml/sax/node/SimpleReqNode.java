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
 * 1660756    2007-02-22  ebak         Embedded object support
 * 1686000    2007-04-20  ebak         modifyInstance() missing from WBEMClient
 * 1720707    2007-05-17  ebak         Conventional Node factory for CIM-XML SAX parser
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 * 3511454    2012-03-27  blaschke-oss SAX nodes not reinitialized properly
 *    2712    2013-11-14  blaschke-oss SimpleReqNode allows any CIM element as child
 *    2538    2013-11-28  blaschke-oss CR14: Support new CORRELATOR element
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

import org.metricshub.wbem.sblim.cimclient.internal.cimxml.sax.SAXSession;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * ELEMENT SIMPLEREQ (CORRELATOR*, (METHODCALL | IMETHODCALL))
 */
public class SimpleReqNode extends AbstractMessageNode {

	private AbstractMethodCallNode iMethodCallNode;

	/**
	 * Ctor.
	 */
	public SimpleReqNode() {
		super(SIMPLEREQ);
	}

	public void addChild(Node pChild) {
		if (pChild instanceof AbstractMethodCallNode) {
			this.iMethodCallNode = (AbstractMethodCallNode) pChild;
		} else if (pChild instanceof CorrelatorNode) {
			// TODO: return to WBEMClient API if JSR48 changes
		}
	}

	/**
	 * @param pAttribs
	 * @param pSession
	 */
	@Override
	public void init(Attributes pAttribs, SAXSession pSession) {
		this.iMethodCallNode = null;
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
		if (pNodeNameEnum == IMETHODCALL || pNodeNameEnum == METHODCALL) {
			if (this.iMethodCallNode != null) throw new SAXException(
					"SIMPLEREQ node can have only one child node!");
		} else if (pNodeNameEnum != CORRELATOR) throw new SAXException(
				"SIMPLEREQ node cannot have " + pNodeNameEnum + " child node!");
	}

	@Override
	public void testCompletness() throws SAXException {
		if (this.iMethodCallNode == null) throw new SAXException(
				"SIMPLEREQ node must have a child node!");
	}

	/**
	 * getAbstractMethodCallNode
	 * 
	 * @return AbstractMethodCallNode
	 */
	public AbstractMethodCallNode getAbstractMethodCallNode() {
		return this.iMethodCallNode;
	}

}
