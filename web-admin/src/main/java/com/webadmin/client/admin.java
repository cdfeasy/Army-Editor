package com.webadmin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;






/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class admin implements IsWidget,EntryPoint {

    interface MyUiBinder extends UiBinder<Widget, admin> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	

    /**
     * This is the entry point method.
     */
    public Widget asWidget() {
        return uiBinder.createAndBindUi(this);
    }

    public void onModuleLoad() {
        RootPanel.get().add(asWidget());
    }
}
