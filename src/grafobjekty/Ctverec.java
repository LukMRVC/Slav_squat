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
public class Ctverec extends Tvar {
    private int rozmer;
    private int speed;
    
    public Ctverec(HerniPanel panel, int rozmer) {
    	super(panel);
        this.rozmer = rozmer;
    }
    
    public Ctverec(HerniPanel panel, int rozmer, int speed) {
        super(panel);
        this.speed = speed;
        this.rozmer = rozmer;
        this.x = (int) Math.round(Math.random()*1000);
        this.y = 10;
    }
        
    @Override
    public void vykresli(Graphics g) {
        super.vykresli(g);
        g.fillRect(this.x, this.y, this.rozmer, this.rozmer);
    }
    
    @Override
    public boolean detekujKurzor(int x, int y){
       return (x <= this.x+this.rozmer && x >= this.x) && (y <= this.y+this.rozmer && y >= this.y);
    }
    
    public int getSpeed(){
    	return this.speed;
    }
    
    public int getRozmer(){
    	return this.rozmer;
    }
}
