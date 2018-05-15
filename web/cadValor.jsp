<%-- 
    Document   : cadValor
    Created on : 11/10/2016, 14:44:04
    Author     : ramon
--%>

<%@page import="dao.ValorDAO"%>
<%@page import="entidade.Valor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Valores - BiblioMaster</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <%
            Valor valor = new ValorDAO().pegarValor();
        %>
        <div class="container">
            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel-heading">Cadastro de Valores</div>
                    <div class="panel-body">
                        <form action="" id="meuForm" method="post">
                            <div class="form-group">
                                <label>Diária*</label>
                                <input autofocus id="diaria" type="number" class="form-control" name="diaria" min="0" value="<%= valor.getDiaria()%>" required>
                            </div>
                            <div class="form-group">
                                <label>Multa*</label>
                                <input id="multa" type="number" class="form-control" name="multa" min="0" value="<%= valor.getMulta()%>" required>
                            </div>
                            <div class="pull-right">
                                <label>* Campo Obrigatório</label>
                                <input type="button" onClick="location.href = 'cadValor.jsp'" value="Cancelar" class="btn btn-default">
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
                        url: "/bibliomaster/acao?parametro=cadastraValor",
                        data: $('form').serialize()
                    }).done(function (retorno) {
                        var resultado = $.trim(retorno);
                        if (resultado.substring(0, 7) == "Sucesso") {
                            swal(resultado.substring(0, 8), resultado.substring(8, resultado.length), "success")
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
