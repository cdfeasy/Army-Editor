package com.webadmin.client.mainForm.properties;

import com.armyeditor.dto.SquadBaseDTO;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 27.09.12
 * Time: 20:28
 * To change this template use File | Settings | File Templates.
 */
public interface SquadBaseProperties extends PropertyAccess<SquadBaseDTO> {
    @Editor.Path("id")
    ModelKeyProvider<SquadBaseDTO> key();
    ValueProvider<SquadBaseDTO, String> id();
    ValueProvider<SquadBaseDTO, String> name();
    ValueProvider<SquadBaseDTO, String> description();
}
