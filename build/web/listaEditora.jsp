<%-- 
    Document   : listaEditora
    Created on : 26/08/2016, 21:02:03
    Author     : ramon
--%>

<%@page import="entidade.Editora"%>
<%@page import="dao.EditoraDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listagem de Editoras - BiblioMaster</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <h1>Listagem de Editoras</h1>

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
                    ArrayList<Object> editoras = new EditoraDAO().consultarTodos();
                    for (int i = 0; i < editoras.size(); i++) {
                        Editora editora = (Editora) editoras.get(i);
                %>
                <tbody>
                    <tr>
                        <td><%= editora.getId()%> </td>
                        <td><%= editora.getNome()%> </td>
                        <td align="center"><a href="/bibliomaster/acao?parametro=editarEditora&id=<%= editora.getId()%>"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a> </td>
                        <td align="center"><a href="#" data-toggle="modal" data-target="#modalExcluir" data-href="/bibliomaster/acao?parametro=excluirEditora&id=<%= editora.getId()%>"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a> </td>
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
