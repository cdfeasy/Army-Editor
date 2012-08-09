/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webadmin.client.mainForm.armorTab;

import com.armyeditor.dto.ArmorDTO;
import com.armyeditor.entrys.Armor;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import static com.google.gwt.editor.client.Editor.*;

/**
 *
 * @author d.asadullin
 */
public interface ArmorProperties extends PropertyAccess<ArmorDTO> {
    @Path("id")
    ModelKeyProvider<ArmorDTO> key();
    ValueProvider<ArmorDTO, String> id();
    ValueProvider<ArmorDTO, String> name();
    ValueProvider<ArmorDTO, String> description();
	}
