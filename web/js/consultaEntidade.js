function consultaEntidade(tipo) {
    var xhttp;
    var criterio = document.getElementById('criterio'+tipo).value;
    if (criterio.value === "") {
        document.getElementById(tipo).innerHTML = "";
        return;
    }
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById(tipo).innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "/bibliomaster/acao?parametro=consultar" + tipo + "&nome=" + criterio.toString(), true);
    xhttp.send();
}
