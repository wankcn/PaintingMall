<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="" method="post">
		验证码:<input type="text" name="checkcode"/><img src="${pageContext.request.contextPath }/CheckImgServlet"><br/>
		<input type="submit" value="提交">
	</form>
</body>
</html>