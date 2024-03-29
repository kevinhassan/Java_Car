import comp102x.IO;
import comp102x.Canvas;
import comp102x.ColorImage;
import java.math.*;
/**
 * Cette classe contient les voitures du canvas Ville
 * 
 * @author Kévin Hasssan IG3 
 * @version 1.0
 */
public class Car2
{

    String proprietaire = "aucun";
    ColorImage carImage = new ColorImage("car-racing1-pink.png");
    ColorImage jerricanImage = new ColorImage("fuel.png");
    //carImage.scale = 0.5;
    double consoEssence = 10.0;
    double essenceDansReservoir = 0.1;
    int x, y = 200;
    private Ville ville; // A une voiture on associe une ville 
    float argent = 100;
    String typeMoteur = "Essence";
    
    public Car2 (Ville ville) 
    {
        this.ville = ville; //On associe la ville créée dans le constructeur de voiture
    }
    
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
        this.x = posX;
        this.y = posY;
    }

    // Deplacer de dist pixels une voiture dans sa direction actuelle
    public void moveForward(int dist)
    { 
        // Obtain the current rotation in degrees
        int rotationInDegrees  = carImage.getRotation();
        double rotationInRadians = rotationInDegrees*Math.PI/180; // <- valeur temporaire a remplacer
    
        // Update the amount of gas in tank assumming that each unit of dist is 10m
        double distKm = 0;
        double essenceUtilisee = 0;
        
        int i = 0;
        
        while(essenceUtilisee < essenceDansReservoir && i < dist && essenceDansReservoir >= 0)
        {
            i++;
            distKm = i /100.0;
            essenceUtilisee = distKm/100 * consoEssence;
        }  
        // Computes the shift in the X and Y axes according to the rotation angle            
        double distX = i * Math.cos(rotationInRadians);
        double distY = i * Math.sin(rotationInRadians);

        carImage.setX(carImage.getX() + (int)distX);
        carImage.setY(carImage.getY() + (int)distY);
        essenceDansReservoir = essenceDansReservoir - essenceUtilisee;
        IO.outputln("Amount of gas used: " + essenceUtilisee + ", gas remained: " + essenceDansReservoir);
        if(essenceDansReservoir <= 0)
        {
            this.ville.canvas.add(jerricanImage,carImage.getX() + carImage.getWidth(),carImage.getY());// ajoute l'image de jerrican juste à côté de la voiture
        }
    }
    
    public void makeTurn(int angleAdditionnel) 
    { 
        // Change l'orientation de la voiture depuis l'orientation actuelle
        carImage.setRotation(carImage.getRotation() + angleAdditionnel);
    }
    
    // addGas ajoute un volume d'essence indiqué au reservoir
     public void faireLePlein(double essenceSupplementaire) 
    {
        if(pretDeLaStation() && essenceSupplementaire >= this.ville.station.achatEssence(this.ville.station.getType(typeMoteur),argent))
        {
            essenceDansReservoir = essenceDansReservoir + essenceSupplementaire;
            argent -= essenceSupplementaire * this.ville.station.getPrix(this.ville.station.getType(typeMoteur));
            if(essenceDansReservoir >= 0)
            {
               this.ville.canvas.remove(jerricanImage);
            }
        }
        else if (!pretDeLaStation())
        {
            IO.outputln("Vous n'êtes pas sur une station service ! Good Luck !");
        }
        else
        {
            IO.outputln(" Vous n'avez pas suffisament d'argent pour remplir le réservoir  ");
        }
    }
    
    public void Car2Demo()
    {
        this.ville.canvas.add(carImage,this.x,this.y); // On ajoute l'image dans le canevas 
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
   public void atteindre(int x, int y)
   {
       this.faireFace(x,y);
       if(x-carImage.getX() == 0) // Si c'est un déplacement verticale
        this.moveForward(Math.abs(y-carImage.getY()));
       else
        this.moveForward(Math.abs(x-carImage.getX())); // Si c'est un déplacement horyzontale
   } 
   public void rejoindreStation()
   {
        this.atteindre(this.ville.station.getX(),this.ville.station.getY());
   }
   public boolean pretDeLaStation()
   {
        return this.carImage.getX() == this.ville.station.getX() &&  this.carImage.getY() == this.ville.station.getY();
   }
}