
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            input, button { padding:5px; border:1px solid #999; border-radius:4px; -moz-border-radius:4px; 
                            -web-kit-border-radius:4px; -khtml-border-radius:4px; } 
            </style>
        </head>

        <body>
            <h2>Inserir Grafo</h2>

            <form action="GrafoController" method="post">
                <br />
                <div id="formulario">
                <p> <label for="nomeGrafo" ><input type="text" id="nomeGrafo" size="20" name="no-0" value="" placeholder="Nome do Grafo" /></label></p>
                <p> <input type="checkbox" name="direcionado" value="directed">Direcionado <br /> </p>   
                <p>
                    <label for="no"><input type="text" id="no" size="20" name="no-0" value="" placeholder="Insira nome do Nó" /></label>
                </p>

            </div>    
            <button id="adiconaCampo">Adiconar Nó</button>
            <div id="arestas">
                <p>Arestas(par ordenado):<br />
                    <label for="aresta"><input type="text" id="aresta" name="aresta-0" value="" size="20" placeholder="A,B"/></p></label>

            </div>
            <button id="adiconaAresta">Adiconar Aresta</button><br />
            <input type="submit" name="bntIncluir" value="Enviar" />
        </form>

        <script>
            $(function () {
                var i = 1;
                var j = 1;
                $('#adiconaCampo').live('click', function () {
                    $('<p><label for="no"><input type="text" id="no" size="20" name="no-' + i + '" value="" placeholder="Insira nome do Nó" /></label> \n\
            <a href="#" id="removerNo">Remove</a></p>').appendTo(formulario);
                    i++;
                    return false;
                });
                $('#adiconaAresta').live('click', function () {
                    $('<p><label for="no"><input type="text" id="aresta" size="20" name="aresta-' + j + '" value="" placeholder="A,B" /></label> \n\
            <a href="#" id="removerAresta">Remove</a></p>').appendTo(arestas);
                    j++;
                    return false;
                });

                $('#removerNo').live('click', function () {
                    if (i > 1) {
                        $(this).parents('p').remove();
                        i--;
                    }
                    return false;
                });
                $('#removerAresta').live('click', function () {
                    if (j > 1) {
                        $(this).parents('p').remove();
                        j--;
                    }
                    return false;
                });

            });
        </script>
    </body>
</html>
