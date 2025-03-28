/*
  (C) Copyright IBM Corp. 2009, 2013

  THIS FILE IS PROVIDED UNDER THE TERMS OF THE ECLIPSE PUBLIC LICENSE
  ("AGREEMENT"). ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS FILE
  CONSTITUTES RECIPIENTS ACCEPTANCE OF THE AGREEMENT.

  You can obtain a current copy of the Eclipse Public License from
  http://www.opensource.org/licenses/eclipse-1.0.php

  @author : Ramandeep S Arora, IBM, arorar@us.ibm.com
 * 
 * Flag       Date        Prog         Description
 * ---------------------------------------------------------------------------
 * 2860081    2009-09-17  raman_arora  Pull Enumeration Feature (DOM Parser)
 * 2878054    2009-10-25  raman_arora  Pull Enumeration Feature (PULL Parser)
 *    2666    2013-09-19  blaschke-oss CR12: Remove ENUMERATIONCONTEXT
 */

package org.metricshub.wbem.sblim.cimclient.internal.wbem;

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

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.metricshub.wbem.javax.cim.CIMArgument;
import org.metricshub.wbem.javax.cim.CIMObjectPath;
import org.metricshub.wbem.javax.wbem.CloseableIterator;
import org.metricshub.wbem.javax.wbem.WBEMException;
import org.metricshub.wbem.javax.wbem.client.EnumerateResponse;
import org.xml.sax.SAXException;

/**
 * Class EnumerateResponseDOM is responsible for all helper functions of DOM
 * parser related with EnumerateResponse.
 * 
 * @param <T>
 *            : Type Variable
 */
public class EnumerateResponseDOM<T> {

	private EnumerateResponse<T> enumResponse;

	/**
	 * EnumerateResponsePULL
	 * 
	 * @param pStream
	 * @param pPath
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws WBEMException
	 */
	@SuppressWarnings("unchecked")
	public EnumerateResponseDOM(InputStreamReader pStream, CIMObjectPath pPath) throws IOException,
			SAXException, ParserConfigurationException, WBEMException {

		String enumContext = null;

		Boolean endOfSequence = null;

		CloseableIterator<?> iter = new CloseableIteratorDOM(pStream, pPath);

		// check for error and CIMExceptions
		try {
			iter.hasNext();
		} catch (RuntimeException e) {
			iter.close();
			if (e.getCause() != null && e.getCause() instanceof WBEMException) { throw (WBEMException) e
					.getCause(); }
			throw e;
		}

		// get list of output CIMArguments i.e. enumContext and endOfSequence
		List<Object> pOutArgA = ((CloseableIteratorDOM) iter).getParamValues();

		// pOutArgA can never be null
		if (pOutArgA == null) { throw new IllegalArgumentException(
				"Output auguments not found during CIM-XML DOM parser"); }

		for (int i = 0; i < pOutArgA.size(); i++) {

			CIMArgument<?> cimArg = (CIMArgument<?>) pOutArgA.get(i);

			if (cimArg.getName().equals("EnumerationContext")) enumContext = (String) cimArg
					.getValue();
			else if (cimArg.getName().equals("EndOfSequence")) endOfSequence = (Boolean) cimArg
					.getValue();
			else throw new IllegalArgumentException(
					"Invalid argument : only EnumerationContext and EndOfSequence are allowed");
		}
		// EndOfSequence can never be null
		if (endOfSequence == null) { throw new IllegalArgumentException(
				"Invalid argument : EndOfSequence can never be null"); }

		// EnumerationContext can't be null if there is more data available
		if ((endOfSequence.booleanValue() == false) && (enumContext == null)) { throw new IllegalArgumentException(
				"Invalid argument : EnumerationContext cannot be null if there is more data available"); }

		this.enumResponse = new EnumerateResponse<T>(enumContext, (CloseableIterator<T>) iter,
				endOfSequence.booleanValue());
	}

	/**
	 * Returns enumResponse
	 * 
	 * @return The value of enumResponse.
	 */
	public EnumerateResponse<T> getEnumResponse() {
		return this.enumResponse;
	}

}
