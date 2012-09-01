/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webadmin.client.services;

import java.io.Serializable;

/**
 *
 * @author dmitry
 */
public class ArmyException extends Exception implements Serializable{

    public ArmyException() {
    }
    

    public ArmyException(String message,Throwable th) {
         super(message,th);
    }
    
    public ArmyException(Throwable th) {
         super(th);
    }
    public ArmyException(String message ) {
         super(message);
    }
    
}
