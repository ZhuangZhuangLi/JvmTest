package zip;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

public class ZipUtil {
	public static void zip(File file, File zipFile) throws Exception {
		ZipOutputStream output = null;
		try {
			output = new ZipOutputStream(new FileOutputStream(zipFile));
			zipFile(output, file, "");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (output != null) {
				output.flush();
				output.close();
			}
		}
	}

	private static void zipFile(ZipOutputStream output, File file,
			String basePath) throws IOException {
		FileInputStream input = null;
		try {
			if (file.isDirectory()) {
				File list[] = file.listFiles();
				basePath = basePath + (basePath.length() == 0 ? "" : "/")
						+ file.getName();
				for (File f : list)
					zipFile(output, f, basePath);
			} else {
				basePath = (basePath.length() == 0 ? "" : basePath + "/")
						+ file.getName();
				output.putNextEntry(new ZipEntry(basePath));
				input = new FileInputStream(file);
				int readLen = 0;
				byte[] buffer = new byte[1024 * 8];
				while ((readLen = input.read(buffer, 0, 1024 * 8)) != -1)
					output.write(buffer, 0, readLen);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (input != null)
				input.close();
		}
	}

	public static void unzip(String zipFilePath, String unzipDirectory)
			throws Exception {
		InputStream input = null;
		OutputStream output = null;
		try {
			File file = new File(zipFilePath);
			ZipFile zipFile = new ZipFile(file);
			String name = file.getName().substring(0,
					file.getName().lastIndexOf("."));
			File unzipFile = new File(unzipDirectory + "/" + name);
			if (unzipFile.exists())
				unzipFile.delete();
			unzipFile.mkdir();
			Enumeration zipEnum = zipFile.getEntries();
			ZipEntry entry = null;
			String entryName = null, path = null;
			String names[] = null;
			int length;
			while (zipEnum.hasMoreElements()) {
				entry = (ZipEntry) zipEnum.nextElement();
				entryName = new String(entry.getName());
				names = entryName.split("\\/");
				length = names.length;
				path = unzipFile.getAbsolutePath();
				for (int v = 0; v < length; v++) {
					if (v < length - 1) {
						File filepath = new File(path += "/" + names[v] + "/");
						filepath.mkdirs();
					}

					else {
						if (entryName.endsWith("/")) {
							File ppath = new File(unzipFile.getAbsolutePath()
									+ "/" + entryName);
							ppath.mkdirs();
						} else {
							input = zipFile.getInputStream(entry);
							output = new FileOutputStream(new File(
									unzipFile.getAbsolutePath() + "/"
											+ entryName.replaceAll("\\s*", "")));
							byte[] buffer = new byte[1024 * 8];
							int readLen = 0;
							while ((readLen = input.read(buffer, 0, 1024 * 8)) != -1)
								output.write(buffer, 0, readLen);
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (input != null)
				input.close();
			if (output != null) {
				output.flush();
				output.close();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		String path = "C:\\Users\\lizheng\\Desktop\\";
		String url = "http://www.commsalliance.com.au/__data/assets/file/0009/2322/G557_2007.zip";
		String file = downloadFromURL(path, url);
		unzip(path + file, path);
		String opfile = getFileName(path, file);
		retriveKML(path + file.replaceAll("\\.zip", ""), opfile);

	}

	public static String getFileName(String path, String file) {
		File dir = new File(path + file.replaceAll("\\.zip", "") + "\\");
		String[] filenamelist = dir.list();
		String opfile = "";
		Pattern pattern = Pattern.compile("TAB$");
		for (String filename : filenamelist) {
			Matcher mat = pattern.matcher(filename);
			if (mat.find()) {
				opfile = filename;
			}
		}
		return opfile;
	}

	public static void retriveKML(String path, String opfile)
			throws IOException, InterruptedException {

		String batFilename = path + "set.bat";
		File batFile = new File(batFilename);
		batFile.createNewFile();
		BufferedWriter output = new BufferedWriter(new FileWriter(batFile));
		String _n = System.getProperty("line.separator");
		output.write("@echo off" + _n);
		output.write("SET FWTOOLS_DIR=C:\\PROGRA~2\\FWTOOL~1.7" + _n);
		output.write("call \"%FWTOOLS_DIR%\\bin\\setfwenv.bat\"" + _n);
		output.write("cd " + path + _n);
		output.write("ogr2ogr -f \"KML\" SMSApolys.kml " + opfile);
		output.close();
		Process process = Runtime.getRuntime().exec(
				"cmd.exe /c   call " + batFilename);
		process.waitFor();
	}

	public static String downloadFromURL(String path, String Url)
			throws Exception {
		int bytesum = 0;
		int byteread = 0;
		String filename = Url.substring(Url.lastIndexOf("/")).replaceAll(
				"\\s*", "");
		URL url = new URL(Url);
		URLConnection conn = url.openConnection();
		InputStream inStream = conn.getInputStream();
		FileOutputStream fs = new FileOutputStream(path + filename);
		byte[] buffer = new byte[1204];
		while ((byteread = inStream.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, byteread);
		}
		fs.close();
		return filename;
	}

}
