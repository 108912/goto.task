package com.task.main;

import com.task.business.LoadCall;
import com.task.business.LoadTaskConfig;
import com.task.business.ThreadPoolManager;

public class Main {

	public static void main(String[] args) {

		ThreadPoolManager tpm = new ThreadPoolManager();
		LoadCall loadcall = new LoadCall();
		tpm.setListCall(loadcall.GetCallConfig(LoadTaskConfig.GetConfig()));
		tpm.RunOne();
		// tpm.RunContinue();
		System.out.println("end");
	}
}
