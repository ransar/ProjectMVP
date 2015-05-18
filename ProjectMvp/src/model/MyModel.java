package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
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

public class MyModel extends Observable implements Model
{
	ExecutorService executor;
	Maze maze;
	Solution sol;
	String MazeName;
	HashMap<String,HashMap<Maze, Solution>> msols;
	
	 /**
	   * This method converts the data in an InputStream to a String.
	   * @param in <b>(InputStream) </b>This is the parameter to the fromStream method
	   * @return s <b>(String) </b>
	   */
	

	
	public MyModel() 
	{
		this.executor = Executors.newCachedThreadPool();
		msols = new HashMap<String, HashMap<Maze,Solution>>();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		MazeSolutionHibernate ms = new MazeSolutionHibernate();
		//ms = (MazeSolutionHibernate) session.get(MazeSolutionHibernate.class,"gogo");
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
			for(int i=0;i<names.length;i++)
			{
				ms = (MazeSolutionHibernate) session.get(MazeSolutionHibernate.class,names[i]);
				HashMap<Maze, Solution> temp = new HashMap<Maze, Solution>();
				//temp.put(ms.stringToMaze(ms.getMaze()), ms.stringToSolution(ms.getSol()));
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
		} catch (ExecutionException e) 
		{
			e.printStackTrace();
		}
		
		
	}

	@Override
	public Maze getMaze() 
	{
		System.out.println("get maze");
		if(maze==null)
		{
			System.out.println("No maze yet");
			return null;
		}
		return maze;
	}

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
			msh.setMaze(maze.toString());
			msh.setSol(sol.toString());
			msh.setId(MazeName);
			
			session.save(msh);
			session.getTransaction().commit();
			
			session.close();
			
		}
		
	}

	@Override
	public Solution getSolution() 
	{
		System.out.println("get solution of the maze");
		if(sol==null)
		{
			System.out.println("No solution yet");
			return null;
		}
		return sol;
	}

	@Override
	public void stop() 
	{
		
		System.out.println("stop");
		if(executor.isShutdown())
			return;
		executor.shutdown();
		
	}
	@Override
	public void setName(String string) 
	{
		this.MazeName = string;
		
	}
	

	
	
	
}
