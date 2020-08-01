package userpanel;

import network.*;
import trainset.*;
import mnist.Mnist;
import javax.swing.*;
import java.awt.*;

/**
 *  Made by MarkisJr. 28/07/2020
 */

class Window extends JComponent
{
	private static final long serialVersionUID = 1L;
	int temp=0;
	
	public void paint(Graphics g)
	{
		System.out.println("Test");
		for (int y=0; y<24; y++)
		{
			for (int x=0; x<24; x++)
			{
				temp = x % 2;
				if (x % 2 == 0)
				{
					switch (temp)
					{
						case 0: g.setColor(Color.WHITE);
						break;
						case 1: g.setColor(Color.BLACK);
					}
				}
				else
				{
					switch (temp)
					{
						case 0: g.setColor(Color.BLACK);
						break;
						case 1: g.setColor(Color.WHITE);
					}
				}
				g.drawRect(x*10, y*10, 10, 10);
			}
		}
	}
	
	/*
	 * public void init() { JToggleButton pixel[][] = new JToggleButton[24][24];
	 * 
	 * for (int y=0; y<24; y++) { for (int x=0; x<24; x++) { pixel[y][x] = new
	 * JToggleButton(); pixel[y][x].setPreferredSize(new Dimension(10,10));
	 * pixel[y][x].setMargin(new Insets(0,0,0,0));
	 * 
	 * pixel[y][x].setBorder(null);
	 * 
	 * if (y % 2 == 0) { temp = x % 2; switch (temp) { case 0:
	 * pixel[y][x].setBackground(Color.WHITE); break; case 1:
	 * pixel[y][x].setBackground(Color.BLACK); break; } } else { temp = x % 2;
	 * switch (temp) { case 0: pixel[y][x].setBackground(Color.BLACK); break; case
	 * 1: pixel[y][x].setBackground(Color.WHITE); break; } } add(pixel[y][x]); } } }
	 */
}


public class Input 
{
	
	public static void main(String[] args)
	{
		/*
		 * Network net = new Network(784, 70, 35, 10); TrainSet set =
		 * Mnist.createTrainSet(0, 4999); Mnist.trainData(net, set, 100, 50, 100);
		 * 
		 * TrainSet testSet = Mnist.createTrainSet(5000, 9999); Mnist.testTrainSet(net,
		 * testSet, 10);
		 */
		//Window importclass = new Window();
		//importclass.init();
		JFrame w = new JFrame();
		w.setBounds(200, 200, 246, 269);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setResizable(true);
		w.getContentPane().add(new Window());
		w.setVisible(true);
	}

}
