/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var xhrVotar;

function votar(idVotante, idVotado){
    var nota = document.getElementById("nota").value;
    
    var url = "/OnceMoreTime/SrvVotacion/VotarUsuario?votante="
        + idVotante
        + "&votado="
        + idVotado
        + "&valor=" 
        + nota;

    if (window.XMLHttpRequest) {
        xhrVotar = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xhrVotar = new ActiveXObject("Microsoft.XMLHTTP");
    }
    
    xhrVotar.open("GET", url, true);
    xhrVotar.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xhrVotar.onreadystatechange = resVotacion;
    xhrVotar.send();
}

function resVotacion(){
    if (xhrVotar.readyState === 4) {
        if (xhrVotar.status === 200) {
            alert(xhrVotar.responseText);
        }
    }
}