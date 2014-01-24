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
        <script type="text/javascript" src="../js/votacion.js"></script>
    </head>
    <body>
        <header>
            <h1>Once More Time!</h1>
        </header>
        <nav>
            <%@include file="WEB-INF/jspf/includeCategorias.jspf" %>
        </nav>
        <article>
            <i class="icon-user"></i> Usuario: ${usuario.nick} <br/>
            <i class="icon-envelope"></i> Correo: <a href="mailto:${usuario.email}">${usuario.email}</a> <br/>
            <i class="icon-phone"></i> Teléfono: ${usuario.telefono} <br/>
            <i class="icon-twitter"></i> Twitter: ${usuario.twitter} <br/>
            <i class="icon-facebook"></i> Facebook: ${usuario.facebook} <br/>
            <i class="icon-thumbs-up"></i> ${usuario.reputacion}/5 de reputación <br/>
            <c:if test="${(not empty sessionScope.usuarioLogged) and (sessionScope.userID != usuario.id)}">
                <select id="nota">
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
                <button onclick="votar(${sessionScope.userID}, ${usuario.id});">Califícame! :)</button>

            </c:if>
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
