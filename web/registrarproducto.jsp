<%-- 
    Document   : registrarproducto
    Created on : 16-Jan-2014, 16:59:41
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
        <link rel="stylesheet" type="text/css" href="../css/oncemoretime/registrarproducto.css" media="all" />
	
	<!-- Javascript -->
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="../js/kickstart.js"></script>
        <script type="text/javascript" src="../js/registrarproducto.js"></script>
    </head>
    <body>
        <header>
            <h1>Once More Time!</h1>
        </header>
        <nav>
            <%@include file="WEB-INF/jspf/includeCategorias.jspf" %>
        </nav>
        <article>
            <div id="registraproducto">
                <form id="datosproducto" method="POST" 
                      action="/OnceMoreTime/SrvArticulo/RegistrarArticulo" onsubmit="return validarFormulario(this);">
                    <img class="align-left" id="imagenprod" width="450" height="300" />
                    <input type="text" id="nombre" name="nombre" placeholder="Nombre del artículo" />
                    <br/>
                    <input type="text" id="imagenurl" name="imagenurl" placeholder="URL de la imagen" 
                           onchange="onUrlChanged(this);"/>
                    <br/>
                    <textarea id="descripcion" name="descripcion" placeholder="Descripción del producto"></textarea>
                    <br/>
                    <input type="text" id="precio" name="precio" placeholder="Precio" />
                    <br/>
                    <div>
                    <%@include file="WEB-INF/jspf/comboboxCategorias.jspf" %>
                    <input type="submit" value="Enviar" />
                    </div>
                </form>
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

