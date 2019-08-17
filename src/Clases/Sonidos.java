/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.BufferedInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author maico
 */
public class Sonidos {
    public static Clip sonido;
   public void Reproducir(String tipo) {
        try {      
        BufferedInputStream bis = new BufferedInputStream(getClass().getResourceAsStream("/Sonidos/"+tipo+".wav"));
            AudioInputStream ais  = AudioSystem.getAudioInputStream(bis);
            sonido = AudioSystem.getClip();
            sonido.open(ais);
            sonido.start();
    } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.out.println("" + e);
            System.err.println(e);
        }
            
    }
   public void Clic(){
       Reproducir("Clic");
   }
   public void Iniciar(){
       Reproducir("sonido_inicio");
   }
   
   public void IniciarSesion (){
       Reproducir("Sesion");
   }
   public void error(){
       Reproducir("Error");
   }
   public void notificacion(){
       Reproducir ("Notify");
   }
   public void EfectoPopUp(){
       Reproducir ("pasar_barra");
   }
   public void SonidoSalida(){
       Reproducir ("SonidoSalida");
   }
    
}