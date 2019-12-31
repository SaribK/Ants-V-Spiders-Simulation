import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Instructions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Instructions extends World
{
    World world;

    public Instructions(World returnWorld)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 750, 1); 
        world = returnWorld;
        //Adding text to explain the instructions
        showText("PRESS O KEY TO GO BACK", getWidth()-200, 50);
        showText("ANTS VS SPIDERS", 150, 50);
        showText("INSTRUCTIONS", getWidth()/2, 50);
        showText("THE FOLLOWING PROGRAM DEPICTS AN INTERACTIVE SIMULATION BETWEEN ANTS AND SPIDERS", getWidth()/2, 200); 
        showText("THE FIRST SPECIES TO REACH 100 KILLS IS THE PREDOMINANT APEX PREDATOR IN SAID ECOSYSTEM", getWidth()/2, 250);
    }

    public void act(){
        if(Greenfoot.isKeyDown("o")){ //go back to previous screen if o key is pressed
            Greenfoot.setWorld(world);
        }
    }
}
