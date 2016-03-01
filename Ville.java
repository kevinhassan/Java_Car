import comp102x.IO;
import comp102x.Canvas;
import comp102x.ColorImage;
/**
 * Cette classe contient la ville
 * 
 * @author Kévin Hasssan IG3 
 * @version 1.0
 */
public class Ville
{
    private String nom;
    Canvas canvas = new Canvas();
    Station_Essence station; 
    public Ville(String nom)
    {
      this.nom=nom;
      station = new Station_Essence(this);
    }
}