/*
  (C) Copyright IBM Corp. 2006, 2013

  THIS FILE IS PROVIDED UNDER THE TERMS OF THE ECLIPSE PUBLIC LICENSE
  ("AGREEMENT"). ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS FILE
  CONSTITUTES RECIPIENTS ACCEPTANCE OF THE AGREEMENT.

  You can obtain a current copy of the Eclipse Public License from
  http://www.opensource.org/licenses/eclipse-1.0.php

  @author : Alexander Wolf-Reber, a.wolf-reber@de.ibm.com
 * 
 * Flag       Date        Prog         Description
 * -------------------------------------------------------------------------------
 * 1565892    2006-11-08  lupusalex    Make SBLIM client JSR48 compliant
 * 1688273    2007-04-19  lupusalex    Full support of HTTP trailers
 * 1815707    2007-10-30  ebak         TLS support
 * 1827728    2007-11-12  ebak         embeddedInstances: attribute EmbeddedObject not set
 * 1848607    2007-12-11  ebak         Strict EmbeddedObject types
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2372030    2008-12-01  blaschke-oss Add property to control synchronized SSL handshaking
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 * 2763216    2009-04-14  blaschke-oss Code cleanup: visible spelling/grammar errors
 * 2846231    2009-09-23  rgummada     connection failure on CIMOM w/o user/pw
 * 2930341    2010-01-12  blaschke-oss Sync up WBEMClientConstants with JSR48 1.0.0
 * 2957387    2010-03-03  blaschke-oss EmbededObject XML attribute must not be all uppercases
 * 2970881    2010-03-15  blaschke-oss Add property to control EmbeddedObject case
 * 3046073    2010-09-07  blaschke-oss Performance hit due to socket conn. creation with timeout
 * 3111718    2010-11-18  blaschke-oss org.sblim.cimclient SSL Code is using the wrong SSL Property
 * 3185763    2011-02-25  blaschke-oss Reliable indication support - Phase 1
 * 3195069    2011-02-28  blaschke-oss Need support to disable SSL Handshake
 * 3197423    2011-03-02  blaschke-oss Server authentication with PegasusLocalAuthInfo failing
 * 3277928    2011-04-06  blaschke-oss CIM-XML tracing cannot be enabled in the field
 * 3206904    2011-05-03  blaschke-oss Indication listener deadlock causes JVM to run out sockets
 * 3288721    2011-05-20  blaschke-oss Need the function of indication reordering
 * 3459036    2011-12-13  blaschke-oss Linked list for RI queue not efficient for many LDs
 * 3485074    2012-02-06  blaschke-oss An Indication trace request
 * 3492246    2012-02-23  blaschke-oss Rename new indication trace property
 * 3492214    2012-02-23  blaschke-oss Add a SenderIPAddress property indications
 * 3492224    2012-02-23  blaschke-oss Need two different timeouts for Socket connections
 * 3521157    2012-05-10  blaschke-oss JSR48 1.0.0: PROP_ENABLE_*_LOGGING is Level, not 0/1
 * 3524050    2012-06-06  blaschke-oss Improve WWW-Authenticate in HTTPClient.java
 * 3536399    2012-08-25  hellerda     Add client/listener peer authentication properties
 * 3572993    2012-10-01  blaschke-oss parseDouble("2.2250738585072012e-308") DoS vulnerability
 * 3598613    2013-01-11  blaschke-oss different data type in cim instance and cim object path
 *    2618    2013-02-27  blaschke-oss Need to add property to disable weak cipher suites for the secure indication
 *    2628    2013-03-26  blaschke-oss Limit size of LinkedList of CIMEvents to be dispatched
 *    2635    2013-05-16  blaschke-oss Slowloris DoS attack for CIM indication listener port
 *    2642    2013-05-21  blaschke-oss Seperate properties needed for cim client and listener to filter out ciphers
 *    2647    2013-07-01  blaschke-oss Add two ssl protocol properties for http server and client
 *    2654    2013-07-29  blaschke-oss Check jcc idle time with CIMOM keepalive timeout to avoid EOF
 *    2151    2013-08-20  blaschke-oss gzip compression not supported
 *    2711    2013-11-13  blaschke-oss LOCALNAMESPACEPATH allows 0 NAMESPACE children
 */
package org.metricshub.wbem.sblim.cimclient;

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

/**
 * The interface WBEMConfigurationProperties contains the names of all
 * configuration properties that are recognized by the CIM Client.
 *
 */
public interface WBEMConfigurationProperties {
	/**
	 * A URL string giving the location of the CIM client config file. <br />
	 * <br />
	 * By default the SBLIM CIM Client looks for
	 * <ul>
	 * <li>file:sblim-cim-client2.properties</li>
	 * <li>file:%USER_HOME%/sblim-cim-client2.properties</li>
	 * <li>file:/etc/java/sblim-cim-client2.properties</li>
	 * </ul>
	 * The first file found will be used. The default search list is not applied
	 * if this property is set, even if the given URL does not exist.<br />
	 */
	public static final String CONFIG_URL = "sblim.wbem.configURL";

	/**
	 * Sets the minimum level for messages to be written to the log file.<br />
	 * <br />
	 * Type: <code>Discrete</code><br />
	 * Recognition: <code>Startup</code><br />
	 * Range: <code>OFF, SEVERE, WARNING, INFO, CONFIG, ALL</code><br />
	 * Default: <code>OFF</code>, which disables file logging completely.
	 */
	public static final String LOG_FILE_LEVEL = "sblim.wbem.logFileLevel";

