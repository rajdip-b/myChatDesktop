package com.app.mychat.utils.classes;

import javafx.animation.FillTransition;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Animations {

    private final FillTransition fillInTransition; // Mouse in
    private final FillTransition fillOutTransition; // Mouse out
    private final Rectangle r;

    public Animations(){
        r = new Rectangle();
        r.setWidth(230);
        r.setHeight(40);
        r.setArcHeight(10);
        r.setArcWidth(10);
        Color colorAzureBlue = Color.rgb(19, 155, 240);
        Color colorLightBlue = Color.rgb(111, 182, 227);
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
