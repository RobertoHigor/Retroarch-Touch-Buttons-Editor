package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Controller implements Initializable { // Para carregar a imagem assim que abrir

	public Pane imagePane;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		AdicionarImagens(imagePane);
	}  
	
	private void AdicionarImagens(Pane pane)
	{
		List<Imagem> imagens = new ArrayList<>();
		Imagem img1 = new Imagem();
		Imagem img2 = new Imagem();
		Imagem img3 = new Imagem();
		Imagem img4 = new Imagem();
		
		img1.setNome("src\\application\\img\\left.png");
		img2.setNome("src\\application\\img\\right.png");
		img3.setNome("src\\application\\img\\up.png");
		img4.setNome("src\\application\\img\\down.png");
		
		// left
		img1.setPosX(0.25f);
		img1.setPosY(0.50f);
		
		// right
		img2.setPosX(0.75f);
		img2.setPosY(0.50f);
		
		// up
		img3.setPosX(0.50f);
		img3.setPosY(0.25f);
		
		// down
		img4.setPosX(0.50f);
		img4.setPosY(0.75f);
		
		
		imagens.add(img1);
		imagens.add(img2);
		imagens.add(img3);
		imagens.add(img4);
		
		
		for (int i = 0; i < imagens.size(); i++)
		{
			ImageView imageView = new ImageView();
			Image image = new Image(new File(imagens.get(i).getNome()).toURI().toString());
			
			imageView.setImage(image);
			imageView.setX(imagens.get(i).getPosX());
			imageView.setY(imagens.get(i).getPosY());
			
			pane.getChildren().add(imageView);
		}
		
	}
	
	private float converterCoordenada(float coordenada)
	{
		float width = 720; //(float) (1280 - imageView.getFitWidth());;
		System.out.println(width);
		return width * coordenada;
	}
 }