<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Listagem - Usuário</title>
</head>
<body>
<h2>Listagem</h2>

<table class="table">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Nome</th>
    </tr>
    </thead>
    <tbody class="listagem">
    </tbody>
</table>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="application/javascript">
    $('document').ready(function () {
        $.ajax({
            url: '/jsp/api/usuarioDAO',
            type: 'GET'
        }).done(function (data) {
            data.map(function (value) {
                $(".listagem").append('<tr><td>' + value['id'] + '</td><td>' + value['nome'] + '</td></tr>');
            });
        }).fail(function () {
            console.log('erro');
        });
    });
</script>
</body>
</html>
