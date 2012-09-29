package com.webadmin.client.mainForm.views;

import com.armyeditor.dto.CodexDTO;
import com.armyeditor.dto.FractionDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.dnd.core.client.DND;
import com.sencha.gxt.dnd.core.client.GridDragSource;
import com.sencha.gxt.dnd.core.client.GridDropTarget;
import com.sencha.gxt.widget.core.client.FramedPanel;
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
import com.webadmin.client.mainForm.properties.CodexProperties;
import com.webadmin.client.mainForm.properties.FractionProperties;
import com.webadmin.client.services.CommonService;
import com.webadmin.client.services.CommonServiceAsync;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 28.09.12
 * Time: 18:59
 * To change this template use File | Settings | File Templates.
 */
public class FractionContainer extends HorizontalPanel {
    private final CommonServiceAsync commonService = GWT.create(CommonService.class);
    VerticalLayoutContainer gridContainer;
    Grid<FractionDTO> fractionGrid;
    ColumnModel<FractionDTO> cm;
    ListStore<FractionDTO> store;
    TextButton updateBtn = new TextButton("Update");
    TextButton delSelBtn = new TextButton("Delete Selection");
    final FractionFields fractionFields = new FractionFields();

    public FractionContainer() {
        FractionProperties props = GWT.create(FractionProperties.class);
        IdentityValueProvider<FractionDTO> identity = new IdentityValueProvider<FractionDTO>();
        final CheckBoxSelectionModel<FractionDTO> sm = new CheckBoxSelectionModel<FractionDTO>(identity);
        ColumnConfig<FractionDTO, String> idColumn = new ColumnConfig<FractionDTO, String>(props.id(), 50, "id");
        ColumnConfig<FractionDTO, String> nameColumn = new ColumnConfig<FractionDTO, String>(props.name(), 150, "name");
        ColumnConfig<FractionDTO, String> descriptColumn = new ColumnConfig<FractionDTO, String>(props.description(), 150, "description");
        List<ColumnConfig<FractionDTO, ?>> l = new ArrayList<ColumnConfig<FractionDTO, ?>>();
        l.add(sm.getColumn());
        l.add(idColumn);
        l.add(nameColumn);
        l.add(descriptColumn);
        cm = new ColumnModel<FractionDTO>(l);
        sm.setSelectionMode(Style.SelectionMode.MULTI);
        store = new ListStore<FractionDTO>(props.key());
        fractionGrid = new Grid<FractionDTO>(store, cm);
        updateStore();
        fractionGrid.setSelectionModel(sm);
        gridContainer = new VerticalLayoutContainer();
        gridContainer.add(fractionGrid);
        gridContainer.add(updateBtn);
        gridContainer.add(delSelBtn);
        this.add(gridContainer);
        initHandlers();
        VerticalLayoutContainer vc = new VerticalLayoutContainer();
        vc.add(fractionFields, new VerticalLayoutContainer.VerticalLayoutData(640,250,new Margins(5)));
        this.add(vc);
    }

