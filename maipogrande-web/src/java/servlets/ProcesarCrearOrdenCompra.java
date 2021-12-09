/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DTO.CabeceraOrdenCompra;
import DTO.CarroCompras;
import DTO.Cliente;
import DTO.DetalleOrdenCompra;
import DTO.ProductoCarro;
import Negocio.NegocioCabeceraOrdenCompra;
import Negocio.NegocioCliente;
import Negocio.NegocioDetalleOrdenCompra;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus
 */
public class ProcesarCrearOrdenCompra extends HttpServlet {

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
            
            Date fechaEmision = new Date();
            HttpSession session = request.getSession();
            int idUsuario = Integer.parseInt(session.getAttribute("idUsuario").toString());
                    
            NegocioCliente negocioCliente = new NegocioCliente();
            Cliente cliente = negocioCliente.buscarClienteUsuario(idUsuario);

            CabeceraOrdenCompra cabeceraOrdenCompra = new CabeceraOrdenCompra();
            cabeceraOrdenCompra.setFechaEmision(fechaEmision);
            cabeceraOrdenCompra.setRutCliente(cliente.getRut());
            
            NegocioCabeceraOrdenCompra negocioCabeceraOrdenCompra = new NegocioCabeceraOrdenCompra();
            negocioCabeceraOrdenCompra.insertarCabeceraOrdenCompra(cabeceraOrdenCompra);
            
            CabeceraOrdenCompra cabeceraOrdenCompraDB = negocioCabeceraOrdenCompra.buscarUltimaInsercion();

            CarroCompras carroCompras = 
                    session.getAttribute("carro") == null ? 
                        new CarroCompras() : (CarroCompras)session.getAttribute("carro");
            
            for(ProductoCarro producto : carroCompras.getProductos()){
                DetalleOrdenCompra detalleOrdenCompra = new DetalleOrdenCompra();
                detalleOrdenCompra.setIdCabeceraOrdenCompra(cabeceraOrdenCompraDB.getIdCabeceraOrdenCompra());
                detalleOrdenCompra.setCantidad(producto.getCantidad());
                detalleOrdenCompra.setPrecioUnitario(producto.getProducto().getPrecio());
                detalleOrdenCompra.setIdProducto(producto.getProducto().getIdProducto());

                NegocioDetalleOrdenCompra negocioDetalleOrdenCompra = new NegocioDetalleOrdenCompra();
                negocioDetalleOrdenCompra.insertarDetalleOrdenCompra(detalleOrdenCompra);
            }
            
            request.setAttribute("ingresoOrdenCompraExitoso", true);
                        
            session.setAttribute("carro", null);
            request.getRequestDispatcher("mis-ordenes-compra.jsp").forward(request, response);
            
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
