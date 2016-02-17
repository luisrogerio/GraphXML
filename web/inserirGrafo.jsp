
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

        <form action="InserirGrafoController" method="post" onsubmit="verificaValorAresta()">
            <p><input type="text" id="nomeGrafo" size="20" name="nomeDoGrafo" value="" placeholder="Nome do Grafo" />
                <label for="gDirecionado"><input type="checkbox" name="direcionado" id="gDirecionado" value="directed">Direcionado</label>
                <label for="gValorado"><input type="checkbox" name="valorado" id="gValorado" value="valorado">Valorado</label><br /> </p> 
            
            <div id="formulario">
                <p> 
                    <input type="text" id="no" size="20" name="nos" value="" placeholder="Insira nome do Nó" />&nbsp;&nbsp;*Utilize nomes unicos para cada nó!
                </p>
            </div>
            <button id="adiconaCampo" class="corBotao">Adiconar Nó</button>
            <p>Arestas(par ordenado):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;<span id="txtValor" style="display: none">Valor:</span></p><br />
            <div id="arestas">
                <input type="text" id="aresta" name="arestas" value="" size="20" placeholder="A,B"/>
                <input type="number" id="valorAresta" name="valorAresta" value="" placeholder="10" style="display: none"/>
            </div>
            <p><button id="adiconaAresta" class="corBotao">Adiconar Aresta</button></p>
            <a href="index.jsp" class="bnt">Voltar</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="bntIncluir" value="Enviar" class="corBotao"/> 
        </form>
        
        <script src="js/scriptFormCreator.js"></script>
    </body>
</html>
