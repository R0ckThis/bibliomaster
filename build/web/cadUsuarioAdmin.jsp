<%-- 
    Document   : cadUsuario
    Created on : 18/10/2016, 14:47:27
    Author     : ramon
--%>

<%@page import="entidade.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Usuários - BiblioMaster</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <%
            Usuario usuario = (Usuario) request.getAttribute("usuario");
            String editar = "true";
            if (usuario == null) {
                usuario = new Usuario();
                editar = "false";
            }

        %>
        <div class="container">
            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel-heading">Cadastro de Usuário</div>
                    <div class="panel-body">
                        <form action="" id="meuForm" method="post">
                            <input type="hidden" name="editar" value="<%= editar%>">
                            <div class="form-group">
                                <label>Pessoa*</label>
                                <div class="pessoa row">
                                    <div class="form-group">
                                        <div class="col-md-3">
                                            <input type="number" class="id form-control" name="pessoa" min="1" required placeholder="ID Pessoa" <%if (editar.equals("true")) {%> value="<%= usuario.getPessoa().getId()%>" readonly<%} else {%> onblur="buscaEntidade(this, 'Pessoa', 'Pessoa não encontrada!')"<%}%>>
                                        </div>
                                        <div class="col-md-9 <%if (editar.equals("false")) {%> input-group <%}%>">
                                            <input type="text" class="nome form-control" readonly <%if (editar.equals("true")) {%> value="<%= usuario.getPessoa().getNome() + " " + usuario.getPessoa().getSobrenome()%>" <%}%>>
                                            <%if (editar.equals("false")) {%><span class="input-group-btn">
                                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalBuscaPessoa"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                                            </span><%}%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Senha<%if (editar.equals("false")) {%>*<%}%></label>
                                <input type="password" class="form-control" name="senha" maxlength="20" <%if (editar.equals("false")) {%> placeholder="Insira a senha" required<%} else {%> placeholder="Deixe em branco para manter"<%}%>>
                            </div>
                            <div class="form-group">
                                <label>Tipo*</label>
                                <label class="radio-inline"><input type="radio" name="tipo" value="Administrador" <%if (usuario.getTipo().equals("Administrador")) {%>checked="checked"<%}%>>Administrador</label>
                                <label class="radio-inline"><input type="radio" name="tipo" value="Usuário" <%if (usuario.getTipo().equals("Usuário")) {%>checked="checked"<%}%>>Usuário</label>
                            </div>
                            <div class="form-group">
                                <label>Ativo*</label>
                                <input type="checkbox" <%if (usuario.isAtivo()) {%> checked="checked" <%}%> name="ativo">
                            </div>
                            <div class="pull-right">
                                <label>* Campo Obrigatório</label>
                                <input type="button" onClick="location.href = 'cadUsuario.jsp'" value="Cancelar" class="btn btn-default">
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
                                        $("#meuForm").submit(function (e) {
                                            $.ajax({
                                                type: "POST",
                                                url: "/bibliomaster/acao?parametro=cadastraUsuario",
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
                                                                window.location = "cadUsuarioAdmin.jsp";
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
        <%@include file="modalBuscaPessoa.jsp" %>
    </body>
</html>
