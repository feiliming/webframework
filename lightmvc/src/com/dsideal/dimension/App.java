package com.dsideal.dimension;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, WriterException, SQLException, ClassNotFoundException
    {

    	m0();
    	m1();
    }
    
    public static void m0() throws WriterException, IOException{
    	String text = "产品信息{产品名称：果冻；产品类别：1423；常用名：果冻；执行标准ID：741258；执行标准名称：建筑用卵石、碎石；企业名称：哇哈哈；企业代码：123456；信用等级：AAA"
    			+ "产品名称：果冻；产品类别：1423；常用名：果冻；执行标准ID：741258；执行标准名称：建筑用卵石、碎石；企业名称：哇哈哈；企业代码：123456；信用等级：AAA"
    			+ "产品名称：果冻；产品类别：1423；常用名：果冻；执行标准ID：741258；执行标准名称：建筑用卵石、碎石；企业名称：哇哈哈；企业代码：123456；信用等级：AAA"
    			+ "产品名称：果冻；产品类别：1423；常用名：果冻；执行标准ID：741258；执行标准名称：建筑用卵石、碎石；企业名称：哇哈哈；企业代码：123456；信用等级：AAA"
    			+ "产品名称：果冻；产品类别：1423；常用名：果冻；执行标准ID：741258；执行标准名称：建筑用卵石、碎石；企业名称：哇哈哈；企业代码：123456；信用等级：AAA}";  
    	int width = 100;  
    	int height = 100;  
    	String format = "png";
    	Map<EncodeHintType, String> hints= new HashMap<EncodeHintType, String>();  
    	hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
    	BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height,hints);  
    	
    	//File outputFile = new File("new.png");  
    	Path path = FileSystems.getDefault().getPath("2dimension", "test.png");
    	//MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);  
    	MatrixToImageWriter.writeToPath(bitMatrix, format, path);
    }
    
    public static void m1() throws IOException, WriterException, SQLException, ClassNotFoundException{
    	//String text = "产品信息{产品名称：果冻；产品类别：1423；常用名：果冻；执行标准ID：741258；执行标准名称：建筑用卵石、碎石；企业名称：哇哈哈；企业代码：123456；信用等级：AAA}";  
    	int width = 100;  
    	int height = 100;  
    	String format = "png"; 
    	
    	Map<EncodeHintType, String> hints= new HashMap<EncodeHintType, String>();  
    	hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  

    	String url = "jdbc:mysql://localhost:3306/lightmvc?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
    	Class.forName("com.mysql.jdbc.Driver");
    	Connection conn = DriverManager.getConnection(url, "root", "root");
    	Statement st = conn.createStatement();
    	String sql = "SELECT p.Pid,p.Product_name,p.Code_id,e.Code_cn,e.creditlevel,p.Product_class,p.Product_commonname,p.Standard_id,p.Standard_name " + 
    			"FROM db_productinfo p LEFT JOIN db_enterpriseinfo e ON p.Code_id = e.Code_id";
    	ResultSet rs = st.executeQuery(sql);
    	while(rs.next()){
    		String text = "产品信息{产品名称："+rs.getString("product_name")+
    				"；产品类别："+rs.getString("product_class")+
    				"；常用名："+rs.getString("product_commonname")+
    				"；执行标准ID："+rs.getString("standard_id")+
    				"；执行标准名称："+rs.getString("standard_name")+
    				"；企业名称："+rs.getString("code_cn")+
    				"；企业代码："+rs.getString("code_id")+
    				"；信用等级："+rs.getString("creditlevel")+"}";  
    	   	BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height,hints);  
        	
        	//File outputFile = new File("new.png");  
        	Path path = FileSystems.getDefault().getPath("2d/product/", rs.getInt("pid")+".png");
        	//MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);  
        	MatrixToImageWriter.writeToPath(bitMatrix, format, path);
    	}
    	conn.close();
    }
}
