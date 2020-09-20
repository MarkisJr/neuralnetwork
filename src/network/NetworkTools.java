package network;

/*
 *  Made by MarkisJr. 1/08/2020
 *  Class serves to store useful methods for Network class or addons
 */

public class NetworkTools 
{
	
	//Used by Network class to create an array for the neural layers
    public static double[] createArray(int size, double init_value)
    {
        if(size < 1)
        {
            return null;
        }
        double[] ar = new double[size];
        for(int i = 0; i < size; i++)
        {
            ar[i] = init_value;
        }
        return ar;
    }

    //Used by Network class to create a 1D randomly seeded array for the neural layers
    public static double[] createRandomArray(int size, double lower_bound, double upper_bound)
    {
        if(size < 1)
        {
            return null;
        }
        double[] ar = new double[size];
        for(int i = 0; i < size; i++)
        {
            ar[i] = randomValue(lower_bound,upper_bound);
        }
        return ar;
    }

    //Used by Network class to create a 2D randomly seeded array for the neural layers
    public static double[][] createRandomArray(int sizeX, int sizeY, double lower_bound, double upper_bound)
    {
        if(sizeX < 1 || sizeY < 1)
        {
            return null;
        }
        double[][] ar = new double[sizeX][sizeY];
        for(int i = 0; i < sizeX; i++){
            ar[i] = createRandomArray(sizeY, lower_bound, upper_bound);
        }
        return ar;
    }

    //Purely serves as an RNG method, can be used for anything
    public static double randomValue(double lower_bound, double upper_bound)
    {
        return Math.random()*(upper_bound-lower_bound) + lower_bound;
    }

    //Returns Integer array seeded with random values
    public static Integer[] randomValues(int lowerBound, int upperBound, int amount) 
    {

        lowerBound --;

        if(amount > (upperBound-lowerBound))
        {
            return null;
        }

        Integer[] values = new Integer[amount];
        for(int i = 0; i< amount; i++)
        {
            int n = (int)(Math.random() * (upperBound-lowerBound+1) + lowerBound);
            while(containsValue(values, n))
            {
                n = (int)(Math.random() * (upperBound-lowerBound+1) + lowerBound);
            }
            values[i] = n;
        }
        return values;
    }

    //Checks if array contains specified value, unused
    public static <T extends Comparable<T>> boolean containsValue(T[] ar, T value)
    {
        for(int i = 0; i < ar.length; i++)
        {
            if(ar[i] != null){
                if(value.compareTo(ar[i]) == 0)
                {
                    return true;
                }
            }

        }
        return false;
    }

    //Returns the index number of highest value (e.g. winning number)
    public static int indexOfHighestValue(double[] values)
    {
        int index = 0;
        for(int i = 1; i < values.length; i++)
        {
            if(values[i] > values[index])
            {
                index = i;
            }
        }
        return index;
    }
    
    //Returns the highest number (e.g. 0.9981231231)
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
