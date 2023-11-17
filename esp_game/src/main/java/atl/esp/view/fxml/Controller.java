package atl.esp.view.fxml;

import atl.esp.exception.RepositoryException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import atl.esp.model.Facade;

import javafx.scene.control.Button;

/**
 * This controls the whole game
 *
 * @author Paul et Léopold
 */
public class Controller {

    @FXML
    private TextArea areaLabels;
    @FXML
    private TextArea areaWords;
    @FXML
    private TextArea areaInfo;
    @FXML
    private ImageView areaImage;
    @FXML
    private TextField textField;
    @FXML
    private Button btnSend;

    private final Facade game;
    private final int playerId;

    private String info;
    private String words;

    public Controller(Facade game, int playerId) throws RepositoryException {
        this.game = game;
        this.playerId = playerId;
        info = "";
        words = "";
    }

    @FXML
    private void buttonHandler() throws RepositoryException {
        String proposition = this.textField.getText();
        if (!proposition.isEmpty()) {
            if (!this.game.hasLabel(proposition)) {
                this.game.propose(proposition, this.playerId);
                this.textField.clear();
            }
        }
    }

    public void updateImg(String path) {

        path = path.substring(26);
        System.out.println(path + "je suis dans updateImg");
        this.areaImage.setImage(new Image("images/" + path));
        
        this.info += "Mise à jour de l'image : " + path + "\n";
        this.areaInfo.setText(this.info);
    }

    public void updateDB(List<String> labels) {
        this.areaLabels.clear();
        String labelStr = "";
        for (String label : labels) {
            labelStr += label;
            labelStr += "\n";
        }
        this.areaLabels.setText(labelStr);
        this.info += "Mise à jour des labels : " + labels.toString() + "\n";
        this.areaInfo.setText(this.info);
    }

    public void updateProps(List<String> words) {
        this.words = "";
        words.forEach((word) -> {
            this.words += word;
            this.words += "\n";
        });
        this.areaWords.setText(this.words);
        this.info += "Mise à jour des propostions : " + words.toString() + "\n";
        this.areaInfo.setText(this.info);
    }

    public void clearAll() {
        this.areaWords.clear();
        this.words = "";
    }

}
