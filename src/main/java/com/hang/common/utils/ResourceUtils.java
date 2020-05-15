package com.hang.common.utils;

import java.io.Closeable;

public class ResourceUtils {

	public static void close(AutoCloseable ...resources) {
		for(AutoCloseable resource : resources) {
			if(resource != null) try {
				resource.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Closeable ...resources) {
		for(Closeable resource : resources) {
			if(resource != null) try {
				resource.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeIfIsResource(Object obj) {
		if(obj == null)
			return;
		if(obj instanceof Closeable)
			close((Closeable)obj);
		else if(obj instanceof AutoCloseable)
			close((AutoCloseable)obj);
	}

}
