import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AbstSpider here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class AbstSpider extends Actor
{
   enum SpiderState{
       IDLE, HUNT, FEED, ATTACK, DEAD, TRACK, FOLLOW, FORAGE, RETURN
    }
    //Properties : what an ant has
    protected int speed = 5;
    protected int size = 20;
    protected int sightRange = 200;
    protected double energy = 100;//the amount of energy, if < 0, it starves
    protected double health = 100;//if < 0, it dies
    protected int capacity = 100;//maximum capacity an ant can carry
    protected int weight = 20;//how much the ant presently weigh
    protected int attack = 5;
    protected SpiderState state = SpiderState.IDLE;
    protected HealthBarSpider hb;//th health bar reference fo r the and/spider , protected: sub classes can inherit this and make it their own, but wont be used in other worlds 
    
  
    
    
    public void act() 
    {
        //on the first act call the hb reference will be null, so make the health bar and add it to the world
        if(hb == null){
            hb = new HealthBarSpider(this, (int)health);
            getWorld().addObject(hb, getX(), getY() - getImage().getHeight()/2);//
        }
        //if the health bar has been made then update it
        else {
            hb.update();
         }
        
       
        turn(Greenfoot.getRandomNumber(60)-30);
        move(Greenfoot.getRandomNumber(5)+0);    
    }    
    
    
    //Methods : what the ant does
    
    protected abstract void eat();
    
    protected abstract void attack();
    
    protected abstract void track();
    
    protected abstract void collectFood();
    
    protected abstract void leaveTrail();
    
}
