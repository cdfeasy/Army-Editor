/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.fractioneditor.client;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 *
 * @author Dmitry
 */
 class ImageCell1 extends com.google.gwt.cell.client.AbstractCell<String> {

    @Override
    public void render(Context context, String value, SafeHtmlBuilder sb) {
        if (value != null) {
            final String vc=value;
            SafeHtml sf=new SafeHtml() {
                public String asString() {
                    return vc;
                }
            };
      sb.append(sf);
    }
    }

  interface Template extends SafeHtmlTemplates {
    @Template("{0}")
    SafeHtml img(String url);
  }

  private static Template template;

  /**
   * Construct a new ImageCell.
   */
  public ImageCell1() {
    if (template == null) {
      template = GWT.create(Template.class);
    }
  }
}
