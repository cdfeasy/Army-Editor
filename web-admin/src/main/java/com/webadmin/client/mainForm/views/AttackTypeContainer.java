package com.webadmin.client.mainForm.views;

import com.armyeditor.dto.AttackTypeDTO;
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
import com.webadmin.client.mainForm.properties.AttackTypeProperties;
import com.webadmin.client.services.CommonService;
import com.webadmin.client.services.CommonServiceAsync;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 28.07.12
 * Time: 12:19
 * To change this template use File | Settings | File Templates.
 */
public class AttackTypeContainer extends HorizontalLayoutContainer {
    private final CommonServiceAsync commonService = GWT.create(CommonService.class);
    VerticalLayoutContainer gridContainer;
    Grid<AttackTypeDTO> attackTypeGrid;
    ColumnModel<AttackTypeDTO> cm;
    ListStore<AttackTypeDTO> store;
    TextButton updateBtn;
    TextButton delSelBtn;
    final AttackTypeFields attackTypeFields = new AttackTypeFields();

    public AttackTypeContainer() {
        AttackTypeProperties props = GWT.create(AttackTypeProperties.class);
        IdentityValueProvider<AttackTypeDTO> identity = new IdentityValueProvider<AttackTypeDTO>();
        final CheckBoxSelectionModel<AttackTypeDTO> sm = new CheckBoxSelectionModel<AttackTypeDTO>(identity);
        ColumnConfig<AttackTypeDTO, String> idColumn = new ColumnConfig<AttackTypeDTO, String>(props.id(), 50, "id");
        ColumnConfig<AttackTypeDTO, String> nameColumn = new ColumnConfig<AttackTypeDTO,String>(props.name(),150,"name");
        ColumnConfig<AttackTypeDTO, String> descripColumn = new ColumnConfig<AttackTypeDTO,String>(props.description(),150,"description");

        List<ColumnConfig<AttackTypeDTO, ?>> l = new ArrayList<ColumnConfig<AttackTypeDTO, ?>>();
        l.add(sm.getColumn());
        l.add(idColumn);
        l.add(nameColumn);
        l.add(descripColumn);
        cm = new ColumnModel<AttackTypeDTO>(l);
        sm.setSelectionMode(Style.SelectionMode.MULTI);
        store = new ListStore<AttackTypeDTO>(props.key());
        updateStore();
        attackTypeGrid = new Grid<AttackTypeDTO>(store,cm);
        attackTypeGrid.setSelectionModel(sm);
        updateBtn = new TextButton("Update", new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                updateStore();
            }
        });
        delSelBtn = new TextButton("Delete Selection", new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                List selectList = attackTypeGrid.getSelectionModel().getSelectedItems();
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
        gridContainer.add(attackTypeGrid);
        gridContainer.add(updateBtn);
        gridContainer.add(delSelBtn);
        this.add(gridContainer);
        VerticalLayoutContainer vc = new VerticalLayoutContainer();
        vc.add(attackTypeFields, new VerticalLayoutContainer.VerticalLayoutData(350,200,new Margins(5,5,5,5)));
        this.add(vc);
        initHandlers();
    }

    void initHandlers() {
        attackTypeGrid.addRowClickHandler(new RowClickEvent.RowClickHandler() {
            @Override
            public void onRowClick(RowClickEvent event) {
                int row = event.getRowIndex();
                AttackTypeDTO a =(AttackTypeDTO) event.getSource().getStore().get(row);
                attackTypeFields.getIdFld().setText(a.getId());
                attackTypeFields.getNameFld().setText(a.getName());
                attackTypeFields.getDescripFld().setText(a.getDescription());
            }
        });

        attackTypeFields.getSaveBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                AttackTypeDTO a = new AttackTypeDTO();
                a.setId(attackTypeFields.getIdFld().getText());
                a.setName(attackTypeFields.getNameFld().getText());
                a.setDescription(attackTypeFields.getDescripFld().getText());
                commonService.changeAttackType(a, new AsyncCallback<Void>() {
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

        attackTypeFields.getSaveNewBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                AttackTypeDTO a = new AttackTypeDTO();
                a.setId(attackTypeFields.getIdFld().getText());
                a.setName(attackTypeFields.getNameFld().getText());
                a.setDescription(attackTypeFields.getDescripFld().getText());
                commonService.addAttackType(a, new AsyncCallback<Void>() {
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

    public void updateStore(){
        commonService.getAttackTypes(new AsyncCallback<List<AttackTypeDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                System.out.println("Запрос упал " + caught.getMessage());
            }

            @Override
            public void onSuccess(List<AttackTypeDTO> result) {
                store.clear();
                for (AttackTypeDTO a : result) {
                    store.add(a);
                }
                attackTypeGrid.reconfigure(store, cm);
            }
        });
    }

    public class AttackTypeFields extends BorderLayoutContainer {
        TextField idFld;
        TextField nameFld;
        TextArea descripFld;
        TextButton saveBtn = new TextButton("Save");
        TextButton saveNewBtn = new TextButton("Save as new item");

        public TextField getIdFld() {
            return idFld;
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

        public void setIdFld(TextField idFld) {
            this.idFld = idFld;
        }

        public AttackTypeFields() {
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
    }
}
