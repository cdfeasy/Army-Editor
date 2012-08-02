package com.webadmin.client.mainForm;

import com.armyeditor.entrys.SpecialRule;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.RowClickEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;
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
    Grid<SpecialRule> specialRuleGrid;
    ColumnModel<SpecialRule> cm;
    ListStore<SpecialRule> store;
    TextButton updateBtn = new TextButton("Update");
    TextButton delSelBtn = new TextButton("Delete Selection");
    final SpecialRuleFields specialRuleFields = new SpecialRuleFields();

    public SpecialRuleContainer() {
        SpecialRuleProperties props = GWT.create(SpecialRuleProperties.class);
        IdentityValueProvider<SpecialRule> identity = new IdentityValueProvider<SpecialRule>();
        final CheckBoxSelectionModel<SpecialRule> sm = new CheckBoxSelectionModel<SpecialRule>(identity);
        ColumnConfig<SpecialRule, String> idColumn = new ColumnConfig<SpecialRule, String>(props.id(), 50, "id");
        ColumnConfig<SpecialRule, String> nameColumn = new ColumnConfig<SpecialRule, String>(props.name(), 150, "name");
        ColumnConfig<SpecialRule, String> descripColumn = new ColumnConfig<SpecialRule, String>(props.description(), 150, "description");

        List<ColumnConfig<SpecialRule, ?>> l = new ArrayList<ColumnConfig<SpecialRule, ?>>();
        l.add(sm.getColumn());
        l.add(idColumn);
        l.add(nameColumn);
        l.add(descripColumn);
        cm = new ColumnModel<SpecialRule>(l);
        sm.setSelectionMode(Style.SelectionMode.MULTI);
        store = new ListStore<SpecialRule>(props.key());
        specialRuleGrid= new Grid<SpecialRule>(store, cm);
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
        commonService.getSpecialRule(new AsyncCallback<List<SpecialRule>>() {

            @Override
            public void onFailure(Throwable caught) {
                System.out.println("Запрос упал " + caught.getMessage());
            }

            @Override
            public void onSuccess(List<SpecialRule> result) {
                store.clear();
                for (SpecialRule a : result) {
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
                SpecialRule a =(SpecialRule) event.getSource().getStore().get(row);
                specialRuleFields.getIdFld().setText(a.getId());
                specialRuleFields.getNameFld().setText(a.getName());
                specialRuleFields.getDescripFld().setText(a.getDescription());
            }
        });
        specialRuleFields.getSaveBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                SpecialRule a = new SpecialRule();
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
                SpecialRule a = new SpecialRule();
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

}
