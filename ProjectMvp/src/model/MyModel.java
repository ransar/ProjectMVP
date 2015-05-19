package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Observable;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import algorithms.demo.MazeSearch;
import algorithms.mazeGenerators.DFSMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MazeGenerator;
import algorithms.search.AStar;
import algorithms.search.MazeManhattanDistance;
import algorithms.search.Solution;

//import com.google.common.util.concurrent.ListenableFuture;
//import com.google.common.util.concurrent.ListeningExecutorService;
//import com.google.common.util.concurrent.MoreExecutors;


/**
* The MyModel class, extends observable and implements the Model interface.
* @author  Sarusi Ran, Gershfeld Itzik 
* @version 1.0
* @since   2015-05-18 
*/


public class MyModel extends Observable implements Model
{
	ExecutorService executor;
	Maze maze;
	Solution sol;
	String MazeName;
	HashMap<String,HashMap<Maze, Solution>> msols;
	
	
	 /**
	   * This is the C'tor of MyModel. 
	   * <p>The first thing it does is initialize the ThreadPool.
	   * The second thing it does is getting all the data(name, maze, solution) from the database, from the MazeSolutionHibernate table..
	   * @param Nothing.
	   * @return Nothing.
	   */
	
	public MyModel() 
	{
		//Initialize the threadpool.
		this.executor = Executors.newCachedThreadPool();
		msols = new HashMap<String, HashMap<Maze,Solution>>();
		//getting all the data from the database.
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		MazeSolutionHibernate ms = new MazeSolutionHibernate();
		String [] names = null;
		try 
		{
			BufferedReader reader = new BufferedReader(new FileReader("names.txt"));
			String line;
			try 
			{
				while ((line = reader.readLine()) != null)
				{
					names = line.split("#");
				}
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=1;i<names.length;i++)
			{
				ms = (MazeSolutionHibernate) session.get(MazeSolutionHibernate.class,names[i]);
				HashMap<Maze, Solution> temp = new HashMap<Maze, Solution>();
				temp.put(ms.stringToMaze(ms.getMaze()), ms.stringToSolution(ms.getSol()));
				msols.put(ms.getId(), temp);
				
			}
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(ms.getId()+" "+ms.getMaze()+" "+ms.getSol());
		
		session.close();
	}
	
	 /**
	   * This method generates a (rows * cols) size maze .
	   * @param rows <b>(int) </b>This is the first parameter to the generateMaze method
	   * @param cols <b>(int) </b>This is the second parameter to the generateMaze method
	   * @return Nothing.
	   */
	
	@Override
	public void generateMaze(int rows, int cols) 
	{
		System.out.println("generated a maze !");
		
	
		Future<Maze> future = executor.submit(new Callable<Maze>()
				{
            @Override
            public Maze call() throws Exception 
            {
    			MazeGenerator mg=new DFSMazeGenerator();
    			maze = mg.generateMaze(rows,cols);
    			return maze;
             }
             });
		try 
		{
			Maze temp = future.get();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		}
		
		
	}

	 /**
	   * This method gets the maze with the specific name.
	   * @param name <b>(String) </b>This is the parameter to the getMaze method.
	   * @return maze <b>(Maze) </b>.
	   */
	
	@Override
	public Maze getMaze(String name) 
	{
		HashMap<Maze, Solution> temp = msols.get(name);
		Maze mz = temp.keySet().iterator().next();
		
		
		System.out.println("get maze");
		if(mz==null)
		{
			System.out.println("No maze yet");
			return null;
		}
		return mz;
	}

	 /**
	   * This method solves the Maze m, in the end it adds the name of the maze, the maze itself and it's solution into the database.
	   * @param m <b>(Maze) </b>This is the parameter to the solveMaze method
	   * @return Nothing.
	   */
	
	@Override
	public void solveMaze(Maze m) 
	{
		System.out.println("solve maze");
		if(msols.containsKey(MazeName))
		{
			HashMap <Maze, Solution> temp = new HashMap<Maze, Solution>(); 
			temp = msols.get(MazeName);
			sol = temp.get(m);
			setChanged();
			notifyObservers(4);
		}
		else
		{
			
			Future<Solution> future = executor.submit(new Callable<Solution>()
			{
                @Override
                public Solution call() throws Exception 
                {
        			System.out.println("\nSolution A* without diagonals");
        			MazeSearch ams1 = new MazeSearch(m,false);
        			AStar sol5 = new AStar();
        			sol5.setH(new MazeManhattanDistance());
        			sol = sol5.search(ams1);
        			return sol;
                 }
            });
			
			//HashMap <Maze, Solution> temp = new HashMap<Maze, Solution>(); 
			//temp.put(maze, sol);
			//msols.put(MazeName,temp);
			
			
			/////here we communicate with the database
			
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			MazeSolutionHibernate msh = new MazeSolutionHibernate();
			Set<String> names = msols.keySet();
			for(String name:names)
			{
				HashMap<Maze, Solution> temp = new HashMap<Maze, Solution>();
				temp = msols.get(name);
				for(Maze mz: temp.keySet())
				{
					if(mz.equals(m))
					{
						msh.setMaze(m.toString());
						msh.setSol(temp.get(m).toString());
						msh.setId(name);
					}
				}
			}
			/*msh.setMaze(maze.toString());
			msh.setSol(sol.toString());
			msh.setId(MazeName);*/
			
			session.save(msh);
			session.getTransaction().commit();
			
			session.close();
			
		}
		
	}

	 /**
	   * This method gets the Solution with the specific maze name.
	   * @param name <b>(String) </b>This is the parameter to the getSolution method.
	   * @return sol <b>(Solution) </b>.
	   */
	
	@Override
	public Solution getSolution(String name) 
	{
		System.out.println("get solution of the maze");
		
		HashMap<Maze, Solution> temp = msols.get(name);
		Solution solution = temp.values().iterator().next();
		
		
		if(solution==null)
		{
			System.out.println("No solution yet");
			return null;
		}
		return solution;
	}

	
	 /**
	   * This method stops the whole process, shuts down the threadpool.
	   * @param Nothing.
	   * @return Nothing.
	   */
	
	@Override
	public void stop() 
	{
		
		System.out.println("stop");
		if(executor.isShutdown())
			return;
		executor.shutdown();
		
	}
	
	 /**
	   * This method sets the name of the maze.
	   * @param s <b>(String) </b>This is the parameter to the setName method
	   * @return Nothing.
	   */
	
	@Override
	public void setName(String string) 
	{
		this.MazeName = string;
		
	}
	

	
	
	
}
