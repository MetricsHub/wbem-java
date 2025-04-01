/*
  SLPLimits.java

  (C) Copyright IBM Corp. 2006, 2009

  THIS FILE IS PROVIDED UNDER THE TERMS OF THE ECLIPSE PUBLIC LICENSE
  ("AGREEMENT"). ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS FILE
  CONSTITUTES RECIPIENTS ACCEPTANCE OF THE AGREEMENT.

  You can obtain a current copy of the Eclipse Public License from
  http://www.opensource.org/licenses/eclipse-1.0.php

  @author : Alexander Wolf-Reber, IBM a.wolf-reber@de.ibm.com
 * 
 * Change History
 * Flag       Date        Prog         Description
 *------------------------------------------------------------------------------- 
 * 1535793    2006-09-14  lupusalex    Fix&Integrate CIM&SLP configuration classes
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 * 2907527    2009-12-02  blaschke-oss Fix SLP properties issues
 */
package org.metricshub.wbem.sblim.slp.internal;

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
 * SLPLimits
 *
 */
public interface SLPLimits {
	/**
	 * MAX_DISCOVERY_GRANULARITY
	 */
	public static final int MAX_DISCOVERY_GRANULARITY = 10800;

	/**
	 * MAX_DISCOVERY_INTERVAL
	 */
	public static final int MAX_DISCOVERY_INTERVAL = 3 * 60 * 60; // 3h

	/**
	 * MAX_MTU
	 */
	public static final int MAX_MTU = 8192;

	/**
	 * MAX_MULTICAST_MAXIMUM_WAIT
	 */
	public static final int MAX_MULTICAST_MAXIMUM_WAIT = 60000;

	/**
	 * MAX_MULTICAST_RADIUS
	 */
	public static final int MAX_MULTICAST_RADIUS = 255;

	/**
	 * MAX_SERVER_SOCKET_QUEUE_LENGTH
	 */
	public static final int MAX_SERVER_SOCKET_QUEUE_LENGTH = 0x7fffffff;

	/**
	 * MAX_TCP_TIMEOUT
	 */
	public static final int MAX_TCP_TIMEOUT = 360000;

	/**
	 * MIN_DISCOVERY_GRANULARITY
	 */
	public static final int MIN_DISCOVERY_GRANULARITY = 0;

	/**
	 * MIN_DISCOVERY_INTERVAL
	 */
	public static final int MIN_DISCOVERY_INTERVAL = 0;

	/**
	 * MIN_MTU
	 */
	public static final int MIN_MTU = 128;

	/**
	 * MIN_MULTICAST_MAXIMUM_WAIT
	 */
	public static final int MIN_MULTICAST_MAXIMUM_WAIT = 1000;

	/**
	 * MIN_MULTICAST_RADIUS
	 */
	public static final int MIN_MULTICAST_RADIUS = 1;

	/**
	 * MIN_SERVER_SOCKET_QUEUE_LENGTH
	 */
	public static final int MIN_SERVER_SOCKET_QUEUE_LENGTH = 0;

	/**
	 * MIN_TCP_TIMEOUT
	 */
	public static final int MIN_TCP_TIMEOUT = 100;
}
