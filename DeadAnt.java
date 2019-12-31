import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class DeadAnt extends AbstAnt
{
    public static long spawnTime = 5000; //how frequently the food spawns

    private static double decayRate = 0.25;//makes the dead and lose energy over time

    private double energy = 100; //variables for the dead ants features
    private double size = 30;
    private double weight = 10;

    public DeadAnt(){
        setEnergy();
    }

    public DeadAnt(double _energy){
        setEnergy(energy);
    }

    public void act() 
    {
        if (energy > 0){
            // make the energy decay
            removeEnergy(decayRate);
        }
        else {
            //remove the dead ant
            getWorld().removeObject(this);
        }
    }  

    public void removeEnergy(double amount){
        //remove energy
        energy -= amount;
        //change the size and weight of the food based on the new energy
        size = 10 + energy*0.2;
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

    protected void getEnergy(){

    }

    protected void getSize(){

    }

}
