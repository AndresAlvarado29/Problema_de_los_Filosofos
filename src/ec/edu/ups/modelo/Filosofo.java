/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import java.awt.Color;
import java.io.File;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Andres
 */
public class Filosofo implements Runnable {

    private int id;
    private int resultado;
    private Thread t;
    private JButton filosofo;
    private JLabel derecha;
    private JLabel izquierda;
    private JLabel resultado1;
    private String process;
    private JTextArea area;

    public Filosofo(int id,JLabel derecha, JLabel izquierda,JButton filosofo, JLabel resultado1, JTextArea area) {
        this.id = id;
        this.filosofo = filosofo;
        this.derecha = derecha;
        this.izquierda = izquierda;
        this.resultado1 = resultado1;
        this.area = area;
        t = new Thread(this);
        t.start();
      
    }

    public JLabel getDerecha() {
        return derecha;
    }

    public JLabel getIzquierda() {
        return izquierda;
    }
    

    @Override
    public void run() {
        for (int i = 0; i < 4; i++) { // se controla el numero de veces que van a comer
            synchronized (this.izquierda) {
                synchronized (this.derecha) {
                    comer();
                }

            }
            pensar();
        }
    }

    private void comer() {
        derecha.setText("Ocupando");
        derecha.setForeground(Color.red);

        izquierda.setText("Ocupando");
        izquierda.setForeground(Color.red);
        filosofo.setText("Comiendo");
        filosofo.setBackground(Color.BLUE);
        
        resultado = Integer.parseInt(resultado1.getText());
        resultado += 1;
        resultado1.setText(String.valueOf(resultado));
        process = "Filosofo " + (id + 1) + ": " + "Comiendo con los tenedores\n";
        area.append(process);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }
        derecha.setText("Libre");
        derecha.setForeground(Color.black);

        izquierda.setText("Libre");
        izquierda.setForeground(Color.black);

        filosofo.setText("Pensando");
        filosofo.setBackground(Color.DARK_GRAY);

        process = "Filosofo " + (id + 1) + ": " + "Dejo de usar los tenedores\n";
        area.append(process);
    }

    private void pensar() {
        derecha.setText("Libre");
        derecha.setForeground(Color.black);

        izquierda.setText("Libre");
        izquierda.setForeground(Color.black);
        
         filosofo.setText("Pensando");
        filosofo.setBackground(Color.DARK_GRAY);
        
         try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
        }
    }

  

}
