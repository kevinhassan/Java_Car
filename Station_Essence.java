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
    private String typesEssenceVendus[]={"Gasoil","Essence"};
    private int prixTypesEssences[]={1,2};
    int x = 600;
    int y = 600;
    ColorImage carImage = new ColorImage("Gas_Station.png");
    Ville ville;    

    public Station_Essence(Ville ville)
    {
        
        this.ville=ville;
        this.ville.canvas.add(carImage,this.x,this.y);
    }

    public int getPrix(String typeEssence)
    {
      //Pre: On suppose que le type d'essence est contenu dans le tableau
       int i = 0;
       String type = typesEssenceVendus[0];
       while(type != typeEssence || i< typesEssenceVendus.length)
       {
         i ++;
         type = typesEssenceVendus[i];
       }
       return prixTypesEssences[i];

    }
    public double achatEssence(int type, float argent)
    {
        return argent/this.prixTypesEssences[type];
    }
}
