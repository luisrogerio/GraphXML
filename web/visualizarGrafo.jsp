<%-- 
    Document   : grafoSalvo
    Created on : 21/11/2015, 21:42:31
    Author     : Usuario001
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grafo</title>
    </head>
    <body>
        <h3>Grafo: <c:out value="${grafo.id}"></c:out> 
            <c:choose>
                <c:when test="${grafo.tipo == 'directed'}">Direcionado</c:when>
                <c:otherwise>Não-Direcionado</c:otherwise>
            </c:choose>
            <br />
            <a href="visualizaGrafoCanvas.jsp" >Visualizar grafo</a>
        </h3>
        <p>Nós</p>
        <ul>
            <c:forEach items="${grafo.nos}" var="no">
                <li><c:out value="${no.id}"></c:out></li>
                </c:forEach>
        </ul>
        <p>Arestas</p>
        <ul>
            <c:forEach items="${grafo.arestas}" var="aresta">
                <li><c:out value="${aresta.origem.id}"></c:out> - <c:out value="${aresta.destino.id}"></c:out></li>
                </c:forEach>
        </ul>
        Grau de emissão:
        <ul>
            <c:forEach items="${grauDeEmissao}" var="no">
                <li><c:out value="${no}"></c:out></li>
                </c:forEach>
        </ul>
        <a href="index.jsp">Voltar</a>
    </body>
</html>
