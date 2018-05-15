<%-- 
    Document   : cadRetirada
    Created on : 11/10/2016, 15:11:15
    Author     : ramon
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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
            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel-heading">Retirada de Livros</div>
                    <div class="panel-body">
                        <form action="" id="meuForm" method="post">
                            <div class="form-group">
                                <label>Pessoa*</label>
                                <div class="pessoa row">
                                    <div class="form-group">
                                        <div class="col-md-3">
                                            <input type="number" class="id form-control" name="pessoa" min="1" required placeholder="ID Pessoa" onblur="buscaEntidade(this, 'Pessoa', 'Pessoa não encontrada!')">
                                        </div>
                                        <div class="col-md-9 input-group">
                                            <input type="text" class="nome form-control" readonly>
                                            <span class="input-group-btn">
                                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalBuscaPessoa"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                                            </span>
                                        </div>
                                    </div>
                                </div> </div>

                            <div class="form-group">
                                <label>Livro(s)* Caso repita o livro, apenas uma cópia será retirada!</label>
                                <div class="input_fields_wrap_livro">
                                    <div class="livro">
                                        <div class="row">
                                            <div class="form-group">
                                                <div class="col-md-3">
                                                    <input type="number" class="id form-control" name="livro" min="1" required placeholder="ID Livro" onblur="buscaEntidade(this, 'Livro', 'Livro não encontrado!')">
                                                </div>
                                                <div class="col-md-9 input-group">
                                                    <input type="text" class="nome form-control" readonly>
                                                    <span class="input-group-btn">
                                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalBuscaLivro"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                                                    </span>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>Observação Livro</label>
                                            <textarea class="form-control" name="observacaoLivro" rows="3"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <button type="button" class="add_field_button_livro btn btn-primary">Adicionar Livro <span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
                            </div>
                            <div class="form-group">
                                <label>Observação Retirada</label>
                                <textarea class="form-control" name="observacao" rows="3"></textarea>
                            </div>
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>Data Retirada*</label>
                                        <input type="date" class="form-control" name="data_retirada" <%Date dataAtual = new Date();
                                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                            String dataStr = sdf.format(dataAtual);%>max="<%= dataStr%>" value="<%= dataStr%>" required>
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
                                <input type="button" onClick="location.href = 'cadIdioma.jsp'" value="Cancelar" class="btn btn-default">
                                <button id="gravar" type="submit" class="btn btn-primary">Gravar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/buscaEntidade.js" type="text/javascript"></script>
        <script src="js/adiciona_livro.js" type="text/javascript"></script>
        <script>
            $(document).ready(function () {
                $("#meuForm").submit(function(e) {
                    $.ajax({
                        type: "POST",
                        url: "/bibliomaster/acao?parametro=retirarLivro",
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
        <%@include file="modalErro.jsp" %>
        <%@include file="modalBuscaLivro.jsp" %>
        <%@include file="modalBuscaPessoa.jsp" %>
    </body>
</html>
