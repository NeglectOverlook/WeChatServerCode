package com.ncu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.ncu.entity.CheckKey;
import com.ncu.entity.Course;
import com.ncu.entity.History;
import com.ncu.entity.SignList;
import com.ncu.entity.Teacher;
import com.ncu.util.DbConnect;

public class TeacherDAO implements ITeacherDAO {
	protected static final String  FIELDS_INSERT1 ="openID,teacherID,teacherName,joinTime";
	protected static final String  FIELDS_INSERT2 ="openID,courseName,ofClass,buildTime";
	protected static String INSERT_SQL1="insert into teacher ("+FIELDS_INSERT1+")"+"values (?,?,?,?)";
	protected static String INSERT_SQL2="insert into course ("+FIELDS_INSERT2+")"+"values (?,?,?,?)";
	protected static String FINDCHECK_SQL="select * from teacher where openID=?";

	@Override
	public int create(Teacher tea) throws Exception { 
		Connection con=null;
	      PreparedStatement prepStmt=null;
	      ResultSet rs=null;
	      int addNum=0;
	      SimpleDateFormat format=new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      String nowtime=format.format(tea.getJoinTime());
	      try{
	    	  con=DbConnect.getDBconnection();
	    	  prepStmt =con.prepareStatement(INSERT_SQL1);	    	  
	    	  prepStmt.setString(1,tea.getOpenID());
	    	  prepStmt.setString(2,tea.getTeacherID());
	    	  prepStmt.setString(3,tea.getTeacherName());
	    	  prepStmt.setString(4, nowtime);
	          addNum=prepStmt.executeUpdate();
	          System.out.println(addNum);
	      } catch(Exception e){
	   
	       e.printStackTrace();
	      } finally{
	    	  
	    	  DbConnect.closeDB(con, prepStmt, rs);
	      }
		return addNum;
	}

	@Override
	public boolean findCheck(String userid) throws Exception {
		Connection con=null;
	      PreparedStatement prepStmt=null;
	      ResultSet rs=null;
	      boolean res=true;
	      try{
	    	  con=DbConnect.getDBconnection();
	    	  prepStmt =con.prepareStatement(FINDCHECK_SQL);	    	  
	    	  prepStmt.setString(1,userid);
	          rs=prepStmt.executeQuery();
	          if(!rs.next()) {
	        	  res=false;
	          }
	      } catch(Exception e){
	       e.printStackTrace();
	      } finally{
	    	 
	    	  DbConnect.closeDB(con, prepStmt, rs);
	      }
		return res;
	}

	@Override
	public int AddCourse(Course course) throws Exception {
		Connection con=null;
	      PreparedStatement prepStmt=null;
	      ResultSet rs=null;
	      int addNum=0;
	      SimpleDateFormat format=new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      String nowtime=format.format(course.getBuildTime());
	      try{
	    	  con=DbConnect.getDBconnection();
	    	  prepStmt =con.prepareStatement(INSERT_SQL2);	    	  
	    	  prepStmt.setString(1,course.getOpenID());
	    	  prepStmt.setString(2,course.getCourseName());
	    	  prepStmt.setString(3,course.getOfClass());
	    	  prepStmt.setString(4, nowtime);
	          addNum=prepStmt.executeUpdate();
	          System.out.println(addNum);
	      } catch(Exception e){
	  
	       e.printStackTrace();
	      } finally{
	    
	    	  DbConnect.closeDB(con, prepStmt, rs);
	      }
		return addNum;
	}

