/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;

import com.Entity.Contacto;
import com.Facade.ContactoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Al-C
 */
@Named(value = "controladorContactenos")
@SessionScoped
public class ControladorContactenos implements Serializable {

   private int estados = 0;
    @Inject
    ContactoFacade objContactoFacade;

    public ControladorContactenos() {
        estados = 0;
    }

    public void registrarContacto() {
        
        try{
        FacesContext objFacesContext = FacesContext.getCurrentInstance();
        ExternalContext objExternalContext = objFacesContext.getExternalContext();
        Map parametros = objExternalContext.getRequestParameterMap();

        Contacto objContacto = new Contacto();

        Date objDate = new Date();
            objContacto.setNombre("" + parametros.get("nombreUsuario"));
            objContacto.setApellido("" + parametros.get("apellidoUsuario"));
            objContacto.setCorreo("" + parametros.get("correoUsuario"));
            objContacto.setAsunto("" + parametros.get("asuntoContactenos"));
            objContacto.setContactenos("" + parametros.get("mensajeContactenos"));
            objContacto.setFechaEnvio(objDate);
            objContactoFacade.create(objContacto);
        }catch(Exception ex)
        {
            System.out.println("La interfaz registro usuario presento el siguiente error "+ex);
        }
    
    }
    
    public List<Contacto> consultarEmail()
    {
        return objContactoFacade.findAll();
    }
    
      public String contarEmails(){
         return ""+objContactoFacade.count();
     }

    public int getEstados() {
        return estados;
    }

    public void setEstados(int estados) {
        this.estados = estados;
    }

}
