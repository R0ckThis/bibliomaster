<%@page import="entidade.Editora"%>
<%@page import="dao.EditoraDAO"%>
<%@page import="java.util.ArrayList"%>

<div class="modal fade" id="modalBuscaEditora" tabindex="-1" role="dialog" aria-labelledby="modalExcluirLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="cancelar close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="modalBuscaLabel">Busca de Editora</h3>
            </div>
            <div class="modal-body">
                <label>Busca pelo Nome</label>
                <div class="input-group">
                    <input id="criterioEditoras" type="text" class="form-control">
                    <span class="input-group-btn">
                        <button id="somebutton" type="button" class="btn btn-primary" onclick="consultaEntidade('Editoras')"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                    </span>
                </div>
                <div id="Editoras">
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
    function selecionarEditora(campo) {
        var id = $(campo).data('id'); // Extract info from data-* attributes
        var nome = $(campo).data('nome');
        var form = $(".editora");
        form.find('.id').val(id);
        form.find('.nome').val(nome);
    }
    ;
</script>
<script src="js/consultaEntidade.js" type="text/javascript"></script>