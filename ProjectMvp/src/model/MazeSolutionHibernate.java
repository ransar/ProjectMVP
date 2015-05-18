package model;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;

import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;
import algorithms.search.State;

@Entity
public class MazeSolutionHibernate 
{
	@Id
	private String id;
	//@Column(name = "maze", length = 65536)
	private String maze;
	//@Column(name = "solution", length = 65536)
	private String sol;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMaze() {
		return maze;
	}
	public void setMaze(String maze) {
		this.maze = maze;
	}
	public String getSol() {
		return sol;
	}
	public void setSol(String sol) {
		this.sol = sol;
	}
	public Maze stringToMaze(String s)
	{
		String [] str = s.split("#");
		int count = 0;
		Maze maze = new Maze(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
		String [] vals = str[2].split("(?<=\\G....)");
		System.out.println(vals[0] + " " +vals[1]);
		for(int i=0;i<Integer.parseInt(str[0]);i++)
		{
			for(int j=0;j<Integer.parseInt(str[1]);j++)
			{
				String [] temp = vals[count].split(",");
				if(temp[0].equals("0"))
					maze.getCell(i, j).setHasBottomWall(false);
				else
					maze.getCell(i, j).setHasBottomWall(true);
				if(temp[1].equals("0"))
					maze.getCell(i, j).setHasLeftWall(false);
				else
					maze.getCell(i, j).setHasLeftWall(true);
				count++;
			}
		}
		
		return maze;
	}
	public Solution stringToSolution(String s)
	{
		String [] str = s.split(" ");
		Solution sol = new Solution();
		List<State> lst = new ArrayList<State>();
		for (int i=0;i<str.length;i++)
		{
			String [] temp = str[i].split(",");
			State st = new State(String.valueOf(temp[0].charAt(1))+","+String.valueOf(temp[1].charAt(0)));
			lst.add(st);
		}
		sol.setList(lst);
		return sol;
	}
}
