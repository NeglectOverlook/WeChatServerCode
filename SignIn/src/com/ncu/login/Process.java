package com.ncu.login;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ncu.dao.StudentDAO;
import com.ncu.dao.TeacherDAO;
import com.ncu.entity.AddCourse;
import com.ncu.entity.CheckKey;
import com.ncu.entity.Course;
import com.ncu.entity.FeedBack;
import com.ncu.entity.History;
import com.ncu.entity.Sign;
import com.ncu.entity.SignList;

/**
 * Servlet implementation class Process
 */
@WebServlet("/Process")
public class Process extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Process() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
     
        response.setHeader("Access-Control-Allow-Origin", "*");
      
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
       
        String flag=request.getParameter("flag");
        if("addc".equals(flag)) {
        	String userid=request.getParameter("userid");
            String course=request.getParameter("slesson");
            String sclass=request.getParameter("sclass");
            Course course2=new Course(userid, course, sclass, new Date());
            TeacherDAO teacherDAO=new TeacherDAO();
            try {
    			teacherDAO.AddCourse(course2);
    		
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }
        if("allc".equals(flag)) {
        	List<Course> list=new ArrayList<>();
        	StudentDAO transfer=new StudentDAO();
        	try {
				list=transfer.getAllCourse();
				for(Course c:list) {
					System.out.println(c.getBuildTime());
				}
				String json=new Gson().toJson(list);
				 Writer out = response.getWriter();
		         out.write(json);
		         out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        if("myc".equals(flag)) {
        	String userid=request.getParameter("userid");
        	List<Course> list=new ArrayList<>();
        	StudentDAO transfer=new StudentDAO();
        	try {
				list=transfer.getmyCourse(userid);
				for(Course c:list) {
					System.out.println(c.getBuildTime());
				}
				String json=new Gson().toJson(list);
				 Writer out = response.getWriter();
		         out.write(json);
		         out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        if("teacherc".equals(flag)) {
        	String userid=request.getParameter("userid");
        	List<Course> list=new ArrayList<>();
        	TeacherDAO transfer=new TeacherDAO();
        	try {
				list=transfer.getmyCourse(userid);
				for(Course c:list) {
					System.out.println(c.getBuildTime());
				}
				String json=new Gson().toJson(list);
				 Writer out = response.getWriter();
		         out.write(json);
		         out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        if("saddc".equals(flag)) {
        	String userid=request.getParameter("userid");
            String tcourseid=request.getParameter("lessonid");
            int courseid=Integer.parseInt(tcourseid);
            AddCourse sclass=new AddCourse(userid, courseid, new Date());
            StudentDAO stu=new StudentDAO();
            try {
            	 boolean res=stu.addCourseCheck(sclass);
            	 if(res) {
            		 String back="yes";
            		 String json = new Gson().toJson(back);
                     Writer out = response.getWriter();
                     out.write(json);
                     out.flush();
            	 }
            	 else {
					stu.addCourse(sclass);
				
				}
    			
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
            
        }
        if("judge".equals(flag)) {
        	String courseid=request.getParameter("id");
        	int id=Integer.parseInt(courseid);
        	TeacherDAO teacher=new TeacherDAO();
        	try {
				int judge=teacher.judgeSign(id);
				String json = new Gson().toJson(judge);
                Writer out = response.getWriter();
                out.write(json);
                out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        if("saveSign".equals(flag)) {
        	String courseid=request.getParameter("id");
        	int id=Integer.parseInt(courseid);
        	String savekey=request.getParameter("signKey");
        	String latitude=request.getParameter("weidu");
        	String longitude=request.getParameter("jingdu");
        
        	CheckKey key=new CheckKey(id, savekey, 1, new Date(),latitude,longitude);
        	TeacherDAO teacher=new TeacherDAO();
        	try {
				teacher.saveSign(key);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        if("sign".equals(flag)) {
        	String id=request.getParameter("id");
        	int courseid=Integer.parseInt(id);
        	String key=request.getParameter("key");
        	String userid=request.getParameter("userid");
        	String tlatitude=request.getParameter("weidu");
        	String tlongitude=request.getParameter("jingdu");
        	double slatitude=Double.parseDouble(tlatitude);
        	double slongitude=Double.parseDouble(tlongitude);
  
        	Sign sign=new Sign(courseid, userid, new Date());
        	StudentDAO studentDAO=new StudentDAO();
        	try {
				String deal=studentDAO.checkSign(sign, key,slatitude,slongitude);
				String json = new Gson().toJson(deal);
                Writer out = response.getWriter();
                out.write(json);
                out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        if("sslist".equals(flag)) {
        	String id=request.getParameter("lessonid");
        	int courseid=Integer.parseInt(id);
        	StudentDAO studentDAO=new StudentDAO();
        	try {
				List<SignList> array=studentDAO.showList(courseid);
				String json = new Gson().toJson(array);
                Writer out = response.getWriter();
                out.write(json);
                out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        if("stops".equals(flag)) {
        	String id=request.getParameter("id");
        	int courseid=Integer.parseInt(id);
        	TeacherDAO teacherDAO =new TeacherDAO();
        	try {
			teacherDAO.stopSign(courseid);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        if("delete".equals(flag)) {
        	String idString=request.getParameter("id");
        	int courseid=Integer.parseInt(idString);
        	TeacherDAO teacherDAO =new TeacherDAO();
        	try {
				teacherDAO.DeleteCourse(courseid);
		
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        if("history".equals(flag)) {
        	String idString=request.getParameter("lessonid");
        	int courseid=Integer.parseInt(idString);
        	TeacherDAO teacherDAO =new TeacherDAO();
        	try {
				List<History> lists=teacherDAO.HistoryRd(courseid);
				String json = new Gson().toJson(lists);
                Writer out = response.getWriter();
                out.write(json);
                out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        if("hsign".equals(flag)) {
        	String idString=request.getParameter("checkid");
        	int checkid=Integer.parseInt(idString);
        	TeacherDAO teacherDAO =new TeacherDAO();
        	try {
				List<SignList> lists=teacherDAO.showList(checkid);
				String json = new Gson().toJson(lists);
                Writer out = response.getWriter();
                out.write(json);
                out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        if("fback".equals(flag)) {
        	String openid=request.getParameter("openID");
        	String suggestCon=request.getParameter("scontent");
        	FeedBack feedBack=new FeedBack(openid, suggestCon, new Date());
        	StudentDAO studentDAO=new StudentDAO();
        	try {
				studentDAO.suggestBack(feedBack);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
