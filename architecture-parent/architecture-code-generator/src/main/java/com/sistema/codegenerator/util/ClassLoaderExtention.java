package com.sistema.codegenerator.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoaderExtention extends ClassLoader {

	public static ClassLoaderExtention getInstance(String pClassPath) {
		return new ClassLoaderExtention(ClassLoaderExtention.class.getClassLoader(), pClassPath);
	}

	private String classPath;

	public ClassLoaderExtention(ClassLoader parent, String pClassPath) {
		super(parent);
		classPath = pClassPath;
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {

		try {
			return super.loadClass(name);
		}

		catch (Exception e) {

			try {

				URLClassLoader cl = URLClassLoader.newInstance(new URL[] { new File(classPath).toURI().toURL() });
				return cl.loadClass(name);

			} catch (MalformedURLException e2) {
				e2.printStackTrace();
			}
		}

		return null;
	}
}
