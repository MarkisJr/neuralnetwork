package userpanel;

import network.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/*
 *  Made by MarkisJr. 28/07/2020
 */

public class MyFrame extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	//Visual components
	private final JSplitPane splitPane;
	private final JPanel topPanel;
	private final JPanel bottomPanel;
	private final DrawingSpace paintArea;
	public static JLabel output;
	public static JButton calculate;
	private final JButton reset;
	
	//Pixel parser
	public static double[][] pixel = new double[28][28];
		
	//Method called on class reference
	public MyFrame()
	{
		//Variable init
		splitPane = new JSplitPane();
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		paintArea = new DrawingSpace();
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
		
		//Configuring bottomPanel
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
		bottomPanel.add(output);
		bottomPanel.add(calculate);
		bottomPanel.add(reset);
		
		//Configuring Label
		output.setAlignmentX(Component.CENTER_ALIGNMENT);
		output.setText("Please Draw a Value");
		
		//Configuring calculate button
		calculate.setAlignmentX(Component.CENTER_ALIGNMENT);
		calculate.setMinimumSize(new Dimension(80,40));
		calculate.setText("Calculate");
		
		//Sends drawing to ANN and calls test method
		calculate.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				calculate.setEnabled(false);
				
				try 
				{
					Network net = Network.loadNetwork("mnist1.txt");
					test(net, pack(pixel));					
				} 
				catch (Exception e1) 
				{}
			}
		});
		
		//Reset button code, clears 2d array of previous drawing and toggles calculate button
		reset.setAlignmentX(Component.CENTER_ALIGNMENT);
		reset.setMinimumSize(new Dimension(80,40));
		reset.setText("Reset");
		reset.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				calculate.setEnabled(true);
				
				output.setText("Please Draw a Value");
				
				for (int i=0; i<pixel.length; i++)
				{
					for (int j=0; j<pixel[i].length; j++)
					{
						pixel[i][j] = 0d;
						repaint();
					}
				}
			}
		});
		
		//Window dimensions, layout and icon
		setPreferredSize(new Dimension(292, 400));
		getContentPane().setLayout(new GridLayout());
		getContentPane().add(splitPane);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Guess my Number");
		try {
			InputStream in = getClass().getResourceAsStream("icon.png");
			BufferedImage img = ImageIO.read(in);
			setIconImage(img);
		} 
		catch (IOException e1) {}
		
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
				list.add(input[j][i]);
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
	public static void test(Network net, double[] input) 
    {
		//Temporary values
		double[] temp = net.calculate(input);
		double answer = NetworkTools.indexOfHighestValue(temp);
		
		//Due to the fact that the ANN is loaded from a predetermined "memory", if all of the inputs fed into the ANN are 0.0, it will always return the same value, 97.337% sure it's a 5
		//While it would be better coding practice to check if the 2d array was completely filled with 0.0, I wanted to demonstrate the still computer like "exactness" of the ANN
		if ((NetworkTools.highestValue(temp) == 0.9733711842294354) && (answer == 5d))
			output.setText("Please Draw a Number");
		//Actual result
		else
			output.setText(String.valueOf(Math.round(NetworkTools.highestValue(temp) * 10000d) / 100d) + "% it's " + String.valueOf((int)answer));
    }
	

	//Default main method
	public static void main(String[] args)
	{
		//Runs the MyFrame class, initializing all visual components
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

//Custom class for the drawing space
class DrawingSpace extends JPanel 
{
	private static final long serialVersionUID = 1L;

	public DrawingSpace()
	{
		//Settings of drawing space
		setPreferredSize(new Dimension(280,280));
		setMaximumSize(new Dimension(280,280));
		setBackground(Color.DARK_GRAY);
		//Mouse listener for when drawing on the paint area
		addMouseListener(new MouseListener() {
			
			volatile private boolean isMouseDown = false;
			
			//mousePressed and mouseReleased used to control the thread
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
			
			//Creates thread
			private void initThread()
			{
				if (checkAndMark())
				{
					new Thread()
					{
						public void run()
						{	
							//Runs while mouse is down
							while (isMouseDown == true) 
							{
								//Gets mouse location relative to the paint area
								Point mouse = getMousePosition();
								
								//Executes when stuff is drawn and nothing has been calculated
								if ((mouse != null) && (MyFrame.calculate.isEnabled()))
								{
									//Gets what pixel the mouse is in relative to the 28x28 pixel grid system
									int xVal = (int) Math.floor(mouse.getX() / 10d);
									int yVal = (int) Math.floor(mouse.getY() / 10d);
									
									//Sets the pixel that was clicked to "coloured", the constant 0.98828125 is used as the ANN never expects 1.0 as it is too "perfect"
									//5 iterations due to brush size
									try 
									{
										MyFrame.pixel[xVal][yVal] = 0.98828125;
										MyFrame.pixel[xVal-1][yVal] = 0.98828125;
										MyFrame.pixel[xVal+1][yVal] = 0.98828125;
										MyFrame.pixel[xVal][yVal-1] = 0.98828125;
										MyFrame.pixel[xVal][yVal+1] = 0.98828125;
									}
									catch (Exception e) {}
									repaint();
								}
							}
							isRunning = false;
						}
					}.start();
				}
			}
		});
	}
	
	//Paint method fills in pixels each time thread is run
	public void paintComponent(Graphics g) {
		try
		{
			super.paintComponent(g);
			g.setColor(Color.WHITE);
			for (int x=0; x<MyFrame.pixel.length; x++)
			{
				for (int y=0; y<MyFrame.pixel[x].length; y++)
				{
					if (MyFrame.pixel[x][y] != 0.0)
						g.fillRect(x*10, y*10, 10, 10);
				}
			}
		}
		catch (Exception e) {}
	}
}