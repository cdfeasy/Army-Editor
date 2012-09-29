package com.webadmin.client.mainForm.views;

import com.armyeditor.dto.CodexDTO;
import com.armyeditor.dto.SquadBaseDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
import com.webadmin.client.mainForm.properties.CodexProperties;
import com.webadmin.client.mainForm.properties.SquadBaseProperties;
import com.webadmin.client.services.CommonService;
import com.webadmin.client.services.CommonServiceAsync;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 27.09.12
 * Time: 18:19
 * To change this template use File | Settings | File Templates.
 */
public class CodexContainer extends HorizontalPanel {
    private final CommonServiceAsync commonService = GWT.create(CommonService.class);
    VerticalLayoutContainer gridContainer;
    Grid<CodexDTO> codexGrid;
    ColumnModel<CodexDTO> cm;
    ListStore<CodexDTO> store;
    TextButton updateBtn = new TextButton("Update");
    TextButton delSelBtn = new TextButton("Delete Selection");
    final CodexFields codexFields = new CodexFields();

    public CodexContainer() {
        CodexProperties props = GWT.create(CodexProperties.class);
        IdentityValueProvider<CodexDTO> identity = new IdentityValueProvider<CodexDTO>();
        final CheckBoxSelectionModel<CodexDTO> sm = new CheckBoxSelectionModel<CodexDTO>(identity);
        ColumnConfig<CodexDTO, String> idColumn = new ColumnConfig<CodexDTO, String>(props.id(), 50, "id");
        ColumnConfig<CodexDTO, String> nameColumn = new ColumnConfig<CodexDTO, String>(props.name(), 150, "name");
        ColumnConfig<CodexDTO, String> descriptColumn = new ColumnConfig<CodexDTO, String>(props.description(), 150, "description");
        List<ColumnConfig<CodexDTO, ?>> l = new ArrayList<ColumnConfig<CodexDTO, ?>>();
        l.add(sm.getColumn());
        l.add(idColumn);
        l.add(nameColumn);
        l.add(descriptColumn);
        cm = new ColumnModel<CodexDTO>(l);
        sm.setSelectionMode(Style.SelectionMode.MULTI);
        store = new ListStore<CodexDTO>(props.key());
        codexGrid = new Grid<CodexDTO>(store, cm);
        updateStore();
        codexGrid.setSelectionModel(sm);
        gridContainer = new VerticalLayoutContainer();
        gridContainer.add(codexGrid);
        gridContainer.add(updateBtn);
        gridContainer.add(delSelBtn);
        this.add(gridContainer);
        initHandlers();
        VerticalLayoutContainer vc = new VerticalLayoutContainer();
        vc.add(codexFields, new VerticalLayoutContainer.VerticalLayoutData(350,200,new Margins(5)));
        this.add(vc);
    }

    public void updateStore() {
        commonService.getCodexs(new AsyncCallback<List<CodexDTO>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Запрос упал " + throwable.getMessage());
            }

            @Override
            public void onSuccess(List<CodexDTO> list) {
                store.clear();
                for (CodexDTO c: list) {
                    store.add(c);
                }
                codexGrid.reconfigure(store, cm);
            }
        });
    }

    void initHandlers() {
        codexGrid.addRowClickHandler(new RowClickEvent.RowClickHandler() {
            @Override
            public void onRowClick(RowClickEvent event) {
                int row = event.getRowIndex();
                CodexDTO a =(CodexDTO) event.getSource().getStore().get(row);
                codexFields.getIdFld().setText(a.getId());
                codexFields.getNameFld().setText(a.getName());
                codexFields.getDescripFld().setText(a.getDescription());

            }
        });

        updateBtn.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                updateStore();
            }
        });

        delSelBtn.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                Info.display("Click", ((TextButton) event.getSource()).getText() + " clicked");
                List selectList = codexGrid.getSelectionModel().getSelectedItems();
                commonService.delCodexs(selectList, new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Запрос упал " + throwable.getMessage());
                        Info.display("Ошибка", "Нарушается целостность базы");
                    }

                    @Override
                    public void onSuccess(Void aVoid) {
                        updateStore();
                    }
                });
            }
        });

        codexFields.getSaveBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                CodexDTO a = new CodexDTO();
                a.setId(codexFields.getIdFld().getText());
                a.setName(codexFields.getNameFld().getText());
                a.setDescription(codexFields.getDescripFld().getText());
                commonService.changeCodex(a, new AsyncCallback<Void>() {
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

        codexFields.getSaveNewBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                CodexDTO a = new CodexDTO();
                a.setId(codexFields.getIdFld().getText());
                a.setName(codexFields.getNameFld().getText());
                a.setDescription(codexFields.getDescripFld().getText());
                commonService.addCodex(a, new AsyncCallback<Void>() {
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

    public class CodexFields extends BorderLayoutContainer {
        TextField idFld;
        TextField nameFld;
        TextArea descripFld;
        TextButton saveBtn = new TextButton("Save");
        TextButton saveNewBtn = new TextButton("Save as new item");


        public CodexFields() {
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
            this.add(vc,new VerticalLayoutContainer.VerticalLayoutData(350,200,new Margins(5)));
        }

        public TextField getIdFld() {
            return idFld;
        }

        public TextField getNameFld() {
            return nameFld;
        }

        public TextArea getDescripFld() {
            return descripFld;
        }

        public TextButton getSaveBtn() {
            return saveBtn;
        }

        public TextButton getSaveNewBtn() {
            return saveNewBtn;
        }
    }
}
