function registrarUsuario(){
    bootbox.dialog({
        title: 'Registro Usuario',
        size: 'small',
        message: $("#nuevoUsuario").html()
    });
    
}
function entrar(){
    bootbox.dialog({
        title: 'Login',
        size: 'small',
        message: $("#login").html()
    }); 
}



