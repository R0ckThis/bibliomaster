<%-- 
    Document   : cadCopia_livro
    Created on : 07/10/2016, 14:02:39
    Author     : ramon
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Copia_livro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Situação de Livro - BiblioMaster</title>
        <script src="jquery/jquery.min.js" type="text/javascript"></script>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <%
            Copia_livro cl = (Copia_livro) request.getAttribute("cl");

            if (cl == null) {
                ArrayList<String> retorno = new ArrayList<>();
                retorno.add("Livro não encontrado!");
                request.setAttribute("retorno", retorno);
                request.setAttribute("paginaRetorno", "listaCopiaLivro.jsp");
                try {
                    RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
                    rd.forward(request, response);
                } catch (Exception e) {
                    System.out.println("erro no encaminhamento: " + e);
                }
            }
        %>
        <div class="container">

            <div class="panel-group">

                <div class="panel panel-default">

                    <div class="panel-heading">Editar Situação de Livro</div>

                    <div class="panel-body">

                        <form action="" id="meuForm" method="post">
                            <div class="form-group row">
                                <div class="col-md-9">
                                    <label>Livro</label>
                                    <input type="hidden" class="form-control" name="livro" value="<%= cl.getLivro().getId()%>">
                                    <input type="text" class="form-control" readonly value="<%= cl.getLivro().getTitulo() + " - " + cl.getLivro().getEditora().getNome()%>">
                                </div>
                                <div class="col-md-3">
                                    <label>Identificador</label>
                                    <input type="text" class="form-control" name="identificador" readonly value="<%= cl.getIdentificador()%>">
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Estado*</label>
                                <input type="text" class="form-control" name="estado" value="<%= cl.getEstado()%>" required>
                            </div>

                            <div class="form-group">
                                <label>Observação</label>
                                <textarea class="form-control" name="observacao" rows="3"><%= cl.getObservacao()%></textarea>
                            </div>

                            <div class="pull-right">
                                <label>* Campo Obrigatório</label>
                                <input type="button" onClick="location.href = 'listaCopiaLivro.jsp'" value="Cancelar" class="btn btn-default">
                                <button type="submit" class="btn btn-primary">Gravar</button>
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
                        url: "/bibliomaster/acao?parametro=editarCopiaLivro",
                        data: $('form').serialize()
                    }).done(function (retorno) {
                        var resultado = $.trim(retorno);
                        if (resultado.substring(0, 7) == "Sucesso") {
                            swal({
                                title: resultado.substring(0, 8),
                                text: resultado.substring(8, resultado.length),
                                type: "success",
                                showCancelButton: false,
                                confirmButtonText: "Ok",
                                closeOnConfirm: true},
                                    function () {
                                        window.location = "listaCopiaLivro.jsp";
                                    });
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
