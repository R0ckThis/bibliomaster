<%-- 
    Document   : listaIdioma
    Created on : 23/08/2016, 20:48:21
    Author     : ramon
--%>

<%@page import="entidade.Idioma"%>
<%@page import="dao.IdiomaDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listagem de Idiomas - BiblioMaster</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <h1>Listagem de Idiomas</h1>

            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nome</th>
                        <th>Sigla</th>
                        <th style="text-align: center">Editar</th>
                        <th style="text-align: center">Excluir</th>
                    </tr>
                </thead>
                <%
                    ArrayList<Object> idiomas = new IdiomaDAO().consultarTodos();
                    for (int i = 0; i < idiomas.size(); i++) {
                        Idioma idioma = (Idioma) idiomas.get(i);
                %>
                <tbody>
                    <tr>
                        <td><%= idioma.getId()%> </td>
                        <td><%= idioma.getNome()%> </td>
                        <td><%= idioma.getSigla()%> </td>
                        <td align="center"><a href="/bibliomaster/acao?parametro=editarIdioma&id=<%= idioma.getId()%>"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a> </td>
                        <td align="center"><a href="#" data-toggle="modal" data-target="#modalExcluir" data-href="/bibliomaster/acao?parametro=excluirIdioma&id=<%= idioma.getId()%>"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a> </td>

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
