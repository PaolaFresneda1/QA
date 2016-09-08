/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;

import com.Entity.Cliente;
import com.Entity.Usuario;
import com.Entity.Usuariotienerol;
import com.Entity.UsuariotienerolPK;
import com.Facade.ClienteFacade;
import com.Facade.UsuariotienerolFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Lina M
 */
@Named(value = "controladorCliente")
@SessionScoped
public class ControladorCliente implements Serializable {

    @Inject
    ClienteFacade clienteFacade;
    
     @Inject
    UsuariotienerolFacade usuarioRolFacade;
    /**
     * Creates a new instance of ControladorCliente
     */
    public ControladorCliente() {
    }
    
    public List<Cliente> listarClientes(){
    List<Cliente> listaClientes;
    listaClientes= clienteFacade.findAll();
    
    return listaClientes;
    }
    
    public void registrarCliente(){
    FacesContext objFacesContext = FacesContext.getCurrentInstance();
        ExternalContext objExternalContext = objFacesContext.getExternalContext();
        Map parametros = objExternalContext.getRequestParameterMap();

        Cliente objCliente = new Cliente();
        
        objCliente.setCedulaCliente(Integer.parseInt(""+parametros.get("documentoUsuario")));
        objCliente.setTipoCliente(""+parametros.get("tipoCliente"));
        objCliente.setEstado("Por aceptar");
        clienteFacade.create(objCliente);
        
    }
    
    public void aceptarCliente(Cliente temp ) {
        
        FacesContext objFaces = FacesContext.getCurrentInstance();
        ExternalContext objExternal = objFaces.getExternalContext();
        Map params = objExternal.getRequestParameterMap();
        
        Cliente miCliente = temp;
        miCliente.setEstado("Aceptado");
        clienteFacade.edit(miCliente);
        
        UsuariotienerolPK usuRoles= new UsuariotienerolPK();
            
        int usuario= Integer.parseInt(""+params.get("idUsuario"));
        usuRoles.setIdUsuario(usuario);
        usuRoles.setIdRol(2);
        
        Usuariotienerol usuarioR= new Usuariotienerol();
        usuarioR.setUsuariotienerolPK(usuRoles);
        
        usuarioRolFacade.create(usuarioR);
    
    }
    
     public void cancelarCliente(Cliente temp, Usuario usu) {
         
        FacesContext objFaces = FacesContext.getCurrentInstance();
        ExternalContext objExternal = objFaces.getExternalContext();
        Map params = objExternal.getRequestParameterMap();
        
        Cliente miCliente = temp;
        miCliente.setEstado("Cancelado");
        clienteFacade.edit(miCliente);
        
        
        Usuario usuario = new Usuario(); 
        usuario.setIdUsuario(Integer.parseInt(""+params.get("idUsuario")));
        
        Usuariotienerol usuRoles= new Usuariotienerol();
        usuRoles.setUsuario(usuario);
        
        usuarioRolFacade.remove(usuRoles);
    }
     
     public String contarClientes(){
         return ""+clienteFacade.count();
     }
     
}
