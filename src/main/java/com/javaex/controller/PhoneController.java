package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@RequestMapping(value="/phone")
@Controller //spring-servlet에 명령한 controller 수색 힌트(이 표기가 있어야 찾을 수 있다)
public class PhoneController {
	
	//필드
	@Autowired //해당 데이터가 필요하다고 알려주는 것,Repository와 연결>spring이 메모리 로딩 후 주소 전달
	private PhoneDao phoneDao;
	//private PhoneDao phoneDao = new PhoneDao(); > controller가 메모리 로딩한 것(관계가 전혀 달라진다)
	
	//생성자
	
	//메소드 g/s
	
	//메소드 일반
	
	@RequestMapping(value="/writeForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String writeForm() {
		System.out.println("PhoneController>writeForm");
		
		
		return "writeForm";
	}
	
	
	@RequestMapping(value="/write",method= {RequestMethod.GET,RequestMethod.POST})
	//model의 attribute에 있는 값을 PersonVo 형태에 맞춰 setter로(생성자와 달리 순서 상관 없음) 값을 넣어 참조 변수 personVo에 삽입 
	//attribute에 있는 값의 이름이 필드 이름과 동일해야 제대로 값을 인식해 입력할 수 있다.(form의 name 값)
	//값을 이름대로 묶어 vo에 담을 경우 setter는 default 생성자를 사용하기 때문에 vo에 반드시 기본 생성자가 존재해야 한다.
	//(@ModelAttribute PersonVo personVo,@RequestParam("name"))처럼 vo로 묶으면서 동시에 특정 파라미터 값을 빼낼 수 있다.
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController>write");
		System.out.println(personVo);
		
		//저장
		phoneDao.personInsert(personVo);
		
		//redirect
		return "redirect:/phone/list";
	}
	
	@RequestMapping(value="/list",method= {RequestMethod.GET,RequestMethod.POST})
	public String list(Model model) {//Model이라는 그릇
		System.out.println("PhoneController>list");
		
		//dao의 메소드로 list 호출
		List<PersonVo> personList = phoneDao.getPersonList();
		
		//controller>>ds데이터 전송(model)
		model.addAttribute("personList", personList);
		
		System.out.println(personList.toString());
		
		//jsp 정보 retuen>>view
		return "list";
	}
	
	@RequestMapping(value="/delete",method= {RequestMethod.GET,RequestMethod.POST})
	public String delete(@RequestParam("id") int id) {
		System.out.println("PhoneController>delete");
		
		phoneDao.personDelete(id);
		
		System.out.println(id);
		
		return "redirect:/phone/list";
	}
	
	@RequestMapping(value="/updateForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String updateForm(@RequestParam("id") int id, Model model) {
		System.out.println("PhoneController>updateForm");
		
		PersonVo personVo = phoneDao.getPerson(id);//업데이트 폼 value값을 위한 해당 인물 정보 불러오기
		
		model.addAttribute("pvo", personVo);//해당 인물 정보를 어트리뷰트에 저장>폼에서 부르기 위함
		
		return "updateForm";
	}
	
	@RequestMapping(value="/update",method= {RequestMethod.GET,RequestMethod.POST})
	public String update(@RequestParam("personId") int id, @ModelAttribute PersonVo vo) {
		//modelAttribute에 PersonVo 형태로 정보를 저장할 때, 이름에 주의해야 한다. updateForm에서 정보를 가져와
		//vo에 담을 때 이름에 맞춰 데이터를 저장하기 때문에 필드 이름과 폼에 있는 name을 맞춰줘야 한다.
		System.out.println("PhoneController>update");
		
		//personId로 1명 정보 personVo에 저장
		PersonVo personVo = phoneDao.getPerson(id);
		
		System.out.println(id);
		System.out.println(personVo);
		
		//어트리뷰트에 담긴 정보로 db 업데이트
		phoneDao.personUpdate(vo);
		
		return "redirect:/phone/list";
	}
	
	
	@RequestMapping(value="/test",method= {RequestMethod.GET,RequestMethod.POST})
	public String test(@RequestParam("n") String name, 
					   @RequestParam(value="age", required=false, defaultValue="-1") int age) {
		//받을 파라미터 값을 지정했을 경우 해당 파라미터 값을 받지 못하면 오류가 발생한다.
		//따라서 디폴트값을 설정하여 파라미터 값을 받지 못했을 경우 발생할 오류를 방지하는 것이 좋다.
		
		System.out.println(name);
		System.out.println(age);
		
		return "writeForm";
	}
	
	
	//파라미터를 받아 진행하는 방법
	@RequestMapping(value = "/view", method = { RequestMethod.GET, RequestMethod.POST })
	public String view(@RequestParam(value="no") int no) {

		System.out.println(no + "번 글 가져오기");

		return "writeForm";
	}
	
	//주소의 특정 부분{}을 파라미터로 사용하는 방법(여러 개, 위치 지정 가능)>블로그 주소 등에 사용된다.
	@RequestMapping(value="/view/{no}",method= {RequestMethod.GET,RequestMethod.POST})
	public String view2(@PathVariable("no") int no) {//경로 변수
		
		System.out.println(no + "번 글 가져오기");
		
		return "writeForm";
	}
}
