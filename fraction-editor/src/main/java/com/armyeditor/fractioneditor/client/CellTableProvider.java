/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.fractioneditor.client;

import com.armyeditor.fractioneditor.client.services.IServiceAsync;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import java.util.List;

/**
 *
 * @author Dmitry
 */
public class CellTableProvider {
        private final IServiceAsync _dataProvider;
	public CellTableProvider(IServiceAsync dataProvider) {
		_dataProvider = dataProvider;
	}

	public void addDataDisplay(final HasData<CellTableModel> dataDisplay) {

		// set row count
		_dataProvider.getCount(new AsyncCallback<Integer>() {

			public void onFailure(Throwable caught) {
				throw new UnsupportedOperationException("Not supported yet.");

			}

			public void onSuccess(Integer result) {
				dataDisplay.setRowCount(result);
			}
		});

		// add callback for lazy range loading
		AsyncDataProvider<CellTableModel> dataProvider = new AsyncDataProvider<CellTableModel>() {
                    @Override
			protected void onRangeChanged(final HasData<CellTableModel> display) {
				final Range range = display.getVisibleRange();

				_dataProvider.getItems(
						range.getStart(),
						range.getLength(),
						new AsyncCallback<List<CellTableModel>>() {

							public void onFailure(Throwable caught) {
                                                            caught.printStackTrace();
								throw new UnsupportedOperationException("Not supported yet.");
							}

							public void onSuccess(List<CellTableModel> result) {
								display.setRowData(range.getStart(), result);
							}
						});
			}
		};
		dataProvider.addDataDisplay(dataDisplay);
              //  dataProvider.updateRowCount(5, true);
	}
}
