package Clases;
import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
/**
 * @web http://www.jc-mouse.net
 * @author Mouse
 */
public class Sonido {
    //objetos soportados
    private JTextField JTextfield;
    private JLabel jLabel;
    private JComboBox jComboBox;
    private JButton jButton;
    private String Tipo = "";
    private Clip clip;
    //paquete donde se encuentran los archivos de sonido WAV
    private String path="/jcsonido/wav/";

    public Sonido(Object object)
    {
        //Eventos del raton
        MouseListener mouseListener = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent evt) {
            if( Tipo.equals("JTextField") )
            {
                if( !JTextfield.isEditable() )
                    JTextfield.setEditable(true);
                JTextfield.selectAll();
                daleplay("clic");
            }
            if( Tipo.equals("JComboBox") )            
                daleplay("swing");
            if( Tipo.equals("JButton") )
                daleplay("beep");
            if( Tipo.equals("JLabel") )
                daleplay("chafez");
        }

        @Override
        public void mouseEntered(MouseEvent evt) {
            if( Tipo.equals("JLabel"))
                daleplay("burro");
            else
                daleplay("water1");
        }

        @Override
        public void mouseExited(MouseEvent evt) {
            if( Tipo.equals("JTextField") )
            {
                if( JTextfield.isEditable() )
                {
                    JTextfield.setEditable(false);
                    daleplay("bubble");
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent evt) {}

        @Override
        public void mouseReleased(MouseEvent evt) {}
    };

    //EVENTOS DE TECLADO
    KeyListener  keyListener = new KeyListener(){

        @Override
        public void keyTyped(KeyEvent e) {
         if( Tipo.equals("JTextField") )
            {
                if( JTextfield.isEditable() )                
                    daleplay("typetext");
                else
                    daleplay("locked");
            }                
        }

        @Override
            public void keyPressed(KeyEvent e) {}

        @Override
            public void keyReleased(KeyEvent e) {}
        };

    //Evento del JCombo cuando se realiza un cambio en un item
    ItemListener Itemlistener = new ItemListener () {
            @Override
            public void itemStateChanged(ItemEvent e) {
                daleplay("out");
            }
        };

    //Dependiendo del objecto que se añada, se asignan eventos y variables
    if(object instanceof javax.swing.JTextField)
    {
       JTextfield  = (JTextField) object;
       JTextfield.addMouseListener(mouseListener);
       JTextfield.addKeyListener(keyListener);
       JTextfield.setEditable(false);
       JTextfield.setCursor(Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ));
       this.Tipo = "JTextField";
    }
    else if(object instanceof javax.swing.JLabel)
    {
        jLabel = (JLabel) object;
        jLabel.addMouseListener(mouseListener);
        jLabel.setCursor(Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ));
        this.Tipo = "JLabel";
    }
    else if(object instanceof javax.swing.JComboBox)
    {
        jComboBox = ((JComboBox) object);
        this.Tipo = "JComboBox";
        jComboBox.addMouseListener(mouseListener);
        jComboBox.addItemListener(Itemlistener);
    }
    else if(object instanceof javax.swing.JButton)
    {
        this.Tipo = "JButton";
        this.jButton = (JButton) object;
        this.jButton.addMouseListener(mouseListener);
    }

    }
    //metodo que reproduce un sonido del paquete WAV
    public void daleplay(String value)
    {        
       try
       {
	  clip=AudioSystem.getClip();
	  clip.open(AudioSystem.getAudioInputStream( getClass().getResourceAsStream( path +  value +".wav" ) ) );
          clip.start();
       }catch(Exception ex){
 	  System.err.println( ex.getMessage() );
        }
    }

}
