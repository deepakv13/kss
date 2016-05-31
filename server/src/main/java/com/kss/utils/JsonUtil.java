package com.kss.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.TypeReference;

public class JsonUtil {

	private static ObjectMapper jsonMapper = new ObjectMapper();

	static {
		jsonMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		jsonMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
	}

	// utility method should have private constructor
	private JsonUtil() {

	}

	public static String buildJsonFromObject(Object ob) throws JsonGenerationException, JsonMappingException, IOException {
		String json;
		json = jsonMapper.writeValueAsString(ob);
		return json;
	}

	public static Object buildObjectFromJson(String json, Class clazz) throws IOException {
		Object ob = jsonMapper.readValue(json, clazz);
		return ob;
	}

	public static <T> List<T> buildObjectListFromJson(String json, Class clazz) throws IOException {

		List<T> ls = jsonMapper.readValue(json, TypeFactory.collectionType(List.class, clazz));
		return ls;

	}

	public static Map<String, Object> buildmapFromJson(String json) throws IOException {
		Map<String, Object> map = jsonMapper.readValue(json, new TypeReference<HashMap<String, Object>>() {
		});

		return map;
	}

}
