function registrarUsuario() {
    bootbox.dialog({
        title: 'Registro Usuario',
        size: 'large',
        message: $("#nuevoUsuario").html()
    });

}
function editarUsuario() {
    bootbox.dialog({
        title: 'Editar',
        size: 'large',
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
function consultarLibros(pID) {
	$.ajax({
		url: '/getLibrosAutor',
		data : {
			id: pID
			},
		datatype: 'json',
		success: function(json){
				$("#listaLibros").html("<table class='table col-12' id='tabla'>");
				$("#tabla").append("<thead><tr><th>Titulo</th><th>Valoracion</th></tr></thead>");
				$.each(json, function(key,value){
							var fila = $("<tr>");
							$("<td>").html(value.titulo + "</td>" ).appendTo(fila);
							$("<td>").html(value.valoracionLibro + "</td>" ).appendTo(fila);
							fila.append("</tr>");
							fila.appendTo("#tabla");
				});
			 	bootbox.dialog({
            		title: 'Lista de libros',
           	 		size: 'small',
					message:  $("#listaLibros").html()
				});
    	}
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

function gestionarPeticion(pId) {
    $.ajax({
        url: "gestionarPeticion",
        data: {
            id: pId
        },
        success: function (pHtml) {
            bootbox.dialog({
                title: "Guardar intercambio",
                size: "large",
                message: pHtml
            })
        },
        error: function (err) {
            alert('Error 404, page not found')
        }
    });

}


