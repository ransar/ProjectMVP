package presenter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerators.Maze;
import model.Model;
import view.Command;
import view.View;

public class Presenter implements Observer{
	HashMap<String, Command> commands = new HashMap<String, Command>();
	HashMap<String, Maze> mazes = new HashMap<String, Maze>();
	String command;
	View v;
	Model m;
	public Presenter(Model m,View v) 
	{
		commands.put("generate maze",  new generateMaze());
		commands.put("display maze",  new displayMaze());
		commands.put("solve maze",  new displayMaze());
		commands.put("display solution",  new displayMaze());
		commands.put("exit",  new Exit());
		this.m=m;
		this.v=v;
	}

	public class generateMaze implements Command
	{

		@Override
		public void doCommand(String path) throws FileNotFoundException,IOException 
		{
			String[] ints = command.split(" ");
			int rows=Integer.parseInt(ints[1]);
			int cols=Integer.parseInt(ints[2]);
			m.generateMaze(rows,cols);
			Maze maze = m.getMaze();
			mazes.put(ints[0], maze);
		}		
	}
	public class displayMaze implements Command
	{
		@Override
		public void doCommand(String path) throws FileNotFoundException,IOException
		{
			v.displayMaze(m.getMaze());
		}	
	}
	public class solveMaze implements Command
	{
		public void doCommand(String path) throws FileNotFoundException,IOException
		{
			String[] ints = command.split(" ");
			BufferedReader reader = new BufferedReader(new FileReader("names.txt"));
			String temp = "";
			String line = "";
			try 
			{
				while ((temp = reader.readLine()) != null)
				{
					line+=temp;
				}
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reader.close();
			
			OutputStream out = new FileOutputStream(new File("names.txt"));
			String str = line + "#" + ints[0];
			out.write(str.getBytes());
			Maze maze = m.getMaze();
			m.solveMaze(maze);
			System.out.println("Solution for " + command + " is ready!");			
		}		
	}
	public class displaySolution implements Command
	{
		@Override
		public void doCommand(String path) throws FileNotFoundException,IOException
		{
			v.displaySolution(m.getSolution(path));			
		}		
	}
	public class Exit implements Command
	{
		@Override
		public void doCommand(String path) throws FileNotFoundException,IOException 
		{
			m.stop();
		}
		
	}
	@Override
	public void update(Observable o, Object arg)
	{
		if(o==v)
		{
			if(arg!=null)
			{
				if((String)arg=="start")
					v.setCommands(this.commands);
				else if((String)arg=="finish")
				{
					Exit e = new Exit();
					try {
						e.doCommand("null");
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else 
				{
					Command c = v.getUserCommand();
					this.command = (String)arg;
					try {
						c.doCommand(null);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		if (o==m)
		{
			
		}
	}
	

}
