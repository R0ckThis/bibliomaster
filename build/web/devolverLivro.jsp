<%-- 
    Document   : devolverLivro
    Created on : 14/10/2016, 11:04:01
    Author     : ramon
--%>

<%@page import="util.Formatacao"%>
<%@page import="entidade.Movimentacao_has_livro"%>
<%@page import="util.Util"%>
<%@page import="dao.ValorDAO"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Movimentacao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Devolução - BiblioMaster</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <%
                Movimentacao mov = (Movimentacao) request.getAttribute("movimentacao");
                Date dataAtual = new Date();
                ValorDAO valorDAO = new ValorDAO();

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
                }
            %>
            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel-heading">Devolução de Livros</div>
                    <div class="panel-body">
                        <form action="" id="meuForm" method="post" onsubmit="removeDisabled()">
                            <input type="hidden" class="form-control" name="id" value="<%= mov.getId()%>">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Pessoa</label>
                                        <input type="hidden" class="id form-control" name="pessoa" value="<%= mov.getPessoa().getId()%>">
                                        <input type="text" class="nome form-control" readonly value="<%= mov.getPessoa().getNome() + " " + mov.getPessoa().getSobrenome()%>">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Reservado para</label>
                                        <input type="text" class="form-control" name="data_reserva" readonly <% if (mov.getData_reserva() != null) {%>value="<%= Formatacao.dateParaString(mov.getData_reserva())%>"<%}%>>
                                    </div>
                                </div>
                            </div>


                            <div class="form-group">
                                <label>Livro(s):</label>
                                <div class="input_fields_wrap_livro">
                                    <%int i = 0;
                                        for (Movimentacao_has_livro livro : mov.getLivros()) {
                                            if (livro.getStatus().equals("Retirado") || livro.getStatus().equals("Devolvido")) {%>
                                    <div class="livro">
                                        <div class="row">
                                            <div class="form-group row">
                                                <div class="col-md-7">
                                                    <div class="col-md-3">
                                                        <label>Id</label>
                                                        <input type="text" class="form-control" name="livro" readonly value="<%= livro.getCopia_livro().getLivro().getId()%>">
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label>Título - Editora</label>
                                                        <input type="text" class="nome form-control" readonly value="<%= livro.getCopia_livro().getLivro().getTitulo() + " - " + livro.getCopia_livro().getLivro().getEditora().getNome()%>">
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label>Identificador</label>
                                                        <input type="text" class="form-control" name="identificador" readonly value="<%= livro.getCopia_livro().getIdentificador()%>">
                                                    </div>
                                                </div>
                                                <div class="col-md-5">
                                                    <div class="col-md-3" style="text-align: center;">
                                                        <div class="row">
                                                            <label>Devolver</label>
                                                        </div>
                                                        <div class="row">
                                                            <input type="checkbox" checked="checked" id="devolver<%= i%>" name="devolver<%= i%>" <%if (livro.getStatus().equals("Devolvido")) {%>disabled<%}%>>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-5">
                                                        <label>Data devolução</label>
                                                        <input type="date" class="form-control" name="data_devolucao" readonly <%if (livro.getStatus().equals("Devolvido")) {%> value="<%= livro.getData_devolucao()%>"<%} else {
                                                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                                            String dataStr = sdf.format(dataAtual);%> value="<%= dataStr%>" <%}%>>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label>Multa</label>
                                                        <input type="number" class="form-control" name="multa" <%if (livro.getStatus().equals("Devolvido")) {%>value="<%= livro.getMulta()%>" readonly <%} else {
                                                            double multa = 0;
                                                            if (dataAtual.after(mov.getData_devolver())) {
                                                                multa = valorDAO.pegarValor().getMulta() * (Util.calculaDiasUteis(mov.getData_devolver(), dataAtual));
                                                            }%> value="<%= multa%>" <%}%>>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>Observação Livro</label>
                                            <textarea class="form-control" name="observacaoLivro" rows="3"><%= livro.getObservacao()%></textarea>
                                        </div>
                                    </div>
                                    <%i++;
                                            }
                                        }%>
                                    <input type="hidden" class="form-control" name="qtdeLivros" value="<%= i%>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Observação</label>
                                <textarea class="form-control" name="observacao" rows="3"><%= mov.getObservacao()%></textarea>
                            </div>
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>Data Retirada</label>
                                        <input type="date" class="form-control" name="data_retirada" readonly value="<%= mov.getData_retirada()%>">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>Data para Devolução</label>
                                        <input type="date" class="form-control" name="data_devolver" readonly value="<%= mov.getData_devolver()%>">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>Valor Retirada</label>
                                        <input type="number" class="form-control" name="valor" readonly value="<%= mov.getValor()%>">
                                    </div>
                                </div>
                            </div>

                            <div class="pull-right">
                                <label>* Campo Obrigatório</label>
                                <input type="button" onClick="location.href = 'listaMovimentacao.jsp'" value="Cancelar" class="btn btn-default">
                                <button type="submit" class="btn btn-primary">Gravar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function removeDisabled() {
                var qtd = <%= i%>;
                for (var i = 0; i < qtd; i++) {
                    var checkbox = document.getElementById('devolver'+i);
                    checkbox.disabled = false;
                }
            }
            ;
            
            $(document).ready(function () {
                $("#meuForm").submit(function(e) {
                    $.ajax({
                        type: "POST",
                        url: "/bibliomaster/acao?parametro=devolverLivro",
                        data: $('form').serialize()
                    }).done(function (retorno) {
                        var resultado = $.trim(retorno);
                        if (resultado.substring(0, 4) == "Erro") {
                            swal(resultado.substring(0, 5), resultado.substring(5, resultado.length), "error")
                        } else {
                            window.location = retorno;
                        }
                    });
                    return false;
                }
                );
            });
        </script>
    </body>
</html>
