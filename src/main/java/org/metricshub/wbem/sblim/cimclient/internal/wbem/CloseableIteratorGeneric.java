/*
  (C) Copyright IBM Corp. 2009

  THIS FILE IS PROVIDED UNDER THE TERMS OF THE ECLIPSE PUBLIC LICENSE
  ("AGREEMENT"). ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS FILE
  CONSTITUTES RECIPIENTS ACCEPTANCE OF THE AGREEMENT.

  You can obtain a current copy of the Eclipse Public License from
  http://www.opensource.org/licenses/eclipse-1.0.php

  @author : Ramandeep S Arora, IBM, arorar@us.ibm.com
 * 
 * Flag       Date        Prog         Description
 * --------------------------------------------------------------------------
 * 2878054    2009-10-25  raman_arora  Pull Enumeration Feature (PULL Parser)
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

import java.util.Iterator;

import org.metricshub.wbem.javax.wbem.CloseableIterator;
import org.metricshub.wbem.javax.wbem.WBEMException;

/**
 * Class CloseableIteratorGeneric creates new CloseableIterator from an Iterator
 * and WBEMException.
 * 
 * @param <E>
 *            : Type
 */
public class CloseableIteratorGeneric<E> implements CloseableIterator<Object> {

	private Iterator<E> iterator;

	private WBEMException iWBEMException;

	/**
	 * Ctor. : creates new CloseableIterator from an Iterator and WBEMException.
	 * 
	 * @param pIterator
	 *            : Iterator to be used in closeableIterator
	 * @param pException
	 *            : WBEMException thrown by parser (this can be null)
	 */
	public CloseableIteratorGeneric(Iterator<E> pIterator, WBEMException pException) {
		this.iterator = pIterator;
		this.iWBEMException = pException;
	}

	/**
	 * Ctor. : creates new CloseableIterator from an Iterator.
	 * 
	 * @param pIterator
	 *            : Iterator to be used in closeableIterator
	 */
	public CloseableIteratorGeneric(Iterator<E> pIterator) {
		this(pIterator, null);
	}

	public void close() {
		this.iterator = null;
		this.iWBEMException = null;
	}

	/**
	 * Returns WBEMException
	 * 
	 * @return WBEMException : This can be null
	 * 
	 */
	public WBEMException getWBEMException() {
		return this.iWBEMException;
	}

	public boolean hasNext() {
		return this.iterator.hasNext();
	}

	public Object next() {
		return this.iterator.next();
	}

	/**
	 * iterator.remove() is not supported
	 */
	public void remove() {
		throw new UnsupportedOperationException("Cannot remove elements from iterator");
	}
}
