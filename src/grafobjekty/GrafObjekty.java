/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafobjekty;

import java.awt.HeadlessException;
import javax.swing.JFrame;
import Ostatni.Menu;

/**
 *
 * @author ucitel
 */
public class GrafObjekty extends JFrame {

    public GrafObjekty() throws HeadlessException {
        this.setTitle("Grafické objekty");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        Menu menu = new Menu();
        this.add(menu);
        this.pack();
    }

        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GrafObjekty okno = new GrafObjekty();
        okno.setVisible(true);
    }
    
}
