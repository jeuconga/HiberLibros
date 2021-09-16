function registrarUsuario() {
    bootbox.dialog({
        title: 'Registro Usuario',
        size: 'small',
        message: $("#nuevoUsuario").html()
    });

}
function editarUsuario() {
    bootbox.dialog({
        title: 'Editar',
        size: 'small',
        message: $("#editarUsuario").html()
    });

}
function eliminarU(pID) {

    bootbox.confirm({
        size: "small",
        message: "¿Estás seguro?",
        callback: function (result) {
            if (result) {
                window.location.href = "/usuarios/borrarUsuario?id=" + pID;
            }
        }
    });
}



