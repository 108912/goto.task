package com.task.model;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class TaskInfoModel {
	private int id;
	private int maxrunnum;
	private long runbegin;
	private long runend;
	private Callable<String> callable;
	private Future<String> future;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String name;

	public int getMaxrunnum() {
		return maxrunnum;
	}

	public void setMaxrunnum(int maxrunnum) {
		this.maxrunnum = maxrunnum;
	}

	public long getRunbegin() {
		return runbegin;
	}

	public void setRunbegin(long runbegin) {
		this.runbegin = runbegin;
	}

	public long getRunend() {
		return runend;
	}

	public void setRunend(long runend) {
		this.runend = runend;
	}

	public Callable<String> getCallable() {
		return callable;
	}

	public void setCallable(Callable<String> callable) {
		this.callable = callable;
	}

	public Future<String> getFuture() {
		return future;
	}

	public void setFuture(Future<String> future) {
		this.future = future;
	}
}
