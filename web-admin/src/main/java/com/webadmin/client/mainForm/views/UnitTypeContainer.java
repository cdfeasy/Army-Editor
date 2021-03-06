package com.webadmin.client.mainForm.views;

import com.armyeditor.dto.OptionDTO;
import com.armyeditor.dto.UnitTypeDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.RowClickEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;
import com.webadmin.client.mainForm.properties.OptionProperties;
import com.webadmin.client.mainForm.properties.UnitTypeProperties;
import com.webadmin.client.services.CommonService;
import com.webadmin.client.services.CommonServiceAsync;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 02.08.12
 * Time: 19:28
 * To change this template use File | Settings | File Templates.
 */
public class UnitTypeContainer extends HorizontalLayoutContainer {
    private final CommonServiceAsync commonService = GWT.create(CommonService.class);
    VerticalLayoutContainer gridContainer;
    Grid<UnitTypeDTO> specialRuleGrid;
    ColumnModel<UnitTypeDTO> cm;
    ListStore<UnitTypeDTO> store;
    TextButton updateBtn = new TextButton("Update");
    TextButton delSelBtn = new TextButton("Delete Selection");
    final UnitTypeFields unitTypeFields = new UnitTypeFields();

    public UnitTypeContainer(){
        UnitTypeProperties props = GWT.create(UnitTypeProperties.class);
        IdentityValueProvider<UnitTypeDTO> identity = new IdentityValueProvider<UnitTypeDTO>();
        final CheckBoxSelectionModel<UnitTypeDTO> sm = new CheckBoxSelectionModel<UnitTypeDTO>(identity);
        ColumnConfig<UnitTypeDTO, String> idColumn = new ColumnConfig<UnitTypeDTO, String>(props.id(), 50, "id");
        ColumnConfig<UnitTypeDTO, String> nameColumn = new ColumnConfig<UnitTypeDTO, String>(props.name(), 150, "name");
        List<ColumnConfig<UnitTypeDTO, ?>> l = new ArrayList<ColumnConfig<UnitTypeDTO, ?>>();
        l.add(sm.getColumn());
        l.add(idColumn);
        l.add(nameColumn);
        cm = new ColumnModel<UnitTypeDTO>(l);
        sm.setSelectionMode(Style.SelectionMode.MULTI);
        store = new ListStore<UnitTypeDTO>(props.key());
        specialRuleGrid= new Grid<UnitTypeDTO>(store, cm);
        updateStore();
        specialRuleGrid.setSelectionModel(sm);
        gridContainer = new VerticalLayoutContainer();
        gridContainer.add(specialRuleGrid);
        gridContainer.add(updateBtn);
        gridContainer.add(delSelBtn);
        this.add(gridContainer);
        VerticalLayoutContainer vc = new VerticalLayoutContainer();
        vc.add(unitTypeFields, new VerticalLayoutContainer.VerticalLayoutData(650,370,new Margins(5)));
        this.add(vc);
        initHandlers();
    }

