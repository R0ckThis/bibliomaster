<div class="modal fade" id="modalExcluir" tabindex="-1" role="dialog" aria-labelledby="modalExcluirLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div style="background-color: #c12e2a; color: #f0f0f0" class="modal-header">
                <button type="button" class="cancelar close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="modalExcluirLabel">Cuidado!</h3>
            </div>
            <div class="modal-body">
                <h4>Tem certeza da exclusão?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="cancelar btn btn-default" data-dismiss="modal">Cancelar</button>
                <a class="excluir btn btn-danger">Excluir</a>
            </div>
        </div>
    </div>
</div>
<script>
    $('#modalExcluir').on('show.bs.modal', function (e) {
        $(this).find('.excluir').attr('href', $(e.relatedTarget).data('href'));

        $('.debug-url').html('Delete URL: <strong>' + $(this).find('.excluir').attr('href') + '</strong>');
    });
</script>