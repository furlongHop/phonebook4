<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>[Phonebook4]</h1>

	<h2>전화번호 수정폼</h2>

	<p>전화번호 수정폼입니다. 수정을 원하는 항목을 새로 기입해주세요.</p>

	<form action="/phonebook4/phone/update" method="get">
		이름(name): <input type="text" name="name" value="${pvo.name}"> <br>
		핸드폰(hp): <input type="text" name="hp" value="${pvo.hp}"> <br>
		회사(company): <input type="text" name="company" value="${pvo.company}"> <br>
		<input type="hidden" name="personId" value="${pvo.personId}"> <br>
		<button type="submit">수정</button>
	</form>
</body>
</html>