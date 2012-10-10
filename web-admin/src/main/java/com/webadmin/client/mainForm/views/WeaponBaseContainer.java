package com.webadmin.client.mainForm.views;

import com.armyeditor.dto.CodexDTO;
import com.armyeditor.dto.OptionDTO;
import com.armyeditor.dto.WeaponBaseDTO;
import com.armyeditor.dto.WeaponTypeDTO;
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
import com.webadmin.client.mainForm.properties.OptionProperties;
import com.webadmin.client.mainForm.properties.WeaponBaseProperties;
import com.webadmin.client.mainForm.properties.WeaponTypeProperties;
import com.webadmin.client.services.CommonService;
import com.webadmin.client.services.CommonServiceAsync;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 10.10.12
 * Time: 18:15
 * To change this template use File | Settings | File Templates.
 */
public class WeaponBaseContainer extends HorizontalLayoutContainer {
    private final CommonServiceAsync commonService = GWT.create(CommonService.class);
    VerticalLayoutContainer gridContainer;
    Grid<WeaponBaseDTO> mainGrid;
    ColumnModel<WeaponBaseDTO> cm;
    ListStore<WeaponBaseDTO> store;
    TextButton updateBtn = new TextButton("Update");
    TextButton delSelBtn = new TextButton("Delete Selection");
    ComboBox<CodexDTO> codexBox;
    final WeaponBaseFields weaponBaseFields = new WeaponBaseFields();

