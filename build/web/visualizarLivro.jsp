<%-- 
    Document   : visualizaLivro
    Created on : 12/09/2016, 08:40:28
    Author     : ramon
--%>

<%@page import="entidade.Genero"%>
<%@page import="entidade.Autor"%>
<%@page import="util.Formatacao"%>
<%@page import="entidade.Livro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Livro livro = (Livro) request.getAttribute("livro");
    if (livro.getCapa().isEmpty()) {
        livro.setCapa("capa/sem-foto.jpg");
    }
%>
<div class="container">

    <div class="col-md-3">
        <img src="<%= livro.getCapa()%>" class="img-responsive" alt="<%= livro.getTitulo()%>">
    </div>
    <div class="col-md-9">
        <h1><%= livro.getTitulo()%></h1>
        <h3><%= livro.getEditora().getNome()%></h3>
        <label>Data de publicação: <%= Formatacao.ajustaDataDMA(livro.getPublicacao().toString())%></label>
        <br>
        <label>Número de páginas: <%if (livro.getPaginas() == 0) {%>Não disponível<%} else {%><%= livro.getPaginas()%><%}%></label>
        <br>
        <label>Edição: <%if (livro.getEdicao().equals("")) {%>Não disponível<%} else {%><%= livro.getEdicao()%><%}%></label>
        <br>
        <label>Localização: <%= livro.getLocalizacao()%></label>
        <br>
        <label>Estoque total: <%= livro.getEstoque_total()%></label>
        <br>
        <label>Estoque disponível: <%= livro.getEstoque_disponivel()%></label>
        <br>
        <label>Idioma: <%= livro.getIdioma().getNome()%></label>
        <br>
        <div class="row">
            <div class="col-md-3" >
                <label>Autor(es):<br> <%for (Autor autor : livro.getAutor()) {%>
                    <%= autor.getNome() + " " + autor.getSobrenome()%>
                    <br>
                    <%}%>
                </label>
            </div>
            <div class="col-md-3">
                <label>Gênero(s):<br> <%for (Genero genero : livro.getGenero()) {%>
                    <%= genero.getNome()%>
                    <br>
                    <%}%>
                </label>
            </div>
        </div>
    </div>
</div>
