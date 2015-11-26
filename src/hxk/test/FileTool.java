package hxk.test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.filefilter.OrFileFilter;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

/**
 * @author Administrator
 * @description
 *2015-5-29  下午3:41:40
 */
public class FileTool {
    public static void main(String[] args) {
	filter();
    }
    
    
    /**
     * @description 使用FileUtils.readLines(file, "UTF-8")来读取文件..并返回List<String>	
     *2015-5-29  下午3:44:00
     *返回类型:void
     */
    public static void readLine(){
	File file = new File("data.txt");
	try {
	    List<String> lines = FileUtils.readLines(file, "UTF-8");
	    for (String string : lines) {
		System.out.println(string);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    /**
     * @description 复制一个文件夹的内容到另一个文件夹	
     *2015-5-29  下午3:56:46
     *返回类型:void
     */
    public static void copyDir(){
	try {
	    FileUtils.copyDirectory(new File("f://from"), new File("f://to"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    /**
     * @description 复制一个文件夹的内容到另一个文件夹,并筛选文件..
     *2015-5-29  下午3:56:46
     *返回类型:void
     */
    public static void copyDirWithFilter(){
	try {
	    FileUtils.copyDirectory(new File("f://from"), new File("f://to"),new FileFilter() {
	        @Override
	        public boolean accept(File file) {
	            if(file.getName().endsWith("txt") || file.isDirectory())
	        	return true;
	    	    return false;
	        }
	    });
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    
    /**
     * @description 复制文件	
     *2015-5-29  下午4:11:29
     *返回类型:void
     */
    public static void copyFile(){
	try {
	    FileUtils.copyFile(new File("f://1.png"), new File("f://2.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    /**
     * @description 删除文件夹	
     *2015-5-29  下午4:44:51
     *返回类型:void
     */
    public static void deleteFolder(){
	try {
	    FileUtils.deleteDirectory(new File("f://from"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    
    /**
     * @description 过滤器	
     *2015-5-29  下午5:12:28
     *返回类型:void
     */
    public static void filter(){
	File floder = FileUtils.getFile("f://test");
	//使用名字过滤器
	String[] acceptedNames = {"1.png", "data.txt"};
	for (String file: floder.list(new NameFileFilter(acceptedNames, IOCase.INSENSITIVE))) {
	    System.out.println("File found, named: " + file);
	}
	//使用匹配符过滤器
	for (String file: floder.list(new WildcardFileFilter("*ata*"))) {
            System.out.println("Wildcard file found, named: " + file);
        }
	//使用前缀过滤器
	for (String file: floder.list(new PrefixFileFilter("example"))) {
            System.out.println("Prefix file found, named: " + file);
        }
	//使用后缀过滤器
        for (String file: floder.list(new SuffixFileFilter(".txt"))) {
            System.out.println("Suffix file found, named: " + file);
        }
        //使用或匹配器来链接多个匹配符
        for (String file: floder.list(new OrFileFilter(new WildcardFileFilter("*ample*"), new SuffixFileFilter(".txt")))) {
            System.out.println("Or file found, named: " + file);
        }
    }
    
    
    
    
    
}
