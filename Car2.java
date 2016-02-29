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

    public void moveForward(int dist)
    { 
        // Obtain the current rotation in degrees
        int rotationInDegrees  = carImage.getRotation();
        
        // Tache 1 - Convertissez la rotation courante de degres en radians
        // Initialisez la variable "rotationInRadians" avec une valeur correcte.
        // ----------------------------------------------------
        // Ecrivez votre code ici :
        
        double rotationInRadians= Math.toRadians(rotationInDegrees); // <- valeur temporaire a remplacer
              // Tache 2 - Calculez la distance a parcourir en x et en y (en fonction de l'angle de rotation)
        // Initialisez les variables distX et distY avec une valeur correcte.
        // Voir enonce en pdf pour plus de details (cos, sin du module java Math)
        // ----------------------------------------------------
        // Ecrivez votre code ici :
                     
        double distX= dist * Math.cos(rotationInRadians),distY= dist * Math.sin(rotationInRadians); // <- valeurs temporaires a remplacer
        
        // ----------------------------------------------------
        
        
        // Move the car in both x and y directions with the correct distances
        // Notice that setX() and setY() take int as argument
        carImage.setX(carImage.getX() + (int)distX);
        carImage.setY(carImage.getY() + (int)distY);
        
        // Update the amount of gas in tank assumming that each unit of dist is 10m
        double distKm = dist / 100.0;
        double essenceUtilisee = distKm / 100.0 * consoEssence;
        essenceDansReservoir = essenceDansReservoir - essenceUtilisee;
        IO.outputln("Amount of gas used: " + essenceUtilisee + ", gas remained: " + essenceDansReservoir);
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
        canvas.add(carImage,200,200); // ajoute l'image de la voiture aux coordonnees indiquees
    }

}
