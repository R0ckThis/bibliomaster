<%-- 
    Document   : histLivro
    Created on : 23/11/2016, 13:55:20
    Author     : ramon
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Histórico de Livros</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel-heading">Histórico de Livro</div>
                    <div class="panel-body">
                        <form action="/bibliomaster/acao?parametro=histLivro" id="meuForm" method="post">
                            <div class="form-group">
                                <label>Livro</label>
                                <div class="livro">
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-md-3">
                                                <input type="number" class="id form-control" name="livro" min="1" placeholder="ID Livro" onblur="buscaEntidade(this, 'Livro', 'Livro não encontrado!')">
                                            </div>
                                            <div class="col-md-9 input-group">
                                                <input type="text" class="nome form-control" readonly>
                                                <span class="input-group-btn">
                                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalBuscaLivro"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label>Data Inicial</label>
                                            <input id="data_inicial" type="date" class="form-control" name="data_inicial" <%Date dataAtual = new Date();
                                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                                String dataStr = sdf.format(dataAtual);%>max="<%= dataStr%>">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label>Data Final</label>
                                            <input id="data_final" type="date" class="form-control" name="data_final" max="<%= dataStr%>">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group pull-right">
                                    <input type="submit" value="Gerar PDF" class="btn btn-primary">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/buscaEntidade.js" type="text/javascript"></script>
        <%@include file="modalErro.jsp" %>
        <%@include file="modalBuscaLivro.jsp" %>
    </body>
</html>
