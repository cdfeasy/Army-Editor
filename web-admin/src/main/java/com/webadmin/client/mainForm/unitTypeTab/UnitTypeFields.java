package com.webadmin.client.mainForm.unitTypeTab;

import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 02.08.12
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */
public class UnitTypeFields extends BorderLayoutContainer {
    TextField idFld;
    TextField nameFld;
    TextButton saveBtn = new TextButton("Save");
    TextButton saveNewBtn = new TextButton("Save as new item");

    public UnitTypeFields(){
        VerticalLayoutContainer vc = new VerticalLayoutContainer();
        idFld = new TextField();
        vc.add(new FieldLabel(idFld, "ID"));
        nameFld = new TextField();
        vc.add(new FieldLabel(nameFld, "Name"));
        vc.add(saveBtn);
        vc.add(saveNewBtn);

        this.setBorders(true);
        this.setHeight(200);
        this.setWidth(350);
        this.add(vc,new VerticalLayoutContainer.VerticalLayoutData(350,200,new Margins(5,5,5,5)));
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