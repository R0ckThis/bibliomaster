<%-- 
    Document   : listaUsuario
    Created on : 18/10/2016, 16:17:18
    Author     : ramon
--%>

<%@page import="entidade.Usuario"%>
<%@page import="dao.UsuarioDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listagem de Usuarios - BiblioMaster</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <h1>Listagem de Usuários</h1>

            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>CPF</th>
                        <th>Nome Completo</th>
                        <th>Tipo</th>
                        <th>Ativo</th>
                        <th style="text-align: center">Editar</th>
                    </tr>
                </thead>
                <%
                    ArrayList<Object> usuarios = new UsuarioDAO().consultarTodos();
                    for (int i = 0; i < usuarios.size(); i++) {
                        Usuario usuario = (Usuario) usuarios.get(i);
                %>
                <tbody>
                    <tr>
                        <td><%= usuario.getPessoa().getCpf()%> </td>
                        <td><%= usuario.getPessoa().getNome() + " " + usuario.getPessoa().getSobrenome()%> </td>
                        <td><%= usuario.getTipo()%> </td>
                        <td><%if(usuario.isAtivo()){%>Sim<%} else {%>Não<%}%> </td>
                        <td align="center"><a href="/bibliomaster/acao?parametro=editarUsuario&pessoa=<%= usuario.getPessoa().getId()%>"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a> </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
                <div class="form-goup pull-right">
                    <a href="/bibliomaster/acao?parametro=gerarCSV&tipo=usuario" class="btn btn-success">Gerar CSV</a>
                </div>
        </div>
    </body>
</html>