    public void updateStore(){
        commonService.getUnitType(new AsyncCallback<List<UnitTypeDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                System.out.println("Запрос упал " + caught.getMessage());
            }

            @Override
            public void onSuccess(List<UnitTypeDTO> result) {
                store.clear();
                for (UnitTypeDTO a : result) {
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
                UnitTypeDTO a =(UnitTypeDTO) event.getSource().getStore().get(row);
                unitTypeFields.getIdFld().setText(a.getId());
                unitTypeFields.getNameFld().setText(a.getName());
                commonService.getOptionsByUnit(a.getId(),new AsyncCallback<List<OptionDTO>>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Запрос упал " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(List<OptionDTO> optionDTOs) {
                        ListStore<OptionDTO> listStore = unitTypeFields.getStore();
                        listStore.clear();
                        ColumnModel<OptionDTO> cm = unitTypeFields.getCm();
                        listStore.addAll(optionDTOs);
                        unitTypeFields.getOptionGrid().reconfigure(listStore,cm);
                        ListStore<OptionDTO> listStore2 = unitTypeFields.store2;
                        listStore2.clear();
                        listStore2.addAll(unitTypeFields.tempStore2);
                        unitTypeFields.getOptionGrid2().reconfigure(listStore2, cm);
                    }
                });
            }
        });
        unitTypeFields.getSaveBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                if (!checkForRepeat(unitTypeFields.getOptionGrid().getStore().getAll())) {
                    UnitTypeDTO a = new UnitTypeDTO();
                    a.setId(unitTypeFields.getIdFld().getText());
                    a.setName(unitTypeFields.getNameFld().getText());
                    ArrayList<OptionDTO> arrayList = new ArrayList<OptionDTO>(unitTypeFields.getOptionGrid().getStore().getAll());
                    a.setOptions(arrayList);
                    commonService.changeUnitType(a, new AsyncCallback<Void>() {
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
                else Info.display("Ошибка", "Повторяющиеся поля");
            }
        });
        unitTypeFields.getSaveNewBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                if (!checkForRepeat(unitTypeFields.getOptionGrid().getStore().getAll())) {
                    UnitTypeDTO a = new UnitTypeDTO();
                    a.setId(unitTypeFields.getIdFld().getText());
                    a.setName(unitTypeFields.getNameFld().getText());
                    ArrayList<OptionDTO> arrayList = new ArrayList<OptionDTO>(unitTypeFields.getOptionGrid().getStore().getAll());
                    a.setOptions(arrayList);
                    commonService.addUnitType(a, new AsyncCallback<Void>() {
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
                else Info.display("Ошибка", "Повторяющиеся поля");
            }
        });

        delSelBtn.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                Info.display("Click", ((TextButton) event.getSource()).getText() + " clicked");
                List selectList = specialRuleGrid.getSelectionModel().getSelectedItems();
                commonService.delUnitTypes(selectList, new AsyncCallback<Void>() {
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

        updateBtn.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                updateStore();
            }
        });

    }

    static boolean checkForRepeat(List list) {
        HashSet set = new HashSet();
        boolean flag = false;
        for (Object o:list) {
            if (!set.add(o)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public class UnitTypeFields extends BorderLayoutContainer {
        TextField idFld;
        TextField nameFld;
        TextButton saveBtn = new TextButton("Save");
        TextButton saveNewBtn = new TextButton("Save as new item");
        Grid<OptionDTO> optionGrid;
        Grid<OptionDTO> optionGrid2;
        private ListStore<OptionDTO> store;
        private ListStore<OptionDTO> store2;
        private List<OptionDTO> tempStore2;
        private ColumnModel<OptionDTO> cm;

        public UnitTypeFields(){
            VerticalLayoutContainer vc = new VerticalLayoutContainer();
            idFld = new TextField();
            vc.add(new FieldLabel(idFld, "ID"));
            nameFld = new TextField();
            vc.add(new FieldLabel(nameFld, "Name"));
            vc.add(saveBtn);
            vc.add(saveNewBtn);

            OptionProperties props = GWT.create(OptionProperties.class);
            ColumnConfig<OptionDTO, String> idColumn = new ColumnConfig<OptionDTO, String>(props.id(), 100, "id");
            ColumnConfig<OptionDTO, String> nameColumn = new ColumnConfig<OptionDTO, String>(props.name(), 100, "name");
            ColumnConfig<OptionDTO, String> descripColumn = new ColumnConfig<OptionDTO, String>(props.description(), 100, "description");
            ColumnConfig<OptionDTO, String> actionColumn = new ColumnConfig<OptionDTO, String>(props.action(), 100, "action");
            List<ColumnConfig<OptionDTO, ?>> l = new ArrayList<ColumnConfig<OptionDTO, ?>>();
            l.add(idColumn);
            l.add(nameColumn);
            l.add(descripColumn);
            l.add(actionColumn);
            cm = new ColumnModel<OptionDTO>(l);
            store = new ListStore<OptionDTO>(props.key());
            optionGrid = new Grid<OptionDTO>(store, cm);

            store2 = new ListStore<OptionDTO>(props.key());
            optionGrid2 = new Grid<OptionDTO>(store2, cm);
            new GridDragSource<OptionDTO>(optionGrid);
            new GridDragSource<OptionDTO>(optionGrid2);
            GridDropTarget<OptionDTO> target1 = new GridDropTarget<OptionDTO>(optionGrid);
            target1.setFeedback(DND.Feedback.INSERT);
            GridDropTarget<OptionDTO> target2 = new GridDropTarget<OptionDTO>(optionGrid2);
            target2.setFeedback(DND.Feedback.INSERT);

            updateStore2();

            FramedPanel cp = new FramedPanel();
            cp.setHeadingText("Options");
            cp.setHeight(250);
            cp.setWidth(630);
            cp.setCollapsible(true);
            cp.setAnimCollapse(true);
            HorizontalLayoutContainer con = new HorizontalLayoutContainer();
            con.add(optionGrid, new HorizontalLayoutData(.5, 1, new Margins(5)));
            con.add(optionGrid2, new HorizontalLayoutData(.5, 1, new Margins(5, 5, 5, 0)));
            cp.add(con);
            cp.addStyleName("margin-10");
            vc.add(cp);
            this.setBorders(true);
            this.add(vc, new VerticalLayoutContainer.VerticalLayoutData(630,400,new Margins(5)));
        }

        void updateStore2() {
            commonService.getOptions(new AsyncCallback<List<OptionDTO>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println("Запрос упал " + throwable.getMessage());
                }

                @Override
                public void onSuccess(List<OptionDTO> optionDTOs) {
                    tempStore2 = optionDTOs;
                    store2.addAll(optionDTOs);
                    optionGrid2.reconfigure(store2, cm);
                }
            });
        }

        public Grid<OptionDTO> getOptionGrid2() {
            return optionGrid2;
        }

        public void setOptionGrid2(Grid<OptionDTO> optionGrid2) {
            this.optionGrid2 = optionGrid2;
        }

        public ListStore<OptionDTO> getStore2() {
            return store2;
        }

        public void setStore2(ListStore<OptionDTO> store2) {
            this.store2 = store2;
        }

        public Grid<OptionDTO> getOptionGrid() {
            return optionGrid;
        }

        public void setOptionGrid(Grid<OptionDTO> optionGrid) {
            this.optionGrid = optionGrid;
        }

        public ListStore<OptionDTO> getStore() {
            return store;
        }

        public void setStore(ListStore<OptionDTO> store) {
            this.store = store;
        }

        public ColumnModel<OptionDTO> getCm() {
            return cm;
        }

        public void setCm(ColumnModel<OptionDTO> cm) {
            this.cm = cm;
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
