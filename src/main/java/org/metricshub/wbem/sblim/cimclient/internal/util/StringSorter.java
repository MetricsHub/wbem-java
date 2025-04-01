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
 * 1565892    2006-10-17  ebak         Make SBLIM client JSR48 compliant
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 * 2531371    2009-02-10  raman_arora  Upgrade client to JDK 1.5 (Phase 2) 
 */

package org.metricshub.wbem.sblim.cimclient.internal.util;

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

import java.util.Arrays;
import java.util.Comparator;

/**
 * Class StringSorter is responsible for non case sensitive sorting and binary
 * searching of String arrays.
 */
public class StringSorter implements Comparator<Object> {
	private static final Comparator<Object> COMPARATOR = new StringSorter();

	/**
	 * Sorts non case sensitively the passed String array, the passed array is
	 * not copied.
	 *
	 * @param pArray
	 *            the array which will be sorted if it's not null
	 * @return pArray
	 */
	public static String[] sort(String[] pArray) {
		if (pArray == null) return null;
		synchronized (pArray) {
			Arrays.sort(pArray, COMPARATOR);
		}
		return pArray;
	}

	/**
	 * Finds pName, in pArray which must be a non case sensitive sorted array of
	 * Strings.
	 *
	 * @param pArray
	 * @param pName
	 * @return <code>true</code> if found, otherwise <code>false</code>
	 */
	public static boolean find(String[] pArray, String pName) {
		if (pArray == null) return false;
		synchronized (pArray) {
			int idx = Arrays.binarySearch(pArray, pName, COMPARATOR);
			return idx >= 0;
		}
	}

	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object pO1, Object pO2) {
		String str1 = (String) pO1;
		String str2 = (String) pO2;
		return str1.compareToIgnoreCase(str2);
	}
}
