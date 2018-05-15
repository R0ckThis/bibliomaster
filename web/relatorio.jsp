<%-- 
    Document   : relatorio.jsp
    Created on : 22/11/2016, 08:27:33
    Author     : ramon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Relatório</title>
    </head>
    <body>
        <%
            byte[] rel = (byte[]) request.getAttribute("relatorio");

            response.setContentType("application/pdf");
            response.setContentLength(rel.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(rel, 0, rel.length);
            outStream.flush();
            outStream.close();
        %>
    </body>
</html>
