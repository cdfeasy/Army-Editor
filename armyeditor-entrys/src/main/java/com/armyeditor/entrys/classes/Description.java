/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.entrys.classes;

/**
 *
 * @author Dmitry
 */
public @interface Description {
    String title() default "";
    int version() default 1;
    String textRus() default "";
    String textEn() default "";
}