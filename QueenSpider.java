import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Ant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class QueenSpider extends AbstSpider
{
    protected double energyThreshold = 50;
    public static long spawnTime = 1000;
    
    
    Food prospect;
    
    protected double eatRate = 1;
    
    /**
     * Act - do whatever the Ant wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        turn(Greenfoot.getRandomNumber(60)-30);
        move(Greenfoot.getRandomNumber(5)+0);
    } 
    
        //Methods : what the ant does
        
        //overriding the inherited move method o take off energy
   

    protected void eat(){
        
    }
    
    protected void attack(){
    }
    
    protected void track(){
        
    }
    
    protected void collectFood(){
        
    }
    
    protected void leaveTrail(){
        
    }
    
    protected void eatFood(){
        health+=eatRate;
        energy+=eatRate;
    }
    
    protected void getEnergy(){
        
    }
    
    protected void getSize(){
        
    }
    
    protected void takeDamage(double damage){
        //remove the damage from the health
        health -= damage*0.05;
        //if all the heath is gone
        if (health <= 0){
            //change it to dead
            state = SpiderState.DEAD;
        }
        if (energy <= 0){
         state = SpiderState.DEAD;   
        }
            //change it to dead
            
    }
    
    
}
