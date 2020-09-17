package userpanel;

import java.awt.*;
import javax.swing.*;

public class test extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private final JSplitPane splitPane;
	private final JPanel topPanel;
	private final JPanel bottomPanel;
	private final JLabel temp;
	private final Drawing paintArea;
	
	public test()
	{
		splitPane = new JSplitPane();
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		paintArea = new Drawing();
		temp = new JLabel();
		
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerLocation(300);
		splitPane.setTopComponent(topPanel);
		splitPane.setBottomComponent(bottomPanel);
		splitPane.setDividerSize(0);
		
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.add(paintArea);
		
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
		bottomPanel.add(temp);
		temp.setText("Temporary");
		
		setPreferredSize(new Dimension(500, 500));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout());
		getContentPane().add(splitPane);		
		pack();
	}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new test().setVisible(true);
			}
		});
	}
}

class Drawing extends JPanel
{
	private static final long serialVersionUID = 1L;

	public Drawing()
	{
		setPreferredSize(new Dimension(280,280));
		setMaximumSize(new Dimension(280,280));
		setBackground(Color.GRAY);
	}
	
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 20, 20);
		
	}
	
	
}