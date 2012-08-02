package com.webadmin.client;

import com.armyeditor.entrys.Armor;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
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
import com.webadmin.client.mainForm.*;
import com.webadmin.client.services.CommonService;
import com.webadmin.client.services.CommonServiceAsync;
import java.util.ArrayList;

import java.util.List;

/**
 * Entry point classes define
 * <code>onModuleLoad()</code>.
 */
public class admin implements EntryPoint {

	interface MyUiBinder extends UiBinder<Widget, admin> {
	}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	private final CommonServiceAsync commonService = GWT.create(CommonService.class);

    @UiField
    HorizontalLayoutContainer armorGridContainer;
    @UiField
    HorizontalLayoutContainer attackTyteGridContainer;
    @UiField
    HorizontalLayoutContainer specialRuleGridContainer;

    public Widget asWidget() {
        Widget d=uiBinder.createAndBindUi(this);
//        configGrid();
//        initHandlers();
//        fldsCon.add(fields);
        armorGridContainer.add(new ArmorContainer());
        attackTyteGridContainer.add(new AttackTypeContainer());
        specialRuleGridContainer.add(new SpecialRuleContainer());
        return d;
	}

    /**
     * This is the entry point method.
     */
	public void onModuleLoad() {
        RootPanel.get().add(asWidget());
	}

	@UiHandler(value = {"folder"})
	void onSelection(SelectionEvent<Widget> event) {
		TabPanel panel = (TabPanel) event.getSource();
		Widget w = event.getSelectedItem();
		TabItemConfig config = panel.getConfig(w);
		Info.display("Message", "'" + config.getText() + "' Selected");
	}
}
