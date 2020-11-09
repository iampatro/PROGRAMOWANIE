import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.*;


public class Fractal extends JPanel
{ 	
	private boolean uwiez;
	private int width=800;
	private int height=800;
	private int iteracje;
	private double xstart;
	private double xkoniec;
	private double ystart;
	private double ykoniec;
	private int com=5;
	
	Julia mb1=null;
	
	
	
	public Fractal(double x,double y,int iterations)
	{
		this.xstart=x;
		this.ystart=y;
		this.iteracje=iterations;
		com=1;
		mb1 = new Julia(x,y);
		setPreferredSize(new Dimension(width,height));
	}
	
	private static void punkt(Graphics2D frame, Color c,double x,double y) 
	{
	    frame.setColor(c); 
	    frame.draw(new Line2D.Double(x,y,x,y)); 
	}	
	 
	
	public static void punktuzyty()
	{
		System.out.println("Uzyty:");
    	System.out.println("Julia x y iteracje");
    	System.exit(0);
	}
	
	public static void main(String[]args)
	{	
	    JFrame frame=null;
	    if(args.length>=1)
	    {	
	    	
	     if(args[0].equals("Julia"))
	    	{
	    		
	    		double x_start=0;
	    		double y_start=0;
	    		int iteracje=0;
	    		if(args.length==1)
	    		{
	    			x_start=-0.4;
	    			y_start=0.6;
	    			iteracje=1000;
	    		}
	    		else if(args.length==4)
	    		{	
	    			x_start = Double.parseDouble(args[1]);
	    			y_start = Double.parseDouble(args[2]);
	    			iteracje = Integer.parseInt(args[3]);
	    		}
	    		else
	    		{
	    			System.out.println("Blad");
	    			punktuzyty();
	    		}
	    		frame = new JFrame("Julia Set"); 
	    		frame.setContentPane(new Fractal(x_start,y_start,iteracje));
	    	}
	    	else
	    	{
	    		System.out.println("Argument jest niezidentyfikowany");
		    	punktuzyty();
	    	}
	    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack(); 
			frame.setLocationRelativeTo(null); 
			frame.setVisible(true); 	
	    }	
	    else
	    {
	    	System.out.println("Nie wystaraczajaco argumentow");
	    	punktuzyty();
	    }
	}
}