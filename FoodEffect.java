import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class PixelEffect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FoodEffect extends Actor
{
    public GifImage foodEffect = new GifImage("foodGIF.gif");
    GreenfootImage imageOne;
    boolean notImageOne;
    
    public FoodEffect()
    {
        List<GreenfootImage> images = foodEffect.getImages(); // get list of images
        imageOne = images.get(0); // get reference to first image
        for(GreenfootImage img : images) img.scale(75, 75); // scale all images
        
        setImage(foodEffect.getCurrentImage()); // set initial image
    }
    public void act() 
    {
         if(foodEffect != null){
         setImage(foodEffect.getCurrentImage());
         }
        if ((getImage() != imageOne) != notImageOne) // was there a change involving first image
        {
            notImageOne = ! notImageOne; // record change
            if (! notImageOne) getWorld().removeObject(this); // if setting first image, remove explosion from world
        }
    }    
}