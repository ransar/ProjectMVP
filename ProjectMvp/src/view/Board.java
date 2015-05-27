package view;



import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import algorithms.mazeGenerators.Cell;
import algorithms.mazeGenerators.Maze;

public class Board extends Composite
{
	
	int mazeR;
	int mazeC;
	Tile[][] tiles;

	public Board(Composite parent, int style) 
	{
		super(parent, style);
		addPaintListener(new PaintListener() 
		{
			
			@Override
			public void paintControl(PaintEvent arg0) 
			{
				if(tiles!=null)
					for(int i=0;i<tiles.length;i++)
						for(int j=0;j<tiles[0].length;j++)
							tiles[i][j].redraw();
			}
		});
	}
	
	public void displayMaze(Maze m)
	{
		if(tiles!=null) 
			delMaze();
		mazeR=m.getRows();
		mazeC=m.getCols();
		GridLayout layout=new GridLayout(mazeC, true);
		layout.horizontalSpacing=0;
		layout.verticalSpacing=0;
		//setLayoutData(layout);
		setLayout(layout);
		tiles=new Tile[mazeR][mazeC];
		for(int i=0;i<mazeR;i++)
			for(int j=0;j<mazeC;j++)
			{
				tiles[i][j]=new Tile(this,SWT.NONE);
				tiles[i][j].setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				tiles[i][j].setImage(setImg(m,i,j));
			}
		layout();
		//redraw();
	}
	private void delMaze()
	{
		for(int i=0;i<tiles.length;i++)
			for(int j=0;j<tiles[0].length;j++)
			{
				tiles[i][j].dispose();
			}
	}
	private Image setImg(Maze maze,int i,int j)
	{
		if(i==0 && j==0)
        {
	          if(maze.getCell(i, j).getHasLeftWall() && maze.getCell(i, j).getHasBottomWall())
	        	  return new Image(getDisplay(),new ImageData("resources/state 4.png"));
	          else 
	          {
	        	  if(!maze.getCell(i, j).getHasBottomWall() && !maze.getCell(i, j).getHasLeftWall())
	        		  return new Image(getDisplay(),new ImageData("resources/state 12.png"));
	        	  else
	        	  {
	        		  if(maze.getCell(i, j).getHasBottomWall())
	        			  return new Image(getDisplay(),new ImageData("resources/state 9.png"));
	        		  else
	        			  return new Image(getDisplay(),new ImageData("resources/state 5.png"));
	        	  }
      	  }
        }
        else
        {
      	  	if(i==0 && j!=0)
      	  	{
		          if(maze.getCell(i, j).getHasLeftWall() && maze.getCell(i, j).getHasBottomWall())
		        	  return new Image(getDisplay(),new ImageData("resources/state 16.png"));
		          else 
		          {
		        	  if(!maze.getCell(i, j).getHasBottomWall() && !maze.getCell(i, j).getHasLeftWall())
		        		  return new Image(getDisplay(),new ImageData("resources/state 7.png"));
		        	  else
		        	  {
		        		  if(maze.getCell(i, j).getHasBottomWall())
		        			  return new Image(getDisplay(),new ImageData("resources/state 6.png"));
		        		  else
		        			  return new Image(getDisplay(),new ImageData("resources/state 13.png"));
		        	  }
	        	  } 
	          }
        
        else
        {
      	  if(j==0 && i!=0)
	          {
		          if(maze.getCell(i, j).getHasLeftWall() && maze.getCell(i, j).getHasBottomWall())
		        	  return new Image(getDisplay(),new ImageData("resources/state 10.png"));
		          else 
		          {
		        	  if(!maze.getCell(i, j).getHasBottomWall() && !maze.getCell(i, j).getHasLeftWall())
		        		  return new Image(getDisplay(),new ImageData("resources/state 8.png"));
		        	  else
		        	  {
		        		  if(maze.getCell(i, j).getHasBottomWall())
		        			  return new Image(getDisplay(),new ImageData("resources/state 14.png"));
		        		  else
		        			  return new Image(getDisplay(),new ImageData("resources/state 15.png"));
		        	  }
	        	  }
	          }
      	  else
	          {
		          if(maze.getCell(i, j).getHasLeftWall() && maze.getCell(i, j).getHasBottomWall())
		        	  return new Image(getDisplay(),new ImageData("resources/state 4.png"));
		          else 
		          {
		        	  if(!maze.getCell(i, j).getHasBottomWall() && !maze.getCell(i, j).getHasLeftWall())
		        		  return new Image(getDisplay(),new ImageData("resources/state 3.png"));
		        	  else
		        	  {
		        		  if(maze.getCell(i, j).getHasBottomWall())
		        			  return new Image(getDisplay(),new ImageData("resources/state 2.png"));
		        		  else
		        			  return new Image(getDisplay(),new ImageData("resources/state 1.png"));		        	  }
	        	  }
	          }
        }
        }
		
	}
	
	
}
