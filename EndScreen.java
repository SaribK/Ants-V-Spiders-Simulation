import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndScreen extends World
{

    /**
     * Constructor for objects of class EndScreen.
     * 
     */
    public EndScreen()
    {    
        
        super(600, 400, 1); 
        if(AntArena.spiderKills == 15){
            showText("Spiders Win", getWidth()/2, getHeight()/2);
        }
        else if(AntArena.antKills == 15){
            showText("Ants Win", getWidth()/2, getHeight()/2);
        }
    }
}
