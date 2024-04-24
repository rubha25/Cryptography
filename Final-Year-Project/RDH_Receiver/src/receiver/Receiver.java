/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package receiver;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author seabirds
 */
public class Receiver extends Thread
{
    ReceiverFrame rf;
    int data1[][];
    int data2[][];
    Receiver(ReceiverFrame re)
    {
        rf=re;
    }
    
    public void run()
    {
       
        try
        {
            DatagramSocket ds=new DatagramSocket(9000) ;
            
            while(true)
            {
                byte data[]=new byte[60000];
                DatagramPacket dp=new DatagramPacket(data,0,data.length);
                ds.receive(dp);
                
                String str=new String(dp.getData()).trim();
                String req[]=str.split("#");
                if(req[0].equals("Image"))
                {
                    String g[]=req[2].split("@");
                    rf.msg=req[1];
                    data1=new int[req.length-2][g.length];
                    rf.img1=new int[req.length-2][g.length];
                    BufferedImage bi1=new BufferedImage(data1.length,data1[0].length,BufferedImage.TYPE_INT_RGB);
                    for(int i=2;i<req.length;i++)
                    {
                        String g1[]=req[i].split("@");
                        for(int j=0;j<g1.length;j++)
                        {
                            int h1=Integer.parseInt(g1[j].trim());
                            data1[i-2][j]=h1;
                            rf.img1[i-2][j]=h1;
                            bi1.setRGB(i-2, j, h1);
                            System.out.print(" "+h1);
                        }
                        System.out.println();
                    }                    
                    /*BufferedImageOp op1 = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
                    BufferedImage gImg=op1.filter(bi1, null);
            
                    ImageIO.write(bi1, "png",new File("org1.png"));
                    ImageIO.write(gImg, "png",new File("gry1.png"));*/
            
                }// data
                
                
                if(req[0].equals("Image1"))
                {
                    String g[]=req[2].split("@");
                    rf.msg=req[1];
                    data2=new int[req.length-2][g.length];
                    rf.img2=new int[req.length-2][g.length];
                    
                    BufferedImage bi1=new BufferedImage(data2.length,data2[0].length,BufferedImage.TYPE_INT_RGB);
                    
                    for(int i=2;i<req.length;i++)
                    {
                        String g1[]=req[i].split("@");
                        for(int j=0;j<g1.length;j++)
                        {
                            int h1=Integer.parseInt(g1[j].trim());
                            data2[i-2][j]=h1;
                            rf.img2[i-2][j]=h1;
                            bi1.setRGB(i-2, j, h1);
                        }
                    }  
                    BufferedImageOp op1 = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
                    BufferedImage gImg=op1.filter(bi1, null);
            
                    ImageIO.write(bi1, "png",new File("enc1.png"));
                    ImageIO.write(gImg, "png",new File("enc.png"));
                    rf.jLabel2.setIcon(new ImageIcon("enc.png"));                   
                    rf.jButton1.setEnabled(true);
                    
            
                }// data1
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
