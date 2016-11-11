/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.pedidoweb.negocio;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author samaelopez
 */
@Stateless
public class ProcesosVentas implements ProcesosVentasLocal {

    @EJB
    private MensajeColaLocal mensajeCola;



    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method") {
    
    
    @Override
    public  void pruebaCola() {
        try {
            Map < String, Object > objs = new HashMap();
            Map < String, Object > props = new HashMap();
            props.put("Accion", "envMail");
            mensajeCola.mandarMsgJMS(objs, props);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(ProcesosVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
