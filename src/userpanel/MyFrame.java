package userpanel;

import network.*;
import trainset.*;
import mnist.Mnist;
import javax.swing.*;
import java.awt.*;

/**
 *  Made by MarkisJr. 28/07/2020
 */

public class MyFrame extends JFrame
{
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
		
		//Window dimensions and layout
		setPreferredSize(new Dimension(300, 400));
		getContentPane().setLayout(new GridLayout());
		getContentPane().add(splitPane);
		
		//Configuring splitpane
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerLocation(250);
		splitPane.setTopComponent(topPanel);
		splitPane.setBottomComponent(bottomPanel);
		
		//Configuring topPanel
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.add(paintArea);
		
		//Configuring bottomPanel
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
		bottomPanel.add(output);
		output.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Setting font size to be bigger
		Font labelFont = output.getFont();
		String labelText = output.getText();
		int stringWidth = output.getFontMetrics(labelFont).stringWidth(labelText);
		int componentWidth = output.getWidth();
		double widthRatio = (double)componentWidth / (double)stringWidth;
		int newFontSize = (int)(labelFont.getSize() * widthRatio);
		int componentHeight = output.getHeight();
		int fontSizeToUse = Math.min(newFontSize, componentHeight);
		output.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		
		output.setText("99.9%");
		
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