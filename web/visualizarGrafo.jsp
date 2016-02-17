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
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <h3>Grafo: <c:out value="${grafo.id}"></c:out> 
            <c:choose>
                <c:when test="${grafo.tipo == 'directed'}">Direcionado</c:when>
                <c:otherwise>Não-Direcionado</c:otherwise>
            </c:choose>
            <br /><br />
            <a href="visualizaGrafoCanvas.jsp" class="bnt">Ver grafo</a>
            Editar o Grafo: <a href="editarGrafo.jsp" class="corBotao botoes">Editar</a>
        </h3>
        <div class="divEsquerda">
            <p>Nós:</p>
            <ul>
                <c:forEach items="${grafo.nos}" var="no">
                    <li><c:out value="${no.id}"></c:out></li>
                    </c:forEach>
            </ul>


            <p>Arestas:</p>
            <ul>
                <c:forEach items="${grafo.arestas}" var="aresta">
                    <li><c:out value="${aresta.id}"></c:out>: <c:out value="${aresta.origem.id}"></c:out> - <c:out value="${aresta.destino.id}"></c:out></li>
                    </c:forEach>
            </ul>
            <p>Grau dos Nós:</p>
            <ul>
                <c:forEach items="${nosComGrau}" var="no">
                    <li><c:out value="${no.key.id}"></c:out>: <c:out value="${no.value}"></c:out></li>
                    </c:forEach>
            </ul>

            <p>Ordem do Grafo:</p>
            <p><c:out value="${ordemGrafo}"></c:out></p>

                <p>Arestas Incidentes:</p>
                <ul>
                <c:forEach items="${grafo.arestas}" var="aresta">
                    <li><c:out value="${aresta.id}"></c:out>: <c:out value="${aresta.origem.id} e ${aresta.destino.id}"></c:out></li>
                    </c:forEach>
            </ul>

            <p>Vertices Adjacentes: </p>
            <ul>
                <c:forEach items="${mapaVerticesAdj}" var="vertice">
                    <li>
                        <c:out value="${vertice.key.id}:"></c:out>
                        <c:forEach items="${vertice.value}" var="verticesAdj">
                            <c:out value="${verticesAdj.id}   "></c:out>
                        </c:forEach>
                    </li>
                </c:forEach>
            </ul>

        </div>




        <c:if test="${tipoGrafoArestas}">
            <div class="divDireita">
                <p>Grau de emissão:</p>
                <ul>
                    <c:forEach items="${grauDeEmissao}" var="no">
                        <li><c:out value="${no.key.id}"></c:out>: <c:out value="${no.value}"></c:out></li>
                        </c:forEach>
                </ul>

                <p>Grau de Recepção:</p>
                <ul>
                    <c:forEach items="${grauDeRecepcao}" var="no">
                        <li><c:out value="${no.key.id}"></c:out>: <c:out value="${no.value}"></c:out></li>
                        </c:forEach>
                </ul>
                <p>Nós Sumidouros: *Nó com grau de saida 0</p>
                <ul>
                    <c:forEach items="${grauDeEmissao}" var="no">
                        <c:if test="${no.value == 0}">
                            <li>
                                <c:out value="${no.key.id}"></c:out>: <c:out value="${no.value}"></c:out>
                                </li>
                        </c:if>

                    </c:forEach>
                </ul>
                <p>Nós Fonte: *Nó com grau de entrada 0</p>
                <ul>
                    <c:forEach items="${grauDeRecepcao}" var="no">
                        <c:if test="${no.value == 0}" >
                            <li>
                                <c:out value="${no.key.id}"></c:out>: <c:out value="${no.value}"></c:out>
                                </li>
                        </c:if>

                    </c:forEach>
                </ul>

                <p>Nós Isolados: </p>
                <ul>
                    <c:forEach items="${nosComGrau}" var="no">
                        <c:if test="${no.value == 0}" >
                            <li>
                                <c:out value="${no.key.id}"></c:out>: <c:out value="${no.value}"></c:out>
                                </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <a href="index.jsp" class="bnt" id="botaoVoltar">Voltar</a>
    </body>
</html>
