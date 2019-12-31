import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AbstAnt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class AbstAnt extends Actor
{
   enum AntState{
       IDLE, HUNT, FEED, ATTACK, DEAD, TRACK, FOLLOW, FORAGE, RETURN, CARRY, STEAL
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
    protected AntState state = AntState.IDLE;
    protected HealthBar hb;//th health bar reference fo r the and/spider , protected: sub classes can inherit this and make it their own, but wont be used in other worlds 
    
    public static AntColonies colony;
    
    /**
     * Act - do whatever the AbstAnt wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //on the first act call the hb reference will be null, so make the health bar and add it to the world
        if(hb == null){
            hb = new HealthBar(this, (int)health);
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
    
    public static void colonyHome(AntColonies home){
        colony = home;
    }
    
    public AntColonies getHome() {
        return colony;
    }
    
    public static boolean isEnemy(AntColonies c1, AntColonies c2){
        //get a boolean to be returned saying true or to if the colonies match
        boolean isEnemy = false;
        if(c1 != c2){
        isEnemy = true;    
        
    }
    else{
        isEnemy = false;
    }
    return isEnemy;
    }
    
    protected abstract void eat();
    
    protected abstract void attack();
    
    protected abstract void track();
    
    protected abstract void collectFood();
    
    protected abstract void leaveTrail();
    
}
