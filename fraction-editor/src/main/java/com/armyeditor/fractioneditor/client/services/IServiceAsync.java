/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.fractioneditor.client.services;

import com.armyeditor.fractioneditor.client.CellTableModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import java.util.List;

/**
 *
 * @author Dmitry
 */
public interface IServiceAsync {

    public void getItems(int startIndex, int maxCount, AsyncCallback<List<CellTableModel>> asyncCallback);

    public void getCount(AsyncCallback<java.lang.Integer> asyncCallback);

    public void greetServer(String name, AsyncCallback<String> asyncCallback);

//     public static final class Util
//    {
//        private static IServiceAsync instance;
//
//        public static final IServiceAsync getInstance()
//        {
//            if ( instance == null )
//            {
//                instance = (IServiceAsync) GWT.create( IServiceAsync.class );
//                ServiceDefTarget target = (ServiceDefTarget) instance;
//                target.setServiceEntryPoint( GWT.getModuleBaseURL() + "Service" );
//            }
//            return instance;
//        }
//
//        private Util()
//        {
//            // Utility class should not be instanciated
//        }
//    }

}
