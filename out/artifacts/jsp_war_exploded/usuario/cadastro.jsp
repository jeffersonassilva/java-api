<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cadastro - Usuário</title>
</head>
<body>
<form action="../api/usuario" method="post">
    <p>Nome:</p>
    <input type="text" name="name"/>
    <br/><br/><br/>
    <input type="submit" value="Salvar"/>
    <a href="/jsp">Cancelar</a>
</form>
</body>
</html>
