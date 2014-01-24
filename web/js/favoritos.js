/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var containerFav;

var favs = eval(localStorage.favs);
var botonFav;
var botonUnFav;
//localStorage.favoritos = undefined;
function setButtons(id) {
    botonFav = "<button id=\"boton\" class=\"medium green\" "
            + "onclick=\"agregarFavorito("
            + id + ");\">"
            + "<i class=\"icon-plus-sign\"/>"
            + " Agregar a favoritos"
            + "</button>";
    botonUnFav = "<button id=\"boton\" class=\"medium orange\" "
            + "onclick=\"eliminarFavorito("
            + id + ");\">"
            + "<i class=\"icon-minus-sign\"/>"
            + " Eliminar de favoritos"
            + "</button>";
}

function comprobarLocalStorage(id) {
    setButtons(id);
    containerFav = document.getElementById("localStorage");
    if (typeof(Storage) === "undefined") {
        containerFav.innerHTML = "<h5>Su navegador no soporta Local Storage</h5>";
    } else {
        if (favs instanceof Array && favs.indexOf(id) !== -1) {
            containerFav.innerHTML = botonUnFav;
        } else {
            containerFav.innerHTML = botonFav;
        }
    }
}

function agregarFavorito(id) {
    if (!(favs instanceof Array)) {
        favs = new Array();
    }

    favs.push(id);
    localStorage.favs = JSON.stringify(favs);
    comprobarLocalStorage(id);
}

function eliminarFavorito(id) {
    if (favs instanceof Array) {
        var indice = favs.indexOf(id);
        if (indice !== -1) {
            favs.splice(indice, 1);
        }
        localStorage.favs = JSON.stringify(favs);
        comprobarLocalStorage(id);
    }
}
