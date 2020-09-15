package userpanel;

import java.awt.*;
import javax.swing.*;

public class test extends JFrame
{
	private JPanel test;
	private Drawing draw;
	
	public test()
	{
		test = new JPanel();
		test.add(draw);
		
		pack();
	}
	
	public static void main(String[] args)
	{
		System.out.println(Math.round(87.1235123));
	}
}

class Drawing extends JPanel
{
	public Drawing()
	{
		setPreferredSize(new Dimension(200,200));
		setBackground(Color.RED);
	}
}