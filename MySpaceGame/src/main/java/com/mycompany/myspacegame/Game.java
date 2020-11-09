/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myspacegame;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Caleb
 */
public class Game extends Canvas implements Runnable {
/*with runnable comes the need to implement specific methods like run*/
    
    //attributs
    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH / 12*9;
    public static final int SCALE = 2;
    public final String TITLE = " Space color Game ";
    private boolean running = false;
    private Thread thread;

    //methods
    private synchronized void start(){
    /* while dealing with thread we want to use synchronize*/
        if (running)//if running is already true we don't want to make it true again
            return;// so we want to get out of the method start if running is already true
        running = true;
        thread = new Thread(this);
        thread.start();// this method start is included in the class Thread it will basically start the process. start the thread
    }
    
    private synchronized void stop(){
        if(!running) // if running is already at false then get out this method if not set running at false to allow stopping the game
            return;
        running = false; // to stop the game loop
        
        try{ // Joining all the thread together can fail so we need a try catch
            thread.join(); // so thread.join will join all the thread together and will wait for it to die.
        }catch(InterruptedException e ){
            e.printStackTrace();
        }
        
        System.exit(1);
    }
    
    public void run(){
    /* The game loop the heart of the game*/
 
    while(running){
        System.out.println("WORKING");
    }
    stop();
    }
    
    public static void main(String[] args){
        
        // all this part basicaly to set the size of the game window.
        Game game = new Game();
        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        
        //all this part to configure paramaters of thegame window.
        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();
        
    }

}
