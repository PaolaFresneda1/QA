/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;

import com.Entity.Ciudad;
import com.Facade.CiudadFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Al-C
 */
@Named(value = "controladorCiudad")
@SessionScoped
public class ControladorCiudad implements Serializable {

    @Inject
    CiudadFacade objCiudadFacade;
    
    public ControladorCiudad() 
    {
    }
    
    public List<Ciudad> listarCiudades()
    {
        List<Ciudad> objListaCiudad;
        objListaCiudad=objCiudadFacade.findAll();
        return objListaCiudad;
    }
    
}
