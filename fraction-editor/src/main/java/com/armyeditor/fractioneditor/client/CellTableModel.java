/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.fractioneditor.client;

import com.google.gwt.view.client.ProvidesKey;
import java.io.Serializable;

/**
 *
 * @author Dmitry
 */
public class CellTableModel implements Serializable{
    public static final ProvidesKey<CellTableModel> KEY_PROVIDER = new ProvidesKey<CellTableModel>() {
      public Object getKey(CellTableModel item) {
        return item == null ? null : item.getId();
      }
    };

    public CellTableModel(){}
    public CellTableModel(Long Id,String maxcount)
    {
        this._id=Id;
        this._maxCount=maxcount;
    }
	private Long _id;
	private String _maxCount;

    public String getMaxCount() {
        return _maxCount;
    }

    public void setMaxCount(String _maxCount) {
        this._maxCount = _maxCount;
    }


	public Long getId() {
		return _id;
	}

	public void setId(Long id) {
		this._id = id;
	}


}
