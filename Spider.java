import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class Spider extends AbstSpider
{
    protected double energyThreshold = 45; //energy the species can have before it needs to find food

    CarrierAnt target;
    DeadAnt prospect;
    Ladybug prospectLB;

    public static long spawnTime = 5000; //spider spawns every 5 seconds
    protected double eatRate = 1; //how fast the species gains energy

    GreenfootSound spiderDying = new GreenfootSound("SpiderDying.mp3"); //adds music for spiders dying

    List<DeadAnt> prospects;
    List<Ladybug> prospectsLB;
    public void act() 
    {
        if (state == SpiderState.IDLE){
            super.act();
            //if: energy is below a threshold value
            if (energy < energyThreshold){
                //look for some food
                prospects = getObjectsInRange(sightRange, DeadAnt.class);
                prospectsLB = getObjectsInRange(sightRange, Ladybug.class);
                //if there is food
                if(prospects.size() > 0){
                    //set to foraging  
                    prospect = prospects.get(0);
                    state = SpiderState.FORAGE;
                }
                else if(prospectsLB.size() > 0){
                    //set to foraging  
                    prospectLB = prospectsLB.get(0);
                    state = SpiderState.FORAGE;
                }
            }
            else{
                //else if: look for an ant to attack
                List<CarrierAnt> targets = getObjectsInRange(sightRange, CarrierAnt.class);                 
                if (targets.size()>0){ //if the list is not empty
                    //get the first target on the list
                    target = targets.get(0); 
                    state = SpiderState.ATTACK;
                }
            }
        }

        if(energy > 100){ //incase the energy goes past 100, reset
            energy = 100;
            state = SpiderState.IDLE;
        }

        if(health > 100){ //same condition as energy
            health = 100;
        }

        else if(state == SpiderState.ATTACK){
            //if touching the target
            if(target.getWorld() == null){
                target = null;
                state = SpiderState.IDLE;

            }
            else if(isTouching(CarrierAnt.class)){
                //calculate the damage done in the attack
                double damage = Greenfoot.getRandomNumber(attack);
                //call the targets takeDamage method
                target.takeDamage(damage);//testing out increasing damage of spider

                //turn toward ant ==> turntowards
            }
            else{
                turnTowards(target.getX(), target.getY());
                //move the ant
                move(speed);
            }

        }

        else if(state == SpiderState.FORAGE){
            if(prospects.size() > 0){
            //if touching the target
            if(prospect.getWorld() == null){
                prospect = null;
                state = SpiderState.IDLE;
            }
            else if(isTouching(DeadAnt.class)){
                //if the max energy isnt reached
                if(energy < 100){
                    //eat food
                    eatFood();
                    //remove energy from the food at the same time
                    prospect.removeEnergy(eatRate);
                }
                else {
                    //reset the values incase they go over 100 and set state to IDLE
                    energy = 100;
                    health = 100;
                    state = SpiderState.IDLE;
                }
            }
            else{
                //turn toward ant ==> turntowards
                turnTowards(prospect.getX(), prospect.getY());
                //move the ant
                move(speed);
            }
        }
        else if(prospectsLB.size() > 0){
            if(prospectLB.getWorld() == null){
                prospectLB = null;
                state = SpiderState.IDLE;
            }
            else if(isTouching(Ladybug.class)){
                //if the max energy isnt reached
                if(energy < 100){
                    //eat food
                    eatFood();
                    //remove energy from the food at the same time
                    prospectLB.removeEnergy(eatRate);
                }
                else {
                    //reset the values incase they go over 100 and set state to IDLE
                    energy = 100;
                    health = 100;
                    state = SpiderState.IDLE;
                }
            }
            else{
                //turn toward ant ==> turntowards
                turnTowards(prospectLB.getX(), prospectLB.getY());
                //move the ant
                move(speed);
            }
        }
        }

        else if(state == SpiderState.DEAD){
            //decrease the amount of spiders by 1 and remove them from the world
            AntArena.spidersAlive --;
            if(isTouching(CarrierAnt.class)){
                AntArena.antKills++;   //increase the kills that ants have
            }
            getWorld().addObject(new DeadSpider(750), this.getX(), this.getY());
            getWorld().removeObject(this); 
            spiderDying.play(); //play dying sound

        }
    } 
    //Methods : what the ant does
    //overriding the inherited move method of take off energy
    public void move(int distance){
        energy -= distance*0.2; 
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

    }

    protected void leaveTrail(){

    }

    protected void eatFood(){
        energy+=eatRate;
        health+=eatRate;
    }

    protected void getEnergy(){

    }

    protected void getSize(){

    }

    protected void takeDamage(double damage){
        //remove the damage from the health
        health -= damage*0.2;
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
