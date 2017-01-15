/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafobjekty;

import java.awt.Graphics;

/**
 *
 * @author ucitel
 */
public class Platforma extends Tvar {
    private final int width;
    private final int height;
    
    public Platforma(HerniPanel panel, int width, int height) {
        super(panel);
        this.width = width;
        this.height = height;
    }    
    
    @Override
    public void vykresli(Graphics g) {
        super.vykresli(g);
        g.fillRect(0, 550, this.width, this.height);
    }
    
    @Override
    public boolean detekujKurzor(int x, int y){
       return false;
    }
}
