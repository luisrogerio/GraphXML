<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grafo</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <h3>Grafo: <c:out value="${grafo.id}"></c:out> 
            <c:choose>
                <c:when test="${not empty mapaDijkstra}">
                    Distancia do nó <c:out value="${nomeNoOrigem}"></c:out> para todos os outros:
                    <ul>
                        <c:forEach items="${mapaDijkstra}" var="no">
                            <li><c:out value="${no.key.id}"></c:out>: <c:out value="${no.value}"></c:out></li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    Distancia do nó <c:out value="${nomeNoOrigem}"></c:out> para o nó <c:out value="${noDestino}"></c:out>:
                    <c:out value="${intDijkstra}"></c:out>
                </c:otherwise>
            </c:choose>
    </body>
</html>
