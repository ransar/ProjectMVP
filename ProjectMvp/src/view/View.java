package view;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;
/**
 * generic interface for a view in MVP pattern
 * @author user1
 *
 */
public interface View {
	void start();  
	Command getUserCommand(); 
	void displayMaze(Maze m); 
	void displaySolution(Solution s);
	void setCommands(HashMap<String, Command> commands2);
	public void printMessage(String str);
}