package com.webadmin.client.mainForm;

import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 26.07.12
 * Time: 1:02
 * To change this template use File | Settings | File Templates.
 */
public class ArmorFieds extends VerticalLayoutContainer {
    TextField idFld;
    TextField nameFld;
    TextArea descripFld;
    TextButton saveBtn = new TextButton("Save");
    TextButton saveNewBtn = new TextButton("Save as new item");

    public ArmorFieds() {
        idFld = new TextField();
        this.add(new FieldLabel(idFld,"ID"));
        nameFld = new TextField();
        this.add(new FieldLabel(nameFld,"Name"));
        descripFld = new TextArea();
        this.add(new FieldLabel(descripFld,"Description"));
        this.add(saveBtn);
        this.add(saveNewBtn);
//        idFld.disable();
    }

    public TextField getIdFld() {
        return idFld;
    }

    public void setIdFld(TextField idFld) {
        this.idFld = idFld;
    }

    public TextField getNameFld() {
        return nameFld;
    }

    public void setNameFld(TextField nameFld) {
        this.nameFld = nameFld;
    }

    public TextArea getDescripFld() {
        return descripFld;
    }

    public void setDescripFld(TextArea descripFld) {
        this.descripFld = descripFld;
    }

    public TextButton getSaveBtn() {
        return saveBtn;
    }

    public void setSaveBtn(TextButton saveBtn) {
        this.saveBtn = saveBtn;
    }

    public TextButton getSaveNewBtn() {
        return saveNewBtn;
    }

    public void setSaveNewBtn(TextButton saveNewBtn) {
        this.saveNewBtn = saveNewBtn;
    }

}
