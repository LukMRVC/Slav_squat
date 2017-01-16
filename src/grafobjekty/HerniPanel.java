/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafobjekty;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;



/**
 *
 * @author ucitel
 */
public class HerniPanel extends JPanel implements ActionListener, MouseListener, KeyListener {
    public static final int KONZOLE_PEASANT = 80;
    public static final int PCMR = 20;
    
    private String nickname;
    private int sirkaPanelu = 1000;
    private int vyskaPanelu = 600;
    private int bullets, score;
    private ArrayList<Tvar> shots, droplets;
    private Tvar aktivni;
    private Timer casovac;
    
    private Obrazek postava;
    private Platforma pt;
    private boolean jump = false;
    private int drop;
    
    java.awt.Window menu;
    
    private JLabel lblScore, lblBullets, lblScoreCount, lblBulletsCount;
    
    public HerniPanel() {
        shots = new ArrayList<>();
        droplets = new ArrayList<>();
        drop = 1;
        lblScore = new JLabel("Skore:");
        lblBullets = new JLabel("Naboje:");
        lblScore.setBounds(26, 12, 52, 15);
        lblBullets.setBounds(855, 12, 104, 15);
        init();
        start();
        this.repaint();
    }
    
    public HerniPanel(java.awt.Window menu, String nickname) {
        this.nickname = nickname;
        this.menu = menu;
        shots = new ArrayList<>();
        droplets = new ArrayList<>();
        drop = 1;
        lblScore = new JLabel("Skore:");
        lblBullets = new JLabel("Naboje:");
        lblScore.setBounds(26, 12, 52, 15);
        lblBullets.setBounds(855, 12, 104, 15);
        init();
        start();
        this.repaint();
    }
    
    private void init() {
    	this.setPreferredSize(new Dimension(this.sirkaPanelu,this.vyskaPanelu));
        this.setSize(new Dimension(this.sirkaPanelu,this.vyskaPanelu));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.setLayout(null);
        this.add(lblScore);
        this.add(lblBullets);
        bullets = score = 0;
        casovac = new Timer(PCMR, this);
        casovac.setInitialDelay(700);
        casovac.start();
        addKeyListener(this);
        addMouseListener(this);
        pt = new Platforma(this, sirkaPanelu, vyskaPanelu);
        pt.setBarva(Color.gray);
        postava = new Obrazek(this, "slav_squat2.png", 450, 420);
        
        lblScoreCount = new JLabel("");
        lblScoreCount.setBounds(85, 12, 52, 15);
        this.add(lblScoreCount);
        
        lblBulletsCount = new JLabel("");
        lblBulletsCount.setBounds(918, 12, 70, 15);
        this.add(lblBulletsCount);
    }
    
    private void start() {
    	
    }
    
    private void restart(){
        droplets.clear();
        shots.clear();
        bullets = score = 0;
        lblScoreCount.setText("0");
        lblBulletsCount.setText("0");
        drop = 1;
        postava.setY(420);
        postava.setX(450);
        this.repaint();
        casovac.start();
        jump = false;
    }
    
    private void fire(){
    	if(bullets != 0){
    		shots.add(new Kruh(this, 10, postava.getX()+75, postava.getY()));
    		--bullets;
    		lblBulletsCount.setText(Integer.toString(bullets));
    	}
    }
    
    private void moveBullet(){
    	if(!shots.isEmpty()){
    		for(Iterator<Tvar> iterator = shots.iterator(); iterator.hasNext(); ) {
    			Tvar value = iterator.next();
    			value.setY(value.getY()-5); 
    			if(value.getY() <= 0 || collision(value)){
        			iterator.remove();
        		}	
        	}
        }
    }

    private void Drop(){
    	if(!droplets.isEmpty()){
	    	for(Iterator<Tvar> iterator = droplets.iterator(); iterator.hasNext(); ) {
	    		Tvar value = iterator.next();
	    		value.setY(value.getY()+((Ctverec) value).getSpeed());
	    		if(fellOnBoris(value))
                            break;
	    		if(value.getY() >= this.vyskaPanelu){
	    			iterator.remove();
	    			score += 50;
	    			lblScoreCount.setText(Integer.toString(score));
	    			if(score % 200 == 0){
	    				bullets += 3;
	    				lblBulletsCount.setText(Integer.toString(bullets));
	    			}
	    		}
	    		
	    	}
  
    	}
    }
    
    private boolean collision(Tvar c){
    	
    	for(Iterator<Tvar> iterator = droplets.iterator();iterator.hasNext();){
    		Tvar t = iterator.next();
    		if((c.getX() >= t.getX() && c.getX() <= t.getX()+((Ctverec)t).getRozmer()) && (c.getY() - t.getY() <=0)){
    			iterator.remove();
    			score += 50;
    			lblScoreCount.setText(Integer.toString(score));
    			if(score % 200 == 0){
    				bullets += 3;
    				lblBulletsCount.setText(Integer.toString(bullets));
    			}
    			return true;
    		}
    	}
    	return false;
    	
    }
    
    
    private boolean fellOnBoris(Tvar c){
    	if((c.getX() >= postava.hitbox.getX() && c.getX() <= postava.hitbox.getX()+postava.hitbox.getRozmer()) 
    			&& (c.getY() >= postava.hitbox.getY()) && (c.getY() <= postava.hitbox.getY() + postava.hitbox.getRozmer()) ){
    		casovac.stop();
                String[] options = new String[] { "Play Again", "Back to menu" };
    		int response = JOptionPane.showOptionDialog(this, "You died, western spy", "You died",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                if(response == 0){
                    restart();
                    return true;
                }
                else{
                    javax.swing.SwingUtilities.getWindowAncestor(HerniPanel.this).dispose();
                    menu.setVisible(true);
                    return true;
                }
    	}
        return false;
    }
    
    @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       for(Tvar o: shots){
    	   o.vykresli(g);
       }
       for(Tvar o: droplets){
    	   o.vykresli(g);
       }
       pt.vykresli(g);
       postava.vykresli(g);
    }


    
    @Override
    public void actionPerformed(ActionEvent ae) {
    	moveBullet();
    	Drop();
    	if(drop % 50 == 0){
    		droplets.add(new Ctverec(this, 30, (int) Math.round(Math.random()*9+1)));
    		drop = 1;
    	}
    	else
    		++drop;
    	
    	if(!postava.jump(jump))
        	jump = false;
        this.repaint(); // metoda, která překreslí panel
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    /*    aktivni = null;
        for( Tvar o : objekty ){
            if(o.detekujKurzor(e.getX(), e.getY())){
                System.out.println(o.getX() + " " + o.getY());
                aktivni = o;
                break;
            }
        }
        
        this.requestFocus(true);
        this.repaint();
        */
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println(e.getKeyCode());
    	switch(e.getKeyCode()){
        case KeyEvent.VK_LEFT:
            postava.moveLeft();
            break;
        case KeyEvent.VK_RIGHT:
            postava.moveRight();
            break;
        case KeyEvent.VK_UP:
        	jump = true;
            break;
        case KeyEvent.VK_DOWN:
        	fire();
            break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
