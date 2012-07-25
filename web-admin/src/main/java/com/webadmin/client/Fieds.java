package com.webadmin.client;

import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 26.07.12
 * Time: 1:02
 * To change this template use File | Settings | File Templates.
 */
public class Fieds extends VerticalLayoutContainer {
    TextField idFld;
    TextField nameFld;
    TextField descripFld;
    TextButton saveBtn = new TextButton("Save");
    TextButton saveNewBtn = new TextButton("Save as new item");

    public Fieds() {
        idFld = new TextField();
        this.add(new FieldLabel(idFld,"ID"));
        nameFld = new TextField();
        this.add(new FieldLabel(nameFld,"Name"));
        descripFld = new TextField();
        this.add(new FieldLabel(descripFld,"Description"));
        this.add(saveBtn);
        this.add(saveNewBtn);
    }
}
