import comp102x.Canvas;
/**
 * Write a description of class Ville here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ville
{
    // instance variables - replace the example below with your own
    private Car2 v1, v2;
    private Canvas canvas;
    private String name = "Unknown";
    private Station_Essence station1;
    /**
     * Constructor for objects of class Ville
     */
    public Ville(String nom)
    {
        // initialise instance variables
        name = nom;
        v1 = new Car2("g1");
        v1.makeTurn(45);
        v2 = new Car2("g2");
        v2.makeTurn(-30);
        station1 = new Station_Essence(["95 S/P","98 S/P"], [2,1]);
    }
    public void deplacerVoiture(int laquelle, int dist){
        if (laquelle == 1){
            v1.moveForward(dist);
        }
        else if (laquelle == 2){
            v2.moveForward(dist);
        }
        else {System.out.println("Error");}
    }
    
    public void sampleVille()
    {
        // put your code here
        Canvas canvas = new Canvas();
        canvas.add(v1.carImage,100,100);
        canvas.add(v2.carImage,400,400);
    }
}
