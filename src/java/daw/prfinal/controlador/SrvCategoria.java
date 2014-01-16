/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.prfinal.controlador;

import daw.prfinal.modelo.Categoria;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jackpanzer
 */
public class SrvCategoria extends HttpServlet {

    @PersistenceContext(unitName = "OnceMoreTimePU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

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
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");

        String accion;
        accion = request.getServletPath();
        String vista = "/index.jsp";
        HttpSession sesion;

        switch (accion) {
            case "/SrvCategoria/ObtenerCategorias":
                try {
                    List<Categoria> categorias;
                    TypedQuery<Categoria> query = em.createNamedQuery("Categoria.findAll", Categoria.class);
                    categorias = query.getResultList();
                    out.println("<ul class=\"icons\">");
                    for (Categoria cat : categorias) {
                        String salida = "<li><i class=\"icon-star\"></i>"
                                + "<a href=\"/OnceMoreTime/SrvArticulo/VerArticulos?cat="
                                + cat.getId()
                                + "\">"
                                + cat.getNombre() + "</a></li>";
                        out.println(salida);
                    }
                    out.println("</ul>");
                } catch (Exception e) {
                    out.printf(e.getMessage());
                }
                break;
            case "/SrvCategoria/ComboCategorias":
                try {
                    List<Categoria> categorias;
                    TypedQuery<Categoria> query = em.createNamedQuery("Categoria.findAll", Categoria.class);
                    categorias = query.getResultList();
                    out.print("<select id=\"combocategorias\" name=\"categoria\">");
                    for (Categoria cat : categorias) {
                        String salida = "<option value=\""
                                + cat.getId()
                                + "\">"
                                + cat.getNombre()
                                + "</option>";
                        out.print(salida);
                    }
                    out.print("</select>");
                } catch (Exception e) {
                    out.printf(e.getMessage());
                }
                break;
            default:
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
