/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author maico
 */
public class Sonidos {
    public void Inicio(){
         try{
            Clip Sonido=AudioSystem.getClip();
            Sonido.open(AudioSystem.getAudioInputStream(new File("src\\Sonidos\\sonido_inicio.wav")));
            Sonido.start();
             }catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){
                 System.err.println(ex+" error");
                 }
    }
    
    public void Click(){
        try{
            Clip Sonido=AudioSystem.getClip();
            Sonido.open(AudioSystem.getAudioInputStream(new File("src\\Sonidos\\sonido_click.wav")));
            Sonido.start();
             }catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){
                 System.err.println(ex+" error");
                 }
    }
    
    public void IniciarSesion(){
        try{Clip Sonido=AudioSystem.getClip();
        Sonido.open(AudioSystem.getAudioInputStream(new File("src\\Sonidos\\fx 31.wav")));
        Sonido.start();
        }catch(Exception ex){
        System.err.println(ex+" error");}
    }
    
    public void CerrarSecion(){
        try{
          Clip Sonido=AudioSystem.getClip();
          Sonido.open(AudioSystem.getAudioInputStream(new File("src\\Sonidos\\sonido_salida.wav")));
          Sonido.start();
          
     }catch(Exception ex){
     System.err.println(ex+" error");}
    }
}
