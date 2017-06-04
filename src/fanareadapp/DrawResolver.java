/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fanareadapp;

import java.util.Date;

/**
 *
 * @author ngrossi
 */
public class DrawResolver 
{
    public static int dameDrawDeHoy()
    {
        int drawActual = -1;
        
        Date hoy = new Date();
        int year = hoy.getYear();
        int mes = hoy.getMonth();
        int dia = hoy.getDate();
        hoy.setHours(0);
        hoy.setMinutes(0);
        hoy.setSeconds(0);
        
        Date fechaPivotComienzoDraw = new Date((2008 - 1900), (5 - 1) ,23,0,0,0);
	
	drawActual = (int) ((hoy.getTime() - fechaPivotComienzoDraw.getTime()) / ((3600 * 24) * 1000));
        
        return drawActual;
    }
}
