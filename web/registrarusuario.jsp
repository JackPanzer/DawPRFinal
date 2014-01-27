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
        <title>Once More Time! - Tu web de compras de segunda mano</title>
        <!-- CSS -->
	<link rel="stylesheet" type="text/css" href="../css/kickstart.css" media="all" />
	<link rel="stylesheet" type="text/css" href="../css/style.css" media="all" /> 
        <link rel="stylesheet" type="text/css" href="../css/oncemoretime/index.css" media="all" />
        <link rel="stylesheet" type="text/css" href="../css/oncemoretime/registrarusuario.css" media="all" />
	
	<!-- Javascript -->
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="../js/kickstart.js"></script>
        <script src="../js/formulario.js" type="text/javascript"></script>
        <script src="../js/provincias.js" type="text/javascript"></script>
    </head>
    <body>
        <header>
            Once More Time!
            <br/>
            <br/>
            <h8>Registrar usuario</h8>
        </header>
        <nav>
            <%@include file="WEB-INF/jspf/includeCategorias.jspf" %>
        </nav>
        <article>
            <div id="registro">
                <form name="formulario" method="POST" action="/OnceMoreTime/SrvUsuario/CrearUsuario" onsubmit="return validarFormulario(this);">
                    <div id="datos">
                        <h4>Datos de la cuenta</h4>
                        <br>
                        <input type="text" id="nombre" name="nombre" value=""
                               title="Nombre" placeholder="Nombre"/>
                        <br/>
                        <input type="text" id="nick" name="nick" value=""
                               title="Alias" placeholder="Alias"
                               onblur="validarNick(this);"/>
                        <div id="aliasErroneo"></div>
                        <br/>
                        <input type="text" id="correo" name="correo" value=""
                               title="Correo" placeholder="Correo"
                               onblur="validarCorreo(this);"/>
                        <div id="correoErroneo"></div>
                        <br/>

                        <input type="password" id="passwd1" name="password" value=""
                               title="Contraseña" placeholder="Contraseña"
                               onblur="validarPasswords();"/>
                        <br/>
                        <input type="password" id="passwd2" value="" 
                               title="Repetir contraseña" placeholder="Repetir contraseña"
                               onblur="validarPasswords();"/>
                        <div id="passErronea"></div>
                        <br />
                    </div>
                    <div id="sobreti">
                        <h4>Háblanos de ti</h4>
                        <br>
                        <input type="text" id="direccion" name="direccion" value=""
                               title="Dirección" placeholder="Dirección" />
                        <br />
                        <input type="text" id="cpostal" name="cpostal" value=""
                               title="Código postal" placeholder="Código postal"
                               onchange="cambiarProvincia(this);"/>
                        <br />

                        <label for="provincia" form="formulario">Provincia</label>
                        <br />
                        <input type="text" name="provincia" value="- -" id="provincia" readonly/>
                        <br />

                        <input type="text" id="facebook" name="facebook" value="" 
                               title="Facebook" placeholder="Cuenta de Facebook"/>
                        <br />

                        <input type="text" id="twitter" name="twitter" value="" 
                               title="Twitter" placeholder="@CuentaDeTwitter"/>
                        <br />

                        <input type="text" id="telefono" name="tlf" pattern="[0-9]{9}"
                               title="Teléfono" placeholder="Teléfono"/>
                        <br />
                    </div>
                    <input id="submit" type="submit" value="Enviar"/>
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
