<%-- 
    Document   : procesos-venta
    Created on : 09-11-2021, 11:06:49
    Author     : Asus
--%>

<%@page import="DTO.EstadoProcesoVenta"%>
<%@page import="Negocio.NegocioEstadoProcesoVenta"%>
<%@page import="DTO.Cliente"%>
<%@page import="Negocio.NegocioCliente"%>
<%@page import="DTO.CabeceraProcesoVenta"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Maipo Grande - Procesos de venta</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    </head>
    <body>
        <div>
            <h1 class="container mx-auto text-2xl">Procesos de venta</h1>
        </div>
        </br>
        <div class="container mx-auto">
            <% 
                List<CabeceraProcesoVenta> procesosVenta = (ArrayList<CabeceraProcesoVenta>)request.getAttribute("procesosVenta");

                String clasesBoton = "p-2 pl-5 pr-5 bg-green-500 text-white inline-block rounded hover:bg-green-400";

                out.print("<div class='p-4 mb-2 rounded bg-gray-100 grid grid-cols-7 font-bold text-gray-500'>");
                out.print("<div class='col-span-1 text-center'>ID</div>");
                out.print("<div class='col-span-1 text-center'>Rut Cliente</div>");
                out.print("<div class='col-span-2 text-center'>Razón Social</div>");
                out.print("<div class='col-span-1 text-center'>Fecha Emisión</div>");
                out.print("<div class='col-span-1 text-center'>Estado</div>");
                out.print("<div class='col-span-1 text-center'>Detalles</div>");
                out.print("</div>");
   
                for(CabeceraProcesoVenta procesoVenta : procesosVenta)
                {
                    NegocioCliente negocioCliente = new NegocioCliente();
                    Cliente cliente = negocioCliente.buscarCliente(procesoVenta.getRutCliente());
                    
                    NegocioEstadoProcesoVenta negocioEstadoPV = new NegocioEstadoProcesoVenta();
                    EstadoProcesoVenta estado = negocioEstadoPV.buscarEstadoProcesoVenta(procesoVenta.getIdEstado());
           
                    out.print("<div class='p-4 mb-2 rounded bg-gray-100 grid grid-cols-7'>");
                    out.print("<div class='col-span-1 flex items-center justify-center'>"+procesoVenta.getIdCabeceraVenta()+"</div>");
                    out.print("<div class='col-span-1 flex items-center justify-center'>"+cliente.getRut()+"-"+cliente.getDvRut()+"</div>");
                    out.print("<div class='col-span-2 flex items-center justify-center'>"+cliente.getRazonSocial()+"</div>");
                    out.print("<div class='col-span-1 flex items-center justify-center'>"+procesoVenta.getFechaEmision()+"</div>");
                    out.print("<div class='col-span-1 flex items-center justify-center'>"+estado.getDescripcion()+"</div>");
                    out.print("<div class='col-span-1 flex items-center justify-center'>");
                    out.print("<a class='"+clasesBoton+"' href='/maipogrande-web/ver-proceso-venta?idProcesoVenta="+procesoVenta.getIdCabeceraVenta()+"'>");
                    out.print("Ver");
                    out.print("</a>");
                    
                    // SI EL USUARIO ES TRANSPORTISTA, MOSTRAR ESTE BOTON POSTULAR
                    out.print("<a class='"+clasesBoton+" ml-2' href='/maipogrande-web/nueva-postulacion-transporte?idProcesoVenta="+procesoVenta.getIdCabeceraVenta()+"'>");
                    out.print("Postular");
                    out.print("</a>");
                    
                    out.print("</div>");
                    out.print("</div>");
                }
            %>
        </div>

        <% 
            if(request.getAttribute("postulacionExitosa") != null){
                out.print("<script>alert('Postulación ingresada con éxito.');</script> ");
            }
        %>
    </body>
</html>
