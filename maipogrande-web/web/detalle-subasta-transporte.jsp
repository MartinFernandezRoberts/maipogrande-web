<%-- 
    Document   : detalle-subasta-transporte
    Created on : 09-12-2021, 13:35:44
    Author     : Asus
--%>

<%@page import="Negocio.NegocioEmpresaTransporte"%>
<%@page import="DTO.EmpresaTransporte"%>
<%@page import="DTO.EstadoSubasta"%>
<%@page import="Negocio.NegocioEstadoSubasta"%>
<%@page import="DTO.CabeceraSubasta"%>
<%@page import="DTO.Producto"%>
<%@page import="Negocio.NegocioProducto"%>
<%@page import="DTO.EstadoProcesoVenta"%>
<%@page import="Negocio.NegocioEstadoProcesoVenta"%>
<%@page import="DTO.CabeceraProcesoVenta"%>
<%@page import="DTO.DetalleProcesoVenta"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DTO.ProductoCarro"%>
<%@page import="DTO.CarroCompras"%>
<%@page import="DTO.Cliente"%>
<%@page import="Negocio.NegocioCliente"%>
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
        
        <!-- ############################# -->
        <!-- ######## INICIO MENU ######## -->
        <!-- ############################# -->
        
        <div class="flex bg-black">
        <!-- Nav -->
        <div
          class="flex flex-wrap justify-between w-screen h-20 text-white bg-black md:flex-nowrap"
        >
          <!-- Logo -->
          <div class="z-30 flex items-center h-full pl-3 space-x-3 bg-black">
                <img src="https://img.icons8.com/external-justicon-flat-justicon/50/000000/external-fruit-thanksgiving-justicon-flat-justicon.png" alt='MaipoGrande'/>
            <p class="text-2xl text-">Maipo Grande</p>
          </div>
      <!-- MenuButton -->
      <button
        class="z-30 flex items-center justify-end flex-grow pr-3 bg-black focus:outline-none md:hidden"
        onClick="toggleNav()"        
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          class="w-8 h-8"
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M4 6h16M4 12h16M4 18h16"
          />
        </svg>
      </button>

          <!-- Menu -->          
          <div
              class="hidden md:flex flex-col items-stretch w-screen text-xl text-center transform bg-black md:flex-row md:translate-y-0 md:space-x-5 md:items-center md:justify-end md:pr-3">
            
              <a
              href="/maipogrande-web/"
              class="h-10 leading-10 border-b-2 border-dotted md:border-none">
                Inicio
            </a>
            <%
                if(session.getAttribute("nombreUsuario") == null){
                    out.print("<a href='/maipogrande-web/login.html' class='px-4 py-2 md:border-none md:bg-red-600 md:rounded-full'>Iniciar Sesión</a>");                
                }else{
                    switch(Integer.parseInt(session.getAttribute("idPerfil").toString())){
                        case 2: // Cliente
                            
                            NegocioCliente negocioCliente = new NegocioCliente();
                            Cliente cliente = negocioCliente.buscarClienteUsuario(Integer.parseInt(session.getAttribute("idUsuario").toString()));
                            
                            
                            out.print("<a href='/maipogrande-web/procesar-cargar-catalogo' class='h-10 leading-10 border-b-2 border-dotted md:border-none'>Catálogo</a>");
                            
                            // Si es cliente interno, muestra ordenes de compra
                            // Si es externo o comerciante, muestra procesos de venta
                            out.print(cliente.getIdTipo() == 2 ?
                                    "<a href='/maipogrande-web/cargar-mis-ordenes-compra' class='h-10 leading-10 border-b-2 border-dotted md:border-none'>Mis Órdenes de Compra</a>"
                                    :"<a href='/maipogrande-web/cargar-mis-procesos-venta' class='h-10 leading-10 border-b-2 border-dotted md:border-none'>Mis Procesos de Venta</a>");
                            CarroCompras carroCompras = session.getAttribute("carro") != null ?
                                                            (CarroCompras)session.getAttribute("carro") : null;
                            int cantidadProductos = 0;
                            
                            if(carroCompras != null ){
                                for(ProductoCarro producto : carroCompras.getProductos()){
                                    cantidadProductos += producto.getCantidad();
                                }
                            }
                            
                            out.print("<a href='/maipogrande-web/procesar-ver-carro' class='h-10 leading-10 border-b-2 border-dotted md:border-none'>Carro"+(cantidadProductos>0?" ("+cantidadProductos+")":"")+"</a>");
                            break;
                            
                        case 3: // Transportista
                            out.print("<a href='/maipogrande-web/cargar-subastas-transporte' class='h-10 leading-10 border-b-2 border-dotted md:border-none'>Subastas de Transporte</a>");
                            out.print("<a href='/maipogrande-web/cargar-mis-postulaciones' class='h-10 leading-10 border-b-2 border-dotted md:border-none'>Mis Postulaciones</a>");
                            break;
           
                        case 4: // Productor
                            out.print("<a href='/maipogrande-web/cargar-procesos-venta' class='h-10 leading-10 border-b-2 border-dotted md:border-none'>Procesos de Venta</a>");
                            out.print("<a href='/maipogrande-web/cargar-mis-postulaciones-productor' class='h-10 leading-10 border-b-2 border-dotted md:border-none'>Mis postulaciones</a>");
                            break;
                    }
                    
                    out.print("<a href='LogoutServlet' class='px-4 py-2 md:border-none md:bg-red-600 md:rounded-full'>Cerrar Sesión</a>");                
                }
            %>

          </div>
        </div>
      </div>
        
    <!-- ############################ -->
    <!-- ######### FIN MENU ######### -->
    <!-- ############################ -->
        <div>
            <h1 class="container mx-auto mt-8 text-2xl">Proceso de venta</h1>
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
                EstadoSubasta estadoSubasta = negocioEstadoSubasta.buscarEstadoSubasta(cabeceraSubasta.getIdEstadoSubasta());
           
                out.print("<p>ID: "+cabeceraSubasta.getIdCabeceraSubasta()+"</p>");
                out.print("<p>Fecha Límite Entrega: "+cabeceraSubasta.getFechaLimiteEntrega()+"</p>");
                out.print("<p>Estado: "+estadoSubasta.getDescripcion()+"</p>");
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
            
            <%
                int idUsuario = Integer.parseInt(session.getAttribute("idUsuario").toString());
                NegocioEmpresaTransporte negocioEmpresaTransporte = new NegocioEmpresaTransporte();
                EmpresaTransporte empresaTransporte = negocioEmpresaTransporte.buscarEmpresaTransporteUsuario(idUsuario);
                
                out.print("<input type='hidden' name='idEmpresaTransporte' value='"+empresaTransporte.getIdEmpresaTransporte()+"'/>");
                
            %>
            
            <input type="submit" value="Confirmar postulación" class="p-2 pl-5 pr-5 bg-green-500 text-white inline-block rounded hover:bg-green-400 cursor-pointer"/>
        </form>
        
    </body>
</html>
