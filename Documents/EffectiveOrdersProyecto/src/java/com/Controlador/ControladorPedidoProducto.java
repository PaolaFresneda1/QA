/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;

import com.Entity.Pedidoproducto;
import com.Facade.PedidoproductoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;


@Named(value = "controladorPedidoProducto")
@SessionScoped
public class ControladorPedidoProducto implements Serializable {

    @Inject
    PedidoproductoFacade objProductoFacade;

    public ControladorPedidoProducto() {
    }

    public List<Pedidoproducto> misAportes() {
        List<Pedidoproducto> misAportes = objProductoFacade.findAll();
        return misAportes ;
    }
}
