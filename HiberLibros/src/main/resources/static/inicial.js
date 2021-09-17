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
function consultarLibros() {

    bootbox.dialog({
        title: 'Lista de libros',
        size: 'small',
        message: $("#listaLibros").html()
    });
}
function anyadirAutor(pID) {
    $.post("/hiberlibros/formAutor", {
        id_usuario: pID
    }, function (pJson) {
        bootbox.dialog({
            title: 'Añadir autor',
            size: 'large',
            message: "<div id='autorForm'>" + $("#autor").html() + "</div>"
        });
        $("#autorForm form").deserialize(pJson);

    });


}



