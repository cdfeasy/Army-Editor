package com.webadmin.client.mainForm.properties;

import com.armyeditor.dto.AttackTypeDTO;
import com.armyeditor.entrys.AttackType;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import static com.google.gwt.editor.client.Editor.Path;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 28.07.12
 * Time: 12:49
 * To change this template use File | Settings | File Templates.
 */
public interface AttackTypeProperties extends PropertyAccess<AttackTypeDTO> {
    @Path("id")
    ModelKeyProvider<AttackTypeDTO> key();
    ValueProvider<AttackTypeDTO, String> id();
    ValueProvider<AttackTypeDTO, String> name();
    ValueProvider<AttackTypeDTO, String> description();
}
