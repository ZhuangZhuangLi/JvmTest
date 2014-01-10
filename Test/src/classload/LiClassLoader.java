package classload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LiClassLoader extends ClassLoader {
	public LiClassLoader(){
		//super(LiClassLoader.class.getClassLoader());
	}
	public Class<?> loadByte(String name){
		byte[] b=null;
		try {
			b = getClassdata(name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.defineClass(null,b, 0, b.length);
	}
	public byte [] getClassdata(String name) throws IOException{
		File f=new File(name);
		FileInputStream fis=new FileInputStream(name);
		Long length=f.length();
		byte []result=new byte[length.intValue()];
		fis.read(result);
		fis.close();
		return result;
	}
	public static void main(String[] args) {
		LiClassLoader loader=new LiClassLoader();
		Class<?>c=loader.loadByte("bin/testgc/TestAllocale.class");
		System.out.println(c);
	}

}
