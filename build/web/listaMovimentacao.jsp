<%-- 
    Document   : listaMovimentacao
    Created on : 13/10/2016, 10:05:37
    Author     : ramon
--%>

<%@page import="entidade.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listagem de Movimentações - BiblioMaster</title>
    </head>
    <%
        HttpSession sessao = ((HttpServletRequest) request).getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
    %>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <h1>Listagem de Movimentações</h1>


            <div class="row">
                <div class="pessoa col-md-6">
                    <label>Pessoa</label>
                    <div class="form-group row">
                        <div class="col-md-3">
                            <input id="pessoa" type="number" class="id form-control" name="pessoa" min="1" required <%if (usuario.getTipo().equals("Usuário")) {%> value="<%= usuario.getPessoa().getId()%>" readonly <%} else {%> onblur="buscaEntidade(this, 'Pessoa', 'Pessoa não encontrada!')"<%}%>>
                        </div>
                        <div class="col-md-9 <%if (usuario.getTipo().equals("Administrador")) {%> input-group <%}%>">
                            <input type="text" class="nome form-control" readonly <%if (usuario.getTipo().equals("Usuário")) {%> value="<%= usuario.getPessoa().getNome() + " " + usuario.getPessoa().getSobrenome()%>"<%}%>>
                            <%if (usuario.getTipo().equals("Administrador")) {%><span class="input-group-btn">
                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalBuscaPessoa"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                            </span><%}%>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="col-md-8">
                            <label>Tipo</label>
                            <div class="row">
                                <label class="radio-inline"><input type="radio" name="opt" checked="checked" value="Retirado">Retirado</label>
                                <label class="radio-inline"><input type="radio" name="opt" value="Reserva">Reservado</label>
                                <label class="radio-inline"><input type="radio" name="opt" value="Cancelado">Cancelado</label>
                                <label class="radio-inline"><input type="radio" name="opt" value="Fechado">Fechado</label>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="row">
                                <label></label>
                            </div>
                            <div class="row pull-right">
                            <input type="button" value="Buscar" class="btn btn-primary" onclick="consultaMovimentacao()">
                            <input type="button" value="Gerar CSV" class="btn btn-success" onclick="gerarCSV()">
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="Movimentacoes">
                <h3 style="text-align: center;">Busque Algo!</h3>
            </div>

        </div>
        <script src="js/buscaEntidade.js" type="text/javascript"></script>
        <%@include file="modalBuscaPessoa.jsp" %>
        <%@include file="modalErro.jsp" %>
        <%@include file="modalCancelar.jsp" %>
        <script>
                                function consultaMovimentacao() {
                                    var xhttp;
                                    var pessoa = document.getElementById('pessoa').value;
                                    var rads = document.getElementsByName('opt');
                                    var status;

                                    for (var i = 0; i < rads.length; i++) {
                                        if (rads[i].checked) {
                                            status = rads[i].value;
                                        }
                                    }

                                    xhttp = new XMLHttpRequest();
                                    xhttp.onreadystatechange = function () {
                                        if (this.readyState === 4 && this.status === 200) {
                                            document.getElementById('Movimentacoes').innerHTML = this.responseText;
                                        }
                                    };
                                    xhttp.open("GET", "/bibliomaster/acao?parametro=consultarMovimentacoes&status=" + status.toString() + "&pessoa=" + pessoa.toString(), true);
                                    xhttp.send();
                                }
                                
                                function gerarCSV() {
                                    var pessoa = document.getElementById('pessoa').value;
                                    var rads = document.getElementsByName('opt');
                                    var status;

                                    for (var i = 0; i < rads.length; i++) {
                                        if (rads[i].checked) {
                                            status = rads[i].value;
                                        }
                                    }
                                    window.open("/bibliomaster/acao?parametro=csvMovimentacoes&status=" + status.toString() + "&pessoa=" + pessoa.toString(),"_self");
                                }
        </script>
    </body>
</html>