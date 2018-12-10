package com.hem.imagej;

import java.io.File;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.OvalRoi;
import ij.gui.Roi;
import ij.plugin.ContrastEnhancer;
import ij.plugin.Selection;
import ij.process.ByteProcessor;
import ij.process.ColorProcessor;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;

public class TestImageJ {

	static String arialImage = "/C:/Users/hsahu/Desktop/Hacathon-2018/IMG_PRO/ImagePreProcessing/DATA/DOTA/part1/images/P0096";
	
	public static void main(String [] args) {
		/*String imageFile = "C:/temp/rough/images/ninja.jpg";
		save(imageFile);*/
		arialmageProcess(null);
		createStaticMask(null);
	}
	
	public static void showAnImage() {
		int width = 400;  
		int height = 400;  
		ImageProcessor ip = new ByteProcessor(width, height);  
		String title = "My new image";  
		ImagePlus imp = new ImagePlus(title, ip);  
		imp.show();
		//imp.flush();
	}
	
	public static void loadImageFromFile(String fileName) {
		
		//getting back a reference
		ImagePlus imp = IJ.openImage(fileName);  
		imp.show();  
		
		//without getting back a reference
		IJ.open(fileName);  
	}
	
	
	public static void testDrawingUsingROI(String fileName) {
		ImagePlus imp = IJ.openImage(fileName);  
		ImageProcessor ip = imp.getProcessor();  
		  
		// Assuming 8-bit image  
		  
		// fill a rectangular region with 255 (on grayscale this is white color):  
		Roi roi = new Roi(10, 10, 10, 10); // x, y, width, height of the rectangle  
		ip.setRoi(roi);  
		ip.setValue(255);  
		ip.fill();  
		  
		// fill an oval region with 255 (white color when grayscale LUT):  
		OvalRoi oroi = new OvalRoi(50, 60, 10, 15); // x, y, width, height of the oval  
		ip.setRoi(oroi);  
		ip.setValue(255);  
		ip.fill(ip.getMask()); // notice different fill method  
		                       // regular fill() would fill the entire bounding box rectangle of the OvalRoi  
		// The method above is valid at least for PolygonRoi and ShapeRoi as well.  
		  
		  
		// draw the contour of any region with 255 pixel intensity  
		Roi roi2 = new Roi(5, 4, 10, 10);
		ip.setValue(255);  
		ip.draw(roi2);  
		  
		// update screen view of the image  
		imp.updateAndDraw();
		imp.show();
	}
	
	
	public static void rotate(String fileName) {
		ImagePlus imp = IJ.openImage(fileName);  
		ImageProcessor ip = imp.getProcessor();
		  
		// rotate WITHOUT enlarging the canvas to fit
		ip.setInterpolate(true); // bilinear  
		ip.rotate(195);  
		  
		/*// rotate ENLARGING the canvas and filling the new areas with background color  
		double angle = 45.0;  
		IJ.run(imp, "Arbitrarily...", "angle=" + angle + " grid=1 interpolate enlarge");
		*/ 
		
		// update screen view of the image  
		imp.updateAndDraw();
		imp.show();
			  
	}
	
	public static void flip(String fileName) {
		ImagePlus imp = IJ.openImage(fileName);
		ImageProcessor ip = imp.getProcessor();  
		  
		ip.flipHorizontal();  
		  
		ip.flipVertical();  
		
		// update screen view of the image  
		imp.updateAndDraw(); 
		imp.show();
	}
	
	public static void scale(String fileName) {
		ImagePlus imp = IJ.openImage(fileName);
		ImageProcessor ip = imp.getProcessor();
		
		// scale WITHOUT modifying the canvas dimensions  
		ip.setInterpolate(true); // bilinear  
		ip.scale(.80, .80); // in X and Y  
		  
		// scale ENLARGING or SHRINKING the canvas dimensions  
		double sx = 10.75;  
		double sy = 10.75;  
		
		
		int new_width = (int)(ip.getWidth() * sx);  
		int new_height = (int)(ip.getHeight() * sy);  
		
		ip.setInterpolate(true); // bilinear  
		ImageProcessor ip2 = ip.resize(new_width, new_height); // of the same type as the original  
		imp.setProcessor(imp.getTitle(), ip2); // UPDATE the original ImagePlus
		
		// update screen view of the image  
		imp.updateAndDraw(); 
		imp.show();
		IJ.saveAs(imp, "png", "out.png"); 
		
	}
			
	public static void crop(String filename) {
		ImagePlus imp = IJ.openImage(filename);
		ImageProcessor ip = imp.getProcessor();
		
		Roi roi = new Roi(1000, 1050, 50, 50);
		ip.setRoi(roi);
		ImagePlus imp2 = new ImagePlus("", ip.crop());  
		imp2.show();
		
		IJ.saveAs(imp2, "png", filename);		
	}
	
