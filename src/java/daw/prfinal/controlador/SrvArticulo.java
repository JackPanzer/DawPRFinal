/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.prfinal.controlador;

import daw.prfinal.modelo.Articulo;
import daw.prfinal.modelo.Categoria;
import daw.prfinal.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
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
public class SrvArticulo extends HttpServlet {

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
        String vista = "/loggedindex.jsp";
        RequestDispatcher rd;
        TypedQuery query;
        HttpSession sesion;

        switch (accion) {
            case "/SrvArticulo/VerArticulos":
                String metodo = request.getParameter("cat");
                if(request.getParameter("cat") != null) {
                    query = em.createNamedQuery("Articulo.findAll", Articulo.class);
                    
                    List<Articulo> articulos = query.getResultList();
                    List<Articulo> filtrados = new ArrayList();
                    
                    for(Articulo art : articulos) {
                        if(art.getCategoriaId().getId() == Long.parseLong(metodo))
                            filtrados.add(art);
                    }
                    
                    request.setAttribute("listaSize", filtrados.size());
                    request.setAttribute("articulos", filtrados);
                    vista = "/tablaproductos.jsp";
                    rd = request.getRequestDispatcher(vista);
                    rd.forward(request, response);
                }
                
                break;
            case "/SrvArticulo/VerProducto":
                String prod = request.getParameter("prod");

                //Ya tengo el ID del producto, se busca, se pasa por parámetro y se
                //devuelve el control a una vista jsp para mostrar los datos

                query = em.createNamedQuery("Articulo.findById", Articulo.class);
                query.setParameter("id", Long.parseLong(prod));

                try {
                    Articulo art = (Articulo) query.getSingleResult();
                    request.setAttribute("producto", art);
                    
                    vista = "/verproducto.jsp";
                    rd = request.getRequestDispatcher(vista);
                    rd.forward(request, response);
                } catch (Exception e) {
                    vista = "/error.jsp";
                    request.setAttribute("errorMessage", e.getMessage());
                    rd = request.getRequestDispatcher(vista);
                    rd.forward(request, response);
                }
                break;
            case "/SrvArticulo/Recientes":
                //Fecha de ayer -> Fecha en formato UTC - 86400000 (milisegundos)
                Long ayer = (new Date()).getTime() - 86400000*3;
                List<Articulo> artRecientes;
                query = em.createNamedQuery("Articulo.findAll", Articulo.class);
                try {
                    artRecientes = query.getResultList();
                    int recienteActual = 0;

                    if (!artRecientes.isEmpty()) {
                        out.print("<table class=\"striped\">");
                        out.print("<tr>");
                        out.print("<th>Nombre</th>");
                        out.print("<th>Precio</th>");
                        out.print("<th>Fecha de publicación</th>");
                        out.print("<th>Categoria</th>");
                        out.print("</tr>");
                        String recLink;
                        while ((recienteActual < artRecientes.size())
                                && (artRecientes.get(recienteActual).getFechaPublicacion().getTime() > ayer)) {
                            out.print("<tr>");
                            Articulo actual = artRecientes.get(recienteActual);
                            recLink = "<td><a href=\"/OnceMoreTime/SrvArticulo/VerProducto?"
                                    + "prod="
                                    + actual.getId()
                                    + "\">"
                                    + actual.getNombre()
                                    + "</a></td>";
                            out.print(recLink);
                            out.print("<td>" + actual.getPrecio() + "</td>");
                            out.print("<td>" + actual.getFechaPublicacion().toString() + "</td>");
                            out.print("<td>" + actual.getCategoriaId().getNombre() + "</td>");
                            recienteActual++;
                            out.print("</tr>");
                        }
                        out.print("</table>");
                    } else {
                        out.println("No hay artículos recientes");
                    }
                } catch (Exception e) {
                    //No hay artículos
                    out.println("No hay artículos recientes");
                }
                break;
            case "/SrvArticulo/FormNuevoArticulo":
                vista = "/registrarproducto.jsp";
                rd = request.getRequestDispatcher(vista);
                rd.forward(request, response);
                break;
            case "/SrvArticulo/RegistrarArticulo":
                sesion = request.getSession();
                Articulo nuevo = new Articulo();

                nuevo.setNombre(request.getParameter("nombre"));
                nuevo.setDescripcion(request.getParameter("descripcion").toString());
                nuevo.setImagenUrl(request.getParameter("imagenurl").toString());
                nuevo.setPrecio(Double.parseDouble(request.getParameter("precio").toString()));

                Usuario vendedor;
                query = em.createNamedQuery("Usuario.findById", Usuario.class);
                query.setParameter("id", Long.parseLong(sesion.getAttribute("userID").toString()));
                vendedor = (Usuario) query.getSingleResult();

                nuevo.setVendedor(vendedor);
                nuevo.setFechaPublicacion(new Date());

                Categoria categoria;
                query = em.createNamedQuery("Categoria.findById", Categoria.class);
                query.setParameter("id", Long.parseLong(request.getParameter("categoria").toString()));
                categoria = (Categoria) query.getSingleResult();

                nuevo.setCategoriaId(categoria);
                try {

                    persist(nuevo);
                    vista = "/exito.jsp";
                    String successMessage = "Artículo registrado con éxito";
                    request.setAttribute("successMessage", successMessage);

                } catch (Exception e) {
                    vista = "/error.jsp";
                    String errorMessage = e.getMessage();
                    request.setAttribute("errorMessage", errorMessage);
                }

                rd = request.getRequestDispatcher(vista);
                rd.forward(request, response);

                break;
            default:
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
