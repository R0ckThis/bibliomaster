<%-- 
    Document   : cadCopia_livro
    Created on : 07/10/2016, 14:02:39
    Author     : ramon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Incluir cópias de um Livro - BiblioMaster</title>
        <script src="jquery/jquery.min.js" type="text/javascript"></script>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">

            <div class="panel-group">

                <div class="panel panel-default">

                    <div class="panel-heading">Incluir cópia de Livros</div>

                    <div class="panel-body">

                        <form action="" id="meuForm" method="post">
                            <label>Livro*</label>
                            <div class="livro row">
                                <div class="form-group">
                                    <div class="col-md-3">
                                        <input id="livro" type="number" class="id form-control" name="livro" min="1" required  onblur="buscaEntidade(this, 'Livro', 'Livro não encontrado!')">
                                    </div>
                                    <div class="col-md-9 input-group">
                                        <input id="nome" type="text" class="nome form-control" readonly>
                                        <span class="input-group-btn">
                                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalBuscaLivro"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                                        </span>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Quantidade*</label>
                                <input id="quantidade" type="number" class="form-control" name="quantidade" min="1" required>
                            </div>
                            <div class="pull-right">
                                <label>* Campo Obrigatório</label>
                                <input type="button" onClick="location.href = 'cadCopia_livro.jsp'" value="Cancelar" class="btn btn-default">
                                <button id="gravar" type="submit" class="btn btn-primary">Gravar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/buscaEntidade.js" type="text/javascript"></script>
        <script>
            $(document).ready(function () {
                $("#meuForm").submit(function(e) {
                    $.ajax({
                        type: "POST",
                        url: "/bibliomaster/acao?parametro=cadastraCopiaLivro",
                        data: $('form').serialize()
                    }).done(function (retorno) {
                        var resultado = $.trim(retorno);
                        if (resultado.substring(0, 7) == "Sucesso") {
                            swal(resultado.substring(0, 8), resultado.substring(8, resultado.length), "success")
                            $('#livro').val("");
                            $('#nome').val("");
                            $('#quantidade').val("");
                        } else {
                            swal(resultado.substring(0, 5), resultado.substring(5, resultado.length), "error")
                        }
                    });
                    return false;
                }
                );
            });
        </script>
        <%@include file="modalErro.jsp" %>
        <%@include file="modalBuscaLivro.jsp" %>
    </body>
</html>
