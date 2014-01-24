/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function filtrar() {
    restaurar();

    var filtroCP = document.getElementById("cpostal");
    var filtroPrecio = document.getElementById("precio");
    var filtroCat = document.getElementById("cat");

    var cpCorrecto = filtroCP !== null && typeof(filtroCP) !== "undefined" && filtroCP.value !== "";
    var precioCorrecto = filtroPrecio !== null && typeof(filtroPrecio) !== "undefined" && parseInt(filtroPrecio.value) !== NaN;
    var catCorrecto = filtroCat !== null && typeof(filtroCat) !== "undefined" && filtroCat.value !== "" && filtroCat.value !== "Todos";

    if (cpCorrecto) {
        filtrarCP(filtroCP.value);
    }

    if (precioCorrecto) {
        filtrarPrecio(filtroPrecio.value);
    }

    if (catCorrecto) {
        filtrarCategoria(filtroCat.value);
    }
}

function restaurar() {
    $("tr").each(function(index) {
        $(this).removeClass("escondido");
    });
}

function filtrarCP(codpostal) {
    $("tr").each(function(index) {
        if (typeof($(this).attr("idd1")) !== "undefined") {
            if ($(this).attr("idd1") !== codpostal) {
                $(this).attr("class", "escondido");
            }
        }
    });
}

function filtrarPrecio(precio) {
    $("tr").each(function(index) {
        if (typeof($(this).attr("idd2")) !== "undefined") {
            var valorContenido = parseFloat($(this).attr("idd2"));
            if (valorContenido !== NaN && valorContenido > parseFloat(precio)) {
                $(this).attr("class", "escondido");
            }
        }
    });
}

function filtrarCategoria(cat) {
    $("tr").each(function(index) {
        if (typeof($(this).attr("idd3")) !== "undefined") {
            if ($(this).attr("idd3") !== cat) {
                $(this).attr("class", "escondido");
            }
        }
    });
}

var xhrFavoritos;

function init_xhr() {
    if (window.XMLHttpRequest) {
        xhrFavoritos = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xhrFavoritos = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xhrFavoritos.open("POST", "/OnceMoreTime/SrvArticulo/ObtenerFavs", true);
    xhrFavoritos.onreadystatechange = favLoaded;
    xhrFavoritos.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhrFavoritos.send("favs=" + localStorage.favs);

}

function favLoaded() {
    if (xhrFavoritos.readyState === 4) {
        if (xhrFavoritos.status === 200) {
            document.getElementById("articulos").innerHTML = xhrFavoritos.responseText;
        }
    }
}