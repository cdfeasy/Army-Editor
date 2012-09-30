package com.webadmin.client.mainForm.properties;

import com.armyeditor.dto.CodexDTO;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 27.09.12
 * Time: 18:12
 * To change this template use File | Settings | File Templates.
 */
public interface CodexProperties extends PropertyAccess<CodexDTO> {
    @Editor.Path("id")
    ModelKeyProvider<CodexDTO> key();
    ValueProvider<CodexDTO, String> id();
    ValueProvider<CodexDTO, String> name();
    ValueProvider<CodexDTO, String> description();
    @Editor.Path("name")
    LabelProvider<CodexDTO> nameLabel();
}
