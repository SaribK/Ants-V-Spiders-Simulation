//ANTS VS SPIDERS, MARCH 7 2019, REVISION 11
//SARIB KASHIF, KARAN SWATCH, BROOKLYN GUO
//PUT WHAT YOU DID

/*
Critical requirements __/ 10   7
Appropriate commenting
Colony class that spawns ant !!!
Ants recognize friendly versus enemy ants !!!
Ant return food to colony
Colony has an energy amount!!!
Colony uses energy to spawn ants !!!
Start screen, stats screen and end screen are function and appropriately called !!!
Appropriate stats are stored !!!
Ants attack the closest enemy !!!
Ants harvest the closest food

Optional requirements (__/8)   4
In game stats !!!
Updated UML diagrams
Animation !!!
Specialized Ants (warrior ants, carrier ants)
Attempt to balance spiders
Attempt to balance specialized ants
User controlled ants
Dead spiders become food !!!
Lady bugs become food !!!
Power up food
Ants have elaborate behaviors
Use of pheromone trails
Magical items of an impressive quality
 */



//ERROR LISTING/NOTES FOR FUTURE
/*
 * encountered error when warrior ant was made subclass of ant
 * use array of ants (ant[0] = carrier ant, ant[1] = warrior ant, etc)
 * got warrior ants, need carrier ants
 */


import greenfoot.*;

public class AntArena extends World
{
    SimpleTimer fSpawnTimer1 = new SimpleTimer(); //adding distinct timers for ants, food, and spiders
    
    SimpleTimer fSpawnTimer3 = new SimpleTimer();
    
    GreenfootSound backGround = new GreenfootSound("Background.mp3"); //loading background and end music
    GreenfootSound endMusic = new GreenfootSound("EndMusic.mp3");
    
    StartScreen startScreen; 
    
    QueenSpider queenSpider = new QueenSpider(); 
    
    
    int numAnts = 20;
    int numSpider = 5;
    public static int antsAlive = 0;
    public static int spidersAlive = 0;
    public static int antKills = 0;
    public static int spiderKills = 0;
    int numFood = 4;
    int numLB = 20;
    
    String colony1 = "Colony 1";
    String colony2 = "Colony 2";
   
    
    
    int colonyEnergy = 1000;
    
    AntColony1 antColony1 = new AntColony1(colony1, colonyEnergy);
    AntColony2 antColony2 = new AntColony2(colony1, colonyEnergy);
    
    public AntArena()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 750, 1); 
        addObject(new DeadAnt(1000), getWidth()/2, getHeight()/2);
        startScreen = new StartScreen(this);
        Greenfoot.setWorld(startScreen);
        //make an array of ant colonies
        addObject(antColony1, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        addObject(antColony2, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        
       // addObject(queenSpider, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        
        for (int i = 0; i < numFood; i++){
            addObject(new Food(500), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }
        for (int i = 0; i < numLB; i++){
          //  addObject(new Ladybug(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }
    }
    
   public void stopped(){
       backGround.pause();
    }
    
    public void act(){
        backGround.playLoop();
        backGround.setVolume(30);
        if(Greenfoot.isKeyDown("i")){
            Greenfoot.setWorld(new Instructions(this)); 
            backGround.pause();
        }
        //if the food spawntimer has enough timer 
        if(fSpawnTimer1.millisElapsed() > Food.spawnTime){
            //then spawn in another food
            //actually adding the object to the world
            addObject(new Food(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
            //actually adding the object to the world
            fSpawnTimer1.mark();
        }
        if(fSpawnTimer3.millisElapsed() > Spider.spawnTime){
            //spawn ants in every second (i have spawnTime set to 1000) at the coordinate 400, 300
      
            //addObject(new Spider(), queenSpider.getX(), queenSpider.getY());
            spidersAlive++;
            fSpawnTimer3.mark();
        }
        //show stats
        showText("Ants Alive: " + antsAlive, 100, 50);
        showText("Ant Kills: " + antKills, 100, 100);
        showText(colony1 + " Energy: " + AntColony1.energy, 100, 150);
        showText("Spiders Alive: " + spidersAlive, getWidth() - 100, 50);
        showText("Spider Kills: " + spiderKills, getWidth() - 100, 100);  
        
        if(spiderKills == 15 || antKills == 15){
            Greenfoot.setWorld(new EndScreen());
            backGround.pause();
        }
        
        
        
        processKeys();
        
    }

    private void processKeys(){
        if (Greenfoot.isKeyDown("o")){
            HealthBar.visible = false;
        }
        else if(Greenfoot.isKeyDown("p")){
            HealthBar.visible = true;
        }
    }
}