	/**
	 * A string specifying the location of the log file. The string may include
	 * the following special components that will be replaced at runtime:<br />
	 * <ul>
	 * <table border="1">
	 * <tr>
	 * <td>/</td>
	 * <td>the local pathname separator</td>
	 * </tr>
	 * <tr>
	 * <td>%t</td>
	 * <td>the system temporary directory</td>
	 * </tr>
	 * <tr>
	 * <td>%h</td>
	 * <td>the value of the "user.home" system property</td>
	 * </tr>
	 * <tr>
	 * <td>%g</td>
	 * <td>the generation number to distinguish rotated logs</td>
	 * </tr>
	 * <tr>
	 * <td>%u</td>
	 * <td>a unique number to resolve conflicts</td>
	 * </tr>
	 * <tr>
	 * <td>%%</td>
	 * <td>translates to a single percent sign "%"</td>
	 * </tr>
	 * </table>
	 * </ul>
	 * Thus for example a pattern of <code>%t/java%g.log</code> with a count of
	 * 2 would typically cause log files to be written on Unix to
	 * /var/tmp/java2.log<br />
	 * <br />
	 * Type: <code>String</code><br />
	 * Recognition: <code>Startup</code><br />
	 * Default: <code>%t/cimclient_log_%g.txt</code>.
	 */
	public static final String LOG_FILE_LOCATION = "sblim.wbem.logFileLocation";

	/**
	 * Sets the maximum size in bytes of a single log file. When the limit is
	 * reached a new file is created. A limit of zero will create a new log file
	 * for every log record !<br />
	 * <br />
	 * Type: <code>Integer</code><br />
	 * Recognition: <code>Startup</code><br />
	 * Range: <code>0 .. Integer.MAX_VALUE</code><br />
	 * Default: <code>100.000</code><br />
	 */
	public static final String LOG_FILE_SIZE_LIMIT = "sblim.wbem.logFileSizeLimit";

	/**
	 * Sets the number of log files to cycle through. When the number is
	 * exceeded the oldest file is dropped.<br />
	 * <br />
	 * Type: <code>Integer</code><br />
	 * Recognition: <code>Startup</code><br />
	 * Range: <code>1 .. Integer.MAX_VALUE</code><br />
	 * Default: <code>5</code><br />
	 */
	public static final String LOG_FILE_COUNT = "sblim.wbem.logFileCount";

	/**
	 * Sets the minimum level for messages to be written to the console logger
	 * file.<br />
	 * <br />
	 * Type: <code>Discrete</code><br />
	 * Recognition: <code>Startup</code><br />
	 * Range: <code>OFF, SEVERE, WARNING, INFO, CONFIG, ALL</code><br />
	 * Default: <code>OFF</code>, which disables console logging completely.
	 */
	public static final String LOG_CONSOLE_LEVEL = "sblim.wbem.logConsoleLevel";

	/**
	 * Sets the type of the console logger. Maybe either message log or trace
	 * log.<br />
	 * <br />
	 * Type: <code>Discrete</code><br />
	 * Recognition: <code>Startup</code><br />
	 * Range: <code>MESSAGE, TRACE</code><br />
	 * Default: <code>MESSAGE</code>.
	 */
	public static final String LOG_CONSOLE_TYPE = "sblim.wbem.logConsoleType";

	/**
	 * Sets the minimum level for messages to be written to the trace file.<br />
	 * <br />
	 * Type: <code>Discrete</code><br />
	 * Recognition: <code>Startup</code><br />
	 * Range:
	 * <code>OFF, SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST, ALL</code><br />
	 * Default: <code>OFF</code>, which disables file tracing completely<br />
	 */
	public static final String TRACE_FILE_LEVEL = "sblim.wbem.traceFileLevel";

	/**
	 * A string specifying the location of the trace file. The string may
	 * include the following special components that will be replaced at
	 * runtime:<br />
	 * <ul>
	 * <table border="1">
	 * <tr>
	 * <td>/</td>
	 * <td>the local pathname separator</td>
	 * </tr>
	 * <tr>
	 * <td>%t</td>
	 * <td>the system temporary directory</td>
	 * </tr>
	 * <tr>
	 * <td>%h</td>
	 * <td>the value of the "user.home" system property</td>
	 * </tr>
	 * <tr>
	 * <td>%g</td>
	 * <td>the generation number to distinguish rotated logs</td>
	 * </tr>
	 * <tr>
	 * <td>%u</td>
	 * <td>a unique number to resolve conflicts</td>
	 * </tr>
	 * <tr>
	 * <td>%%</td>
	 * <td>translates to a single percent sign "%"</td>
	 * </tr>
	 * </table>
	 * </ul>
	 * Thus for example a pattern of <code>%t/java%g.log</code> with a count of
	 * 2 would typically cause log files to be written on Unix to
	 * /var/tmp/java2.log<br />
	 * <br />
	 * Type: <code>String</code><br />
	 * Recognition: <code>Startup</code><br />
	 * Default: <code>%t/cimclient_trace_%g.txt</code><br />
	 */
	public static final String TRACE_FILE_LOCATION = "sblim.wbem.traceFileLocation";

	/**
	 * Sets the maximum size in bytes of a single log file. When the limit is
	 * reached a new file is created. A limit of zero creates a new file for
	 * each trace record !<br />
	 * <br />
	 * Type: <code>Integer</code><br />
	 * Recognition: <code>Startup</code><br />
	 * Range: <code>0 .. Integer.MAX_VALUE</code><br />
	 * Default: <code>1.000.000</code><br />
	 */
	public static final String TRACE_FILE_SIZE_LIMIT = "sblim.wbem.traceFileSizeLimit";

	/**
	 * Sets the number of log files to cycle through. When the number is
	 * exceeded the oldest file is dropped.<br />
	 * <br />
	 * Type: <code>Integer</code><br />
	 * Recognition: <code>Startup</code><br />
	 * Range: <code>1 .. Integer.MAX_VALUE</code><br />
	 * Default: <code>5</code><br />
	 */
	public static final String TRACE_FILE_COUNT = "sblim.wbem.traceFileCount";

	/**
	 * The timeout for http requests. A timeout of zero is interpreted as
	 * infinite timeout.<br />
	 * <br />
	 * Type: <code>Integer</code><br />
	 * Unit: <code>Milliseconds</code><br />
	 * Recognition: <code>Anytime</code><br />
	 * Range: <code>0 .. Integer.MAX_VALUE</code><br />
	 * Default: <code>0</code><br />
	 */
	public static final String HTTP_TIMEOUT = "sblim.wbem.httpTimeout";

