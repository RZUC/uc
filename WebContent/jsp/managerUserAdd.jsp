<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<thead>
			<td>用户名：</td>
			<td>密码：</td>
			<td>创建时间：</td>
			<td>来源：</td>
			<td>用户类型：</td>
		</thead>
		<tbody>
			<td>${user.name }</td>
			<td>${user.password}</td>
			<td>${user.createTime}</td>
			<td>${user.whence}</td>
			<td>${user.userTypeId}</td>
		</tbody>
	</table>
</body>
</html>