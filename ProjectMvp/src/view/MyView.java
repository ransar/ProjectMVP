package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Observable;

import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;

public class MyView extends Observable implements View {
	Command command;
	CliMVP c;
	HashMap<String, Command> commands = new HashMap<String, Command>();
	public MyView() 
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter writer = new PrintWriter(System.out);
		this.c=new CliMVP(reader, writer);
		c.setView(this);
	}
	@Override
	public void start() 
	{
		setChanged();
		notifyObservers("start");
		c.start();
	}
	public void setChangedFunc()
	{
		setChanged();
	}
	@Override
	public void setCommands(HashMap<String, Command> commands2)
	{
		this.commands = commands2;
		c.setHashMap(commands2);
	}
	public void printMessage(String str)
	{
		System.out.println(str);
	}
	@Override
	public Command getUserCommand() 
	{
		return command;
	}

	@Override
	public void displayMaze(Maze m) 
	{
		m.print();
	}

	@Override
	public void displaySolution(Solution s)
	{
		
	}

}
