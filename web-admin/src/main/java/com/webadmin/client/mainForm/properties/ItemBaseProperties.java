package com.webadmin.client.mainForm.properties;

import com.armyeditor.dto.ItemBaseDTO;
import com.armyeditor.dto.ItemDTO;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 12.09.12
 * Time: 17:41
 * To change this template use File | Settings | File Templates.
 */
public interface ItemBaseProperties extends PropertyAccess<ItemBaseDTO> {
    @Editor.Path("id")
    ModelKeyProvider<ItemBaseDTO> key();
    ValueProvider<ItemBaseDTO, String> id();
    ValueProvider<ItemBaseDTO, String> name();
    ValueProvider<ItemBaseDTO, String> description();
    @Editor.Path("name")
    LabelProvider<ItemBaseDTO> nameLabel();
}