	public static void save(String path1) {
		
		ImagePlus imp = IJ.openImage(path1);
		ImageProcessor ip = imp.getProcessor();
		
		Roi roi = new Roi(40, 50, 50, 50);
		ip.setRoi(roi);
		ImagePlus imp2 = new ImagePlus("", ip.crop());  
		//imp2.show();
		imp.updateAndDraw();
		File file = new File(path1);
		String path2 =  "/c:/temp/rough/" +file.getName() ;
		//logInfo("Saving at " + path2);
		IJ.saveAs(imp2, "png",path2);
		imp.flush();
		//path2 = path1;
		
	}

	
	public static void arialmageProcess(String filename) {
		if(filename == null) {
			filename = arialImage;
		}

		//open Image
		ImagePlus imp = IJ.openImage("chest.jpg");		
		ImagePlus imp2 = IJ.openImage("chest.jpg");
		ImagePlus mask = IJ.openImage("mask.jpg");
		//Convert image
		ImageConverter imageConverter = new ImageConverter(imp);
		imageConverter.convertToGray8();
		
		imageConverter = new ImageConverter(imp2);
		imageConverter.convertToGray8();
		
		//ColorProcessor pro = new ColorProcessor(imp.getImage());
		
		//get imageProcessor
		ImageProcessor ip = imp.getProcessor();
		ImageProcessor ip2 = imp2.getProcessor();
		
		//transform image
		//ip.blurGaussian(2.1);
		//ip.flipVertical();
		ContrastEnhancer ca = new ContrastEnhancer();
		ca.equalize(imp);
		ip.blurGaussian(5);
		//ip.dilate();
		
		ip.threshold(200);
		ip.blurGaussian(3);
		ip.threshold(200);
		
		ip.invert();
		
		/*IJ.setAutoThreshold(imp, "Default");		
		IJ.run(imp, "Convert to Mask", ""); */
				
		imp.updateAndDraw();
		
		applyMask(imp2,imp);
		//applyMask(imp2,mask);
		applyMask(imp2,createStaticMask(null));
		
		//Save the image	
		
		//Roi roi = new Roi(1000, 1050, 50, 50);
		OvalRoi oroi = new OvalRoi(20, 90, 435, 370); // x, y, width, height of the oval  
		//ip.setRoi(oroi);
		ip2.setRoi(oroi);
		
		ImagePlus imp3 = new ImagePlus("",ip2.crop());// ip2.crop());
		//imp3.getProcessor().invert();//  threshold(100);
		ca.equalize(imp3);
		imp3.updateAndDraw();
		String path2 =  "/c:/temp/rough/test.png";		
		IJ.saveAs(imp3, "png",path2);
		imp.flush();
	}
	
	public static void applyMask(ImagePlus image, ImagePlus mask) {
		ImageProcessor ip = image.getProcessor();
		
		if (image.getType() == ImagePlus.GRAY8) {  
		    int width = ip.getWidth();  
		    int height = ip.getHeight();
		    
		    byte[] maskPixs = (byte[])mask.getProcessor().getPixels();
		    byte[] pix = (byte[])image.getProcessor().getPixels();
		    
		    // set each pixel value to whatever, between -128 and 127  
		    for (int y=0; y<height; y++) {  
		        for (int x=0; x<width; x++) {  
		            // Editing pixel at x,y position
		        	if(maskPixs[y * width + x] == 0) {
		        		pix[y * width + x] = 0;		        		
		        	}else {
		        		//pix[y * width + x] = pix[y * width + x];
		        		//System.out.println(mask.getProcessor().getPixel(y * width, x) + "--- " + new_pixels[y * width + x]);
		        	}
		        }  
		    }  
		    // update ImageProcessor to new array  
		    //ip.setPixels(pix);
		    image.updateAndDraw();
		}  
	}
	
	public static ImagePlus createStaticMask(String filename) {
		
		filename = "mask.jpg";
		ImagePlus imp = IJ.openImage(filename);
		//Convert image
		ImageConverter imageConverter = new ImageConverter(imp);
		imageConverter.convertToGray8();
		
		ImageProcessor ip = imp.getProcessor();
		ContrastEnhancer ca = new ContrastEnhancer();
		ca.equalize(imp);
		ip.invert();
		ip.blurGaussian(20);
		ip.threshold(20);
		ip.invert();
				
		imp.updateAndDraw();
		
		return imp;
		//ImagePlus imp3 = new ImagePlus("", ip);
		//String path2 =  "/c:/temp/rough/staticMask.png";		
		//IJ.saveAs(imp3, "png",path2);
		//imp.flush();
		//imp3.flush();		
	}
	
	
public static ImagePlus createStaticMask2(String filename) {
		
		filename = "mask.jpg";
		ImagePlus imp = IJ.openImage(filename);
		//Convert image
		ImageConverter imageConverter = new ImageConverter(imp);
		imageConverter.convertToGray8();
		
		ImageProcessor ip = imp.getProcessor();
		ContrastEnhancer ca = new ContrastEnhancer();
		ca.equalize(imp);
		ip.invert();
		ip.blurGaussian(20);
		ip.threshold(20);
		ip.invert();
				
		imp.updateAndDraw();
		
		//return imp;
		ImagePlus imp3 = new ImagePlus("", ip);
		String path2 =  "/c:/temp/rough/staticMask.png";		
		IJ.saveAs(imp3, "png",path2);
		imp.flush();
		imp3.flush();
		return null;
	}
	
}
