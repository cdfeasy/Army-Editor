/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webadmin.client.mainForm;

import com.armyeditor.dto.*;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;
import com.google.gwt.thirdparty.guava.common.base.Splitter;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
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
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.CheckChangeEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridRowEditing;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.webadmin.client.mainForm.properties.WeaponBaseProperties;
import com.webadmin.client.mainForm.properties.WeaponProperties;
import com.webadmin.client.services.ArmyService;
import com.webadmin.client.services.ArmyServiceAsync;
import com.webadmin.client.services.CommonService;
import com.webadmin.client.services.CommonServiceAsync;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dmitry
 */
public class ArmyPage extends HorizontalLayoutContainer {
    
    
    
    private final ArmyServiceAsync commonService = GWT.create(ArmyService.class);
    private final CommonServiceAsync baseService = GWT.create(CommonService.class);
    
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
    
    private void processSquadDTO(SquadPartBaseDTO dto,Object parent, TreeStore<Object> store){
      store.add(parent,dto);
      for(SquadPartBaseDTO squad: dto.getModifications()){
          processSquadDTO(squad,dto,store);
      }
  }
    
  private void processCodexDTO(CodexDTO dto,TreeStore<Object> store){
      store.add(dto);
      for(SquadBaseDTO squad: dto.getSquads()){
          //store.add(squad.getSquadPartBase(),dto);
          store.add(dto,squad);
          processSquadDTO(squad.getSquadPartBase(),squad,store);
      }
  }
  
