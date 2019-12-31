import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartScreen extends World
{
    AntArena arena;
    /**
     * Constructor for objects of class StartScreen.
     * 
     */
    public StartScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 750, 1); 
        addObject(new StartButton(), getWidth()/2, getHeight()/3);
        addObject(new InstructionsButton(), getWidth()/2, getHeight()-200);
    }

    public StartScreen(AntArena w){
        super(1000, 750, 1); 
        arena = w;
        addObject(new StartButton(), getWidth()/2, getHeight()/3);
        addObject(new InstructionsButton(), getWidth()/2, getHeight()-200);
    }

    public void act(){
        // if(Greenfoot.isKeyDown("space")){
            // Greenfoot.setWorld(arena);
        // }
        // else if(Greenfoot.isKeyDown("i")){
            // Greenfoot.setWorld(new Instructions(this));
        // }
        
        
    }

    
    
}
