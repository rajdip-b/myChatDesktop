package com.app.mychat.utils.classes.ui;

import javafx.animation.FillTransition;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static java.awt.Color.*;

public class Animations {

    private final FillTransition fillInTransition; // Mouse in
    private final FillTransition fillOutTransition; // Mouse out
    private final Rectangle r,r2,r1,r3;

    public Animations(){
        r = new Rectangle();
        r.setWidth(230);
        r.setHeight(40);
        r.setArcHeight(10);
        r.setArcWidth(10);
        r3= new Rectangle();
        r3.setWidth(230);
        r3.setHeight(40);
        r3.setArcHeight(25);
        r3.setArcWidth(25);
        r1= new Rectangle();
        r2= new Rectangle();
        r2.setArcHeight(25);
        r2.setArcWidth(25);
        r2.setWidth(1035);
        r2.setHeight(702);
        r1.setWidth(230);
        r1.setHeight(40);
        r1.setArcHeight(15);
        r1.setArcWidth(15);
        Color colorAzureBlue = Color.rgb(19, 155, 240);
        Color colorLightBlue = Color.rgb(111, 182, 227);
        Color colorLightGreen = Color.rgb(95,219,167);
        fillInTransition = new FillTransition(Duration.millis(200));
        fillInTransition.setFromValue(colorAzureBlue);
        fillInTransition.setToValue(colorLightBlue);
        fillInTransition.setCycleCount(1);
        fillOutTransition = new FillTransition(Duration.millis(200));
        fillOutTransition.setFromValue(colorLightBlue);
        fillOutTransition.setToValue(colorAzureBlue);
        fillOutTransition.setCycleCount(1);
    }

    public void setButtonAnimation(Button button) {
        button.setShape(r);
        button.getShape().fillProperty().addListener((observable, oldPaint, newPaint) -> {
            //set fill
            String style=String.format("-fx-background-color: #%s", newPaint.toString().substring(2));
            button.setStyle(style);
        });
        button.setOnMouseEntered(event -> {
            fillInTransition.setShape(button.getShape());
            fillInTransition.play();
        });
        button.setOnMouseExited(event -> {
            fillOutTransition.setShape(button.getShape());
            fillOutTransition.play();
        });
    }

}
