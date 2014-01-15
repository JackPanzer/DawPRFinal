/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var provincias = new Array ("- -","Álava", "Albacete", "Alicante", "Almería", "Ávila", 
"Badajoz", "Baleares", "Barcelona", "Burgos", "Cáceres", "Cádiz", "Castellón", 
"Ciudad Real", "Córdoba", "Coruña", "Cuenca", "Girona", "Granada", "Guadalajara", 
"Guipuzcoa", "Huelva", "Huesca", "Jaén", "León", "Lleida", "Rioja", "Lugo", 
"Madrid", "Málaga", "Murcia", "Navarra", "Orense", "Asturias", "Palencia", "Las "+ 
"Palmas", "Pontevedra", "Salamanca", "Tenerife", "Cantabria", "Segovia", "Sevilla", 
"Soria", "Tarragona", "Teruel", "Toledo", "Valencia", "Valladolid", "Vizcaya", 
"Zamora", "Zaragoza", "Ceuta", "Melilla"); 

function cambiarProvincia(valor){
    //Obteniendo el campo de provincia
    var textfield = document.getElementById("provincia");
    var cp = parseInt(valor.value.substr(0,2));
    
    if((cp != NaN) && (cp != null) && (cp > 0)){
        textfield.value = provincias[cp];
    } else {
        textfield.value = "- -";
    }
}