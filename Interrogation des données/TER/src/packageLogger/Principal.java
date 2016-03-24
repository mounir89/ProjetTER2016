package packageLogger;

import java.io.IOException;
import java.util.logging.Level;
import packageExceptions.Exception_ParseException;

public class Principal{

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
                
           /*try {
                
                     File fichierLog = new File(".\\ZZZZ\\logFile.log");
            
                    if(!fichierLog.exists())
                    {
                        fichierLog.createNewFile();
                    }
                     
                    throw new Exception_ParseException();
            
            } catch (Exception_ParseException ex) {
                
                LoggerException.getLoggerException().log(Level.WARNING, "Houssem MSG", ex);
                
            }*/

           LoggerException.getLoggerException().log(Level.WARNING, "Houssem MSG", new Exception_ParseException());
		 
		
	}

}
