import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class AntColony1 extends AntColonies
{
    protected ArrayList <CarrierAnt> ants = new ArrayList<CarrierAnt>();
    static int energy;
    SimpleTimer antSpawnTimer = new SimpleTimer();
    protected String name;
    public AntColony1(String n, int e)
    {
        name = n; //set the colonies name
        energy = e;
    }

    public void act() 
    {
        if(antSpawnTimer.millisElapsed() > CarrierAnt.spawnTime){
            
            if(this.getWorld() != null){
                CarrierAnt ant = new CarrierAnt(this);
                WarriorAnt antW = new WarriorAnt(this);
                getWorld().addObject(ant, this.getX(), this.getY());
                getWorld().addObject(antW, this.getX(), this.getY());
                AntArena.antsAlive+=2; //increases stats of living ants
                
                ants.add(ant); //ants.add(antW);
                energy -= 200; //lose energy every time ant is added
                
                if(energy <= 0){
                    getWorld().removeObject(this);
                }   
            }
        
        antSpawnTimer.mark();
    }
}    
}
