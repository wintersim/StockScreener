package cc.catgasm.model;

import cc.catgasm.controller.StockViewController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    private Stage stage;

    @Override
    public void start(Stage tempStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/cc/catgasm/view/stockView.fxml").openStream());

        //Workaround because we cannot set the StageStyle UTILITY and UNDECORATED at the same time
        tempStage.initStyle(StageStyle.UTILITY);
        tempStage.setOpacity(0);
        tempStage.setHeight(0);
        tempStage.setWidth(0);
        tempStage.show();

        stage = new Stage();
        stage.initOwner(tempStage);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));

        final StockViewController svc = loader.getController();

        stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            KeyCode kc = event.getCode();
            if (KeyCode.ESCAPE == kc) {
                stage.close();
                tempStage.close();
                Platform.exit();
            } else if (kc.isFunctionKey()) { //Handle Function keys in Controller
                svc.updateTicker(kc);
            }
        });

        stage.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            KeyCode kc = event.getCode();
            if (event.isAltDown()) {
                if (KeyCode.UP == kc) {
                    updateWindowSize(10);
                } else if (KeyCode.DOWN == kc) {
                    updateWindowSize(-10);
                }
            } else {
                if (KeyCode.LEFT == kc) {
                    stage.setX(stage.getX() - 10);
                } else if (KeyCode.RIGHT == kc) {
                    stage.setX(stage.getX() + 10);
                }

                if (KeyCode.UP == kc) {
                    stage.setY(stage.getY() - 10);
                } else if (KeyCode.DOWN == kc) {
                    stage.setY(stage.getY() + 10);
                }
            }
        });

        stage.focusedProperty().addListener((observableValue, wasFocused, focused) -> stage.toBack());
        stage.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, mouseEvent -> stage.toBack());
        stage.show();
        putStageInCorner();
    }

    private void putStageInCorner() {
        final int margin = 10;
        Rectangle2D sb = Screen.getPrimary().getBounds();
        stage.setX((sb.getWidth() - stage.getWidth()) - margin);
        stage.setY(margin);
    }

    private void updateWindowSize(double factor) {
        stage.setWidth(stage.getWidth() + factor);
        stage.setHeight(stage.getHeight() + factor);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
