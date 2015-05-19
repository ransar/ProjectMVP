package boot;

import model.MazeSolutionHibernate;
import model.MyModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import presenter.Presenter;
import view.MyView;
import algorithms.demo.MazeSearch;
import algorithms.mazeGenerators.DFSMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MazeGenerator;
import algorithms.search.AStar;
import algorithms.search.MazeManhattanDistance;
import algorithms.search.Solution;


/**
 * This is the Run class that contains the main function which connects the observer with the other observables.
 */

public class Run {

	 /**
	   * This is the main method which connects the observer with the other observables, in this function we are assembling the MVP parts.
	   * @return Nothing.
	   */
	public static void main(String[] args) 
	{
		//my Program
		//System.out.println("hello");
		MazeSolutionHibernate ms = new MazeSolutionHibernate();
		MazeGenerator mg=new DFSMazeGenerator();
		Maze maze = mg.generateMaze(10,10);
		ms.setMaze(maze.toString());
		//Maze temp = ms.stringToMaze(maze.toString());
		//temp.print();
		MazeSearch ams1 = new MazeSearch(maze,false);
		AStar sol5 = new AStar();
		sol5.setH(new MazeManhattanDistance());
		Solution sol = sol5.search(ams1);
		System.out.println(sol.toString());
		ms.setSol(sol.toString());
		Solution sl;
		//sl = ms.stringToSolution(sol.toString());
		//System.out.println();
		//sl.displaySolution();
		ms.setId("test2");
        System.out.println("Trying to create a test connection with the database.");
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
        Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(ms);
		session.getTransaction().commit();
        System.out.println("Test connection with the database created successfuly.");
		
		session.close();
		
		///////////////////////////////////////////////////////////////////
		/*MyModel m = new MyModel();
		/*m.setName("test123");
		m.generateMaze(5,5);
		m.solveMaze(m.getMaze());
		m.getMaze().print();
		m.getSolution().displaySolution();*/
		/*MyView v = new MyView();
		Presenter p = new Presenter(m,v);
		m.addObserver(p);
		v.addObserver(p);
		v.start();*/
		/*ms = null;
		session = sessionFactory.openSession();
		session.beginTransaction();
		ms = (MazeSolutionHibernate) session.get(MazeSolutionHibernate.class,"walla");
		System.out.println(ms.getId()+" "+ms.getMaze()+" "+ms.getSol());
		
		session.close();*/
	}
	

}
