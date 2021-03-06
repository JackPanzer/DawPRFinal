<%-- 
    Document   : verproducto
    Created on : 09-Jan-2014, 18:58:12
    Author     : Juan Manuel Pedraza García <jackpanzer@github.com>
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="../css/kickstart.css" media="all" />
        <link rel="stylesheet" type="text/css" href="../css/style.css" media="all" /> 
        <link rel="stylesheet" type="text/css" href="../css/oncemoretime/index.css" media="all" />

        <!-- Javascript -->
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script type="text/javascript" src="../js/kickstart.js"></script>
        <script type="text/javascript" src="../js/registrarproducto.js"></script>
        <script type="text/javascript" src="../js/favoritos.js"></script>
    </head>
    <body onload="comprobarLocalStorage(${producto.id})">
        <header>
            Once More Time!
            <br/>
            <br/>
            <h8>Viendo artículo</h8>
        </header>
        <nav>
            <%@include file="WEB-INF/jspf/includeCategorias.jspf" %>
        </nav>
        <article>
            <img class="align-left" id="imagen" width="300" height="250"/>
            <script type="text/javascript">
                var imagen = document.getElementById("imagen");
                imagen.src = "${producto.imagenUrl}";

            </script>
            <h3>${producto.nombre}</h3>
            <p>${producto.descripcion}</p>
            <h5>${producto.precio} Euros</h5>
            <c:choose>
                <c:when test="${producto.vendedor.id == sessionScope.userID}">

                </c:when>
                <c:otherwise>
                    <h5>Reputación del vendedor: ${producto.vendedor.reputacion}</h5>
                    <h5>Vendedor: <a href="/OnceMoreTime/SrvUsuario/VerPerfil?id=${producto.vendedor.id}">${producto.vendedor.nick}</a></h5>
                    </c:otherwise>
                </c:choose>

            <!-- Uso de LocalStorage -->
            <div id="localStorage">
                
            </div>
        </article>
        <aside>
            <%@include file="WEB-INF/jspf/panelControl.jspf" %>
        </aside>
        <footer>
            Juan Manuel Pedraza - 
            Práctica final de Desarrollo de Aplicaciones Web - 
            Universidad de Huelva
        </footer>
    </body>
</html>
