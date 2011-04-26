/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.armyeditor.fractioneditor.client;


import com.armyeditor.entrys.WeaponType;
import com.armyeditor.fractioneditor.client.services.IBaseService;
import com.armyeditor.fractioneditor.client.services.IBaseServiceAsync;
import com.armyeditor.fractioneditor.client.services.IServiceAsync;
import com.armyeditor.fractioneditor.client.services.IService;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.ImageCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Main entry point.
 *
 * @author Dmitry
 */
public class MainWindow implements EntryPoint {
    /** 
     * Creates a new instance of MainEntryPoint
     */
    public MainWindow() {
    }

       final ListBox lbFraction=new ListBox();
       final ListBox lbCodex=new ListBox();
       final ListBox lbSquad=new ListBox();
       final ListBox lbUnit=new ListBox();

    private final IServiceAsync greetingService = GWT.create(IService.class);
    /** 
     * The entry point method, called automatically by loading a module
     * that declares an implementing class as an entry-point
     */
    final Label lbl=new Label();
    ClickHandler fractionClickHandler=new ClickHandler() {
            public void onClick(ClickEvent event) {
                if(lbFraction.getValue(lbFraction.getSelectedIndex()).equals("f1"))
                {
                     lbCodex.clear();
                     lbCodex.addItem("f1-c1");
                     lbCodex.addItem("f1-c2");
                }
                else
                {
                     lbCodex.clear();
                     lbCodex.addItem("f2-c1");
                     lbCodex.addItem("f2-c2");
                }
            }
    };

    ClickHandler codexClickHandler=new ClickHandler() {
            public void onClick(ClickEvent event) {
                lbSquad.clear();
                lbUnit.clear();;
                if(lbCodex.getValue(lbCodex.getSelectedIndex()).equals("f1-c1"))
                {
                     lbSquad.addItem("f1-c1-s1");
                     lbSquad.addItem("f1-c1-s2");
                     lbUnit.addItem("f1-c1-u1");
                     lbUnit.addItem("f1-c1-u2");
                }
                if(lbCodex.getValue(lbCodex.getSelectedIndex()).equals("f2-c2"))
                {
                     lbSquad.addItem("f2-c2-s1");
                     lbSquad.addItem("f2-c2-s2");
                      lbUnit.addItem("f2-c2-u1");
                     lbUnit.addItem("f2-c2-u2");
                }
            }
    };

    ClickHandler testbtn=new ClickHandler() {
            public void onClick(ClickEvent event) {
                final Label lbl=new Label();
                Random rnd=new Random(new Date().getTime());
                greetingService.greetServer("sdsd",
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								//
                                                            caught.printStackTrace();
                                                            //     lbl.setText(caught.getMessage());
							}

							public void onSuccess(String result) {
								//System.out.println(result);
                                                              //  lbl.setText(result);
							}
						});

