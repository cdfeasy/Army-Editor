package com.webadmin.client.mainForm.weaponTypeTab;

import com.armyeditor.dto.WeaponTypeDTO;
import com.armyeditor.entrys.WeaponType;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import static com.google.gwt.editor.client.Editor.*;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 02.08.12
 * Time: 20:05
 * To change this template use File | Settings | File Templates.
 */
public interface WeaponTypeProperties extends PropertyAccess<WeaponTypeDTO> {
    @Path("id")
    ModelKeyProvider<WeaponTypeDTO> key();
    ValueProvider<WeaponTypeDTO, String> id();
    ValueProvider<WeaponTypeDTO, String> name();
    ValueProvider<WeaponTypeDTO, String> description();
}
