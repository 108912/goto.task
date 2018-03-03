package com.common;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LogHelper {
	private static String loggername = "default";
	private static Logger log = LogManager.getLogger(loggername);

	public static void Reset(String logname) {
		loggername = logname;
		log = LogManager.getLogger(loggername);
	}

	public static void Error(String message) {
		log.error(message);
	}

	public static void Info(String message) {
		log.info(message);
	}
}
