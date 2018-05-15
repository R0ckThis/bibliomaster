<%-- 
    Document   : cadIdioma
    Created on : 23/08/2016, 21:00:56
    Author     : ramon
--%>

<%@page import="entidade.Idioma"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Idiomas - BiblioMaster</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <%
            Idioma idioma = (Idioma) request.getAttribute("idioma");

            if (idioma == null) {
                idioma = new Idioma();
            }

        %>
        <div class="container">
            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel-heading">Cadastro de Idiomas</div>
                    <div class="panel-body">
                        <form action="/bibliomaster/acao?parametro=cadastraIdioma" id="meuForm" method="post">
                            <input id="id" type="hidden" name="id" value="<%= idioma.getId()%>">
                            <div class="form-group">
                                <label>Nome do idioma*</label>
                                <input autofocus id="nome" type="text" class="form-control" name="nome" maxlength="45" placeholder="Insira o nome" value="<%= idioma.getNome()%>" required>
                            </div>
                            <div class="form-group">
                                <label>Sigla*</label>
                                <input id="sigla" type="text" class="form-control" name="sigla" maxlength="6" placeholder="Insira a sigla" value="<%= idioma.getSigla()%>" required>
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
        <script>
            $(document).ready(function () {
                $("#meuForm").submit(function(e) {
                    $.ajax({
                        type: "POST",
                        url: "/bibliomaster/acao?parametro=cadastraIdioma",
                        data: $('form').serialize()
                    }).done(function (retorno) {
                        var resultado = $.trim(retorno);
                        if (resultado.substring(0, 7) == "Sucesso") {
                            swal(resultado.substring(0, 8), resultado.substring(8, resultado.length), "success")
                            $('#id').val("0");
                            $('#nome').val("");
                            $('#sigla').val("");
                            $('#nome').focus();
                        } else {
                            swal(resultado.substring(0, 5), resultado.substring(5, resultado.length), "error")
                        }
                    });
                    return false;
                }
                );
            });
        </script>
    </body>
</html>
