package com.task.business;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class CallRealize implements Callable<String> {
	public int runmaxnum;
	public int id;
	public int sleepnum;
	public String name;
	public String pagkagename;
	public String classname;
	public String actionname;

	public String call() {
		String result = "id:" + id + ",name:" + name + ",result:";
		try {
			Class<?> cls = TaskGlobal.ClassLoaders.loadClass(pagkagename + "." + classname);
			Object obj = cls.newInstance();
			Method method0 = cls.getMethod(actionname);
			Object o0 = method0.invoke(obj);
			result += (String) o0;
		} catch (Exception e) {
			e.printStackTrace();
			result += "¼ÓÔØ³ö´í";
		}
		System.out.println(result);
		try {
			Thread.sleep(sleepnum * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result;
	}
}