	@Override
	public List<Course> getmyCourse(String userid) throws Exception { 
		String sql="select courseID,openID,courseName,ofClass,buildTime from course where openID=?";
		Connection con=null;
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		List<Course> all=new ArrayList<>();
		try {
			con=DbConnect.getDBconnection();
			prepStmt=con.prepareStatement(sql);
			prepStmt.setString(1, userid);
			rs=prepStmt.executeQuery();
			while(rs.next())
			{
				Course course=new Course();
				course.setCourseID(rs.getInt(1));
				course.setOpenID(rs.getString(2));
				course.setCourseName(rs.getString(3));
				course.setOfClass(rs.getString(4));
				course.setBuildTime(rs.getTimestamp(5));
				all.add(course);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {			
			DbConnect.closeDB(con, prepStmt, rs);
		
		}		
		return all;
	}

	@Override
	public int judgeSign(int id) throws Exception {
		String sql="select state from mycheck where courseID=? order by checkTime desc limit 1";
		Connection con=null;
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		int judge=-1;
		try {
			con=DbConnect.getDBconnection();
			prepStmt=con.prepareStatement(sql);
			prepStmt.setInt(1, id);
			rs=prepStmt.executeQuery();
			if(rs.next())
			{
				judge=rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {			
			DbConnect.closeDB(con, prepStmt, rs);
			
		}
		return judge;
	}

	@Override
	public int saveSign(CheckKey check) throws Exception {
		String sql="insert into mycheck (courseID,checkKey,state,checkTime,cLatitude,cLongitude) values (?,?,?,?,?,?)";
		  Connection con=null;
	      PreparedStatement prepStmt=null;
	      ResultSet rs=null;
	      int addNum=0;
	      SimpleDateFormat format=new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      String nowtime=format.format(check.getCheckTime());
	      try{
	    	  con=DbConnect.getDBconnection();
	    	  prepStmt =con.prepareStatement(sql);	    	  
	    	  prepStmt.setInt(1,check.getCourseID());
	    	  prepStmt.setString(2, check.getCheckKey());
	    	  prepStmt.setInt(3, check.getState());
	    	  prepStmt.setString(4, nowtime);
	    	  prepStmt.setString(5, check.getLatitude());
	    	  prepStmt.setString(6, check.getLongitude());
	          addNum=prepStmt.executeUpdate();
	   
	      } catch(Exception e){
	    	  
	       e.printStackTrace();
	      } finally{
	    	  
	    	  DbConnect.closeDB(con, prepStmt, rs);
	      }
		return addNum;
	}

	@Override
	public int stopSign(int courseid) throws Exception {
		String sql="update mycheck set state=0 where courseID=?";
		  Connection con=null;
	      PreparedStatement prepStmt=null;
	      ResultSet rs=null;
	      int addNum=0;
	      try{
	    	  con=DbConnect.getDBconnection();
	    	  prepStmt =con.prepareStatement(sql);	    	  
	    	  prepStmt.setInt(1,courseid);
	          addNum=prepStmt.executeUpdate();
	        
	      } catch(Exception e){
	    	  
	       e.printStackTrace();
	      } finally{
	    	 
	    	  DbConnect.closeDB(con, prepStmt, rs);
	      }
		return addNum;
	}

	@Override
	public int DeleteCourse(int courseid) throws Exception {
		String sql1="delete from course where courseID=?";
		String sql2="delete from sign where courseID=?";
		String sql3="delete from mycheck where courseID=?";
		String sql4="delete from addcourse where courseID=?";
		Connection con=null;
	      PreparedStatement prepStmt=null;
	      ResultSet rs=null;
	      int addNum=0;
	      try{
	    	  con=DbConnect.getDBconnection();
	    	  prepStmt =con.prepareStatement(sql1);	    	  
	    	  prepStmt.setInt(1,courseid);
	          addNum=prepStmt.executeUpdate();
	     
	          prepStmt =con.prepareStatement(sql2);	    	  
	    	  prepStmt.setInt(1,courseid);
	          addNum=prepStmt.executeUpdate();
	          prepStmt =con.prepareStatement(sql3);	    	  
	    	  prepStmt.setInt(1,courseid);
	          addNum=prepStmt.executeUpdate();
	          prepStmt =con.prepareStatement(sql4);	    	  
	    	  prepStmt.setInt(1,courseid);
	          addNum=prepStmt.executeUpdate();
	      } catch(Exception e){
	    	
	       e.printStackTrace();
	      } finally{
	    	 
	    	  DbConnect.closeDB(con, prepStmt, rs);
	      }
		return addNum;
	}

	@Override
	public List<History> HistoryRd(int courseid) throws Exception {
		String sql="select checkID,courseID,checkTime from mycheck where courseID=? order by checkTime asc";
		Connection con=null;
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		List<History> all=new ArrayList<>();
		SimpleDateFormat format=new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      
		try {
			con=DbConnect.getDBconnection();
			prepStmt=con.prepareStatement(sql);
			prepStmt.setInt(1, courseid);
			rs=prepStmt.executeQuery();
			while(rs.next())
			{
				History list=new History();
				list.setCheckID(rs.getInt(1));
				list.setCourseID(rs.getInt(2));
				String nowtime=format.format(rs.getTimestamp(3));
				list.setCheckTime(nowtime);
				all.add(list);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {			
			DbConnect.closeDB(con, prepStmt, rs);
			
		}
		return all;
	}

	@Override
	public List<SignList> showList(int checkid) throws Exception {
		String sql="select studentName,studentNum,signTime from sign a,student b where a.openID=b.openID and a.checkID=?";
		Connection con=null;
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		 List<SignList> all=new ArrayList<SignList>();
		SimpleDateFormat format=new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			con=DbConnect.getDBconnection();
			prepStmt=con.prepareStatement(sql);
			prepStmt.setInt(1, checkid);
			rs=prepStmt.executeQuery();
			while(rs.next())
			{
				SignList list=new SignList();
	        	 list.setStudentName(rs.getString(1));
	        	 list.setStudentNum(rs.getString(2));
	        	 String nowtime=format.format(rs.getTimestamp(3));
	        	 list.setSignTime(nowtime);
				all.add(list);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {			
			DbConnect.closeDB(con, prepStmt, rs);
			
		}
		return all;
	}

}





