package view;


import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
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

		
	}
	public void displayMaze(Maze maze)
	{
		setBackground(new Color(null, 255, 255, 255));
		addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				   e.gc.setForeground(new Color(null,0,0,0));
				   e.gc.setBackground(new Color(null,0,0,0));
				   //int width = (maze.getCols())*100;
				   //int height = (maze.getRows())*100;
				   //setSize(width, height);
				   int width=getSize().x;
				   int height=getSize().y;
				   System.out.println(width);
				   System.out.println(height);
				   int w = 100;
				   int h = 100;
				 //  int w=width/(maze.getRows());
				   //int h=height/(maze.getCols());

				   for(int i=0;i<maze.getRows();i++)
				      for(int j=0;j<maze.getCols();j++)
				      {
				          int x=j*w;
				          int y=i*h;
				          if(i==0 && j==0)
				          {
					          if(maze.getCell(i, j).getHasLeftWall() && maze.getCell(i, j).getHasBottomWall())
					        	  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 4.png")), x, y);
					          else 
					          {
					        	  if(!maze.getCell(i, j).getHasBottomWall() && !maze.getCell(i, j).getHasLeftWall())
					        		  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 12.png")), x, y);
					        	  else
					        	  {
					        		  if(maze.getCell(i, j).getHasBottomWall())
					        			  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 9.png")), x, y);
					        		  else
					        			  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 5.png")), x, y);
					        	  }
				        	  }
				          }
				          else
				          {
				        	  	if(i==0 && j!=0)
				        	  	{
						          if(maze.getCell(i, j).getHasLeftWall() && maze.getCell(i, j).getHasBottomWall())
						        	  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 16.png")), x, y);
						          else 
						          {
						        	  if(!maze.getCell(i, j).getHasBottomWall() && !maze.getCell(i, j).getHasLeftWall())
						        		  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 7.png")), x, y);
						        	  else
						        	  {
						        		  if(maze.getCell(i, j).getHasBottomWall())
						        			  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 6.png")), x, y);
						        		  else
						        			  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 13.png")), x, y);
						        	  }
					        	  } 
					          }
				          
				          else
				          {
				        	  if(j==0 && i!=0)
					          {
						          if(maze.getCell(i, j).getHasLeftWall() && maze.getCell(i, j).getHasBottomWall())
						        	  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 10.png")), x, y);
						          else 
						          {
						        	  if(!maze.getCell(i, j).getHasBottomWall() && !maze.getCell(i, j).getHasLeftWall())
						        		  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 8.png")), x, y);
						        	  else
						        	  {
						        		  if(maze.getCell(i, j).getHasBottomWall())
						        			  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 14.png")), x, y);
						        		  else
						        			  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 15.png")), x, y);
						        	  }
					        	  }
					          }
				        	  else
					          {
						          if(maze.getCell(i, j).getHasLeftWall() && maze.getCell(i, j).getHasBottomWall())
						        	  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 4.png")), x, y);
						          else 
						          {
						        	  if(!maze.getCell(i, j).getHasBottomWall() && !maze.getCell(i, j).getHasLeftWall())
						        		  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 3.png")), x, y);
						        	  else
						        	  {
						        		  if(maze.getCell(i, j).getHasBottomWall())
						        			  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 2.png")), x, y);
						        		  else
						        			  e.gc.drawImage(new Image(Display.getCurrent(),new ImageData("resources/state 1.png")), x, y);
						        	  }
					        	  }
					          }
				          }
				          }
				         
				      }
				   
			}
				
		});
	}
	public void start(Maze maze)
	{
		displayMaze(maze);
	}

}