    void updateStore() {
        commonService.getFractions(new AsyncCallback<List<FractionDTO>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Запрос упал " + throwable.getMessage());
            }

            @Override
            public void onSuccess(List<FractionDTO> fractionDTOs) {
                store.clear();
                for (FractionDTO c: fractionDTOs) {
                    store.add(c);
                }
                fractionGrid.reconfigure(store, cm);
            }
        });
    }

    void initHandlers() {
        fractionGrid.addRowClickHandler(new RowClickEvent.RowClickHandler() {
            @Override
            public void onRowClick(RowClickEvent event) {
                int row = event.getRowIndex();
                FractionDTO a =(FractionDTO) event.getSource().getStore().get(row);
                fractionFields.getIdFld().setText(a.getId());
                fractionFields.getNameFld().setText(a.getName());
                fractionFields.getDescripFld().setText(a.getDescription());
                fractionFields.getCodexGrid().mask();
                commonService.getCodexByFraction(a.getId(), new AsyncCallback<List<CodexDTO>>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Запрос упал " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(List<CodexDTO> list) {
                        ListStore<CodexDTO> listStore = fractionFields.getCodexStore();
                        listStore.clear();
                        ColumnModel<CodexDTO> cm = fractionFields.getCodexCm();
                        listStore.addAll(list);
                        fractionFields.getCodexGrid().reconfigure(listStore,cm);
                        ListStore<CodexDTO> listStore2 = fractionFields.getCodexStoreCommon();
                        listStore2.clear();
                        listStore2.addAll(fractionFields.getCodexTempStore());
                        fractionFields.getCodexGridCommon().reconfigure(listStore2, cm);
                        fractionFields.getCodexGrid().unmask();
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

        delSelBtn.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                List selectList = fractionGrid.getSelectionModel().getSelectedItems();
                commonService.delFractions(selectList, new AsyncCallback<Void>() {
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

        fractionFields.getSaveBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                FractionDTO f = new FractionDTO();
                f.setId(fractionFields.getIdFld().getText());
                f.setName(fractionFields.getNameFld().getText());
                f.setDescription(fractionFields.getDescripFld().getText());
                ArrayList<CodexDTO> list = new ArrayList<CodexDTO>(fractionFields.getCodexGrid().getStore().getAll());
                f.setCodexes(list);
                commonService.addFraction(f, new AsyncCallback<Void>() {
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

        fractionFields.getSaveNewBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                FractionDTO f = new FractionDTO();
                f.setId(fractionFields.getIdFld().getText());
                f.setName(fractionFields.getNameFld().getText());
                f.setDescription(fractionFields.getDescripFld().getText());
                ArrayList<CodexDTO> list = new ArrayList<CodexDTO>(fractionFields.getCodexGrid().getStore().getAll());
                f.setCodexes(list);
                commonService.changeFraction(f, new AsyncCallback<Void>() {
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

    public class FractionFields extends BorderLayoutContainer {
        private TextField idFld;
        private TextField nameFld;
        private TextArea descripFld;
        private TextButton saveBtn = new TextButton("Save");
        private TextButton saveNewBtn = new TextButton("Save as new item");

        private Grid<CodexDTO> codexGrid;
        private Grid<CodexDTO> codexGridCommon;
        private ListStore<CodexDTO> codexStore;
        private ListStore<CodexDTO> codexStoreCommon;
        private List<CodexDTO> codexTempStore;
        private ColumnModel<CodexDTO> codexCm;

        public FractionFields() {
            VerticalLayoutContainer vc = new VerticalLayoutContainer();
            idFld = new TextField();
            vc.add(new FieldLabel(idFld, "ID"));
            nameFld = new TextField();
            vc.add(new FieldLabel(nameFld, "Name"));
            descripFld = new TextArea();
            vc.add(new FieldLabel(descripFld, "Description"));
            vc.add(saveBtn);
            vc.add(saveNewBtn);

            CodexProperties props = GWT.create(CodexProperties.class);
            codexStore = new ListStore<CodexDTO>(props.key());
            ColumnConfig<CodexDTO, String> idColumn = new ColumnConfig<CodexDTO, String>(props.id(), 100, "id");
            ColumnConfig<CodexDTO, String> nameColumn = new ColumnConfig<CodexDTO, String>(props.name(), 100, "name");
            ColumnConfig<CodexDTO, String> descripColumn = new ColumnConfig<CodexDTO, String>(props.description(), 100, "description");
            List<ColumnConfig<CodexDTO, ?>> l = new ArrayList<ColumnConfig<CodexDTO, ?>>();
            l.add(idColumn);
            l.add(nameColumn);
            l.add(descripColumn);
            codexCm = new ColumnModel<CodexDTO>(l);
            codexGrid = new Grid<CodexDTO>(codexStore, codexCm);

            codexStoreCommon = new ListStore<CodexDTO>(props.key());
            codexGridCommon = new Grid<CodexDTO>(codexStoreCommon, codexCm);
            new GridDragSource<CodexDTO>(codexGrid);
            new GridDragSource<CodexDTO>(codexGridCommon);
            GridDropTarget<CodexDTO> target1 = new GridDropTarget<CodexDTO>(codexGrid);
            target1.setFeedback(DND.Feedback.INSERT);
            GridDropTarget<CodexDTO> target2 = new GridDropTarget<CodexDTO>(codexGridCommon);
            target2.setFeedback(DND.Feedback.INSERT);
            updateCodexCommonStore();

            FramedPanel cp = new FramedPanel();
            cp.setHeadingText("Codex");
            cp.setCollapsible(true);
            cp.setAnimCollapse(true);
            HorizontalLayoutContainer con = new HorizontalLayoutContainer();
            con.add(codexGrid, new HorizontalLayoutContainer.HorizontalLayoutData(.5, 1, new Margins(5)));
            con.add(codexGridCommon, new HorizontalLayoutContainer.HorizontalLayoutData(.5, 1, new Margins(5, 5, 5, 0)));
            cp.add(con);
            cp.addStyleName("margin-10");
            vc.add(cp,new VerticalLayoutContainer.VerticalLayoutData(640,250,new Margins(3,0,3,0)));

            this.setHeight(420);
            this.setWidth(650);
            this.setBorders(true);
            this.add(vc,new VerticalLayoutContainer.VerticalLayoutData(650,450,new Margins(5)));
        }

        void updateCodexCommonStore() {
            commonService.getCodexs(new AsyncCallback<List<CodexDTO>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println("Запрос упал " + throwable.getMessage());
                }

                @Override
                public void onSuccess(List<CodexDTO> list) {
                    codexTempStore = list;
                    codexStoreCommon.addAll(list);
                    codexGridCommon.reconfigure(codexStoreCommon, codexCm);
                }
            });
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

        public Grid<CodexDTO> getCodexGrid() {
            return codexGrid;
        }

        public Grid<CodexDTO> getCodexGridCommon() {
            return codexGridCommon;
        }

        public ListStore<CodexDTO> getCodexStore() {
            return codexStore;
        }

        public ListStore<CodexDTO> getCodexStoreCommon() {
            return codexStoreCommon;
        }

        public List<CodexDTO> getCodexTempStore() {
            return codexTempStore;
        }

        public ColumnModel<CodexDTO> getCodexCm() {
            return codexCm;
        }
    }
}
