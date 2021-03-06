/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DTO.CabeceraPostulacion;
import DTO.CabeceraProcesoVenta;
import DTO.DetallePostulacion;
import DTO.Productor;
import Negocio.NegocioCabeceraPostulacion;
import Negocio.NegocioCabeceraProcesoVenta;
import Negocio.NegocioDetalleOrdenCompra;
import Negocio.NegocioDetallePostulacion;
import Negocio.NegocioProductor;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus
 */
@WebServlet(name = "ProcesarPostulacionProductor", urlPatterns = {"/procesar-postulacion-productor"})
public class ProcesarPostulacionProductor extends HttpServlet {

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

        try{
            HttpSession session = request.getSession();
            int idUsuario = Integer.parseInt(session.getAttribute("idUsuario").toString());

            NegocioProductor negocioProductor = new NegocioProductor();
            Productor productor = negocioProductor.buscarProductorUsuario(idUsuario);

            int cantidadProductos = Integer.parseInt(request.getParameter("cantidadProductos"));
            int rutProductor = productor.getRutProductor();
            int idCabeceraPV = Integer.parseInt(request.getParameter("IdCabeceraVenta"));

            Date fechaEmision = new Date();

            CabeceraPostulacion cabeceraPostulacion = new CabeceraPostulacion();
            cabeceraPostulacion.setIdCabeceraPV(idCabeceraPV);
            cabeceraPostulacion.setRutProductor(rutProductor);
            cabeceraPostulacion.setFechaEmision(fechaEmision);
            cabeceraPostulacion.setIdEstadoPostulacion(1);

            NegocioCabeceraPostulacion negocioCabeceraPostulacion = new NegocioCabeceraPostulacion();
            negocioCabeceraPostulacion.insertarCabeceraPostulacion(cabeceraPostulacion);
            
            cabeceraPostulacion = negocioCabeceraPostulacion.buscarUltimaInsercion();

            for(int i=0; i<cantidadProductos; i++){
                String producto = request.getParameter("producto"+(i+1));
                if(producto != null){

                    int idProducto = Integer.parseInt(producto);
                    int cantidad = Integer.parseInt(request.getParameter("cantidad"+(i+1)));
                    int precio = Integer.parseInt(request.getParameter("precio"+(i+1)));
                    
                    DetallePostulacion detallePostulacion = new DetallePostulacion();
                    detallePostulacion.setIdCabeceraPostulacion(cabeceraPostulacion.getIdCabeceraPostulacion());
                    detallePostulacion.setCantidad(cantidad);
                    detallePostulacion.setPrecioUnitario(precio);
                    detallePostulacion.setIdProducto(idProducto);
                    
                    NegocioDetallePostulacion negocioDetallePostulacion = new NegocioDetallePostulacion();
                    negocioDetallePostulacion.insertarDetallePostulacion(detallePostulacion);
                }
            }
            
            ArrayList<CabeceraPostulacion> listaPostulaciones = negocioCabeceraPostulacion.listarCabeceraPostulacionProductor(productor.getRutProductor());        

            request.setAttribute("postulacionesProductor", listaPostulaciones);

            request.setAttribute("postulacionExitosa", true);
            request.getRequestDispatcher("mis-postulaciones-productor.jsp").forward(request, response);
            
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
