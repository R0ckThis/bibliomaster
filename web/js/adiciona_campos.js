$(document).ready(function () {
var wrapper = $(".input_fields_wrap_autor"); //Fields wrapper
        var add_button = $(".add_field_button_autor"); //Add button ID

        $(add_button).click(function (e) { //on add input button click
e.preventDefault();
        var length = wrapper.find("input:text").length;
        $(wrapper).append('<div class="form-group">' +
        '<div class="autor row">' +
        '<div class="col-md-3">' +
        '<input type="number" class="id form-control" name="autor" min="1" required onblur="buscaEntidade(this, ' + "'Autor', 'Autor não encontrado!'" + ')">' +
        '</div>' +
        '<div class="col-md-9 input-group">' +
        '<input type="text" class="nome form-control" readonly>' +
        '<span class="input-group-btn">' +
        '<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalBuscaAutor"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>' +
        '<button class="remove_field btn btn-danger btn btn-danger"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>' +
        '</span>' +
        '</div>' +
        '</div>' +
        '</div>'); //add input box
});
        $(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
e.preventDefault();
        $(this).parent().parent().parent().parent().remove();
});
});
        $(document).ready(function () {
var wrapper = $(".input_fields_wrap_genero"); //Fields wrapper
        var add_button = $(".add_field_button_genero"); //Add button ID

        $(add_button).click(function (e) { //on add input button click
e.preventDefault();
        var length = wrapper.find("input:text").length;
        $(wrapper).append('<div class="form-group">' +
        '<div class="genero row">' +
        '<div class="col-md-3">' +
        '<input type="number" class="id form-control" name="genero" min="1" required onblur="buscaEntidade(this,' + "'Genero', 'Gênero não encontrado!'" + ')">' +
        '</div>' +
        '<div class="col-md-9 input-group">' +
        '<input type="text" class="nome form-control" readonly>' +
        '<span class="input-group-btn">' +
        '<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalBuscaGenero"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>' +
        '<button class="remove_field btn btn-danger btn btn-danger"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>' +
        '</span>' +
        '</div>' +
        '</div>' +
        '</div>'); //add input box
});
        $(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
e.preventDefault();
        $(this).parent().parent().parent().parent().remove();
});
});
