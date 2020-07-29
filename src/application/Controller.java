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
		FileHandler file = new FileHandler();
		List<Imagem> imagens = new ArrayList<>();
		imagens = file.retornarImagens();		
		
		// TODO: Utilizar os valores da variável Range
		// TODO: Corrigir posições
		// Criar ImageView da imagem
		for (int i = 0; i < imagens.size(); i++)
		{
			ImageView imageView = new ImageView();
			Image image = new Image(new File(imagens.get(i).getNome()).toURI().toString());
			
			imageView.setImage(image);
			imageView.setX(converterCoordenada(imagens.get(i).getPosX(), 'x'));
			imageView.setY(converterCoordenada(imagens.get(i).getPosY(), 'y'));
			imageView.setFitHeight(imagens.get(i).getRangeY() * imagePane.getPrefHeight());
			imageView.setFitWidth(imagens.get(i).getRangeX() * imagePane.getPrefWidth());
					
			pane.getChildren().add(imageView);

		}
		
	}
	
	private float converterCoordenada(float coordenada, char tipo)
	{
		//float width = 720; (float) (1280 - imageView.getFitWidth());;
		float width = (float) imagePane.getPrefWidth();
		float height = (float) imagePane.getPrefHeight();	
		
		if (tipo=='x')
		{
			return coordenada * width;
		}
		if (tipo=='y')
		{
			return coordenada * height;
		}
		
		return 100f;
	}
 }