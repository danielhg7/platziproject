package com.platzi.profesoresplatzi.dao;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.platzi.profesoresplatzi.model.Teacher;
import com.platzi.profesoresplatzi.model.TeacherSocialMedia;

@Repository
@Transactional
public class TeacherDaoImpl extends AbstractSession implements TeacherDao {
	
	@Override
	public void saveTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		getSession().persist(teacher);
	}
	
	@Override
	public void deleteTeacherById(Long idTeacher) {
		// TODO Auto-generated method stub
		Teacher teacher = findTeacherById(idTeacher);
		if(teacher != null){
			
			Iterator<TeacherSocialMedia> i = teacher.getTeacherSocialMedias().iterator();
			while(i.hasNext()){
				TeacherSocialMedia teacherSocialMedia = i.next();
				i.remove();
				getSession().delete(teacherSocialMedia);
			}
			teacher.getTeacherSocialMedias().clear();
			getSession().delete(teacher);
		}
	}
	
	@Override
	public void updateTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		getSession().update(teacher);
	}
	
	@Override
	public List<Teacher> findAllTeachers() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Teacher").list();
	}
	
	@Override
	public Teacher findTeacherById(Long idTeacher) {
		// TODO Auto-generated method stub
		return (Teacher) getSession().get(Teacher.class, idTeacher);
	}
	
	@Override
	public Teacher findTeacherByName(String name) {
		// TODO Auto-generated method stub
		return (Teacher) getSession().createQuery(
				"from Teacher where name = :name")
				.setParameter("name", name).uniqueResult();
	}

}
