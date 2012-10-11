package com.webadmin.client.mainForm.views;

import com.armyeditor.dto.CodexDTO;
import com.armyeditor.dto.ItemBaseDTO;
import com.armyeditor.dto.OptionDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
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
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;
import com.webadmin.client.mainForm.properties.CodexProperties;
import com.webadmin.client.mainForm.properties.ItemBaseProperties;
import com.webadmin.client.mainForm.properties.OptionProperties;
import com.webadmin.client.services.CommonService;
import com.webadmin.client.services.CommonServiceAsync;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 11.10.12
 * Time: 17:37
 * To change this template use File | Settings | File Templates.
 */
public class ItemBaseContainer extends HorizontalLayoutContainer {
    private final CommonServiceAsync commonService = GWT.create(CommonService.class);
    VerticalLayoutContainer gridContainer;
    Grid<ItemBaseDTO> mainGrid;
    ColumnModel<ItemBaseDTO> cm;
    ListStore<ItemBaseDTO> store;
    TextButton updateBtn = new TextButton("Update");
    TextButton delSelBtn = new TextButton("Delete Selection");
    ComboBox<CodexDTO> codexBox;
    final ItemBaseFields itemBaseFields = new ItemBaseFields();

    public ItemBaseContainer() {
        ItemBaseProperties props = GWT.create(ItemBaseProperties.class);
        IdentityValueProvider<ItemBaseDTO> identity = new IdentityValueProvider<ItemBaseDTO>();
        final CheckBoxSelectionModel<ItemBaseDTO> sm = new CheckBoxSelectionModel<ItemBaseDTO>(identity);
        ColumnConfig<ItemBaseDTO, String> idColumn = new ColumnConfig<ItemBaseDTO, String>(props.id(),50,"id");
        ColumnConfig<ItemBaseDTO, String> nameColumn = new ColumnConfig<ItemBaseDTO, String>(props.name(),60,"name");
        ColumnConfig<ItemBaseDTO, String> descriptionColumn = new ColumnConfig<ItemBaseDTO, String>(props.description(),100,"description");
        List<ColumnConfig<ItemBaseDTO,?>> l =new ArrayList<ColumnConfig<ItemBaseDTO, ?>>();
        l.add(sm.getColumn());
        l.add(idColumn);
        l.add(nameColumn);
        l.add(descriptionColumn);
        cm = new ColumnModel<ItemBaseDTO>(l);
        sm.setSelectionMode(Style.SelectionMode.MULTI);
        store = new ListStore<ItemBaseDTO>(props.key());
        mainGrid = new Grid<ItemBaseDTO>(store, cm);
        mainGrid.setSelectionModel(sm);

        CodexProperties codexProperties = GWT.create(CodexProperties.class);
        final ListStore<CodexDTO> codexListStore = new ListStore<CodexDTO>(codexProperties.key());
        commonService.getCodexs(new AsyncCallback<List<CodexDTO>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Запрос упал " + throwable.getMessage());
            }

            @Override
            public void onSuccess(List<CodexDTO> list) {
                codexListStore.addAll(list);
            }
        });
        codexBox = new ComboBox<CodexDTO>(codexListStore, codexProperties.nameLabel());
        codexBox.setTypeAhead(true);
        codexBox.setTriggerAction(ComboBoxCell.TriggerAction.ALL);

        updateBtn = new TextButton("Update", new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                if (codexBox.getValue()!=null) {
                    updateStore(codexBox.getValue().getId());
                }
            }
        });

        delSelBtn = new TextButton("Delete Selection", new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                List selectList = mainGrid.getSelectionModel().getSelectedItems();
                commonService.delItemBase(selectList, new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Запрос упал " + throwable.getMessage());
                        Info.display("Ошибка", "Нарушается целостность базы");
                    }

                    @Override
                    public void onSuccess(Void aVoid) {
                        updateStore(codexBox.getValue().getId());
                    }
                });
            }
        });

        codexBox.addSelectionHandler(new SelectionHandler<CodexDTO>() {
            @Override
            public void onSelection(SelectionEvent<CodexDTO> codexDTOSelectionEvent) {
                updateStore(codexDTOSelectionEvent.getSelectedItem().getId());
            }
        });
        gridContainer = new VerticalLayoutContainer();
        gridContainer.add(new FieldLabel(codexBox, "Select codex"));
        gridContainer.add(mainGrid);
        gridContainer.add(updateBtn);
        gridContainer.add(delSelBtn);
        this.add(gridContainer);
        VerticalLayoutContainer vc = new VerticalLayoutContainer();
        vc.add(itemBaseFields, new VerticalLayoutContainer.VerticalLayoutData(665,600,new Margins(5,5,5,5)));
        this.add(vc);
        initHandlers();
    }

    public void updateStore(String id){
        mainGrid.mask();
        commonService.getItemBaseById(id, new AsyncCallback<List<ItemBaseDTO>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Запрос упал " + throwable.getMessage());
            }

            @Override
            public void onSuccess(List<ItemBaseDTO> list) {
                store.clear();
                store.addAll(list);
                mainGrid.reconfigure(store, cm);
                mainGrid.unmask();
            }
        });
    }

    void initHandlers() {
        mainGrid.addRowClickHandler(new RowClickEvent.RowClickHandler() {
            @Override
            public void onRowClick(RowClickEvent event) {
                int row = event.getRowIndex();
                ItemBaseDTO a = (ItemBaseDTO) event.getSource().getStore().get(row);
                itemBaseFields.getIdFld().setText(a.getId());
                itemBaseFields.getNameFld().setText(a.getName());
                itemBaseFields.getDescriptionFld().setText(a.getDescription());
                itemBaseFields.optionGrid.mask();
                commonService.getOptionsByWeaponBase(a.getId(), new AsyncCallback<List<OptionDTO>>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Запрос упал " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(List<OptionDTO> list) {
                        ListStore<OptionDTO> listStore = itemBaseFields.getOptionStore();
                        listStore.clear();
                        ColumnModel<OptionDTO> cm = itemBaseFields.getOptionCm();
                        listStore.addAll(list);
                        itemBaseFields.getOptionGrid().reconfigure(listStore, cm);
                        ListStore<OptionDTO> listStore2 = itemBaseFields.getOptionStoreCommon();
                        listStore2.clear();
                        listStore2.addAll(itemBaseFields.getOptionTempStore());
                        itemBaseFields.getOptionGridCommon().reconfigure(listStore2, cm);
                        itemBaseFields.optionGrid.unmask();
                    }
                });
            }
        });

        itemBaseFields.getSaveBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                if(codexBox.getValue()!=null) {
                    if (!checkForRepeat(itemBaseFields.getOptionGrid().getStore().getAll())) {
                        ItemBaseDTO a = new ItemBaseDTO();
                        a.setId(itemBaseFields.getIdFld().getText());
                        a.setName(itemBaseFields.getNameFld().getText());
                        a.setDescription(itemBaseFields.getDescriptionFld().getText());
                        a.setCodex(codexBox.getValue());
                        ArrayList<OptionDTO> arrayList = new ArrayList<OptionDTO>(itemBaseFields.getOptionGrid().getStore().getAll());
                        a.setOptions(arrayList);
                        commonService.changeItemBase(a, new AsyncCallback<Void>() {
                            @Override
                            public void onFailure(Throwable throwable) {
                                System.out.println("Запрос упал " + throwable.getMessage());
                            }

                            @Override
                            public void onSuccess(Void aVoid) {
                                updateStore(codexBox.getValue().getId());
                            }
                        });
                    }
                    else Info.display("Ошибка", "Повторяющиеся поля");
                }
                else Info.display("","Не выбран кодекс");
            }
        });

        itemBaseFields.getSaveNewBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                if(codexBox.getValue()!=null) {
                    if (!checkForRepeat(itemBaseFields.getOptionGrid().getStore().getAll())) {
                        ItemBaseDTO a = new ItemBaseDTO();
                        a.setId(itemBaseFields.getIdFld().getText());
                        a.setName(itemBaseFields.getNameFld().getText());
                        a.setDescription(itemBaseFields.getDescriptionFld().getText());
                        a.setCodex(codexBox.getValue());
                        ArrayList<OptionDTO> arrayList = new ArrayList<OptionDTO>(itemBaseFields.getOptionGrid().getStore().getAll());
                        a.setOptions(arrayList);
                        commonService.addItemBase(a, new AsyncCallback<Void>() {
                            @Override
                            public void onFailure(Throwable throwable) {
                                System.out.println("Запрос упал " + throwable.getMessage());
                            }

                            @Override
                            public void onSuccess(Void aVoid) {
                                updateStore(codexBox.getValue().getId());
                            }
                        });
                    }
                    else Info.display("Ошибка", "Повторяющиеся поля");
                }
                else Info.display("","Не выбран кодекс");
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

    class ItemBaseFields extends BorderLayoutContainer {
        TextField idFld;
        TextField nameFld;
        TextField descriptionFld;

        Grid<OptionDTO> optionGrid;
        Grid<OptionDTO> optionGridCommon;
        private ListStore<OptionDTO> optionStore;
        private ListStore<OptionDTO> optionStoreCommon;
        private List<OptionDTO> optionTempStore;
        private ColumnModel<OptionDTO> optionCm;

        TextButton saveBtn = new TextButton("Save");
        TextButton saveNewBtn = new TextButton("Save as new item");

        ItemBaseFields() {
            VerticalLayoutContainer vc = new VerticalLayoutContainer();
            idFld = new TextField();
            vc.add(new FieldLabel(idFld,"ID"));
            nameFld = new TextField();
            vc.add(new FieldLabel(nameFld,"Name"));
            descriptionFld = new TextField();
            vc.add(new FieldLabel(descriptionFld,"Description"));

            vc.add(saveBtn);
            vc.add(saveNewBtn);

            OptionProperties optionProps = GWT.create(OptionProperties.class);
            ColumnConfig<OptionDTO, String> idColumn = new ColumnConfig<OptionDTO, String>(optionProps.id(), 100, "id");
            ColumnConfig<OptionDTO, String> nameColumn = new ColumnConfig<OptionDTO, String>(optionProps.name(), 100, "name");
            ColumnConfig<OptionDTO, String> descripColumn = new ColumnConfig<OptionDTO, String>(optionProps.description(), 100, "description");
            List<ColumnConfig<OptionDTO, ?>> l = new ArrayList<ColumnConfig<OptionDTO, ?>>();
            l.add(idColumn);
            l.add(nameColumn);
            l.add(descripColumn);
            optionCm = new ColumnModel<OptionDTO>(l);
            optionStore = new ListStore<OptionDTO>(optionProps.key());
            optionGrid = new Grid<OptionDTO>(optionStore, optionCm);
            optionStoreCommon = new ListStore<OptionDTO>(optionProps.key());
            optionGridCommon = new Grid<OptionDTO>(optionStoreCommon, optionCm);
            new GridDragSource<OptionDTO>(optionGrid);
            new GridDragSource<OptionDTO>(optionGridCommon);
            GridDropTarget<OptionDTO> target1 = new GridDropTarget<OptionDTO>(optionGrid);
            target1.setFeedback(DND.Feedback.INSERT);
            GridDropTarget<OptionDTO> target2 = new GridDropTarget<OptionDTO>(optionGridCommon);
            target2.setFeedback(DND.Feedback.INSERT);
            updateOptionCommonStore();

            FramedPanel cp = new FramedPanel();
            cp.setHeadingText("Options");
            cp.setCollapsible(true);
            cp.setAnimCollapse(true);
            HorizontalLayoutContainer con = new HorizontalLayoutContainer();
            con.add(optionGrid, new HorizontalLayoutData(.5, 1, new Margins(5)));
            con.add(optionGridCommon, new HorizontalLayoutData(.5, 1, new Margins(5, 5, 5, 0)));
            cp.add(con);
            cp.addStyleName("margin-10");
            vc.add(cp,new VerticalLayoutContainer.VerticalLayoutData(640,250,new Margins(3,0,3,0)));

            this.setBorders(true);
            this.add(vc,new VerticalLayoutContainer.VerticalLayoutData(350,700,new Margins(5)));
        }

        void updateOptionCommonStore() {
            commonService.getOptions(new AsyncCallback<List<OptionDTO>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println("Запрос упал " + throwable.getMessage());
                }

                @Override
                public void onSuccess(List<OptionDTO> optionDTOs) {
                    optionTempStore = optionDTOs;
                    optionStoreCommon.addAll(optionDTOs);
                    optionGridCommon.reconfigure(optionStoreCommon, optionCm);
                }
            });
        }

        public TextField getIdFld() {
            return idFld;
        }

        public TextField getNameFld() {
            return nameFld;
        }

        public TextField getDescriptionFld() {
            return descriptionFld;
        }

        public Grid<OptionDTO> getOptionGrid() {
            return optionGrid;
        }

        public Grid<OptionDTO> getOptionGridCommon() {
            return optionGridCommon;
        }

        public ListStore<OptionDTO> getOptionStore() {
            return optionStore;
        }

        public ListStore<OptionDTO> getOptionStoreCommon() {
            return optionStoreCommon;
        }

        public List<OptionDTO> getOptionTempStore() {
            return optionTempStore;
        }

        public ColumnModel<OptionDTO> getOptionCm() {
            return optionCm;
        }

        public TextButton getSaveBtn() {
            return saveBtn;
        }

        public TextButton getSaveNewBtn() {
            return saveNewBtn;
        }
    }
}
