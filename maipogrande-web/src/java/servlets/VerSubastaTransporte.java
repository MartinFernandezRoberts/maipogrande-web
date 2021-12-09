/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DTO.CabeceraProcesoVenta;
import DTO.CabeceraSubasta;
import DTO.DetalleProcesoVenta;
import Negocio.NegocioCabeceraProcesoVenta;
import Negocio.NegocioCabeceraSubasta;
import Negocio.NegocioDetalleProcesoVenta;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Asus
 */
public class VerSubastaTransporte extends HttpServlet {

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
        
        int idSubastaTransporte = Integer.parseInt(request.getParameter("idSubastaTransporte"));
        
        NegocioCabeceraSubasta negocioCabeceraSubasta = new NegocioCabeceraSubasta();
        CabeceraSubasta cabeceraSubasta = negocioCabeceraSubasta.buscarCabeceraSubasta(idSubastaTransporte);
        
        NegocioCabeceraProcesoVenta negocioCabeceraPV = new NegocioCabeceraProcesoVenta();
        CabeceraProcesoVenta cabeceraProcesosVenta = negocioCabeceraPV.buscarCabeceraProcesoVenta(cabeceraSubasta.getIdCabeceraProcesoVenta());
        
        NegocioDetalleProcesoVenta negocioDetallePV = new NegocioDetalleProcesoVenta();
        ArrayList<DetalleProcesoVenta> listaDetallesProcesoVenta = negocioDetallePV.listarDetallesProcesoVenta(cabeceraSubasta.getIdCabeceraProcesoVenta());
        
        request.setAttribute("cabeceraSubasta", cabeceraSubasta);
        request.setAttribute("cabeceraProcesoVenta", cabeceraProcesosVenta);
        request.setAttribute("listaDetallesProcesoVenta", listaDetallesProcesoVenta);
        request.getRequestDispatcher("detalle-subasta-transporte.jsp").forward(request, response);
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
