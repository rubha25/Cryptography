/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datahider;

/**
 *
 * @author seabirds
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DataHiderFrame df=new DataHiderFrame();
        df.setVisible(true);
        df.setResizable(false);
        df.setTitle("Data Hider");
        
        DataHider dh=new DataHider(df);
        dh.start();
    }
}
