import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Title : Gray scale Class
 * Description: Converts images to gray scale by using one of the 7 functions provided
 * @author Shehar Yar
 *
 */
public class GrayScale {

	//BufferedImage object
   BufferedImage  image;
   int width;
   int height;
   
   //Class Definition
   public GrayScale(String grayType) {
   
	   //if statements to decide which gray scale is being applied
	   
	   //Average method
	  if(grayType.equals("Average"))
	  {
		  
	      try {
	    	  //file object which is gathered from the GUI FileChooser
	         File input = new File(GUI.filename);
	         //image file as buffered image read by the specified file
	         image = ImageIO.read(input);
	         //integer values to gather the height and width of the image to go through each pixel
	         width = image.getWidth();
	         height = image.getHeight();
	         
	         //for loops goes through each pixel and grabs the RGB values of each pixel
	         for(int i=0; i<height; i++){
	         
	            for(int j=0; j<width; j++){
	            
	            	//color object and sets the values of the color by using getRGB function
	               Color c = new Color(image.getRGB(j, i));
	               //gets red color value from color object
	               int red = (int)(c.getRed());
	               //gets green color values from color object
	               int green = (int)(c.getGreen());
	               //gets blue color values from color object
	               int blue = (int)(c.getBlue());
	               //creates new color object and give the new color by using average method by adding the RGB and dividing by 3
	               Color newColor = new Color((red+green+blue)/3,(red+green+blue)/3,(red+green+blue)/3);
	               
	               //set new RGB values to i and j by getting the RGB values from the new color object.
	               image.setRGB(j,i,newColor.
	            		   getRGB());
	            }
	         }
	         
	         //Write file to output in main source folder
	         File ouptut = new File("Average.jpg");
	         ImageIO.write(image, "jpg", ouptut);
	         
	      } catch (Exception e) {}
	  }
	  if(grayType.equals("luma"))
	  {
		  
	      try {
	         File input = new File(GUI.filename);
	         image = ImageIO.read(input);
	         width = image.getWidth();
	         height = image.getHeight();
	         
	         for(int i=0; i<height; i++){
	         
	            for(int j=0; j<width; j++){
	            
	               Color c = new Color(image.getRGB(j, i));
	               //Luma Algorithm values for red green and blue
	               int red = (int)(c.getRed()*0.299);
	               int green = (int)(c.getGreen()*0.587);
	               int blue = (int)(c.getBlue()*0.114);
	               //applying the new luma values
	               Color newColor = new Color(red+green+blue,red+green+blue,red+green+blue);
	               
	               image.setRGB(j,i,newColor.
	            		   getRGB());
	            }
	         }
	         
	         File ouptut = new File("luma.jpg");
	         ImageIO.write(image, "jpg", ouptut);
	         
	      } catch (Exception e) {}
	  }
	  if(grayType.equals("Desaturation"))
	  {
		  //same comments as first if statement
	      try {
	         File input = new File(GUI.filename);
	         image = ImageIO.read(input);
	         width = image.getWidth();
	         height = image.getHeight();
	         
	         for(int i=0; i<height; i++){
	         
	            for(int j=0; j<width; j++){
	            
	               Color c = new Color(image.getRGB(j, i));
	               int red = (int)(c.getRed());
	               int green = (int)(c.getGreen());
	               int blue = (int)(c.getBlue());
	               // grab the total by using Max and Min and dividing them by 2 to grab the new gray Scale
	               int total = (Math.max(red, Math.max(green, blue))+Math.min(red, Math.min(green, blue)))/2;
	               //adding the total to the new color so it can be set to the image
	               Color newColor = new Color(
	            		   total,
	            		   total,
	            		   total);
	               
	               image.setRGB(j,i,newColor.
	            		   getRGB());
	            }
	         }
	         
	         File ouptut = new File("Desaturation.jpg");
	         ImageIO.write(image, "jpg", ouptut);
	         
	      } catch (Exception e) {}
	  }
	  if(grayType.equals("Decomposition"))
	  {
		  
	      try {
	         File input = new File(GUI.filename);
	         image = ImageIO.read(input);
	         width = image.getWidth();
	         height = image.getHeight();
	         //Choosing Min or Max by using JOptionPane
	         String ans = JOptionPane.showInputDialog("Enter Min or Max for Decomposition");
	         
	         for(int i=0; i<height; i++){
	         
	            for(int j=0; j<width; j++){
	            
	               Color c = new Color(image.getRGB(j, i));
	               int red = (int)(c.getRed());
	               int green = (int)(c.getGreen());
	               int blue = (int)(c.getBlue());
	               
	               //total set to 0
	               int total = 0;
	               //if max then get the maximum values from red , green, blue to total
	               if(ans.equals("Max"))
	               {
	            	   total = Math.max(red, Math.max(green, blue));
	               }
	               //if min then get the minimum values from red , green, blue to total
	               if(ans.equals("Min"))
	               {
	            	   total = Math.min(red, Math.min(green, blue));
	               }
	               //setting new color from total
	               Color newColor = new Color(
	            		   total,
	            		   total,
	            		   total);
	               
	               image.setRGB(j,i,newColor.
	            		   getRGB());
	            }
	         }
	         
	         File ouptut = new File("Decomposition.jpg");
	         ImageIO.write(image, "jpg", ouptut);
	         
	      } catch (Exception e) {}
	  }
	  if(grayType.equals("CustomGrayScale"))
	  {
		  
	      try {
	         File input = new File(GUI.filename);
	         image = ImageIO.read(input);
	         width = image.getWidth();
	         height = image.getHeight();
	         //Choosing number of shades
	         int NumberOfShades = Integer.parseInt(JOptionPane.showInputDialog("Enter a number between 2-256"));
	         
	         for(int i=0; i<height; i++){
	         
	            for(int j=0; j<width; j++){
	            
	               Color c = new Color(image.getRGB(j, i));
	               int red = (int)(c.getRed());
	               int green = (int)(c.getGreen());
	               int blue = (int)(c.getBlue());
	               
	               //conversing factor grabs the 255 shades over the number of shades chosen before
	               int conversionFactor = 255/(NumberOfShades-1);
	               //gets the average value of the red green blue
	               int averageValue = (red+green+blue)/3;
	               // total value gets the average value over the conversion factor plus the half and multiplies back again to conversion factor.
	               int total = (int) ((averageValue/conversionFactor)+0.5)*conversionFactor;
	               
	               Color newColor = new Color(
	            		   total,
	            		   total,
	            		   total);
	               
	               image.setRGB(j,i,newColor.
	            		   getRGB());
	            }
	         }
	         
	         File ouptut = new File("CustomGrayScale.jpg");
	         ImageIO.write(image, "jpg", ouptut);
	         
	      } catch (Exception e) {}
	  }
	  if(grayType.equals("Dithering"))
	  {
		  
	      try {
	         File input = new File(GUI.filename);
	         image = ImageIO.read(input);
	         //using floydSteinbergDithering function (I don't take credit for this function)
	         image = Dither.floydSteinbergDithering(image);
	         width = image.getWidth();
	         height = image.getHeight();
	         //choosing number of shades
	         int NumberOfShades = Integer.parseInt(JOptionPane.showInputDialog("Enter a number between 2-256"));
	         
	         for(int i=0; i<height; i++){
	         
	            for(int j=0; j<width; j++){
	            
	               Color c = new Color(image.getRGB(j, i));
	               int red = (int)(c.getRed());
	               int green = (int)(c.getGreen());
	               int blue = (int)(c.getBlue());
	               //same as above if statement
	               int conversionFactor = 255/(NumberOfShades-1);
	               int averageValue = (red+green+blue)/3;
	               int total = (int) ((averageValue/conversionFactor)+0.5)*conversionFactor;
	               
	               Color newColor = new Color(
	            		   total,
	            		   total,
	            		   total);
	               
	               image.setRGB(j,i,newColor.
	            		   getRGB());
	            }
	         }
	         File ouptut = new File("Dithering.jpg");
	         ImageIO.write(image, "jpg", ouptut);
	         
	      } catch (Exception e) {}
	  }
	  if(grayType.equals("SingleColorChannel"))
	  {
		  
	      try {
	         File input = new File(GUI.filename);
	         image = ImageIO.read(input);
	         width = image.getWidth();
	         height = image.getHeight();
	         //entering one of the colors
	         String ans = JOptionPane.showInputDialog("Single-Color-Channel: Enter one of the colors. red,green,blue");
	         
	         for(int i=0; i<height; i++){
	         
	            for(int j=0; j<width; j++){
	            
	               Color c = new Color(image.getRGB(j, i));
	               //get rgb values
	               int red = (int)(c.getRed());
	               int green = (int)(c.getGreen());
	               int blue = (int)(c.getBlue());
	               
	               int total = 0;
	               //if the answer equals the choices provided in JoptionPane
	               //then set total to the answer chosen by the user
	               if(ans.equals("red"))
	               {
	            	   total = red;
	               }
	               if(ans.equals("green"))
	               {
	            	   total = green;
	               }
	               if(ans.equals("blue"))
	               {
	            	   total = blue;
	               }
	               Color newColor = new Color(
	            		   total,
	            		   total,
	            		   total);
	               
	               image.setRGB(j,i,newColor.
	            		   getRGB());
	            }
	         }
	         
	         File ouptut = new File("SingleColorChannel.jpg");
	         ImageIO.write(image, "jpg", ouptut);
	         
	      } catch (Exception e) {}
	  }
	  
   
   
   
   }
   
}