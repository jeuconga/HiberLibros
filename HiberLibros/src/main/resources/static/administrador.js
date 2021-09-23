
function modificarLibros(pId) {
    $.ajax({
        url: '/libros/modificar',
        data: {
            id: pId
        },
        success: function (pHtml) {
            bootbox.dialog({
                title: "Modificar",
                size: "large",
                message: pHtml
            })
        },
        error: function (err) {
            alert('Error 404, page not found')
        }
    });
}

function modificarRelato(pId) {
    $.ajax({
        url: '/relato/modificar',
        data: {
            id: pId
        },
        success: function (pHtml) {
            bootbox.dialog({
                title: "Modificar",
                size: "large",
                message: pHtml
            })
        },
        error: function (err) {
            alert('Error 404, page not found')
        }
    });
}


function mostrarLibros(pId) {
    $.ajax({
        url: '/librosAutor',
        data: {
            id: pId
        },
        success: function (pHtml) {
            bootbox.dialog({

                size: "large",
                message: pHtml
            })
        },
        error: function (err) {
            alert('Error 404, page not found')
        }
    });
}

function editarAutor(pId) {
    $.ajax({
        url: '/editarAutor',
        data: {
            id: pId
        },
        success: function (pHtml) {
            bootbox.dialog({

                size: "large",
                message: pHtml
            })
        },
        error: function (err) {
            alert('Error 404, page not found')
        }
    });
}

function altaUsuario() {

    bootbox.dialog({

        size: "large",
        message: $('#altaUsuario').html()
    });


}


function eliminarLibro(pID) {

    bootbox.confirm({
        size: "small",
        message: "¿Estás seguro?",
        callback: function (result) {
            if (result) {
                window.location.href = "/libros/eliminarAdmin?id=" + pID;
            }
        }
    });
}

function eliminarUsuario(pID) {

    bootbox.confirm({
        size: "small",
        message: "¿Estás seguro?",
        callback: function (result) {
            if (result) {
                window.location.href = "/usuarios/borrar?id=" + pID;
            }
        }
    });
}

function eliminarRelato(pID) {

    bootbox.confirm({
        size: "small",
        message: "¿Estás seguro?",
        callback: function (result) {
            if (result) {
                window.location.href = "/relato/eliminarAdmin?id=" + pID;
            }
        }
    });
}

function eliminarAutor(pID) {

    bootbox.confirm({
        size: "small",
        message: "¿Estás seguro?",
        callback: function (result) {
            if (result) {
                window.location.href = "/eliminarAutor?id=" + pID;
            }
        }
    });
}

function editarGenero(pID) {
    $.ajax({
        url: '/genero/editar',
        data: {id: pID},
        datatype: 'json',
        success: function (pJson) {
            bootbox.dialog({
                title: 'Editar',
                size: 'large',
                message: "<div id='editar'>" + $("#editarGenero").html() + "</div>"
            });
            $("#editar form").deserialize(pJson);
        }

    });


}

///////////////////////////////////////////////////////////////////
$(document).ready(function () {
    $("#capa").load("/vistaAdministrador #inicio");
});
$(document).ready(function () {
    $("#boton").click(function (event) {
        $("#capa").load("/usuarios/listarAdmin");
    });
});
$(document).ready(function () {
    $("#boton2").click(function (event) {
        $("#capa").load("/libros/listarAdmin");
    });
});
$(document).ready(function () {
    $("#boton3").click(function (event) {
        $("#capa").load("/relato/listarAdmin");
    });
});
$(document).ready(function () {
    $("#boton4").click(function (event) {
        $("#capa").load("/autores/listarAdmin");
    });
});
$(document).ready(function () {
    $("#boton5").click(function (event) {
        $("#capa").load("/genero/listarAdmin");
    });
});
$(function () {
    $("#container").simpleCalendar();
});
$("#container").simpleCalendar({

// displays events
    displayEvent: true,
    // event dates
    events: [

        //genera nuevo evento para el dia siguiente de 1 hora de duracion
        {
            startDate: new Date(new Date().setHours(new Date().getHours() + 24)).toDateString(),
            endDate: new Date(new Date().setHours(new Date().getHours() + 25)).toISOString(),
            summary: 'Crea un recordatorio'
        },
        // genera evento para ayer por la tarde
        {
            startDate: new Date(new Date().setHours(new Date().getHours() - new Date().getHours() - 12, 0)).toISOString(),
            endDate: new Date(new Date().setHours(new Date().getHours() - new Date().getHours() - 11)).getTime(),
            summary: 'Crea un recordatorio 2'
        },
        //genera nuevo evento para los ultimos dos dias
        {
            startDate: new Date(new Date().setHours(new Date().getHours() - 48)).toISOString(),
            endDate: new Date(new Date().setHours(new Date().getHours() - 24)).getTime(),
            summary: 'Crea un recordatorio 3'
        }
    ],
    //Dishabilitar descripcion de evento
    disableEventDetails: false,
    disableEmptyDetails: false

});
$("#container").simpleCalendar({

    displayYear: true

});
$("#container").simpleCalendar({

    months: ['enero', 'febrero', 'marzo', 'abril', 'mayo', 'junio', 'julio', 'agosto', 'septiembre', 'octubre', 'noviembre', 'diciembre'],
    days: ['domingo', 'lunes', 'martes', 'miercoles', 'jueves', 'viernes', 'sabado'],
});
$("#container").simpleCalendar({

    fixedStartDay: false

});
$("#container").simpleCalendar({

// called after init
    onInit: function (calendar) {},
    // called on month change
    onMonthChange: function (month, year) {},
    // called on date selection
    onDateSelect: function (date, events) {}

});



