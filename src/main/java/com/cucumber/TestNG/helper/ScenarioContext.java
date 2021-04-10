package com.cucumber.TestNG.helper;

import java.util.HashMap;
import java.util.Map;
import static java.lang.ThreadLocal.withInitial;

public class ScenarioContext {
	private ScenarioContext() {
		throw new IllegalStateException("Utility Class");
	}

	private final static ThreadLocal<Map<String, Object>> testContexts = withInitial(HashMap::new);

	public static Object getContext(Context key) {
		return testContexts.get().get(key.toString());
	}

	public static void setContext(Context key, Object object) {
		testContexts.get().put(key.toString(), object);
	}
}
