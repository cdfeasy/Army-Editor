package com.webadmin.client.mainForm.unitTypeTab;

import com.armyeditor.entrys.UnitType;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 02.08.12
 * Time: 19:36
 * To change this template use File | Settings | File Templates.
 */
public interface UnitTypeProperties extends PropertyAccess<UnitType> {
    @Editor.Path("id")
    ModelKeyProvider<UnitType> key();
    ValueProvider<UnitType, String> id();
    ValueProvider<UnitType, String> name();
}
