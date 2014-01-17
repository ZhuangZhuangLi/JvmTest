package zip;
import java.util.zip.*;
import java.io.*;

public class ZipUtil {
     public static void main(String[] args) {
               Unzip unzip = new Unzip();
               if (unzip.unzip("C:\\Users\\lizheng\\Desktop\\G557_2007.zip","C:\\Users\\lizheng\\Desktop\\G557_2007\\")) {
                    System.out.println("文件解压成功。");
               } else {
                    System.out.println("文件解压失败。");
               }
          }    
}

class Unzip {
     public Unzip() {}

     /*
     * @param srcZipFile 需解压的文件名
     * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">@return</a>  如果解压成功返回true
     */
     public boolean unzip(String srcZipFile,String dest) {
          boolean isSuccessful = true;
          try {
               BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcZipFile));
               ZipInputStream zis = new ZipInputStream(bis);

               BufferedOutputStream bos = null;

               //byte[] b = new byte[1024];
               ZipEntry entry = null;
               while ((entry=zis.getNextEntry()) != null) {
                    String entryName = entry.getName();
                    bos = new BufferedOutputStream(new FileOutputStream(dest+entryName));
                    int b = 0;
                    while ((b = zis.read()) != -1) {
                         bos.write(b);
                    }
                    bos.flush();
                    bos.close();
               }
               zis.close();
          } catch (IOException e) {
               isSuccessful = false;
          }
          return isSuccessful;
     }
}