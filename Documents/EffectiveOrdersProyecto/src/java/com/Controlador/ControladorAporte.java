/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;


import com.Entity.Aporte;
import com.Entity.Pedido;
import com.Entity.Producto;
import com.Entity.Proveedor;
import com.Facade.AporteFacade;
import com.Facade.PedidoFacade;
import com.Facade.ProductoFacade;
import com.Facade.ProveedorFacade;
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
 * @author Al-C
 */
@Named(value = "controladorAporte")
@SessionScoped
public class ControladorAporte implements Serializable {
    
    @Inject
    AporteFacade objAporteFacade;
    
    @Inject
    PedidoFacade pedidoFacade;
    
    @Inject
    ProductoFacade productoFacade;
    
    @Inject
    ProveedorFacade proveedorFacade;
    
   
    public ControladorAporte() 
    {
        
    }
    
    public void registrarAporte()
    {
        
        FacesContext objFacesContext = FacesContext.getCurrentInstance();
        ExternalContext objExternalContext = objFacesContext.getExternalContext();
        Map parametros = objExternalContext.getRequestParameterMap();
        
        Aporte objAporte = new Aporte();
        Pedido objPedido= new Pedido();
       
        objPedido = pedidoFacade.find(Integer.parseInt(""+parametros.get("ocultoIdPedido")));
        
        objAporte.setIdPedido(objPedido);
        
        Producto objProducto= new Producto();
        
        objProducto.setCodigoProducto(Integer.parseInt(""+parametros.get("ocultoIdPedroducto")));
        
        objProducto = productoFacade.find(objProducto.getCodigoProducto());
        
        objAporte.setCantidadAportada(Integer.parseInt(""+parametros.get("CantidadAporte")));        
        objAporte.setIdProducto(objProducto);
        Proveedor proveedor = new Proveedor();
        proveedor = proveedorFacade.find(Integer.parseInt(""+parametros.get("cedulaCliente")));
        objAporte.setCedulaProveedor(proveedor);
        objAporteFacade.create(objAporte);
        
    }
     
    public List<Aporte> listarAportes()
    {
        List<Aporte> objAportes;
        return objAportes= objAporteFacade.findAll();
    }
    
    
}
