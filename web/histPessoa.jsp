<%-- 
    Document   : histPessoa
    Created on : 24/11/2016, 08:39:33
    Author     : ramon
--%>

<%@page import="entidade.Usuario"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Histórico de Pessoa</title>
    </head>
    <%
        HttpSession sessao = ((HttpServletRequest) request).getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
    %>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel-heading">Histórico de Pessoa</div>
                    <div class="panel-body">
                        <form action="/bibliomaster/acao?parametro=histPessoa" id="meuForm" method="post">
                            <div class="form-group">
                                <label>Pessoa</label>
                                <div class="pessoa row">
                                    <div class="form-group">
                                        <div class="col-md-3">
                                            <input type="number" class="id form-control" name="pessoa" min="1" placeholder="ID Pessoa" <%if (usuario.getTipo().equals("Usuário")) {%> value="<%= usuario.getPessoa().getId()%>" readonly <%} else {%> onblur="buscaEntidade(this, 'Pessoa', 'Pessoa não encontrada!')"<%}%>>
                                        </div>
                                        <div class="col-md-9 <%if (usuario.getTipo().equals("Administrador")) {%> input-group <%}%>">
                                            <input type="text" class="nome form-control" readonly <%if (usuario.getTipo().equals("Usuário")) {%> value="<%= usuario.getPessoa().getNome() + " " + usuario.getPessoa().getSobrenome()%>"<%}%>>
                                            <%if (usuario.getTipo().equals("Administrador")) {%><span class="input-group-btn">
                                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalBuscaPessoa"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                                            </span><%}%>
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
                                    <div class="col-md-3">
                                        <label>Tipo</label>
                                        <div class="form-group">
                                            <label class="radio-inline"><input type="radio" name="tipo" value="Multa">Multa</label>
                                            <label class="radio-inline"><input type="radio" name="tipo" checked="checked" value="Tudo">Tudo</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group pull-right">
                                    <input type="submit" value="Gerar PDF" class="btn btn-primary" onclick="this.form.target='_blank';return true;">
                                </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/buscaEntidade.js" type="text/javascript"></script>
        <%@include file="modalErro.jsp" %>
        <%@include file="modalBuscaPessoa.jsp" %>
    </body>
</html>
