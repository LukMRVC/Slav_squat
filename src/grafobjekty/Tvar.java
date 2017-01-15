/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafobjekty;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


/**
 *
 * @author ucitel
 */
public abstract class Tvar{
    private HerniPanel panel;
    protected int x;
    protected int y;
    private Color barva = Color.BLACK;
    private double pruhlednost;
    private Random generator;
            
    public Tvar(HerniPanel panel){
       this.generator = new Random(); 
       this.panel = panel;
       this.x = generator.nextInt(this.panel.getWidth());
       this.y = generator.nextInt(this.panel.getHeight());
    }
    
    public void vykresli(Graphics g) {
        g.setColor(this.barva);
    }

    public Color getBarva() {
        return barva;
    }

    public void setBarva(Color barva) {
        this.barva = barva;
    }

    public double getPruhlednost() {
        return pruhlednost;
    }

    public void setPruhlednost(double pruhlednost) {
        this.pruhlednost = pruhlednost;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
    public void fire(){
        this.y -= 5;
    }
    
    abstract protected boolean detekujKurzor(int x, int y);
}
