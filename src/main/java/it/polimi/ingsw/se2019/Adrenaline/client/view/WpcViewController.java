package it.polimi.ingsw.se2019.Adrenaline.client.view;

import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.logging.Logger;

public class WpcViewController extends GUIController {

    @FXML
    private AnchorPane root;
    @FXML
    private Label firstWPC;
    @FXML
    private Label secondWPC;
    @FXML
    private Label thirdWPC;
    @FXML
    private Label fourthWPC;
    @FXML
    private GridPane firstGrid;
    @FXML
    private GridPane secondGrid;
    @FXML
    private GridPane thirdGrid;
    @FXML
    private GridPane fourthGrid;
    @FXML
    private ImageView pOCImage;

    private static final String GRID_STYLE = "gridStyle";
    private static final String LABEL_EFFECT = "labelEffect";
    private MatchViewController matchViewController;


    public WpcViewController(BoardStatus boardStatus, MatchViewController matchViewController) {
        this.boardStatus = boardStatus;
        this.matchViewController = matchViewController;
    }

    /**
     *
     * The checkChoice method is used to memorize the
     * choice of the player.
     * @param windowPatternCardIndex chosen windowPatternCard.
     *
     */

    private void checkChoice(int windowPatternCardIndex) {
        if (windowPatternCardIndex > 0 && windowPatternCardIndex <= 4) {
            matchViewController.notify(Integer.toString(windowPatternCardIndex));
            matchViewController.setInit();
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
        } else {
            Logger.getGlobal().warning("It's null!");
        }
    }




}
