package src.Files;

import java.io.File;
import src.apps.DatCon;

public class WorkingDir {
	private File workingDir = null;

	private File tempDir = null;

	//private DatCon datCon;

	private String userHome;

	// private LoggingPanel log;

	public static WorkingDir instance;

	public WorkingDir(DatCon datCon) {
		instance = this;
		//this.datCon = datCon;
		// this.log = datCon.log;
		// this.log = csvView.log;
		userHome = System.getProperty("user.home");
		if (userHome == null || userHome.length() == 0) {
			DatConLog.Error("Can't find the users home directory");
		} else {
			workingDir = makeDir(".DatConDir");
			tempDir = makeDir(".DatConDir/temp");
		}
	}

	private File makeDir(String dirName) {
		File theDir = new File(userHome + "/" + dirName);
		if (!theDir.exists()) {
			try {
				boolean result = theDir.mkdir();
				if (result) {
					DatConLog.Log("Created working Dir "
							+ theDir.getAbsolutePath());
				} else {
					DatConLog.Error("Can't create working Dir "
							+ theDir.getAbsolutePath());
					return null;
				}
			} catch (SecurityException se) {
				DatConLog.Exception(se);
				return null;
			}
		}
		return theDir.getAbsoluteFile();
	}

	public File getWorkingDir() {
		return workingDir;
	}

	public String getWorkingDirName() {
		if (workingDir != null) {
			return workingDir.getAbsolutePath();
		} else
			return null;
	}

	public String getTempDirName() {
		if (tempDir != null) {
			return tempDir.getAbsolutePath();
		} else
			return null;
	}

	public File getTempDir() {
		return tempDir;
	}
}
