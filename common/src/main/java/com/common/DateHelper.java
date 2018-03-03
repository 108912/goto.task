package com.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.sun.jmx.snmp.Timestamp;

public class DateHelper {
	// ʱ���ʽ��
	private final static SimpleDateFormat simpleDateFormat_yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	// ʱ���ʽ��
	private final static SimpleDateFormat simpleDateFormat_yyyyMMdd = new SimpleDateFormat("yyyyMMdd");

	// ʱ���ʽ��
	private final static SimpleDateFormat simpleDateFormat_yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");

	// ʱ���ʽ��
	private final static SimpleDateFormat simpleDateFormat_yyyy_MM = new SimpleDateFormat("yyyy-MM");

	// ʱ���ʽ��
	private final static SimpleDateFormat simpleDateFormat_yyyyMM = new SimpleDateFormat("yyyyMM");

	// ʱ���ʽ��
	private final static SimpleDateFormat simpleDateFormat_yyyy_MM_dd_HH_mm_ss_SSS = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss,SSS");

	/**
	 * ��ǰʱ��
	 */
	public static Date getNowDate() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	public static String DateTo_yyyyMMdd(Date date) {
		if (date == null)
			return null;
		return simpleDateFormat_yyyyMMdd.format(date);
	}

	public static String DateTo_yyyy_MM_dd(Date date) {
		if (date == null)
			return null;
		return simpleDateFormat_yyyy_MM_dd.format(date);
	}

	public static Date stringToDate_yyyy_MM_dd(String str) {
		if (str == null)
			return null;
		try {
			return simpleDateFormat_yyyy_MM_dd.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ���ص�ǰʱ��(����)
	 */
	public static long getNowTime_long() {
		long time = new Date().getTime();
		return time;
	}

	/**
	 * �õ���ǰʱ��
	 */
	public static String getNowTime_yyyy_MM_dd_HH_mm_ss() {
		return simpleDateFormat_yyyy_MM_dd_HH_mm_ss.format(new Date());
	}

	/**
	 * �õ���ǰʱ��
	 */
	public static Timestamp getNowTime_timestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * ��longתΪTimestamp
	 */
	public static Timestamp LongToTimestamp(long _long) {
		return new Timestamp(_long);
	}
}
