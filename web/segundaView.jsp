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
    </head>
    <body>
        <h3>Carregue um Grafo</h3>
        <form method="post" action="CarregaGrafoController" enctype="multipart/form-data">
           
            <input type="file" name="uploadFile" /> 
            <br/><br/> 
            <input type="submit" value="Upload" />
        </form>
        <p><a href="index.jsp">Voltar</a></p>
    </body>
</html>
