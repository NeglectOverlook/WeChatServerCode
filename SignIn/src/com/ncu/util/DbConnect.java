package com.ncu.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnect {
	private static String driverName;
	private static String url;
	private static String userName;
	private static String password;	
    static{
        try {
           //用流读入properties配置文件
            InputStream inputStream = DbConnect.class.getClassLoader().getResourceAsStream("/mysql.properties");
            Properties properties = new Properties();
          // 从输入字节流读取属性列表（键和元素对）
            properties.load(inputStream);
            //用此属性列表中指定的键搜索属性，获取驱动，url，username，password
            driverName = properties.getProperty("DRIVER");
            url = properties.getProperty("URL");
            userName = properties.getProperty("USERNAME");
            password = properties.getProperty("PASSWORD");
            Class.forName(driverName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
//    获取数据库连接
    public static Connection getDBconnection() {
        Connection conn=null;
        try {
            conn=DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
  
    public static void closeDB(Connection con,PreparedStatement pstm, ResultSet rs)
    {
    	try{
  		   if(rs!=null) 
  			   rs.close(); 
  		   if(pstm!=null) 
  			   pstm.close();
 		   if(con!=null) 
 			   con.close();				 
 	   }catch (SQLException e) {				
 			e.printStackTrace();			
 	   }
		
    }

}
