<%-- 
    Document   : cadGenero
    Created on : 05/09/2016, 10:22:01
    Author     : ramon
--%>

<%@page import="entidade.Genero"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Gênero - BiblioMaster</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <%
            Genero genero = (Genero) request.getAttribute("genero");

            if (genero == null) {
                genero = new Genero();
            }

        %>
        <div class="container">
            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel-heading">Cadastro de Gênero</div>
                    <div class="panel-body">
                        <form action="" id="meuForm" method="post">
                            <input id="id" type="hidden" name="id" value="<%= genero.getId()%>">
                            <div class="form-group">
                                <label>Nome do Gênero*</label>
                                <input autofocus id="nome" type="text" class="form-control" name="nome" maxlength="45" placeholder="Insira o nome" value="<%= genero.getNome()%>" required>
                            </div>
                            <div class="pull-right">
                                <label>* Campo Obrigatório</label>
                                <input type="button" onClick="location.href = 'cadGenero.jsp'" value="Cancelar" class="btn btn-default">
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
                        url: "/bibliomaster/acao?parametro=cadastraGenero",
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
