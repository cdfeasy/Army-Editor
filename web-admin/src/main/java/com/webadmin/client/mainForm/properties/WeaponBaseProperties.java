package com.webadmin.client.mainForm.properties;

import com.armyeditor.dto.WeaponBaseDTO;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 11.09.12
 * Time: 18:56
 * To change this template use File | Settings | File Templates.
 */
public interface WeaponBaseProperties extends PropertyAccess<WeaponBaseDTO> {
    @Editor.Path("id")
    ModelKeyProvider<WeaponBaseDTO> key();
    ValueProvider<WeaponBaseDTO, String> id();
    ValueProvider<WeaponBaseDTO, String> name();
    ValueProvider<WeaponBaseDTO, String> description();
    ValueProvider<WeaponBaseDTO, String> range();
    ValueProvider<WeaponBaseDTO, String> ap();
    ValueProvider<WeaponBaseDTO, String> str();
    ValueProvider<WeaponBaseDTO, String> fireCount();
    @Editor.Path("name")
    LabelProvider<WeaponBaseDTO> nameLabel();
}
