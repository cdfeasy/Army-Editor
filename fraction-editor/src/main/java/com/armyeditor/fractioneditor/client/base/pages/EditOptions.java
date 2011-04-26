/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.fractioneditor.client.base.pages;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 *
 * @author Dmitry
 */
public class EditOptions {
    public static FlowPanel getPage()
    {
        FlowPanel Panel = new FlowPanel();
        Panel.add(new Label("a"));
        return Panel;
    }
}
