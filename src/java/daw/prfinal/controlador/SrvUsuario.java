/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.prfinal.controlador;

import daw.prfinal.modelo.Rol;
import daw.prfinal.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jackpanzer
 */
public class SrvUsuario extends HttpServlet {
    @PersistenceContext(unitName = "OnceMoreTimePU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    protected long getUsersAmount()
    {
        long ret = 0;
        
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findAll", Usuario.class);
        List<Usuario> usuarios = query.getResultList();
        
        ret = usuarios.size()+1;
        
        return ret;
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");

        String accion;
        accion = request.getServletPath();
        String vista = "/loggedindex.jsp";
        RequestDispatcher rd;
        HttpSession sesion;

        switch (accion) {
            case "/SrvUsuario/Logout":
                sesion = request.getSession();
                sesion.invalidate();
                vista = "/loggedindex.jsp";
                rd = request.getRequestDispatcher(vista);
                rd.forward(request, response);
                break;
            case "/SrvUsuario/Registrar":
                vista = "/registrarusuario.jsp";
                rd = request.getRequestDispatcher(vista);
                rd.forward(request, response);
                break;
            case "/SrvUsuario/Login":
                try {
                    MessageDigest md5 = MessageDigest.getInstance("md5");

                    String usuario = request.getParameter("usuario");
                    String passwd = request.getParameter("passwd");

                    Usuario user;

                    TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findByNick", Usuario.class);
                    query.setParameter("nick", usuario);

                    user = query.getSingleResult();

                    byte[] hash = md5.digest(passwd.getBytes());
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < hash.length; i++) {
                        sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
                    }
                    String formedpass = sb.toString();

                    if (user.getPass().equalsIgnoreCase(formedpass)) {
                        //Los datos de usuario son correctos: Buen loggin, inicia la sesión.
                        sesion = request.getSession(true);
                        sesion.setAttribute("usuarioLogged", "true");
                        sesion.setAttribute("userID", user.getId().toString());
                        sesion.setAttribute("nick", user.getNick());

                    }
                    else
                    {
                        vista = "/error.jsp";
                        request.setAttribute("errorMessage", "Usuario no existente o contraseña equivocada");
                    }
                    

                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(SrvUsuario.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoResultException e) {
                    //Mal loggin
                    vista = "/error.jsp";
                    request.setAttribute("errorMessage", "Usuario no existente o contraseña equivocada");
                }
                
                rd = request.getRequestDispatcher(vista);
                rd.forward(request, response);
                break;
            case "/SrvUsuario/CrearUsuario":

                try {
                    MessageDigest md5 = MessageDigest.getInstance("md5");

                    Usuario nuevo = new Usuario();

                    nuevo.setId(getUsersAmount());

                    nuevo.setNombre(request.getParameter("nombre"));
                    nuevo.setNick(request.getParameter("nick"));
                    nuevo.setCodpostal(Integer.parseInt(request.getParameter("cpostal")));
                    nuevo.setFechaRegistro(new Date());
                    nuevo.setDireccion(request.getParameter("direccion"));
                    nuevo.setEmail(request.getParameter("correo"));

                    byte[] hash = md5.digest(request.getParameter("password").getBytes());
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < hash.length; i++) {
                        sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
                    }
                    String formedpass = sb.toString();

                    nuevo.setPass(formedpass);
                    nuevo.setRolId(em.find(Rol.class, 2L));
                    nuevo.setReputacion(0F);

                    try {
                        nuevo.setFacebook(request.getParameter("facebook"));
                    } catch (Exception e) {
                    }
                    try {
                        nuevo.setTwitter(request.getParameter("twitter"));
                    } catch (Exception e) {
                    }
                    try {
                        nuevo.setTelefono(Integer.parseInt(request.getParameter("tlf")));
                    } catch (Exception e) {
                    }

                    persist(nuevo);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(SrvUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }

                vista = "/loggedindex.jsp";
                rd = request.getRequestDispatcher(vista);
                rd.forward(request, response);
                break;
            case "/SrvUsuario/ComprobarNick":
                try {
                    TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findByNick", Usuario.class);
                    query.setParameter("nick", request.getParameter("nick"));

                    query.getSingleResult();
                    //Se llega hasta aquí -> error
                    out.print("error");
                    
                } catch (NoResultException e) {
                    //No se han encontrado coincidencias -> nick disponible
                    out.print("no error");
                } catch (Exception e) {
                }
                break;
            case "/SrvUsuario/ComprobarCorreo":
                try {
                    TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findByEmail", Usuario.class);
                    query.setParameter("email", request.getParameter("correo"));

                    query.getSingleResult();
                    //Se llega hasta aquí -> error
                    out.print("error");
                    
                } catch (NoResultException e) {
                    //No se han encontrado coincidencias -> nick disponible
                    out.print("no error");
                } catch (Exception e) {
                }
                break;
            default:
                vista = "/loggedindex.jsp";
                rd = request.getRequestDispatcher(vista);
                rd.forward(request, response);
                break;
        }
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
}
