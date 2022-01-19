package com.javaex.vo;

public class PersonVo {

	//필드
	private String name;
	private String hp;
	private String company;
	private int personId;
	
	//생성자
	public PersonVo() {
		
	}
	
	public PersonVo(String name, String hp, String company) {
		this.name = name;
		this.hp = hp;
		this.company = company;
	}

	public PersonVo(int personId, String name, String hp, String company) {
		this.name = name;
		this.hp = hp;
		this.company = company;
		this.personId = personId;
	}

	//메소드 - g/s
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
	//메소드 - 일반
	@Override
	public String toString() {
		return "PersonVo [name=" + name + ", hp=" + hp + ", company=" + company + ", personId=" + personId + "]";
	}

	
	
	
}
