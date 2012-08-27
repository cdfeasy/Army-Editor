package com.webadmin.client.mainForm.properties;

import com.armyeditor.dto.FractionDTO;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 26.08.12
 * Time: 14:58
 * To change this template use File | Settings | File Templates.
 */
public interface FractionProperties extends PropertyAccess<FractionDTO> {
    @Editor.Path("id")
    ModelKeyProvider<FractionDTO> key();
    ValueProvider<FractionDTO, String> id();
    ValueProvider<FractionDTO, String> name();
    ValueProvider<FractionDTO, String> description();
    @Editor.Path("name")
    LabelProvider<FractionDTO> nameLabel();
}
