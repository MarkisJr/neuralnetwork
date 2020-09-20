package parser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/*
 * Made by MarkisJr. 15/08/2020
 */

public class Parser 
{
	
	private Node mainContent;
	private String fileName;
	
	//Loads previous ANN "brain" file
	public void load(String file) throws Exception 
	{
		this.fileName = file;
		//Points the class where to begin reading text file
		String data = "<MainContent>";
		InputStream in = getClass().getResourceAsStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line;
		while((line = reader.readLine())!= null)
		{
			if(!line.trim().equals(""))
				data += line;
		}
		data += "</MainContent>";
		this.generateNodes(data);
	}
	
	//Creates ANN file to later loading
	public void create(String fileName) 
	{
		this.fileName = fileName;
		this.mainContent = new Node("MainContent");
	}
	
	//Generates relevant nodes for indexing brain file
	private void generateNodes(String data) throws Exception
	{
		mainContent = Node.parse(data);
	}
	
	//Parses the content to be read to Node class
	public Node getContent() 
	{
		return mainContent;
	}

	//Get value at specific node and specific indent
	public String getValue(String[] keys, String attr) 
	{
		Node curr = mainContent;
		for(String k:keys){
			curr = curr.getChild(k);
			if(curr == null)
			{
				return null;
			}
		}
		return curr.getAttribute(attr).getValue();
	}
	
	//Sets values at specific node
	public void setValue(String[] keys, String attr, String value) 
	{
		Node curr = mainContent;
		for(String k:keys){
			curr = curr.getChild(k);
			if(curr == null)
			{
				return;
			}
		}
		if(curr.containsAttribute(new Attribute(attr,"")))
		{
			curr.setAttribute(attr, value);
		}
		else
		{
			curr.addAttribute(attr, value);
		}
	}
	
	//Adds name of node by way of another node
	public void addNode(String[] keys, Node n)
	{
		Node curr = mainContent;
		for(String k:keys){
			curr = curr.getChild(k);
			if(curr == null)
			{
				return;
			}
		}
		curr.addChild(n);
	}
	
	//Adds name of node by way of a string
	public void addNode(String[] keys, String node)
	{
		Node curr = mainContent;
		for(String k:keys)
		{
			curr = curr.getChild(k);
			if(curr == null)
			{
				return;
			}
		}
		curr.addChild(new Node(node));
	}
	
	//Writes or reads to/from file
	public void close() throws Exception
	{
		PrintWriter out = new PrintWriter(fileName);
		for(Node n:mainContent.getChilds())
		{
			out.print(n.toParse(0));
		}
		out.close();
	}
	
	
}
