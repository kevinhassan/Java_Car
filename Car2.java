import comp102x.IO;
import comp102x.Canvas;
import comp102x.ColorImage;

/**
 * Write a description of class Cars here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Car2
{

    String proprietaire = "aucun";
    ColorImage carImage = new ColorImage("car-racing1-pink.png");
    //carImage.scale = 0.5;
    double consoEssence = 10.0;
    double essenceDansReservoir = 6.0;
    int x, y = 200;
    public Car2 () {}
    
    public Car2(String nomDuproprietaire) 
    {
        proprietaire = nomDuproprietaire;
        carImage = new ColorImage();
    }
    
    public Car2 (String nomDuproprietaire, double newconsoEssence) 
    {
        proprietaire = nomDuproprietaire;
        carImage = new ColorImage( );
        consoEssence = newconsoEssence;
    }
    public Car2 (int posX, int posY)
    {
        x = posX;
        y = posY;
    }


    // Deplacer de dist pixels une voiture dans sa direction actuelle
    public void moveForward(int dist)
    { 
        // Obtain the current rotation in degrees
        int rotationInDegrees  = carImage.getRotation();
        double rotationInRadians = rotationInDegrees*Math.PI/180; // <- valeur temporaire a remplacer
       
        // Computes the shift in the X and Y axes according to the rotation angle            
        double distX = dist * Math.cos(rotationInRadians);
        double distY = dist * Math.sin(rotationInRadians);
       
        // Move the car in both x and y directions with the correct distances
        // Notice that setX() and setY() take int as argument
        carImage.setX(carImage.getX() + (int)distX);
        carImage.setY(carImage.getY() + (int)distY);
        
        // Update the amount of gas in tank assumming that each unit of dist is 10m
        double distKm = dist / 100.0;
        double essenceUtilisee = distKm / 100.0 * consoEssence;
        
        if((essenceDansReservoir - essenceUtilisee) >= 0)
        {
            carImage.setX(carImage.getX() + (int)distX);
            carImage.setY(carImage.getY() + (int)distY);
            essenceDansReservoir = essenceDansReservoir - essenceUtilisee;
            IO.outputln("Amount of gas used: " + essenceUtilisee + ", gas remained: " + essenceDansReservoir);
        }        
    }
    
    public void makeTurn(int angleAdditionnel) 
    { 
        // Change l'orientation de la voiture depuis l'orientation actuelle
        carImage.setRotation(carImage.getRotation() + angleAdditionnel);
    }
    
    // addGas ajoute un volume d'essence indiqué au reservoir
     public void addGas(double essenceSupplementaire) {
         essenceDansReservoir = essenceDansReservoir + essenceSupplementaire;
    }
    
    public void Car2Demo()
    {
        Canvas canvas = new Canvas(); // declare une zone pour dessiner des objets
        canvas.add(carImage,x,y); // ajoute l'image de la voiture aux coordonnees indiquees
    }
    

    // Fait tourner une voiture (la vitesse est en pixels)
    // le cercle effectue par la voiture est d'autant plus grand que la vitesse est grande
    public void tournerEnRond(int vitesse) 
    { 
        int precision = 10;
        for (int nb = 1; nb <= precision; nb++) 
        {
            moveForward(vitesse);
            makeTurn(360/precision);
            try{ Thread.sleep(300); } 
            catch (Exception e) {} ;
        }
    }
    
    // Oriente une voiture dans la direction d'un objectif (dont les coordonnees sont donnees en param)
    public void faireFace(int xObj, int yObj) 
    {
            // Methode de http://www.gamefromscratch.com/post/2012/11/18/GameDev-math-recipes-Rotating-to-face-a-point.aspx
            double angle = Math.atan2(yObj - carImage.getY(), xObj - carImage.getX() );
            angle = angle * (180/Math.PI);
            carImage.setRotation((int)angle);
   }    
}