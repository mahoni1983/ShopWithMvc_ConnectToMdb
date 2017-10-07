import model.Model;
import view.ViewMainFrame;

public class Program {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Program is staring");
		ViewMainFrame viewMainFrame = new ViewMainFrame();
		
		Model model = new Model();
		Controller controller = new Controller(viewMainFrame, model);
	//	viewMainFrame.setProductList(model.getProductList());
		viewMainFrame.run();
	}

}
