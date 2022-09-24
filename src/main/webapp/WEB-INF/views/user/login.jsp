<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

			<h1>로그인 페이지 입니다 </h1>
						<form method="post"
							action="${pageContext.request.contextPath }/login">

							<div>
								<label for="userId"> 
								<input type="text" name="userId" id="userId" maxlength="50" lang="en"
									placeholder="아이디를 입력해 주세요.">
								</label>
							</div>

							<div>
								<label for="userPwd"> 
								<input type="password" name="userPwd" id="userPwd"maxlength="15" lang="en"
									placeholder="비밀번호를 입력해 주세요.">
								</label>
							</div>

							<div >
								<input type="submit" value="로그인">
							</div>
						</form>

<P>  모델 테스트 :  ${model}. </P>
</body>
</html>
