package com.task.model;

public class TaskConfigModel {

	private int id;
	private boolean IsRun;
	private int RunIntervalSecond;
	private int RunMaxSecond;
	private String TaskName;
	private String PackageName;
	private String ClassName;
	public String ActionName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isIsRun() {
		return IsRun;
	}

	public void setIsRun(boolean isRun) {
		IsRun = isRun;
	}

	public int getRunIntervalSecond() {
		return RunIntervalSecond;
	}

	public void setRunIntervalSecond(int runIntervalSecond) {
		RunIntervalSecond = runIntervalSecond;
	}

	public int getRunMaxSecond() {
		return RunMaxSecond;
	}

	public void setRunMaxSecond(int runMaxSecond) {
		RunMaxSecond = runMaxSecond;
	}

	public String getTaskName() {
		return TaskName;
	}

	public void setTaskName(String taskName) {
		TaskName = taskName;
	}

	public String getPackageName() {
		return PackageName;
	}

	public void setPackageName(String packageName) {
		PackageName = packageName;
	}

	public String getClassName() {
		return ClassName;
	}

	public void setClassName(String className) {
		ClassName = className;
	}

	public String getActionName() {
		return ActionName;
	}

	public void setActionName(String actionName) {
		ActionName = actionName;
	}

}
