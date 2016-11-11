package com.asi.pedidoweb.controller;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.asi.pedidoweb.negocio.GestorEmailLocal;
import com.asi.pedidoweb.negocio.ProcesosVentasLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.component.panelmenu.PanelMenu;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.DynamicMenuModel;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.menu.Submenu;

/**
 *
 * @author samaelopez
 */
@ManagedBean(name = "pedidoWEb")
@ViewScoped
public class PedidoWeb implements Serializable{

    @EJB
    private ProcesosVentasLocal procesosVentas;

    @EJB
    private GestorEmailLocal gestorEmail;
    
    

    /**
     * Creates a new instance of PedidoWEb
     */
    public PedidoWeb() {
    }
    
    private PanelMenu panelMenu = new PanelMenu();
    
    
    public void crearGrupos () {
        MenuModel model = new DynamicMenuModel();
        MenuElement elem = new DefaultSubMenu("lanes", "");
        
        model.addElement(elem);
        Submenu submenu = new DefaultSubMenu("submenu", "");
        
        
        panelMenu.setModel(model);
    }

    public PanelMenu getPanelMenu() {
        return panelMenu;
    }

    public void setPanelMenu(PanelMenu panelMenu) {
        this.panelMenu = panelMenu;
    }
    
    
    public  void purebaEnviarcorreo() {
        try {
            List < String > lst = new ArrayList<>();
            lst.add("gerencia@bodeguitadelcerdito.com");
            gestorEmail.enviarEmail("Correo de prueba", "Prueba", "pso", lst, "sa");
        } catch (Exception ex) {
            Logger.getLogger(PedidoWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
        public  void purebaStrin() {
        try {
            procesosVentas.pruebaCola();
        } catch (Exception ex) {
            Logger.getLogger(PedidoWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        
}
