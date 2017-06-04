package fanareadapp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
 
public class FTPDownloader {
 
    public static boolean bajar(String direccionIPServidor , int puerto, String user , String pass , String urlArchivoOrigen , String urlArchivoDestino)
    {
        boolean termineDeBajar = false;
        
        FTPClient ftpClient = new FTPClient();
        
        try 
        {
            ftpClient.connect(direccionIPServidor, puerto);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            //ARCHIVO ORIGEN:
            String remoteFile1 = urlArchivoOrigen;
            
            //ARCHIVO DESTINO:
            File downloadFile1 = new File(urlArchivoDestino);
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
            
            
            System.out.println("Descargando ftp://" + direccionIPServidor+ ":" + puerto + "" +   urlArchivoOrigen + " , por favor espere..");
            boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
            outputStream1.close();
 
            if (success) 
            {
                System.out.println("Archivo (" + urlArchivoDestino +  ") ha sido descargado con exito!");
                termineDeBajar = true;
            }
            else
            {
                System.out.println("no se pudo bajar");
            }
        } 
        catch (Exception  ex) 
        {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        finally 
        {
            try
            {
                if (ftpClient.isConnected())
                {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return termineDeBajar;
    }
    
}