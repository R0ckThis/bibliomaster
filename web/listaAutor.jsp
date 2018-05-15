<%-- 
    Document   : listaAutor
    Created on : 05/09/2016, 14:09:35
    Author     : ramon
--%>

<%@page import="dao.AutorDAO"%>
<%@page import="entidade.Autor"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listagem de Autores - BiblioMaster</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <h1>Listagem de Autores</h1>

            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nome</th>
                        <th>Sobrenome</th>
                        <th style="text-align: center">Editar</th>
                        <th style="text-align: center">Excluir</th>
                    </tr>
                </thead>
                <%
                    ArrayList<Object> autores = new AutorDAO().consultarTodos();
                    for (int i = 0; i < autores.size(); i++) {
                        Autor autor = (Autor) autores.get(i);
                %>
                <tbody>
                    <tr>
                        <td><%= autor.getId()%> </td>
                        <td><%= autor.getNome()%> </td>
                        <td><%= autor.getSobrenome()%> </td>
                        <td align="center"><a href="/bibliomaster/acao?parametro=editarAutor&id=<%= autor.getId()%>"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a> </td>
                        <td align="center"><a href="#" data-toggle="modal" data-target="#modalExcluir" data-href="/bibliomaster/acao?parametro=excluirAutor&id=<%= autor.getId()%>"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a> </td>
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
