public class Julia
{
	protected boolean uwiez;
	private double xwejsciowe;
	private double ywejsciowe;
	private double x;
	private double y;
	private double zx1;
	private double zy1;
	private int gg;
	
	public Julia(double x,double y)
	{
		xwejsciowe=x;
		ywejsciowe=y;
	}
	
	public void wartosc(double i,double j)
	{
		zx1=(((double)i*2)/800)-1;
		zy1=(((double)j*2)/800)-1;
	}
	
	public boolean uwiezsprawdz(double x,double y,int iteracje)		
	{
		gg=0;
		x=xwejsciowe;
		y=ywejsciowe;
		while(iteracje-->0)
		{
			double znext_x=(zx1*zx1)-(zy1*zy1)+x;
			double znext_y=(2*zx1*zy1)+y;
			zx1=znext_x;
			zy1=znext_y;
			gg++;
			if((Math.pow(zx1,2)+Math.pow(zy1,2))>4)
			{	
				return uwiez=false;
			}	
		}	
		return uwiez=true;
	}
	
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	
	public int getiteracje()
	{
		return gg;
	}
}