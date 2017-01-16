/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafobjekty;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 *
 * @author student
 */
public class Obrazek{
    private BufferedImage img = null;
    private String src = "";
    int x, y;
    Ctverec hitbox;
    private boolean fall;
    
    
    public Obrazek(HerniPanel panel, String src,int x,int y){
        this.src = src;
        this.x = x;
        this.y = y;
        fall = false;
        try{
            img = ImageIO.read(new File(src));
        } catch(IOException e){
            System.out.println("Invalid Path");
        }
        hitbox = new Ctverec(panel, 110);
        hitbox.setX(this.getX());
        hitbox.setY(this.getY());
    }
    
    public void moveRight(){
        this.x += 8;
        hitbox.x+=8;
    }
    public void moveLeft(){
        this.x -= 8;
        hitbox.x-=8;
    }
   
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public void setY(int x){
    	 this.y = x;
    	 hitbox.y = x;
    }
    
    public void setX(int x){
        this.x = x;
        hitbox.x = x;
    }
   
    
    public void vykresli(Graphics g){
        g.drawImage(img, this.x, this.y, null);
    }

    public boolean jump(boolean jump){
    	if(jump && this.y >= 200 && !fall){
			this.y -=5;
			hitbox.y -=5;
			if(this.y == 200)
				fall = true;
		}	
    	else if(fall && this.y <= 420){
    		this.y+=5;
    		hitbox.y += 5;
    	}
			
		if(this.y == 420){
				fall = false;
				return false;
			}
		return true;
    }

    
}