	/**
	 * The size of the internal http connection pools. Each
	 * <code>WBEMClient</code> instance has it's own http connection pool. A
	 * positive value defines the number of connections, zero that no connection
	 * will be reused, and -1 all connections will be reused (when it's
	 * possible).<br />
	 * <br />
	 * Type: <code>Integer</code><br />
	 * Recognition: <code>Anytime</code><br />
	 * Range: <code>-1, 0, 1 .. Integer.MAX_VALUE</code><br />
	 * Default: <code>16</code><br />
	 */
	public static final String HTTP_POOL_SIZE = "sblim.wbem.httpPoolSize";

	/**
	 * The Java class name of the authentication module to use for http
	 * authentication. <br />
	 * <br />
	 * Type: <code>String</code><br />
	 * Recognition: <code>On next authentication</code><br />
	 * Range:
	 *
	 * <code>org.sblim.cimclient.internal.http.WwwAuthInfo, org.sblim.cimclient.internal.http.PegasusLocalAuthInfo or any self-written subclass of org.sblim.cimclient.internal.http.AuthorizationInfo</code>
	 * <br />
	 * Default: <code>org.sblim.cimclient.internal.http.WwwAuthInfo</code><br />
	 */
	public static final String HTTP_AUTHENTICATION_MODULE = "sblim.wbem.httpAuthModule";

	/**
	 * The WWW-Authenticate information to use when sending the first request to
	 * a server. <br />
	 * <br />
	 * Note: This string must exactly match what the server returns in the<br />
	 * WWW-Authenticate field of an HTTP 401 response when authentication<br />
	 * fails. The following two strings are examples:<br />
	 * <br />
	 *
	 *
	 * Basic realm=&quot;Secure Area&quot;
	 * Digest realm=&quot;testrealm@host.com&quot;,qop=&quot;auth,auth-int&quot;,nonce=&quot;dcd98b7102dd2f0e8b11d0f600bfb0c093&quot;,opaque=&quot;5ccc069c403ebaf9f0171e9517f40e41&quot;
	 *
	 *
	 * Type: <code>String</code><br />
	 * Recognition: <code>On next authentication</code><br />
	 * Range: <code>Basic, Digest</code><br />
	 * Default: <code>none</code><br />
	 */
	public static final String HTTP_WWW_AUTHENTICATE_INFO = "sblim.wbem.httpWwwAuthenticateInfo";

	/**
	 * Specifies if MPOST is used for transmitting http messages. If false, POST
	 * is used.<br />
	 * <br />
	 * Type: <code>Boolean</code><br />
	 * Recognition: <code>Anytime</code><br />
	 * Range: <code>true, false</code><br />
	 * Default: <code>true</code><br />
	 */
	public static final String HTTP_USE_MPOST = "sblim.wbem.httpMPOST";

	/**
	 * Specifies if chunking is used for transmitting http messages.<br />
	 * <br />
	 * Type: <code>Boolean</code><br />
	 * Recognition: <code>Anytime</code><br />
	 * Range: <code>true, false</code><br />
	 * Default: <code>true</code><br />
	 */
	public static final String HTTP_USE_CHUNKING = "sblim.wbem.httpChunking";

	/**
	 * Specifies the http protocol version to use. This option is useful if the
	 * protocol negotiation fails.<br />
	 * <br />
	 * Type: <code>String</code><br />
	 * Recognition: <code>Anytime</code><br />
	 * Range: <code>1.0, 1.1</code><br />
	 * Default: <code>1.1</code><br />
	 */
	public static final String HTTP_VERSION = "sblim.wbem.httpVersion";

	/**
	 * Specifies how often the client will retry to connect to a CIMOM which
	 * refused the connection in the first place.<br />
	 * <br />
	 * Type: <code>Integer</code><br />
	 * Recognition: <code>Anytime</code><br />
	 * Range: <code>0 .. Integer.MAX_VALUE</code><br />
	 * Default: <code>0</code><br />
	 */
	public static final String HTTP_CONNECTION_RETRIES = "sblim.wbem.httpConnectionRetries";

	/**
	 * Specifies if the client will discard and request again http documents
	 * with less than a given number of bytes.<br />
	 * <br />
	 * Type: <code>Boolean</code><br />
	 * Recognition: <code>Anytime</code><br />
	 * Range: <code>true, false</code><br />
	 * Default: <code>false</code><br />
	 */
	public static final String HTTP_ENABLE_CONTENT_LENGTH_RETRY = "sblim.wbem.httpEnableContentLengthRetry";

	/**
	 * Specifies the threshold above which a http document is regarded as valid
	 * by the content length retry algorithm.<br />
	 * <br />
	 * Type: <code>Integer</code><br />
	 * Recognition: <code>Anytime</code><br />
	 * Range: <code>0 .. Integer.MAX_VALUE</code><br />
	 * Default: <code>50</code><br />
	 */
	public static final String HTTP_CONTENT_LENGTH_THRESHOLD = "sblim.wbem.httpContentLengthThreshold";

	/**
	 * The file path of the SSL keystore.<br />
	 * <br />
	 * Type: <code>String</code><br />
	 * Recognition: <code>On next SSL connection</code><br />
	 * Default: none<br />
	 */
	public static final String KEYSTORE_PATH = "javax.net.ssl.keyStore";

	/**
	 * The type of the keystore.<br />
	 * <br />
	 * Type: <code>String</code><br />
	 * Recognition: <code>On next SSL connection</code><br />
	 * Range: <code>PKCS12, JKS, ...</code><br />
	 * Default: <code>JKS</code><br />
	 */
	public static final String KEYSTORE_TYPE = "javax.net.ssl.keyStoreType";

	/**
	 * The password of the keystore.<br />
	 * <br />
	 * Type: <code>String</code><br />
	 * Recognition: <code>On next SSL connection</code><br />
	 * Default: none<br />
	 */
	public static final String KEYSTORE_PASSWORD = "javax.net.ssl.keyStorePassword";

	/**
	 * The file path of the SSL truststore.<br />
	 * <br />
	 * Type: <code>String</code><br />
	 * Recognition: <code>On next SSL connection</code><br />
	 * Default: none<br />
	 */
	public static final String TRUSTSTORE_PATH = "javax.net.ssl.trustStore";

