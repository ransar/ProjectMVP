package view;

import model.MazeSolutionHibernate;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import algorithms.mazeGenerators.DFSMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MazeGenerator;

public class MazeDisplay extends Canvas
{

	public MazeDisplay(Composite parent, int style) 
	{
		super(parent, style);
		MazeSolutionHibernate ms = new MazeSolutionHibernate();
		MazeGenerator mg=new DFSMazeGenerator();
		Maze maze = mg.generateMaze(8,8);
		setBackground(new Color(null, 255, 255, 255));
		//Image st1 = new Image(display,)
		addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				   e.gc.setForeground(new Color(null,0,0,0));
				   e.gc.setBackground(new Color(null,0,0,0));

				   int width=getSize().x;
				   int height=getSize().y;

				   int w=width/maze.getRows();
				   int h=height/maze.getCols();

				   for(int i=0;i<maze.getRows();i++)
				   {
					   maze.getCell(i, 0).setHasRightWall(true);
				   }
				   for(int j=0;j<maze.getCols();j++)
				   {
					   maze.getCell(0, j).setHasTopWall(true);
				   }
				   for(int i=0;i<maze.getRows();i++)
				      for(int j=0;j<maze.getCols();j++)
				      {
				          int x=j*w;
				          int y=i*h;
				          if(i==0 && j==0)
				          {
					          if(maze.getCell(i, j).getHasLeftWall() && maze.getCell(i, j).getHasBottomWall())
					        	  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 4.jpg")), x, y);
					          else 
					          {
					        	  if(!maze.getCell(i, j).getHasBottomWall() && !maze.getCell(i, j).getHasLeftWall())
					        		  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 3.jpg")), x, y);
					        	  else
					        	  {
					        		  if(maze.getCell(i, j).getHasBottomWall())
					        			  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 2.jpg")), x, y);
					        		  else
					        			  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 1.jpg")), x, y);
					        	  }
				        	  }
				          }
				          if(i==0 && j!=0)
				          {
					          if(maze.getCell(i, j).getHasLeftWall() && maze.getCell(i, j).getHasBottomWall())
					        	  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 4.jpg")), x, y);
					          else 
					          {
					        	  if(!maze.getCell(i, j).getHasBottomWall() && !maze.getCell(i, j).getHasLeftWall())
					        		  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 3.jpg")), x, y);
					        	  else
					        	  {
					        		  if(maze.getCell(i, j).getHasBottomWall())
					        			  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 2.jpg")), x, y);
					        		  else
					        			  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 1.jpg")), x, y);
					        	  }
				        	  } 
				          }
				          if(j==0 && i!=0)
				          {
					          if(maze.getCell(i, j).getHasLeftWall() && maze.getCell(i, j).getHasBottomWall())
					        	  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 4.jpg")), x, y);
					          else 
					          {
					        	  if(!maze.getCell(i, j).getHasBottomWall() && !maze.getCell(i, j).getHasLeftWall())
					        		  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 3.jpg")), x, y);
					        	  else
					        	  {
					        		  if(maze.getCell(i, j).getHasBottomWall())
					        			  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 2.jpg")), x, y);
					        		  else
					        			  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 1.jpg")), x, y);
					        	  }
				        	  }
				          }
				          else
				          {
					          if(maze.getCell(i, j).getHasLeftWall() && maze.getCell(i, j).getHasBottomWall())
					        	  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 4.jpg")), x, y);
					          else 
					          {
					        	  if(!maze.getCell(i, j).getHasBottomWall() && !maze.getCell(i, j).getHasLeftWall())
					        		  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 3.jpg")), x, y);
					        	  else
					        	  {
					        		  if(maze.getCell(i, j).getHasBottomWall())
					        			  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 2.jpg")), x, y);
					        		  else
					        			  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 1.jpg")), x, y);
					        	  }
				        	  }
				          }
				      }

			}
				
		});
	}

}
