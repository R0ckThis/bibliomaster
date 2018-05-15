<%-- 
    Document   : listaGenero
    Created on : 05/09/2016, 10:45:53
    Author     : ramon
--%>

<%@page import="dao.GeneroDAO"%>
<%@page import="entidade.Genero"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listagem de Gêneros - BiblioMaster</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <h1>Listagem de Gêneros</h1>

            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nome</th>
                        <th style="text-align: center">Editar</th>
                        <th style="text-align: center">Excluir</th>
                    </tr>
                </thead>
                <%
                    ArrayList<Object> generos = new GeneroDAO().consultarTodos();
                    for (int i = 0; i < generos.size(); i++) {
                        Genero genero = (Genero) generos.get(i);
                %>
                <tbody>
                    <tr>
                        <td><%= genero.getId()%> </td>
                        <td><%= genero.getNome()%> </td>
                        <td align="center"><a href="/bibliomaster/acao?parametro=editarGenero&id=<%= genero.getId()%>"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a> </td>
                        <td align="center"><a href="#" data-toggle="modal" data-target="#modalExcluir" data-href="/bibliomaster/acao?parametro=excluirGenero&id=<%= genero.getId()%>"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a> </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        <%@include file="modalExcluir.jsp" %>
    </body>
</html>
