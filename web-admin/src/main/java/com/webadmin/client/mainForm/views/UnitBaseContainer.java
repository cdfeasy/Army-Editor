package com.webadmin.client.mainForm.views;

import com.armyeditor.dto.*;
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
import com.sencha.gxt.widget.core.client.form.*;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridRowEditing;
import com.sencha.gxt.widget.core.client.info.Info;
import com.webadmin.client.mainForm.properties.*;
import com.webadmin.client.services.CommonService;
import com.webadmin.client.services.CommonServiceAsync;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 17.08.12
 * Time: 18:49
 * To change this template use File | Settings | File Templates.
 */
public class UnitBaseContainer extends HorizontalLayoutContainer {
    private final CommonServiceAsync commonService = GWT.create(CommonService.class);
    VerticalLayoutContainer gridContainer;
    Grid<UnitBaseDTO> unitBaseGrid;
    ColumnModel<UnitBaseDTO> cm;
    ListStore<UnitBaseDTO> store;
    TextButton updateBtn = new TextButton("Update");
    TextButton delSelBtn = new TextButton("Delete Selection");
    ComboBox<CodexDTO> codexBox;
    final UnitBaseFields unitBaseFields = new UnitBaseFields();

    public UnitBaseContainer(){
        UnitBaseProperties props = GWT.create(UnitBaseProperties.class);
        IdentityValueProvider<UnitBaseDTO> identity = new IdentityValueProvider<UnitBaseDTO>();
        final CheckBoxSelectionModel<UnitBaseDTO> sm = new CheckBoxSelectionModel<UnitBaseDTO>(identity);
        ColumnConfig<UnitBaseDTO, String> idColumn = new ColumnConfig<UnitBaseDTO, String>(props.id(),50,"id");
        ColumnConfig<UnitBaseDTO, Integer> wsColumn = new ColumnConfig<UnitBaseDTO, Integer>(props.ws(), 50, "ws");
        ColumnConfig<UnitBaseDTO, Integer> bsColumn = new ColumnConfig<UnitBaseDTO, Integer>(props.bs(), 50, "bs");
        ColumnConfig<UnitBaseDTO, Integer> sColumn = new ColumnConfig<UnitBaseDTO, Integer>(props.s(), 50, "s");
        ColumnConfig<UnitBaseDTO, Integer> tColumn = new ColumnConfig<UnitBaseDTO, Integer>(props.t(), 50, "t");
        ColumnConfig<UnitBaseDTO, Integer> wColumn = new ColumnConfig<UnitBaseDTO, Integer>(props.w(), 50, "w");
        ColumnConfig<UnitBaseDTO, Integer> iColumn = new ColumnConfig<UnitBaseDTO, Integer>(props.i(), 50, "i");
        ColumnConfig<UnitBaseDTO, Integer> aColumn = new ColumnConfig<UnitBaseDTO, Integer>(props.a(), 50, "a");
        ColumnConfig<UnitBaseDTO, String>  swColumn = new ColumnConfig<UnitBaseDTO, String>(props.sv(), 50, "sw");
        ColumnConfig<UnitBaseDTO, Integer> ldColumn = new ColumnConfig<UnitBaseDTO, Integer>(props.ld(), 50, "ld");
        ColumnConfig<UnitBaseDTO, Integer> costColumn = new ColumnConfig<UnitBaseDTO, Integer>(props.cost(), 50, "cost");

        List<ColumnConfig<UnitBaseDTO,?>> l =new ArrayList<ColumnConfig<UnitBaseDTO, ?>>();
        l.add(sm.getColumn());
        l.add(idColumn);
        l.add(wsColumn);
        l.add(bsColumn);
        l.add(sColumn);
        l.add(tColumn);
        l.add(wColumn);
        l.add(iColumn);
        l.add(aColumn);
        l.add(swColumn);
        l.add(ldColumn);
        l.add(costColumn);
        cm = new ColumnModel<UnitBaseDTO>(l);
        sm.setSelectionMode(Style.SelectionMode.MULTI);
        store = new ListStore<UnitBaseDTO>(props.key());
        unitBaseGrid = new Grid<UnitBaseDTO>(store, cm);
        unitBaseGrid.setSelectionModel(sm);

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

        updateBtn = new TextButton("Update", new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                updateStore(codexBox.getValue().getId());
            }
        });
        delSelBtn = new TextButton("Delete Selection", new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                List selectList = unitBaseGrid.getSelectionModel().getSelectedItems();
                commonService.delUnitBases(selectList, new AsyncCallback<Void>() {
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
        gridContainer.add(codexBox);
        gridContainer.add(unitBaseGrid);
        gridContainer.add(updateBtn);
        gridContainer.add(delSelBtn);
        this.add(gridContainer);
        VerticalLayoutContainer vc = new VerticalLayoutContainer();
        vc.add(unitBaseFields, new VerticalLayoutContainer.VerticalLayoutData(665,1200,new Margins(5,5,5,5)));
        this.add(vc);
        initHandlers();
    }

    public void updateStore(String id){
        unitBaseGrid.mask();
        commonService.getUnitBase(id, new AsyncCallback<List<UnitBaseDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                System.out.println("Запрос упал " + caught.getMessage());
                unitBaseGrid.unmask();
            }

            @Override
            public void onSuccess(List<UnitBaseDTO> result) {
                store.clear();
                for (UnitBaseDTO a : result) {
                    store.add(a);
                }
                unitBaseGrid.reconfigure(store, cm);
                unitBaseGrid.unmask();
            }
        });
    }

    void initHandlers() {
        unitBaseGrid.addRowClickHandler(new RowClickEvent.RowClickHandler() {
            @Override
            public void onRowClick(RowClickEvent event) {
                int row = event.getRowIndex();
                UnitBaseDTO a =(UnitBaseDTO) event.getSource().getStore().get(row);
                unitBaseFields.getIdFld().setText(a.getId());
                unitBaseFields.getWsFld().setText(Integer.toString(a.getWs()));
                unitBaseFields.getBsFld().setText(Integer.toString(a.getBs()));
                unitBaseFields.getsFld().setText(Integer.toString(a.getS()));
                unitBaseFields.gettFld().setText(Integer.toString(a.getT()));
                unitBaseFields.getwFld().setText(Integer.toString(a.getW()));
                unitBaseFields.getiFld().setText(Integer.toString(a.getI()));
                unitBaseFields.getaFld().setText(Integer.toString(a.getA()));
                unitBaseFields.getLdFld().setText(Integer.toString(a.getLd()));
                unitBaseFields.getSvFld().setText(a.getSv());
                unitBaseFields.getCostFld().setText(Integer.toString(a.getCost()));
                unitBaseFields.getUnitTypeBox().setValue(a.getUnitType());
                unitBaseFields.optionGrid.mask();
                unitBaseFields.weaponGrid.mask();
                unitBaseFields.itemGrid.mask();
                commonService.getUnitById(a.getId(), new AsyncCallback<UnitBaseDTO>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Запрос упал " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(UnitBaseDTO unitBaseDTO) {
                        ListStore<OptionDTO> listStore = unitBaseFields.getStore();
                        listStore.clear();
                        ColumnModel<OptionDTO> cm = unitBaseFields.getOptionCm();
                        listStore.addAll(unitBaseDTO.getOptions());
                        unitBaseFields.getOptionGrid().reconfigure(listStore,cm);
                        ListStore<OptionDTO> listStore2 = unitBaseFields.getOptionStoreCommon();
                        listStore2.clear();
                        listStore2.addAll(unitBaseFields.getOptionTempStore());
                        unitBaseFields.getOptionGridCommon().reconfigure(listStore2, cm);

                        ListStore<WeaponDTO> weaponStore = unitBaseFields.getWeaponStore();
                        weaponStore.clear();
                        ColumnModel<WeaponDTO> weaponCm = unitBaseFields.getWeaponCm();
                        weaponStore.addAll(unitBaseDTO.getWeapons());
                        unitBaseFields.getWeaponGrid().reconfigure(weaponStore,weaponCm);

                        ListStore<ItemDTO> itemStore = unitBaseFields.getItemStore();
                        itemStore.clear();
                        ColumnModel<ItemDTO> itemCm = unitBaseFields.getItemCm();
                        itemStore.addAll(unitBaseDTO.getItems());
                        unitBaseFields.getItemGrid().reconfigure(itemStore,itemCm);
                        unitBaseFields.optionGrid.unmask();
                        unitBaseFields.weaponGrid.unmask();
                        unitBaseFields.itemGrid.unmask();
                    }

                });
            }
        });
        unitBaseFields.getSaveBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                UnitBaseDTO a = new UnitBaseDTO();
                a.setId(unitBaseFields.getIdFld().getText());
                a.setWs(Integer.parseInt(unitBaseFields.getWsFld().getText()));
                a.setBs(Integer.parseInt(unitBaseFields.getBsFld().getText()));
                a.setS(Integer.parseInt(unitBaseFields.getsFld().getText()));
                a.setT(Integer.parseInt(unitBaseFields.gettFld().getText()));
                a.setW(Integer.parseInt(unitBaseFields.getwFld().getText()));
                a.setI(Integer.parseInt(unitBaseFields.getiFld().getText()));
                a.setA(Integer.parseInt(unitBaseFields.getaFld().getText()));
                a.setLd(Integer.parseInt(unitBaseFields.getLdFld().getText()));
                a.setSv(unitBaseFields.getSvFld().getText());
                a.setCost(Integer.parseInt(unitBaseFields.getCostFld().getText()));
                a.setUnitType(unitBaseFields.getUnitTypeBox().getValue());
                a.setCodex(codexBox.getValue());
                ArrayList<OptionDTO> arrayList = new ArrayList<OptionDTO>(unitBaseFields.getOptionGrid().getStore().getAll());
                a.setOptions(arrayList);
                unitBaseFields.getWeaponGrid().getStore().commitChanges();
                ArrayList<WeaponDTO> weaponList = new ArrayList<WeaponDTO>(unitBaseFields.getWeaponGrid().getStore().getAll());
                a.setWeapons(weaponList);
                unitBaseFields.getItemGrid().getStore().commitChanges();
                ArrayList<ItemDTO> itemList  = new ArrayList<ItemDTO>(unitBaseFields.getItemGrid().getStore().getAll());
                a.setItems(itemList);

                commonService.changeUnitBase(a, new AsyncCallback<Void>() {
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
        });
        unitBaseFields.getSaveNewBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                UnitBaseDTO a = new UnitBaseDTO();
                a.setId(unitBaseFields.getIdFld().getText());
                a.setWs(Integer.parseInt(unitBaseFields.getWsFld().getText()));
                a.setBs(Integer.parseInt(unitBaseFields.getBsFld().getText()));
                a.setS(Integer.parseInt(unitBaseFields.getsFld().getText()));
                a.setT(Integer.parseInt(unitBaseFields.gettFld().getText()));
                a.setW(Integer.parseInt(unitBaseFields.getwFld().getText()));
                a.setI(Integer.parseInt(unitBaseFields.getiFld().getText()));
                a.setA(Integer.parseInt(unitBaseFields.getaFld().getText()));
                a.setLd(Integer.parseInt(unitBaseFields.getLdFld().getText()));
                a.setSv(unitBaseFields.getSvFld().getText());
                a.setCost(Integer.parseInt(unitBaseFields.getCostFld().getText()));
                a.setUnitType(unitBaseFields.getUnitTypeBox().getValue());
                a.setCodex(codexBox.getValue());
                ArrayList<OptionDTO> arrayList = new ArrayList<OptionDTO>(unitBaseFields.getOptionGrid().getStore().getAll());
                a.setOptions(arrayList);
                unitBaseFields.getWeaponGrid().getStore().commitChanges();
                ArrayList<WeaponDTO> weaponList = new ArrayList<WeaponDTO>(unitBaseFields.getWeaponGrid().getStore().getAll());
                a.setWeapons(weaponList);
                unitBaseFields.getItemGrid().getStore().commitChanges();
                ArrayList<ItemDTO> itemList  = new ArrayList<ItemDTO>(unitBaseFields.getItemGrid().getStore().getAll());
                a.setItems(itemList);

                commonService.addUnitBase(a, new AsyncCallback<Void>() {
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
        });
    }


    public class UnitBaseFields extends BorderLayoutContainer {
        TextField idFld;
        TextField wsFld;
        TextField bsFld;
        TextField sFld;
        TextField tFld;
        TextField wFld;
        TextField iFld;
        TextField aFld;
        TextField ldFld;
        TextField svFld;
        TextField costFld;

        ComboBox<UnitTypeDTO> unitTypeBox;
        ListStore<UnitTypeDTO> unitTypeStore;

        Grid<OptionDTO> optionGrid;
        Grid<OptionDTO> optionGridCommon;
        private ListStore<OptionDTO> optionStore;
        private ListStore<OptionDTO> optionStoreCommon;
        private List<OptionDTO> optionTempStore;
        private ColumnModel<OptionDTO> optionCm;

        Grid<WeaponDTO> weaponGrid;
        private ListStore<WeaponDTO> weaponStore;
        private ColumnModel<WeaponDTO> weaponCm;

        ComboBox<WeaponBaseDTO> weaponBaseBox;
        ListStore<WeaponBaseDTO> weaponBaseStore;
        TextButton addWeaponBtn;

        Grid<ItemDTO> itemGrid;
        private ListStore<ItemDTO> itemStore;
        private ColumnModel<ItemDTO> itemCm;

        ComboBox<ItemBaseDTO> itemBaseBox;
        ListStore<ItemBaseDTO> itemBaseStore;
        TextButton addItemBtn;

        TextButton saveBtn = new TextButton("Save");
        TextButton saveNewBtn = new TextButton("Save as new item");

        public UnitBaseFields() {
            VerticalLayoutContainer vc = new VerticalLayoutContainer();
            idFld = new TextField();
            vc.add(new FieldLabel(idFld,"ID"));
            wsFld = new TextField();
            vc.add(new FieldLabel(wsFld,"WS"));
            bsFld = new TextField();
            vc.add(new FieldLabel(bsFld,"BS"));
            sFld = new TextField();
            vc.add(new FieldLabel(sFld,"S"));
            tFld = new TextField();
            vc.add(new FieldLabel(tFld,"T"));
            wFld = new TextField();
            vc.add(new FieldLabel(wFld,"W"));
            iFld = new TextField();
            vc.add(new FieldLabel(iFld,"I"));
            aFld = new TextField();
            vc.add(new FieldLabel(aFld,"A"));
            ldFld = new TextField();
            vc.add(new FieldLabel(ldFld,"LD"));
            svFld = new TextField();
            vc.add(new FieldLabel(svFld,"SV"));
            costFld = new TextField();
            vc.add(new FieldLabel(costFld,"Cost"));
            UnitTypeProperties props = GWT.create(UnitTypeProperties.class);
            unitTypeStore = new ListStore<UnitTypeDTO>(props.key());
            commonService.getUnitType(new AsyncCallback<List<UnitTypeDTO>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println("Запрос упал " + throwable.getMessage());
                }

                @Override
                public void onSuccess(List<UnitTypeDTO> unitTypeDTOs) {
                    unitTypeStore.addAll(unitTypeDTOs);
                }
            });
            unitTypeBox = new ComboBox<UnitTypeDTO>(unitTypeStore, props.nameLabel());
            unitTypeBox.setTypeAhead(true);
            vc.add(new FieldLabel(unitTypeBox, "Unit Base"));
            vc.add(saveBtn);
            vc.add(saveNewBtn);

            OptionProperties optionProps = GWT.create(OptionProperties.class);
            ColumnConfig<OptionDTO, String> idColumn = new ColumnConfig<OptionDTO, String>(optionProps.id(), 100, "id");
            ColumnConfig<OptionDTO, String> nameColumn = new ColumnConfig<OptionDTO, String>(optionProps.name(), 100, "name");
            ColumnConfig<OptionDTO, String> descripColumn = new ColumnConfig<OptionDTO, String>(optionProps.description(), 100, "description");
            ColumnConfig<OptionDTO, String> actionColumn = new ColumnConfig<OptionDTO, String>(optionProps.action(), 100, "action");
            List<ColumnConfig<OptionDTO, ?>> l = new ArrayList<ColumnConfig<OptionDTO, ?>>();
            l.add(idColumn);
            l.add(nameColumn);
            l.add(descripColumn);
            l.add(actionColumn);
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

            WeaponProperties weaponProps = GWT.create(WeaponProperties.class);
            ColumnConfig<WeaponDTO, Long> idColumnWeapon = new ColumnConfig<WeaponDTO, Long>(weaponProps.id(), 100, "id");
            ColumnConfig<WeaponDTO, String> weaponColumn = new ColumnConfig<WeaponDTO, String>(weaponProps.weapon(), 100, "weapon");
            ColumnConfig<WeaponDTO, Integer> costColumnWeapon = new ColumnConfig<WeaponDTO, Integer>(weaponProps.cost(), 100, "cost");
            List<ColumnConfig<WeaponDTO, ?>> lWeapon = new ArrayList<ColumnConfig<WeaponDTO, ?>>();
            lWeapon.add(idColumnWeapon);
            lWeapon.add(weaponColumn);
            lWeapon.add(costColumnWeapon);
            weaponCm = new ColumnModel<WeaponDTO>(lWeapon);
            weaponStore = new ListStore<WeaponDTO>(weaponProps.key());
            weaponGrid = new Grid<WeaponDTO>(weaponStore, weaponCm);
            final GridEditing<WeaponDTO> editing = new GridInlineEditing(weaponGrid);
            NumberPropertyEditor np = new NumberPropertyEditor.IntegerPropertyEditor();
            editing.addEditor(costColumnWeapon, new NumberField<Integer>(np));

            WeaponBaseProperties weaponBaseProps = GWT.create(WeaponBaseProperties.class);
            weaponBaseStore = new ListStore<WeaponBaseDTO>(weaponBaseProps.key());
            commonService.getWeaponBases(new AsyncCallback<List<WeaponBaseDTO>>() {
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

            ItemProperties itemProperties = GWT.create(ItemProperties.class);
            ColumnConfig<ItemDTO, Long> idColumnItem = new ColumnConfig<ItemDTO, Long>(itemProperties.id(), 100, "id");
            ColumnConfig<ItemDTO, String> itemBaseColumn = new ColumnConfig<ItemDTO, String>(itemProperties.itemBase(), 100, "weapon");
            ColumnConfig<ItemDTO, Integer> costColumnItem = new ColumnConfig<ItemDTO, Integer>(itemProperties.cost(), 100, "cost");
            List<ColumnConfig<ItemDTO,?>> lItem = new ArrayList<ColumnConfig<ItemDTO, ?>>();
            lItem.add(idColumnItem);
            lItem.add(itemBaseColumn);
            lItem.add(costColumnItem);
            itemCm = new ColumnModel<ItemDTO>(lItem);
            itemStore = new ListStore<ItemDTO>(itemProperties.key());
            itemGrid = new Grid<ItemDTO>(itemStore, itemCm);
            final GridEditing<ItemDTO> itemEdit = new GridInlineEditing<ItemDTO>(itemGrid);
            np = new NumberPropertyEditor.IntegerPropertyEditor();
            itemEdit.addEditor(costColumnItem, new NumberField<Integer>(np));

            ItemBaseProperties itemBaseProperties = GWT.create(ItemBaseProperties.class);
            itemBaseStore = new ListStore<ItemBaseDTO>(itemBaseProperties.key());
            commonService.getItemBases(new AsyncCallback<List<ItemBaseDTO>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println("Запрос упал " + throwable.getMessage());
                }

                @Override
                public void onSuccess(List<ItemBaseDTO> itemBaseDTOs) {
                    itemBaseStore.addAll(itemBaseDTOs);
                }
            });
            itemBaseBox = new ComboBox<ItemBaseDTO>(itemBaseStore, itemBaseProperties.nameLabel());
            itemBaseBox.setTypeAhead(true);
            itemBaseBox.setTriggerAction(ComboBoxCell.TriggerAction.ALL);

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

            cp = new FramedPanel();
            cp.setHeadingText("Weapons");
            cp.setCollapsible(true);
            cp.setAnimCollapse(true);
            con = new HorizontalLayoutContainer();
            con.add(weaponGrid, new HorizontalLayoutData(.5, 1, new Margins(5)));
            addWeaponBtn = new TextButton("Add Weapon", new SelectEvent.SelectHandler() {
                @Override
                public void onSelect(SelectEvent event) {
                    WeaponDTO w = new WeaponDTO();
                    WeaponBaseDTO weaponBaseDTO = weaponBaseBox.getValue();
                    w.setWeapon(weaponBaseDTO);
                    w.setCost(0);
                    weaponStore.add(w);
                    updateWeaponGrid();

                }
            });
            TextButton delWeaponBtn = new TextButton("Delete Selected Weapon", new SelectEvent.SelectHandler() {
                @Override
                public void onSelect(SelectEvent event) {
                    WeaponDTO w = weaponGrid.getSelectionModel().getSelectedItem();
                    weaponStore.remove(w);
                    updateWeaponGrid();
                }
            });
            VerticalLayoutContainer verticalLayoutContainer = new VerticalLayoutContainer();
            verticalLayoutContainer.add(weaponBaseBox);
            verticalLayoutContainer.add(addWeaponBtn);
            verticalLayoutContainer.add(delWeaponBtn);
            con.add(verticalLayoutContainer, new HorizontalLayoutData(.5, 1, new Margins(5, 5, 5, 0)));
            cp.add(con);
            cp.addStyleName("margin-10");
            vc.add(cp,new VerticalLayoutContainer.VerticalLayoutData(640,250,new Margins(3,0,3,0)));

            cp = new FramedPanel();
            cp.setHeadingText("Items");
            cp.setCollapsible(true);
            cp.setAnimCollapse(true);
            con = new HorizontalLayoutContainer();
            con.add(itemGrid, new HorizontalLayoutData(.5, 1, new Margins(5)));
            addItemBtn = new TextButton("Add Item", new SelectEvent.SelectHandler() {
                @Override
                public void onSelect(SelectEvent event) {
                    ItemDTO i = new ItemDTO();
                    ItemBaseDTO itemBaseDTO = itemBaseBox.getValue();
                    i.setItemBase(itemBaseDTO);
                    i.setCost(0);
                    itemStore.add(i);
                    updateItemGrid();
                }
            });
            TextButton delItemBtn = new TextButton("Delete Selected Item", new SelectEvent.SelectHandler() {
                @Override
                public void onSelect(SelectEvent event) {
                    ItemDTO i = itemGrid.getSelectionModel().getSelectedItem();
                    List<ItemDTO> list = new ArrayList<ItemDTO>();
                    list.add(i);
                    commonService.delItems(list, new AsyncCallback<Void>() {
                        @Override
                        public void onFailure(Throwable throwable) {
                            System.out.println("Запрос упал " + throwable.getMessage());
                        }

                        @Override
                        public void onSuccess(Void aVoid) {
                            updateItemGrid();
                        }
                    });
                }
            });
            verticalLayoutContainer = new VerticalLayoutContainer();
            verticalLayoutContainer.add(itemBaseBox);
            verticalLayoutContainer.add(addItemBtn);
            con.add(verticalLayoutContainer, new HorizontalLayoutData(.5, 1, new Margins(5, 5, 5, 0)));
            cp.add(con);
            cp.addStyleName("margin-10");
            vc.add(cp,new VerticalLayoutContainer.VerticalLayoutData(640,250,new Margins(3,0,3,0)));

            this.setBorders(true);
            this.add(vc,new VerticalLayoutContainer.VerticalLayoutData(350,700,new Margins(5)));
        }

        public Grid<ItemDTO> getItemGrid() {
            return itemGrid;
        }

        public void setItemGrid(Grid<ItemDTO> itemGrid) {
            this.itemGrid = itemGrid;
        }

        public ListStore<ItemDTO> getItemStore() {
            return itemStore;
        }

        public void setItemStore(ListStore<ItemDTO> itemStore) {
            this.itemStore = itemStore;
        }

        public ColumnModel<ItemDTO> getItemCm() {
            return itemCm;
        }

        public void setItemCm(ColumnModel<ItemDTO> itemCm) {
            this.itemCm = itemCm;
        }

        public ListStore<OptionDTO> getOptionStore() {
            return optionStore;
        }

        public void setOptionStore(ListStore<OptionDTO> optionStore) {
            this.optionStore = optionStore;
        }

        public ListStore<OptionDTO> getOptionStoreCommon() {
            return optionStoreCommon;
        }

        public void setOptionStoreCommon(ListStore<OptionDTO> optionStoreCommon) {
            this.optionStoreCommon = optionStoreCommon;
        }

        public List<OptionDTO> getOptionTempStore() {
            return optionTempStore;
        }

        public void setOptionTempStore(List<OptionDTO> optionTempStore) {
            this.optionTempStore = optionTempStore;
        }

        public Grid<WeaponDTO> getWeaponGrid() {
            return weaponGrid;
        }

        public void setWeaponGrid(Grid<WeaponDTO> weaponGrid) {
            this.weaponGrid = weaponGrid;
        }

        public ListStore<WeaponDTO> getWeaponStore() {
            return weaponStore;
        }

        public void setWeaponStore(ListStore<WeaponDTO> weaponStore) {
            this.weaponStore = weaponStore;
        }

        public ColumnModel<WeaponDTO> getWeaponCm() {
            return weaponCm;
        }

        public void setWeaponCm(ColumnModel<WeaponDTO> weaponCm) {
            this.weaponCm = weaponCm;
        }

        public Grid<OptionDTO> getOptionGrid() {
            return optionGrid;
        }

        public void setOptionGrid(Grid<OptionDTO> optionGrid) {
            this.optionGrid = optionGrid;
        }

        public Grid<OptionDTO> getOptionGridCommon() {
            return optionGridCommon;
        }

        public void setOptionGridCommon(Grid<OptionDTO> optionGridCommon) {
            this.optionGridCommon = optionGridCommon;
        }

        public ListStore<OptionDTO> getStore() {
            return optionStore;
        }

        public void setStore(ListStore<OptionDTO> store) {
            this.optionStore = store;
        }

        public ListStore<OptionDTO> getStoreCommon() {
            return optionStoreCommon;
        }

        public void setStoreCommon(ListStore<OptionDTO> storeCommon) {
            this.optionStoreCommon = storeCommon;
        }

        public List<OptionDTO> getTempStore() {
            return optionTempStore;
        }

        public void setTempStore(List<OptionDTO> tempStore) {
            this.optionTempStore = tempStore;
        }

        public ColumnModel<OptionDTO> getOptionCm() {
            return optionCm;
        }

        public void setOptionCm(ColumnModel<OptionDTO> optionCm) {
            this.optionCm = optionCm;
        }

        void updateWeaponGrid() {
            weaponGrid.reconfigure(weaponStore, weaponCm);
        }

        void updateItemGrid() {
            itemGrid.reconfigure(itemStore,itemCm);
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

        public void setIdFld(TextField idFld) {
            this.idFld = idFld;
        }

        public TextField getWsFld() {
            return wsFld;
        }

        public void setWsFld(TextField wsFld) {
            this.wsFld = wsFld;
        }

        public TextField getsFld() {
            return sFld;
        }

        public void setsFld(TextField sFld) {
            this.sFld = sFld;
        }

        public TextField getBsFld() {
            return bsFld;
        }

        public void setBsFld(TextField bsFld) {
            this.bsFld = bsFld;
        }

        public TextField gettFld() {
            return tFld;
        }

        public void settFld(TextField tFld) {
            this.tFld = tFld;
        }

        public TextField getwFld() {
            return wFld;
        }

        public void setwFld(TextField wFld) {
            this.wFld = wFld;
        }

        public TextField getiFld() {
            return iFld;
        }

        public void setiFld(TextField iFld) {
            this.iFld = iFld;
        }

        public TextField getaFld() {
            return aFld;
        }

        public void setaFld(TextField aFld) {
            this.aFld = aFld;
        }

        public TextField getLdFld() {
            return ldFld;
        }

        public void setLdFld(TextField ldFld) {
            this.ldFld = ldFld;
        }

        public TextField getSvFld() {
            return svFld;
        }

        public void setSvFld(TextField svFld) {
            this.svFld = svFld;
        }

        public TextField getCostFld() {
            return costFld;
        }

        public void setCostFld(TextField costFld) {
            this.costFld = costFld;
        }

        public ComboBox<UnitTypeDTO> getUnitTypeBox() {
            return unitTypeBox;
        }

        public void setUnitTypeBox(ComboBox<UnitTypeDTO> unitTypeBox) {
            this.unitTypeBox = unitTypeBox;
        }

        public ListStore<UnitTypeDTO> getUnitTypeStore() {
            return unitTypeStore;
        }

        public void setUnitTypeStore(ListStore<UnitTypeDTO> unitTypeStore) {
            this.unitTypeStore = unitTypeStore;
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
