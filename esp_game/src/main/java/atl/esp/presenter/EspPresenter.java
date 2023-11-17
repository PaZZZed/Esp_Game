package atl.esp.presenter;

import atl.esp.exception.RepositoryException;
import atl.esp.model.Facade;
import atl.esp.view.fxml.Controller;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Paul et Léopold
 */
public class EspPresenter implements Presenter {

    private final int idPlayer;
    private final Facade game;
    private final Controller mfxCtrl;
    private boolean setRestart = false;

    private String lastUrl;

    public EspPresenter(int idPlayer, Facade game, Stage stage) throws RepositoryException, IOException {
        this.idPlayer = idPlayer;
        this.game = game;
        //new URL("/fxml/monFXML.fxml");
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/espGame.fxml"));
        this.mfxCtrl = new Controller(game, idPlayer);
        loadFXML(fxml, stage);
    }

    private void loadFXML(FXMLLoader loader, Stage stage) throws IOException {
        loader.setController(this.mfxCtrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize() {
        send();
        this.lastUrl = this.game.getImage().getPath();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    @Override
    public void send() {
        //System.out.println(this.game.getImage().getPath());
        this.mfxCtrl.updateImg(this.game.getImage().getPath());
        this.mfxCtrl.updateDB(this.game.getLabels());
        this.mfxCtrl.clearAll();
    }

    @Override
    public void update(Facade observable) {
        if (this.game.isOver()) {
            System.out.println("on turn");
            this.game.nextTurn();
            send();
        } else if (!this.lastUrl.equals(this.game.getImage().getPath())) {
              System.out.println("on else if");
            send();
            this.lastUrl = this.game.getImage().getPath();
        } else {
              System.out.println("on else");
            this.mfxCtrl.clearAll();
            if (!this.game.isPreviousProposal(this.lastUrl, this.idPlayer)) {
                this.mfxCtrl.updateProps(this.game.getWordss(this.idPlayer));
            }
        }
    }

}
