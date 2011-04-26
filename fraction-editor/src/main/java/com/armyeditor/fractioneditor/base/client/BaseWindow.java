/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.fractioneditor.base.client;

import com.armyeditor.entrys.WeaponType;
import com.armyeditor.fractioneditor.client.base.pages.EditArmorTypes;
import com.armyeditor.fractioneditor.client.base.pages.EditOptions;
import com.armyeditor.fractioneditor.client.services.IBaseService;
import com.armyeditor.fractioneditor.client.services.IBaseServiceAsync;
import com.armyeditor.fractioneditor.client.services.IService;
import com.armyeditor.fractioneditor.client.services.IServiceAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import java.util.List;
/**
 *
 * @author Dmitry
 */
public class BaseWindow implements EntryPoint {
    /**
     * Creates a new instance of MainEntryPoint
     */

      private final IBaseServiceAsync Service = GWT.create(IBaseService.class);

     final FlowPanel _optionsPanel;
     FlowPanel _armorTypesPanel;
    public BaseWindow() {
        _optionsPanel =EditOptions.getPage();
        _armorTypesPanel=EditArmorTypes.getPage(Service);
    }


    final TabPanel mainPanel= new TabPanel();
    final FlowPanel attackTypePanel = new FlowPanel();
    final FlowPanel fractionPanel = new FlowPanel();
    final Label lbl=new Label();

//    private final IServiceAsync greetingService = GWT.create(IService.class);

  ClickHandler testbtn=new ClickHandler() {
            public void onClick(ClickEvent event) {
                    Service.getWeaponTypes(new AsyncCallback<List<WeaponType>>() {
                                                    public void onFailure(Throwable caught) {
                                                        System.out.println("----------------------------");
                                                        System.out.println("----------------------------");
                                                        System.out.println("----------------------------");
                                                        caught.printStackTrace();
                                                         lbl.setText(caught.getMessage());
                                                    }

                                                    public void onSuccess(List<WeaponType> result) {
                                                        System.out.println("----------------------------");
                                                        System.out.println("---------11111111----------");
                                                        System.out.println("----------------------------");
                                                        lbl.setText(Integer.toString(result.size()));
                                                    }

						});
              }
        };
    public void initStartup()
    {
        attackTypePanel.add(new Label("a"));
        fractionPanel.add(new Label("b"));
        mainPanel.add(attackTypePanel,"one");
        mainPanel.add(fractionPanel,"two");
        mainPanel.add(_optionsPanel,"three");
        mainPanel.add(_armorTypesPanel,"four");
        mainPanel.selectTab(0);
    //    mainPanel.setSize("500px", "250px");
     //   mainPanel.addStyleName("table-center");
        RootPanel.get().add(mainPanel);
         RootPanel.get().add(lbl,200,200);
         Button button1 = new Button("Click me1");
          button1.addClickHandler(testbtn);
           RootPanel.get().add(button1);
    }


    public void onModuleLoad() {
        initStartup();
    }


}
