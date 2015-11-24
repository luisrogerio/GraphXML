<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página Inicial</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
        <script src="js/scriptFormCreator.js"></script>

    </head>
    <body>
        <c:if test="${not empty mensagem}"><h2 id="mensagem">${mensagem}</h2></c:if>
        <ul>
            <li><a href="primeiraView.jsp">Inserir Grafo</a></li>
            <li><a href="segundaView.jsp">Carregar Grafo</a></li>
        </ul>

    </body>
</html>
