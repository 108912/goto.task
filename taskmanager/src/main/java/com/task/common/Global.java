package com.task.common;

import java.util.ArrayList;
import java.util.List;

import com.common.BreakPointHelper;
import com.common.FileHelper;

public class Global {

	public static BreakPointHelper breakpointManager = new BreakPointHelper("D:\\config\\breakpoint\\");

	public static String TaskConfigPath = "src/main/resources/TaskConfig/";
	public static String TaskConfigName = "Task1.json";
	public static String JarPath = "D:/Program/javatest/";
	public static List<String> TaskJarPath = LoadTaskJarPath();

	public static List<String> LoadTaskJarPath() {
		List<String> result = new ArrayList<String>();
		List<String> templist = FileHelper.getAllFileNameInFold(JarPath);
		if (templist != null && templist.size() > 0) {
			String validstr = ".jar";
			for (String o : templist) {
				if (o.indexOf(validstr) == (o.length() - validstr.length())) {
					result.add(o);
				}
			}
		}
		return result;
	}

}
