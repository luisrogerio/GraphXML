<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página Inicial</title>
        <link rel="stylesheet" href="css/style.css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
        <script src="js/scriptFormCreator.js"></script>

    </head>
    <body>
        <c:if test="${not empty mensagem}">
            <h2 id="mensagem">${mensagem}</h2>
        </c:if> 
        <div class="margem">
            <a href="inserirGrafo.jsp" class="corBotao botoes">Inserir Grafo</a>
            <a href="carregarGrafo.jsp" class="corBotao botoes">Carregar Grafo</a>

            <c:if test="${not empty grafo}">
                <a href="editarGrafo.jsp" class="corBotao botoes">Editar Grafo</a> <br /><br />
                <form action="DownloadGrafo" enctype="multipart/form-data" method="post" class="semQuebraDeLinha">
                    <input type="hidden" name="idGrafo" value="${grafo.id}" />
                    <input type="submit" value="Download" class="bnt"/> 
                    <a href="visualizaGrafoCanvas.jsp" class="bnt">Ver Grafo</a>
                </form>
                <form action="GeraInformacoesGrafo" method="post" class="semQuebraDeLinha">
                    <input type="submit" value="Informações do Grafo" class="bnt"/>
                </form>
                <br /><br />
                <form action="CalcularDijkstra" method="post">
                    <input type="submit" value="Calcular Dijkstra" class="corBotao botoes" />
                    Nó de Origem:
                    <select name="noOrigem" class="bnt">
                        <c:forEach items="${grafo.nos}" var="no">
                            <option value="<c:out value="${no.id}"></c:out>"><c:out value="${no.id}"></c:out></option>
                        </c:forEach>
                    </select>
                    &nbsp;Nó de Destino:
                    <select name="noDestino" class="bnt">
                        <option value="todosNos">Todos os nós</option>
                        <c:forEach items="${grafo.nos}" var="no">
                            <option value="<c:out value="${no.id}"></c:out>"><c:out value="${no.id}"></c:out></option>
                        </c:forEach>
                    </select>
                </form><br />
                <c:if test="${grafo.tipo == 'undirected' && grafo.tipoAresta}">
                    <form action="AlgoritmoDeKruskalController" method="post">
                        <input type="submit" value="Árvore Geradora Mínima - Algoritmo de Kruskal" class="corBotao botoes" />
                    </form>
                </c:if>
            </c:if>
        </div>
    </body>
</html>
