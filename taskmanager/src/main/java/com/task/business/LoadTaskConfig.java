package com.task.business;

import java.util.ArrayList;
import java.util.List;

import com.common.FileHelper;
import com.task.common.Global;
import com.task.model.TaskConfigModel;

import net.sf.json.JSONArray;

public class LoadTaskConfig {

	@SuppressWarnings({ "unchecked", "deprecation" })
	public static List<TaskConfigModel> GetConfig() {
		List<TaskConfigModel> result = new ArrayList<TaskConfigModel>();
		String pathfull = Global.TaskConfigPath + Global.TaskConfigName;
		String filecontent = FileHelper.readLogByString(pathfull);
		JSONArray jsonarray = JSONArray.fromObject(filecontent);
		result = (List<TaskConfigModel>) JSONArray.toList(jsonarray, TaskConfigModel.class);
		return result;
	}
}