    public WeaponBaseContainer() {
        WeaponBaseProperties props = GWT.create(WeaponBaseProperties.class);
        IdentityValueProvider<WeaponBaseDTO> identity = new IdentityValueProvider<WeaponBaseDTO>();
        final CheckBoxSelectionModel<WeaponBaseDTO> sm = new CheckBoxSelectionModel<WeaponBaseDTO>(identity);
        ColumnConfig<WeaponBaseDTO, String> idColumn = new ColumnConfig<WeaponBaseDTO, String>(props.id(),50,"id");
        ColumnConfig<WeaponBaseDTO, String> nameColumn = new ColumnConfig<WeaponBaseDTO, String>(props.name(),60,"name");
        ColumnConfig<WeaponBaseDTO, String> descriptionColumn = new ColumnConfig<WeaponBaseDTO, String>(props.description(),100,"description");
        ColumnConfig<WeaponBaseDTO, String> apColumn = new ColumnConfig<WeaponBaseDTO, String>(props.ap(),50,"ap");
        ColumnConfig<WeaponBaseDTO, String> strColumn = new ColumnConfig<WeaponBaseDTO, String>(props.str(),50,"str");
        ColumnConfig<WeaponBaseDTO, String> rangeColumn = new ColumnConfig<WeaponBaseDTO, String>(props.range(),50,"range");
        ColumnConfig<WeaponBaseDTO, String> fireCountColumn = new ColumnConfig<WeaponBaseDTO, String>(props.fireCount(),50,"fireCount");
        List<ColumnConfig<WeaponBaseDTO,?>> l =new ArrayList<ColumnConfig<WeaponBaseDTO, ?>>();
        l.add(sm.getColumn());
        l.add(idColumn);
        l.add(nameColumn);
        l.add(descriptionColumn);
        l.add(rangeColumn);
        l.add(apColumn);
        l.add(strColumn);
        l.add(fireCountColumn);
        cm = new ColumnModel<WeaponBaseDTO>(l);
        sm.setSelectionMode(Style.SelectionMode.MULTI);
        store = new ListStore<WeaponBaseDTO>(props.key());
        mainGrid = new Grid<WeaponBaseDTO>(store, cm);
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
                commonService.delWeaponBase(selectList, new AsyncCallback<Void>() {
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
        vc.add(weaponBaseFields, new VerticalLayoutContainer.VerticalLayoutData(665,600,new Margins(5,5,5,5)));
        this.add(vc);
        initHandlers();
    }

    public void updateStore(String id){
        mainGrid.mask();
        commonService.getWeaponBaseById(id, new AsyncCallback<List<WeaponBaseDTO>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Запрос упал " + throwable.getMessage());
            }

            @Override
            public void onSuccess(List<WeaponBaseDTO> list) {
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
                WeaponBaseDTO a = (WeaponBaseDTO) event.getSource().getStore().get(row);
                weaponBaseFields.getIdFld().setText(a.getId());
                weaponBaseFields.getNameFld().setText(a.getName());
                weaponBaseFields.getDescriptionFld().setText(a.getDescription());
                weaponBaseFields.getApFld().setText(a.getAp());
                weaponBaseFields.getStrFld().setText(a.getStr());
                weaponBaseFields.getRangeFld().setText(a.getRange());
                weaponBaseFields.getFireCountFld().setText(a.getFireCount());
                weaponBaseFields.optionGrid.mask();
                commonService.getOptionsByWeaponBase(a.getId(), new AsyncCallback<List<OptionDTO>>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Запрос упал " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(List<OptionDTO> list) {
                        ListStore<OptionDTO> listStore = weaponBaseFields.getOptionStore();
                        listStore.clear();
                        ColumnModel<OptionDTO> cm = weaponBaseFields.getOptionCm();
                        listStore.addAll(list);
                        weaponBaseFields.getOptionGrid().reconfigure(listStore, cm);
                        ListStore<OptionDTO> listStore2 = weaponBaseFields.getOptionStoreCommon();
                        listStore2.clear();
                        listStore2.addAll(weaponBaseFields.getOptionTempStore());
                        weaponBaseFields.getOptionGridCommon().reconfigure(listStore2, cm);
                        weaponBaseFields.optionGrid.unmask();
                    }
                });
            }
        });

        weaponBaseFields.getSaveBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                WeaponBaseDTO a = new WeaponBaseDTO();
                a.setId(weaponBaseFields.getIdFld().getText());
                a.setName(weaponBaseFields.getNameFld().getText());
                a.setDescription(weaponBaseFields.getDescriptionFld().getText());
                a.setAp(weaponBaseFields.getApFld().getText());
                a.setStr(weaponBaseFields.getStrFld().getText());
                a.setRange(weaponBaseFields.getStrFld().getText());
                a.setFireCount(weaponBaseFields.getFireCountFld().getText());
                a.setType(weaponBaseFields.getWeaponTypeBox().getValue());
                ArrayList<OptionDTO> arrayList = new ArrayList<OptionDTO>(weaponBaseFields.getOptionGrid().getStore().getAll());
                a.setOptions(arrayList);
                commonService.changeWeaponBase(a, new AsyncCallback<Void>() {
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

        weaponBaseFields.getSaveNewBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                WeaponBaseDTO a = new WeaponBaseDTO();
                a.setId(weaponBaseFields.getIdFld().getText());
                a.setName(weaponBaseFields.getNameFld().getText());
                a.setDescription(weaponBaseFields.getDescriptionFld().getText());
                a.setAp(weaponBaseFields.getApFld().getText());
                a.setStr(weaponBaseFields.getStrFld().getText());
                a.setRange(weaponBaseFields.getStrFld().getText());
                a.setFireCount(weaponBaseFields.getFireCountFld().getText());
                a.setType(weaponBaseFields.getWeaponTypeBox().getValue());
                ArrayList<OptionDTO> arrayList = new ArrayList<OptionDTO>(weaponBaseFields.getOptionGrid().getStore().getAll());
                a.setOptions(arrayList);
                commonService.addWeaponBase(a, new AsyncCallback<Void>() {
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


    class WeaponBaseFields extends BorderLayoutContainer {
        TextField idFld;
        TextField nameFld;
        TextField descriptionFld;
        TextField rangeFld;
        TextField apFld;
        TextField strFld;
        TextField fireCountFld;

        ComboBox<WeaponTypeDTO> weaponTypeBox;
        ListStore<WeaponTypeDTO> weaponTypeStore;

        Grid<OptionDTO> optionGrid;
        Grid<OptionDTO> optionGridCommon;
        private ListStore<OptionDTO> optionStore;
        private ListStore<OptionDTO> optionStoreCommon;
        private List<OptionDTO> optionTempStore;
        private ColumnModel<OptionDTO> optionCm;

        TextButton saveBtn = new TextButton("Save");
        TextButton saveNewBtn = new TextButton("Save as new item");

        WeaponBaseFields() {
            VerticalLayoutContainer vc = new VerticalLayoutContainer();
            idFld = new TextField();
            vc.add(new FieldLabel(idFld,"ID"));
            nameFld = new TextField();
            vc.add(new FieldLabel(nameFld,"Name"));
            descriptionFld = new TextField();
            vc.add(new FieldLabel(descriptionFld,"Description"));
            rangeFld = new TextField();
            vc.add(new FieldLabel(rangeFld,"Range"));
            apFld = new TextField();
            vc.add(new FieldLabel(apFld,"AP"));
            strFld = new TextField();
            vc.add(new FieldLabel(strFld,"STR"));
            fireCountFld = new TextField();
            vc.add(new FieldLabel(fireCountFld,"Fire count"));

            WeaponTypeProperties weaponTypeProps = GWT.create(WeaponTypeProperties.class);
            weaponTypeStore = new ListStore<WeaponTypeDTO>(weaponTypeProps.key());
            commonService.getWeaponType(new AsyncCallback<List<WeaponTypeDTO>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println("Запрос упал " + throwable.getMessage());
                }

                @Override
                public void onSuccess(List<WeaponTypeDTO> weaponTypeDTOs) {
                    weaponTypeStore.addAll(weaponTypeDTOs);
                }
            });
            weaponTypeBox = new ComboBox<WeaponTypeDTO>(weaponTypeStore, weaponTypeProps.nameLabel());
            weaponTypeBox.setTypeAhead(true);
            vc.add(new FieldLabel(weaponTypeBox, "Unit Type"));
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

        public TextField getRangeFld() {
            return rangeFld;
        }

        public TextField getApFld() {
            return apFld;
        }

        public TextField getStrFld() {
            return strFld;
        }

        public TextField getFireCountFld() {
            return fireCountFld;
        }

        public ComboBox<WeaponTypeDTO> getWeaponTypeBox() {
            return weaponTypeBox;
        }

        public ListStore<WeaponTypeDTO> getWeaponTypeStore() {
            return weaponTypeStore;
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
