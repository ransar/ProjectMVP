package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class CliMVP extends CLI{
	MyView v;
	public CliMVP(BufferedReader in, PrintWriter out) 
	{
		super(in, out);
		
		// TODO Auto-generated constructor stub
	}
	public void setHashMap(HashMap<String, Command> h)
	{
		for (String s : h.keySet()) 
		{
			addCommand(h.get(s), s);
		}
	}
	public void setView(MyView v2)
	{
		this.v=v2;
	}
	@Override
	public void start()
	{
		String arg = "";
		boolean flag=false;
		String commandName="";
		System.out.print("Enter command: ");
		try {
			String line = in.readLine();
			while (!line.equals("exit"))
			{
				String[] sp = line.split(" ");
				if (sp.length > 2)
				{
					commandName = sp[0] + " " + sp[1];
					flag=true;
				}
				else if(sp.length == 2)
				{
					commandName = sp[0];
					flag=true;
				}
				if(flag==true)
				{
					if(sp.length==2)
					{
						arg=sp[1];
						Command command = this.userCommands.selectCommand(commandName);
						command.doCommand(arg);
					}
					else if (sp.length > 2)
					{
						for(int i=2;i<sp.length;i++)
						{
							arg += sp[i];
							arg += " ";
						}
						arg = arg.substring(0, arg.length()-1);
						if(userCommands.commands.containsKey(commandName))
						{
							Command command = this.userCommands.selectCommand(commandName);
							v.command = command;
							v.setChangedFunc();
							v.notifyObservers(arg);
						}
					}
					else
						System.out.println("Wrong amout of words, sould be at least 2");
					
				}
				else
					System.out.println("Wrong command name,enter another one : ");
				System.out.print("Enter command: ");
				line = in.readLine();
			}
			if(line.equals("exit"))
			{
				System.out.println("exiting");
				v.setChangedFunc();
				v.notifyObservers("finish");
			}
						
		} catch (IOException e) {			
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {				
				e.printStackTrace();
			}			
		}	
	}

}
