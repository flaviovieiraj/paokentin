<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>

</head>
<body>

	<h1>Pães Cadastrados</h1>
	<a href="/">home</a>

	<h3>${msg}</h3>

	<table class="table table-dark">
		<tr><th>Código</th><th>Nome</th><th>Telefone</th><th>Ações</th></tr>
		<c:forEach items="${paes}" var="pao">
			<tr>
				<td>${cliente.codigo}</td>
				<td>${cliente.nome}</td>
				<td>${cliente.contato}</td>
				<td><a href="/cliente/${cliente.codigo}">detalhar</a>
					<a href="/cliente/update/${cliente.codigo}">alterar</a>
					<a href="/cliente/delete/${cliente.codigo}">deletar</a></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>