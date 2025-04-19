<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>

	.formError{
	
		color:red;
	}
</style>

</head>
<body>

	<h2 style="text-align: center">Página de Login</h2>
	
	<form:form action="${pageContext.request.contextPath}/autenticacionUsuario" method="POST">
	
	
	<c:if test="${param.error!=null}">
	
	<strong class="formError">Usuario o contraseña incorrecta</strong>
	</c:if>
	
		<p >
		
		Usuario: <input type="text" name="username"/>
		</p>
		
		<p>
		
		Contraseña: <input type="password" name="password"/>
		</p>
		
		<input type="submit" value="Login" />
	
	</form:form>

</body>
</html>