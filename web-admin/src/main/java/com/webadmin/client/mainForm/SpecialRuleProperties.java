package com.webadmin.client.mainForm;

import com.armyeditor.entrys.SpecialRule;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 02.08.12
 * Time: 15:43
 * To change this template use File | Settings | File Templates.
 */
public interface SpecialRuleProperties extends PropertyAccess<SpecialRule> {
    ModelKeyProvider<SpecialRule> id();
    ValueProvider<SpecialRule, String> name();
    ValueProvider<SpecialRule, String> description();
}
