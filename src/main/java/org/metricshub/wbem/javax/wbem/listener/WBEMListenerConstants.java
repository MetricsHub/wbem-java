/*
  (C) Copyright IBM Corp. 2012

  THIS FILE IS PROVIDED UNDER THE TERMS OF THE ECLIPSE PUBLIC LICENSE
  ("AGREEMENT"). ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS FILE
  CONSTITUTES RECIPIENTS ACCEPTANCE OF THE AGREEMENT.

  You can obtain a current copy of the Eclipse Public License from
  http://www.opensource.org/licenses/eclipse-1.0.php

  @author : Dave Blaschke, blaschke@us.ibm.com
 * 
 * Change History
 * Flag       Date        Prog         Description
 * -------------------------------------------------------------------------------
 * 3496380    2012-03-02  blaschke-oss JSR48 1.0.0: add new WBEMListenerConstants
 */
package org.metricshub.wbem.javax.wbem.listener;

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
 * This class defines the constants used for <code>WBEMListener</code>
 * configuration. If a property is tagged as optional an implementation may or
 * may not support the property. If the implementation does not support the
 * property, it must throw an <code>IllegalArgumentException</code> if the
 * property value is attempted to be set.
 */
public class WBEMListenerConstants {

	/**
	 * Use this property to set the list of ciphers the listener will support.
	 * Setting this value to null will use the default set of ciphers provided
	 * by the version of Java being used. Optional.
	 */
	public static final String PROP_LISTENER_CIPHERS = "javax.wbem.listener.ciphers";

	/**
	 * This property along with the PROP_LISTENER_KEYSTORE_PASSWORD and
	 * PROP_LISTENER_TRUSTSTORE are used to configure mutual authentication.
	 * This property is used to provide the filename of the keystore. The path
	 * can be relative or full. Optional.
	 */
	public static final String PROP_LISTENER_KEYSTORE = "javax.wbem.listener.keyStore";

	/**
	 * This property along with the PROP_LISTENER_KEYSTORE and
	 * PROP_LISTENER_TRUSTSTORE are used to configure mutual authentication.
	 * This property is used to provide the password of the keystore. Optional.
	 */
	public static final String PROP_LISTENER_KEYSTORE_PASSWORD = "javax.wbem.listener.keyStorePassword";

	/**
	 * This property along with the PROP_LISTENER_KEYSTORE and
	 * PROP_LISTENER_KEYSTORE_PASSWORD are used to configure mutual
	 * authentication. This property is used to provide the filename of the
	 * truststore. The path can be relative or full. Optional.
	 */
	public static final String PROP_LISTENER_TRUSTSTORE = "javax.wbem.listener.trustStore";
}
