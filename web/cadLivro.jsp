<%-- 
    Document   : cadLivro
    Created on : 23/09/2016, 13:51:17
    Author     : ramon
--%>

<%@page import="entidade.Autor"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="entidade.Livro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Livro - Bibliomaster</title>
        <script src="jquery/jquery.min.js" type="text/javascript"></script>
        <script src="js/fileUpload.js" type="text/javascript"></script>
    </head>
    <body>
        <script src="js/mostraLabel.js" type="text/javascript"></script>
        <%@include file="menu.jsp" %>
        <%
            Livro livro = (Livro) request.getAttribute("livro");

            if (livro == null) {
                livro = new Livro();
            }
        %>
        <div class="container">

            <div class="panel-group">

                <div class="panel panel-default">

                    <div class="panel-heading">Cadastro de Livros</div>

                    <div class="panel-body">

                        <form action="" id="meuForm" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="id" value="<%= livro.getId()%>">
                            <div class="col-md-3">
                                <div class="row" style="text-align: center">
                                    <label>Capa</label>
                                    <img id="blah" <%if (livro.getCapa().isEmpty()) {
                                            livro.setCapa("capa/sem-foto.jpg");
                                        }%>src="<%= livro.getCapa()%>" class="img-responsive" alt="Your Image">
                                </div>
                                <br>
                                <div class="row input-group">
                                    <label class="input-group-btn">
                                        <span class="btn btn-primary">
                                            Procurar&hellip; <input type="file" style="display: none;" multiple id="imgInp" accept="image/png, image/jpeg" name="capa">
                                        </span>
                                    </label>
                                    <input type="text" class="form-control" readonly>
                                </div>
                            </div>
                            <div class="col-md-9">
                                <div class="form-group">
                                    <label>Título*</label>
                                    <input autofocus id="titulo" type="text" class="form-control" name="titulo" maxlength="100" placeholder="Insira o título" value="<%= livro.getTitulo()%>" required>
                                </div>

                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label>Data de Publicação*</label>
                                            <input id="publicacao" type="date" class="form-control" name="publicacao" <%Date dataAtual = new Date();
                                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                                String dataStr = sdf.format(dataAtual);%>max="<%= dataStr%>" value="<%= livro.getPublicacao()%>" required>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label>Nº de páginas</label>
                                            <input id="paginas" type="number" name="paginas" min="1" class="form-control" <%if (livro.getPaginas() != 0) {%>value="<%= livro.getPaginas()%><%}%>">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label>Edição</label>
                                            <input id="edicao" type="text" class="form-control" name="edicao" maxlength="8" placeholder="Insira a edição" value="<%= livro.getEdicao()%>">
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label>Localização*</label>
                                            <input id="localizacao" type="text" class="form-control" name="localizacao" maxlength="35" placeholder="Insira a localização" value="<%= livro.getLocalizacao()%>" required>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Editora*</label>
                                        <div class="editora row">
                                            <div class="col-md-3">
                                                <input id="editora" type="number" class="id form-control" name="editora" min="1" <%if (livro.getEditora().getId() != 0) {%>value="<%= livro.getEditora().getId()%><%}%>" required  onblur="buscaEntidade(this, 'Editora', 'Editora não encontrada!')">
                                            </div>
                                            <div class="col-md-9 input-group">
                                                <input id="nome" type="text" class="nome form-control" readonly value="<%= livro.getEditora().getNome()%>">
                                                <span class="input-group-btn">
                                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalBuscaEditora"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Idioma*</label>
                                            <div class="idioma row">
                                                <div class="col-md-3">
                                                    <input id="idioma" type="number" class="id form-control" name="idioma" min="1" <%if (livro.getIdioma().getId() != 0) {%>value="<%= livro.getIdioma().getId()%><%}%>" required onblur="buscaEntidade(this, 'Idioma', 'Idioma não encontrado!')">
                                                </div>
                                                <div class="col-md-9 input-group">
                                                    <input id="nome" type="text" class="nome form-control" readonly value="<%= livro.getIdioma().getNome()%>">
                                                    <span class="input-group-btn">
                                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalBuscaIdioma"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>



                                <div class="form-goup">


                                    <div class="row">
                                        <div class="col-md-6">
                                            <label>Autor(es)*</label>
                                            <div class="input_fields_wrap_autor">
                                                <div class="form-group">
                                                    <div class="autor row">
                                                        <div class="col-md-3">
                                                            <input id="autor" type="number" class="id form-control" name="autor" min="1" <% if (livro.getAutor().size() != 0) {%>value="<%= livro.getAutor().get(0).getId()%><%}%>" required onblur="buscaEntidade(this, 'Autor', 'Autor não encontrado!')">
                                                        </div>
                                                        <div class="col-md-9 input-group">
                                                            <input id="nome" type="text" class="nome form-control" readonly <% if (livro.getAutor().size() != 0) {%>value="<%= livro.getAutor().get(0).getNome() + " " + livro.getAutor().get(0).getSobrenome()%> <%}%>">
                                                            <span class="input-group-btn">
                                                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalBuscaAutor"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                                                            </span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <%for (int i = 1; i < livro.getAutor().size(); i++) {%>
                                                <div class="form-group">
                                                    <div class="autor row">
                                                        <div class="col-md-3">
                                                            <input id="autor" type="number" class="id form-control" name="autor" min="1" value="<%= livro.getAutor().get(i).getId()%>" required onblur="buscaEntidade(this, 'Autor', 'Autor não Encontrado')">
                                                        </div>
                                                        <div class="col-md-9 input-group">
                                                            <input id="nome" type="text" class="nome form-control" readonly value="<%= livro.getAutor().get(i).getNome() + " " + livro.getAutor().get(i).getSobrenome()%>">
                                                            <span class="input-group-btn">
                                                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalBuscaAutor"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                                                                <button class="remove_field btn btn-danger btn btn-danger"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                                                            </span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <%}%>
                                            </div>
                                            <button type="button" class="add_field_button_autor btn btn-primary">Adicionar Autor(a) <span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
                                        </div>
                                        <div class="col-md-6">
                                            <label>Gênero(s)*</label>
                                            <div class="input_fields_wrap_genero">
                                                <div class="form-group">
                                                    <div class="genero row">
                                                        <div class="col-md-3">
                                                            <input id="genero" type="number" class="id form-control" name="genero" min="1" <% if (livro.getGenero().size() != 0) {%>value="<%= livro.getGenero().get(0).getId()%><%}%>" required onblur="buscaEntidade(this, 'Genero', 'Gênero não encontrado!')">
                                                        </div>
                                                        <div class="col-md-9 input-group">
                                                            <input id="nome" type="text" class="nome form-control" readonly <% if (livro.getGenero().size() != 0) {%>value="<%= livro.getGenero().get(0).getNome()%> <%}%>">
                                                            <span class="input-group-btn">
                                                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalBuscaGenero"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                                                            </span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <%for (int i = 1; i < livro.getGenero().size(); i++) {%>
                                                <div class="form-group">
                                                    <div class="genero row">
                                                        <div class="col-md-3">
                                                            <input id="genero" type="number" class="id form-control" name="genero" min="1" required value="<%= livro.getGenero().get(i).getId()%>" onblur="buscaEntidade(this, 'Genero', 'Gênero não encontrado!')">
                                                        </div>
                                                        <div class="col-md-9 input-group">
                                                            <input id="nome" type="text" class="nome form-control" readonly value="<%= livro.getGenero().get(i).getNome()%>">
                                                            <span class="input-group-btn">
                                                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalBuscaGenero"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                                                                <button class="remove_field btn btn-danger btn btn-danger"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                                                            </span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <%}%>
                                            </div>
                                            <button type="button" class="add_field_button_genero btn btn-primary">Adicionar Gênero <span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="pull-right">
                                <label>* Campo Obrigatório</label>
                                <input type="button" onClick="location.href = 'cadLivro.jsp'" value="Cancelar" class="btn btn-default">
                                <button id="gravar" type="submit" class="btn btn-primary">Gravar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/adiciona_campos.js" type="text/javascript"></script>
        <script src="js/buscaEntidade.js" type="text/javascript"></script>
        <script>
                                    $(document).ready(function () {
                                        $("#meuForm").submit(function(e) {
                                            var formData = new FormData($('#meuForm')[0]);
                                            $.ajax({
                                                type: "POST",
                                                url: "/bibliomaster/acao?parametro=cadastraLivro",
                                                data: formData,
                                                processData: false,
                                                contentType: false
                                            }).done(function (retorno) {
                                                var resultado = $.trim(retorno);
                                                if (resultado.substring(0, 7) == "Sucesso") {
                                                    swal({   
                                                        title: resultado.substring(0, 8),   
                                                        text: resultado.substring(8, resultado.length),   
                                                        type: "success",   
                                                        showCancelButton: false,     
                                                        confirmButtonText: "Ok",   
                                                        closeOnConfirm: true }, 
                                                    function(){   window.location="cadLivro.jsp"; });
                                                } else {
                                                    swal(resultado.substring(0, 5), resultado.substring(5, resultado.length), "error")
                                                }
                                            });
                                            return false;
                                        }
                                        );
                                    });
        </script>
        <%@include file="modalBuscaEditora.jsp" %>
        <%@include file="modalBuscaIdioma.jsp" %>
        <%@include file="modalBuscaAutor.jsp" %>
        <%@include file="modalBuscaGenero.jsp" %>
        <%@include file="modalErro.jsp" %>

    </body>
</html>
