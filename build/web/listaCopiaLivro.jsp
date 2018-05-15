<%-- 
    Document   : listaCopiaLivro
    Created on : 07/10/2016, 15:35:53
    Author     : ramon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listagem de Cópias de Livros - BiblioMaster</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <h1>Listagem de Cópias Livro</h1>

            <label>Livro*</label>
            <div class="livro row">
                <div class="form-group">
                    <div class="col-md-3">
                        <input id="criterioCopias" type="number" class="id form-control" name="livro" min="1" required onblur="buscaEntidade(this, 'Livro', 'Livro não encontrado!')">
                    </div>
                    <div class="col-md-9 input-group">
                        <input type="text" class="nome form-control" readonly>
                        <span class="input-group-btn">
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalBuscaLivro"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                        </span>
                    </div>
                </div>
            </div>

            <div id="Copias">
                <h3 style="text-align: center;">Escolha o Livro!</h3>
            </div>


        </div>
        <script src="js/consultaEntidade.js" type="text/javascript"></script>
        <%@include file="modalBuscaLivro.jsp" %>
        <%@include file="modalErro.jsp" %>
        <script>
                    (function () {
                        $('#modalBuscaLivro').on('hide.bs.modal', function () {
                            consultaEntidade('Copias'); // Button that triggered the modal
                        });
                    })();

                    function buscaEntidade(div, tipo, mensagem) {
                        var xhttp;
                        xhttp = new XMLHttpRequest();
                        var form = $(div).parent().parent().parent();
                        xhttp.onreadystatechange = function () {
                            if (this.readyState === 4 && this.status === 200) {
                                if (this.responseText !== "") {
                                    form.find('.nome').val(this.responseText);
                                    consultaEntidade('Copias');
                                } else {
                                    form.find('.id').val("");
                                    form.find('.nome').val("");
                                    consultaEntidade('Copias');
                                    $('#modalErro').find('.mensagem').text(mensagem);
                                    $('#modalErro').modal('show');
                                }
                            }
                        };
                        if (div.value !== "") {
                            xhttp.open("GET", "/bibliomaster/acao?parametro=buscar" + tipo + "&id=" + div.value, true);
                            xhttp.send();
                        } else {
                            form.find('.nome').val("");
                            consultaEntidade('Copias');
                            $('#modalErro').find('.mensagem').text(mensagem);
                            $('#modalErro').modal('show');
                        }
                    }
                    ;
        </script>
    </body>
</html>