	/**
	 * The type of the truststore.<br />
	 * <br />
	 * Type: <code>String</code><br />
	 * Recognition: <code>On next SSL connection</code><br />
	 * Range: <code>PKCS12, JKS, ...</code><br />
	 * Default: <code>JKS</code><br />
	 */
	public static final String TRUSTSTORE_TYPE = "javax.net.ssl.trustStoreType";

	/**
	 * The password of the truststore.<br />
	 * <br />
	 * Type: <code>String</code><br />
	 * Recognition: <code>On next SSL connection</code><br />
	 * Default: none<br />
	 */
	public static final String TRUSTSTORE_PASSWORD = "javax.net.ssl.trustStorePassword";

	/**
	 * The provider to use for creation of SSL client sockets.<br />
	 * <br />
	 * <em>Security property: JRE global access via <code>Security.setProperty()</code> and <code>Security.getProperty()</code> !</em>
	 * <br />
	 * <br />
	 * Type: <code>Java class name</code><br />
	 * Recognition: <code>On next SSL connection</code><br />
	 * Default: <code>Security.getProviders("SSLContext.SSL")</code><br />
	 */
	public static final String SSL_SOCKET_PROVIDER = "sblim.wbem.sslSocketProvider";

	/**
	 * The provider to use for creation of SSL server sockets.<br />
	 * <br />
	 * <em>Security property: JRE global access via <code>Security.setProperty()</code> and <code>Security.getProperty()</code> !</em>
	 * <br />
	 * <br />
	 * Type: <code>Java class name</code><br />
	 * Recognition: <code>On next SSL connection</code><br />
	 * Default: <code>Security.getProviders("SSLContext.SSL")</code><br />
	 */
	public static final String SSL_SERVER_SOCKET_PROVIDER = "sblim.wbem.sslServerSocketProvider";

	/**
	 * The protocol used for SSLContext.getInstance(String protocol). For
	 * IBMJSSE2 provider it can be "SSL_TLS".<br/>
	 * <br/>
	 * Security property: JRE global access via
	 * <code>Security.setProperty()</code> and
	 * <code>Security.getProperty()</code><br/>
	 * Recognition: <code>On next SSL connection</code><br/>
	 * Default: "SSL"
	 */
	public static final String SSL_PROTOCOL = "ssl.Protocol";

	/**
	 * The protocol used for SSLContext.getInstance(String protocol) by a
	 * client. This property overrides any value set via the ssl.Protocol
	 * property.<br/>
	 * <br/>
	 * Recognition: <code>On next SSL connection</code><br/>
	 * Default: none
	 */
	public static final String SSL_CLIENT_PROTOCOL = "sblim.wbem.sslClientProtocol";

	/**
	 * The protocol used for SSLContext.getInstance(String protocol) by a
	 * listener. This property overrides any value set via the ssl.Protocol
	 * property.<br/>
	 * <br/>
	 * Recognition: <code>On next SSL connection</code><br/>
	 * Default: none
	 */
	public static final String SSL_LISTENER_PROTOCOL = "sblim.wbem.sslListenerProtocol";

	/**
	 * The key manager factory algorithm name.<br />
	 * <br />
	 * <em>Security property: JRE global access via <code>Security.setProperty()</code> and <code>Security.getProperty()</code> !</em>
	 * <br />
	 * <br />
	 * Type: <code>String</code><br />
	 * Recognition: <code>On next SSL connection</code><br />
	 * Range: <code>IbmX509, SunX509, ...</code><br />
	 * Default: <code>JRE specific</code><br />
	 */
	public static final String SSL_KEYMANAGER_ALGORITHM = "ssl.KeyManagerFactory.algorithm";

	/**
	 * The trust manager factory algorithm name.<br />
	 * <br />
	 * <em>Security property: JRE global access via <code>Security.setProperty()</code> and <code>Security.getProperty()</code> !</em>
	 * <br />
	 * <br />
	 * Type: <code>String</code><br />
	 * Recognition: <code>On next SSL connection</code><br />
	 * Range: <code>IbmX509, SunX509, ...</code><br />
	 * Default: <code>JRE specific</code><br />
	 */
	public static final String SSL_TRUSTMANAGER_ALGORITHM = "ssl.TrustManagerFactory.algorithm";

	/**
	 * Determines if a HTTPS client will attempt to authenticate the server
	 * (i.e. CIMOM) by verifying the server certificate.<br />
	 * <br />
	 * If false, do not attempt verification. If true, the client will attempt
	 * to verify the server certificate against the contents of the truststore;
	 * in this case a valid path must be defined in "javax.net.ssl.trustStore"
	 * or no connection will be permitted. <br />
	 * <br />
	 * Type: <code>Boolean</code><br />
	 * Recognition: On initialization of a new client<br />
	 * Default: <code>false</code><br />
	 */
	public static final String SSL_CLIENT_PEER_VERIFICATION = "sblim.wbem.sslClientPeerVerification";

	/**
	 * Determines how a HTTPS listener will handle authentication of a client
	 * (i.e. indication sender):<br />
	 * <ul>
	 * <table border="1">
	 * <tr>
	 * <td>ignore</td>
	 * <td>do not examine the client certificate</td>
	 * </tr>
	 * <tr>
	 * <td>accept</td>
	 * <td>examine client certificate if presented; do not fail if not presented
	 * </td>
	 * </tr>
	 * <tr>
	 * <td>require</td>
	 * <td>examine client certificate; fail if not presented</td>
	 * </tr>
	 * </table>
	 * </ul>
	 * If set to "ignore", do not attempt verification. If set to "accept" or
	 * "require", the listener will attempt to verify the sender against the
	 * contents of the truststore; in this case a valid path must be defined in
	 * "javax.net.ssl.trustStore" or no connection will be permitted. <br />
	 * <br />
	 * Type: <code>String</code><br />
	 * Recognition: On next call to addListener()<br />
	 * Default: <code>ignore</code><br />
	 */
	public static final String SSL_LISTENER_PEER_VERIFICATION = "sblim.wbem.sslListenerPeerVerification";

