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
import com.sencha.gxt.widget.core.client.info.Info;
import com.webadmin.client.mainForm.properties.*;
import com.webadmin.client.services.CommonService;
import com.webadmin.client.services.CommonServiceAsync;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 02.10.12
 * Time: 19:59
 * To change this template use File | Settings | File Templates.
 */
public class VenicleBaseContainer extends HorizontalLayoutContainer {
    private final CommonServiceAsync commonService = GWT.create(CommonService.class);
    VerticalLayoutContainer gridContainer;
    Grid<VenicleBaseDTO> venicleBaseGrid;
    ColumnModel<VenicleBaseDTO> cm;
    ListStore<VenicleBaseDTO> store;
    TextButton updateBtn = new TextButton("Update");
    TextButton delSelBtn = new TextButton("Delete Selection");
    ComboBox<CodexDTO> codexBox;
    final VenicleBaseFields venicleBaseFields = new VenicleBaseFields();

    public VenicleBaseContainer() {
        VenicleBaseProperties props = GWT.create(VenicleBaseProperties.class);
        IdentityValueProvider<VenicleBaseDTO> identity = new IdentityValueProvider<VenicleBaseDTO>();
        final CheckBoxSelectionModel<VenicleBaseDTO> sm = new CheckBoxSelectionModel<VenicleBaseDTO>(identity);
        ColumnConfig<VenicleBaseDTO, String> idColumn = new ColumnConfig<VenicleBaseDTO, String>(props.id(),50,"id");
        ColumnConfig<VenicleBaseDTO, Integer> wsColumn = new ColumnConfig<VenicleBaseDTO, Integer>(props.ws(), 50, "ws");
        ColumnConfig<VenicleBaseDTO, Integer> bsColumn = new ColumnConfig<VenicleBaseDTO, Integer>(props.bs(), 50, "bs");
        ColumnConfig<VenicleBaseDTO, Integer> sColumn = new ColumnConfig<VenicleBaseDTO, Integer>(props.s(), 50, "s");
        ColumnConfig<VenicleBaseDTO, Integer> wColumn = new ColumnConfig<VenicleBaseDTO, Integer>(props.w(), 50, "w");
        ColumnConfig<VenicleBaseDTO, Integer> iColumn = new ColumnConfig<VenicleBaseDTO, Integer>(props.i(), 50, "i");
        ColumnConfig<VenicleBaseDTO, Integer> aColumn = new ColumnConfig<VenicleBaseDTO, Integer>(props.a(), 50, "a");
        ColumnConfig<VenicleBaseDTO, Integer> frontColumn = new ColumnConfig<VenicleBaseDTO, Integer>(props.front(), 50, "front");
        ColumnConfig<VenicleBaseDTO, Integer> sideColumn = new ColumnConfig<VenicleBaseDTO, Integer>(props.side(), 50, "size");
        ColumnConfig<VenicleBaseDTO, Integer> rearColumn = new ColumnConfig<VenicleBaseDTO, Integer>(props.rear(), 50, "rear");
        ColumnConfig<VenicleBaseDTO, Integer> costColumn = new ColumnConfig<VenicleBaseDTO, Integer>(props.cost(), 50, "cost");
        List<ColumnConfig<VenicleBaseDTO,?>> l =new ArrayList<ColumnConfig<VenicleBaseDTO, ?>>();
        l.add(sm.getColumn());
        l.add(idColumn);
        l.add(wsColumn);
        l.add(bsColumn);
        l.add(sColumn);
        l.add(wColumn);
        l.add(iColumn);
        l.add(aColumn);
        l.add(frontColumn);
        l.add(sideColumn);
        l.add(rearColumn);
        l.add(costColumn);
        cm = new ColumnModel<VenicleBaseDTO>(l);
        sm.setSelectionMode(Style.SelectionMode.MULTI);
        store = new ListStore<VenicleBaseDTO>(props.key());
        venicleBaseGrid = new Grid<VenicleBaseDTO>(store, cm);
        venicleBaseGrid.setSelectionModel(sm);

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
                List selectList = venicleBaseGrid.getSelectionModel().getSelectedItems();
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

        initHandlers();
        gridContainer = new VerticalLayoutContainer();
        gridContainer.add(new FieldLabel(codexBox, "Select codex"));
        gridContainer.add(venicleBaseGrid);
        gridContainer.add(updateBtn);
        gridContainer.add(delSelBtn);
        this.add(gridContainer);
        VerticalLayoutContainer vc = new VerticalLayoutContainer();
        vc.add(venicleBaseFields, new VerticalLayoutContainer.VerticalLayoutData(665,1200,new Margins(5,5,5,5)));
        this.add(vc);
    }

