package com.webadmin.client.mainForm.properties;

import com.armyeditor.dto.VenicleBaseDTO;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 02.10.12
 * Time: 19:46
 * To change this template use File | Settings | File Templates.
 */
public interface VenicleBaseProperties extends PropertyAccess<VenicleBaseDTO> {
    @Editor.Path("id")
    ModelKeyProvider<VenicleBaseDTO> key();
    ValueProvider<VenicleBaseDTO, String> id();
    ValueProvider<VenicleBaseDTO, Integer> ws();
    ValueProvider<VenicleBaseDTO, Integer> bs();
    ValueProvider<VenicleBaseDTO, Integer> s();
    ValueProvider<VenicleBaseDTO, Integer> w();
    ValueProvider<VenicleBaseDTO, Integer> i();
    ValueProvider<VenicleBaseDTO, Integer> a();
    ValueProvider<VenicleBaseDTO, Integer> front();
    ValueProvider<VenicleBaseDTO, Integer> size();
    ValueProvider<VenicleBaseDTO, Integer> rear();
    ValueProvider<VenicleBaseDTO, Integer> cost();
}
