<%-- 
    Document   : plantillaJSP
    Created on : 16-Jan-2014, 13:58:20
    Author     : Juan Manuel Pedraza García <jackpanzer@github.com>
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <script type="text/javascript" src="../js/queryFavoritos.js"></script>
    </head>
    <body>
        <header>
            Once More Time!
            <br/>
            <br/>
            <h8>Artículos</h8>
        </header>
        <nav>
            <%@include file="WEB-INF/jspf/includeCategorias.jspf" %>
        </nav>
        <article>
            <h5>Se han recuperado un total de ${listaSize} artículos</h5>
            <%@include file="WEB-INF/jspf/filtro.jspf" %>
            <div id="productos">
            <c:if test="${listaSize != 0}">
                <table class="striped">
                    <tr>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Fecha de publicación</th>
                        <th>Vendedor</th>
                        <th>Código postal</th>
                        <th>Categoría</th>
                    </tr>
                    <c:forEach items="${articulos}" var="artActual">
                        <tr idd2="${artActual.precio}" idd1="${artActual.vendedor.codpostal}" idd3="${artActual.categoriaId.nombre}">
                            <td><a href="/OnceMoreTime/SrvArticulo/VerProducto?prod=${artActual.id}">${artActual.nombre}</a></td>
                            <td>${artActual.precio}</td>
                            <c:set var="fechaString">${artActual.fechaPublicacion}</c:set>
                            <td>${fechaString}</td>
                            <td><a href="/OnceMoreTime/SrvUsuario/VerPerfil?id=${artActual.vendedor.id}">${artActual.vendedor.nick}</a></td>
                            <td>${artActual.vendedor.codpostal}</td>
                            <td>${artActual.categoriaId.nombre}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
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

