package com.cooksys.serialization.assignment;

import java.io.File;

public class FileUtil {

	public static File[] enterDirectory(File directory) {
		try {
			if (!directory.isDirectory())
				throw new RuntimeException("Argument is not a directory.");

			File[] directoryContents = directory.listFiles();
			return directoryContents;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new File[0];
		}

	}
}
