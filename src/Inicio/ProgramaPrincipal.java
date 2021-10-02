/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Inicio;

import GUI.*;

/**
 *
 * @author Grupo2
 */
public class ProgramaPrincipal 
{
     public static void main(String[] args)
     {
         Thread hilo1;
         
         PantallaMenu pMenu = new PantallaMenu();
         hilo1 = new Thread(pMenu);
         pMenu.setVisible(true);
         hilo1.start();
     }
    
}
