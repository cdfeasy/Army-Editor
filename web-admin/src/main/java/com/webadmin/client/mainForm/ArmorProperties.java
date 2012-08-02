/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webadmin.client.mainForm;

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
public interface ArmorProperties extends PropertyAccess<Armor> {
    @Path("id")
    ModelKeyProvider<Armor> key();
    ValueProvider<Armor, String> id();
    ValueProvider<Armor, String> name();
    ValueProvider<Armor, String> description();
	}
