package org.metricshub.wbem.client;

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

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.security.auth.Subject;

import org.metricshub.wbem.javax.cim.CIMInstance;
import org.metricshub.wbem.javax.cim.CIMObjectPath;
import org.metricshub.wbem.javax.wbem.CloseableIterator;
import org.metricshub.wbem.javax.wbem.WBEMException;
import org.metricshub.wbem.javax.wbem.client.EnumerateResponse;
import org.metricshub.wbem.javax.wbem.client.PasswordCredential;
import org.metricshub.wbem.javax.wbem.client.UserPrincipal;
import org.metricshub.wbem.javax.wbem.client.WBEMClient;
import org.metricshub.wbem.javax.wbem.client.WBEMClientConstants;
import org.metricshub.wbem.javax.wbem.client.WBEMClientFactory;
import org.metricshub.wbem.javax.cim.CIMProperty;

/**
 * Matsya WBEM client for query execution.
 *
 */
public class WbemClient implements AutoCloseable {

	private static final Locale[] FIXED_AVAILABLE_LOCALES_ARRAY = {Locale.ENGLISH};

	private WBEMClient client;

	private CloseableIterator<CIMInstance> iterator;

	/**
	 * Connect to WBEM client.
	 *
	 * @param url
	 * @param username
	 * @param password
	 * @param timeout
	 * @throws WBEMException
	 */
	public void connect(
			final URL url,
			final String username,
			final char[] password,
			int timeout) throws WBEMException {

		Utils.checkNonNull(url, "url");
		Utils.checkNonNull(username, "username");
		Utils.checkNonNull(password, "password");

		final CIMObjectPath cimObjectPath = new CIMObjectPath(
				url.getProtocol(),
				url.getHost(),
				String.valueOf(url.getPort()),
				null,
				null,
				null);

		final Subject subject = new Subject();
		subject.getPrincipals().add(new UserPrincipal(username));
		subject.getPrivateCredentials().add(new PasswordCredential(password));

		// Create and initialize a WBEM client.
		client = WBEMClientFactory.getClient("CIM-XML");
		client.setProperty(WBEMClientConstants.PROP_TIMEOUT, String.valueOf(timeout));
		client.initialize(cimObjectPath, subject, FIXED_AVAILABLE_LOCALES_ARRAY);
	}

	@Override
	public void close() {
		if (iterator != null) {
			iterator.close();
		}

		if (client != null) {
			client.close();
		}
	}

	/**
	 * Execute a WQL query on remote.
	 * @param wqlQuery The query handler.
	 * @param namespace The WBEM namespace.
	 * @param arraySeparator The array separator value. default value '|'
	 * @return
	 * @throws WBEMException
	 */
	public WbemQueryResult executeWql(
			final WqlQuery wqlQuery,
			final String namespace,
			final String arraySeparator) throws WBEMException {

		Utils.checkNonNull(wqlQuery, "wqlQuery");
		Utils.checkNonNull(namespace, "namespace");

		if (client == null) {
			throw new IllegalStateException("client must be connected first.");
		}

		iterator = client.enumerateInstances(
				new CIMObjectPath(null, null, null, namespace, wqlQuery.getClassName(), null),
				true,
				false,
				true,
				wqlQuery.getPropertiesArray());

		return enumerateInstances(wqlQuery, iterator, arraySeparator);
	}

	/**
	 * Get associators.
	 * @param wqlQuery The query handler.
	 * @param objectPathAssociators The object path for ASSOCIATORS.
	 * @param arraySeparator The array separator value. default value '|'
	 * @return
	 * @throws WBEMException
	 */
	public WbemQueryResult getAssociators(
			final WqlQuery wqlQuery,
			final String objectPathAssociators,
			final String arraySeparator) throws WBEMException {

		Utils.checkNonNull(wqlQuery, "wqlQuery");
		Utils.checkNonNull(objectPathAssociators, "objectPathAssociators");

		if (client == null) {
			throw new IllegalStateException("client must be connected first.");
		}

		final EnumerateResponse<CIMInstance> response = client.associators(
				new CIMObjectPath(objectPathAssociators),
				wqlQuery.getClassName(),
				null,
				null,
				null,
				false,
				wqlQuery.getPropertiesArray(),
				null,
				null,
				null,
				false,
				null);
		iterator = response.getResponses();

		return enumerateInstances(wqlQuery, iterator, arraySeparator);
	}

	public static WbemQueryResult enumerateInstances(
			final WqlQuery wqlQuery,
			final CloseableIterator<CIMInstance> iterator,
			final String arraySeparator) {
		if (iterator == null)  {
			return new WbemQueryResult(new ArrayList<>(), new ArrayList<>());
		}

		Set<String> properties = null;
		List<String> originalProperties = null;
		final List<List<String>> values = new ArrayList<>();

		while (iterator.hasNext()) {
			final CIMInstance cimInstance = iterator.next();

			if (properties == null) {
				properties =
						wqlQuery.getProperties().isEmpty() ?
						Stream.of(cimInstance.getProperties())
								.map(CIMProperty::getName)
								.collect(Collectors.toCollection(LinkedHashSet::new)) :
						wqlQuery.getProperties();
			}

			if (originalProperties == null) {
				originalProperties = wqlQuery.hasDuplicateProperties() ?
						wqlQuery.getOriginalProperties() :
						properties.stream().collect(Collectors.toList());
			}

			final List<String> row;
			if (wqlQuery.hasDuplicateProperties()) {

				final Map<String, String> cimProperties = properties.stream().collect(Collectors.toMap(
						String::toLowerCase,
						property -> WbemCimDataHandler.getCimPropertyAsString(property, cimInstance, arraySeparator)));

				row = originalProperties.stream()
						.map(property -> cimProperties.get(property.toLowerCase()))
						.collect(Collectors.toList());

			} else {
				row = properties.stream()
						.map(property -> WbemCimDataHandler.getCimPropertyAsString(property, cimInstance, arraySeparator))
						.collect(Collectors.toList());
			}

			values.add(row);
		}

		return new WbemQueryResult(originalProperties, values);
	}
}
