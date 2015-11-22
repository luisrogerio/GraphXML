
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página Inicial</title>
    </head>
    <body>
        <!--Colocar aqui um negócio em jQuery que faça as coisas desaparecem em 5 segundos-->
        <c:if test="${not empty mensagem}"><h2>${mensagem}</h2></c:if>
        <ul>
            <li><a href="primeiraView.jsp">Inserir Grafo</a></li>
            <li><a href="segundaView.jsp">Carregar Grafo</a></li>
        </ul>
    </body>
</html>
