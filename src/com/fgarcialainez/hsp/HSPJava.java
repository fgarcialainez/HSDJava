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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.text.Normalizer;

/**
 * Utility class that connects with some external services to retrieve historical
 * stock data from different services
 * @author Felix Garcia Lainez
 */
public class HSPJava 
{
    /**
     * Retrieves stock data from invertia.com in xls format
     * @param ticker Invertia stock ticker (i.e. RV011BSCH, RV011TELEFON, etc)
     * @param beginDate The begin date
     * @param endDate The end date
     * @return true if success, else false
     */
    public static boolean retrieveStockDataFromInvertia(String ticker, String beginDate, String endDate, String xlsOutputFilename)
    {
        boolean success = true;
        
        String xlsContent = "";
        String content;
        
        try
         {
            String strURL = "http://www.invertia.com/inc/bolsa/ficha/excel.asp?FechaDesde=" + beginDate + "%2000:00&FechaHasta=" + endDate + "%2000:00&idtel=" + ticker;
            
            URL url = new URL(strURL);
 
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(bis, "ISO-8859-1"));
            
            do{
                content = br.readLine();
                
                if(content != null)
                    xlsContent = xlsContent + content;
            }while(content != null);
            
            xlsContent = Normalizer.normalize(xlsContent, Normalizer.Form.NFD);
            xlsContent = xlsContent.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
            
            bis.close();
            
            PrintWriter out = new PrintWriter(xlsOutputFilename);
            out.println(xlsContent);
 
        } catch (Exception ex){
            success = false;
            System.out.println("There has been a problem with invertia.com service. Exception received: " + ex.getMessage());
        }
        
        return success;
    }
    
    /**
     * @TODO
     * @return 
     */
    public static void retrieveStockDataFromYahooFinance()
    {
    }
}