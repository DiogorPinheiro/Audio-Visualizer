/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audiointerface;

import javafx.beans.property.SimpleObjectProperty;
import java.io.File;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.net.URL;
import javafx.beans.InvalidationListener;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.beans.Observable;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.util.List;
import javafx.scene.chart.AreaChart;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author diogopinheiro
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button input;
    @FXML
    private Button output;
    @FXML
    private Button play;
    @FXML
    private Button stop;
    @FXML
    private Slider volume;
    @FXML
    private Slider pitch;
    @FXML
    private Slider pan;
    @FXML
    private TextField info;
    
   
    private DirectoryChooser directoryChooser = new DirectoryChooser();
    private FileChooser mediaFileChooser = new FileChooser();
    private static SimpleObjectProperty<File> lastKnownDirectoryProperty = new SimpleObjectProperty<>(); 
    MediaPlayer player;
    
    public void inputAudio(){ // Abrir explorador de ficheiros para receber .mp3 ou .wab
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Mp3 Files", "*.mp3"));
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Wav Files", "*.wav"));
	File selectedFile = fileChooser.showOpenDialog(null);
        
		if (selectedFile != null) {

			info.setText("File selected: " + selectedFile.getName());
                        final Media audio = new Media(selectedFile.toURI().toString());
                        player = new MediaPlayer(audio);
                        player.setOnEndOfMedia( () -> player.stop());
		}
		else {
			info.setText("File selection cancelled.");
		}
    }
     
    public void playAudio(){
        player.play();
    }
    
    public void stop(){
        player.stop();
    }

   

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        volume.setValue(player.getVolume() * 100);
        volume.valueProperty().addListener(new InvalidationListener(){
            @Override
            public void invalidated(Observable observable){
                player.setVolume(volume.getValue()/100);
            }
        });   
              
    }   
    
}
