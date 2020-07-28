package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
	
	Pane rootLayout;
	@Override
	public void start(Stage primaryStage) {
		try {
			// Stage > Scene
			
			// Carregar FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("FXMLtela.fxml"));			
			rootLayout = loader.load();	
			
			// Definindo layout
			Scene scene = new Scene(rootLayout);
			
			primaryStage.setTitle("Oi");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args); // chama o método start
	}
}
