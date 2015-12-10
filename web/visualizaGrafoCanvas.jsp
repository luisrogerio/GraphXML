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
            <c:if test="${grafo.tipo == 'directed'}"></c:if>
                Dracula.Edge.style.directed = <c:out value="$test"/>;

            <c:forEach items="${grafo.arestas}" var = "aresta">
                g.addEdge("<c:out value="${aresta.origem}"/>", "<c:out value="${aresta.destino}"/>");
            </c:forEach>

                //var layouter = new Graph.Layout.Ordered(g, topologicalSort(g)); //OUTRA FORMA DE ORGANIZAR O GRAFO
                var layouter = new Graph.Layout.Spring(g);
                var renderer = new Graph.Renderer.Raphael('canvas', g, width, height);
                layouter.layout();
                renderer.draw();
            });

        </script>
        <style type="text/css">
            body {
                overflow: hidden;
            }
        </style>
    </head>
    <body>
        <h3>Grafo: <c:out value="${grafo.id}"></c:out>
            <div id="canvas"></div> 
            <br />
            Reorganizar o Grafo: <button id="redraw" onclick="redraw();">Reorganizar</button>
    </body>
</html>
