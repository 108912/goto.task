package com.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileHelper {
	String newline = "\r\n";// windows

	/**
	 * д���ļ�,ĩβ�Զ����\r\n utf-8 ׷��
	 * 
	 * @param path
	 * @param str
	 */
	public static void Write(String path, String str) {
		try {
			File file = new File(path);
			if (!file.exists())
				file.createNewFile();
			FileOutputStream out = new FileOutputStream(file); // true��ʾ׷��
			StringBuffer sb = new StringBuffer();
			sb.append(str + "\r\n");
			out.write(sb.toString().getBytes("utf-8"));//
			out.close();
		} catch (IOException ex) {
			System.out.println(ex.getStackTrace());
		}
	}

	/**
	 * д���ļ�,ĩβ�Զ����\r\n
	 * 
	 * @param path
	 * @param str
	 */
	public static void Write(String path, String str, boolean is_append, String encode) {
		try {
			File file = new File(path);
			if (!file.exists())
				file.createNewFile();
			FileOutputStream out = new FileOutputStream(file, is_append); // true��ʾ׷��
			StringBuffer sb = new StringBuffer();
			sb.append(str + "\r\n");
			out.write(sb.toString().getBytes(encode));//
			out.close();
		} catch (IOException ex) {
			System.out.println(ex.getStackTrace());
		}
	}

	/**
	 * �����ļ���string�Żأ����\r\n����
	 * 
	 * @param path
	 * @return
	 */
	public static String readLogByString(String path) {
		StringBuffer sb = new StringBuffer();
		String tempstr = null;
		try {
			File file = new File(path);
			if (!file.exists())
				throw new FileNotFoundException();
			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis, "utf-8"));
			while ((tempstr = br.readLine()) != null) {
				sb.append(tempstr + "\r\n");
			}
		} catch (IOException ex) {
			System.out.println(ex.getStackTrace());
		}
		return sb.toString();
	}

	/**
	 * ������� �����ļ���string�Żأ����\r\n����
	 * 
	 * @param path
	 * @return
	 */
	public static String readLogByStringAndEncode(String path, String encode) {
		StringBuffer sb = new StringBuffer();
		String tempstr = null;
		try {
			File file = new File(path);
			if (!file.exists())
				throw new FileNotFoundException();
			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis, encode));
			while ((tempstr = br.readLine()) != null) {
				sb.append(tempstr + "\r\n");
			}
		} catch (IOException ex) {
			System.out.println(ex.getStackTrace());
		}
		return sb.toString();
	}

	/**
	 * ���ж�ȡ�ļ�����list<String>����ʽ����
	 * 
	 * @param path
	 * @return
	 */
	public static List<String> readLogByList(String path) {
		List<String> lines = new ArrayList<String>();
		String tempstr = null;
		try {
			File file = new File(path);
			if (!file.exists()) {
				throw new FileNotFoundException();
			}
			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis, "utf-8"));
			while ((tempstr = br.readLine()) != null) {
				lines.add(tempstr.toString());
			}
		} catch (IOException ex) {
			System.out.println(ex.getStackTrace());
		}
		return lines;
	}
	/**
	 * ����Ŀ¼
	 * 
	 * @param dir_path
	 */
	public static void mkDir(String dir_path) {
		File myFolderPath = new File(dir_path);
		try {
			if (!myFolderPath.exists()) {
				myFolderPath.mkdir();
			}
		} catch (Exception e) {
			LogHelper.Error("�½�Ŀ¼��������");
			e.printStackTrace();
		}
	}

	/**
	 * �����ļ�
	 * 
	 * @param file_path
	 */
	public static void createNewFile(String file_path) {
		File myFilePath = new File(file_path);
		try {
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
		} catch (Exception e) {
			LogHelper.Error("�½��ļ���������");
			e.printStackTrace();
		}
	}

	/**
	 * �ݹ�ɾ���ļ�����Ŀ¼
	 * 
	 * @param file_path
	 */
	public static void deleteEveryThing(String file_path) {
		try {
			File file = new File(file_path);
			if (!file.exists()) {
				return;
			}
			if (file.isFile()) {
				file.delete();
			} else {
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					String root = files[i].getAbsolutePath();// �õ����ļ����ļ��еľ���·��
					deleteEveryThing(root);
				}
				file.delete();
			}
		} catch (Exception e) {
			LogHelper.Error("ɾ���ļ�ʧ��");
		}
	}

	/*
	 * �õ�һ���ļ����������ļ�
	 */
	public static List<String> getAllFileNameInFold(String fold_path) {
		List<String> file_paths = new ArrayList<String>();

		LinkedList<String> folderList = new LinkedList<String>();
		folderList.add(fold_path);
		while (folderList.size() > 0) {
			File file = new File(folderList.peekLast());
			folderList.removeLast();
			File[] files = file.listFiles();
			ArrayList<File> fileList = new ArrayList<File>();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					folderList.add(files[i].getPath());
				} else {
					fileList.add(files[i]);
				}
			}
			for (File f : fileList) {
				file_paths.add(f.getAbsoluteFile().getPath());
			}
		}
		return file_paths;
	}
}