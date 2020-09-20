package mnist;

import network.*;
import java.io.File;

/*
 * Created by MarkisJr. 13/07/2020
 * This class was used to train the ANN, training can be done through calling the main method
 * mnist1.txt, used by the Number Guesser program was created this way
 */
public class Mnist {

    public static void main(String[] args) 
    {
    	//This commented code creates a new trained network for images 1-20,000 saved to mnistX.txt
//    	Network net = new Network(784, 75, 30, 10);
//    	TrainSet set = createTrainSet(0, 19999);
//    	
//    	trainData(net, set, 100, 100, 100, "res/mnistX.txt");
    	
    	//This commented code loads mnistX.txt and tests it on images 20,000 to 25,000
//    	try 
//    	{
//			Network net = Network.loadNetwork("res/mnistX.txt");
//			TrainSet set = createTrainSet(20000, 24999);
//			
//			testTrainSet(net, set, 100);
//		} 
//    	catch (Exception e) {}
    }

    @SuppressWarnings("resource")
	public static TrainSet createTrainSet(int start, int end) 
    {

        TrainSet set = new TrainSet(28 * 28, 10);

        try 
        {
            String path = new File("").getAbsolutePath();

            MnistImageFile m = new MnistImageFile(path + "/res/trainImage.idx3-ubyte", "rw");
            MnistLabelFile l = new MnistLabelFile(path + "/res/trainLabel.idx1-ubyte", "rw");

            for(int i = start; i <= end; i++) 
            {
                if(i % 100 ==  0){
                    System.out.println("prepared: " + i);
                }

                double[] input = new double[28 * 28];
                double[] output = new double[10];

                output[l.readLabel()] = 1d;
                for(int j = 0; j < 28*28; j++){
                    input[j] = (double)m.read() / (double)256;
                }

                set.addData(input, output);
                m.next();
                l.next();
            }
        } catch (Exception e) 
        {
            e.printStackTrace();
        }

         return set;
    }
    
    public static void trainData(Network net,TrainSet set, int epochs, int loops, int batch_size, String output_file) 
    {
        for(int e = 0; e < epochs;e++) 
        {
            net.train(set, loops, batch_size);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>   "+ e +"   <<<<<<<<<<<<<<<<<<<<<<<<<<");
            try 
            {
				net.saveNetwork(output_file);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        }
        System.out.println(">>>>>>>>>>>>>>>>>>   Finished Training   <<<<<<<<<<<<<<<<<<<");
    }

    public static void testTrainSet(Network net, TrainSet set, int printSteps) 
    {
        int correct = 0;
        for(int i = 0; i < set.size(); i++) 
        {
            double highest = NetworkTools.indexOfHighestValue(net.calculate(set.getInput(i)));
            double actualHighest = NetworkTools.indexOfHighestValue(set.getOutput(i));
            if(highest == actualHighest) 
            {
                correct ++ ;
            }
            if(i % printSteps == 0) 
            {
                System.out.println(i + ": " + (double)correct / (double) (i + 1));
            }
        }
        System.out.println("Testing finished, RESULT: " + correct + " / " + set.size() + "  -> " + (double)correct / (double)set.size()*100 + " %");
    }
}
