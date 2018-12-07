package com.hem.test.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class TextToGraphics {

	public static void drawText() throws MalformedURLException, IOException {
		 final BufferedImage image = ImageIO.read(new File("C:/Users/hsahu/Desktop/Hacathon-2018/IMG_PRO/OCR/data/text2.png"));

			    Graphics g = image.getGraphics();
			    Font f = new Font("Arial", 2, 20);
			    g.setFont(f);//g.getFont().deriveFont(30f));
			    
			    
			    g.drawString("Hello World!", 100, 100);
			    g.dispose();

			    ImageIO.write(image, "png", new File("test.png"));
	}
	
	public static String outDir = "c:/temp/rough/outImages";
	public static Integer counter = 0;
    public static void main(String[] args) throws MalformedURLException, IOException {
    	generateAadharCardImage("1234  3456  6789", "Surjeet Singh", "20-02-1996", "M");
    	generateAadharCardImage("1234  2336  6789", "Puneet Saha", "10-06-1993", "M");
    	generateAadharCardImage("2432  3456  6789", "Ankit Singh", "22-01-1989", "M");
    	generateAadharCardImage("1234  3456  6453", "Pratab Pandey", "21-04-1983", "M");
    	generateAadharCardImage("2334  3456  6789", "Kumar Sanu", "15-05-1981", "M");
    	generateAadharCardImage("1234  2353  6789", "Kaka Dave", "30-08-1986", "M");
    	generateAadharCardImage("2432  3456  6789", "Lakaan Kapoor", "22-11-1997", "M");
    	generateAadharCardImage("1234  3456  5464", "Surjeet Shah", "27-12-1977", "M");
    	
    	generateAadharCardImage("1234  3456  1234", "Shankar Watch", "20-12-1980", "M");
    	generateAadharCardImage("3456  3456  6789", "Sukumar Gupta", "13-06-1999", "M");
    	generateAadharCardImage("1234  3456  3234", "Ravi Singh", "12-03-2000", "M");
    	generateAadharCardImage("1234  7586  6789", "Ram Kumar", "17-06-1997", "M");
    	generateAadharCardImage("1234  3454  6789", "Inder Jha", "29-08-1986", "M");
    	generateAadharCardImage("4567  3456  6789", "Jhon Joseph", "14-09-1988", "M");
    	
    	/*generateAadharCardImage("1234  3456  6789", "Surjeet Singh", "20-02-1996", "M");
    	generateAadharCardImage("1234  3456  6789", "Surjeet Singh", "20-02-1996", "M");
    	generateAadharCardImage("1234  3456  6789", "Surjeet Singh", "20-02-1996", "M");
    	generateAadharCardImage("1234  3456  6789", "Surjeet Singh", "20-02-1996", "M");
    	generateAadharCardImage("1234  3456  6789", "Surjeet Singh", "20-02-1996", "M");
    	generateAadharCardImage("1234  3456  6789", "Surjeet Singh", "20-02-1996", "M");*/
    }
    
    public static void generateAadharCardImage(String aaNo, String name, String DOB, String gender) throws IOException{    	
    	String template = "C:/Users/hsahu/Desktop/Hacathon-2018/IMG_PRO/OCR/data/AadharTemplet.png";
    	Integer imageNo = counter++;
    	String resultImage =  outDir + "/Test-" + imageNo + ".png";
    	
    	Font font = new Font("Verdana", Font.PLAIN, 52);
    	drawHelloWorld(template,resultImage, aaNo, font, 360, 310); 
    	
    	font = new Font("Verdana", Font.PLAIN, 28);
    	drawHelloWorld(resultImage,resultImage,name, font, 240, 511);
    	drawHelloWorld(resultImage,resultImage,DOB, font, 240, 570);
    	drawHelloWorld(resultImage,resultImage,gender, font, 806, 570);
    }
    
    public static void drawHelloWorld(String srcFile, String tgtFile,
    						String content, Font f, int x, int y ) throws IOException {    	


    	BufferedImage img = ImageIO.read(new File(srcFile));
    	Graphics2D g2d = img.createGraphics();
    	
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        
        g2d.setFont(f);
        g2d.setColor(Color.BLACK);
        
        g2d.drawString(content, x, y);
        
        g2d.dispose();
        
        try {
            ImageIO.write(img, "png", new File(tgtFile));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}