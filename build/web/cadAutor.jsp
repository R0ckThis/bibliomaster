<%-- 
    Document   : cadAutor
    Created on : 05/09/2016, 13:53:44
    Author     : ramon
--%>

<%@page import="entidade.Autor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Autor - BiblioMaster</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <%
            Autor autor = (Autor) request.getAttribute("autor");

            if (autor == null) {
                autor = new Autor();
            }

        %>
        <div class="container">
            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel-heading">Cadastro de Autor</div>
                    <div class="panel-body">
                        <form action="/bibliomaster/acao?parametro=cadastraAutor" id="meuForm" method="post">
                            <input id="id" type="hidden" name="id" value="<%= autor.getId()%>">
                            <div class="form-group">
                                <label>Nome*</label>
                                <input autofocus id="nome" type="text" class="form-control" name="nome" maxlength="30" placeholder="Insira o nome" value="<%= autor.getNome()%>" required>
                            </div>
                            <div class="form-group">
                                <label>Sobrenome*</label>
                                <input id="sobrenome" type="text" class="form-control" name="sobrenome" maxlength="60" placeholder="Insira o sobrenome" value="<%= autor.getSobrenome()%>" required>
                            </div>
                            <div class="pull-right">
                                <label>* Campo Obrigatório</label>
                                <input type="button" onClick="location.href = 'cadAutor.jsp'" value="Cancelar" class="btn btn-default">
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
                        url: "/bibliomaster/acao?parametro=cadastraAutor",
                        data: $('form').serialize()
                    }).done(function (retorno) {
                        var resultado = $.trim(retorno);
                        if (resultado.substring(0, 7) == "Sucesso") {
                            swal(resultado.substring(0, 8), resultado.substring(8, resultado.length), "success")
                            $('#id').val("0");
                            $('#nome').val("");
                            $('#sobrenome').val("");
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
