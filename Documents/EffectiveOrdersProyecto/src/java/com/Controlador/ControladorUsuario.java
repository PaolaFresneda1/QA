package com.Controlador;

import com.Entity.Aporte;
import com.Entity.Ciudad;
import com.Entity.Conductor;
import com.Entity.Envio;
import com.Entity.Oferta;
import com.Entity.Pedido;
import com.Entity.Rol;
import com.Entity.Usuario;
import com.Entity.Usuariotienerol;
import com.Facade.UsuarioFacade;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Named(value = "controladorUsuario")
@SessionScoped
public class ControladorUsuario implements Serializable {

    private int genero = 0;
    Usuario objUsuarioLogin;
    @Inject
    UsuarioFacade objUsuarioFacade;
    String mensaje;
    Rol roles = new Rol();

    public ControladorUsuario() {
    }

    public void registrarUsuario() {

        try {
            FacesContext objFacesContext = FacesContext.getCurrentInstance();
            ExternalContext objExternalContext = objFacesContext.getExternalContext();
            Map parametros = objExternalContext.getRequestParameterMap();

            Usuario objUsuario = new Usuario();

            objUsuario.setIdUsuario(Integer.parseInt("" + parametros.get("documento")));
            objUsuario.setNombre("" + parametros.get("nombre"));
            objUsuario.setApellido("" + parametros.get("apellido"));
            objUsuario.setCorreo("" + parametros.get("correo"));
            objUsuario.setContrasena("" + parametros.get("contrasena"));
            objUsuario.setDireccion("" + parametros.get("direccion"));
            objUsuario.setTelefono(Integer.parseInt("" + parametros.get("tel")));
            objUsuario.setGenero("" + parametros.get("genero"));
            objUsuario.setEstado(true);
            Ciudad objCiudad = new Ciudad();
            objCiudad.setIdCiudad(Integer.parseInt("" + parametros.get("ciudad")));
            objUsuario.setIdCiudad(objCiudad);

            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
            String fechaFormulario = "" + parametros.get("fechaN");
            Date fecha = null;
            fecha = formatoDelTexto.parse(fechaFormulario);

            objUsuario.setFechaDeNacimiento(fecha);
            objUsuarioFacade.create(objUsuario);
            mensaje="creado";
        } catch (ParseException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            System.out.println("La interfaz registro usuario presento el siguiente error " + ex);

        }

    }

    public void actualizarUsuario() throws ParseException {
        FacesContext faces = FacesContext.getCurrentInstance();
        ExternalContext externalContext = faces.getExternalContext();
        Map parametros = externalContext.getRequestParameterMap();

        Usuario objUsuario = new Usuario();
        objUsuario.setIdUsuario(Integer.parseInt("" + parametros.get("documento")));
        objUsuario.setNombre("" + parametros.get("nombre"));
        objUsuario.setApellido("" + parametros.get("apellido"));
        objUsuario.setCorreo("" + parametros.get("correo"));
        objUsuario.setContrasena("" + parametros.get("contrasena"));
        objUsuario.setDireccion("" + parametros.get("direccion"));
        objUsuario.setTelefono(Integer.parseInt("" + parametros.get("tel")));
        objUsuario.setGenero("" + parametros.get("genero"));
        objUsuario.setEstado(true);
        Ciudad objCiudad = new Ciudad();
        objCiudad.setIdCiudad(Integer.parseInt("" + parametros.get("ciudad")));
        objUsuario.setIdCiudad(objCiudad);

        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormulario = "" + parametros.get("fechaN");
        Date fecha = null;
        fecha = formatoDelTexto.parse(fechaFormulario);

        objUsuario.setFechaDeNacimiento(fecha);

        objUsuarioFacade.edit(objUsuario);
    }

