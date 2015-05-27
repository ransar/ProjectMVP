package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import presenter.Presenter;
import algorithms.mazeGenerators.DFSMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MazeGenerator;
import algorithms.search.Solution;

public class StartWindow extends BasicWindow implements View
{
	Presenter p;
	CliMVP c;
	int numR = 0;
	int numC = 0;
	Maze m;
	Solution sol;
	MazeDisplay maze;
	public StartWindow(String title, int width, int height) 
	{
		super(title, width, height);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter writer = new PrintWriter(System.out);
		this.c=new CliMVP(reader, writer);
		c.setView(this);

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
		
		Label numOfRows = new Label(shell, SWT.COLOR_BLUE);
		numOfRows.setText("Choose the number of rows and columns");
		numOfRows.setLayoutData(new GridData(SWT.NONE,SWT.NONE, false,false,1,1));
		maze=new MazeDisplay(shell, SWT.BORDER);
		maze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,7));
		maze.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseScrolled(MouseEvent e) 
			{
				//MazeDisplay.this.getSize().x *  (int) 1.1;
				if((e.stateMask & SWT.CTRL) != 0)
				{
					if(e.count > 0)
					{
						maze.setSize((int)((maze.getSize().x)*1.1),(int)((maze.getSize().y)*1.1));
						maze.redraw();
					}
					else 
					{
						maze.setSize((int)((maze.getSize().x)/1.1),(int)((maze.getSize().y)/1.1));
						maze.redraw();
					
					}
						
				}
				
			}
		});
		Combo rows = new Combo(shell, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);
		rows.setLayoutData(new GridData(SWT.NONE,SWT.NONE, false,false,1,1));
		for(int i=2;i<11;i++)
		{
			rows.add(i + " rows");
		}
		Label numOfCols = new Label(shell, SWT.COLOR_BLUE);
		numOfCols.setText("Choose the number of columns");
		numOfCols.setLayoutData(new GridData(SWT.NONE,SWT.NONE, false,false,1,1));
		Combo cols = new Combo(shell, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);
		cols.setLayoutData(new GridData(SWT.NONE,SWT.NONE, false,false,1,1));
		for(int i=2;i<11;i++)
		{
			cols.add(i + " columns");
		}
		Button a=new Button(shell, SWT.PUSH);
		a.setText("Generate the maze");
		a.setLayoutData(new GridData(SWT.NONE,SWT.NONE, false,false,1,1));
		Button hint=new Button(shell, SWT.PUSH);
		hint.setText("Give me a hint ✆");
		hint.setLayoutData(new GridData(SWT.NONE,SWT.NONE, false,false,1,1));
		Button solve=new Button(shell, SWT.PUSH);
		solve.setText("Solve the maze ☺");
		solve.setLayoutData(new GridData(SWT.NONE,SWT.NONE, false,false,1,1));
		cols.addSelectionListener(new SelectionListener() 
		{
			
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				for(int i=2;i<11;i++)
				{
					StringBuilder str= new StringBuilder();
					str.append(i);
					str.append(" columns");
					if(cols.getText().equals(str.toString()))
						numC = i;
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		rows.addSelectionListener(new SelectionListener() 
		{
			
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				for(int i=2;i<11;i++)
				{
					StringBuilder str= new StringBuilder();
					str.append(i);
					str.append(" rows");
					if(rows.getText().equals(str.toString()))
						numR = i;
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		a.addSelectionListener(new SelectionListener() 
		{
			
			
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				MazeGenerator mg=new DFSMazeGenerator();
				m = mg.generateMaze(numR,numC);
				maze.start(m);
				/*notifyObservers("generate maze gogo 10 10");
				m = p.getM().getMaze();
				maze.start(m);*/
				maze.forceFocus();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
			
		hint.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		solve.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		

		
	}
	
}
