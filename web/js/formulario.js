/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var validable = true;
var comprobarValidables = [0, 0, 0];
var xhrNick;
var xhrCorreo;

function nickValido(){
    if (xhrNick.readyState === 4) {
        if (xhrNick.status === 200) {
            var mensaje = document.getElementById("aliasErroneo");
            var respuesta = xhrNick.responseText;
            var notice;
            if(respuesta === "error"){
                notice = "<div class=\"notice error\"><i class=\"icon-remove-sign icon-large\"></i> Nick en uso" 
                            +"<a href=\"#close\" class=\"icon-remove\"></a></div>";
                comprobarValidables[0] = 0;
            } else {
                notice = "<div class=\"notice success\"><i class=\"icon-ok icon-large\"></i> Nick disponible" 
                            +"<a href=\"#close\" class=\"icon-remove\"></a></div>";
                comprobarValidables[0] = 1;
            }
            
            mensaje.innerHTML = notice;
        }
    }
}

function validarNick(){
    var mensaje = document.getElementById("aliasErroneo");
    mensaje.innerHTML = "Validando nick...";
    var nick = document.getElementById("nick").value;
    if(nick === "" || nick.length < 6){
        var notice = "<div class=\"notice error\"><i class=\"icon-remove-sign icon-large\"></i> Nick no válido o menor de 6 caracteres" 
                            +"<a href=\"#close\" class=\"icon-remove\"></a></div>";
        mensaje.innerHTML = notice;
        comprobarValidables[0] = 0;
        return false;
    }
    if (window.XMLHttpRequest) {
        xhrNick = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xhrNick = new ActiveXObject("Microsoft.XMLHTTP");
    }
    
    xhrNick.open("POST", "/OnceMoreTime/SrvUsuario/ComprobarNick", true);
    xhrNick.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xhrNick.send("nick="+nick);
    xhrNick.onreadystatechange = nickValido;
}

function correoValido(){
    if (xhrCorreo.readyState === 4) {
        if (xhrCorreo.status === 200) {
            var mensaje = document.getElementById("correoErroneo");
            var respuesta = xhrCorreo.responseText;
            var notice;
            if(respuesta === "error"){
                notice = "<div class=\"notice error\"><i class=\"icon-remove-sign icon-large\"></i> Correo en uso" 
                            +"<a href=\"#close\" class=\"icon-remove\"></a></div>";
                comprobarValidables[1] = 0;
            } else {
                notice = "<div class=\"notice success\"><i class=\"icon-ok icon-large\"></i> Correo disponible" 
                            +"<a href=\"#close\" class=\"icon-remove\"></a></div>";
                comprobarValidables[1] = 1;
            }
            
            mensaje.innerHTML = notice;
        }
    }
}

function validarCorreo(){
    var mensaje = document.getElementById("correoErroneo");
    mensaje.innerHTML = "Validando email...";
    var correo = document.getElementById("correo").value;
    
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    
    if(!re.test(correo)){
        var mensaje = document.getElementById("correoErroneo");
        var notice = "<div class=\"notice error\"><i class=\"icon-remove-sign icon-large\"></i> Formato de correo no válido" 
                            +"<a href=\"#close\" class=\"icon-remove\"></a></div>";
        comprobarValidables[1] = 0;
        mensaje.innerHTML = notice;
        return false;
    }
    
    if (window.XMLHttpRequest) {
        xhrCorreo = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xhrCorreo = new ActiveXObject("Microsoft.XMLHTTP");
    }
    
    xhrCorreo.open("POST", "/OnceMoreTime/SrvUsuario/ComprobarCorreo", true);
    xhrCorreo.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xhrCorreo.send("correo="+correo);
    xhrCorreo.onreadystatechange = correoValido;
}

function validarPasswords(){
    var pass = document.getElementById("passwd1").value;
    var pass_bis = document.getElementById("passwd2").value;
    var mensaje = document.getElementById("passErronea");
    var notice; 
    
    if(pass === "" || pass.length < 6){
        notice = "<div class=\"notice error\"><i class=\"icon-remove-sign icon-large\"></i> Contraseña no válida o menor de 6 caracteres" 
                            +"<a href=\"#close\" class=\"icon-remove\"></a></div>";
        mensaje.innerHTML = notice;
        comprobarValidables[2] = 0;
        return false;
    }
    if(pass !== pass_bis){
        notice = "<div class=\"notice error\"><i class=\"icon-remove-sign icon-large\"></i> Las contraseñas no coinciden" 
                            +"<a href=\"#close\" class=\"icon-remove\"></a></div>";
        mensaje.innerHTML = notice;
        comprobarValidables[2] = 0;
        return false;
    }
    
    notice = "<div class=\"notice success\"><i class=\"icon-ok icon-large\"></i> Contraseña válida" 
                            +"<a href=\"#close\" class=\"icon-remove\"></a></div>";
    mensaje.innerHTML = notice;
    comprobarValidables[2] = 1;
}

function validarFormulario(formulario){
    var formu = formulario;
    //Datos imprescindibles
    var usuario = document.getElementById("nombre").value;
    var direccion = document.getElementById("direccion").value;
    var cp = document.getElementById("cpostal").value;
    
    if(usuario === "" || usuario.length < 6 ){
        alert("Revise el campo Usuario");
        document.getElementById("nombre").focus();
        return false;
    }
    if(comprobarValidables[0] === 0){
        alert("Revise el campo Alias");
        document.getElementById("nick").focus();
        return false;
    }
    if(comprobarValidables[1] === 0){
        alert("Revise el campo Correo");
        document.getElementById("correo").focus();
        return false;
    }
    if(comprobarValidables[2] === 0){
        alert("Revise las contraseñas");
        document.getElementById("passwd1").focus();
        return false;
    }
    if(cp === NaN || cp === ""){
        alert("Revise el código postal introducido");
        document.getElementById("cpostal").focus();
        return false;
    }
    if(direccion.value === "" || direccion.value.length < 6 ){
        alert("Revise el campo Dirección");
        document.getElementById("direccion").focus();
        return false;
    }
    
    return true;
}