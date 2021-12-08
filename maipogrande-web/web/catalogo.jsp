<%-- 
    Document   : catalogo
    Created on : 08-12-2021, 13:20:30
    Author     : Asus
--%>

<%@page import="DTO.ProductoCarro"%>
<%@page import="DTO.CarroCompras"%>
<%@page import="DTO.Cliente"%>
<%@page import="Negocio.NegocioCliente"%>
<%@page import="java.util.List"%>
<%@page import="DTO.Producto"%>
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
<!--              <a
              href="/maipogrande-web/cargar-procesos-venta"
              class="h-10 leading-10 border-b-2 border-dotted md:border-none">
                Procesos de Venta
            </a>-->
<!--            <a
              href="#Nosotros"
              class="h-10 leading-10 border-b-2 border-dotted md:border-none">
                Sobre Nosotrxs
            </a>-->
<!--            <a
              href="#Servicios"
              class="h-10 leading-10 border-b-2 border-dotted md:border-none">
                Servicios
            </a>-->
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
                            out.print("<a href='/maipogrande-web/cargar-postulaciones-productor' class='h-10 leading-10 border-b-2 border-dotted md:border-none'>Mis postulaciones</a>");
                            break;
                    }
                    
                    out.print("<a href='LogoutServlet' class='px-4 py-2 md:border-none md:bg-red-600 md:rounded-full'>Cerrar Sesión</a>");                
                }
            %>

          </div>
                    <!-- Menu mov-->
<!--          <div
              class="flex flex-col items-stretch w-screen text-xl text-center transform bg-black md:flex-row md:translate-y-0 md:space-x-5 md:items-center md:justify-end md:pr-3 md:hidden" 
              id="navM"
              style="display: none;" >
            
              <a
              href="/maipogrande-web/"
              class="h-10 leading-10 border-b-2 border-dotted md:border-none">
                Inicio
            </a>
            <a
              href="#Nosotros"
              class="h-10 leading-10 border-b-2 border-dotted md:border-none">
                Sobre Nosotrxs
            </a>
            <a
              href="#Servicios"
              class="h-10 leading-10 border-b-2 border-dotted md:border-none">
                Servicios
            </a>
            <a
              href="/maipogrande-web/login.html"
              class="h-10 leading-10 border-b-2 border-dotted md:border-none md:bg-red-600 md:rounded-full md:w-24">
                Acceso
            </a>
          </div>-->
        </div>
      </div>
        
    <!-- ############################ -->
    <!-- ######### FIN MENU ######### -->
    <!-- ############################ -->
        
        <div>
            <h1 class="container mx-auto mt-8 text-2xl">Cat&aacute;logo</h1>
        </div>
        </br>
        <div class="container mx-auto">
            <% 
                List<Producto> productos = (ArrayList<Producto>)request.getAttribute("productos");

                String clasesBoton = "p-2 pl-5 pr-5 bg-green-500 text-white inline-block rounded hover:bg-green-400";

                out.print("<div class='p-4 mb-2 rounded bg-gray-100 grid grid-cols-5 font-bold text-gray-500'>");
                out.print("<div class='col-span-1 text-center'>ID</div>");
                out.print("<div class='col-span-1 text-center'>Nombre</div>");
                out.print("<div class='col-span-1 text-center'>Precio</div>");
                out.print("<div class='col-span-1 text-center'>Cantidad</div>");
                out.print("<div class='col-span-1 text-center'>Agregar al Carro</div>");
                out.print("</div>");
   
                for(Producto producto : productos)
                {           
                    out.print("<form class='p-4 mb-2 rounded bg-gray-100 grid grid-cols-5' action='/maipogrande-web/procesar-agregar-al-carro' method='POST'>");
                    out.print("<div class='col-span-1 flex items-center justify-center'>"+producto.getIdProducto()+"</div>");
                    out.print("<div class='col-span-1 flex items-center justify-center'>"+producto.getNombreProducto()+"</div>");
                    out.print("<div class='col-span-1 flex items-center justify-center'>$"+producto.getPrecio()+"</div>");
                    out.print("<div class='col-span-1 flex items-center justify-center'>");
                    out.print("<input type='number' name='cantidad' required class='border-2 border-gray-300 hover:border-gray-400'/>");
                    out.print("</div>");
                    out.print("<div class='col-span-1 flex items-center justify-center'>");
                    out.print("<input type='submit' value='Agregar al carro' class='"+clasesBoton+"'>");
                    out.print("</div>");
                    out.print("<input type='hidden' name='idProducto' value='"+producto.getIdProducto()+"' />");
                    out.print("</form>");
                }
            %>
        </div>
        
        <% 
            if(request.getAttribute("agregaAlCarroExitoso") != null){
                out.print("<script>alert('Se ha agregado el producto al carro.');</script> ");
            }
        %>
    </body>
</html>
