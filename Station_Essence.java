
/**
 * Write a description of class Station_Essence here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Station_Essence
{
    // instance variables - replace the example below with your own
    private String[] typesEssenceVendu;
    private int[] prixTypesEssences;
    /**
     * Constructor for objects of class Station_Essence
     */
    public Station_Essence(String[] typeEssence, int[] prix)
    {
        // initialise instance variables
        typesEssenceVendu = typeEssence;
        prixTypesEssences = prix;
    }
    public int getPrix(String typeEssence)
    {
       int i = 0;
       String type = typesEssenceVendu[0];
       while(type != typeEssence || i< typesEssenceVendu.length)
       {
           type = typesEssenceVendu[i];
       }
       return prixTypesEssences[i];

    }
    public int achatEssence(int type, float argent)
    {
        
    }
}