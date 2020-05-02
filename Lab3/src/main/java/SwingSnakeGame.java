import model.Model;
import viewcontroller.ViewController;
import viewcontroller.view.View;

public class SwingSnakeGame {
	private final Model model;
	private final View view;
	private final ViewController viewController;

	public SwingSnakeGame(){
		model = new Model();
		view = new View();
		viewController = new ViewController(model, view);
		model.addObserver(viewController);
	}

	public void start() {
		view.setVisible(true);
	}
}
