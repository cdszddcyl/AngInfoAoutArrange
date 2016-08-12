package com.anginfo.utils;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class RunCourse {
	public static List<String> runShell(String shStr) throws Exception {

		List<String> strList = new ArrayList<String>();
		if (SystemUtils.IS_OS_LINUX) {
			Process process;
			process = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", shStr }, null, null);
			InputStreamReader ir = new InputStreamReader(process.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String line;
			process.waitFor();
			while ((line = input.readLine()) != null) {
				strList.add(line + "</br>");
			}
			ir.close();
		} else {
			Process process;
			String[] param_array = shStr.split("[\\s]+");
			ProcessBuilder pb = new ProcessBuilder(param_array);
			process = pb.start();
			InputStreamReader ir = new InputStreamReader(process.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String line;
			process.waitFor();
			while ((line = input.readLine()) != null) {
				strList.add(line + "</br>");
			}
			ir.close();
		}

		return strList;
	}
}
