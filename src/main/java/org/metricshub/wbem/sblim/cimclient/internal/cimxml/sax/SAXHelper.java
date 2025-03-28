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
 * 1660756    2007-02-22  ebak         Embedded object support
 * 1688273    2007-04-16  ebak         Full support of HTTP trailers
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 * 2797550    2009-06-01  raman_arora  JSR48 compliance - add Java Generics
 */

package org.metricshub.wbem.sblim.cimclient.internal.cimxml.sax;

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

import java.io.InputStreamReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.metricshub.wbem.javax.cim.CIMArgument;
import org.metricshub.wbem.javax.cim.CIMObjectPath;
import org.metricshub.wbem.javax.wbem.WBEMException;
import org.metricshub.wbem.sblim.cimclient.internal.cimxml.sax.node.CIMNode;
import org.metricshub.wbem.sblim.cimclient.internal.cimxml.sax.node.MessageNode;
import org.metricshub.wbem.sblim.cimclient.internal.cimxml.sax.node.SimpleRspNode;
import org.metricshub.wbem.sblim.cimclient.internal.http.io.TrailerException;
import org.metricshub.wbem.sblim.cimclient.internal.wbem.CIMError;
import org.metricshub.wbem.sblim.cimclient.internal.cimxml.sax.node.AbstractMessageNode;
import org.xml.sax.InputSource;

/**
 * Class SAXHelper stores methods to help high level CIM-XML pull and SAX
 * parsing.
 */
public class SAXHelper {

	/**
	 * parseInvokeMethodResponse
	 * 
	 * @param pIs
	 * @param pOutArgs
	 * @param pDefPath
	 * @return Object, any kind of JSR48 value class
	 * @throws Exception
	 */
	public static Object parseInvokeMethodResponse(InputStreamReader pIs,
			CIMArgument<?>[] pOutArgs, CIMObjectPath pDefPath) throws Exception {
		XMLDefaultHandlerImpl hndlr = new XMLDefaultHandlerImpl(pDefPath);
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		try {
			saxParser.parse(new InputSource(pIs), hndlr);
		} catch (TrailerException e) {
			throw e.getWBEMException();
		}

		CIMNode cimNode = hndlr.getCIMNode();
		MessageNode msgNode = cimNode.getMessageNode();
		AbstractMessageNode absMsgNode = msgNode.getAbstractMessageNode();
		SimpleRspNode simpRspNode = (SimpleRspNode) absMsgNode;
		CIMError cimErr = simpRspNode.getCIMError();
		if (cimErr != null) throw new WBEMException(cimErr.getCode(), cimErr.getDescription(),
				cimErr.getCIMInstances());
		CIMArgument<?>[] outArgs = simpRspNode.getCIMArguments();
		if (pOutArgs != null && outArgs != null) {
			int len = Math.min(pOutArgs.length, outArgs.length);
			for (int i = 0; i < len; i++)
				pOutArgs[i] = outArgs[i];
		}
		return simpRspNode.getReturnValueCount() == 0 ? null : simpRspNode.readReturnValue();
	}

}
