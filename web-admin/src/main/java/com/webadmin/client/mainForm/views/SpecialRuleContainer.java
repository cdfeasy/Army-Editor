package com.webadmin.client.mainForm.views;

import com.armyeditor.dto.SpecialRuleDTO;
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
import com.webadmin.client.mainForm.properties.SpecialRuleProperties;
import com.webadmin.client.services.CommonService;
import com.webadmin.client.services.CommonServiceAsync;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 02.08.12
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public class SpecialRuleContainer extends HorizontalLayoutContainer {
    private final CommonServiceAsync commonService = GWT.create(CommonService.class);
    VerticalLayoutContainer gridContainer;
    Grid<SpecialRuleDTO> specialRuleGrid;
    ColumnModel<SpecialRuleDTO> cm;
    ListStore<SpecialRuleDTO> store;
    TextButton updateBtn = new TextButton("Update");
    TextButton delSelBtn = new TextButton("Delete Selection");
    final SpecialRuleFields specialRuleFields = new SpecialRuleFields();

    public SpecialRuleContainer() {
        SpecialRuleProperties props = GWT.create(SpecialRuleProperties.class);
        IdentityValueProvider<SpecialRuleDTO> identity = new IdentityValueProvider<SpecialRuleDTO>();
        final CheckBoxSelectionModel<SpecialRuleDTO> sm = new CheckBoxSelectionModel<SpecialRuleDTO>(identity);
        ColumnConfig<SpecialRuleDTO, String> idColumn = new ColumnConfig<SpecialRuleDTO, String>(props.id(), 50, "id");
        ColumnConfig<SpecialRuleDTO, String> nameColumn = new ColumnConfig<SpecialRuleDTO, String>(props.name(), 150, "name");
        ColumnConfig<SpecialRuleDTO, String> descripColumn = new ColumnConfig<SpecialRuleDTO, String>(props.description(), 150, "description");

        List<ColumnConfig<SpecialRuleDTO, ?>> l = new ArrayList<ColumnConfig<SpecialRuleDTO, ?>>();
        l.add(sm.getColumn());
        l.add(idColumn);
        l.add(nameColumn);
        l.add(descripColumn);
        cm = new ColumnModel<SpecialRuleDTO>(l);
        sm.setSelectionMode(Style.SelectionMode.MULTI);
        store = new ListStore<SpecialRuleDTO>(props.key());
        specialRuleGrid= new Grid<SpecialRuleDTO>(store, cm);
        updateStore();
        specialRuleGrid.setSelectionModel(sm);
        gridContainer = new VerticalLayoutContainer();
        gridContainer.add(specialRuleGrid);
        gridContainer.add(updateBtn);
        gridContainer.add(delSelBtn);
        this.add(gridContainer);
        VerticalLayoutContainer vc = new VerticalLayoutContainer();
        vc.add(specialRuleFields, new VerticalLayoutContainer.VerticalLayoutData(350,200,new Margins(5,5,5,5)));
        this.add(vc);
        initHandlers();
    }

    public void updateStore(){
        commonService.getSpecialRule(new AsyncCallback<List<SpecialRuleDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                System.out.println("Запрос упал " + caught.getMessage());
            }

            @Override
            public void onSuccess(List<SpecialRuleDTO> result) {
                store.clear();
                for (SpecialRuleDTO a : result) {
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
                SpecialRuleDTO a =(SpecialRuleDTO) event.getSource().getStore().get(row);
                specialRuleFields.getIdFld().setText(a.getId());
                specialRuleFields.getNameFld().setText(a.getName());
                specialRuleFields.getDescripFld().setText(a.getDescription());
            }
        });
        specialRuleFields.getSaveBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                SpecialRuleDTO a = new SpecialRuleDTO();
                a.setId(specialRuleFields.getIdFld().getText());
                a.setName(specialRuleFields.getNameFld().getText());
                a.setDescription(specialRuleFields.getDescripFld().getText());
                commonService.changeSpecialRule(a, new AsyncCallback<Void>() {
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
        specialRuleFields.getSaveNewBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                SpecialRuleDTO a = new SpecialRuleDTO();
                a.setId(specialRuleFields.getIdFld().getText());
                a.setName(specialRuleFields.getNameFld().getText());
                a.setDescription(specialRuleFields.getDescripFld().getText());
                commonService.addSpecialRule(a, new AsyncCallback<Void>() {
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

    public class SpecialRuleFields extends BorderLayoutContainer {
        TextField idFld;
        TextField nameFld;
        TextArea descripFld;
        TextButton saveBtn = new TextButton("Save");
        TextButton saveNewBtn = new TextButton("Save as new item");

        public SpecialRuleFields() {
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
