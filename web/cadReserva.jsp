<%-- 
    Document   : cadMovimentacao
    Created on : 10/10/2016, 13:51:48
    Author     : ramon
--%>

<%@page import="entidade.Usuario"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reservas - BiblioMaster</title>
    </head>
    <%
        HttpSession sessao = ((HttpServletRequest) request).getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
    %>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel-heading">Reserva de Livros</div>
                    <div class="panel-body">
                        <form action="" id="meuForm" method="post">
                            <div class="form-group">
                                <label>Pessoa*</label>
                                <div class="pessoa row">
                                    <div class="form-group">
                                        <div class="col-md-3">
                                            <input type="number" class="id form-control" name="pessoa" min="1" required placeholder="ID Pessoa" <%if (usuario.getTipo().equals("Usuário")) {%> value="<%= usuario.getPessoa().getId()%>" readonly <%} else {%> onblur="buscaEntidade(this, 'Pessoa', 'Pessoa não encontrada!')"<%}%>>
                                        </div>
                                        <div class="col-md-9 <%if (usuario.getTipo().equals("Administrador")) {%> input-group <%}%>">
                                            <input type="text" class="nome form-control" readonly <%if (usuario.getTipo().equals("Usuário")) {%> value="<%= usuario.getPessoa().getNome() + " " + usuario.getPessoa().getSobrenome()%>"<%}%>>
                                            <%if (usuario.getTipo().equals("Administrador")) {%><span class="input-group-btn">
                                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalBuscaPessoa"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                                            </span><%}%>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Livro(s)* Caso repita o livro, apenas uma cópia será reservada!</label>
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
                                <label>Observação Reserva</label>
                                <textarea class="form-control" name="observacao" rows="3"></textarea>
                            </div>

                            <div class="col-md-3 row">
                                <div class="form-group">
                                    <label>Reservar para*</label>
                                    <input type="date" class="form-control" name="data_reserva" required <%Date data = new Date();
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                        String dataMin = sdf.format(data);
                                        data.setDate(data.getDate() + 7);
                                        String dataMax = sdf.format(data);%>min="<%= dataMin%>" max="<%= dataMax%>">
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
                                        $("#meuForm").submit(function (e) {
                                            $.ajax({
                                                type: "POST",
                                                url: "/bibliomaster/acao?parametro=reservarLivro",
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
