<%-- 
    Document   : index
    Created on : 16/08/2016, 19:48:59
    Author     : fabricio.pretto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <title>Login - BiblioMaster</title>
        <script src="jquery/jquery.maskedinput.js" type="text/javascript"></script>
        <script>

            function mascara(o, f) {
                v_obj = o
                v_fun = f
                setTimeout("execmascara()", 1)
            }

            function execmascara() {
                v_obj.value = v_fun(v_obj.value)
            }

            function mascaraCpf(v) {
                v = v.replace(/\D/g, "")                    //Remove tudo o que não é dígito
                v = v.replace(/(\d{3})(\d)/, "$1.$2")       //Coloca um ponto entre o terceiro e o quarto dígitos
                v = v.replace(/(\d{3})(\d)/, "$1.$2")       //Coloca um ponto entre o terceiro e o quarto dígitos
                //de novo (para o segundo bloco de números)
                v = v.replace(/(\d{3})(\d{1,2})$/, "$1-$2") //Coloca um hífen entre o terceiro e o quarto dígitos
                return v
            }
        </script>
    </head>
    <body>
        <div class="jumbotron text-center">
            <h1>BiblioMaster</h1>
            <p>Sistema para Biblioteca </p>
        </div>
        <div class="container">
            <div class="panel-group">
                <div class="panel panel-primary" style="max-width: 330px; margin: 0 auto;">
                    <div class="panel-heading">Faça seu Login</div>
                    <div class="panel-body">
                        <%if (request.getAttribute("erro") != null) {%>
                        <div class="alert alert-danger" role="alert" style="text-align: center;">
                            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                            <span class="sr-only">Error:</span>
                            Usuário ou Senha Incorreto(s)!
                        </div>
                        <%}%>
                        <form action="/bibliomaster/acao?parametro=login" id="meuForm" method="post">
                            <div class="form-group">
                                <label for="nome">CPF:</label>
                                <input type="text" name="cpf" maxlength="14" class="form-control" placeholder="000.000.000-00" onkeypress="mascara(this, mascaraCpf)"/>
                            </div>
                            <div class="form-group">
                                <label for="sigla">Senha:</label>
                                <input type="password" class="form-control" name="senha" placeholder="Insira a senha">
                            </div>
                            <div class="pull-right">
                                <a href="cadUsuario.jsp" class="btn btn-default">Fazer Cadastro</a>
                                <button type="submit" class="btn btn-primary">Acessar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
