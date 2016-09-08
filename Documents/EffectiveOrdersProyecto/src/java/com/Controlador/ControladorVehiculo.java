package com.Controlador;

import com.Entity.Proveedor;
import com.Entity.Usuario;
import com.Entity.Vehiculo;
import com.Facade.ProveedorFacade;
import com.Facade.VehiculoFacade;
import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@Named(value = "controladorVehiculo")
@SessionScoped
public class ControladorVehiculo implements Serializable {

    private int estados;
    private Usuario objUsuariosLogin;

    List<Vehiculo> arregloVehiculos = new ArrayList<>();

    @Inject
    VehiculoFacade vehiculosF;

    @Inject
    ProveedorFacade proveedoresF;

    /**
     * Creates a new instance of ControladorVehiculo
     */
    public ControladorVehiculo() {
    }
    
    
    Vehiculo miVehiculo = new Vehiculo();
    Proveedor miProveedor = new Proveedor();
    Workbook archivoExcel;
    Sheet mihoja;

    public List<Vehiculo> listarVehiculos() {
        List<Vehiculo> misVehiculos = new ArrayList<Vehiculo>();
        misVehiculos = vehiculosF.findAll();
        return misVehiculos;
    }
    
     public List<Vehiculo> leerVehiculos() {
        arregloVehiculos = vehiculosF.findAll();
        return this.arregloVehiculos;
    }
  



    public void nuevoVehiculo() {

        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            Map params = externalContext.getRequestParameterMap();

            HttpServletRequest miSesion = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            this.objUsuariosLogin = (Usuario) miSesion.getSession().getAttribute("usuariologin");

            miVehiculo.setPlacaVehiculo("" + params.get("placaVehiculo"));
            miProveedor.setCedulaProveedor(Integer.parseInt("" + params.get("idProveedor")));

            miVehiculo.setMarca("" + params.get("marca"));
            miVehiculo.setTipoVehiculo("" + params.get("tipoVehiculo"));
            miVehiculo.setCapacidad(Integer.parseInt((String) (params.get("capacidad"))));
            miProveedor.setUsuario(this.objUsuariosLogin);
            miProveedor.setCedulaProveedor(this.objUsuariosLogin.getIdUsuario());
            miVehiculo.setIdProveedor(this.objUsuariosLogin.getProveedor());
            miProveedor.setEmpresa("" + params.get("empresa"));
            miProveedor.setNit("" + params.get("nit"));
            miProveedor.setEstado("Aceptado");
            miVehiculo.setIdProveedor(miProveedor);

            estados = 1;
            vehiculosF.create(miVehiculo);
        } catch (Exception ex) {
            ex.printStackTrace();
            estados = 2;
        }
    }
    
    public int cantidadHojas(String ruta) throws IOException, BiffException {
       archivoExcel = Workbook.getWorkbook(new File(ruta));
        mihoja = archivoExcel.getSheet(0);
        return archivoExcel.getNumberOfSheets();
    }
    
    public void nuevoVehiculoExcel() {
        
             FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletRequest miSesion = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            this.objUsuariosLogin = (Usuario) miSesion.getSession().getAttribute("usuariologin");
            miProveedor.setCedulaProveedor(objUsuariosLogin.getIdUsuario());
            miProveedor.setUsuario(this.objUsuariosLogin);
            miProveedor.setCedulaProveedor(this.objUsuariosLogin.getIdUsuario());
            miVehiculo.setIdProveedor(this.objUsuariosLogin.getProveedor());
            miProveedor.setEstado("Aceptado");
            miVehiculo.setIdProveedor(miProveedor);
            estados = 1;
            vehiculosF.create(miVehiculo);
        
           
        
    }
    
    

    public int getEstados() {
        return estados;
    }

    public void setEstados(int estados) {
        this.estados = estados;
    }

    public List<Vehiculo> getArregloVehiculos() {
        return arregloVehiculos;
    }

    public void setArregloVehiculos(List<Vehiculo> arregloVehiculos) {
        this.arregloVehiculos = arregloVehiculos;
    }

    public Sheet getMihoja() {
        return mihoja;
    }

    public void setMihoja(Sheet mihoja) {
        this.mihoja = mihoja;
    }
    
    public Vehiculo getMiVehiculo() {
        return miVehiculo;
    }

    public void setMiVehiculo(Vehiculo miVehiculo) {
        this.miVehiculo = miVehiculo;
    }

    public Proveedor getMiProveedor() {
        return miProveedor;
    }

    public void setMiProveedor(Proveedor miProveedor) {
        this.miProveedor = miProveedor;
    }

}
