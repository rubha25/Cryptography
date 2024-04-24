/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package owner;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author seabirds
 */
public class OwnerReceiver extends Thread
{
    OwnerFrame of;
    OwnerReceiver(OwnerFrame fe)
    {
        of=fe;
    }
    public void run()
    {
       
        try
        {
            DatagramSocket ds=new DatagramSocket(7000) ;
            
            while(true)
            {
                byte data[]=new byte[60000];
                DatagramPacket dp=new DatagramPacket(data,0,data.length);
                ds.receive(dp);
                
                String str=new String(dp.getData()).trim();
                String req[]=str.split("#");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
