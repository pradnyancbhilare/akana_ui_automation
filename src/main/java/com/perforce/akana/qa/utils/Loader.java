package com.perforce.akana.qa.utils;

import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.perforce.akana.qa.exception.TestCodeException;

public class Loader {
	private static final Logger logger = LogManager.getLogger(Loader.class);
	private static ClassLoader classLoader = Loader.class.getClassLoader();

	public static String findAbsolutePath(String filePath) {
		logger.trace("Finding:" + filePath);
		URL url = classLoader.getResource(filePath);
		if (url == null) {
			throw new TestCodeException(String.format("Unable to find path: %s", filePath));
		}

		logger.trace("Found:" + url.getPath());
		return url.getPath();
	}
}
