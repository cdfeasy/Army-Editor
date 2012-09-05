package com.webadmin.client.mainForm.properties;

import com.armyeditor.dto.OptionDTO;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 13.08.12
 * Time: 21:39
 * To change this template use File | Settings | File Templates.
 */
public interface OptionProperties extends PropertyAccess<OptionDTO> {
    @Editor.Path("id")
    ModelKeyProvider<OptionDTO> key();
    ValueProvider<OptionDTO, String> id();
    ValueProvider<OptionDTO, String> name();
    ValueProvider<OptionDTO, String> description();
    ValueProvider<OptionDTO, String> action();
}
