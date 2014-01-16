/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function validarFormulario(formulario){
    var nombreProducto = document.getElementById("nombre");
    var urlImagen = document.getElementById("imagenurl");
    var descripcion = document.getElementById("descripcion");
    var precio = document.getElementById("precio");
    
    if(nombreProducto.value === ""){
        alert("Introduzca un nombre");
        nombreProducto.focus();
        return false;
    }
    
    if(urlImagen.value === ""){
        alert("Introduzca una URL de imagen");
        urlImagen.focus();
        return false;
    }
    
    if(descripcion.value === ""){
        alert("Introduzca una descripción");
        urlImagen.focus();
        return false;
    }
    
    var newNumber = new Number(precio.value);
    
    if(newNumber.toString() === "NaN" || precio.value <= 0){
        alert("Introduzca un precio válido");
        precio.focus();
        return false;
    }
    
    return true;
}

function onUrlChanged(textfield){
    var imagen = document.getElementById("imagenprod");
    imagen.src = textfield.value;
}
