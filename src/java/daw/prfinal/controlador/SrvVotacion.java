/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.prfinal.controlador;

import daw.prfinal.modelo.Categoria;
import daw.prfinal.modelo.Usuario;
import daw.prfinal.modelo.Votacion;
import daw.prfinal.modelo.VotacionPK;
import java.io.IOException;
import java.io.PrintWriter;
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
public class SrvVotacion extends HttpServlet {

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

        RequestDispatcher rd;
        String accion;
        accion = request.getServletPath();
        String vista = "/index.jsp";
        HttpSession sesion;
        TypedQuery q;

        switch (accion) {
            case "/SrvVotacion/VotarUsuario":
                try {
                    long votante = Long.parseLong(request.getParameter("votante"));
                    long votado = Long.parseLong(request.getParameter("votado"));
                    VotacionPK vpk = new VotacionPK(votante, votado);
                    Votacion v = new Votacion();

                    v.setVotacionPK(vpk);
                    v.setFechaVoto(new Date());

                    Usuario emisor;
                    Usuario receptor;

                    q = em.createNamedQuery("Usuario.findById", Usuario.class);
                    q.setParameter("id", votado);
                    receptor = (Usuario) q.getSingleResult();

                    q = em.createNamedQuery("Usuario.findById", Usuario.class);
                    q.setParameter("id", votante);
                    emisor = (Usuario) q.getSingleResult();

                    v.setUsuario(receptor);
                    v.setUsuario1(emisor);

                    v.setPuntuacion(Integer.parseInt(request.getParameter("valor")));
                    try{
                        persist(v);
                    } catch (RuntimeException e) {
                        
                    }
                    List<Votacion> votos;

                    q = em.createNamedQuery("Votacion.findByVotado", Votacion.class);
                    q.setParameter("votado", votado);

                    votos = q.getResultList();

                    int votoSize = votos.size();
                    int totalNota = 0;

                    for (Votacion voto : votos) {
                        totalNota += voto.getPuntuacion();
                    }

                    float res = totalNota / votoSize;

                    receptor.setReputacion(res);
                    merge(receptor);

                    out.println("Voto emitido con éxito");
                } catch (Exception e) {
                    out.println("Error: " + e.getMessage());
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
    
    public void merge(Object object) {
        try {
            utx.begin();
            em.merge(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
}
