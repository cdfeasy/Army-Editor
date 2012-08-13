package com.webadmin.client.mainForm.views;

import com.armyeditor.dto.WeaponTypeDTO;
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
import com.sencha.gxt.widget.core.client.info.Info;
import com.webadmin.client.mainForm.properties.WeaponTypeProperties;
import com.webadmin.client.services.CommonService;
import com.webadmin.client.services.CommonServiceAsync;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 02.08.12
 * Time: 20:17
 * To change this template use File | Settings | File Templates.
 */
public class WeaponTypeContainer extends HorizontalLayoutContainer {
    private final CommonServiceAsync commonService = GWT.create(CommonService.class);
    VerticalLayoutContainer gridContainer;
    Grid<WeaponTypeDTO> specialRuleGrid;
    ColumnModel<WeaponTypeDTO> cm;
    ListStore<WeaponTypeDTO> store;
    TextButton updateBtn = new TextButton("Update");
    TextButton delSelBtn = new TextButton("Delete Selection");
    final WeaponTypeFields weaponTypeFields = new WeaponTypeFields();

    public WeaponTypeContainer() {
        WeaponTypeProperties props = GWT.create(WeaponTypeProperties.class);
        IdentityValueProvider<WeaponTypeDTO> identity = new IdentityValueProvider<WeaponTypeDTO>();
        final CheckBoxSelectionModel<WeaponTypeDTO> sm = new CheckBoxSelectionModel<WeaponTypeDTO>(identity);
        ColumnConfig<WeaponTypeDTO, String> idColumn = new ColumnConfig<WeaponTypeDTO, String>(props.id(), 50, "id");
        ColumnConfig<WeaponTypeDTO, String> nameColumn = new ColumnConfig<WeaponTypeDTO, String>(props.name(), 150, "name");
        ColumnConfig<WeaponTypeDTO, String> descripColumn = new ColumnConfig<WeaponTypeDTO, String>(props.description(), 150, "description");

        List<ColumnConfig<WeaponTypeDTO, ?>> l = new ArrayList<ColumnConfig<WeaponTypeDTO, ?>>();
        l.add(sm.getColumn());
        l.add(idColumn);
        l.add(nameColumn);
        l.add(descripColumn);
        cm = new ColumnModel<WeaponTypeDTO>(l);
        sm.setSelectionMode(Style.SelectionMode.MULTI);
        store = new ListStore<WeaponTypeDTO>(props.key());
        specialRuleGrid= new Grid<WeaponTypeDTO>(store, cm);
        updateStore();
        specialRuleGrid.setSelectionModel(sm);
        gridContainer = new VerticalLayoutContainer();
        gridContainer.add(specialRuleGrid);
        gridContainer.add(updateBtn);
        gridContainer.add(delSelBtn);
        this.add(gridContainer);
        VerticalLayoutContainer vc = new VerticalLayoutContainer();
        vc.add(weaponTypeFields, new VerticalLayoutContainer.VerticalLayoutData(350,200,new Margins(5,5,5,5)));
        this.add(vc);
        initHandlers();
    }

    public void updateStore(){
        commonService.getWeaponType(new AsyncCallback<List<WeaponTypeDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                System.out.println("Запрос упал " + caught.getMessage());
            }

            @Override
            public void onSuccess(List<WeaponTypeDTO> result) {
                store.clear();
                for (WeaponTypeDTO a : result) {
                    store.add(a);
                }
                specialRuleGrid.reconfigure(store, cm);
            }
        });
    }

    void initHandlers() {
        specialRuleGrid.addRowClickHandler(new RowClickEvent.RowClickHandler() {
            @Override
            public void onRowClick(RowClickEvent event) {
                int row = event.getRowIndex();
                WeaponTypeDTO a =(WeaponTypeDTO) event.getSource().getStore().get(row);
                weaponTypeFields.getIdFld().setText(a.getId());
                weaponTypeFields.getNameFld().setText(a.getName());
                weaponTypeFields.getDescripFld().setText(a.getDescription());
            }
        });
        weaponTypeFields.getSaveBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                WeaponTypeDTO a = new WeaponTypeDTO();
                a.setId(weaponTypeFields.getIdFld().getText());
                a.setName(weaponTypeFields.getNameFld().getText());
                a.setDescription(weaponTypeFields.getDescripFld().getText());
                commonService.changeWeaponType(a, new AsyncCallback<Void>() {
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
        weaponTypeFields.getSaveNewBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                WeaponTypeDTO a = new WeaponTypeDTO();
                a.setId(weaponTypeFields.getIdFld().getText());
                a.setName(weaponTypeFields.getNameFld().getText());
                a.setDescription(weaponTypeFields.getDescripFld().getText());
                commonService.addWeaponType(a, new AsyncCallback<Void>() {
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

        delSelBtn.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                Info.display("Click", ((TextButton) event.getSource()).getText() + " clicked");
                List selectList = specialRuleGrid.getSelectionModel().getSelectedItems();
                commonService.delSpecialRules(selectList, new AsyncCallback<Void>() {
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

        updateBtn.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                updateStore();
            }
        });
    }

    public class WeaponTypeFields extends BorderLayoutContainer {
        TextField idFld;
        TextField nameFld;
        TextArea descripFld;
        TextButton saveBtn = new TextButton("Save");
        TextButton saveNewBtn = new TextButton("Save as new item");

        public WeaponTypeFields(){
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