    public ComboBox<WeaponBaseDTO> getWeaponCombobox() {
        ComboBox<WeaponBaseDTO> weaponBaseBox;
        WeaponBaseProperties weaponBaseProps = GWT.create(WeaponBaseProperties.class);
        final ListStore<WeaponBaseDTO> weaponBaseStore = new ListStore<WeaponBaseDTO>(weaponBaseProps.key());
        baseService.getWeaponBases(new AsyncCallback<List<WeaponBaseDTO>>() {

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Запрос упал " + throwable.getMessage());
            }

            @Override
            public void onSuccess(List<WeaponBaseDTO> weaponBaseDTOs) {
                weaponBaseStore.addAll(weaponBaseDTOs);
            }
        });
        weaponBaseBox = new ComboBox<WeaponBaseDTO>(weaponBaseStore, weaponBaseProps.nameLabel());
        weaponBaseBox.setTypeAhead(true);
        weaponBaseBox.setTriggerAction(ComboBoxCell.TriggerAction.ALL);
        return weaponBaseBox;
    }
  
    public void filldata(final TextField unitName,
            final TextField cost,
            final TextField min,
            final TextField max,
            final TextField condition,
            final VerticalLayoutContainer modifications,
            VerticalLayoutContainer optionsWeapons,
            SquadPartBaseDTO result) {
        unitName.setText(result.getId());
        cost.setText(Integer.toString(result.getUnit().getCost()));
        min.setText(Integer.toString(result.getMinSize()));
        max.setText(Integer.toString(result.getMaxSize()));
        condition.setText(result.getConditions());

        if (result.getModifications().size() > 0) {
            for (SquadPartBaseDTO s : result.getModifications()) {
                modifications.add(initUnitContaner(s.getId(), s),new VerticalLayoutData(-1, -1));

            }
        }
        VerticalLayoutContainer weapons=new VerticalLayoutContainer();
        for(WeaponSelectionDTO wp:result.getWeaponSelection()){
            ComboBox<WeaponBaseDTO> weaponCombobox = getWeaponCombobox();
            TextButton add = new TextButton("add");
            TextButton remove = new TextButton("remove");
            VerticalLayoutContainer weapon=new VerticalLayoutContainer();
            TextField weaponcond = new TextField();
            ListStore<WeaponDTO> weaponStore;
            ColumnModel<WeaponDTO> weaponCm;
            WeaponProperties weaponProps = GWT.create(WeaponProperties.class);
            ColumnConfig<WeaponDTO, String> weaponColumn = new ColumnConfig<WeaponDTO, String>(weaponProps.weapon(), 100, "weapon");
            ColumnConfig<WeaponDTO, Integer> costColumnWeapon = new ColumnConfig<WeaponDTO, Integer>(weaponProps.cost(), 100, "cost");
            List<ColumnConfig<WeaponDTO, ?>> lWeapon = new ArrayList<ColumnConfig<WeaponDTO, ?>>();
            lWeapon.add(weaponColumn);
            lWeapon.add(costColumnWeapon);
            weaponCm = new ColumnModel<WeaponDTO>(lWeapon);
            weaponStore = new ListStore<WeaponDTO>(weaponProps.key());
            weaponStore.addAll( wp.getWeapon());
            Grid<WeaponDTO> weaponGrid=new  Grid<WeaponDTO>(weaponStore,weaponCm);
            weapon.add(weaponcond,new VerticalLayoutData(-1, -1));
            weapon.add(weaponGrid,new VerticalLayoutData(-1, -1));
            HorizontalPanel panel=new HorizontalPanel();
          //  panel.setWidth("400px");
            panel.add(add);
            panel.add(weaponCombobox);
            panel.add(remove);
            
            
//            HorizontalLayoutContainer c = new HorizontalLayoutContainer();
//            c.add(add, new HorizontalLayoutData(100,100));
//            c.add(weaponCombobox, new HorizontalLayoutData(300,100));
//            c.add(remove, new HorizontalLayoutData(100,100));
            weapon.add(panel,new VerticalLayoutData(400, -1));
            weapons.add(weapon,new VerticalLayoutData(-1, -1));
        }
        optionsWeapons.add(weapons,new VerticalLayoutData(-1, -1));
    }
  
    private VerticalLayoutContainer initUnitContaner(String unitBaseId, SquadPartBaseDTO squad) {
        final VerticalLayoutContainer unitContainer = new VerticalLayoutContainer();
        final TextField unitName = new TextField();
        final TextField cost = new TextField();
        final TextField min = new TextField();
        final TextField max = new TextField();
        final TextField condition = new TextField();
        final VerticalLayoutContainer modifications = new VerticalLayoutContainer();
        final VerticalLayoutContainer optionsWeapons = new VerticalLayoutContainer();
        if (squad == null) {
            commonService.getSquadPart(unitBaseId, new AsyncCallback<SquadPartBaseDTO>() {

                @Override
                public void onFailure(Throwable caught) {
                    Info.display("error ", caught.getMessage());
                    caught.printStackTrace();
                }

                @Override
                public void onSuccess(SquadPartBaseDTO result) {
                    filldata(unitName, cost, min, max, condition, modifications, optionsWeapons,result);

                }
            });
        } else {
            filldata(unitName, cost, min, max, condition, modifications,optionsWeapons, squad);
        }
        unitContainer.add(unitName,new VerticalLayoutData(-1, -1));
        unitContainer.add(cost,new VerticalLayoutData(-1, -1));
        unitContainer.add(min,new VerticalLayoutData(-1, -1));
        unitContainer.add(max,new VerticalLayoutData(-1, -1));
        unitContainer.add(condition,new VerticalLayoutData(-1, -1));
        unitContainer.add(optionsWeapons,new VerticalLayoutData(-1, -1));
//        VerticalPanel vp = new VerticalPanel();
//        vp.setSpacing(10);
//        vp.add(modifications);
        unitContainer.add(modifications,new VerticalLayoutData(-1, -1, new Margins(30)));

        return unitContainer;
    }
  
  VerticalLayoutContainer infoContainer;
  TextField nameSquad;
  
  
  
  private void init(CodexDTO dto){
    HorizontalLayoutContainer con = new HorizontalLayoutContainer();
   
    VerticalLayoutContainer treeContainer=new VerticalLayoutContainer();
    infoContainer=new VerticalLayoutContainer();;
   // initInfoContaner();
    
    con.addStyleName("margin-10");
 
    final TreeStore<Object> store = new TreeStore<Object>(new KeyProvider());
 
    
    processCodexDTO(dto,store);
   
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
      public String getPath() {
        return "name";
      }

            @Override
            public void setValue(Object object, String value) {
                
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
    
    final TextField name=new TextField();
    SimpleSafeHtmlCell<String> cell = new SimpleSafeHtmlCell<String>(SimpleSafeHtmlRenderer.getInstance(), "click") {
      @Override
      public void onBrowserEvent(Cell.Context context, Element parent, String value, NativeEvent event,
          ValueUpdater<String> valueUpdater) {
        super.onBrowserEvent(context, parent, value, event, valueUpdater);
            if ("click".equals(event.getType())) {
            // name.setText(tree.getSelectionModel().getSelectedItem().getClass().toString()+"/"+ tree.getSelectionModel().getSelectedItem().toString());
             Object item=tree.getSelectionModel().getSelectedItem(); 
             if (item instanceof CodexDTO) name.setText( ((CodexDTO)item).getId());
             
             if (item instanceof SquadBaseDTO) {
                    name.setText(((SquadBaseDTO) item).getId());
                    infoContainer.clear();
                    infoContainer.add(name,new VerticalLayoutData(700, 1000));
                    infoContainer.add(initUnitContaner(((SquadBaseDTO)item).getSquadPartBase().getId(),null),new VerticalLayoutData(700, 1000));
                    
                    
                }
             if (item instanceof SquadPartBaseDTO) name.setText( ((SquadPartBaseDTO)item).getId());
      
            }
      }
    };
    tree.setCell(cell);
    
 
    buttonBar.setLayoutData(new MarginData(4));
    treeContainer.add(buttonBar);
    treeContainer.add(tree);
    con.add(treeContainer, new HorizontalLayoutData(-1, -1, new Margins(5)));
    con.add(infoContainer, new HorizontalLayoutData(-1, -1, new Margins(5)));
    this.add(con);
  }  

  public ArmyPage() {
      super();
   commonService.getCodex(new AsyncCallback<CodexDTO>() {

          @Override
          public void onFailure(Throwable caught) {
              caught.printStackTrace();
              throw new UnsupportedOperationException(caught.getMessage());
          }

          @Override
          public void onSuccess(CodexDTO result) {
              init(result);
          }
      });
  }
   
}
