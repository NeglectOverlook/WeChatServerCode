package com.ncu.dao;

import java.util.List;

import com.ncu.entity.CheckKey;
import com.ncu.entity.Course;
import com.ncu.entity.History;
import com.ncu.entity.SignList;
import com.ncu.entity.Teacher;

public interface ITeacherDAO {
	public abstract int create(Teacher tea) throws Exception;
	public abstract boolean findCheck(String userid) throws Exception;
	public abstract int AddCourse(Course course) throws Exception;
	public abstract List<Course> getmyCourse(String userid) throws Exception;
	public abstract int judgeSign(int id) throws Exception;//该函数用于点击课程进行考勤时，判断该课程是否上次考勤结束，如果结束开始新的考勤，如果没有结束进入上次考勤。
	public abstract int saveSign(CheckKey check) throws Exception;//保存老师签到，只有老师可以修改状态
	public abstract int stopSign(int courseid) throws Exception;
	public abstract int DeleteCourse(int courseid) throws Exception;
	public abstract List<History> HistoryRd(int courseid) throws Exception;
	public abstract List<SignList> showList(int checkid) throws Exception;//展示签到列表

}
