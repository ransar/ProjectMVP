package model;

import java.util.Observable;

import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;

public class MyModel extends Observable implements Model{

	@Override
	public void generateMaze(int rows, int cols) {
		System.out.println("model generating....");
		
	}

	@Override
	public Maze getMaze() {
		System.out.println("model getting maze...");
		return null;
	}

	@Override
	public void solveMaze(Maze m) {
		System.out.println("model solving maze....");
		
	}

	@Override
	public Solution getSolution() {
		System.out.println("model getting solution....");
		return null;
	}

	@Override
	public void stop() {
		System.out.println("model sTOPPING....");
		
	}
	
}
