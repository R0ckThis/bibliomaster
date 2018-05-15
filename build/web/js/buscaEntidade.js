function buscaEntidade(div, tipo, mensagem) {
    var xhttp;
    xhttp = new XMLHttpRequest();
    var form = $(div).parent().parent().parent();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            if (this.responseText !== "") {
                form.find('.nome').val(this.responseText);
            } else {
                form.find('.id').val("");
                form.find('.nome').val("");
                $('#modalErro').find('.mensagem').text(mensagem);
                $('#modalErro').modal('show');
            }
        }
    };
    if (div.value !== "") {
        xhttp.open("GET", "/bibliomaster/acao?parametro=buscar"+tipo+"&id=" + div.value, true);
        xhttp.send();
    } else {
        form.find('.nome').val("");
        $('#modalErro').find('.mensagem').text(mensagem);
        $('#modalErro').modal('show');
    }
};