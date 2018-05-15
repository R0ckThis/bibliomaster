<div class="modal fade" id="modalBuscaLivro" tabindex="-1" role="dialog" aria-labelledby="modalBuscaLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="cancelar close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="modalBuscaLabel">Busca de Livro</h3>
            </div>
            <div class="modal-body">
                <label>Busca pelo Título</label>
                <div class="input-group">
                    <input id="criterioLivros" type="text" class="form-control">
                    <span class="input-group-btn">
                        <button id="somebutton" type="button" class="btn btn-primary" onclick="consultaEntidade('Livros')"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                    </span>
                </div>
                <div id="Livros">
                    <h3 style="text-align: center;">Busque Algo!</h3>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="cancelar btn btn-default" data-dismiss="modal">Cancelar</button>
            </div>
        </div>
    </div>
</div>
<%@include file="modalLivro.jsp" %>
<script>
    var divSelecionada;
    (function () {
        $('#modalBuscaLivro').on('show.bs.modal', function (event) {
            divSelecionada = $(event.relatedTarget).parent().parent().parent(); // Button that triggered the modal
        });
    })();
    function selecionarLivro(campo) {
        var id = $(campo).data('id'); // Extract info from data-* attributes
        var nome = $(campo).data('nome');
        var form = divSelecionada;
        form.find('.id').val(id);
        form.find('.nome').val(nome);
    }
    ;
</script>
<script src="js/consultaEntidade.js" type="text/javascript"></script>
