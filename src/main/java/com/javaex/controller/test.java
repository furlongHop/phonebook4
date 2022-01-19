package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/board")//해당 컨트롤러 공통 주소
public class test {

	//필드
	
	//생성자
	
	//메소드 g/s
	
	//메소드 일반
	
	//주소창에 입력: get 방식, 폼에서 method룰 post 지정: post 방식
	//method=RequestMethod.GET/POST → method 방식 지정>지정한 방식의 폼만 수령
	@RequestMapping(value="/list",method={RequestMethod.GET, RequestMethod.POST})//두 가지 방식 모두 수령(괄호 주의)
	//@RequestMapping("/list"):value는 표기하지 않아도 된다. get, post 방식 둘 다 사용(method 표기 안 할 시)
	public String TestPrint() {
		System.out.println("TestPrint");
		
		return "/WEB-INF/views/Test.jsp"; //포워드 개념
	}
	
	@RequestMapping(value="/writeForm",method={RequestMethod.GET, RequestMethod.POST})
	public String TestPrint2() {
		System.out.println("TestPrint2");
		
		return "/WEB-INF/views/Test.jsp";
	}
	
	@RequestMapping(value="/write",method={RequestMethod.GET, RequestMethod.POST})
	public String TestPrint3() {
		System.out.println("TestPrint3");
		
		return "/WEB-INF/views/Test.jsp";
	}
	
}
