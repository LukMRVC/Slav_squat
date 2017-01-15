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
public class Kruh extends Tvar {
    private int rozmer;
    
    public Kruh(HerniPanel panel, int rozmer) {
        super(panel);
        this.rozmer = rozmer;
    }
    
    public Kruh(HerniPanel panel, int rozmer, int x, int y) {
        super(panel);
        this.rozmer = rozmer;
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void vykresli(Graphics g) {
        super.vykresli(g);
        g.fillOval(this.x, this.y, this.rozmer, this.rozmer);
    }
    
    @Override
    public boolean detekujKurzor(int x, int y){
        return Math.sqrt(Math.pow(Math.abs(x-this.x-this.rozmer/2), 2) + Math.pow(Math.abs(y-this.y-this.rozmer/2), 2)) <= this.rozmer/2;
    }
    
    
}
