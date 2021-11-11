<%-- 
    Document   : nueva-postulacion
    Created on : 09-11-2021, 18:13:50
    Author     : Asus
--%>

<%@page import="Negocio.NegocioEstadoSubasta"%>
<%@page import="DTO.EstadoSubasta"%>
<%@page import="DTO.CabeceraSubasta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Maipo Grande - Nueva Postulación Transporte</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    </head>
    <body>
        
        <div>
            <h1 class="container mx-auto text-2xl">Nueva Postulación de Transporte</h1>
        </div>
        </br>
        <div class="container mx-auto bg-gray-100 p-4 mb-4">
            <h2 class="container mx-auto text-xl">Datos de Subasta</h2>
            <br/>
            <%
                CabeceraSubasta cabeceraSubasta = (CabeceraSubasta)request.getAttribute("cabeceraSubasta");
                
                NegocioEstadoSubasta negocioEstadoSubasta = new NegocioEstadoSubasta();
                EstadoSubasta estado = negocioEstadoSubasta.buscarEstadoProcesoVenta(cabeceraSubasta.getIdEstadoSubasta());
           
                out.print("<p>ID: "+cabeceraSubasta.getIdCabeceraSubasta()+"</p>");
                out.print("<p>Fecha Límite Entrega: "+cabeceraSubasta.getFechaLimiteEntrega()+"</p>");
                out.print("<p>Estado: "+estado.getDescripcion()+"</p>");
            %>
        </div>
        
        <form class="container mx-auto bg-gray-100 p-4 mb-4" method="POST" action="/maipogrande-web/procesar-postulacion-transporte">
            <h2 class="container mx-auto text-xl">Postulación</h2>
            <br/>
            <div class="mb-2">
                <label for="capacidadCarga">Capacidad de Carga:</label>
                <input class="border-2 border-gray-300 hover:border-gray-400" type="text" name="capacidadCarga" id="capacidadCarga" required/>
            </div>
            <div class="mb-2">
                <label for="refrigeracion">Refrigeración:</label>
                <input class="border-2 border-gray-300 hover:border-gray-400" type="text" name="refrigeracion"  id="refrigeracion" required/>
            </div>
            <div class="mb-2">
                <label for="pesoMaximo">Peso Máximo:</label>
                <input class="border-2 border-gray-300 hover:border-gray-400" type="text" name="pesoMaximo" id="pesoMaximo" required/>
            </div>
            <div class="mb-2">
                <label for="extras">Extras:</label>
                <input class="border-2 border-gray-300 hover:border-gray-400" type="text" name="extras" id="extras" required/>
            </div>
            <div class="mb-2">
                <label for="precio">Precio:</label>
                <input class="border-2 border-gray-300 hover:border-gray-400" type="number" name="precio" id="precio" required/>
            </div>
            <div class="mb-4">
                <label for="fechaEstimadaEntrega">Fecha Estimada Entrega:</label>
                <input class="border-2 border-gray-300 hover:border-gray-400" type="date" name="fechaEstimadaEntrega" id="fechaEstimadaEntrega" required/>
            </div>
            
            <input type="hidden" name="idCabeceraSubasta" value="<% out.print(cabeceraSubasta.getIdCabeceraSubasta()); %>"/>
            <!-- AGREGAR ID EMPRESA TRANSPORTE EN INPUT HIDDEN (SACAR DE SESION) -->
            <!--<input type="hidden" name="idEmpresaTransporte" value=<% // out.print(idEmpresaTransporte); %>/>-->
            
            <input type="submit" value="Confirmar postulación" class="p-2 pl-5 pr-5 bg-green-500 text-white inline-block rounded hover:bg-green-400 cursor-pointer"/>
        </form>
    </body>
</html>
