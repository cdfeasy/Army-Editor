/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webadmin.client;

import com.armyeditor.entrys.Armor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 *
 * @author d.asadullin
 */
public interface PostProperties extends PropertyAccess<Armor> {

		ModelKeyProvider<Armor> id();

		ValueProvider<Armor, String> name();
	}
