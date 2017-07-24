/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swipeaction;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author blj0011
 */
public class FXMLDocumentController implements Initializable
{
    @FXML private Pane paneBoard;    
    @FXML private Label label;
    @FXML private Button btnUp, btnDown, btnLeft, btnRight;
    
    
    List<ImageView> imageViewData = new ArrayList();
    ObservableList<ImageView> ImageViewContainer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try 
        {
            ImageView iv2 = new ImageView();
            iv2.setFitHeight(100);
            iv2.setFitWidth(100);
            iv2.setLayoutX(100);
            iv2.setLayoutY(100);            
            Image iamge2 = new Image(getClass().getResource("images/archive-2.png").toURI().toString());
            iv2.setImage(iamge2);
            imageViewData.add(iv2);
            
            
            ImageView iv1 = new ImageView();
            iv1.setFitHeight(100);
            iv1.setFitWidth(100);
            iv1.setLayoutX(100);
            iv1.setLayoutY(200);            
            Image iamge1 = new Image(getClass().getResource("images/archive-2.png").toURI().toString());
            iv1.setImage(iamge1);
            imageViewData.add(iv1);
            
            paneBoard.getChildren().addAll(imageViewData);
            
        }
        catch (URISyntaxException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    
    @FXML void handleBtnUp(ActionEvent event)
    {   
        disableAllButton(true);
        
        new AnimationTimer(){
            @Override
            public void handle(long now)
            {
                for(ImageView item : imageViewData)
                {
                    item.setLayoutY(item.getLayoutY() - 5);
                    if(item.getBoundsInParent().getMinY() <= 0)
                    {
                        item.setLayoutY(0);
                        this.stop();
                        disableAllButton(false);
                    }
                }
            }
            
        }.start();
    }
    
    @FXML void handleBtnDown(ActionEvent event)
    {
        disableAllButton(true);
        new AnimationTimer(){
            @Override
            public void handle(long now)
            {
                for(ImageView item : imageViewData)
                {
                    item.setLayoutY(item.getLayoutY()+ 5);
                    if(item.getBoundsInParent().getMaxY()>= paneBoard.getHeight())
                    {
                        item.setLayoutY(paneBoard.getHeight() - item.getFitHeight());
                        this.stop();
                        disableAllButton(false);
                    }
                }
            }            
        }.start();        
    }
    
    @FXML void handleBtnLeft(ActionEvent event)
    {
        disableAllButton(true);
        new AnimationTimer(){
            @Override
            public void handle(long now)
            {
                for(ImageView item : imageViewData)
                {
                    item.setLayoutX(item.getLayoutX() - 5);
                    if(item.getBoundsInParent().getMinX() <= 0)
                    {
                        item.setLayoutX(0);
                        this.stop();
                        disableAllButton(false);
                    }
                }
            }            
        }.start();
    }
    
    @FXML void handleBtnRight(ActionEvent event)
    {
        disableAllButton(true);
        new AnimationTimer(){
            @Override
            public void handle(long now)
            {
                for(ImageView item : imageViewData)
                {
                    item.setLayoutX(item.getLayoutX() + 5);
                    if(item.getBoundsInParent().getMaxX() >= paneBoard.getWidth())
                    {
                        item.setLayoutX(paneBoard.getWidth() - item.getFitWidth());
                        this.stop();
                        disableAllButton(false);
                    }
                }
            }
            
        }.start();        
    }
    
    private void disableAllButton(boolean control)
    {
        btnUp.setDisable(control);
        btnDown.setDisable(control);
        btnLeft.setDisable(control);
        btnRight.setDisable(control);
    }
}
