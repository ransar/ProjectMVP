package boot;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.IOException;

import model.MyModel;
import presenter.Presenter;
import presenter.Properties;
import view.MyView;

///////Submitted by: Sarusi Ran 208631143, Gershfeld Itzik 208491886


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
		
		///////////////////////////////////////////////////////////////////
		MyModel m = new MyModel(new Properties());
		MyView v = new MyView();
		Properties pro;
		if((pro=readProperties())!=null)
			m=new MyModel(pro);
		else
			m=new MyModel(new Properties());
		Presenter p = new Presenter(m,v);
		m.addObserver(p);
		v.addObserver(p);
		v.start();
	}
	
	private static Properties readProperties()
	{
		XMLDecoder d;
		Properties p=null;
		try {
			FileInputStream in=new FileInputStream("properties.xml");
			d=new XMLDecoder(in);
			p=(Properties)d.readObject();
			d.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}
	

}
