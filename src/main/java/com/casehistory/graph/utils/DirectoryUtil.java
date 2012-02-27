/**
 * 
 */
package com.casehistory.graph.utils;

import java.io.File;

/**
 * @author Abhinav Tripathi
 */
public class DirectoryUtil {

	public static final String PATH_DELIMETER = "/";

	public static void build(String path, boolean lastPartIsAFile) {
		if (pathExists(path)) {
			int nextDelimIndex = path.indexOf(PATH_DELIMETER, 1);
			while (nextDelimIndex != -1) {
				File currentDir = new File(path.substring(0, nextDelimIndex));
				if (!currentDir.exists()) {
					currentDir.mkdir();
				}
			}
			if (!lastPartIsAFile) {
				new File(path).mkdir();
			}
		}
	}

	public static boolean pathExists(String path) {
		return new File(path).exists();
	}

}
