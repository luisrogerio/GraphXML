
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/style.css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
    </head>

    <body>
        <h2>Inserir Grafo</h2>

        <form action="GrafoController" method="post">
            <br />
            <div id="formulario">
                <p> <label for="nomeGrafo" ><input type="text" id="nomeGrafo" size="20" name="no-0" value="" placeholder="Nome do Grafo" /></label><input type="checkbox" name="direcionado" value="directed">Direcionado <br /> </p>   
                <p>
                    <label for="no"><input type="text" id="no" size="20" name="no-0" value="" placeholder="Insira nome do Nó" /></label>
                </p>

            </div>    
            <button id="adiconaCampo">Adiconar Nó</button>
            <div id="arestas">
                <p>Arestas(par ordenado):<br />
                    <label for="aresta"><input type="text" id="aresta" name="aresta-0" value="" size="20" placeholder="A,B"/></p></label>

            </div>
            <p><button id="adiconaAresta">Adiconar Aresta</button></p>
            <input type="submit" name="bntIncluir" value="Enviar" />
        </form>
        <script src="js/scriptFormCreator.js"></script>
    </body>
</html>
