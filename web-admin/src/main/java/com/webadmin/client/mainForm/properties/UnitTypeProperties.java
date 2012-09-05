package com.webadmin.client.mainForm.properties;

import com.armyeditor.dto.UnitTypeDTO;
import com.armyeditor.entrys.UnitType;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 02.08.12
 * Time: 19:36
 * To change this template use File | Settings | File Templates.
 */
public interface UnitTypeProperties extends PropertyAccess<UnitTypeDTO> {
    @Editor.Path("id")
    ModelKeyProvider<UnitTypeDTO> key();
    ValueProvider<UnitTypeDTO, String> id();
    ValueProvider<UnitTypeDTO, String> name();
    @Editor.Path("name")
    LabelProvider<UnitTypeDTO> nameLabel();
}
