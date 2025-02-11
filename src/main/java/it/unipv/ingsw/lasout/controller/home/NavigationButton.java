package it.unipv.ingsw.lasout.controller.home;

import io.github.palexdev.materialfx.controls.MFXButton;

import java.io.IOException;

public class NavigationButton extends MFXButton {

    public NavigationButton(HomeController homeController, String text, String path) {

        super(text);
        setMaxWidth(Double.MAX_VALUE);
        setMinHeight(45);


        setOnAction(event->{
            homeController.changeActive(this);
            homeController.setDisplay(path);
        });
    }
}
