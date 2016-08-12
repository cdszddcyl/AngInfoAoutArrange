package com.anginfo.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 * @author StoneCai 2016年08月05日18:00:03 添加 文件操作类
 *
 */
public class FileOperate {

	public static void readFileByByte(String filePath) {
		File file = new File(filePath);
		// InputStream:此抽象类是表示字节输入流的所有类的超类。
		InputStream ins = null;
		OutputStream outs = null;
		try {
			// FileInputStream:从文件系统中的某个文件中获得输入字节。
			ins = new FileInputStream(file);
			outs = new FileOutputStream("");
			int temp;
			// read():从输入流中读取数据的下一个字节。
			while ((temp = ins.read()) != -1) {
				outs.write(temp);
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if (ins != null && outs != null) {
				try {
					outs.close();
					ins.close();
				} catch (IOException e) {
					e.getStackTrace();
				}
			}
		}
	}

	public static void readFileByCharacter(String filePath) {
		File file = new File(filePath);
		// FileReader:用来读取字符文件的便捷类。
		FileReader reader = null;
		FileWriter writer = null;
		try {
			reader = new FileReader(file);
			writer = new FileWriter("");
			int temp;
			while ((temp = reader.read()) != -1) {
				writer.write((char) temp);
			}
		} catch (IOException e) {
			e.getStackTrace();
		} finally {
			if (reader != null && writer != null) {
				try {
					reader.close();
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Stone.Cai 2016年08月08日10:13:17 添加 写入文件数据
	 * 
	 * @param infile
	 * @param outFile
	 */
	public static void writerFileByLine(File file, String data) {
		BufferedWriter bufWriter = null;
		try {
			bufWriter = new BufferedWriter(new FileWriter(file, true));
			bufWriter.write(data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufWriter != null) {
				try {
					bufWriter.close();
				} catch (IOException e) {
					e.getStackTrace();
				}
			}
		}
	}

	public static void readFileByLine(String infile, String outFile) {
		File file = new File(infile);
		// BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
		BufferedReader bufReader = null;
		BufferedWriter bufWriter = null;
		try {
			// FileReader:用来读取字符文件的便捷类。
			bufReader = new BufferedReader(new FileReader(file));
			bufWriter = new BufferedWriter(new FileWriter(outFile));
			// buf = new BufferedReader(new InputStreamReader(new
			// FileInputStream(file)));
			String temp = null;
			while ((temp = bufReader.readLine()) != null) {
				bufWriter.write(temp + "\n");
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if (bufReader != null && bufWriter != null) {
				try {
					bufReader.close();
					bufWriter.close();
				} catch (IOException e) {
					e.getStackTrace();
				}
			}
		}
	}

	/**
	 * Stone.Cai 2016年08月12日14:09:52 添加 读取文件
	 */
	@SuppressWarnings("resource")
	public static String readFileByLine(String path) {
		StringBuffer str=new StringBuffer();
		BufferedReader bufReader = null;
		try {
			bufReader = new BufferedReader(new FileReader(new File(path)));
			String temp = null;
			while ((temp = bufReader.readLine()) != null) {
				str.append(temp);
			}
			return str.toString();
		} catch (Exception e) {
			return null;
		}finally{
			if(bufReader!=null){
				try {
					bufReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Stone.Cai 2016年08月12日09:46:41 添加 根据目录删除文件
	 */
	public static void deleteDir(String path) {
		File file = new File(path);
		// 删除子项
		deleteFile(file);
		// 删除自己
		file.delete();
	}

	private static void deleteFile(File file) {
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				deleteFile(f);
			}
			;
		}
		file.delete();
	}

}
