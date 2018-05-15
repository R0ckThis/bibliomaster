<!-- Modal -->
<div class="modal fade" id="modalLivro" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body"><div class="te"></div></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<script>
        function visualizaLivro(id, titulo) {
            $('#modalLivro').removeData('bs.modal');
            $('#modalLivro .modal-title').text(titulo);
            $('#modalLivro .te').load('/bibliomaster/acao?parametro=visualizarLivro&id=' + id);
            $('#modalLivro').modal({
                show: true
            });
        };
</script>