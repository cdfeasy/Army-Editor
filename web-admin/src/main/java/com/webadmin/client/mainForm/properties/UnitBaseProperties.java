package com.webadmin.client.mainForm.properties;

import com.armyeditor.dto.UnitBaseDTO;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 17.08.12
 * Time: 16:21
 * To change this template use File | Settings | File Templates.
 */
public interface UnitBaseProperties extends PropertyAccess<UnitBaseDTO> {
    @Editor.Path("id")
    ModelKeyProvider<UnitBaseDTO> key();
    ValueProvider<UnitBaseDTO, String> id();
    ValueProvider<UnitBaseDTO, Integer> ws();
    ValueProvider<UnitBaseDTO, Integer> bs();
    ValueProvider<UnitBaseDTO, Integer> s();
    ValueProvider<UnitBaseDTO, Integer> t();
    ValueProvider<UnitBaseDTO, Integer> w();
    ValueProvider<UnitBaseDTO, Integer> i();
    ValueProvider<UnitBaseDTO, Integer> a();
    ValueProvider<UnitBaseDTO, Integer> ld();
    ValueProvider<UnitBaseDTO, Integer> sv();
    ValueProvider<UnitBaseDTO, Integer> cost();
    @Editor.Path("unitType.name")
    ValueProvider<UnitBaseDTO, String> unitType(); //todo
}
