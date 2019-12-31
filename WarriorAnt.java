import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;


public class WarriorAnt extends AbstAnt
{
    //uncommented areas were already mentioned and commented under Spider subclass
    AntColonies colony;
    
    protected double energyThreshold = 50;
    public static long spawnTime = 3000; //spawn every 2.5 seconds
    Spider spiderTarget;
    WarriorAnt antTarget;

    Food prospect;

    protected double eatRate = 1;
    GreenfootImage dA = new GreenfootImage("DeadAnt2.png"); //dead ant image being imported
    
    List<Spider> spiderTargets;
    List<WarriorAnt> antTargets;
    
     public WarriorAnt(AntColonies c){
        colony = c;
     }
    
    public void act() 
    {
        if (state == AntState.IDLE){
            super.act();
            //if: energy is below a threshold value
            if (energy < energyThreshold){
                //look for some food
                List<Food> prospects = getObjectsInRange(sightRange, Food.class);
                //if there is food
                if(prospects.size() > 0){
                    //set to foraging  
                    prospect = prospects.get(0);
                    state = AntState.FORAGE;
                }
            }
            else{
                //else if: look for an ant to attack
                spiderTargets = getObjectsInRange(sightRange, Spider.class);
                antTargets = getObjectsInRange(sightRange, WarriorAnt.class);
                if (spiderTargets.size()>0){//if the list is not empty
                    spiderTarget = spiderTargets.get(0);
                    state = AntState.ATTACK;
                }
                else if (antTargets.size()>0){
                 antTarget = antTargets.get(0); 
                 if(isEnemy(this.colony, antTarget.colony)){
                 state = AntState.ATTACK;
                }
                }
            }
        }

        if(energy > 100){
            energy = 100;
            state = AntState.IDLE;
        }

        if(health > 100){
            health = 100;
        }

        else if(state == AntState.ATTACK){
            //if touching the target
            if(spiderTargets.size() > 0){
            if(spiderTarget.getWorld() == null){
                spiderTarget = null;
                state = AntState.IDLE;
            }
            else if(isTouching(Spider.class)){
                //calculate the damage done in the attack
                double damage = Greenfoot.getRandomNumber(attack);
                spiderTarget.takeDamage(damage);
            }
            else{ 
                turnTowards(spiderTarget.getX(), spiderTarget.getY());
                //move the ant
                move(speed);
            }
            }
            else if(antTargets.size() > 0){
              if(antTarget.getWorld() == null){
                antTarget = null;
                state = AntState.IDLE;
            }
            else if(isTouching(WarriorAnt.class)){
                //calculate the damage done in the attack
                double damage = Greenfoot.getRandomNumber(attack);
                antTarget.takeDamage(damage);
            }
            else{ 
                turnTowards(antTarget.getX(), antTarget.getY());
                //move the ant
                move(speed);
            }   
            }

        }

        else if(state == AntState.FORAGE){
            //if touching the target
            if(prospect.getWorld() == null){
                prospect = null;
                state = AntState.IDLE;
            }
            else if(isTouching(Food.class)){
                if(energy < 100){
                    eatFood();
                    prospect.removeEnergy(eatRate);

                }
                else {
                    
                    energy = 100;
                    health = 100;
                    state = AntState.CARRY;
                }
                //turn toward ant ==> turntowards
            }
            else{
                turnTowards(prospect.getX(), prospect.getY());
                //move the ant
                move(speed);
            }

        }
        else if(state == AntState.CARRY){
            if(prospect.getWorld() == null){
                prospect = null;
                state = AntState.IDLE;
            }
            else{
                collectFood();
            }
        }
        else if(state == AntState.STEAL){
            
        }
        else if(state == AntState.DEAD){
            //add a dead ant at the ants position and then remove the ant
            getWorld().addObject(new DeadAnt(), this.getX(), this.getY());
            if(isTouching(Spider.class)){
                AntArena.spiderKills++;
            }
            AntArena.antsAlive --;
            getWorld().removeObject(this);
        }
        
    } 
    
    //Methods : what the ant does
    
    //overriding the inherited move method o take off energy
    public void move(int distance){
        energy -= distance*0.3;
        takeDamage(distance);
        super.move(distance);
    }

    protected void eat(){
        
    }

    protected void attack(){
        
    }

    protected void track(){
        
    }

    protected void collectFood(){
       
        //turnTowards(getWorld().AntColony.getX(), getWorld().AntColony.getY()); 
        
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
        health -= damage*0.3;
        //if all the heath is gone
        if (health <= 0){
            //change it to dead
            state = AntState.DEAD;
        }
        if (energy <= 0){
            state = AntState.DEAD;   
        }
        //change it to dead

    }

}
