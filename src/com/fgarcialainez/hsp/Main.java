/**
 *
 * HSPJava
 *
 * Created by Felix Garcia Lainez on June 2, 2012
 * Copyright 2012 Felix Garcia Lainez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fgarcialainez.hsp;

/**
 * Main class to test class HSPJava
 * @author Felix Garcia Lainez
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {    
        //RETRIEVE DATA FROM IBERDROLA BETWEEN FEB 23, 2010 TO APRIL 20, 2011. SAVE OUTPUT DATA ON FILE testIberdrola.xls
        HSPJava.retrieveStockDataFromInvertia("RV011IBERDRO", "2010/2/23", "2011/4/20", "testIberdrola.xls");
        
        //RETRIEVE DATA FROM BANCO SANTANDER BETWEEN MARCH 1, 2010 TO JAN 25, 2011. SAVE OUTPUT DATA ON FILE testSantander.xls
        HSPJava.retrieveStockDataFromInvertia("RV011BSCH", "2010/3/1", "2011/1/25", "testSantander.xls");
        
        //RETRIEVE DATA FROM TELEFONICA BETWEEN JAN 1, 2009 TO JAN 31, 2011. SAVE OUTPUT DATA ON FILE testTelefonica.xls
        HSPJava.retrieveStockDataFromInvertia("RV011TELEFON", "2009/1/1", "2011/1/31", "testTelefonica.xls");
    }
}