	/**
	 * The comma-separated list of cipher suites that are to be disabled by the
	 * client when connecting via an SSL socket. In general, this is the list of
	 * cipher suites considered "too weak" for use in a particular environment.<br />
	 * <br />
	 * Type: <code>String</code><br />
	 * Recognition: <code>On initialization of a new client</code><br />
	 * Default: <code>none</code><br />
	 */
	public static final String SSL_CLIENT_CIPHER_SUITES_TO_DISABLE = "sblim.wbem.sslClientCipherSuitesToDisable";

	/**
	 * The comma-separated list of cipher suites that are to be disabled by the
	 * listener when connecting via an SSL socket. In general, this is the list
	 * of cipher suites considered "too weak" for use in a particular
	 * environment.<br />
	 * <br />
	 * Type: <code>String</code><br />
	 * Recognition: <code>On next call to addListener()</code><br />
	 * Default: <code>none</code><br />
	 */
	public static final String SSL_LISTENER_CIPHER_SUITES_TO_DISABLE = "sblim.wbem.sslListenerCipherSuitesToDisable";

	/**
	 * Specifies the XML parser for parsing CIM-XML responses.<br />
	 * The SAX parser is the default choice since it is fast, resource saving
	 * and interoperable. The streaming algorithm of the PULL parser uses the
	 * fewest possible resources but at the prize to keep the CIMOMs response
	 * open for a long time. That works with many but not all CIMOMs. The DOM
	 * parser is slow and resource hungry but nice to debug.<br />
	 * <br />
	 * Type: <code>Discrete</code><br />
	 * Recognition: <code>Anytime</code><br />
	 * Range: <code>DOM, PULL, SAX</code><br />
	 * Default: <code>SAX</code><br />
	 */
	public static final String CIMXML_PARSER = "sblim.wbem.cimxmlParser";

	/**
	 * Enables or disables tracing of CIM-XML communication. The trace is sent
	 * to an output stream the application has to set via the LogAndTraceManager
	 * class.<br />
	 * <br />
	 * Type: <code>Boolean</code><br />
	 * Recognition: <code>Anytime</code><br />
	 * Range: <code>true, false</code><br />
	 * Default: <code>false</code><br />
	 */
	public static final String CIMXML_TRACING = "sblim.wbem.cimxmlTracing";

	/**
	 * Specifies the stream to use for tracing CIM-XML communication in the
	 * event the application does not set one via the LogAndTraceManager class.
	 * This stream can either be standard output (System.out), standard error
	 * output (System.err) or a filename to be opened by the client.
	 *
	 * Note: This property has no effect unless sblim.wbem.cimxmlTracing is set
	 * to true.
	 *
	 * Note: This property has no effect if the application already set the
	 * stream prior to client initialization. If the application sets the stream
	 * after client initialization, the stream specified by this property is
	 * overridden.
	 *
	 * Note: If a filename is specified, it is opened and all CIM-XML
	 * communication is written to it - no checks are made for an existing file
	 * or for filling up the disk. USE WITH CAUTION.
	 *
	 * Type: <code>String</code><br />
	 * Recognition: <code>Startup</code><br />
	 * Range: <code>System.out, System.err, filename</code><br />
	 * Default: <code>none</code><br />
	 */
	public static final String CIMXML_TRACE_STREAM = "sblim.wbem.cimxmlTraceStream";

	/**
	 *
	 * Tells the XML builder how to sign embedded objects. This is necessary due to
	 * the non-consequent handling of embedded objects on different CIMOMs.
	 * &quot;AttribOnly&quot;       - only the EmbeddedObject=&quot;instance/object&quot; is used
	 *                      (should be good for Pegasus)
	 * &quot;EmbObjQuali&quot;      - on qualified CIM-XML elements the EmbeddedObject qualifier is used
	 *                      for embedded classes and instances
	 * &quot;EmbObjAndEmbInstQuali&quot; -
	 *                      on qualified CIM-XML elements the EmbeddedObject qualifier is used
	 *                      for embedded classes and the EmbeddedInstance=&quot;className&quot; qualifier
	 *                      is used for embedded instances
	 * Type: String
	 * Recognition: Anytime
	 * Range: AttribOnly, EmbObjQuali, EmbObjAndEmbInstQuali
	 * Default: AttribOnly
	 *
	 */
	public static final String CIMXML_EMBOBJBUILDER = "sblim.wbem.cimxmlEmbObjBuilder";

	/**
	 *
	 * If set the type of valueless EmbeddedObjects are mapped to CLASS_T. It should work well
	 * with OpenPegasus-2.7.0.
	 * If unset no type mapping is done for valuless EmbeddedObjects.
	 *
	 * Type: Boolean
	 * Default: true
	 *
	 */
	public static final String CIMXML_PARSER_STRICT_EMBOBJ_TYPES = "sblim.wbem.cimxmlParser.strictEmbObjTypes";

	/**
	 *
	 * If set to false, the embedded object entity in all requests is in mixed case
	 * (EmbeddedObject) per DSP0203.  If set to true, the embedded object entity is in
	 * upper case (EMBEDDEDOBJECT) - this works with some older CIMOMs, such as OpenPegasus
	 * 2.6.1 and 2.7.0.
	 *
	 * &lt;!ENTITY % EmbeddedObject &quot;EmbeddedObject (object|instance) #IMPLIED&quot;&gt;
	 *
	 * Type: Boolean
	 * Recognition: Startup
	 * Range: true, false
	 * Default: true
	 *
	 */
	public static final String CIMXML_BUILDER_UPPERCASE_EMBOBJ_ENTITIES =
		"sblim.wbem.cimxmlBuilder.upperCaseEmbObjEntities";

	/**
	 *
	 * If set to true, SSL handshakes are performed after an SSL socket is created by the
	 * socket factory.  If set to false, handshakes are not performed, which is useful if
	 * if the handshake has already taken place.
	 *
	 * Type: Boolean
	 * Recognition: Anytime
	 * Default: true
	 *
	 */
	public static final String PERFORM_SSL_HANDSHAKE = "sblim.wbem.performSslHandshake";

