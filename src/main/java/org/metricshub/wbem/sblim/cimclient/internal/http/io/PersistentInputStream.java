/*
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
 * 1565892    2006-11-28  lupusalex    Make SBLIM client JSR48 compliant
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 */
package org.metricshub.wbem.sblim.cimclient.internal.http.io;

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

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class PersistentInputStream implements an input stream for which close() can
 * be disabled.
 * 
 */
public class PersistentInputStream extends FilterInputStream {

	boolean iClosable = false;

	boolean iClosed = false;

	/**
	 * Ctor.
	 * 
	 * @param pStream
	 *            The underlying stream
	 */
	public PersistentInputStream(InputStream pStream) {
		this(pStream, false);
	}

	/**
	 * Ctor.
	 * 
	 * @param pStream
	 *            The underlying stream
	 * @param pClosable
	 *            If <code>false</code> this stream will ignore calls to the
	 *            close() method.
	 */
	public PersistentInputStream(InputStream pStream, boolean pClosable) {
		super(pStream);
		this.iClosable = pClosable;
	}

	@Override
	public synchronized void close() throws IOException {
		if (!this.iClosed) {
			this.iClosed = true;
			if (this.iClosable) this.in.close();
		} else throw new IOException("Error while closing the input stream. It was already closed");
	}
}
