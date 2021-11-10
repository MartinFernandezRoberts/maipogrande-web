<%-- 
    Document   : detalle-proceso-venta
    Created on : 09-11-2021, 17:07:07
    Author     : Asus
--%>

<%@page import="DTO.Producto"%>
<%@page import="Negocio.NegocioProducto"%>
<%@page import="DTO.EstadoProcesoVenta"%>
<%@page import="Negocio.NegocioEstadoProcesoVenta"%>
<%@page import="DTO.DetalleProcesoVenta"%>
<%@page import="DTO.Cliente"%>
<%@page import="Negocio.NegocioCliente"%>
<%@page import="java.util.List"%>
<%@page import="DTO.CabeceraProcesoVenta"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Maipo Grande - Detalle Proceso de Venta</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    </head>
    <body>
        <div>
            <h1 class="container mx-auto text-2xl">Proceso de venta</h1>
        </div>
        </br>
        <div class="container mx-auto bg-gray-100 p-4 mb-4">
            <h2 class="container mx-auto text-xl">Datos</h2>
            <br/>
            <% 
                List<DetalleProcesoVenta> detallesProcesoVenta = (ArrayList<DetalleProcesoVenta>)request.getAttribute("listaDetallesProcesoVenta");
                CabeceraProcesoVenta cabeceraProcesoVenta = (CabeceraProcesoVenta)request.getAttribute("cabeceraProcesoVenta");
                
                NegocioCliente negocioCliente = new NegocioCliente();
                Cliente cliente = negocioCliente.buscarCliente(cabeceraProcesoVenta.getRutCliente());

                NegocioEstadoProcesoVenta negocioEstadoPV = new NegocioEstadoProcesoVenta();
                EstadoProcesoVenta estado = negocioEstadoPV.buscarEstadoProcesoVenta(cabeceraProcesoVenta.getIdEstado());
           
                out.print("<p>ID: "+cabeceraProcesoVenta.getIdCabeceraVenta()+"</p>");
                out.print("<p>Rut Cliente: "+cliente.getRut()+"-"+cliente.getDvRut()+"</p>");
                out.print("<p>Razon Social: "+cliente.getRazonSocial()+"</p>");
                out.print("<p>Fecha Emisión: "+cabeceraProcesoVenta.getIdCabeceraVenta()+"</p>");
                out.print("<p>Estado: "+estado.getDescripcion()+"</p>");
                out.print("<p>Observaciones: "+cabeceraProcesoVenta.getObservaciones()+"</p>");
            %>
        </div>
        <div class="container mx-auto bg-gray-100 p-4 mb-4">
           <h2 class="container mx-auto text-xl">Productos</h2>
           <br/>
           <% 
                out.print("<div class='p-4 mb-2 rounded bg-gray-100 grid grid-cols-6 font-bold text-gray-500'>");
                out.print("<div class='col-span-1 text-center'>ID</div>");
                out.print("<div class='col-span-2 text-center'>Nombre</div>");
                out.print("<div class='col-span-1 text-center'>Cantidad</div>");
                out.print("<div class='col-span-1 text-center'>Precio Unitario</div>");
                out.print("<div class='col-span-1 text-center'>Total</div>");
                out.print("</div>");
                
                int total = 0;
                
                for(DetalleProcesoVenta detalleProcesoVenta : detallesProcesoVenta)
                {
                    NegocioProducto negocioProducto = new NegocioProducto();
                    Producto producto = negocioProducto.buscarProducto(detalleProcesoVenta.getIdProducto());
                    
                    out.print("<div class='p-4 mb-2 rounded bg-gray-100 grid grid-cols-6'>");
                    out.print("<div class='col-span-1 flex items-center justify-center'>"+producto.getIdProducto()+"</div>");
                    out.print("<div class='col-span-2 flex items-center justify-center'>"+producto.getNombreProducto()+"</div>");
                    out.print("<div class='col-span-1 flex items-center justify-center'>"+detalleProcesoVenta.getCantidad()+"</div>");
                    out.print("<div class='col-span-1 flex items-center justify-center'>"+detalleProcesoVenta.getPrecioUnitario()+"</div>");
                    out.print("<div class='col-span-1 flex items-center justify-center'>$"+detalleProcesoVenta.getCantidad()*detalleProcesoVenta.getPrecioUnitario()+"</div>");
                    out.print("</div>");
                    
                    total += detalleProcesoVenta.getCantidad()*detalleProcesoVenta.getPrecioUnitario();
                }
                
                out.print("<div class='p-4 mb-2 rounded bg-gray-100 grid grid-cols-6'>");
                out.print("<div class='col-span-5 flex items-center justify-end font-bold'>Total</div>");
                out.print("<div class='col-span-1 flex items-center justify-center font-bold'>$"+total+"</div>");
                out.print("</div>");
            %>
        </div>
        <form class="container mx-auto bg-gray-100 p-4 mb-4" action="/maipogrande-web/procesar-postulacion-productor" method="POST">
           <h2 class="container mx-auto text-xl">Postulación</h2>
           <br/>
           <% 
                out.print("<div class='p-4 mb-2 rounded bg-gray-100 grid grid-cols-5 font-bold text-gray-500'>");
                out.print("<div class='col-span-1 text-center'>ID</div>");
                out.print("<div class='col-span-2 text-center'>Nombre</div>");
                out.print("<div class='col-span-1 text-center'>Cantidad</div>");
                out.print("<div class='col-span-1 text-center'>Precio Unitario</div>");
                out.print("</div>");
                
                int cantidadProductos = 0;
                
                for(DetalleProcesoVenta detalleProcesoVenta : detallesProcesoVenta)
                {
                    
                    NegocioProducto negocioProducto = new NegocioProducto();
                    Producto producto = negocioProducto.buscarProducto(detalleProcesoVenta.getIdProducto());
                    
                    out.print("<div class='p-4 mb-2 rounded bg-gray-100 grid grid-cols-5'>");
                    out.print("<div class='col-span-1 flex items-center justify-center'>"+producto.getIdProducto()+"</div>");
                    out.print("<div class='col-span-2 flex items-center justify-center'>"+producto.getNombreProducto()+"</div>");
                    out.print("<div class='col-span-1 flex items-center justify-center'>"+
                            "<input class='border-2 border-gray-300 hover:border-gray-400' type='number' name='cantidad"+producto.getIdProducto()+"' />"
                            +"</div>");
                    out.print("<div class='col-span-1 flex items-center justify-center'>"+
                            "<input class='border-2 border-gray-300 hover:border-gray-400' type='number' name='precio"+producto.getIdProducto()+"' />"
                            +"</div>");
                    out.print("</div>");
                    out.print("<input type='hidden' name='id"+producto.getIdProducto()+"' value='"+producto.getIdProducto()+"'/>");
                    
                    cantidadProductos+=1;
                }
            %>
        <input type="hidden" name="cantidadProductos" value="<% out.print(cantidadProductos); %>"/>
        <div class="container mx-auto p-4 flex justify-end">
            <input type="submit" class="p-2 pl-5 pr-5 bg-green-500 text-white inline-block rounded hover:bg-green-400 font-bold uppercase">Postular</a>
        </div>
        </form>
    </body>
</html>
