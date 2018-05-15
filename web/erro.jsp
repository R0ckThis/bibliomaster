<%-- 
    Document   : erro
    Created on : 24/08/2016, 20:01:23
    Author     : ramon
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Erro - BiblioMaster</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <div class="panel-group">
                <div class="panel panel-danger">
                    <div class="panel-heading" style="font-size: 25px"><strong>Erro!</strong></div>
                    <div class="panel-body">
                        <%
                            String pagina = (String) request.getAttribute("paginaRetorno");
                            ArrayList<String> retorno = (ArrayList<String>) request.getAttribute("retorno");
                            for (int i = 0; i < retorno.size(); i++) {
                        %>

                        <h3><%=retorno.get(i)%></h3>

                        <%}%>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
                <a href="<%= pagina%>">Voltar para o cadastro</a>
                <br>
                <a href="menu.jsp">Voltar para o início</a>
        </div>
    </body>
</html>
