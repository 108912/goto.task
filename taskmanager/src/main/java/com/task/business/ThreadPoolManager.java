package com.task.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.task.model.TaskInfoModel;

public class ThreadPoolManager {

	public ThreadPoolManager() {
		setThreadPoolNum(4);
	}

	private int ThreadPoolNum;

	public int getThreadPoolNum() {
		return ThreadPoolNum;
	}

	public void setThreadPoolNum(int threadPoolNum) {
		ThreadPoolNum = threadPoolNum;
	}

	private List<Callable<String>> listcall;

	public List<Callable<String>> getListCall() {
		return listcall;
	}

	public void setListCall(List<Callable<String>> ListCall) {
		listcall = ListCall;
	}

	/**
	 * 所有任务执行一次
	 * 
	 * @return
	 * @throws TimeoutException
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public long RunOne() {
		long result = 0;
		ExecutorService exec = Executors.newCachedThreadPool();
		List<TaskInfoModel> listtask = new ArrayList<TaskInfoModel>();
		List<Callable<String>> ListCallWait = getListCall();
		List<Callable<String>> ListCallRun = new ArrayList<Callable<String>>();
		List<Callable<String>> ListCallComplete = new ArrayList<Callable<String>>();
		boolean iscontinue = true;
		do {
			if (listtask.size() == 0 && ListCallWait != null && ListCallWait.size() == 0) {
				break;
			}
			// 线程池任务状态更新
			if (listtask.size() > 0) {
				for (int i = listtask.size(); i >= 0; i--) {
					if (i > 0) {
						try {
							String res = "";
							Calendar nowTime = Calendar.getInstance();
							TaskInfoModel temptask = listtask.get(i - 1);
							Future<String> tempfuture = temptask.getFuture();
							Callable<String> tempcallable = temptask.getCallable();
							if (temptask.getRunend() < nowTime.getTimeInMillis()) {
								res = temptask.getId() + " " + temptask.getName() + " 运行超时";
								tempfuture.cancel(true);
							} else {
								res = tempfuture.get(0, TimeUnit.SECONDS);
							}
							ListCallComplete.add(tempcallable);
							ListCallRun.remove(tempcallable);
							listtask.remove(temptask);
							System.out.println("任务执行完成" + res);
							result++;
						} catch (Exception e) {
							// e.printStackTrace();
						}
					}
				}
			}

			// 线程池添加任务
			if (listtask.size() < getThreadPoolNum() && ListCallWait.size() > 0) {
				for (int i = listtask.size(); i < getThreadPoolNum(); i++) {
					if (ListCallWait.size() > 0) {
						CallRealize tempt = (CallRealize) ListCallWait.get(0);
						Future<String> tempfuture = exec.submit(tempt);
						TaskInfoModel temptask = new TaskInfoModel();

						Calendar nowbegin = Calendar.getInstance();
						Calendar nowend = Calendar.getInstance();
						nowend.add(Calendar.SECOND, tempt.runmaxnum);

						temptask.setRunbegin(nowbegin.getTimeInMillis());
						temptask.setRunend(nowend.getTimeInMillis());
						temptask.setId(tempt.id);
						temptask.setName(tempt.name);
						temptask.setCallable(tempt);
						temptask.setFuture(tempfuture);
						listtask.add(temptask);
						ListCallRun.add(tempt);
						ListCallWait.remove(tempt);
					}
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (iscontinue);

		System.out.println("pool end");
		return result;

	}

	/**
	 * 所有任务一直执行
	 * 
	 * @return
	 */
	public long RunContinue() {
		long result = 0;
		ExecutorService exec = Executors.newCachedThreadPool();
		List<TaskInfoModel> listtask = new ArrayList<TaskInfoModel>();
		List<Callable<String>> ListCallWait = getListCall();
		List<Callable<String>> ListCallRun = new ArrayList<Callable<String>>();
		List<Callable<String>> ListCallComplete = new ArrayList<Callable<String>>();

		boolean iscontinue = true;
		do {
			if (listtask.size() == 0 && ListCallWait != null && ListCallWait.size() == 0) {
				break;
			}
			// 线程池任务状态更新
			if (listtask.size() > 0) {
				for (int i = listtask.size(); i >= 0; i--) {
					if (i > 0) {
						try {
							String res = "";
							Calendar nowTime = Calendar.getInstance();
							TaskInfoModel temptask = listtask.get(i - 1);
							Future<String> tempfuture = temptask.getFuture();
							Callable<String> tempcallable = temptask.getCallable();
							if (temptask.getRunend() < nowTime.getTimeInMillis()) {
								res = temptask.getId() + " " + temptask.getName() + " 运行超时";
								tempfuture.cancel(true);
							} else {
								res = tempfuture.get(0, TimeUnit.SECONDS);
							}
							ListCallComplete.add(tempcallable);
							ListCallRun.remove(tempcallable);
							listtask.remove(temptask);
							System.out.println("任务执行完成" + res);
							result++;
						} catch (Exception e) {
							// e.printStackTrace();
						}
					}
				}
			}

			// 线程池添加任务
			if (listtask.size() < getThreadPoolNum() && ListCallWait.size() > 0) {
				for (int i = listtask.size(); i < getThreadPoolNum(); i++) {
					if (ListCallWait.size() > 0) {
						CallRealize tempt = (CallRealize) ListCallWait.get(0);
						Future<String> tempfuture = exec.submit(tempt);
						TaskInfoModel temptask = new TaskInfoModel();

						Calendar nowbegin = Calendar.getInstance();
						Calendar nowend = Calendar.getInstance();
						nowend.add(Calendar.SECOND, tempt.runmaxnum);

						temptask.setRunbegin(nowbegin.getTimeInMillis());
						temptask.setRunend(nowend.getTimeInMillis());
						temptask.setId(tempt.id);
						temptask.setName(tempt.name);
						temptask.setCallable(tempt);
						temptask.setFuture(tempfuture);
						listtask.add(temptask);
						ListCallRun.add(tempt);
						ListCallWait.remove(tempt);
					}
				}
			}
			if (ListCallComplete.size() > 0) {
				Callable<String> tempcallable = ListCallComplete.get(0);
				ListCallWait.add(tempcallable);
				ListCallComplete.remove(tempcallable);
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (iscontinue);

		System.out.println("pool end");
		return result;
	}

	/**
	 * 撤销所有线程
	 * 
	 * @return
	 */
	public boolean Cancel() {
		boolean result = false;
		return result;

	}
}
