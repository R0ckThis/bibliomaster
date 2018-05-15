<%-- 
    Document   : visualizaMovimentacao
    Created on : 17/10/2016, 08:16:21
    Author     : ramon
--%>

<%@page import="util.Formatacao"%>
<%@page import="entidade.Movimentacao_has_livro"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Movimentacao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualizar Movimentação - BiblioMaster</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <%
                Movimentacao mov = (Movimentacao) request.getAttribute("movimentacao");

                double valorMulta = 0;
                if (mov == null) {
                    ArrayList<String> retorno = new ArrayList<>();
                    retorno.add("Movimentação não encontrada!");
                    request.setAttribute("retorno", retorno);
                    request.setAttribute("paginaRetorno", "listaMovimentacao.jsp");
                    try {
                        RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
                        rd.forward(request, response);
                    } catch (Exception e) {
                        System.out.println("erro no encaminhamento: " + e);
                    }
                } else {
                    for (Movimentacao_has_livro mhl : mov.getLivros()) {
                        valorMulta = valorMulta + mhl.getMulta();
                    }
                }
            %>
            <div class="panel-group">
                <div class="panel panel-success">
                    <div class="panel-heading"><%= request.getAttribute("mensagem")%></div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label>Id Movimentação</label>
                                    <input type="text" class="id form-control" readonly value="<%= mov.getId()%>">
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label>Status Movimentação</label>
                                    <input type="text" class="id form-control" readonly value="<%= mov.getStatus()%>">
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="form-group">
                                    <label>Pessoa</label>
                                    <input type="text" class="nome form-control" readonly value="<%= mov.getPessoa().getNome() + " " + mov.getPessoa().getSobrenome()%>">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Reservado para</label>
                                    <input type="text" class="form-control" name="data_reserva" readonly <% if (mov.getData_reserva() != null) {%>value="<%= Formatacao.dateParaString(mov.getData_reserva())%>"<%}%>>
                                </div>
                            </div>
                        </div>


                        <div class="form-group">
                            <label>Livro(s):</label>
                            <%for (Movimentacao_has_livro livro : mov.getLivros()) {%>
                            <div class="livro">
                                <div class="row">
                                    <div class="form-group row">
                                        <div class="col-md-7">
                                            <div class="col-md-3">
                                                <label>Status</label>
                                                <input type="text" class="form-control" name="livro" readonly value="<%= livro.getStatus()%>">
                                            </div>
                                            <div class="col-md-2">
                                                <label>Id</label>
                                                <input type="text" class="form-control" name="livro" readonly value="<%= livro.getCopia_livro().getLivro().getId()%>">
                                            </div>
                                            <div class="col-md-5">
                                                <label>Título - Editora</label>
                                                <input type="text" class="nome form-control" readonly value="<%= livro.getCopia_livro().getLivro().getTitulo() + " - " + livro.getCopia_livro().getLivro().getEditora().getNome()%>">
                                            </div>
                                            <div class="col-md-2">
                                                <label>Identificador</label>
                                                <input type="text" class="form-control" name="identificador" readonly value="<%= livro.getCopia_livro().getIdentificador()%>">
                                            </div>
                                        </div>
                                        <div class="col-md-5">
                                            <div class="col-md-6">
                                                <label>Data devolução</label>
                                                <input type="text" class="form-control" name="data_devolucao" readonly <% if (livro.getData_devolucao() != null) {%> value="<%= Formatacao.dateParaString(livro.getData_devolucao())%>"<%}%>>
                                            </div>
                                            <div class="col-md-6">
                                                <label>Multa</label>
                                                <input type="number" class="form-control" name="multa" value="<%= livro.getMulta()%>" readonly>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label>Observação Livro</label>
                                    <textarea class="form-control" name="observacaoLivro" rows="3" readonly><%= livro.getObservacao()%></textarea>
                                </div>
                            </div>
                            <%}%>
                        </div>
                        <div class="form-group">
                            <label>Observação</label>
                            <textarea class="form-control" name="observacao" rows="3" readonly><%= mov.getObservacao()%></textarea>
                        </div>
                        <div class="row">
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label>Data Retirada</label>
                                    <input type="text" class="form-control" name="data_retirada" readonly <% if (mov.getData_retirada() != null) {%> value="<%= Formatacao.dateParaString(mov.getData_retirada())%>" <%}%>>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label>Data para Devolução</label>
                                    <input type="text" class="form-control" name="data_devolver" readonly <% if (mov.getData_devolver() != null) {%> value="<%= Formatacao.dateParaString(mov.getData_devolver())%>"<%}%>>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label>Valor Retirada</label>
                                    <input type="number" class="form-control" name="valor" readonly value="<%= mov.getValor()%>">
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label>Soma Multas</label>
                                    <input type="number" class="form-control" name="valor" readonly value="<%= valorMulta%>">
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label>Valor Total</label>
                                    <input type="number" class="form-control" name="valor" readonly value="<%= mov.getValor() + valorMulta%>">
                                </div>
                            </div>
                            <div class="col-md-2" style="text-align: center">
                                <div class="form-group hidden-print bottom">
                                    <div class="row">
                                        <label>Imprimir</label>
                                    </div>
                                    <button type="button" onclick="javascript:window.print()" class="btn btn-info"><span class="glyphicon glyphicon-print" aria-hidden="true"></span></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
