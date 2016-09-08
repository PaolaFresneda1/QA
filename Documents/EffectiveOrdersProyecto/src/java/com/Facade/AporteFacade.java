/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Facade;

import com.Entity.Aporte;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Al-C
 */
@Stateless
public class AporteFacade extends AbstractFacade<Aporte> {

    @PersistenceContext(unitName = "EffectiveProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AporteFacade() {
        super(Aporte.class);
    }
    
}
