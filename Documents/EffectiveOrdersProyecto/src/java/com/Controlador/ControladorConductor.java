/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;

import com.Entity.Conductor;
import com.Entity.Proveedor;
import com.Entity.Usuario;
import com.Entity.Vehiculo;
import com.Facade.ConductorFacade;
import com.Facade.ProveedorFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;


@Named(value = "controladorConductor")
@Dependent
public class ControladorConductor {

    List<Conductor> arregloConductores = new ArrayList<>();
    private int estados;
    private Usuario objUsuariosLogin;

    //Injects
    @Inject
    ConductorFacade conductoresF;

    @Inject
    ProveedorFacade proveedoresF;

    /**
     * Creates a new instance of ControladorConductor
     */
    public ControladorConductor() {
    }

    public List<Conductor> leerConductores() {
        arregloConductores = conductoresF.findAll();
        return this.arregloConductores;
    }

    public void nuevoConductor() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            Map params = externalContext.getRequestParameterMap();

            Conductor miConductor = new Conductor();
            Vehiculo miVehiculo = new Vehiculo();
            Proveedor miProveedor = new Proveedor();

            HttpServletRequest miSesion = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            this.objUsuariosLogin = (Usuario) miSesion.getSession().getAttribute("usuariologin");

            miConductor.setIdCedulaConductor(Integer.parseInt("" + (params.get("cedulaConductor"))));
            miProveedor.setCedulaProveedor(Integer.parseInt("" + params.get("idProveedor")));

            miConductor.setNombre("" + params.get("nombre"));
            miConductor.setApellido("" + params.get("apellido"));
            miConductor.setCorreo("" + params.get("correo"));
            miConductor.setTelefono(Integer.parseInt("" + params.get("telefono")));
            miConductor.setNumeroLicencia(Integer.parseInt("" + (params.get("numeroLicencia"))));
            miConductor.setNivelLicencia("" + params.get("tipoLicencia"));
            miVehiculo.setPlacaVehiculo("" + params.get("placaVehiculo"));
            miConductor.setIdVehiculo(miVehiculo);
            miProveedor.setUsuario(this.objUsuariosLogin);
            miProveedor.setCedulaProveedor(this.objUsuariosLogin.getIdUsuario());
            miVehiculo.setIdProveedor(this.objUsuariosLogin.getProveedor());
            miProveedor.setEmpresa("" + params.get("empresa"));
            miProveedor.setNit("" + params.get("nit"));
            miProveedor.setEstado("Aceptado");
            miConductor.setCedulaProveedor(miProveedor);

            estados = 1;
            conductoresF.create(miConductor);
        } catch (Exception ex) {
            ex.printStackTrace();
            estados = 2;
        }
    }

    //CRUD Conductores
    public String borrarConductor(Conductor conductores) {

        conductoresF.remove(conductores);
        return "tablaConductores.xhtml";
    }

    public String editarConductor() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map params = externalContext.getRequestParameterMap();

        Conductor miConductor = this.conductoresF.find(Integer.parseInt((String) params.get("cedulaConductor")));

        miConductor.setNombre("" + params.get("nombre"));
        miConductor.setApellido("" + params.get("apellido"));
        miConductor.setCorreo("" + params.get("correo"));
        miConductor.setTelefono(Integer.parseInt("" + params.get("telefono")));

        conductoresF.edit(miConductor);
        return "tablaConductores.xhtml";
    }

    public String contarConductores() {
        return "" + conductoresF.count();
    }

    public List<Conductor> getArregloConductores() {
        return arregloConductores;
    }

    public void setArregloConductores(List<Conductor> arregloConductores) {
        this.arregloConductores = arregloConductores;
    }

    public int getEstados() {
        return estados;
    }

    public void setEstados(int estados) {
        this.estados = estados;
    }
}
