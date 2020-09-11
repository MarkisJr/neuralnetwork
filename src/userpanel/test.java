package userpanel;

public class test 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		
		double[] i = {0.12312123, 0.6543532423, 0.54534534, 0.987856456};
		
		double test = highestValue(i);
		
		System.out.println(test);
	}
	
	public static double highestValue(double[] values)
    {
    	double max = 0d;
    	for (int i = 0; i < values.length; i++)
    	{
    		if (values[i] > max)
    			max = values[i];
    	}
    	return max;
    }

}
