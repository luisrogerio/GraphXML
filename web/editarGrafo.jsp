<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/style.css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
    </head>

    <body>
            <h2>Editar Grafo</h2>

            <form action="InserirGrafoController" method="post">
                <p><input type="text" id="nomeGrafo" size="20" name="nomeDoGrafo" value="<c:out value="${grafo.id}"></c:out>" />
                <label for="gDirecionado"><input type="checkbox" name="direcionado" id="gDirecionado" value="directed" <c:if test="${grafo.tipo == 'directed'}"> checked</c:if>/>Direcionado</label>
                <label for="gValorado"><input type="checkbox" name="valorado" id="gValorado" value="valorado" <c:if test="${grafo.tipoAresta}"> checked</c:if>/>Valorado</label><br /> </p> 

                <div id="formulario">
                <c:forEach items="${grafo.nos}" var="no">
                    <p>
                        <input type="text" id="no" size="20" name="nos" value="<c:out value="${no.id}"></c:out>" />
                            <a href="#" id="removerNo" class="bnt bntExcluir">Remove</a>
                        </p>
                </c:forEach>
            </div>
            <button id="adiconaCampo" class="corBotao">Adiconar Nó</button>

            <p>Arestas(par ordenado):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;<span id="txtValor" style="display: none">Valor:</span></p><br />  
            <div id="arestas">
                <c:forEach items="${grafo.arestas}" var = "aresta"> 
                    <p>
                        <input type="text" id="aresta" name="arestas" value='<c:out value="${aresta.origem.id}"></c:out><c:out value=","></c:out><c:out value="${aresta.destino.id}"></c:out>' size="20"/>
                        <input type="number" id="valorAresta" name="valorAresta" value="<c:out value="${aresta.valor}"></c:out>"/>
                            <a href="#" id="removerAresta" class="bnt bntExcluir">Remove</a>
                        </p>
                </c:forEach>
            </div>
            <p><button id="adiconaAresta" class="corBotao">Adiconar Aresta</button></p>
            <a href="index.jsp" class="bnt">Voltar</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="bntIncluir" value="Enviar" class="corBotao"/> 
        </form>
        <script src="js/scriptFormCreator.js"></script>

    </body>
</html>
