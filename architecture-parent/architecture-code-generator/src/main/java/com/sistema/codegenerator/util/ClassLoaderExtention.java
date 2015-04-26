package com.sistema.codegenerator.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class ClassLoaderExtention extends ClassLoader {

	public static ClassLoaderExtention getInstance(String... pClassPath) {
		return new ClassLoaderExtention(ClassLoaderExtention.class.getClassLoader(), pClassPath);
	}

	private List<URL> classPath;

	public ClassLoaderExtention(ClassLoader parent, String... pClassPath) {

		super(parent);

		classPath = new ArrayList<>();

		for (String string : pClassPath) {
			try {
				classPath.add(new File(string).toURI().toURL());
			} catch (MalformedURLException e2) {
				e2.printStackTrace();
			}
		}

	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {

		try {
			return super.loadClass(name);
		}

		catch (Exception e) {

			URLClassLoader cl = URLClassLoader.newInstance((URL[]) classPath.toArray(new URL[classPath.size()]));
			return cl.loadClass(name);

		}
	}
}
