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
	<link rel="stylesheet" type="text/css" href="../style.css" media="all" /> 
	
	<!-- Javascript -->
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="../js/kickstart.js"></script>
    </head>
    <body>
        <header>
            <h1>Once More Time!</h1>
        </header>
        <nav>
            <%@include file="WEB-INF/jspf/includeCategorias.jspf" %>
        </nav>
        <article>
            
        </article>
        <aside>
            <%@include file="WEB-INF/jspf/panelControl.jspf" %>
        </aside>
        <footer>
            Juan Manuel Pedraza - 
            Práctica final de Desarrollo de Aplicaciones Web - 
            Universidad de Huelva
        </footer>
        <script src="js/index.js" type="text/javascript"></script>
    </body>
</html>

