
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Food here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Food extends Actor
{
    public static long spawnTime = 1500; //if STATIC, your accessing it specific throught the class
    
    private static double decayRate = 0.1;//let try this to make the food lose energy over time
    private double energy = 100;
    private double size = 30;
    private double weight = 10;
    
    public Food(){
        setEnergy();
    }
    
    public Food(double _energy){
        setEnergy(energy);
    }
    
    /**
     * Act - do whatever the Food wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (energy > 0){
            // make the energy decay
            removeEnergy(decayRate);
        }
        else {
            getWorld().addObject(new FoodEffect(), this.getX(), this.getY());
            getWorld().removeObject(this);
        }
    }  
    
    public void removeEnergy(double amount){
        //remove energy
        energy -= amount;
        // change the size and weight of the food based on the new energy
        size = 10 + energy*0.2;
        
        //getImage().scale((int)size, (int)size); (moved to setSize())
        setEnergy();
    }
    
 
    
    protected void setEnergy(){
        size = 10 + energy*0.2;
        weight = 5 + energy*100;
        getImage().scale((int)size, (int)size);
    }
    
    protected void setEnergy(double energy){
        this.energy = energy;
        setEnergy();
    }
    
    public boolean stillAvailable(){
        return getWorld() != null;
    }
}
