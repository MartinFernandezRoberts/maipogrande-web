/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import DTO.Cliente;
import Negocio.Negocio;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Grupo2
 */
@WebService(serviceName = "webServiceCliente")
public class webServiceCliente {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "webInsertarCliente")
    public void webInsertarCliente(@WebParam(name = "cliente") Cliente cliente) {
       Negocio auxNegocio = new Negocio();
       auxNegocio.ingresaCliente(cliente);
    }
}
