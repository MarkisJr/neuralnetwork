package userpanel;

import network.*;
import java.util.ArrayList;
//import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *  Made by MarkisJr. 28/07/2020
 */

public class MyFrame extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	//Visual components
	private final JSplitPane splitPane;
	private final JPanel topPanel;
	private final JPanel bottomPanel;
	private final JPanel paintArea;
	private final JLabel output;
	private final JButton calculate;
	private final JButton reset;
	
	//Pixel parser
	double[][] pixel = new double[28][28];
	
	//Button state
	boolean isClicked = false;
	
	public MyFrame()
	{
		//Variable declaration
		splitPane = new JSplitPane();
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		paintArea = new JPanel();
		output = new JLabel();
		calculate = new JButton();
		reset = new JButton();
		
		//Configuring splitpane
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerLocation(280);
		splitPane.setTopComponent(topPanel);
		splitPane.setBottomComponent(bottomPanel);
		splitPane.setDividerSize(0);
		
		//Configuring topPanel
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.add(paintArea);
		paintArea.setPreferredSize(new Dimension(280,280));
		paintArea.setMaximumSize(new Dimension(280,280));
		paintArea.setBackground(Color.DARK_GRAY);
		paintArea.addMouseListener(new MouseListener() {
			
			volatile private boolean isMouseDown = false;
			
			@Override
			public void mousePressed(MouseEvent e) {
				isMouseDown = true;
				initThread();
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				isMouseDown = false;
			}
			
			//Unneeded methods required to remain
			@Override public void mouseExited(MouseEvent e){}
			@Override public void mouseEntered(MouseEvent e){}
			@Override public void mouseClicked(MouseEvent e){}
			
			//State of thread
			volatile private boolean isRunning = false;
			
			//Checks if there is already a new thread in existence
			private synchronized boolean checkAndMark()
			{
				if (isRunning) 
					return false;
				isRunning = true;
				return true;				
			}
			
			//Creates thread and executes code as long as mouse is held down
			private void initThread()
			{
				if (checkAndMark())
				{
					new Thread()
					{
						public void run()
						{	
							while (isMouseDown == true) 
							{
								Point mouse = paintArea.getMousePosition();
								
								if (mouse == null)
									System.out.println("Exceeding bounds");
								else
								{
									int xVal = (int) Math.floor(mouse.getX() / 10d);
									int yVal = (int) Math.floor(mouse.getY() / 10d);
									
									pixel[xVal][yVal] = 0.98828125;
									
									
								}
							}
							isRunning = false;
						}
					}.start();
				}
			}
		});		
		
		//Configuring bottomPanel
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
		bottomPanel.add(output);
		bottomPanel.add(calculate);
		bottomPanel.add(reset);
		output.setAlignmentX(Component.CENTER_ALIGNMENT);
		output.setText("00.0");
		
		calculate.setAlignmentX(Component.CENTER_ALIGNMENT);
		calculate.setMinimumSize(new Dimension(80,40));
		calculate.setText("Calculate");
		calculate.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				calculate.setEnabled(false);
				reset.setEnabled(true);
				
				try 
				{
					Network net = Network.loadNetwork("res/mnist1.txt");
					System.out.println("The Value is: " + test(net, pack(pixel)));
					
				} 
				catch (Exception e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		
		reset.setEnabled(false);
		reset.setAlignmentX(Component.CENTER_ALIGNMENT);
		reset.setMinimumSize(new Dimension(80,40));
		reset.setText("Reset");
		reset.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				calculate.setEnabled(true);
				reset.setEnabled(false);
				
				for (int i=0; i<pixel.length; i++)
				{
					for (int j=0; j<pixel[i].length; j++)
					{
						pixel[i][j] = 0d;
					}
				}
			}
		});
		
		//Window dimensions and layout
		setPreferredSize(new Dimension(292, 400));
		getContentPane().setLayout(new GridLayout());
		getContentPane().add(splitPane);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Packs data for Runnable() class
		pack();
	}
	
	//Packs 2d array into a 1d array
	public static double[] pack(double[][] input)
	{
		ArrayList<Double> list = new ArrayList<Double>();
		
		for (int i=0; i<input.length; i++)
		{
			for (int j=0; j<input[i].length; j++)
			{
				list.add(input[i][j]);
			}
		}
		
		double[] output = new double[list.size()];
		for (int i=0; i<list.size(); i++)
		{
			output[i] = list.get(i);
		}
		return output;
	}
	
	//Sends array off neural network
	public static double test(Network net, double[] input) 
    {
		double[] temp = net.calculate(input);
		double answer = NetworkTools.indexOfHighestValue(temp);
		double certainty = NetworkTools.highestValue(temp)*100d;
		System.out.println(certainty + "% certain");
		
        return answer;
    }

	//Default main method
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new MyFrame().setVisible(true);
			}
		});
	}
}