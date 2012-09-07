package com.webadmin.client.mainForm.properties;

import com.armyeditor.dto.WeaponDTO;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 06.09.12
 * Time: 14:39
 * To change this template use File | Settings | File Templates.
 */
public interface WeaponProperties extends PropertyAccess<WeaponDTO> {
    @Editor.Path("id")
    ModelKeyProvider<WeaponDTO> key();
    ValueProvider<WeaponDTO, Long> id();
    @Editor.Path("weapon.name")
    ValueProvider<WeaponDTO, String> weapon();
    ValueProvider<WeaponDTO, Integer> cost();
}
