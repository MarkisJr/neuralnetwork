package userpanel;

import network.*;
import trainset.*;
import mnist.Mnist;
import javax.swing.*;
import java.awt.*;
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
	
	public MyFrame()
	{
		//Variable declaration
		splitPane = new JSplitPane();
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		paintArea = new JPanel();
		output = new JLabel();
		
		//Configuring splitpane
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerLocation(240);
		splitPane.setTopComponent(topPanel);
		splitPane.setBottomComponent(bottomPanel);
		splitPane.setDividerSize(0);
		
		//Configuring topPanel
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.add(paintArea);
		paintArea.setPreferredSize(new Dimension(240,240));
		paintArea.setMaximumSize(new Dimension(240,240));
		paintArea.setBackground(Color.DARK_GRAY);
		paintArea.addMouseListener(new MouseListener() {
			
			volatile private boolean isMouseDown = false;
			
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Mouse was pressed...");
				isMouseDown = true;
				initThread();
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("Mouse was released...");
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
									
									System.out.println(String.valueOf(mouse));
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
		output.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		output.setText("99.9");
		
		//Window dimensions and layout
		setPreferredSize(new Dimension(250, 400));
		getContentPane().setLayout(new GridLayout());
		getContentPane().add(splitPane);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Packs data for Runnable() class
		pack();
	}
	
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