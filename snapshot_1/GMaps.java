
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
 
public class GMaps extends JEditorPane{
     
    private float longitude;
    private float latitude;
 
    private static final long serialVersionUID = 1L;
    private int zoomFactor = 7;
    private String ApiKey = "AIzaSyBFRDVIFPpSvD2pZSwpLF61V_2h1D_azEQ";
    private String roadmap = "roadmap";
    public final String viewTerrain = "terrain";
    public final String viewSatellite = "satellite";
    public final String viewHybrid = "hybrid";
    public final String viewRoadmap = "roadmap";
    private String bouton = "";
    private String cursor ="";
    private String line ="";
 
    public GMaps(float longitude,float latitude)
    {
        HTMLEditorKit kit = new HTMLEditorKit();
        HTMLDocument htmlDoc = (HTMLDocument) kit.createDefaultDocument();
        this.setEditable(false);
        this.setContentType("text/html");
        this.setEditorKit(kit);
        this.setDocument(htmlDoc);
        this.setLongitude(longitude);
        this.setLatitude(latitude);
 
    }/* C'EST LA FONCTION POUR RAJOUTER DES CURSEUR*/
 
    public void addCursor(String color,String lettre,float clongitude,float clatitude)
    {
        cursor +="&markers=color:"+color+"%7Clabel:"+lettre+"%7C"+clatitude+","+clongitude;
    }
    
/* C'EST LA FONCTION POUR RAJOUTER DES ligne sur ta carte*/
    public void addLine(String color,float llongitude1, float llatitude1,float llongitude2,float llatitude2) {
        line +="&path=color:0x0000ff|weight:5|"+llatitude1+","+llongitude1+"|"+llongitude2+","+llatitude2;
    }
    /**
     * Fixer le zoom
     * @param zoom valeur de 0  21
     */
    public void setZoom(int zoom) {
        this.zoomFactor = zoom;
    }
    public int getZoom() {
        return zoomFactor;
    }
 
    /**
     * Fixer la cl de developpeur
     * @param key APIKey fourni par Google
     */
    public void setApiKey(String key) {
        this.ApiKey = key;
    }
 
    /**
     * Fixer le type de vue
     * @param roadMap
     */
    public void setRoadmap(String roadMap) {
        this.roadmap = roadMap;
    }
 
    /**
     * Afficher la carte d'aprs des coordonnes GPS
     * @param latitude
     * @param longitude
     * @param width
     * @param height
     * @throws Exception erreur si la APIKey est non renseigne
     */
    public void showCoordinate(int width, int height) throws Exception {
        this.setMap(Float.toString(latitude),Float.toString(longitude) , width, height);
    }
 
    /**
     * Afficher la carte d'aprs une ville et son pays
     * @param city
     * @param country
     * @param width
     * @param height
     * @throws Exception erreur si la APIKey est non renseigne
     */
    public void showLocation(String city, String country, int width, int height) throws Exception {
        this.setMap(city, country, width, height);
    }
 
   /**
    *
    * @param x
    * @param y
    * @param width
    * @param height
    * @throws Exception
    */
    private void setMap(String x, String y, Integer width, Integer height) throws Exception {
        if (this.ApiKey.isEmpty()) {
            throw new Exception("Developper API Key not set !!!!");
        }
 
        String url = "http://maps.google.com/maps/api/staticmap?";
        url += "center=" + x + "," + y;
        url += "&amp;zoom=" + this.zoomFactor;
        url += "&amp;size=" + width.toString() + "x" + height.toString();
        url += "&amp;maptype=" + this.roadmap;
        url += cursor;
        url += line;
        url += "&amp;sensor=false";
        String html = "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>";
        html += "<html><head></head>";
        html +=    "<body><img src='" + url + "'>";
        html += "</body></html>";
        this.setText(html);
    
    }
    public void setLongitude(float longitude) {this.longitude = longitude;}
    public float getLongitude() {return longitude;}
    public void setLatitude(float latitude) {this.latitude = latitude;}
    public float getLatitude() {return latitude;}
    
    public static void main(String[] args) {
        GMaps googleMap = new GMaps(43.919622f,2.137612f);
        try {
            googleMap.setApiKey("maCleGoogleMap");
            //  googleMap.setRoadmap(googleMap.viewHybrid);
 
            /**
            Afficher la ville de Strabourg
             */
            googleMap.showLocation("strasbourg", "france", 390, 400);
            /**
             * Afficher Paris en fonction ses coordonnées GPS
             */
            //  googleMap.showCoordinate("48.8667", "2.3333",390, 400);
        } catch (Exception ex) {
            Logger.getLogger(GMaps.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(googleMap);
        frame.setSize(400, 420);
        frame.setLocation(200, 200);
        frame.setVisible(true);
    }

}