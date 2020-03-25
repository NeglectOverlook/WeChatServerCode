package com.ncu.login;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ncu.dao.StudentDAO;
import com.ncu.dao.TeacherDAO;
import com.ncu.entity.Student;
import com.ncu.entity.Teacher;
import com.ncu.util.CommonUtil;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String APPID="xxxxxxxxxx";
	private static final String SECRET="xxxxxxxxxxxxxxxxxxxxxxxxxx";

    /**
     * Default constructor. 
     */
    public Login() {
        // TODO Auto-generated constructor stub
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
        String flag=request.getParameter("flag");
     //   System.out.println(flag);
        if("login".equals(flag)) {
        	String code=request.getParameter("js_code");
        	String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+
   				 "&secret="+SECRET+"&js_code="+ code +"&grant_type=authorization_code";
        	JSONObject sjson=CommonUtil.httpsRequest(url, "GET", null);
        	/*String openid="";
        	String session_key="";
        	if (sjson != null) {
				try {
					 openid = sjson.getString("openid");
					 session_key=sjson.getString("session_key");
				} catch (Exception e) {
					System.out.println("业务操作失败");
					e.printStackTrace();
				}
			} else {
				System.out.println("code无效");
			}
        	System.out.println(session_key+"  "+openid);*/
        	
        	/*Map<String, Object> result = new HashMap<String, Object>();
            result.put("res", "test");
            result.put("msg", "后台已收到");*/
         //   String json = new Gson().toJson(sjson);
           // System.out.println(json);
        	Writer out=response.getWriter();
        	out.write(sjson.toString());
        	out.flush();
        }
        if("init".equals(flag)) {
        	StudentDAO studentDAO=new StudentDAO();
        	String userid=request.getParameter("userid");
            boolean res=true;
            try {
    			res=studentDAO.findCheck(userid);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("res", res);
            result.put("msg", "后台已收到");
            String json = new Gson().toJson(result);
            //返回值给微信小程序
            Writer out = response.getWriter();
            out.write(json);
            out.flush();
        }
        if("student".equals(flag)) {
        	StudentDAO studentDAO=new StudentDAO();
        	String userid=request.getParameter("userid");
        	String studentName=request.getParameter("sname");
        	String studentNum=request.getParameter("snum");
        	Student student=new Student(userid, studentName, studentNum,new Date());
        	try {
				int a=studentDAO.create(student);
				if(a!=0) {
					System.out.println("插入成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        if("teacher".equals(flag)) {
        	TeacherDAO teacherDAO=new TeacherDAO();
        	String userid=request.getParameter("userid");
        	String teacherName=request.getParameter("tname");
        	String teacherID=request.getParameter("tnum");
        	Teacher tea=new Teacher(userid, teacherID, teacherName,new Date());
        	try {
				int a=teacherDAO.create(tea);
				if(a!=0) {
					System.out.println("插入成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        if("guide".equals(flag)) {
        	StudentDAO studentDAO=new StudentDAO();
        	String userid=request.getParameter("userid");
        	System.out.println(userid);
            boolean res=true;
            String state="";
            try {
    			res=studentDAO.findCheck(userid);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
            if(res) {
            	state="student";
            }
            else{
				TeacherDAO teacherDAO=new TeacherDAO();
				try {
					res=teacherDAO.findCheck(userid);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(res) {
		            	state="teacher";
		         }
				else {
					state="none";
				}
			}
            String json = new Gson().toJson(state);
            //返回值给微信小程序
            Writer out = response.getWriter();
            out.write(json);
            out.flush();
        }
        if("myInfo".equals(flag)) {
        	String userid=request.getParameter("userid");
        	StudentDAO studentDAO=new StudentDAO();
        	try {
				List<String> list=studentDAO.myInfo(userid);
				Map<String, String> result = new HashMap<String, String>();
	            result.put("backName",list.get(0));
	            result.put("backNum", list.get(1));
	            String json = new Gson().toJson(result);
	            //返回值给微信小程序
	            Writer out = response.getWriter();
	            out.write(json);
	            out.flush();
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
