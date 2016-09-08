/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;

import com.Entity.Proveedor;
import com.Entity.Usuario;
import com.Entity.Usuariotienerol;
import com.Entity.UsuariotienerolPK;
import com.Facade.ProveedorFacade;
import com.Facade.UsuariotienerolFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


@Named(value = "controladorProveedor")
@SessionScoped
public class ControladorProveedor implements Serializable {

    @Inject
    ProveedorFacade objProveedorFacade;
    
     @Inject
    UsuariotienerolFacade usuarioRolFacade;

    public ControladorProveedor() {
    }

    public void registrarProveedor() {
        FacesContext objFacesContext = FacesContext.getCurrentInstance();
        ExternalContext objExternalContext = objFacesContext.getExternalContext();
        Map parametros = objExternalContext.getRequestParameterMap();

        Proveedor objProveedor = new Proveedor();
        objProveedor.setCedulaProveedor(Integer.parseInt("" + parametros.get("documentoUsuario")));
        objProveedor.setEmpresa("" + parametros.get("empresa"));
        objProveedor.setNit("" + parametros.get("nit"));
        objProveedorFacade.create(objProveedor);
    }
    
    public List<Proveedor> listarProveedores(){
        List<Proveedor> listaProveedor;
        listaProveedor= objProveedorFacade.findAll();
        
        return listaProveedor;
    }
    
       public void aceptarProovedor(Proveedor temp ) {
        
        FacesContext objFaces = FacesContext.getCurrentInstance();
        ExternalContext objExternal = objFaces.getExternalContext();
        Map params = objExternal.getRequestParameterMap();
        
        Proveedor miProveedor = temp;
        miProveedor.setEstado("Aceptado");
        objProveedorFacade.edit(miProveedor);
        
        UsuariotienerolPK usuRoles= new UsuariotienerolPK();
            
        int usuario= Integer.parseInt(""+params.get("idUsuario"));
        usuRoles.setIdUsuario(usuario);
        usuRoles.setIdRol(3);
        
        Usuariotienerol usuarioR= new Usuariotienerol();
        usuarioR.setUsuariotienerolPK(usuRoles);
        
        usuarioRolFacade.create(usuarioR);
    
    }
    
     public void cancelarProveedor(Proveedor temp) {
         
        FacesContext objFaces = FacesContext.getCurrentInstance();
        ExternalContext objExternal = objFaces.getExternalContext();
        Map params = objExternal.getRequestParameterMap();
        
        Proveedor miProveedor = temp;
        miProveedor.setEstado("Cancelado");
        objProveedorFacade.edit(miProveedor);
        
        
        Usuario usuario = new Usuario(); 
        usuario.setIdUsuario(Integer.parseInt(""+params.get("idUsuario")));
        
        Usuariotienerol usuRoles= new Usuariotienerol();
        usuRoles.setUsuario(usuario);
        
        usuarioRolFacade.remove(usuRoles);
        
    }
     
     public String contarProveedor(){
         return ""+objProveedorFacade.count();
     }
    
}
