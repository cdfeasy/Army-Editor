package com.webadmin.client.mainForm.views;

import com.armyeditor.dto.FractionDTO;
import com.armyeditor.dto.UnitBaseDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
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
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.webadmin.client.mainForm.properties.FractionProperties;
import com.webadmin.client.mainForm.properties.UnitBaseProperties;
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
    ComboBox<FractionDTO> fractionBox;
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
        l.add(ldColumn);
        l.add(costColumn);
        cm = new ColumnModel<UnitBaseDTO>(l);
        sm.setSelectionMode(Style.SelectionMode.MULTI);
        store = new ListStore<UnitBaseDTO>(props.key());
        unitBaseGrid = new Grid<UnitBaseDTO>(store, cm);
        unitBaseGrid.setSelectionModel(sm);

        FractionProperties fractionProperties = GWT.create(FractionProperties.class);
        final ListStore<FractionDTO> fractionListStore = new ListStore<FractionDTO>(fractionProperties.key());
        commonService.getFractions(new AsyncCallback<List<FractionDTO>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Запрос упал " + throwable.getMessage());
            }

            @Override
            public void onSuccess(List<FractionDTO> fractionDTOs) {
                fractionListStore.addAll(fractionDTOs);
            }
        });
        fractionBox = new ComboBox<FractionDTO>(fractionListStore, fractionProperties.nameLabel());

        updateBtn = new TextButton("Update", new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                updateStore(fractionBox.getCurrentValue().getId());
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
                    }

                    @Override
                    public void onSuccess(Void aVoid) {
                        updateStore(fractionBox.getCurrentValue().getId()); //todo
                    }
                });
            }
        });

        fractionBox.addSelectionHandler(new SelectionHandler<FractionDTO>() {
            @Override
            public void onSelection(SelectionEvent<FractionDTO> fractionDTOSelectionEvent) {
                updateStore(fractionDTOSelectionEvent.getSelectedItem().getId());
            }
        });

        gridContainer = new VerticalLayoutContainer();
        gridContainer.add(fractionBox);
        gridContainer.add(unitBaseGrid);
        gridContainer.add(updateBtn);
        gridContainer.add(delSelBtn);
        this.add(gridContainer);
        VerticalLayoutContainer vc = new VerticalLayoutContainer();
        vc.add(unitBaseFields, new VerticalLayoutContainer.VerticalLayoutData(350,400,new Margins(5,5,5,5)));
        this.add(vc);
        initHandlers();
    }

    public void updateStore(String id){
        commonService.getUnitBase(id, new AsyncCallback<List<UnitBaseDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                System.out.println("Запрос упал " + caught.getMessage());
            }

            @Override
            public void onSuccess(List<UnitBaseDTO> result) {
                store.clear();
                for (UnitBaseDTO a : result) {
                    store.add(a);
                }
                unitBaseGrid.reconfigure(store, cm);
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
                unitBaseFields.getSvFld().setText(Integer.toString(a.getSv()));
                unitBaseFields.getCostFld().setText(Integer.toString(a.getCost()));
                unitBaseFields.getUnitTypeFld().setText(a.getUnitType().getName());
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
                a.setSv(Integer.parseInt(unitBaseFields.getSvFld().getText()));
                a.setCost(Integer.parseInt(unitBaseFields.getCostFld().getText()));

                commonService.changeUnitBase(a, new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Запрос упал " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Void aVoid) {
                        updateStore(fractionBox.getCurrentValue().getId());
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
                a.setSv(Integer.parseInt(unitBaseFields.getSvFld().getText()));
                a.setCost(Integer.parseInt(unitBaseFields.getCostFld().getText()));
                commonService.addUnitBase(a, new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Запрос упал " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Void aVoid) {
                        updateStore(fractionBox.getCurrentValue().getId());
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
        TextField unitTypeFld;
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
            unitTypeFld = new TextField();
            vc.add(new FieldLabel(unitTypeFld,"Unit Base"));
            vc.add(saveBtn);
            vc.add(saveNewBtn);

            this.setBorders(true);
            this.setHeight(700);
            this.setWidth(350);
            this.add(vc,new VerticalLayoutContainer.VerticalLayoutData(350,700,new Margins(5,5,5,5)));
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

        public TextField getUnitTypeFld() {
            return unitTypeFld;
        }

        public void setUnitTypeFld(TextField unitTypeFld) {
            this.unitTypeFld = unitTypeFld;
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
