package com.common;

import java.io.File;
import java.io.IOException;

public class CommonHelper {

	public static String GetGuid() {
		return java.util.UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String GetStr(String str) {
		String result = "";
		if (str != null && str != "" && str.trim().length() > 0) {
			result = str.trim();
		}
		return result;
	}

	public static Boolean StrIsNotNull(String s) {
		Boolean result = false;
		if (s != null && s != "") {
			result = true;
		}
		return result;
	}

	/**
	 * 获取当前运行目录
	 * 
	 * @return
	 */
	public static String GetCurrentPath() {
		String result = null;
		File directory = new File(".");
		try {
			result = directory.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String UrlAddParam(String urlstr, String paramstr) {
		String result = urlstr;
		if (StrIsNotNull(urlstr)) {
			if (urlstr.indexOf('?') > 0) {
				result += "&" + paramstr;
			} else {
				result += "?" + paramstr;
			}
		}
		return result;
	}

	public static String getIP(javax.servlet.http.HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}
}
