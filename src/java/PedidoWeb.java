/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.Dependent;
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
@Named(value = "pedidoWEb")
@Dependent
public class PedidoWeb {

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
    
    
    
    
}
