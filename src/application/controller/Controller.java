package application.controller;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.model.Imagem;
import application.util.ConfigFileReader;
import application.util.FileReader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class Controller implements Initializable { // Para carregar a imagem assim que abrir

	public Pane imagePane;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		AdicionarImagens(imagePane);
	}  
	
	private void AdicionarImagens(Pane pane)
	{
		FileReader config = new ConfigFileReader();
		config.setConfig("teste");
		List<Imagem> imagens = config.retornarImagens();		
		
		//addBackground(pane);
		
		for (int i = 0; i < imagens.size(); i++)
		{
			// img pnel
			ImageView imageView = new ImageView();
			Imagem tmpImg = imagens.get(i);
			
			String imgName = tmpImg.getNome();
			System.out.println(imgName);
			Image image = new Image(
					new File("src/application/img/"+imgName).toURI().toString());
			
			setImageViewAttributes(imageView, tmpImg, image);
					
			pane.getChildren().add(imageView);
		}	

	}

	private void addBackground(TilePane pane) {
		// Criar ImageView da imagem
		ImageView background = new ImageView();
		Image backgroundImage = new Image(new File("src/application/img/background.png").toURI().toString());
		background.setImage(backgroundImage);
		background.setFitWidth(1280);
		background.setFitHeight(720);

		pane.getChildren().add(background);
	}

	// Get the Imagem class attributes and put it on the ImageView
	private void setImageViewAttributes(ImageView imageView, Imagem tmpImg, Image image) {
		// Set image attributes
		imageView.setImage(image);
		imageView.setX(converterCoordenada(tmpImg.getPosX(), 'x'));
		imageView.setY(converterCoordenada(tmpImg.getPosY(), 'y'));
		imageView.setFitHeight(image.getHeight() + (image.getHeight() * tmpImg.getRangeY()) * 2);
		imageView.setFitWidth(image.getWidth() + (image.getWidth() * tmpImg.getRangeX()) * 2);
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