	/**
	 *
	 * If set to false, SSL handshakes are not synchronized.  If set to true, SSL handshakes
	 * are synchronized as a workaround for an IBMJSSE1 problem with thread-safe handshakes.
	 *
	 * Note: This property has no affect unless sblim.wbem.performSslHandshake is set to
	 * true.
	 *
	 * Type: Boolean
	 * Recognition: Anytime
	 * Default: false
	 *
	 */
	public static final String SYNCHRONIZED_SSL_HANDSHAKE = "sblim.wbem.synchronizedSslHandshake";

	/**
	 *
	 * If set to true, socket connections are attempted with the timeout value defined by
	 * sblim.wbem.socketConnectTimeout.  If set to false, socket connections are attempted
	 * without a timeout.  Using a timeout for socket connections is the preferred method
	 * but may introduce intermittent, significant performance impacts during the connection
	 * process in Java 5+ (see Sun bug 5092063).
	 *
	 * Type: Boolean
	 * Recognition: Anytime
	 * Default: true
	 *
	 */
	public static final String SOCKET_CONNECT_WITH_TIMEOUT = "sblim.wbem.socketConnectWithTimeout";

	/**
	 *
	 * The timeout for socket connect requests. A timeout of zero is interpreted
	 * as infinite timeout.
	 *
	 * Note: This property has no effect unless socket connection with timeout is
	 * enabled (see the sblim.wbem.socketConnectWithTimeout property).
	 *
	 * Type: Integer
	 * Unit: Milliseconds
	 * Recognition: Anytime
	 * Range: 0 .. Integer.MAX_VALUE
	 * Default: 0
	 *
	 */
	public static final String SOCKET_CONNECT_TIMEOUT = "sblim.wbem.socketConnectTimeout";

	/**
	 * The idle timeout between socket requests after which the socket is
	 * automatically reset (closed, then reopened). A timeout of zero is
	 * interpreted as infinite timeout. <br />
	 * Type: <code>Integer</code><br />
	 * Unit: <code>Milliseconds</code><br />
	 * Recognition: <code>Anytime</code><br />
	 * Range: <code>0 .. Integer.MAX_VALUE</code><br />
	 * Default: <code>0</code><br />
	 */
	public static final String SOCKET_IDLE_TIMEOUT = "sblim.wbem.socketIdleTimeout";

	/**
	 *
	 * Turn on/off usage of the default user/password, which can be used
	 * if the CIMOM requires a &quot;garbage&quot; credential.  If set to false,
	 * user-supplied credentials will be applied.  If set to true,
	 * default credentials will be applied when both the user-supplied
	 * principal and credential are null/empty.
	 *
	 * Type: Boolean
	 * Recognition: Startup
	 * Default: false
	 *
	 */
	public static final String KEY_CREDENTIALS_DEFAULT_ENABLED = "sblim.wbem.default.authorization.enabled";

	/**
	 *
	 * The name of the user for the &quot;garbage&quot; credential.
	 *
	 * Note: This property has no effect unless default authorization is
	 * enabled (see the sblim.wbem.default.authorization.enabled property)
	 * AND both the user-supplied principal and credential are null/empty.
	 *
	 * Type: String
	 * Recognition: Startup
	 * Default: &quot;default&quot;
	 *
	 */
	public static final String KEY_DEFAULT_PRINCIPAL = "sblim.wbem.default.principal";

	/**
	 *
	 * The credential of the user for the &quot;garbage&quot; credential.
	 *
	 * Note: This property has no effect unless default authorization is
	 * enabled (see the sblim.wbem.default.authorization.enabled property)
	 * AND both the user-supplied principal and credential are null/empty.
	 *
	 * Type: String
	 * Recognition: Startup
	 * Default: &quot;default&quot;
	 *
	 */
	public static final String KEY_DEFAULT_CREDENTIAL = "sblim.wbem.default.credential";

	/**
	 * The timeout for http connections of an indication listener. A timeout of
	 * zero is interpreted as infinite timeout.<br />
	 * <br />
	 * Type: <code>Integer</code><br />
	 * Unit: <code>Milliseconds</code><br />
	 * Recognition: <code>On next creation of a WBEMListener</code><br />
	 * Range: <code>0 .. Integer.MAX_VALUE</code><br />
	 * Default: <code>10000</code><br />
	 */
	public static final String LISTENER_HTTP_TIMEOUT = "sblim.wbem.listenerHttpTimeout";

	/**
	 * The header timeout for http connections of an indication listener. The
	 * header timeout is defined as the maximum amount of time allowed to read
	 * in the entire http header. A timeout of zero is interpreted as infinite
	 * timeout.<br />
	 * <br />
	 * Note: One form of DoS attack sends periodic http header lines in an
	 * attempt to keep the socket open indefinitely. This timeout can be used to
	 * thwart such an attempt.<br />
	 * <br />
	 * Type: <code>Integer</code><br/>
	 * Unit: <code>Milliseconds</code><br />
	 * Recognition: <code>On next creation of a WBEMListener<code><br/>
	 * Range: <code>0 .. Integer.MAX_VALUE</code><br />
	 * Default: <code>30000</code><br/>
	 */
	public static final String LISTENER_HTTP_HEADER_TIMEOUT = "sblim.wbem.listenerHttpHeaderTimeout";

	/**
	 * The maximum allowable timeouts an http connection of an indication
	 * listener can have before the client ignores it. In other words, the
	 * number of times an IP exceeds sblim.wbem.listenerHttpTimeout and
	 * sblim.wbem.listenerHttpHeaderTimeout before it is blocked. A value of
	 * zero is interpreted as unlimited timeouts.<br />
	 * <br />
	 * Type: <code>Integer</code><br />
	 * Recognition: <code>On next creation of a WBEMListener</code><br />
	 * Range: <code>0 .. Integer.MAX_VALUE</code><br />
	 * Default: <code>0</code><br />
	 */
	public static final String LISTENER_HTTP_MAX_ALLOWED_TIMEOUTS = "sblim.wbem.listenerHttpMaxAllowedTimeouts";

