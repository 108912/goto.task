package com.taskbusiness.demo1;

import java.util.Calendar;

public class callaction {

	public String Test() {
		Calendar c = Calendar.getInstance();
		String result = "com.task.demo1.Test date:" + c.getTime();
		System.out.println(result);
		return result;
	}

	public String Test2() {
		Calendar c = Calendar.getInstance();
		String result = "com.task.demo1.Test2 date:" + c.getTime();
		System.out.println(result);
		return result;
	}
}
