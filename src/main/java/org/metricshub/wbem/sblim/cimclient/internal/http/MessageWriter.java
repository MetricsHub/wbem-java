/*
  (C) Copyright IBM Corp. 2005, 2013

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
 * 17620      2005-06-29  thschaef     eliminate ASCIIPrintStream1 in import statement       
 * 1535756    2006-08-07  lupusalex    Make code warning free
 * 1565892    2006-11-28  lupusalex    Make SBLIM client JSR48 compliant
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 * 3304058    2011-05-20  blaschke-oss Use same date format in change history
 * 3601894    2013-01-23  blaschke-oss Enhance HTTP and CIM-XML tracing
 *    2620    2013-02-23  blaschke-oss Chunked output broken
 */

package org.metricshub.wbem.sblim.cimclient.internal.http;

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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import org.metricshub.wbem.sblim.cimclient.internal.http.io.ASCIIPrintStream;
import org.metricshub.wbem.sblim.cimclient.internal.http.io.ChunkedOutputStream;
import org.metricshub.wbem.sblim.cimclient.internal.http.io.PersistentOutputStream;
import org.metricshub.wbem.sblim.cimclient.internal.logging.LogAndTraceBroker;

/**
 * Class MessageWriter is responsible for creating http messages
 *
 */
public class MessageWriter {
	HttpHeader iHeader = null;

	HttpServerMethod iMethod = null;

	HttpHeader iTrailer = null;

	boolean iChunked = false;

	boolean iPersistent = false;

	ASCIIPrintStream iRealOS;

	ASCIIPrintStream iClientOS;

	ByteArrayOutputStream iBufferedOS;

	/**
	 * Ctor.
	 *
	 * @param pStream
	 * @param pPersistent
	 * @param pChunked
	 */
	public MessageWriter(OutputStream pStream, boolean pPersistent, boolean pChunked) {
		this.iRealOS = new ASCIIPrintStream(pStream);
		this.iChunked = pChunked;
		this.iPersistent = pPersistent;
		this.iBufferedOS = new ByteArrayOutputStream();
		if (pChunked) {
			this.iClientOS =
				new ASCIIPrintStream(new ChunkedOutputStream(new PersistentOutputStream(this.iBufferedOS, pPersistent), 512));
		} else {
			this.iClientOS = new ASCIIPrintStream(new PersistentOutputStream(this.iBufferedOS, pPersistent));
		}
		this.iHeader = new HttpHeader();
		this.iMethod =
			new HttpServerMethod(HttpConnectionHandler.MAJOR_VERSION, HttpConnectionHandler.MINOR_VERSION, 200, "OK");
	}

	/**
	 * Resets the stream
	 */
	public void reset() {
		this.iBufferedOS.reset();
	}

	/**
	 * Sets the http header
	 *
	 * @param header
	 *            The new value
	 */
	public void setHeader(HttpHeader header) {
		this.iHeader = header;
	}

	/**
	 * Sets the http server method
	 *
	 * @param method
	 *            The new value
	 */
	public void setMethod(HttpServerMethod method) {
		this.iMethod = method;
	}

	/**
	 * Returns the http header
	 *
	 * @return The http header
	 */
	public HttpHeader getHeader() {
		return this.iHeader;
	}

	/**
	 * Returns the http server method
	 *
	 * @return The http server method
	 */
	public HttpServerMethod getMethod() {
		return this.iMethod;
	}

	/**
	 * Returns the output stream
	 *
	 * @return The output stream
	 */
	public ASCIIPrintStream getOutputStream() {
		return this.iClientOS;
	}

	/**
	 * Write the message and flushes the streams
	 *
	 * @throws IOException
	 */
	public void close() throws IOException {
		this.iMethod.write(this.iRealOS);
		this.iRealOS.flush();
		if (!this.iChunked) this.iHeader.removeField("Transfer-Encoding"); else this.iHeader.addField(
				"Transfer-Encoding",
				"chunked"
			);
		if (this.iPersistent) this.iHeader.addField("Connection", "Keep-iAlive"); else this.iHeader.addField(
				"Connection",
				"close"
			);

		this.iHeader.addField("Content-Type", "application/xml;charset=\"utf-8\"");
		if (!this.iChunked) this.iHeader.addField("Content-length", Integer.toString(this.iBufferedOS.size()));
		LogAndTraceBroker.getBroker().trace(Level.FINER, "Indication Response HTTP Headers= " + this.iHeader.toString());
		this.iHeader.write(this.iRealOS);
		this.iRealOS.flush();
		if (this.iChunked) this.iClientOS.close();
		this.iBufferedOS.writeTo(this.iRealOS);
		if (this.iChunked && (this.iTrailer != null)) {
			LogAndTraceBroker
				.getBroker()
				.trace(Level.FINER, "Indication Response HTTP Trailer Headers= " + this.iTrailer.toString());
			this.iTrailer.write(this.iRealOS);
		}
		this.iRealOS.flush();
	}

	/**
	 * Sets the trailer
	 *
	 * @param pTrailer
	 *            The new value
	 */
	public void setTrailer(HttpHeader pTrailer) {
		this.iTrailer = pTrailer;
	}
}