	/**
	 * The size of the thread pool for the connection handlers of the indication
	 * for http connections of an indication listener. This is the maximum
	 * number of handler threads the pool might create on heavy load.<br />
	 * A value of -1 is interpreted as infinity. <br />
	 * Type: <code>Integer</code><br />
	 * Unit: <code>Count</code><br />
	 * Recognition: <code>On next creation of a WBEMListener</code><br />
	 * Range: <code>-1 .. Integer.MAX_VALUE</code><br />
	 * Default: <code>8</code><br />
	 */
	public static final String LISTENER_MAX_POOL_SIZE = "sblim.wbem.listenerPoolMaxSize";

	/**
	 * The minimal number of connection handlers of the indication listener that
	 * will be kept open by the thread pool regardless of the current load. <br />
	 * Type: <code>Integer</code><br />
	 * Unit: <code>Count</code><br />
	 * Recognition: <code>On next creation of a WBEMListener</code><br />
	 * Range: <code>0 .. Integer.MAX_VALUE</code><br />
	 * Default: <code>2</code><br />
	 */
	public static final String LISTENER_MIN_POOL_SIZE = "sblim.wbem.listenerPoolMinSize";

	/**
	 * The maximum number of queued connections (the fixed capacity of the
	 * ArrayBlockingQueue of pending connections incoming to the listener).
	 * Whereas increasing this number will result in a correspondingly greater
	 * memory usage, making the number too small can result in HTTP 503
	 * "Service temporarily overloaded" returned to server if there is no room
	 * in queue for an incoming connection. <br />
	 * Type: <code>Integer</code><br />
	 * Unit: <code>Count</code><br />
	 * Recognition: <code>On next creation of a WBEMListener</code><br />
	 * Range: <code>1 .. Integer.MAX_VALUE</code><br />
	 * Default: <code>32</code><br />
	 */
	public static final String LISTENER_MAX_QUEUE_SIZE = "sblim.wbem.listenerQueueMaxSize";

	/**
	 * The number of queued connections that is tolerated before the thread pool
	 * creates an additional handler thread. Increasing this value leads to a
	 * less &quot;nervous&quot; creation/destruction of handlers. However it
	 * makes the listener more vulnerable to frozen connections.<br />
	 * <br />
	 * Type: <code>Integer</code><br />
	 * Unit: <code>Count</code><br />
	 * Recognition: <code>On next creation of a WBEMListener</code><br />
	 * Range: <code>0 .. Integer.MAX_VALUE</code><br />
	 * Default: <code>2</code><br />
	 */
	public static final String LISTENER_BACKLOG = "sblim.wbem.listenerBacklog";

	/**
	 * The idle time of a worker that is tolerated before the worker is
	 * destroyed by the thread pool. By setting the minimal pool size >0 you can
	 * protect a given number of worker from destruction.<br />
	 * <br />
	 * Type: <code>Long</code><br />
	 * Unit: <code>Milliseconds</code><br />
	 * Recognition: <code>On next creation of a WBEMListener</code><br />
	 * Range: <code>0 .. Long.MAX_VALUE</code><br />
	 * Default: <code>30000</code><br />
	 */
	public static final String LISTENER_HANDLER_MAX_IDLE = "sblim.wbem.listenerHandlerMaxIdle";

	/**
	 * The maximum number of queued events (the fixed capacity of the LinkedList
	 * of indications awaiting delivery to the listener). When the maximum is
	 * reached, the oldest indications are discarded to make room for the newest
	 * ones. A value of 0 is interpreted as infinity. <br />
	 * Type: <code>Integer</code><br />
	 * Unit: <code>Count</code><br />
	 * Recognition: <code>On next creation of a WBEMListener</code><br />
	 * Range: <code>0 .. Integer.MAX_VALUE</code><br />
	 * Default: <code>0</code><br />
	 */
	public static final String LISTENER_MAX_QUEUED_EVENTS = "sblim.wbem.listenerMaxQueuedEvents";

	/**
	 *
	 * If set to true, reliable indication support is enabled and indications
	 * are processed accordingly.  If set to false, reliable indication
	 * support is disabled and indications are passed directly to listener.
	 *
	 * If reliable indication support is enabled, incoming indications are
	 * handled as documented in DSP1054 which includes queuing unexpected
	 * indications, caching all indications for the duration of their sequence
	 * identifier lifetime, and logging missing, duplicate and out-of-order
	 * indications.
	 *
	 * The sequence identifier lifetime is defined as:
	 *   DeliveryRetryAttempts * DeliveryRetryInterval * 10
	 * These values can be set by the sblim.wbem.listenerDeliveryRetryAttempts
	 * and sblim.wbem.listenerDeliveryRetryInterval properties below.
	 *
	 * Unexpected indications are queued in either a linked list or a hash
	 * table based on the sblim.wbem.listenerReliableIndicationHashtableCapacity
	 * property below.  The linked list is better suited for a small number of
	 * listener destinations per WBEMListener while the hash table is better
	 * suited for a large number.
	 *
	 * Type: Boolean
	 * Recognition: On next creation of a WBEMListener
	 * Default: false
	 *
	 */
	public static final String LISTENER_ENABLE_RELIABLE_INDICATIONS = "sblim.wbem.listenerEnableReliableIndications";

	/**
	 *
	 * The default value to use for the CIM_IndicationService DeliveryRetryAttempts
	 * property.  See DSP1054 for details on reliable indications.
	 *
	 * Note: This property has no effect unless reliable indication support is
	 * enabled.  See the sblim.wbem.listenerEnableReliableIndications property.
	 *
	 * Type: Long
	 * Unit: Count
	 * Recognition: On next creation of a WBEMListener
	 * Range: 1 .. 1000
	 * Default: 3
	 *
	 */
	public static final String LISTENER_DELIVERY_RETRY_ATTEMPTS = "sblim.wbem.listenerDeliveryRetryAttempts";

	/**
	 *
	 * The default value to use for the CIM_IndicationService DeliveryRetryInterval
	 * property.  See DSP1054 for details on reliable indications.
	 *
	 * Note: This property has no effect unless reliable indication support is
	 * enabled.  See the sblim.wbem.listenerEnableReliableIndications property.
	 *
	 * Type: Long
	 * Unit: Seconds
	 * Recognition: On next creation of a WBEMListener
	 * Range: 1 .. 86400
	 * Default: 20
	 *
	 */
	public static final String LISTENER_DELIVERY_RETRY_INTERVAL = "sblim.wbem.listenerDeliveryRetryInterval";

