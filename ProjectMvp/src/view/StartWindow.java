package view;

import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;

import presenter.Presenter;
import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;

public class StartWindow extends BasicWindow implements View
{
	Presenter p;
	public StartWindow(String title, int width, int height) 
	{
		super(title, width, height);
		

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Command getUserCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void displayMaze(Maze m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySolution(Solution s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCommands(HashMap<String, Command> commands2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printMessage(String str) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void initWidgets() 
	{
		shell.setLayout(new GridLayout(2,false));
		
		Button a=new Button(shell, SWT.PUSH);
		a.setText("Generate a maze");
		a.setLayoutData(new GridData(SWT.NONE,SWT.NONE, false,false));
		MazeDisplay maze=new MazeDisplay(shell, SWT.BORDER);
		maze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true));
		a.addSelectionListener(new SelectionListener() 
		{
			
			
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
			

		
	}
	
}
