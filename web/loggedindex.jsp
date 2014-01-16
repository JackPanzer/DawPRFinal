<%-- 
    Document   : index
    Created on : 15-Dec-2013, 03:53:27
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
    </head>
    <body>
        <header>
            <h1>Once More Time!</h1>
            <br/>
            <h2>Página principal</h2>
        </header>
        <nav>
            <%@include file="WEB-INF/jspf/includeCategorias.jspf" %>
        </nav>
        <article>
            <div id="descripcion_web">
                <p>
                    Bienvenido usuario a la página de Once More Time, un portal de compra-venta 
                    de artículos de segunda mano de todo tipo.
                    <br/>
                    Entre nuestros estándares, tratan de destacar la atención al usuario y 
                    la seguridad a la hora de hacer las compras.
                    <br/>
                    Este sitio web utiliza cookies con el propósito de hacerle más fácil la
                    utilización del sitio web y en ningún momento obtiene información sobre 
                    usted. Para poder utilizar este sitio web debe tener habilitadas las 
                    cookies en su navegador.
                </p>
            </div>
            <div id="recientes">
                No hay articulos recientes
            </div>
        </article>
        <aside>
            <div id="panel_control">
                <c:if test="${not empty sessionScope.usuarioLogged}">
                    <div id="userlogged">
                        <p>Bienvenido ${sessionScope.nick}</p>
                        <ul>
                            <li><a href="/OnceMoreTime/SrvUsuario/Logout">Cerrar sesión</a></li>
                            <li><a href="/OnceMoreTime/SrvArticulo/VerArticulosPropios">Ver articulos publicados</a></li>
                        </ul>
                    </div>
                </c:if>
                <c:if test="${empty sessionScope.usuarioLogged}">
                    <div id="usernotlogged">
                        <p>Bienvenido invitado</p>
                        <ul>
                            <li><a href="/OnceMoreTime/SrvUsuario/Registrar">Registrar</a></li>
                            <li>Acceder</li>
                            <form action="/OnceMoreTime/SrvUsuario/Login" method="POST">
                                <input type="text" id="usuario" name="usuario" value="" />
                                <br/>
                                <input type="password" id="passwd" name="passwd" value="" />
                                <br/>
                                <input type="submit" value="Entrar" onclick="validarLogin();">
                            </form>
                        </ul>
                    </div>
                </c:if>
            </div>
        </aside>
        <footer>
            Juan Manuel Pedraza - 
            Práctica final de Desarrollo de Aplicaciones Web - 
            Universidad de Huelva
        </footer>
        <script src="../js/index.js" type="text/javascript"></script>
    </body>
</html>