    public String login() throws IOException {

        FacesContext objFacesContext = FacesContext.getCurrentInstance();
        ExternalContext objExternalContext = objFacesContext.getExternalContext();
        Map parametros = objExternalContext.getRequestParameterMap();

        int idUsuario = Integer.parseInt("" + parametros.get("documento"));
        String contrasena = "" + parametros.get("contrasena");

        Usuario objUsuario = objUsuarioFacade.login(idUsuario, contrasena);

        if (objUsuario.getIdUsuario() == 0) {
            return "registro";
        } else {

            //Redirecion
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            FacesContext.getCurrentInstance().
                    responseComplete();
            objUsuarioLogin = objUsuarioFacade.find(objUsuario.getIdUsuario());

            //Sesion
            HttpServletRequest miSesion = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            miSesion.getSession().setAttribute("usulogin", objUsuario);
            miSesion.getSession().setAttribute("usuariologin", objUsuarioLogin);
            if (objUsuario.getGenero().equals("Masculino")) {
                genero = 1;
            } else {
                genero = 2;
            }

            List<Usuariotienerol> misRoles = new ArrayList<>();
            int id = objUsuarioLogin.getIdUsuario();
            misRoles = objUsuarioFacade.consultarRoles(id);

            for (Usuariotienerol misRole : misRoles) {
                int rol = misRole.getUsuariotienerolPK().getIdRol();
                if (rol == 1) {
                    response.sendRedirect("Usuario/index.xhtml");
                } else if (rol == 2) {
                    response.sendRedirect("Cliente/index.xhtml");
                } else if (rol == 3) {
                    response.sendRedirect("Proveedor/index.xhtml");
                } else if (rol == 4) {
                    response.sendRedirect("Gerente/index.xhtml");
                }
            }

        }

        return "null";
    }

    public void cerrarSesion() {
        try {
          
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            FacesContext.getCurrentInstance().responseComplete();
             response.sendRedirect("../iniciarSesion.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
    
    public List<Usuariotienerol> mostrarRoles() {

        List<Usuariotienerol> misRoles;
        int id = objUsuarioLogin.getIdUsuario();
        return misRoles = objUsuarioFacade.consultarRoles(id);
    }

    public String cambiarFechaFormato(Date temp) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        return formatoDelTexto.format(temp);
    }
    
    public String cambiarFechaFormato2(Date temp) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
        return formatoDelTexto.format(temp);
    }

    public String contarUsuarios() {
        return "" + objUsuarioFacade.count();
    }
    
    public void inactivarUsuario(Usuario temp ) {
        Usuario miUsuario = temp;
        miUsuario.setEstado(false);
        objUsuarioFacade.edit(miUsuario);
    }
    
    public void activarUsuario(Usuario temp ) {
        Usuario miUsuario = temp;
        miUsuario.setEstado(true);
        objUsuarioFacade.edit(miUsuario);
    }
    
    public List<Usuario> leerUsuarios(){
    List<Usuario> misUsuarios;
    misUsuarios= objUsuarioFacade.findAll();
    
    return misUsuarios;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public Usuario getObjUsuarioLogin() {
        return objUsuarioLogin;
    }

    public void setObjUsuarioLogin(Usuario objUsuarioLogin) {
        this.objUsuarioLogin = objUsuarioLogin;
    }
    
    public int contarConductores(){
        int contar = 0;
        for (Conductor conductor : objUsuarioLogin.getProveedor().getConductorCollection()) {
               contar = contar+1;
        }
        return contar;
    }
    
    public int contarEnvios(){
        int contar = 0;
        for (Envio envios : objUsuarioLogin.getProveedor().getEnvioCollection()) {
               contar = contar+1;
        }
        return contar;
    }
    
     public int contarOfertas(){
        int contar = 0;
        for (Oferta oferta : objUsuarioLogin.getProveedor().getOfertaCollection()) {
               contar = contar+1;
        }
        return contar;
    }
     
     public int contarPedido(){
        int contar = 0;
        for (Aporte aportes : objUsuarioLogin.getProveedor().getAporteCollection()) {
               contar = contar+1;
        }
        return contar;
    }

}
