<%@page import="entidade.Autor"%>
<%@page import="dao.AutorDAO"%>
<%@page import="java.util.ArrayList"%>
<div class="modal fade" id="modalBuscaAutor" tabindex="-1" role="dialog" aria-labelledby="modalBuscaLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="cancelar close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="modalBuscaLabel">Busca de Autor</h3>
            </div>
            <div class="modal-body">
                <label>Busca pelo Nome completo</label>
                <div class="input-group">
                    <input id="criterioAutores" type="text" class="form-control">
                    <span class="input-group-btn">
                        <button id="somebutton" type="button" class="btn btn-primary" onclick="consultaEntidade('Autores')"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                    </span>
                </div>
                <div id="Autores">
                    <h3 style="text-align: center;">Busque Algo!</h3>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="cancelar btn btn-default" data-dismiss="modal">Cancelar</button>
            </div>
        </div>
    </div>
</div>
<script>
    var divSelecionada;
    (function () {
        $('#modalBuscaAutor').on('show.bs.modal', function (event) {
            divSelecionada = $(event.relatedTarget).parent().parent().parent(); // Button that triggered the modal
        });
    })();
    function selecionarAutor(campo) {
            var id = $(campo).data('id'); // Extract info from data-* attributes
            var nome = $(campo).data('nome');
            var form = divSelecionada;
            form.find('.id').val(id);
            form.find('.nome').val(nome);
        };
</script>
<script src="js/consultaEntidade.js" type="text/javascript"></script>