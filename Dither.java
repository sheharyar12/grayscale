import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
 
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
 
/**
 * Title : Dither Class 
 * *********************************I DO NOT TAKE CREDIT FOR THIS CLASS****************************************
 * Source : https://gist.github.com/naikrovek/643a9799171d20820cb9
 * Description: Converts images to dither state but not to gray scale
 *
 */
class Dither {
    static class C3 {
    	// RGB Values , global variables
        int r, g, b;
 
        /**
         * Title : C3
         * Description: Get RGB values from color and set it to the global variables
         * @param c
         */
        public C3(int c) {
            Color color = new Color(c);
            r = color.getRed();
            g = color.getGreen();
            b = color.getBlue();
        }
 
        /**
         * Title C3
         * Description : get rgb values from an image and set those values to global RGb variables
         * @param r
         * @param g
         * @param b
         */
        public C3(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }
 
        /**
         * Title : C3
         * Description : grabs the color values from C3 function and adds the same values to global variables
         * @param o
         * @return new C3 values
         */
        public C3 add(C3 o) {
            return new C3(r + o.r, g + o.g, b + o.b);
        }
 
        /**
         * Title : clamp
         * Description: uses Max function to get the maximum from 0 
         * to the minimum values from 255 pixels and the color thats passed through
         * @param c
         * @return
         */
        public int clamp(int c) {
            return Math.max(0, Math.min(255, c));
        }
 
        /**
         * Title : diff
         * Description : subtracts the values from the global values from the RGB.
         * Add the diffrence squared from RGB and return the new distance squared.
         * @param o
         * @return
         */
        public int diff(C3 o) {
            int Rdiff = o.r - r;
            int Gdiff = o.g - g;
            int Bdiff = o.b - b;
            int distanceSquared = Rdiff * Rdiff + Gdiff * Gdiff + Bdiff * Bdiff;
            return distanceSquared;
        }
 
        
        public C3 mul(double d) {
            return new C3((int) (d * r), (int) (d * g), (int) (d * b));
        }
 
        public C3 sub(C3 o) {
            return new C3(r - o.r, g - o.g, b - o.b);
        }
 
        public Color toColor() {
            return new Color(clamp(r), clamp(g), clamp(b));
        }
 
        public int toRGB() {
            return toColor().getRGB();
        }
    }
 
    /**
     * Title findClosestpaletteColor
     * Description: Looks from the Palette around the pixel and grabs them and return those Palette.
     * @param c
     * @param palette
     * @return
     */
    private static C3 findClosestPaletteColor(C3 c, C3[] palette) {
        C3 closest = palette[0];
 
        for (C3 n : palette) {
            if (n.diff(c) < closest.diff(c)) {
                closest = n;
            }
        }
 
        return closest;
    }
 
    /**
     * Title : floydSteinbergDithering
     * description: Converts image to Dithering image
     * @param img
     * @return
     */
    public static BufferedImage floydSteinbergDithering(BufferedImage img) {
 
        C3[] palette = new C3[] { 
                new C3(  0,   0,   0), // black
                new C3(  0,   0, 255), // green
                new C3(  0, 255,   0), // blue
                new C3(  0, 255, 255), // cyan
                new C3(255,   0,   0), // red
                new C3(255,   0, 255), // purple
                new C3(255, 255,   0), // yellow
                new C3(255, 255, 255)  // white
        };
 
        //heigh and width of the image
        int w = img.getWidth();
        int h = img.getHeight();
 
        C3[][] d = new C3[h][w];
 
        //grab the RGB colors and put it in a 2 d array
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                d[y][x] = new C3(img.getRGB(x, y));
            }
        }
 
        // for loop to make the image dither style
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
 
            	//the old color object 
                C3 oldColor = d[y][x];
                //find the closest palettes from the color
                C3 newColor = findClosestPaletteColor(oldColor, palette);
                //set the new image to from the new color RGB
                img.setRGB(x, y, newColor.toColor().getRGB());
 
                C3 err = oldColor.sub(newColor);
 
                //if statement to grab the palletes and convert the image to dither style
                if (x + 1 < w) {
                    d[y][x + 1] = d[y][x + 1].add(err.mul(7. / 16));
                }
                
                if (x - 1 >= 0 && y + 1 < h) {
                    d[y + 1][x - 1] = d[y + 1][x - 1].add(err.mul(3. / 16));
                }
                
                if (y + 1 < h) {
                    d[y + 1][x] = d[y + 1][x].add(err.mul(5. / 16));
                }
                
                if (x + 1 < w && y + 1 < h) {
                    d[y + 1][x + 1] = d[y + 1][x + 1].add(err.mul(1. / 16));
                }
            }
        }
 
        return img;
    }
 

}