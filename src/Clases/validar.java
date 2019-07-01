/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_ENTER;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author l
 */
public class validar {

    public void timer(JFrame x) {
        Thread mith = Thread.currentThread();
        try {
            long start = 5000;
            while (start > 0) {
                start = System.currentTimeMillis();
                mith.sleep(5000);
                x.show();
            }
        } catch (InterruptedException c) {
            System.out.println(c);
        }
    }

    public void cambia_color_rojo(JTextField x) {
        if (x.getText().equals("")) {
            x.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, Color.red));
        }
    }

    public void cambia_color_rojo2(JComboBox x) {
        if (x.getSelectedIndex() == 0) {
            x.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, Color.red));
        }
    }

    public void color_azul(JTextField y) {

        y.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent me) {
                y.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, new Color(0, 153, 255)));
            }

            public void mousePressed(MouseEvent me) {
            }

            public void mouseReleased(MouseEvent me) {
            }

            public void mouseEntered(MouseEvent me) {
            }

            public void mouseExited(MouseEvent me) {
            }
        });

    }

    public void color_azul2(JComboBox y) {

        y.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent me) {
                y.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, new Color(0, 153, 255)));
            }

            public void mousePressed(MouseEvent me) {
            }

            public void mouseReleased(MouseEvent me) {
            }

            public void mouseEntered(MouseEvent me) {
            }

            public void mouseExited(MouseEvent me) {
            }
        });

    }

//al dar en enter se realiza la validación, o eso se supone que debe hacer
/* !!!!POR ALGUNA EXTRAÑA RAZON ESTA MIERDA NO FUNCIONA¡¡¡
     public void enter(JTextField campo) {
        campo.addKeyListener(new KeyAdapter() {
            public void KeyTiped(KeyEvent e) {
                char c = e.getKeyChar();
                if(c == e.VK_ENTER){
                    loggin.bot_entrar.doClick();
                }
            }
        });
    }
     */
    public void letras(JTextField campo) {
        campo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c) || !Character.isLetterOrDigit(c)) {
                    e.consume();
                }
            }
        });
    }

    public void especiales(JTextField campo) {
        campo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetterOrDigit(c)) {
                    e.consume();
                }
            }
        });
    }

    public void numeros(JTextField campo) {
        campo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c)) {
                    e.consume();
                }
            }
        });
    }

    public void limitar_caracteres(JTextField campo, int x) {
        campo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                int max = campo.getText().length();
                if (max >= x) {
                    e.consume();
                }
            }
        });
    }

    public void min_max_edad(JTextField campo, int a, int b) {
        campo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                String edad = campo.getText();
                int EDAD = Integer.parseInt(edad);

                if (EDAD >= a || EDAD <= b) {
                    e.consume();
                }
            }
        });
    }

}
