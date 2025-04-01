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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.metricshub.wbem.client.exceptions.WqlQuerySyntaxException;
import org.metricshub.wbem.javax.wbem.WBEMException;

/**
 * Functions to execute WBEM query.
 *
 */
public class WbemExecutor {

	private WbemExecutor() {}

	/**
	 * Execute a WBEM query on remote.
	 *
	 * @param url The remote URL.
	 * @param namespace The WBEM namespace.
	 * @param username The user name.
	 * @param password The Password.
	 * @param query The WQL Query to execute.
	 * @param timeout Timeout in milliseconds.
	 * @param arraySeparator The array separator value. default value '|'
	 * @return
	 * @throws WqlQuerySyntaxException On WQL syntax errors.
	 * @throws WBEMException On WBEM errors.
	 * @throws TimeoutException To notify userName of timeout.
	 * @throws InterruptedException
	 */
	public static WbemQueryResult executeWql(
		final URL url,
		final String namespace,
		final String username,
		final char[] password,
		final String query,
		int timeout,
		final String arraySeparator
	)
		throws WqlQuerySyntaxException, WBEMException, TimeoutException, InterruptedException {
		return executeMethod(url, namespace, username, password, query, null, timeout, arraySeparator);
	}

	/**
	 * Execute WBEM get associators on remote.
	 *
	 * @param url The remote URL.
	 * @param username The user name.
	 * @param password The Password.
	 * @param query The WQL Query to execute.
	 * @param objectPathAssociators The object path for ASSOCIATORS.
	 * @param timeout Timeout in milliseconds.
	 * @param arraySeparator The array separator value. default value '|'
	 * @return
	 * @throws WqlQuerySyntaxException On WQL syntax errors.
	 * @throws WBEMException On WBEM errors.
	 * @throws TimeoutException To notify userName of timeout.
	 * @throws InterruptedException
	 */
	public static WbemQueryResult getAssociators(
		final URL url,
		final String username,
		final char[] password,
		final String query,
		final String objectPathAssociators,
		int timeout,
		final String arraySeparator
	)
		throws WqlQuerySyntaxException, WBEMException, TimeoutException, InterruptedException {
		return executeMethod(url, null, username, password, query, objectPathAssociators, timeout, arraySeparator);
	}

	/**
	 * Execute the WBEM method with timeout.
	 *
	 * @param url
	 * @param namespace
	 * @param username
	 * @param password
	 * @param query
	 * @param objectPathAssociators
	 * @param timeout
	 * @param arraySeparator
	 * @return
	 * @throws InterruptedException
	 * @throws TimeoutException
	 * @throws WBEMException
	 * @throws WqlQuerySyntaxException
	 */
	private static WbemQueryResult executeMethod(
		final URL url,
		final String namespace,
		final String username,
		final char[] password,
		final String query,
		final String objectPathAssociators,
		int timeout,
		final String arraySeparator
	)
		throws InterruptedException, TimeoutException, WBEMException, WqlQuerySyntaxException {
		Utils.checkNonNull(url, "url");
		Utils.checkNonNull(username, "username");
		Utils.checkNonNull(password, "password");

		final WqlQuery wqlQuery = WqlQuery.parseQuery(query);

		final ExecutorService executor = Executors.newSingleThreadExecutor();

		final Future<WbemQueryResult> future = executor.submit(
			() -> {
				try (final WbemClient matsyaWbemClient = new WbemClient()) {
					matsyaWbemClient.connect(url, username, password, timeout);

					return objectPathAssociators == null
						? matsyaWbemClient.executeWql(wqlQuery, namespace, arraySeparator)
						: matsyaWbemClient.getAssociators(wqlQuery, objectPathAssociators, arraySeparator);
				}
			}
		);

		try {
			return future.get(timeout, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw (InterruptedException) e;
		} catch (TimeoutException e) {
			future.cancel(true);
			throw e;
		} catch (ExecutionException e) {
			if (e.getCause() instanceof WBEMException) {
				throw (WBEMException) e.getCause();
			}
			// else should be RunTimeException as matsyaWbemClient only thrown
			// WBEMException as checked exceptions.
			throw (RuntimeException) e.getCause();
		} finally {
			executor.shutdownNow();
		}
	}
}
