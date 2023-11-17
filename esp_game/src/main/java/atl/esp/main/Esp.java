package atl.esp.main;

import atl.esp.config.ConfigManager;
import atl.esp.model.*;
import atl.esp.presenter.EspPresenter;
import atl.esp.presenter.Presenter;
import atl.esp.view.fx.ViewFX;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Paul et Léopold
 */
public class Esp extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de données stockée : " + dbUrl);

            Stage secondStage = new Stage();
            Facade game = new Game();
            game.start();
            Presenter p1 = new EspPresenter(1, game, stage);
            Presenter p2 = new EspPresenter(2, game, secondStage);
            game.addObserver(p1);
            game.addObserver(p2);
            p1.initialize();
            p2.initialize();
        } catch (IOException ex) {
            System.out.println("Erreur IO " + ex.getMessage());
        }
    }
}
