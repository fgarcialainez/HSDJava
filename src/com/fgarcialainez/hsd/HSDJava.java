/**
 * HSDJava
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
package com.fgarcialainez.hsd;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.text.Format;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class that connects with some external services to retrieve historical stock data
 * @author Felix Garcia Lainez
 */
public class HSDJava 
{
    /**
     * Retrieves stock data from invertia.com in xls format
     * @param ticker Invertia stock ticker (i.e. RV011BSCH, RV011TELEFON, etc)
     * @param beginDate The begin date with format YYYY/MM/DD
     * @param endDate The end date with format YYYY/MM/DD
     * @param xlsOutputFilename The name of the .xls to be created
     * @return true if success, else false
     * @see http://www.invertia.com
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
     * Retrieves stock data from yahoo finance in csv format
     * @param ticker Yahoo stock ticker (i.e. SAN.MC, TEF.MC, AAPL, etc)
     * @param beginDate The begin date with format YYYY-MM-DD
     * @param endDate The end date with format YYYY-MM-DD
     * @param csvOutputFilename The name of the .csv to be created
     * @return true if success, else false
     * @see http://finance.yahoo.com/
     */
    public static boolean retrieveStockDataFromYahooFinance(String ticker, String beginDate, String endDate, String csvOutputFilename)
    {
        boolean success = true;
        
        String csvContent = "";
        String content;
        
        try
        {
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            
            Date bDate = (Date)formatter.parseObject(beginDate);
            Date eDate = (Date)formatter.parseObject(endDate);
            
            Calendar bDateCalendar = Calendar.getInstance();
            Calendar eDateCalendar = Calendar.getInstance();
            
            bDateCalendar.setTime(bDate);
            eDateCalendar.setTime(eDate);
             
            String strURL = "http://ichart.yahoo.com/table.csv?s=" + ticker + "&d=" + eDateCalendar.get(Calendar.MONTH) + "&e=" + eDateCalendar.get(Calendar.DAY_OF_MONTH) + 
                            "&f=" + eDateCalendar.get(Calendar.YEAR) + "&a=" + bDateCalendar.get(Calendar.MONTH) + "&b=" + bDateCalendar.get(Calendar.DAY_OF_MONTH) + 
                            "&c=" + bDateCalendar.get(Calendar.YEAR) + "&ignore=.csv";
            
            URL url = new URL(strURL);
 
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(bis, "ISO-8859-1"));
            
            do{
                content = br.readLine();
                
                if(content != null)
                    csvContent = csvContent + content + "\n";
            }while(content != null);
            
            bis.close();
            
            PrintWriter out = new PrintWriter(csvOutputFilename);
            out.println(csvContent);
            out.flush();
            out.close();
 
        } catch (Exception ex){
            success = false;
            System.out.println("There has been a problem with yahoo.com service. Exception received: " + ex.getMessage());
        }
        
        return success;
    }
    
    /**
     * Retrieves last stock data from Google finance
     * @param ticker The stock ticker
     * @return A Stock object if success, else null
     * @see http://www.google.com/finance
     */
    public static Stock retrieveLastStockDataFromGoogleFinance(String ticker)
    {
        Stock stockData = new Stock();
        
        //@TODO - IMPLEMENT
        
        return stockData;
    }
}