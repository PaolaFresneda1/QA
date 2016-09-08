/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Facade;

import com.Entity.Usuario;
import com.Entity.Usuariotienerol;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "EffectiveProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    
    public Usuario login(int idUsuario, String contrasena) {
        Usuario objUsuario = new Usuario();
        objUsuario.setIdUsuario(0);
        Query q;
        q = em.createQuery("select u from Usuario u where u.idUsuario = :idPersona and u.contrasena =:contrasenaPersona");
        q.setParameter("idPersona", idUsuario);
        q.setParameter("contrasenaPersona", contrasena);

        List<Usuario> onjListUsuario = q.getResultList();
        if (onjListUsuario.isEmpty()) {
            return objUsuario;
        } else {
            q = em.createQuery("select u from Usuario u where u.idUsuario = :id");
            q.setParameter("id", onjListUsuario.get(0).getIdUsuario());
            List<Usuario> objListUsuarioDatos = q.getResultList();
            return objListUsuarioDatos.get(0);
        }

    }

    public List<Usuariotienerol> consultarRoles(int idUsuario) {
        List<Usuariotienerol> objArregloUsuario;
        Query q;
        q = em.createQuery("SELECT u FROM Usuariotienerol u WHERE u.usuariotienerolPK.idUsuario = :idUsuario");
        q.setParameter("idUsuario", idUsuario);
        objArregloUsuario = q.getResultList();
        return objArregloUsuario;

    }
    
}
