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
		//设置请求编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        /* 设置响应头允许ajax跨域访问 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        System.out.println("可以访问2");
        String flag=request.getParameter("flag");
        if("addc".equals(flag)) {
        	String userid=request.getParameter("userid");
            String course=request.getParameter("slesson");
            String sclass=request.getParameter("sclass");
            Course course2=new Course(userid, course, sclass, new Date());
            TeacherDAO teacherDAO=new TeacherDAO();
            try {
    			int a=teacherDAO.AddCourse(course2);
    			if(a!=0) {
    				System.out.println("添加课程成功");
    			}
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
					int a=stu.addCourse(sclass);
					if(a!=0) {
						System.out.println("学生添加课程成功");
					}
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
        	System.out.println("id:"+id+"key:"+savekey+"纬度："+latitude+"经度："+longitude);
        	CheckKey key=new CheckKey(id, savekey, 1, new Date(),latitude,longitude);
        	TeacherDAO teacher=new TeacherDAO();
        	try {
				int a=teacher.saveSign(key);
				if(a!=0) {
					System.out.println("存储老师签到成功");
				}
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
        	//System.out.println("存储学生："+"纬度："+slatitude+"经度："+slongitude);
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
				int a=teacherDAO.stopSign(courseid);
			//	System.out.println("更新条数："+a);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        if("delete".equals(flag)) {
        	String idString=request.getParameter("id");
        	int courseid=Integer.parseInt(idString);
        	TeacherDAO teacherDAO =new TeacherDAO();
        	try {
				int a=teacherDAO.DeleteCourse(courseid);
			//	System.out.println("删除条数："+a);
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
				int a=studentDAO.suggestBack(feedBack);
				if(a!=0) {
					System.out.println("插入成功");
				}
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
