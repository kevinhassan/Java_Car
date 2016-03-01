import comp102x.IO;
import comp102x.Canvas;
import comp102x.ColorImage;

/**
 * Cette classe contient la station service 
 * 
 * @author Kévin Hasssan IG3 
 * @version 1.0
 */
public class Station_Essence
{
    // instance variables - replace the example below with your own
    private String typesEssenceVendus[]={"Diesel","Essence","Ethanol","Huile de Friture"};
    private int prixTypesEssences[]={1,3,1,2};
    int x= (int)(Math.random() * 600);
    int y= (int)(Math.random() * 600);
    ColorImage carImage = new ColorImage("Gas_Station.png");
    Ville ville;    

    public Station_Essence(Ville ville)
    {
        this.ville=ville;
    }
    public int getType(String typeEssence)
    { //Pre: On suppose que le type d'essence est contenu dans le tableau
       int i = 0;
       String type = typesEssenceVendus[0];
       while(type != typeEssence || i< typesEssenceVendus.length)
       {
         i ++;
         type = typesEssenceVendus[i];
       }
       return i;
    }
    
    public int getPrix(int type)
    {
        return this.prixTypesEssences[type];
    }
    
    public double achatEssence(int type, float argent)
    {
        return argent/this.getPrix(type);
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }    
}
