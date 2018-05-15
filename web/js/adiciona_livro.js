$(document).ready(function () {
    var wrapper = $(".input_fields_wrap_livro"); //Fields wrapper
    var add_button = $(".add_field_button_livro"); //Add button ID

    $(add_button).click(function (e) { //on add input button click
        e.preventDefault();
        var length = wrapper.find("input:text").length;
        $(wrapper).append('<div class="livro"><div class="form-group">' +
                '<div class="row">' +
                '<div class="col-md-3">' +
                '<input type="number" class="id form-control" name="livro" min="1" required placeholder="ID Livro" onblur="buscaEntidade(this, ' + "'Livro', 'Livro não encontrado!'" + ')">' +
                '</div>' +
                '<div class="col-md-9 input-group">' +
                '<input type="text" class="nome form-control" readonly>' +
                '<span class="input-group-btn">' +
                '<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalBuscaLivro"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>' +
                '<button class="remove_field btn btn-danger btn btn-danger"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>' +
                '</span>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '<div class="form-group">' +
                '<label>Observação Livro</label>' +
                '<textarea class="form-control" name="observacaoLivro" rows="3"></textarea>' +
                '</div></div></div>'); //add input box
    });
    $(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
        e.preventDefault();
        $(this).parent().parent().parent().parent().parent().remove();
    });
});