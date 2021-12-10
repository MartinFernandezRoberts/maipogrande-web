<%-- 
    Document   : index
    Created on : 08-12-2021, 11:39:24
    Author     : Asus
--%>

<%@page import="DTO.ProductoCarro"%>
<%@page import="DTO.CarroCompras"%>
<%@page import="DTO.CarroCompras"%>
<%@page import="DTO.Cliente"%>
<%@page import="Negocio.NegocioCliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>MaipoGrande - Venta local y Exportaciones de Fruta.</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.4.1.min.js"></script>
        <script src="main.js" type="text/javascript"> </script>
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
        
        <div class="sliderAx h-auto mt-20">
            <div id="slider-1" class="container mx-auto">
                <div class="bg-cover bg-center  h-auto text-white py-24 px-10 object-fill" style="background-image: url(https://www.dosfarma.com/blog/wp-content/uploads/2020/02/vitaminas-1024x672.jpg)">
                    <div class="md:w-1/2 w-auto">
                        <p class="font-bold text-sm uppercase bg-purple-400 bg-opacity-30 w-auto">Que no te falten vitaminas</p>
                        <p class="text-3xl font-bold bg-purple-500 bg-opacity-30">Somos líderes en el mercado mundial</p>
                        <p class="text-2xl mb-10 leading-none bg-purple-400 bg-opacity-30">Tenemos una solución para cada tipo de cliente</p>
                        <a href="#" class="bg-purple-800 py-4 px-8 text-white font-bold uppercase text-xs rounded hover:bg-gray-200 hover:text-gray-800">Contáctanos</a>
                    </div>  
                </div> 
            <!-- container -->
            <br>
            </div>
            <div id="slider-2" class="container mx-auto">
                <div class="bg-cover bg-top  h-auto text-white py-24 px-10 object-fill" style="background-image: url(https://actualidadjuridica.doe.cl/wp-content/uploads/2021/08/Transporte.jpg)">  
                    <div class="md:w-1/2 w-auto">
                        <p class="font-bold text-sm uppercase bg-purple-400 bg-opacity-30 w-auto">Transportes</p>
                        <p class="text-3xl font-bold bg-purple-500 bg-opacity-30 w-auto">Calidad donde estés</p>
                        <p class="text-2xl mb-10 leading-none bg-purple-400 bg-opacity-30 w-auto">Conoce nuestros transportistas asociados</p>
                        <a href="#" class="bg-purple-800 py-4 px-8 text-white font-bold uppercase text-xs rounded hover:bg-gray-200 hover:text-gray-800">Saber más</a>
                    </div> 
                </div> 
            <!-- container -->
            <br>
            </div>
        </div>
        <div  class="flex justify-between w-12 mx-auto pb-2">
            <button id="sButton1" onclick="sliderButton1()" class="bg-purple-400 rounded-full w-4 pb-2 "></button>
            <button id="sButton2" onclick="sliderButton2() " class="bg-purple-400 rounded-full w-4 p-2"></button>
        </div>
    
        <% 
            if(request.getAttribute("error") != null){
                out.print("<script>alert('El nombre de usuario o contraseña ingresados no son correctos.');</script> ");
            }
        %>
    </body>
</html>
