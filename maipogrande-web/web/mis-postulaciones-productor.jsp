<%-- 
    Document   : mis-postulaciones-productor
    Created on : 09-12-2021, 19:50:32
    Author     : Asus
--%>

<%@page import="Negocio.NegocioCabeceraPostulacion"%>
<%@page import="DTO.Productor"%>
<%@page import="Negocio.NegocioProductor"%>
<%@page import="DTO.CabeceraPostulacion"%>
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
        <title>Maipo Grande - Procesos de venta</title>
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
                            out.print("<a href='/maipogrande-web/cargar-subastas' class='h-10 leading-10 border-b-2 border-dotted md:border-none'>Subastas de Transporte</a>");
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
            <h1 class="container mx-auto mt-8 text-2xl">Procesos de venta</h1>
        </div>
        </br>
        <div class="container mx-auto">
            <% 
                ArrayList<CabeceraPostulacion> postulacionesProductor = (ArrayList<CabeceraPostulacion>)request.getAttribute("postulacionesProductor");
                if(postulacionesProductor==null){
                    int idUsuario = Integer.parseInt(session.getAttribute("idUsuario").toString());

                    NegocioProductor negocioProductor = new NegocioProductor();
                    Productor productor = negocioProductor.buscarProductorUsuario(idUsuario);

                    NegocioCabeceraPostulacion negocioCabeceraPostulacion = new NegocioCabeceraPostulacion();
                    postulacionesProductor = negocioCabeceraPostulacion.listarCabeceraPostulacionProductor(productor.getRutProductor());        

                }

                String clasesBoton = "p-2 pl-5 pr-5 bg-green-500 text-white inline-block rounded hover:bg-green-400";

                out.print("<div class='p-4 mb-2 rounded bg-gray-100 grid grid-cols-4 font-bold text-gray-500'>");
                out.print("<div class='col-span-1 text-center'>ID Postulación</div>");
                out.print("<div class='col-span-1 text-center'>Fecha Emisión</div>");
                out.print("<div class='col-span-1 text-center'>Proceso de Venta</div>");
                out.print("<div class='col-span-1 text-center'>Ver Detalles</div>");
                out.print("</div>");
   
                for(CabeceraPostulacion postulacionProductor : postulacionesProductor)
                {
           
                    out.print("<div class='p-4 mb-2 rounded bg-gray-100 grid grid-cols-4'>");
                    out.print("<div class='col-span-1 flex items-center justify-center'>"+postulacionProductor.getIdCabeceraPostulacion()+"</div>");
                    out.print("<div class='col-span-1 flex items-center justify-center'>"+postulacionProductor.getFechaEmision()+"</div>");
                    out.print("<div class='col-span-1 flex items-center justify-center'>"+postulacionProductor.getIdCabeceraPV()+"</div>");
                    out.print("<div class='col-span-1 flex items-center justify-center'>");
                    out.print("<a class='"+clasesBoton+"' href='/maipogrande-web/ver-postulacion-productor?idPostulacion="+postulacionProductor.getIdCabeceraPostulacion()+"'>");
                    out.print("Ver Detalles");
                    out.print("</a>");
                    out.print("</div>");
                    out.print("</div>");
                }
            %>
        </div>
    </body>
</html>
