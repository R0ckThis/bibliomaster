<%-- 
    Document   : sucesso
    Created on : 24/08/2016, 21:07:35
    Author     : ramon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sucesso - BiblioMaster</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <%
            String pagina = (String) request.getAttribute("paginaRetorno");
            String nomeRetorno = (String) request.getAttribute("nomeRetorno");
            String mensagem = (String) request.getAttribute("mensagem");
        %>
        <div class="container">
            <div class="panel-group">
                <div class="panel panel-success">
                    <div class="panel-heading" style="font-size: 25px"><strong>Sucesso!</strong></div>
                    <div class="panel-body">
                        <%=mensagem%>
                    </div>
                </div>
            </div>


            <a href='<%= pagina%>'><%= nomeRetorno%></a>
            <br>
            <a href='menu.jsp'>Volta para o início</a>
        </div>
    </body>
</html>
