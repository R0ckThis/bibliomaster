<div class="modal fade" id="modalCancelar" tabindex="-1" role="dialog" aria-labelledby="modalCancelarLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div style="background-color: #c12e2a; color: #f0f0f0" class="modal-header">
                <button type="button" class="cancelar close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="modalCancelarLabel">Cuidado!</h3>
            </div>
            <div class="modal-body">
                <h4>Tem certeza do cancelamento?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="cancelar btn btn-default" data-dismiss="modal">Fechar</button>
                <a class="cancelar btn btn-danger">Cancelar</a>
            </div>
        </div>
    </div>
</div>
<script>
    $('#modalCancelar').on('show.bs.modal', function (e) {
        $(this).find('.cancelar').attr('href', $(e.relatedTarget).data('href'));

        $('.debug-url').html('Delete URL: <strong>' + $(this).find('.cancelar').attr('href') + '</strong>');
    });
</script>