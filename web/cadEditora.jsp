<%-- 
    Document   : cadEditora
    Created on : 25/08/2016, 20:00:36
    Author     : ramon
--%>

<%@page import="entidade.Editora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Editora - BiblioMaster</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <%
            Editora editora = (Editora) request.getAttribute("editora");

            if (editora == null) {
                editora = new Editora();
            }

        %>
        <div class="container">
            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel-heading">Cadastro de Editora</div>
                    <div class="panel-body">
                        <form action="" id="meuForm" method="post">
                            <input id="id" type="hidden" name="id" value="<%= editora.getId()%>">
                            <div class="form-group">
                                <label>Nome da Editora*</label>
                                <input autofocus id="nome" type="text" class="form-control" name="nome" maxlength="50" placeholder="Insira o nome" value="<%= editora.getNome()%>" required>
                            </div>
                            <div class="pull-right">
                                <label>* Campo Obrigatório</label>
                                <input type="button" onClick="location.href = 'cadEditora.jsp'" value="Cancelar" class="btn btn-default">
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
                        url: "/bibliomaster/acao?parametro=cadastraEditora",
                        data: $('form').serialize()
                    }).done(function (retorno) {
                        var resultado = $.trim(retorno);
                        if (resultado.substring(0, 7) == "Sucesso") {
                            swal(resultado.substring(0, 8), resultado.substring(8, resultado.length), "success")
                            $('#id').val("0");
                            $('#nome').val("");
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
