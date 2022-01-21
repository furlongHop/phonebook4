package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository // 저장소:@Autowired와 연결, 스프링이 데이터를 가져올 때 사용
public class PhoneDao {

	// 필드
	@Autowired
	private SqlSession sqlSession;

	// 전체 리스트 가져오기
	public List<PersonVo> getPersonList() {
		System.out.println("PhoneDao.getList()");

		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList");// ("namespace.id")
		System.out.println(personList);

		return personList;
	}
	
	

	// 전화번호 추가
	public int personInsert(PersonVo personVo) {
		System.out.println("PhoneDao.personInsert()");

		// insert 메소드는 값을 무조건 1개만 받을 수 있기 때문에 값이 여러 개면 묶어야 한다.
		int count = sqlSession.insert("phonebook.insert", personVo);

		System.out.println(count + "건 저장되었습니다.");
		return count;
	}

	public int personInsert2(String name, String hp, String company) {
		System.out.println("PhoneDao.personInsert2: 파라미터 여러 개를 받을 때");

		// Map 쓰는 상황: PersonVo를 생성해야 하는데 이곳에만 쓰일 것 같아 필드로 올리기도 애매할 때,Vo 사용 없이 해결

		// Map 쓰는 법(02.java>04.자바 컬렉션 프레임 워크): Map<key(자료형),value(자료형)>
		Map<String, String> personMap = new HashMap<String, String>();
		personMap.put("name", name);//(key,value)→insert: #{key}
		personMap.put("hp", hp);
		personMap.put("company", company);

		sqlSession.insert("phonebook.insert2", personMap);

		return 0;
	}
	

	
	
	public int personDelete(int personId) {
		System.out.println("PhoneDao.personDelete()");

		int count = sqlSession.delete("phonebook.delete", personId);

		System.out.println(count + "건을 삭제했습니다.");

		return count;
	}
	
	

	// 전화번호 하나 가져오기
	public PersonVo getPerson(int personId) {
		System.out.println("PhoneDao.getPerson()");

		return sqlSession.selectOne("phonebook.selectOne", personId);

	}

	// 전화번호 1명정보
	public PersonVo getPerson2(int personId) {
		System.out.println("PhoneDao.getPerson2()");

		//<String, Object>로 둔 이유: value 자료형을 String으로 두면 person_id(자료형:int)의 경우 오류가 난다.
		//value 값들의 자료형들이 다양할 경우 Object로 표기한다.
		Map<String, Object> personMap = sqlSession.selectOne("phonebook.selectPerson", personId);
		System.out.println(personMap.keySet());//순서 무작위로 저장된 값 가져오기(키 세트)

		//Map으로 리턴될 경우 key값은 컬럼명(대문자)으로 저장(put)되어 있다. Map으로 저장된 value값을 호출하고 싶을 땐 get 사용.
		System.out.println(personMap.get("PERSON_ID"));
		System.out.println(personMap.get("NAME"));
		System.out.println(personMap.get("HP"));
		System.out.println(personMap.get("COMPANY"));

		return null;
	}
	
	

	// 전화번호 업데이트
	public int personUpdate(PersonVo personVo) {
		System.out.println("PhoneDao.pesonUpdate()");

		int count = sqlSession.update("phonebook.update", personVo);

		System.out.println(count + "건 수정되었습니다.");

		return count;
	}

}
