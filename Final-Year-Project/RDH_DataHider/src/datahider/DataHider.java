/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datahider;

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
public class DataHider extends Thread
{
    DataHiderFrame df;
    int data1[][];
    int data2[][];
    DataHider(DataHiderFrame de)
    {
        df=de;
    }
    public void run()
    {
       
        try
        {
            DatagramSocket ds=new DatagramSocket(8000) ;
            
            while(true)
            {
                byte data[]=new byte[60000];
                DatagramPacket dp=new DatagramPacket(data,0,data.length);
                ds.receive(dp);
                
                String str=new String(dp.getData()).trim();
                String req[]=str.split("#");
                if(req[0].equals("Data"))
                {
                    String g[]=req[1].split("@");
                    data1=new int[req.length-1][g.length];
                    df.con1=new int[req.length-1][g.length];
                    BufferedImage bi1=new BufferedImage(data1.length,data1[0].length,BufferedImage.TYPE_INT_RGB);
                    for(int i=1;i<req.length;i++)
                    {
                        String g1[]=req[i].split("@");
                        for(int j=0;j<g1.length;j++)
                        {
                            int h1=Integer.parseInt(g1[j].trim());
                            data1[i-1][j]=h1;
                            df.con1[i-1][j]=h1;
                            bi1.setRGB(i-1, j, h1);
                            System.out.print(" "+h1);
                        }
                        System.out.println();
                    }                    
                    /*BufferedImageOp op1 = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
                    BufferedImage gImg=op1.filter(bi1, null);
            
                    ImageIO.write(bi1, "png",new File("org1.png"));
                    ImageIO.write(gImg, "png",new File("gry1.png"));*/
            
                }// data
                
                
                if(req[0].equals("Data1"))
                {
                    String g[]=req[1].split("@");
                    data2=new int[req.length-1][g.length];
                    df.con2=new int[req.length-1][g.length];
                    
                    BufferedImage bi1=new BufferedImage(data2.length,data2[0].length,BufferedImage.TYPE_INT_RGB);
                    
                    for(int i=1;i<req.length;i++)
                    {
                        String g1[]=req[i].split("@");
                        for(int j=0;j<g1.length;j++)
                        {
                            int h1=Integer.parseInt(g1[j].trim());
                            data2[i-1][j]=h1;
                            df.con2[i-1][j]=h1;
                            bi1.setRGB(i-1, j, h1);
                        }
                    }  
                    BufferedImageOp op1 = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
                    BufferedImage gImg=op1.filter(bi1, null);
            
                    ImageIO.write(bi1, "png",new File("enc1.png"));
                    ImageIO.write(gImg, "png",new File("enc.png"));
                    df.jLabel3.setIcon(new ImageIcon("enc.png"));                   
                    df.jButton2.setEnabled(true);
                    
            
                }// data1
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