          //    RootPanel.get().add(lbl,rnd.nextInt(500),rnd.nextInt(500) );
            }
        };

        ClickHandler testbtn1=new ClickHandler() {
            public void onClick(ClickEvent event) {
                    Service.getWeaponTypes(new AsyncCallback<List<WeaponType>>() {
                                                    public void onFailure(Throwable caught) {
                                                        System.out.println("----------------------------");
                                                        System.out.println("----------------------------");
                                                        System.out.println("----------------------------");
                                                        caught.printStackTrace();
                                                    }

                                                    public void onSuccess(List<WeaponType> result) {
                                                        System.out.println("----------------------------");
                                                        System.out.println("---------11111111----------");
                                                        System.out.println("----------------------------");
                                                        lbl.setText(Integer.toString(result.size()));
                                                    //    lbl.setText(Integer.toString(result.size()));
                                                    }

						});
              }
        };


 private final IBaseServiceAsync Service = GWT.create(IBaseService.class);

    public void initStartup()
    {
       com.google.gwt.user.client.ui.Grid fTable=new  com.google.gwt.user.client.ui.Grid(4,2);
       fTable.setText(0, 0, "Fraction");
       fTable.setWidget(0, 1, lbFraction);
       fTable.setText(1, 0, "Codex");
       fTable.setWidget(1, 1, lbCodex);
       fTable.setText(2, 0, "Squad");
       fTable.setWidget(2, 1, lbSquad);
       fTable.setText(3, 0, "Unit");
       fTable.setWidget(3, 1, lbUnit);
     //  fTable.getFlexCellFormatter().
  //g.getCellFormatter().setWidth(0, 2, "256px");
       lbFraction.addItem("f1");
       lbFraction.addItem("f2");
       lbFraction.addClickHandler(fractionClickHandler);
       lbCodex.addClickHandler(codexClickHandler);
//
//
//
//    //   fTable.setWidget(1, 0, new Button("Wide Button"));
//
//    // ...and set it's column span so that it takes up the whole row.
//      //  fTable.getFlexCellFormatter().setColSpan(1, 0, 3);
        RootPanel.get().add( fTable,0,0);
       final CellTable<CellTableModel>  cellTable = new CellTable<CellTableModel>(3, CellTableModel.KEY_PROVIDER);
		cellTable.setWidth("500", true);
		cellTable.setHeight("300");

        SingleSelectionModel selectionModel = new SingleSelectionModel<CellTableModel>(
				CellTableModel.KEY_PROVIDER);
	cellTable.setSelectionModel(selectionModel,
				DefaultSelectionEventManager.<CellTableModel>createCheckboxManager());
        initTableColumns(selectionModel,cellTable);

        final CellTableProvider provider = new CellTableProvider(greetingService);
	provider.addDataDisplay(cellTable);

        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
	SimplePager pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
	pager.setDisplay(cellTable);
        RootPanel.get().add( cellTable,100,100);

        ClickHandler testbtn1=new ClickHandler() {
            public void onClick(ClickEvent event) {
                cellTable.setVisibleRange(4, 3);
                //provider.addDataDisplay(cellTable);
            }
        };
          Button button1 = new Button("Click me2");
          button1.addClickHandler(testbtn1);
          RootPanel.get().add(button1,800,800);
          com.google.gwt.user.client.ui.Image img=new  com.google.gwt.user.client.ui.Image();
          img.setUrl(getImageData());

           RootPanel.get().add(img,900,800);
     //    RootPanel.get().add( pager,100,100);


    }

    public String getImageData(){
       String base64 = "iVBORw0KGgoAAAANSUhEUgAAACsAAAAdCAIAAAC8HAInAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAADnSURBVEhL7ZRJDsUwCENz/0vTSmkpYjQd1E3+rl/gOOaRQX//xt8GaDmglcFOYcbBGE8p2RWkiPqcS1CcMXvcznKJbKOrVt9StrVSmcXcEun4Dmw1y4EmODbZ2M5AqihFZAQzAOWA/7yyybVUGDgWcgSRCEQi+3NVIvf26glMNYnShMwwWZCIPrel5yDCSuLZOr5+D2zOdhyqpoRf15dURyYs1XZGbDc5pTGF/K4H2AH3XzlwJ6I2tnzB7meQXxrJv/ceILiUkLoiTzNIlgUxfWcbQV287P0M8LPf56B79nJwJrY4INoAHy9CZzCeYKwAAAAASUVORK5CYII=";
       return base64;
}

    private void initTableColumns(
		final SelectionModel<CellTableModel> selectionModel, CellTable cellTable) {
		// Checkbox column. This table will uses a checkbox column for selection.
		// Alternatively, you can call cellTable.setSelectionEnabled(true) to enable
		// mouse selection.
		Column<CellTableModel, Boolean> checkColumn = new Column<CellTableModel, Boolean>(
				new CheckboxCell(true, false)) {

			@Override
			public Boolean getValue(CellTableModel object) {
				// Get the value from the selection model.
				return selectionModel.isSelected(object);
			}
		};
		//cellTable.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
                cellTable.addColumn(checkColumn);
	//	cellTable.setColumnWidth(checkColumn, 40, Unit.PX);

		// Short name.
		Column<CellTableModel, String> idcolumn = new Column<CellTableModel, String>(
				new TextCell()) {

			@Override
			public String getValue(CellTableModel object) {
				return Long.toString(object.getId());
			}
		};
		cellTable.addColumn(idcolumn, "idcolumn");

        Image close = new Image(getImageData());
        Column<CellTableModel, String> imgcolumn = new Column<CellTableModel, String>(
                new com.google.gwt.cell.client.AbstractCell<String>() {

                    @Override
                    public void render(Context context, String value, SafeHtmlBuilder sb) {
                        if (value != null) {
                            sb.append(com.google.gwt.safehtml.shared.SafeHtmlUtils.fromTrustedString(value));
                        }
                    }
                }) {

            @Override
            public String getValue(CellTableModel object) {
                return "<img src=\"data:image/png;base64," +getImageData()+ "\"/>";
            }
        };
        cellTable.addColumn(imgcolumn, "imgcolumn");
		//cellTable.setColumnWidth(shortNameColumn, 20, Unit.PCT);

		// Last name.
		Column<CellTableModel, String> maxcoutColumn = new Column<CellTableModel, String>(
				new TextCell()) {

			@Override
			public String getValue(CellTableModel object) {
				return object.getMaxCount();
			}
		};
		cellTable.addColumn(maxcoutColumn, "maxcoutColumn");
		//cellTable.setColumnWidth(fullNameColumn, 20, Unit.PCT);
	}

     public void initTest()
     {
         Button button1 = new Button("Click me1111");
          button1.addClickHandler(testbtn1);
          RootPanel.get().add(button1,700,700);
          RootPanel.get().add(lbl,750,750);

     }

  //  BaseWindow bs=new BaseWindow();
  

    public void onModuleLoad() {
        initStartup();
         initTest();
      //  initTest();
      //  bs.initStartup();





//        final Label label = new Label("Hello, GWT!!!");
//        final Button button = new Button("Click me!");
//        Button button1 = new Button("Click me1");
//        button.addClickHandler(new ClickHandler() {
//            public void onClick(ClickEvent event) {
//                label.setVisible(!label.isVisible());
//            }
//        });
//        button1.addClickHandler(new ClickHandler() {
//
//            public void onClick(ClickEvent event) {
//                final Label lbl=new Label();
//                Random rnd=new Random(new Date().getTime());
//              //  RootPanel.get().add(lbl,rnd.nextInt(500),rnd.nextInt(500) );
//
//                greetingService.greetServer("sdsd",
//						new AsyncCallback<String>() {
//							public void onFailure(Throwable caught) {
//								// Show the RPC error message to the user
//								caught.printStackTrace();
//                                                                 lbl.setText(caught.getMessage());
//							}
//
//							public void onSuccess(String result) {
//								System.out.println(result);
//                                                                lbl.setText(result);
//							}
//						});
//
//               RootPanel.get().add(lbl,rnd.nextInt(500),rnd.nextInt(500) );
//
//
//
//            }
//        });
//
//        RootPanel.get().add(button);
//        RootPanel.get().add(button1);
//        RootPanel.get().add(label);
    }


}
