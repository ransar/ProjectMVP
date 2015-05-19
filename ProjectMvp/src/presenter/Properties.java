package presenter;

import java.io.Serializable;

public class Properties implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 933599711108468L;
	/**
	 * 
	 */
	public enum MazeGenerator 
	{
		DFS_ALGO,RANDOM_ALGO
	};
	public enum MazeSolver 
	{
		ASTAR_MANHATTAN_DISTANCE,ASTAR_AIR_DISTANCE,BFS_DIAGONAL,BFS_NO_DIAGONAL
	};
	private int threadNumber;
	private MazeSolver mazeSolver;
	private MazeGenerator mazeGenerator;
	private Boolean diagonal;
	private double regCost;
	private double diagonalCost;
	
	public Properties()
	{
		this.threadNumber=10;
		this.mazeSolver=MazeSolver.ASTAR_MANHATTAN_DISTANCE;
		this.mazeGenerator=MazeGenerator.DFS_ALGO;
		this.diagonal=false;
		this.regCost=10;
		this.diagonalCost=15;
	}
	public Properties(MazeSolver mazeSolver,int threadNumber,MazeGenerator mazeGenerator, Boolean diagonal,double regCost,double diagonalCost)
	{
		this.threadNumber=threadNumber;
		this.mazeSolver=mazeSolver;
		this.mazeGenerator=mazeGenerator;
		this.diagonal=diagonal;
		this.regCost=regCost;
		this.diagonalCost=diagonalCost;
	}

	public int getThreadNumber() {
		return threadNumber;
	}
	public void setThreadNumber(int threadNumber) {
		this.threadNumber = threadNumber;
	}
	public MazeSolver getMazeSolver() {
		return mazeSolver;
	}
	public void setMazeSolver(MazeSolver mazeSolver) {
		this.mazeSolver = mazeSolver;
	}
	public MazeGenerator getMazeGenerator() {
		return mazeGenerator;
	}
	public void setMazeGenerator(MazeGenerator mazeGenerator) {
		this.mazeGenerator = mazeGenerator;
	}
	public Boolean getDiagonal() {
		return diagonal;
	}
	public void setDiagonal(Boolean diagonal) {
		this.diagonal = diagonal;
	}
	public double getMovementCost() {
		return regCost;
	}
	public void setMovementCost(double movementCost) {
		this.regCost = movementCost;
	}
	public double getDiagonalMovementCost() {
		return diagonalCost;
	}
	public void setDiagonalMovementCost(double diagonalCost) {
		this.diagonalCost = diagonalCost;
	}
}