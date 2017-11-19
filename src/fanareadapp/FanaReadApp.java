package fanareadapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.telnet.EchoOptionHandler;
import org.apache.commons.net.telnet.InvalidTelnetOptionException;
import org.apache.commons.net.telnet.SimpleOptionHandler;
import org.apache.commons.net.telnet.SuppressGAOptionHandler;
import org.apache.commons.net.telnet.TelnetClient;
import org.apache.commons.net.telnet.TerminalTypeOptionHandler;

public class FanaReadApp 
{
    public static String estadoActual;
    public static List<String> estados = new ArrayList();
    
    
   
    
    
    public static void main(String args[])
    {
        int drawDeHoy = DrawResolver.dameDrawDeHoy() ;
        System.out.println("DRAW: " + drawDeHoy);

        //DEBO HACER UN BOS A LA OPCION 11:
        Telnet telnet = new Telnet("192.168.5.200", "root", "tecacc");
        //System.out.println("Got Connection...");
            telnet.sendCommand("ps ");
            telnet.sendCommand("ls ");
            telnet.sendCommand("ifconfig ");
            telnet.sendCommand("uptime ");
            telnet.disconnect();
            System.out.println("DONE");

        /*
        String archivoDestino = "C:\\chequeo matches fantikon\\destino.txt";
        if(FTPDownloader.bajar("192.168.88.11", 21, "l5_rdusr", "l5_rdusr", "/f2/lotos/l5_arge/log/sys/PRINT_PDRW_4100_" + drawDeHoy + ".TXT" , archivoDestino))
        {
            inicializarArrayEstados();

            List<String> arrRaw = leerArchivo(archivoDestino);

            System.out.println("procesando " + arrRaw.size() + " lineas");

            int contador = 0 ;
            String idUltimoEvento = "0";
            for(String s : arrRaw)
            {
                if(s != null)
                {
                    if(estadoActual == estados.get(0) && s.contains("EVENT["))
                    {
                        //System.out.println(contador + " -> " + s);
                        estadoActual =  estados.get(1);
                    }
                    if(estadoActual == estados.get(1) && s.contains("]"))
                    {
                        int indiceApertura = (s.indexOf("EVENT[") + 6);
                        int indiceCierre = s.indexOf("]");
                        idUltimoEvento = s.substring(indiceApertura, indiceCierre);
                        estadoActual =  estados.get(2);
                    }
                    if(estadoActual == estados.get(2) && s.contains("Sts:"))
                    {
                        int indiceApertura = s.indexOf("Sts:");
                        int indiceCierre = s.indexOf("(");
                        String estado = s.substring( (indiceApertura + 4) ,indiceCierre);
                        if(!estado.equalsIgnoreCase("Payout"))
                        {
                            System.out.println("Evento[" + idUltimoEvento + "] - estado: " + estado );
                        }

                        estadoActual = estados.get(0);

                    }
                    else
                    {
                        //System.out.println(contador + " | " + s);
                    }
                }

                contador++;
            }
        }*/
    }
    
    public static List<String> leerArchivo(String ruta)
    {
        List<String> arrRaw = new ArrayList<String>();
        String salida = "";
        
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(ruta));
            if(br != null)
            {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) 
                {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                    arrRaw.add(line);
                }
                //arrRaw.add(sb.toString());
                //salida = sb.toString();
                
                br.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return arrRaw;
    }
    public static void inicializarArrayEstados()
    {
        estados.add("init");
        estados.add("abriEvent");
        estados.add("cerreEvent");
        
        estadoActual = estados.get(0);
    }
}