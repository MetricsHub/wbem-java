/*
  (C) Copyright IBM Corp. 2009

  THIS FILE IS PROVIDED UNDER THE TERMS OF THE ECLIPSE PUBLIC LICENSE
  ("AGREEMENT"). ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS FILE
  CONSTITUTES RECIPIENTS ACCEPTANCE OF THE AGREEMENT.

  You can obtain a current copy of the Eclipse Public License from
  http://www.opensource.org/licenses/eclipse-1.0.php

  @author : Ramandeep Arora, arorar@us.ibm.com
 * 
 * Flag       Date        Prog         Description
 * -------------------------------------------------------------------------------
 * 2531371    2009-02-10  raman_arora  Upgrade client to JDK 1.5 (Phase 2) 
 * 2797696    2009-05-27  raman_arora  Input files use unsafe operations
 * 2797550    2009-06-01  raman_arora  JSR48 compliance - add Java Generics
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

import java.util.ArrayList;
import java.util.Vector;

/**
 * Class GenericExts is responsible for generic initialization
 */
public class GenericExts {

	/**
	 * initArrayList : If arrayList is null then it will return the new
	 * arrayList of same type if it is not null then it will clear the arrayList
	 * 
	 * @param <T>
	 *            : Type Parameter
	 * 
	 * @param pAL
	 *            : ArrayList to be initialized
	 * @return ArrayList : initialized ArrayList
	 */
	public static <T> ArrayList<T> initClearArrayList(ArrayList<T> pAL) {
		if (pAL == null) return new ArrayList<T>();
		pAL.clear();
		return pAL;
	}

	/**
	 * initArrayList : If arrayList is null then it will return the new
	 * arrayList of same type if it is not null then it will return the same
	 * arrayList
	 * 
	 * @param <T>
	 *            : Type Parameter
	 * 
	 * @param pAL
	 *            : ArrayList to be initialized
	 * @return ArrayList : initialized ArrayList
	 */
	public static <T> ArrayList<T> initArrayList(ArrayList<T> pAL) {
		if (pAL == null) return new ArrayList<T>();
		return pAL;
	}

	/**
	 * cloneVector : Generic deep copy of the vector. If original vector is null
	 * then return value will also be null.
	 * 
	 * @param <T>
	 *            : Type of vector
	 * 
	 * @param oldVec
	 *            : The original vector.
	 * 
	 * @return Vector&lt;T&gt; : Deep copy of original vector.
	 */
	public static synchronized <T> Vector<T> cloneVector(Vector<T> oldVec) {
		if (oldVec == null) return null;
		Vector<T> newVec = new Vector<T>(oldVec.size());
		for (T obj : oldVec)
			newVec.add(obj);
		return newVec;
	}
}
