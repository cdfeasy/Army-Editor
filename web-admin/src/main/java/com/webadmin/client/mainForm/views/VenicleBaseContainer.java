package com.webadmin.client.mainForm.views;

import com.armyeditor.dto.CodexDTO;
import com.armyeditor.dto.VenicleBaseDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;
import com.webadmin.client.mainForm.properties.CodexProperties;
import com.webadmin.client.mainForm.properties.UnitBaseProperties;
import com.webadmin.client.mainForm.properties.VenicleBaseProperties;
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
        ColumnConfig<VenicleBaseDTO, Integer> sizeColumn = new ColumnConfig<VenicleBaseDTO, Integer>(props.size(), 50, "size");
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
        l.add(sizeColumn);
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

        updateBtn = new TextButton("Update", new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                updateStore(codexBox.getValue().getId());
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

        gridContainer = new VerticalLayoutContainer();
        gridContainer.add(codexBox);
        gridContainer.add(venicleBaseGrid);
        gridContainer.add(updateBtn);
        gridContainer.add(delSelBtn);
        this.add(gridContainer);
        VerticalLayoutContainer vc = new VerticalLayoutContainer();
//        vc.add(unitBaseFields, new VerticalLayoutContainer.VerticalLayoutData(665,1200,new Margins(5,5,5,5)));
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
            }
        });

    }
}
