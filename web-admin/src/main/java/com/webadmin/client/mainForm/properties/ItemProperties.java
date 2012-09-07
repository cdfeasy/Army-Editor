package com.webadmin.client.mainForm.properties;

import com.armyeditor.dto.ItemDTO;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 07.09.12
 * Time: 11:46
 * To change this template use File | Settings | File Templates.
 */
public interface ItemProperties extends PropertyAccess<ItemDTO> {
    @Editor.Path("id")
    ModelKeyProvider<ItemDTO> key();
    ValueProvider<ItemDTO, Long> id();
    @Editor.Path("itemBase.name")
    ValueProvider<ItemDTO, String> itemBase();
    ValueProvider<ItemDTO, Integer> cost();
}
