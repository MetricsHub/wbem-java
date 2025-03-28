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
 * 1565892    2006-11-05  ebak         Make SBLIM client JSR48 compliant
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 */

package org.metricshub.wbem.sblim.cimclient.internal.uri;

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

import org.metricshub.wbem.sblim.cimclient.internal.util.MOF;
import org.metricshub.wbem.sblim.cimclient.internal.util.Util;

/**
 * Class UntypedReferenceValue encapsulates an untyped reference value.
 */
public class ReferenceValue extends Value implements QuotedValue {

	private URI iRef;

	/**
	 * Ctor.
	 * 
	 * @param pRef
	 */
	public ReferenceValue(URI pRef) {
		this.iRef = pRef;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.iRef.toString();
	}

	/**
	 * @see QuotedValue#toQuotedString()
	 */
	public String toQuotedString() {
		return Util.quote(this.iRef.toString());
	}

	/**
	 * getRef
	 * 
	 * @return <code>URI</code>
	 */
	public URI getRef() {
		return this.iRef;
	}

	/**
	 * @see Value#getTypeInfo()
	 */
	@Override
	public String getTypeInfo() {
		return MOF.REFERENCE;
	}

}
