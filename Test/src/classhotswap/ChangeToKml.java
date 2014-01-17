package classhotswap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import de.micromata.opengis.kml.v_2_2_0.Kml;

public class ChangeToKml {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		/*
		 * @echo off
SET FWTOOLS_DIR=C:\PROGRA~2\FWTOOL~1.7
call "%FWTOOLS_DIR%\bin\setfwenv.bat"

cd C:\Users\lizheng\Desktop\G557_2007
ogr2ogr -f "KML" SMSApolys.kml SMSApolys.TAB 
&& 
		 */
//		
//		   Process process = Runtime.getRuntime().exec("cmd.exe /c   SET FWTOOLS_DIR=C:\\PROGRA~2\\FWTOOL~1.7 && cmd.exe /c  call \"%FWTOOLS_DIR%\\bin\\setfwenv.bat\" &&cmd.exe /c cd C:\\Users\\lizheng\\Desktop\\G557_2007 && ogr2ogr -f KML SMSApolys.kml SMSApolys.TAB");
//		   process.waitFor();
//	   retriveKML();
		File file=new File("C:\\Users\\lizheng\\Desktop\\G557_2007\\SMSApolys.kml");
		parseKMLtoPlaceMark(file);

	}

	public static void parseKMLtoPlaceMark(File file) {
		Kml kml=Kml.unmarshal(file);
		
		
	}

	public static void retriveKML() throws IOException, InterruptedException {
		String batFilename="C:\\Users\\lizheng\\Desktop\\setfx.bat";
		   File batFile=new File(batFilename);
		   batFile.createNewFile();
		   BufferedWriter output = new BufferedWriter(new FileWriter(batFile));
		   String _n=System.getProperty("line.separator");
		   output.write("@echo off"+_n);
		   output.write("SET FWTOOLS_DIR=C:\\PROGRA~2\\FWTOOL~1.7"+_n);
		   output.write("call \"%FWTOOLS_DIR%\\bin\\setfwenv.bat\""+_n);
		   output.write("cd C:\\Users\\lizheng\\Desktop\\G557_2007"+_n);
		   output.write("ogr2ogr -f \"KML\" SMSApolys.kml SMSApolys.TAB");
		   output.close();
		   Process process = Runtime.getRuntime().exec("cmd.exe /c   call "+batFilename);
		   process.waitFor();
	}

}
