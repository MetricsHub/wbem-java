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

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.metricshub.wbem.client.exceptions.WqlQuerySyntaxException;

/**
 * Handler for WQL query.
 *
 */
public class WqlQuery {

	private WqlQuery() {}

	private static final String ID = "[^\\s]+";
	private static final String LIST_SEPARATOR = "\\s*,\\s*";

	private static final Pattern CHECK_SELECT_PATTERN = Pattern.compile(
		"^\\s*SELECT\\s+(\\*|(?!SELECT|FROM|WHERE)\\w+|((?!SELECT|FROM|WHERE)\\w+\\s*,\\s*)+((?!SELECT|FROM|WHERE)\\w+))\\s+FROM\\s+((?!SELECT|WHERE|FROM)\\w+)\\s*?$",
		Pattern.CASE_INSENSITIVE
	);

	private static final Pattern EXTRACT_SELECT_PATTERN = Pattern.compile(
		"^\\s*SELECT\\s+(.+)\\s+FROM\\s+(" + ID + ")\\s*$",
		Pattern.CASE_INSENSITIVE
	);
	private static final int SELECT_GROUP_CLASSNAME = 2;
	private static final int SELECT_GROUP_FIELDS = 1;
	private static final int SELECT_GROUP_COUNT = 2;

	private String className;
	private Set<String> properties;
	private List<String> originalProperties;

	/**
	 * Constructor for WQL query.
	 *
	 * @param query the WQL query.
	 * @throws WqlQuerySyntaxException On WQL Syntax exception.
	 */
	public static WqlQuery parseQuery(final String query) throws WqlQuerySyntaxException {
		Utils.checkNonNull(query, "query");

		if (CHECK_SELECT_PATTERN.matcher(query).find()) {
			final WqlQuery wqlQuery = new WqlQuery();
			wqlQuery.parseSelect(query);
			return wqlQuery;
		}
		throw new WqlQuerySyntaxException(query);
	}

	public String getClassName() {
		return className;
	}

	public String[] getPropertiesArray() {
		return properties.isEmpty()
			? null
			: properties
				.stream()
				.filter(property -> !WbemCimDataHandler.PATH_PROPERTY.equalsIgnoreCase(property))
				.toArray(String[]::new);
	}

	public Set<String> getProperties() {
		return properties;
	}

	public List<String> getOriginalProperties() {
		return originalProperties;
	}

	public boolean hasDuplicateProperties() {
		return properties != null && originalProperties != null && properties.size() != originalProperties.size();
	}

	private void parseSelect(final String query) throws WqlQuerySyntaxException {
		final Matcher matcher = EXTRACT_SELECT_PATTERN.matcher(query);
		if (!matcher.find()) {
			throw new WqlQuerySyntaxException(query);
		}
		if (matcher.groupCount() < SELECT_GROUP_COUNT) {
			throw new WqlQuerySyntaxException(query);
		}

		className = matcher.group(SELECT_GROUP_CLASSNAME);

		final String fieldsList = matcher.group(SELECT_GROUP_FIELDS);
		final String[] fieldArray = Optional
			.ofNullable(fieldsList)
			.filter(s -> !"*".equals(s))
			.map(s -> s.split(LIST_SEPARATOR))
			.orElse(new String[0]);

		originalProperties = Stream.of(fieldArray).map(String::trim).collect(Collectors.toList());

		// using a map, so the properties will keep the case and the order of the query.
		final Map<String, String> originalMap = new HashMap<>();
		originalProperties
			.stream()
			.forEach(property -> originalMap.computeIfAbsent(property.toLowerCase(), prop -> property));

		properties =
			originalProperties
				.stream()
				.map(property -> originalMap.get(property.toLowerCase()))
				.collect(Collectors.toCollection(LinkedHashSet::new));
	}
}
