/*
  (C) Copyright IBM Corp. 2006, 2012

  THIS FILE IS PROVIDED UNDER THE TERMS OF THE ECLIPSE PUBLIC LICENSE
  ("AGREEMENT"). ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS FILE
  CONSTITUTES RECIPIENTS ACCEPTANCE OF THE AGREEMENT.

  You can obtain a current copy of the Eclipse Public License from
  http://www.opensource.org/licenses/eclipse-1.0.php

  @author : Endre Bak, ebak@de.ibm.com
 * 
 * Change History
 * Flag       Date        Prog         Description
 *-------------------------------------------------------------------------------------------------
 *            2006-04-25  ebak         Initial commit
 *            2006-04-27  wolfalex     Full implementation
 * 1737141    2007-06-18  ebak         Sync up with JSR48 evolution
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 * 2899389    2009-11-18  blaschke-oss Password maximum length of 16?
 * 2959264    2010-02-25  blaschke-oss Sync up javax.client.* javadoc with JSR48 1.0.0
 * 2978722    2010-03-29  blaschke-oss PasswordCredential(char[]) constructor is wrong
 * 3496301    2012-03-02  blaschke-oss Sync up javax.* javadoc with JSR48 1.0.0 Final
 */

package org.metricshub.wbem.javax.wbem.client;

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

//Sync'd against JSR48 1.0.0 javadoc (build 1.6.0_18) on Thu Mar 01 12:21:26 EST 2012
/**
 * <code>PasswordCredential</code> implements a password based credential. This
 * is used with <code>UserPrincipal</code>. <code>PasswordCredential</code>
 * includes the password and optionally the host information for which the
 * password is used to authenticate the <code>UserPrincipal</code>.
 * 
 */
public class PasswordCredential extends Object {

	private String iPw;

	private String iHostname;

	/**
	 * Creates a <code>PasswordCredential</code> using the supplied password.
	 * 
	 * @param pCredential
	 *            The user login password in clear text.
	 * @throws IllegalArgumentException
	 *             If the credential is null.
	 */
	public PasswordCredential(char[] pCredential) {
		if (pCredential != null) {
			this.iPw = new String(pCredential);
		} else {
			throw new IllegalArgumentException("Invalid password");
		}
	}

	/**
	 * Creates a <code>PasswordCredential</code> using the supplied password.
	 * 
	 * @param pCredential
	 *            The user login password in clear text.
	 * @throws IllegalArgumentException
	 *             If the credential is null.
	 */
	public PasswordCredential(String pCredential) {
		if (pCredential != null) {
			this.iPw = pCredential;
		} else {
			throw new IllegalArgumentException("Invalid password");
		}
	}

	/**
	 * Creates a <code>PasswordCredential</code> using the supplied password and
	 * host name.
	 * 
	 * @param pCredential
	 *            The user login password in clear text.
	 * @param pHostname
	 *            The host name information for which the password is used to
	 *            authenticate.
	 * @throws IllegalArgumentException
	 *             If the credential is null.
	 */
	public PasswordCredential(String pCredential, String pHostname) {
		if (pCredential != null) {
			this.iPw = pCredential;
		} else {
			throw new IllegalArgumentException("Invalid password");
		}
		this.iHostname = pHostname;
	}

	/**
	 * Get the host name for which the password is used to authenticate.
	 * 
	 * @return The host name.
	 */
	public String getHostName() {
		return this.iHostname;
	}

	/**
	 * Return the user login password in clear text.
	 * 
	 * @return The user login password.
	 */
	public char[] getUserPassword() {
		return this.iPw.toCharArray();
	}

}
