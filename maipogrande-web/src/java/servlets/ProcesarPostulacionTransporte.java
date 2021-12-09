/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DTO.CabeceraProcesoVenta;
import DTO.DetalleSubasta;
import Negocio.NegocioCabeceraProcesoVenta;
import Negocio.NegocioDetalleSubasta;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Asus
 */
@WebServlet(name = "ProcesarPostulacionTransporte", urlPatterns = {"/procesar-postulacion-transporte"})
public class ProcesarPostulacionTransporte extends HttpServlet {

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
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        
        try{
            String capacidadCarga = request.getParameter("capacidadCarga");
            String refrigeracion = request.getParameter("refrigeracion");
            String pesoMaximo = request.getParameter("pesoMaximo");
            String extras = request.getParameter("extras");
            int precio = Integer.parseInt(request.getParameter("precio"));
            Date fechaEstimadaEntrega = formatter.parse(request.getParameter("fechaEstimadaEntrega"));
            int idCabeceraSubasta = Integer.parseInt(request.getParameter("idCabeceraSubasta"));
            int idEmpresaTransporte = Integer.parseInt(request.getParameter("idEmpresaTransporte"));

            NegocioDetalleSubasta negocioDetalleSubasta = new NegocioDetalleSubasta();
            DetalleSubasta detalleSubasta = new DetalleSubasta();
            detalleSubasta.setFechaEstimadaEntrega(fechaEstimadaEntrega);
            detalleSubasta.setCapacidadCarga(capacidadCarga);
            detalleSubasta.setRefrigeracion(refrigeracion);
            detalleSubasta.setPesoMaximo(pesoMaximo);
            detalleSubasta.setExtras(extras);
            detalleSubasta.setPrecio(precio);
            detalleSubasta.setIdCabeceraSubasta(idCabeceraSubasta);
            detalleSubasta.setIdEmpresaTransporte(idEmpresaTransporte);
            
            negocioDetalleSubasta.insertarDetalleProcesoVenta(detalleSubasta);

            request.setAttribute("postulacionExitosa", true);
            request.getRequestDispatcher("mis-postulaciones-transporte.jsp").forward(request, response);
            
        }catch(Exception ex){
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Error</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Error: " + ex + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
