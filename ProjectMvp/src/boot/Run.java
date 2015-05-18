package boot;

import presenter.Presenter;
import model.MyModel;
import view.MyView;

public class Run {

	public static void main(String[] args) 
	{
		MyView v= new MyView();
		MyModel m = new MyModel();
		Presenter p = new Presenter(m, v);
		v.addObserver(p);
		m.addObserver(p);
		v.start();

	}

}
