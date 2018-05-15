<%-- 
    Document   : listaLivros
    Created on : 09/09/2016, 14:35:19
    Author     : ramon
--%>

<%@page import="entidade.Usuario"%>
<%@page import="util.Formatacao"%>
<%@page import="entidade.Genero"%>
<%@page import="entidade.Autor"%>
<%@page import="entidade.Livro"%>
<%@page import="dao.LivroDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listagem de Livros</title>
    </head>
    <%
        HttpSession sessao = ((HttpServletRequest) request).getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
    %>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <h1>Listagem de Livros</h1>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Título</th>
                        <th>Publicação</th>
                        <th>Edição</th>
                        <th>Idioma</th>
                        <th>Editora</th>
                        <th>Autor(es)</th>
                        <th>Gênero(s)</th>
                        <%if (usuario.getTipo().equals("Administrador")) {%><th style="text-align: center">Editar</th><%}%>
                        <th style="text-align: center">Visualizar</th>
                    </tr>
                </thead>
                <%
                    ArrayList<Object> livros = new LivroDAO().consultarTodos();
                    for (int i = 0; i < livros.size(); i++) {
                        Livro livro = (Livro) livros.get(i);
                %>
                <tbody>
                    <tr>
                        <td style="vertical-align: middle;"><%= livro.getId()%></td>
                        <td style="vertical-align: middle;"><%= livro.getTitulo()%> </td>
                        <td style="vertical-align: middle;"><%= Formatacao.ajustaDataDMA(livro.getPublicacao().toString())%> </td>
                        <td style="vertical-align: middle;"><%= livro.getEdicao()%> </td>
                        <td style="vertical-align: middle;"><%= livro.getIdioma().getSigla()%> </td>
                        <td style="vertical-align: middle;"><%= livro.getEditora().getNome()%> </td>
                        <td style="vertical-align: middle;">
                            <%for (Autor autor : livro.getAutor()) {%>
                            <%= autor.getNome() + " " + autor.getSobrenome()%>
                            <br>
                            <%}%>

                        </td>

                        <td style="vertical-align: middle;">
                            <%for (Genero genero : livro.getGenero()) {%>
                            <%= genero.getNome()%>
                            <br>
                            <%}%>
                        </td>
                        <%if (usuario.getTipo().equals("Administrador")) {%><td style="vertical-align: middle;" align="center"><a href="/bibliomaster/acao?parametro=editarLivro&id=<%= livro.getId()%>"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a> </td><%}%>
                        <td style="vertical-align: middle;" align="center"><a href="#" onclick="visualizaLivro('<%= livro.getId()%>', '<%= livro.getTitulo() + " - " + livro.getEditora().getNome()%>')"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a> </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
                <div class="form-goup pull-right">
                    <a href="/bibliomaster/acao?parametro=gerarCSV&tipo=livro" class="btn btn-success">Gerar CSV</a>
                </div>
        </div>
        <%@include file="modalLivro.jsp" %>
    </body>
</html>
