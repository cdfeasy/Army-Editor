package com.webadmin.client.mainForm;

import com.armyeditor.entrys.WeaponType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.webadmin.client.services.CommonService;
import com.webadmin.client.services.CommonServiceAsync;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 02.08.12
 * Time: 20:17
 * To change this template use File | Settings | File Templates.
 */
public class WeaponTypeContainer extends HorizontalLayoutContainer {
    private final CommonServiceAsync commonService = GWT.create(CommonService.class);
    VerticalLayoutContainer gridContainer;
    Grid<WeaponType> specialRuleGrid;
    ColumnModel<WeaponType> cm;
    ListStore<WeaponType> store;
    TextButton updateBtn = new TextButton("Update");
    TextButton delSelBtn = new TextButton("Delete Selection");
    final WeaponTypeFields specialRuleFields = new WeaponTypeFields();

    public WeaponTypeContainer() {

    }

    /*public void updateStore(){
        commonService.getSpecialRule(new AsyncCallback<List<WeaponType>>() {

            @Override
            public void onFailure(Throwable caught) {
                System.out.println("Запрос упал " + caught.getMessage());
            }

            @Override
            public void onSuccess(List<WeaponType> result) {
                store.clear();
                for (WeaponType a : result) {
                    store.add(a);
                }
                specialRuleGrid.reconfigure(store, cm);
            }
        });
    }*/
}
