<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualiza Grafo</title>
        <script type="text/javascript" src="js/vendor/raphael.js"></script>
        <script type="text/javascript" src="js/vendor/jquery-1.8.2.js"></script>
        <script type="text/javascript" src="js/lib/dracula_graffle.js"></script>
        <script type="text/javascript" src="js/lib/dracula_graph.js"></script>
        <script type="text/javascript" src="js/lib/dracula_algorithms.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                var width = $(document).width();
                var height = $(document).height();
                var g = new Graph();

                Dracula.Edge.style.directed =<c:choose>
                <c:when test="${grafo.tipo == 'directed'}">true</c:when>
                <c:otherwise>false</c:otherwise>
            </c:choose>
            <c:forEach items="${grafo.nos}" var="no">
                        g.addNode("<c:out value="${no.id}"></c:out>");
            </c:forEach>
            <c:forEach items="${grafo.arestas}" var = "aresta"> //nao funciona para nos isolados, tente adicionar tds os nos e depois criar as arestas
                        g.addEdge("<c:out value="${aresta.origem.id}"/>", "<c:out value="${aresta.destino.id}"/>");
            </c:forEach>
//g.addEdge("asdasd",""); isso nao funfa, ele cria uma aresta ligada a um no vazio
                        //var layouter = new Graph.Layout.Ordered(g, topologicalSort(g)); //OUTRA FORMA DE ORGANIZAR O GRAFO
                        var layouter = new Graph.Layout.Spring(g);
                        var renderer = new Graph.Renderer.Raphael('canvas', g, width, height);
                        layouter.layout();
                        renderer.draw();


                    });


        </script>
        <style>
            body{
                overflow-x: hidden;
            }

        </style>
    </head>
    <body>
        <h3>Grafo: <c:out value="${grafo.id}"></c:out>
            <p> Reorganizar o Grafo: <button id="reorganizar" >Reorganizar</button> </p>
            <p><a href="index.jsp">voltar</a></p>
            <div id="canvas"></div> 
            <br />
            <script>
                $("#reorganizar").click(function () {
                    var layouter = new Graph.Layout.Spring(g);
                    var renderer = new Graph.Renderer.Raphael('canvas', g, width, height);
                    layouter.layout();
                    renderer.draw();
                })
                        ;
            </script>
    </body>
</html>
