<%-- 
    Document   : relLivros
    Created on : 22/11/2016, 09:35:00
    Author     : ramon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Relatório de Livros</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel-heading">Relatório de Livros</div>
                    <div class="panel-body">
                        <form action="/bibliomaster/acao?parametro=relLivros" id="meuForm" method="post">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label class="radio-inline"><input type="radio" name="tipo" checked="checked" value="Retirado">Retirados</label>
                                    <label class="radio-inline"><input type="radio" name="tipo" value="Estoque">Em estoque</label>
                                    <label class="radio-inline"><input type="radio" name="tipo" value="Todos">Todos</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <input type="submit" value="Gerar PDF" class="btn btn-primary">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
