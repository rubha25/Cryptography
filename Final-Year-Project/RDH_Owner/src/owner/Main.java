/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package owner;

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
        OwnerFrame of=new OwnerFrame();
        of.setVisible(true);
        of.setTitle("Data Owner");
        of.setResizable(false);
        
        OwnerReceiver or=new OwnerReceiver(of);
        or.start();
    }
}
