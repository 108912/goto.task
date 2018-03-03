package com.task.business;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import com.task.common.Global;

public class TaskGlobal {

	public static ClassLoader ClassLoaders = LoadClassLoaders();

	public static ClassLoader LoadClassLoaders() {
		ClassLoader loader = null;
		try {
			List<String> filepathlist = Global.TaskJarPath;
			if (filepathlist != null && filepathlist.size() > 0) {
				List<URL> urllist = new ArrayList<URL>();

				URL[] urlarray = new URL[filepathlist.size()];
				for (String item : filepathlist) {
					File file1 = new File(item);// jar包的路径
					URL url1 = file1.toURI().toURL();
					urllist.add(url1);
				}
				for (int i = 0; i < urllist.size(); i++) {
					urlarray[i] = urllist.get(i);
				}
				loader = new URLClassLoader(urlarray);// 创建类加载器
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return loader;
	}
}
