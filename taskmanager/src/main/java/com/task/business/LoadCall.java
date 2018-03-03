package com.task.business;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import com.task.model.TaskConfigModel;

public class LoadCall {
	public List<Callable<String>> GetCallConfig(List<TaskConfigModel> taskconfig) {
		List<Callable<String>> listCall = new ArrayList<Callable<String>>();
		if (taskconfig != null && taskconfig.size() > 0) {
			for (TaskConfigModel item : taskconfig) {
				if (item.isIsRun()) {
					CallRealize t = new CallRealize();
					t.runmaxnum = item.getRunMaxSecond();
					t.id = item.getId();
					t.sleepnum = item.getRunIntervalSecond();
					t.name = item.getTaskName();
					t.pagkagename = item.getPackageName();
					t.classname = item.getClassName();
					t.actionname = item.ActionName;
					listCall.add(t);
				}
			}
		}
		return listCall;
	}
}
