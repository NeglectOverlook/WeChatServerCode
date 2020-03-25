package com.ncu.dao;

import java.util.List;

import com.ncu.entity.AddCourse;
import com.ncu.entity.Course;
import com.ncu.entity.FeedBack;
import com.ncu.entity.Sign;
import com.ncu.entity.SignList;
import com.ncu.entity.Student;

public interface IStudentDAO {
	public abstract int create(Student stu) throws Exception;  //注册用户
	public abstract boolean findCheck(String userid) throws Exception; //判断是否已经注册
	public abstract List<Course> getAllCourse() throws Exception; //全部课程
	public abstract List<Course> getmyCourse(String userid) throws Exception;  //我的课程
	public abstract int addCourse(AddCourse course) throws Exception;  //添加课程至我的课程
	public abstract boolean addCourseCheck(AddCourse course) throws Exception;  //检查课程是否已经添加
	public abstract String checkSign(Sign sign,String key,double wei,double jing) throws Exception;  //检查学生的签到
	public abstract int saveSign(Sign sign) throws Exception;  //保存学生的签到
	public abstract List<SignList> showList(int courseid) throws Exception;//展示签到列表
	public abstract boolean ifSign(int checkid,String userid) throws Exception;//检查该学生是否已经签过到了
	public abstract boolean ifNear(double long1, double lat1, double long2, double lat2) throws Exception;//判断是否在教室
	public abstract int suggestBack(FeedBack feedBack) throws Exception;//存储意见反馈
	public abstract List<String> myInfo(String userid)throws Exception;//返回我的消息
}
