<%-- 
    Document   : segundaView
    Created on : 19/11/2015, 19:46:17
    Author     : Usuario001
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <h3>Carregue um Grafo</h3>
        <form method="post" action="CarregaGrafoController" enctype="multipart/form-data">

            <input type="file" name="uploadFile" class="botoes corBotao"/> 
            <br/><br/> 
            <a href="index.jsp" class="bnt">Voltar</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit" value="Upload" class="corBotao"/>
        </form>
       
    </body>
</html>
