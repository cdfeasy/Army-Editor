package com.webadmin.client.mainForm.views;

import com.armyeditor.dto.ArmorDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.RowClickEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.webadmin.client.mainForm.properties.ArmorProperties;
import com.webadmin.client.services.CommonService;
import com.webadmin.client.services.CommonServiceAsync;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 02.08.12
 * Time: 13:43
 * To change this template use File | Settings | File Templates.
 */
public class ArmorContainer extends HorizontalLayoutContainer {
    private final CommonServiceAsync commonService = GWT.create(CommonService.class);
    VerticalLayoutContainer gridContainer;
    Grid<ArmorDTO> armorGrid;
    ColumnModel<ArmorDTO> cm;
    ListStore<ArmorDTO> store;
    TextButton updateBtn;
    TextButton delSelBtn;
    final ArmorFieds armorFieds = new ArmorFieds();

    public ArmorContainer() {
        ArmorProperties props = GWT.create(ArmorProperties.class);
        IdentityValueProvider<ArmorDTO> identity = new IdentityValueProvider<ArmorDTO>();
        final CheckBoxSelectionModel<ArmorDTO> sm = new CheckBoxSelectionModel<ArmorDTO>(identity);
        ColumnConfig<ArmorDTO, String> idColumn = new ColumnConfig<ArmorDTO, String>(props.id(), 50, "id");
        ColumnConfig<ArmorDTO, String> nameColumn = new ColumnConfig<ArmorDTO, String>(props.name(), 150, "name");
        ColumnConfig<ArmorDTO, String> descripColumn = new ColumnConfig<ArmorDTO, String>(props.description(), 150, "description");

        List<ColumnConfig<ArmorDTO, ?>> l = new ArrayList<ColumnConfig<ArmorDTO, ?>>();
        l.add(sm.getColumn());
        l.add(idColumn);
        l.add(nameColumn);
        l.add(descripColumn);
        cm = new ColumnModel<ArmorDTO>(l);
        sm.setSelectionMode(Style.SelectionMode.MULTI);
        store = new ListStore<ArmorDTO>(props.key());
        armorGrid= new Grid<ArmorDTO>(store, cm);
        updateStore();
        armorGrid.setSelectionModel(sm);

        updateBtn = new TextButton("Update", new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                updateStore();
            }
        });
        delSelBtn = new TextButton("Delete Selection", new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                List selectList = armorGrid.getSelectionModel().getSelectedItems();
                commonService.delAttackTypes(selectList, new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Запрос упал " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Void aVoid) {
                        updateStore();
                    }
                });
            }
        });

        gridContainer = new VerticalLayoutContainer();
        gridContainer.add(armorGrid);
        gridContainer.add(updateBtn);
        gridContainer.add(delSelBtn);
        this.add(gridContainer);
        VerticalLayoutContainer vc = new VerticalLayoutContainer();
        vc.add(armorFieds, new VerticalLayoutContainer.VerticalLayoutData(350,200,new Margins(5,5,5,5)));
        this.add(vc);
        initHandlers();
    }

    public void updateStore(){
        commonService.getArmors(new AsyncCallback<List<ArmorDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                System.out.println("Запрос упал " + caught.getMessage());
            }

            @Override
            public void onSuccess(List<ArmorDTO> result) {
                store.clear();
                for (ArmorDTO a : result) {
                    store.add(a);
                }
                armorGrid.reconfigure(store,cm);
            }
        });
    }

    void initHandlers() {
        armorGrid.addRowClickHandler(new RowClickEvent.RowClickHandler() {
            @Override
            public void onRowClick(RowClickEvent event) {
                int row = event.getRowIndex();
                ArmorDTO a =(ArmorDTO) event.getSource().getStore().get(row);
                armorFieds.getIdFld().setText(a.getId());
                armorFieds.getNameFld().setText(a.getName());
                armorFieds.getDescripFld().setText(a.getDescription());
            }
        });
        armorFieds.getSaveBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                ArmorDTO a = new ArmorDTO();
                a.setId(armorFieds.getIdFld().getText());
                a.setName(armorFieds.getNameFld().getText());
                a.setDescription(armorFieds.getDescripFld().getText());
                commonService.changeArmor(a, new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Запрос упал " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Void aVoid) {
                        updateStore();
                    }
                });
            }
        });
        armorFieds.getSaveNewBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                ArmorDTO a = new ArmorDTO();
                a.setId(armorFieds.getIdFld().getText());
                a.setName(armorFieds.getNameFld().getText());
                a.setDescription(armorFieds.getDescripFld().getText());
                commonService.addArmor(a, new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Запрос упал " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Void aVoid) {
                        updateStore();
                    }
                });
            }
        });

    }

    public class ArmorFieds extends BorderLayoutContainer {
        TextField idFld;
        TextField nameFld;
        TextArea descripFld;
        TextButton saveBtn = new TextButton("Save");
        TextButton saveNewBtn = new TextButton("Save as new item");

        public ArmorFieds() {
            VerticalLayoutContainer vc = new VerticalLayoutContainer();
            idFld = new TextField();
            vc.add(new FieldLabel(idFld, "ID"));
            nameFld = new TextField();
            vc.add(new FieldLabel(nameFld, "Name"));
            descripFld = new TextArea();
            vc.add(new FieldLabel(descripFld, "Description"));
            vc.add(saveBtn);
            vc.add(saveNewBtn);

            this.setBorders(true);
            this.setHeight(200);
            this.setWidth(350);
            this.add(vc,new VerticalLayoutContainer.VerticalLayoutData(350,200,new Margins(5,5,5,5)));
        }

        public TextField getIdFld() {
            return idFld;
        }

        public void setIdFld(TextField idFld) {
            this.idFld = idFld;
        }

        public TextField getNameFld() {
            return nameFld;
        }

        public void setNameFld(TextField nameFld) {
            this.nameFld = nameFld;
        }

        public TextArea getDescripFld() {
            return descripFld;
        }

        public void setDescripFld(TextArea descripFld) {
            this.descripFld = descripFld;
        }

        public TextButton getSaveBtn() {
            return saveBtn;
        }

        public void setSaveBtn(TextButton saveBtn) {
            this.saveBtn = saveBtn;
        }

        public TextButton getSaveNewBtn() {
            return saveNewBtn;
        }

        public void setSaveNewBtn(TextButton saveNewBtn) {
            this.saveNewBtn = saveNewBtn;
        }

    }

}
