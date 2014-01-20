/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var containerFav;
//localStorage.favoritos = undefined;

function comprobarLocalStorage(id){
    containerFav = document.getElementById("localStorage");
    if(typeof(Storage)==="undefined"){
        containerFav.innerHtml="<h5>Su navegador no soporta Local Storage</h5>";
    } else {
        if(localStorage.favoritos === undefined 
                || localStorage.favoritos === ""
                || localStorage.favoritos.indexOf(id) === -1) {
            //No hay nada en el almacenamiento local o no está el elemento
            containerFav.innerHTML = "<button id=\"boton\" class=\"medium green\" "
                    +"onclick=\"agregarFavorito("
                    + id+");\">"
                    +"<i class=\"icon-plus-sign\"/>"
                    +" Agregar a favoritos"
                    +"</button>";
        } else {
            //El elemento está
            containerFav.innerHTML = "<button id=\"boton\" class=\"medium orange\" "
                    +"onclick=\"eliminarFavorito("
                    + id+");\">"
                    +"<i class=\"icon-minus-sign\"/>"
                    +" Eliminar de favoritos"
                    +"</button>";
        }
    }
}

function agregarFavorito(id){
    if(localStorage.favoritos === undefined 
                || localStorage.favoritos === ""){
        //No está creada nuestra variable de localStorage, se crea
        localStorage.favoritos = [id];
    } else {
        localStorage.favoritos.push(id);
    }
    comprobarLocalStorage(id);
}

function eliminarFavorito(id){
    if(localStorage.favoritos === undefined 
                || localStorage.favoritos === ""){
        //LocalStorage está a null, nada que hacer
        return;
    } else {
        var posicion = localStorage.favoritos.indexOf(id);
        localStorage.favoritos = localStorage.favoritos.splice(posicion, 1);
        comprobarLocalStorage(id);
    }
}
