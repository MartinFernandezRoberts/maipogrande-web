<%-- Document : cliente Created on : 03-10-2021, 19:38:21 Author : Asus --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="servlets.Procesar"%>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>MaipoGrande - <% out.print( session.getAttribute("perfilUsuario") ); %></title>
  </head>
  <body>
      <h1>Bienvenido <% out.print( session.getAttribute("name") ); %> </h1>
      <h1>Usted es  <% out.print( session.getAttribute("tipoCliente") ); %> </h1>
      <h1>Giro: <% out.print( session.getAttribute("giroCliente") ); %> </h1>
    <li>
      <ul>
        <a href="">Ingresar proceso de venta</a>
      </ul>
      <ul>
        <a href="">Ver procesos de ventas activos</a>
      </ul>
      <ul>
        <a href="">Administar productos</a>
      </ul>
      <ul>
        <a href="LogoutServlet">Cerrar Sesión</a>
      </ul>
    </li>
  </body>
</html>
