package hxk.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

/**
 * @author Administrator
 * @description
 *2015-5-29  下午2:27:50
 */
public class ReadURLResponse {
    public static void main(String[] args) throws MalformedURLException, IOException {
	io();
    }
    
    
    /**
     * @description 常规的读取一个URL返回的资源	
     *2015-5-29  下午2:29:34
     *返回类型:void
     */
    public static void common() throws MalformedURLException, IOException{
	InputStream in = new URL( "http://commons.apache.org" ).openStream();
	 try {
	   InputStreamReader inR = new InputStreamReader( in );
	   BufferedReader buf = new BufferedReader( inR );
	   String line;
	   while ( ( line = buf.readLine() ) != null ) {
	     System.out.println( line );
	   }
	 } finally {
	   in.close();
	 }
    }
    
    public static void io() {
	try {
	    InputStream in = new URL( "http://commons.apache.org" ).openStream();
	    BufferedInputStream ins = new BufferedInputStream(in);
	    BufferedOutputStream outs = new BufferedOutputStream(new FileOutputStream("data.txt"));
	    //每次开启1024的byte字节
	    byte[] buffer = new byte[1024];
	    int length = -1;
		
	    while((length = ins.read(buffer))!=-1) {
		outs.write(buffer, 0, length);
	    }
		
	    outs.flush();
	    outs.close();
	    in.close();
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	
    }
    
    /**
     * @description 使用了IOUtils之后的读取代码
     * 		IOUtils.toString(inputstream)	
                     缺点  : 使用以下方式读取一个1GB的文件时会造成在内存创造一个1GB的String
     *2015-5-29  下午2:32:49
     *返回类型:void
     */
    public static void ioUtil() throws MalformedURLException, IOException{
	 InputStream in = new URL( "http://commons.apache.org" ).openStream();
	 try {
	   System.out.println( IOUtils.toString( in ) );
	 } finally {
	   IOUtils.closeQuietly(in);
	 }
    }
}
