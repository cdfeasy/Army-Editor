/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.fractioneditor.client.base.pages;


import com.armyeditor.entrys.WeaponType;
import com.armyeditor.fractioneditor.client.services.IBaseService;
import com.armyeditor.fractioneditor.client.services.IBaseServiceAsync;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import java.util.List;

/**
 *
 * @author Dmitry
 */
public class EditArmorTypes {
    public static FlowPanel getPage(IBaseServiceAsync service)
    {
        FlowPanel Panel = new FlowPanel();
        final Label lbl=new  Label();
        service.getWeaponTypes(new AsyncCallback<List<WeaponType>>() {
                                                    public void onFailure(Throwable caught) {
                                                        System.out.println("----------------------------");
                                                        System.out.println("----------------------------");
                                                        System.out.println("----------------------------");
                                                        caught.printStackTrace();
                                                    }

                                                    public void onSuccess(List<WeaponType> result) {
                                                        System.out.println("----------------------------");
                                                        System.out.println("---------11111111----------");
                                                        System.out.println("----------------------------");
                                                        lbl.setText(Integer.toString(result.size()));
                                                    }

						});
        Panel.add(lbl);
        return Panel;
    }
}
