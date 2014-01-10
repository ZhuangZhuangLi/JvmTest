package classhotswap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class CodeExcute {
	String javaname="TestCode";
	public  void compile() throws Exception
	  {
		//"javac HotSwapLoader.java"
		com.sun.tools.javac.Main.compile(new String[]{"src/classhotswap/"+this.javaname+".java"});
//	    Process p = Runtime.getRuntime().exec("javac src/classhotswap/"+this.javaname+".java");
//	    InputStream perrorStream = p.getErrorStream();
//	    InputStream poutStream = p.getInputStream();
//	    int pec, poc;
//	    while(((pec = perrorStream.read()) != -1) | ((poc = poutStream.read()) != -1))
//	    {
//	      System.err.print((pec != -1) ? ((char) pec) : ' ');
//	      System.out.print((poc != -1) ? ((char) poc) : ' ');
//	    }
//	    return p.exitValue();
	  }
	public byte[] getByte() throws Exception{
		File f=new File("src/classhotswap/"+this.javaname+".class");
		FileInputStream fis=new FileInputStream(f);
		Long length=f.length();
		byte []result=new byte[length.intValue()];
		fis.read(result);
		fis.close();
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CodeExcute c=new CodeExcute();
		c.compile();
		byte [] Classbyte=c.getByte();
		HotSwapLoader hl=new HotSwapLoader();
		Class<?> testclass=hl.loadByte(Classbyte);
		testclass.getMethod("main", new Class[]{String [].class}).invoke(testclass.newInstance(),new Object[]{null});
	}

}