    public void updateStore(String id){
        venicleBaseGrid.mask();
        commonService.getVenicleBase(id, new AsyncCallback<List<VenicleBaseDTO>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Запрос упал " + throwable.getMessage());
            }

            @Override
            public void onSuccess(List<VenicleBaseDTO> list) {
                store.clear();
                store.addAll(list);
                venicleBaseGrid.reconfigure(store, cm);
                venicleBaseGrid.unmask();
            }
        });
    }

    void initHandlers() {
        venicleBaseGrid.addRowClickHandler(new RowClickEvent.RowClickHandler() {
            @Override
            public void onRowClick(RowClickEvent event) {
                int row = event.getRowIndex();
                VenicleBaseDTO a =(VenicleBaseDTO) event.getSource().getStore().get(row);
                venicleBaseFields.getIdFld().setText(a.getId());
                venicleBaseFields.getWsFld().setText(Integer.toString(a.getWs()));
                venicleBaseFields.getBsFld().setText(Integer.toString(a.getBs()));
                venicleBaseFields.getsFld().setText(Integer.toString(a.getS()));
                venicleBaseFields.getFrontFld().setText(Integer.toString(a.getFront()));
                venicleBaseFields.getwFld().setText(Integer.toString(a.getW()));
                venicleBaseFields.getiFld().setText(Integer.toString(a.getI()));
                venicleBaseFields.getaFld().setText(Integer.toString(a.getA()));
                venicleBaseFields.getRearFld().setText(Integer.toString(a.getRear()));
                venicleBaseFields.getSideFld().setText(Integer.toString(a.getSide()));
                venicleBaseFields.getCostFld().setText(Integer.toString(a.getCost()));
                venicleBaseFields.getUnitTypeBox().setValue(a.getUnitType());
                venicleBaseFields.optionGrid.mask();
                venicleBaseFields.weaponGrid.mask();
                venicleBaseFields.itemGrid.mask();
                commonService.getUnitById(a.getId(), new AsyncCallback<UnitBaseDTO>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Запрос упал " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(UnitBaseDTO unitBaseDTO) {
                        ListStore<OptionDTO> listStore = venicleBaseFields.getOptionStore();
                        listStore.clear();
                        ColumnModel<OptionDTO> cm = venicleBaseFields.getOptionCm();
                        listStore.addAll(unitBaseDTO.getOptions());
                        venicleBaseFields.getOptionGrid().reconfigure(listStore,cm);
                        ListStore<OptionDTO> listStore2 = venicleBaseFields.getOptionStoreCommon();
                        listStore2.clear();
                        listStore2.addAll(venicleBaseFields.getOptionTempStore());
                        venicleBaseFields.getOptionGridCommon().reconfigure(listStore2, cm);

                        ListStore<WeaponDTO> weaponStore = venicleBaseFields.getWeaponStore();
                        weaponStore.clear();
                        ColumnModel<WeaponDTO> weaponCm = venicleBaseFields.getWeaponCm();
                        weaponStore.addAll(unitBaseDTO.getWeapons());
                        venicleBaseFields.getWeaponGrid().reconfigure(weaponStore,weaponCm);

                        ListStore<ItemDTO> itemStore = venicleBaseFields.getItemStore();
                        itemStore.clear();
                        ColumnModel<ItemDTO> itemCm = venicleBaseFields.getItemCm();
                        itemStore.addAll(unitBaseDTO.getItems());
                        venicleBaseFields.getItemGrid().reconfigure(itemStore,itemCm);
                        venicleBaseFields.optionGrid.unmask();
                        venicleBaseFields.weaponGrid.unmask();
                        venicleBaseFields.itemGrid.unmask();
                    }

                });
            }
        });
        venicleBaseFields.getSaveBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                VenicleBaseDTO a = new VenicleBaseDTO();
                a.setId(venicleBaseFields.getIdFld().getText());
                a.setWs(Integer.parseInt(venicleBaseFields.getWsFld().getText()));
                a.setBs(Integer.parseInt(venicleBaseFields.getBsFld().getText()));
                a.setS(Integer.parseInt(venicleBaseFields.getsFld().getText()));
                a.setFront(Integer.parseInt(venicleBaseFields.getFrontFld().getText()));
                a.setW(Integer.parseInt(venicleBaseFields.getwFld().getText()));
                a.setI(Integer.parseInt(venicleBaseFields.getiFld().getText()));
                a.setA(Integer.parseInt(venicleBaseFields.getaFld().getText()));
                a.setSide(Integer.parseInt(venicleBaseFields.getSideFld().getText()));
                a.setRear(Integer.parseInt(venicleBaseFields.getRearFld().getText()));
                a.setCost(Integer.parseInt(venicleBaseFields.getCostFld().getText()));
                a.setUnitType(venicleBaseFields.getUnitTypeBox().getValue());
                a.setCodex(codexBox.getValue());
                ArrayList<OptionDTO> arrayList = new ArrayList<OptionDTO>(venicleBaseFields.getOptionGrid().getStore().getAll());
                a.setOptions(arrayList);
                venicleBaseFields.getWeaponGrid().getStore().commitChanges();
                ArrayList<WeaponDTO> weaponList = new ArrayList<WeaponDTO>(venicleBaseFields.getWeaponGrid().getStore().getAll());
                a.setWeapons(weaponList);
                venicleBaseFields.getItemGrid().getStore().commitChanges();
                ArrayList<ItemDTO> itemList  = new ArrayList<ItemDTO>(venicleBaseFields.getItemGrid().getStore().getAll());
                a.setItems(itemList);

                commonService.changeVenicleBase(a, new AsyncCallback<Void>() {
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
        venicleBaseFields.getSaveNewBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                VenicleBaseDTO a = new VenicleBaseDTO();
                a.setId(venicleBaseFields.getIdFld().getText());
                a.setWs(Integer.parseInt(venicleBaseFields.getWsFld().getText()));
                a.setBs(Integer.parseInt(venicleBaseFields.getBsFld().getText()));
                a.setS(Integer.parseInt(venicleBaseFields.getsFld().getText()));
                a.setFront(Integer.parseInt(venicleBaseFields.getFrontFld().getText()));
                a.setW(Integer.parseInt(venicleBaseFields.getwFld().getText()));
                a.setI(Integer.parseInt(venicleBaseFields.getiFld().getText()));
                a.setA(Integer.parseInt(venicleBaseFields.getaFld().getText()));
                a.setSide(Integer.parseInt(venicleBaseFields.getSideFld().getText()));
                a.setRear(Integer.parseInt(venicleBaseFields.getRearFld().getText()));
                a.setCost(Integer.parseInt(venicleBaseFields.getCostFld().getText()));
                a.setUnitType(venicleBaseFields.getUnitTypeBox().getValue());
                a.setCodex(codexBox.getValue());
                ArrayList<OptionDTO> arrayList = new ArrayList<OptionDTO>(venicleBaseFields.getOptionGrid().getStore().getAll());
                a.setOptions(arrayList);
                venicleBaseFields.getWeaponGrid().getStore().commitChanges();
                ArrayList<WeaponDTO> weaponList = new ArrayList<WeaponDTO>(venicleBaseFields.getWeaponGrid().getStore().getAll());
                a.setWeapons(weaponList);
                venicleBaseFields.getItemGrid().getStore().commitChanges();
                ArrayList<ItemDTO> itemList  = new ArrayList<ItemDTO>(venicleBaseFields.getItemGrid().getStore().getAll());
                a.setItems(itemList);

                commonService.addVenicleBase(a, new AsyncCallback<Void>() {
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


     class VenicleBaseFields extends BorderLayoutContainer {
        TextField idFld;
        TextField wsFld;
        TextField bsFld;
        TextField sFld;
        TextField wFld;
        TextField iFld;
        TextField aFld;
        TextField frontFld;
        TextField sideFld;
        TextField rearFld;
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

        public VenicleBaseFields() {
            VerticalLayoutContainer vc = new VerticalLayoutContainer();
            idFld = new TextField();
            vc.add(new FieldLabel(idFld,"ID"));
            wsFld = new TextField();
            vc.add(new FieldLabel(wsFld,"WS"));
            bsFld = new TextField();
            vc.add(new FieldLabel(bsFld,"BS"));
            sFld = new TextField();
            vc.add(new FieldLabel(sFld,"S"));
            wFld = new TextField();
            vc.add(new FieldLabel(wFld,"W"));
            aFld = new TextField();
            vc.add(new FieldLabel(aFld,"A"));
            iFld = new TextField();
            vc.add(new FieldLabel(iFld,"I"));
            frontFld = new TextField();
            vc.add(new FieldLabel(frontFld,"Front"));
            sideFld = new TextField();
            vc.add(new FieldLabel(sideFld,"Side"));
            rearFld = new TextField();
            vc.add(new FieldLabel(rearFld,"Rear"));
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
            vc.add(new FieldLabel(unitTypeBox, "Unit Type"));
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

            con.add(verticalLayoutContainer, new HorizontalLayoutContainer.HorizontalLayoutData(.5, 1, new Margins(5, 5, 5, 0)));
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

        void updateWeaponGrid() {
            weaponGrid.reconfigure(weaponStore, weaponCm);
        }

        void updateItemGrid() {
            itemGrid.reconfigure(itemStore,itemCm);
        }

        public TextField getIdFld() {
            return idFld;
        }

        public TextField getWsFld() {
            return wsFld;
        }

        public TextField getBsFld() {
            return bsFld;
        }

        public TextField getsFld() {
            return sFld;
        }

        public TextField getwFld() {
            return wFld;
        }

        public TextField getiFld() {
            return iFld;
        }

        public TextField getaFld() {
            return aFld;
        }

        public TextField getFrontFld() {
            return frontFld;
        }

        public TextField getSideFld() {
            return sideFld;
        }

        public TextField getRearFld() {
            return rearFld;
        }

        public TextField getCostFld() {
            return costFld;
        }

        public ComboBox<UnitTypeDTO> getUnitTypeBox() {
            return unitTypeBox;
        }

        public ListStore<UnitTypeDTO> getUnitTypeStore() {
            return unitTypeStore;
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

        public Grid<WeaponDTO> getWeaponGrid() {
            return weaponGrid;
        }

        public ListStore<WeaponDTO> getWeaponStore() {
            return weaponStore;
        }

        public ColumnModel<WeaponDTO> getWeaponCm() {
            return weaponCm;
        }

        public ComboBox<WeaponBaseDTO> getWeaponBaseBox() {
            return weaponBaseBox;
        }

        public ListStore<WeaponBaseDTO> getWeaponBaseStore() {
            return weaponBaseStore;
        }

        public TextButton getAddWeaponBtn() {
            return addWeaponBtn;
        }

        public Grid<ItemDTO> getItemGrid() {
            return itemGrid;
        }

        public ListStore<ItemDTO> getItemStore() {
            return itemStore;
        }

        public ColumnModel<ItemDTO> getItemCm() {
            return itemCm;
        }

        public ComboBox<ItemBaseDTO> getItemBaseBox() {
            return itemBaseBox;
        }

        public ListStore<ItemBaseDTO> getItemBaseStore() {
            return itemBaseStore;
        }

        public TextButton getAddItemBtn() {
            return addItemBtn;
        }

        public TextButton getSaveBtn() {
            return saveBtn;
        }

        public TextButton getSaveNewBtn() {
            return saveNewBtn;
        }
    }
}
