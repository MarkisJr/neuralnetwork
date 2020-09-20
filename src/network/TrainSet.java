package network;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *  Made by MarkisJr. 28/07/2020
 *  Handles batches of data
 */

public class TrainSet 
{
	//Constants set when class is called
    public final int INPUT_SIZE;
    public final int OUTPUT_SIZE;

    //double[][] <- index1: 0 = input, 1 = output || index2: index of element
    private ArrayList<double[][]> data = new ArrayList<>();

    //Setting inputs to constants
    public TrainSet(int INPUT_SIZE, int OUTPUT_SIZE) 
    {
        this.INPUT_SIZE = INPUT_SIZE;
        this.OUTPUT_SIZE = OUTPUT_SIZE;
    }

    //List handles tracking of inputs to expected outputs
    public void addData(double[] in, double[] expected) 
    {
        if(in.length != INPUT_SIZE || expected.length != OUTPUT_SIZE) return;
        data.add(new double[][]{in, expected});
    }

    //Uses its own methods to extract the batch
    public TrainSet extractBatch(int size) 
    {
        if(size > 0 && size <= this.size()) 
        {
            TrainSet set = new TrainSet(INPUT_SIZE, OUTPUT_SIZE);
            Integer[] ids = NetworkTools.randomValues(0,this.size() - 1, size);
            for(Integer i:ids) 
            {
                set.addData(this.getInput(i),this.getOutput(i));
            }
            return set;
        }
        else return this;
    }

    //Used to display inputs and outputs to console, unused (testing)
    public String toString() 
    {
        String s = "TrainSet ["+INPUT_SIZE+ " ; "+OUTPUT_SIZE+"]\n";
        int index = 0;
        for(double[][] r:data) 
        {
            s += index +":   "+Arrays.toString(r[0]) +"  >-||-<  "+Arrays.toString(r[1]) +"\n";
            index++;
        }
        return s;
    }

    //Methods responsible for external use when retrieving data from the set
    public int size() 
    {
        return data.size();
    }

    public double[] getInput(int index) 
    {
        if(index >= 0 && index < size())
            return data.get(index)[0];
        else return null;
    }

    public double[] getOutput(int index) 
    {
        if(index >= 0 && index < size())
            return data.get(index)[1];
        else return null;
    }

    public int getINPUT_SIZE() 
    {
        return INPUT_SIZE;
    }

    public int getOUTPUT_SIZE() 
    {
        return OUTPUT_SIZE;
    }
}
