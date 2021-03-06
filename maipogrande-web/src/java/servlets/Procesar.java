/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import DTO.Usuario;
import DTO.Cliente;
import DTO.TipoCliente;
import DTO.Perfil;
import Negocio.NegocioTipoCliente;
import Negocio.NegocioCliente;
import Negocio.NegocioPerfil;
import Negocio.NegocioUsuario;

/**
 *
 * @author Asus
 */
public class Procesar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

       String nombreUsuario = request.getParameter("txtNombreUsuario");
       String password = request.getParameter("txtPassword");

       NegocioUsuario negocioUsuario = new NegocioUsuario();
       Usuario usuario = negocioUsuario.iniciarSesion(nombreUsuario, password);
       
       if(usuario.getIdUsuario()!=0){
           
           int idPerfil = usuario.getIdPerfil();
           int idUsuario = usuario.getIdUsuario();
           
           NegocioPerfil negocioPerfil = new NegocioPerfil();
           Perfil perfilUsuario = negocioPerfil.buscarPerfil(idPerfil);
           
           HttpSession session = request.getSession();
           session.setAttribute("nombreUsuario",nombreUsuario);
           session.setAttribute("idUsuario",idUsuario);
           session.setAttribute("idPerfil", idPerfil);
           session.setAttribute("descPerfil", perfilUsuario.getDescPerfil());
           session.setAttribute("carro", null);
                
           request.getRequestDispatcher("index.jsp").forward(request, response);
       }else{
            request.setAttribute("error", true);
            request.getRequestDispatcher("index.jsp").forward(request, response);
       }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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

}
