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
        <p>Grau de emissão:</p>
        <ul>
            <c:forEach items="${grauDeEmissao}" var="no">
                <li><c:out value="${no}"></c:out></li>
                </c:forEach>
        </ul>
        <p>Grau de Recepção</p>
        <ul>
            <c:forEach items="${grauDeRecepcao}" var="grauRep">
                <li><c:out value="${grauRep}"></c:out></li>
                </c:forEach>
        </ul>

        <p>Nós sumidouros</p>
        <ul>
            <c:forEach items="${nosSumidouros}" var="sumidouro">
                <li><c:out value="${sumidouro.id}"></c:out></li>
                </c:forEach>
        </ul>

        <p>Nós Fontes</p>
        <c:choose>
            <c:when test="${empty nosFonte}">
                <p>Está vazio</p>
            </c:when>
            <c:otherwise>
                <ul>
                    <c:forEach items="${nosFonte}" var="fonte">
                        <li>
                            <c:out value="${fonte.id}"></c:out>
                        </li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>

        <p>Lista de Antecessores</p>
        <ul>
            <c:forEach items="${listaNosAntecessores}" var="listaAnt" >
                <li><c:out value="${listaAnt['key'].id}"></c:out>:
                    <c:forEach items="${listaAnt['value']}" var="valores">
                        <c:out value="${valores.id}"></c:out>
                    </c:forEach>
                </li>
            </c:forEach>
        </ul>
        <p>Lista de Sucessores</p>
        <ul>
            <c:forEach items="${listaNosSucessores}" var="listaSuc" >
                <li><c:out value="${listaSuc['key'].id}"></c:out>:
                    <c:forEach items="${listaSuc['value']}" var="valores">
                        <c:out value="${valores.id}"></c:out>
                    </c:forEach>
                </li>
            </c:forEach>
        </ul>
        <p>Vértices Independentes</p>
        <ul>
            <c:forEach items="${listaVerticesIndependentes}" var="veticesIndep" >
                <li><c:out value="${veticesIndep}"></c:out>:
                    <c:forEach items="${veticesIndep['value']}" var="valores">
                        <c:out value="${valores.id}"></c:out>
                    </c:forEach>
                </li>
            </c:forEach>
        </ul>
        
        




        <a href="index.jsp">Voltar</a>
    </body>
</html>
