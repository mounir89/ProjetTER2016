/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.terweb.packageLogger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 *
 * @author proprietaire
 */
public class LoggerException {

       private static Logger logger=null;
       
       private static final String WEBINF = "WEB-INF";

       private static String getWebInfPath(){

          String filePath = "";

          URL url = LoggerException.class.getResource("LoggerException.class");

          String className = url.getFile();

          filePath = className.substring(0,className.indexOf(WEBINF) + WEBINF.length());

          return filePath;

       }
       
       private static void createLogger() throws IOException
       {
            //File folderLog = new File(getWebInfPath()+"\\LOG");
           
            File folderLog = new File(".\\LOG");
            
            if(!folderLog.exists())
            {
                folderLog.mkdir();
            }
            
            //File fichierLog = new File(getWebInfPath()+"\\LOG\\logFile.log");
            
            //File fichierLog = new File(".\\LOG\\logFile.log");
            
            /*if(!fichierLog.exists())
            {
                fichierLog.createNewFile();
            }
            */
            
            
            logger = Logger.getLogger("LoggerException");
           
            //
            // Create an instance of FileHandler that write log to a file called
            // app.log. Each new message will be appended at the at of the log file.
            //

            FileHandler fileHandler = new FileHandler(".\\LOG\\logFile.log",3072,100,true);

            logger.addHandler(fileHandler);

            /*if (logger.isLoggable(Level.WARNING)) {
                logger.warning("Warning message");
            }*/
            
       }
       
       public static Logger getLoggerException() throws IOException
       {
           if(logger==null)
           {
               createLogger();
           }
           
           return logger;
       }      
}
