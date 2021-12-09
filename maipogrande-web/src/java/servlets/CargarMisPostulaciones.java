/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DTO.CabeceraPostulacion;
import DTO.DetallePostulacion;
import DTO.DetalleSubasta;
import DTO.EmpresaTransporte;
import Negocio.NegocioCabeceraPostulacion;
import Negocio.NegocioDetallePostulacion;
import Negocio.NegocioDetalleSubasta;
import Negocio.NegocioEmpresaTransporte;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus
 */
public class CargarMisPostulaciones extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        int idUsuario = Integer.parseInt(session.getAttribute("idUsuario").toString());
                    
        NegocioEmpresaTransporte negocioEmpresaTransporte = new NegocioEmpresaTransporte();
        EmpresaTransporte empresaTransporte = negocioEmpresaTransporte.buscarEmpresaTransporteUsuario(idUsuario);
                
 
        NegocioDetalleSubasta negocioDetalleSubasta = new NegocioDetalleSubasta();
        ArrayList<DetalleSubasta> listaPostulaciones = negocioDetalleSubasta.listarDetallesSubastaEmpresa(empresaTransporte.getIdEmpresaTransporte());        
        
        request.setAttribute("subastasTransporte", listaPostulaciones);
        request.getRequestDispatcher("mis-postulaciones-transporte.jsp").forward(request, response);
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
