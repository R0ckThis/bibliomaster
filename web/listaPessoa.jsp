<%-- 
    Document   : listaPessoa
    Created on : 08/09/2016, 11:10:31
    Author     : ramon
--%>

<%@page import="dao.PessoaDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Pessoa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listagem de Pessoas - BiblioMaster</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <h1>Listagem de Pessoas</h1>

            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nome</th>
                        <th>Sobrenome</th>
                        <th>CPF</th>
                        <th>RG</th>
                        <th>Email</th>
                        <th style="text-align: center">Editar</th>
                        <th style="text-align: center">Excluir</th>
                    </tr>
                </thead>
                <%
                    ArrayList<Object> pessoas = new PessoaDAO().consultarTodos();
                    for (int i = 0; i < pessoas.size(); i++) {
                        Pessoa pessoa = (Pessoa) pessoas.get(i);
                %>
                <tbody>
                    <tr>
                        <td><%= pessoa.getId()%> </td>
                        <td><%= pessoa.getNome()%> </td>
                        <td><%= pessoa.getSobrenome()%> </td>
                        <td><%= pessoa.getCpf()%> </td>
                        <td><%= pessoa.getRg()%> </td>
                        <td><%= pessoa.getEmail()%> </td>
                        <td align="center"><a href="/bibliomaster/acao?parametro=editarPessoa&id=<%= pessoa.getId()%>"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a> </td>
                        <td align="center"><a href="#" data-toggle="modal" data-target="#modalExcluir" data-href="/bibliomaster/acao?parametro=excluirPessoa&id=<%= pessoa.getId()%>"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a> </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
                <div class="form-goup pull-right">
                    <a href="/bibliomaster/acao?parametro=gerarCSV&tipo=pessoa" class="btn btn-success">Gerar CSV</a>
                </div>
        </div>
        <%@include file="modalExcluir.jsp" %>
    </body>
</html>
