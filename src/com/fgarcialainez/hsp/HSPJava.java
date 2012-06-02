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
import java.io.PrintWriter;
import java.net.URL;

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
        boolean result = false;
        
        String xlsContent = "";
        String content;
        
        try
         {
            byte[] buffer = new byte[4096];
            
            String strURL = "http://www.invertia.com/inc/bolsa/ficha/excel.asp?FechaDesde=" + beginDate + "%2000:00&FechaHasta=" + endDate + "%2000:00&idtel=" + ticker;
            
            URL url = new URL(strURL);
 
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            int bytesRead;
 
            while ((bytesRead = bis.read(buffer)) != -1)
            {
                content = new String(buffer, 0, bytesRead);
                xlsContent = xlsContent + content;
            }
            
            bis.close();
            
            PrintWriter out = new PrintWriter(xlsOutputFilename);
            out.println(xlsContent);
 
            System.out.println(xlsContent);
 
        } catch (Exception ex){
            System.out.println("There has been a problem with invertia.com service. Exception received: " + ex.getMessage());
        }
        
        return result;
    }
    
    /**
     * @TODO
     * @return 
     */
    public static void retrieveStockDataFromYahooFinance()
    {
    }
}