	/**
	 *
	 * The default value to use for the reliable indication handler's initial
	 * hash table capacity.  A value of 0 indicates use a linked list instead.
	 * Linked lists are better suited for a small number of listener destinations
	 * per WBEMListener while hash tables are better suited for a large number.
	 *
	 * Note: This property has no effect unless reliable indication support is
	 * enabled.  See the sblim.wbem.listenerEnableReliableIndications property.
	 *
	 * Type: Integer
	 * Unit: Count
	 * Recognition: On next creation of a WBEMListener
	 * Range: 0 .. 25000
	 * Default: 0
	 *
	 */
	public static final String LISTENER_RELIABLE_INDICATION_HASHTABLE_CAPACITY =
		"sblim.wbem.listenerReliableIndicationHashtableCapacity";

	/**
	 *
	 * The filter to use for tracing of incoming indications at the FINE level.
	 *
	 * If string is empty, no tracing of incoming indications will occur.  If
	 * string is not empty, it identifies one or more properties to be included
	 * in the trace of all incoming indications.  An optional class can be used
	 * to filter the output to include only those indications that contain the
	 * substring.  For example, to trace the SequenceContext and SequenceNumber
	 * properties of all alerts, use the following:
	 *
	 *    alert:sequencecontext,sequencenumber
	 *
	 * To trace the IndicationTime of all indications, use the following:
	 *
	 *    indicationtime
	 *
	 * Note: This property has no effect unless tracing is enabled.  See the
	 * sblim.wbem.traceFileLevel property.
	 *
	 * Type: String
	 * Recognition: On next creation of WBEMListener
	 * Format: [class:]property[,property]*
	 *
	 */
	public static final String LISTENER_INDICATION_TRACE_FILTER = "sblim.wbem.listenerIndicationTraceFilter";

	/**
	 *
	 * If set to true, a property will be added to all indications that identifies
	 * the sender's IP address.  If set to false, the property will not be added.
	 *
	 * The property is a CIMProperty with:
	 *   name = &quot;SBLIMJCC_SenderIPAddress&quot;
	 *   data type = CIMDataType.STRING_T
	 *   value = String returned by InetAddress.getHostAddress() (i.e. 1.2.3.4)
	 *
	 * Type: Boolean
	 * Recognition: On next creation of a WBEMListener
	 * Default: false
	 *
	 */
	public static final String LISTENER_ADD_SENDER_IP_ADDRESS = "sblim.wbem.listenerAddSenderIPAddress";

	/**
	 *
	 * If set to true, numeric string values passed to the java.lang.Double
	 * constructor or its parseDouble method will be checked to make sure they
	 * are not in the range that hangs Java 6- (see Sun bug 4421494).  If
	 * set to false, the string values will not be checked.
	 *
	 * Note: This property should only be set to true if running on Java 5 or
	 * Java 6 prior to update 24.
	 *
	 * Type: Boolean
	 * Recognition: Startup
	 * Default: true
	 *
	 */
	public static final String VERIFY_JAVA_LANG_DOUBLE_STRINGS = "sblim.wbem.verifyJavaLangDoubleStrings";

	/**
	 *
	 * If set to true, numeric key data types in a CIMInstance's CIMObjectPath
	 * will be synchronized to match those of the corresponding keys within
	 * the CIMInstance's CIMProperty[].  If set to false, the numeric key data
	 * types will not be synchronized.
	 *
	 * Note: Only numeric key data types in CIMInstances from CIMOM responses
	 * are synchronized, application calls to the CIMInstance constructor are
	 * not affected.
	 *
	 * Type: Boolean
	 * Recognition: Startup
	 * Default: false
	 *
	 */
	public static final String SYNCHRONIZE_NUMERIC_KEY_DATA_TYPES = "sblim.wbem.synchronizeNumericKeyDataTypes";

	/**
	 *
	 * If set to true, gzip encoding is enabled.  If set to false, gzip encoding
	 * is not enabled.  When enabled, outgoing requests include the HTTP header
	 * &quot;Accept-Encoding: gzip&quot; to indicate to the CIMOM that the client handles
	 * message bodies compressed with gzip.  If the incoming response includes
	 * &quot;Content-Encoding: gzip&quot; the message body will be decompressed with gzip
	 * before being processed.
	 *
	 * Note: This property does not affect indications or outgoing requests.
	 *
	 * Type: Boolean
	 * Recognition: Anytime
	 * Default: false
	 *
	 */
	public static final String ENABLE_GZIP_ENCODING = "sblim.wbem.enableGzipEncoding";

	/**
	 *
	 * If set to true, the CIM-XML parser will allow empty LOCALNAMESPACEPATH
	 * elements in incoming responses.  If set to false, the parser will not
	 * allow empty LOCALNAMESPACEPATH elements.
	 *
	 * Note: Some older CIMOMs sent empty LOCALNAMESPACEPATHs, relying on the
	 * client to provide the local namespace path.  This is a violation of
	 * DSP0201, which dictates that LOCALNAMESPACEPATH must contain at least
	 * one NAMESPACE child.  By default, this property is set to false so the
	 * client can adhere to the CIM-XML specifications.  Set this property to
	 * true if &quot;LOCALNAMESPACEPATH requires NAMESPACE&quot; exceptions occur while
	 * interacting with one of these older CIMOMs.
	 *
	 * Type: Boolean
	 * Recognition: Anytime
	 * Default: false
	 *
	 */
	public static final String CIMXML_PARSER_ALLOW_EMPTY_LOCALNAMESPACEPATH =
		"sblim.wbem.cimxmlParser.allowEmptyLocalNameSpacePath";

	/**
	 * AMMO-863
	 * <p>
	 * New configuration property to force the use of a strict mode for the
	 * HTTP header Connection=Keep-alive (that may not be used for HTTP/1.1).
	 */
	public static final String HTTP_KEEP_ALIVE_STRICT_MODE = "sblim.wbem.httpKeepAliveStrictMode";
}
