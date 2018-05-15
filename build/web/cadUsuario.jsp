<%-- 
    Document   : cadUsuario
    Created on : 19/10/2016, 14:55:43
    Author     : ramon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Usuário - Bibliomaster</title>
        <script src="jquery/jquery.min.js" type="text/javascript"></script>
        <script src="jquery/jquery.maskedinput.js" type="text/javascript"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link href="css/sweetalert.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/sweetalert.min.js" type="text/javascript"></script>
        <script>

            function mascara(o, f) {
                v_obj = o
                v_fun = f
                setTimeout("execmascara()", 1)
            }

            function execmascara() {
                v_obj.value = v_fun(v_obj.value)
            }

            function mascaraTelefone(v) {
                v = v.replace(/\D/g, "")                 //Remove tudo o que não é dígito
                v = v.replace(/^(\d\d)(\d)/g, "($1) $2") //Coloca parênteses em volta dos dois primeiros dígitos
                return v
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
        <br>
        <div class="container">

            <div class="panel-group">

                <div class="panel panel-default">

                    <div class="panel-heading">Cadastro de Usuário</div>

                    <div class="panel-body">

                        <form action="" id="meuForm" method="post">

                            <div class="row">

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Nome*</label>
                                        <input autofocus type="text" class="form-control" name="nome" maxlength="30" placeholder="Insira o nome" required>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Sobrenome*</label>
                                        <input type="text" class="form-control" name="sobrenome" maxlength="60" placeholder="Insira o sobrenome" required>
                                    </div>
                                </div>
                            </div>

                            <div class="row">

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>CPF*</label>
                                        <input type="text" name="cpf" maxlength="14" class="form-control" placeholder="000.000.000-00" required onkeypress="mascara(this, mascaraCpf)"/>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>RG*</label>
                                        <input type="text" class="form-control" name="rg" maxlength="11" placeholder="Insira o RG" required>
                                    </div>
                                </div>
                            </div>
                            <div class="row">

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Email</label>
                                        <input type="email" class="form-control" name="email" maxlength="150" placeholder="exemplo@email.com">
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Senha*</label>
                                        <input type="password" class="form-control" name="senha" maxlength="20" placeholder="Insira a senha" required>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <button type="button" class="add_field_button btn btn-primary">Adicionar Telefone <span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
                            </div>

                            <div class="form-goup">
                                <div class="input_fields_wrap">

                                </div>
                            </div>

                            <div class="pull-right">
                                <label>* Campo Obrigatório</label>
                                <input type="button" onClick="location.href = 'index.jsp'" value="Cancelar" class="btn btn-default">
                                <button id="gravar" type="submit" class="btn btn-primary">Gravar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function () {
                var max_fields = 5; //maximum input boxes allowed
                var wrapper = $(".input_fields_wrap"); //Fields wrapper
                var add_button = $(".add_field_button"); //Add button ID

                var x = 0; //initlal text box count
                $(add_button).click(function (e) { //on add input button click
                    e.preventDefault();
                    if (x < max_fields) { //max input box allowed
                        x++; //text box increment
                        $(wrapper).append('<div><div class="input-group col-md-3">' +
                                '<input id="telefone" class="form-control" name="telefone" maxlength="14" type="text" placeholder="Número" onkeypress="mascara(this, mascaraTelefone)" required/>' +
                                '<div class="input-group-btn">' +
                                '<button class="remove_field btn btn-danger btn btn-danger"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>' +
                                '</div>' +
                                '</div>' +
                                '<br>' +
                                '</div>'); //add input box
                    }
                });

                $(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
                    e.preventDefault();
                    $(this).parent('div').parent('div').parent('div').remove();
                    x--;
                });

                $("#meuForm").submit(function (e) {
                    $.ajax({
                        type: "POST",
                        url: "/bibliomaster/acao?parametro=cadastraUsuarioNormal",
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
                                        window.location = "index.jsp";
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
    </body>
</html>
