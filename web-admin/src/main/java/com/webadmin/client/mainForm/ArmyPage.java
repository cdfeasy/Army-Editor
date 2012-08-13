/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webadmin.client.mainForm;

import com.armyeditor.dto.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.button.ButtonBar;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.sencha.gxt.widget.core.client.tree.TreeView;
import com.webadmin.client.mainForm.properties.ArmorProperties;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dmitry
 */
public class ArmyPage extends HorizontalLayoutContainer {
    class KeyProvider implements ModelKeyProvider {
    @Override
    public String getKey(Object item) {
      String s="";
      if (item instanceof CodexDTO) s= "codex-"+ ((CodexDTO)item).getId();
      if (item instanceof SquadBaseDTO) s= "SquadBase-"+ ((SquadBaseDTO)item).getId();
      if (item instanceof SquadPartBaseDTO) s= "SquadPartBase-"+ ((SquadPartBaseDTO)item).getId();
      return s;
    }
    }
    

  public ArmyPage() {
      super();
    FlowLayoutContainer con = new FlowLayoutContainer();
    con.addStyleName("margin-10");
 
    TreeStore<Object> store = new TreeStore<Object>(new KeyProvider());
 
    CodexDTO root = new CodexDTO();
    root.setId("orks");
    root.setName("orks");
    SquadBaseDTO child=new SquadBaseDTO();
    child.setId("child1");
    child.setName("child1");
    root.getSquads().add(child);
    store.add(root);
    store.add(root, child);
    
    final Tree<Object, String> tree = new Tree<Object, String>(store, new ValueProvider<Object, String>() {
 
        @Override
        public String getValue(Object object) {
            String s = "";
            if (object instanceof CodexDTO) {
                s = "codex-" + ((CodexDTO) object).getName();
            }
            if (object instanceof SquadBaseDTO) {
                s = "SquadBase-" + ((SquadBaseDTO) object).getName();
            }
            if (object instanceof SquadPartBaseDTO) {
                s = "SquadPartBase-" + ((SquadPartBaseDTO) object).getId();
            }
            return s;
        }
 
      @Override
      public void setValue(Object object, String value) {
      }
 
      @Override
      public String getPath() {
        return "name";
      }
    });
    tree.setWidth(300);
  
 
    ButtonBar buttonBar = new ButtonBar();
 
    buttonBar.add(new TextButton("Expand All", new SelectHandler() {
 
      @Override
      public void onSelect(SelectEvent event) {
        tree.expandAll();
      }
    }));
    buttonBar.add(new TextButton("Collapse All", new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        tree.collapseAll();
      }
 
    }));
 
    buttonBar.setLayoutData(new MarginData(4));
    con.add(buttonBar);
    con.add(tree);
    this.add(con);
  }
   
}
