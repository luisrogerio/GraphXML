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
        <h2>Escolha o arquivo:</h2>
        <form action="SegundoController"  method="post" enctype="multipart/form-data">
            <p><input type="file" id="xml"> </p>
             <input type="submit" name="bntIncluir" value="Enviar" />
        </form>
    </body>
</html>
