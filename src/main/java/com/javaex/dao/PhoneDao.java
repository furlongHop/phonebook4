package com.javaex.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository //저장소:@Autowired와 연결, 스프링이 데이터를 가져올 때 사용
public class PhoneDao {
	
	
	//필드	
	@Autowired
	private SqlSession sqlSession;
	
	
	//전체 리스트 가져오기
	public List<PersonVo> getPersonList() {
		System.out.println("PhoneDao.getList()");
		
		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList");//("namespace.id")
		System.out.println(personList);
		
		return personList;
	}

	//전화번호 추가
	public int personInsert(PersonVo personVo) {
		System.out.println("PhoneDao.personInsert()");
		
		//insert 메소드는 값을 무조건 1개만 받을 수 있기 때문에 값이 여러 개면 묶어야 한다.
		int count = sqlSession.insert("phonebook.insert", personVo);
		
		System.out.println(count + "건 저장되었습니다.");
		return count;
	}

	public int personDelete(int personId) {
		System.out.println("PhoneDao.personDelete()");
		
		int count = sqlSession.delete("phonebook.delete", personId);
		
		System.out.println(count + "건을 삭제했습니다.");
		
		return count;
	}
	
	//전화번호 하나 가져오기
	public PersonVo getPerson(int personId) {
		System.out.println("PhoneDao.getPerson()");
	
		return sqlSession.selectOne("phonebook.selectOne", personId);
	
	}
	
	//전화번호 업데이트
	public int personUpdate(PersonVo personVo) {
		System.out.println("PhoneDao.pesonUpdate()");
		
		int count = sqlSession.update("phonebook.update", personVo);
		
		System.out.println(count + "건 수정되었습니다.");
		
		return count;
	}
	
}
