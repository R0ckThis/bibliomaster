<%-- 
    Document   : retirarReserva
    Created on : 13/10/2016, 14:57:05
    Author     : ramon
--%>

<%@page import="entidade.Movimentacao_has_livro"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Movimentacao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Retiradas - BiblioMaster</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <%
                Movimentacao mov = (Movimentacao) request.getAttribute("movimentacao");

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
                    <div class="panel-heading">Retirada de Livros</div>
                    <div class="panel-body">
                        <form action="" id="meuForm" method="post">
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
                                        <input type="date" class="form-control" name="data_reserva" readonly value="<%= mov.getData_reserva()%>">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Livro(s):</label>
                                <div class="input_fields_wrap_livro">
                                    <%int i = 0;
                                        for (Movimentacao_has_livro livro : mov.getLivros()) {%>
                                    <div class="livro">
                                        <div class="row">
                                            <div class="form-group">
                                                <div class="col-md-2">
                                                    <label>Id</label>
                                                    <input type="text" class="form-control" name="livro" readonly value="<%= livro.getCopia_livro().getLivro().getId()%>">
                                                </div>
                                                <div class="col-md-4">
                                                    <label>Título - Editora</label>
                                                    <input type="text" class="nome form-control" readonly value="<%= livro.getCopia_livro().getLivro().getTitulo() + " - " + livro.getCopia_livro().getLivro().getEditora().getNome()%>">
                                                </div>
                                                <div class="col-md-3">
                                                    <label>Identificador</label>
                                                    <input type="text" class="form-control" name="identificador" readonly value="<%= livro.getCopia_livro().getIdentificador()%>">
                                                </div>

                                                <div class="col-md-3" style="text-align: center;">
                                                    <div class="row">
                                                        <label>Retirar</label>
                                                    </div>
                                                    <div class="row">
                                                        <input type="checkbox" checked="checked" name="retirar<%= i%>">
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
                                        <label>Data Retirada*</label>
                                        <input type="date" class="form-control" name="data_retirada" <%Date dataAtual = new Date();
                                            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                                            String dataStr = sdf1.format(dataAtual);%>max="<%= dataStr%>" value="<%= dataStr%>" required>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>Data Devolução*</label>
                                        <input type="date" class="form-control" name="data_devolver" required <%Date data = new Date();
                                            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                                            String dataMin = sdf2.format(data);
                                            data.setDate(data.getDate() + 7);
                                            String dataDevPadrao = sdf2.format(data);
                                            data.setDate(data.getDate() + 7);
                                            String dataMax = sdf2.format(data);%>min="<%= dataMin%>" value="<%= dataDevPadrao%>" max="<%= dataMax%>">
                                    </div>
                                </div>
                            </div>

                            <div class="pull-right">
                                <label>* Campo Obrigatório</label>
                                <input type="button" onClick="location.href = 'listaMovimentacao.jsp'" value="Cancelar" class="btn btn-default">
                                <button id="gravar" type="submit" class="btn btn-primary">Gravar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function () {
                $("#meuForm").submit(function (e) {
                    $.ajax({
                        type: "POST",
                        url: "/bibliomaster/acao?parametro=retirarReservaLivro",
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
