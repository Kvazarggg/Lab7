package sample.test_fx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableWithDataController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<BreakingBadPhrase> TableData;

    @FXML
    private TableColumn<BreakingBadPhrase, String> author;

    @FXML
    private TableColumn<BreakingBadPhrase, String> quote;

    @FXML
    private TableColumn<BreakingBadPhrase, Integer> quoteId;

    @FXML
    private TableColumn<BreakingBadPhrase, String> series;

    ObservableList<BreakingBadPhrase> list = FXCollections.observableArrayList(
            new BreakingBadPhrase(1, "ergeg", "gergeg", "rgerg"),
            new BreakingBadPhrase(2, "FEFEF", "Walter White", "Breaking Bad"),
            new BreakingBadPhrase(3, "GRGRG", "Walter Black", "foo")
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JSONGetter jsonGetter = new JSONGetter();
        JSONGetter.url = "https://breakingbadapi.com/api/quotes";
        jsonGetter.run();

        Phrases phrases = new Phrases();
        phrases.ReadAndParseToList(jsonGetter);

        quoteId.setCellValueFactory(new PropertyValueFactory<BreakingBadPhrase, Integer>("quoteId"));
        quote.setCellValueFactory(new PropertyValueFactory<BreakingBadPhrase, String>("quote"));
        author.setCellValueFactory(new PropertyValueFactory<BreakingBadPhrase, String>("author"));
        series.setCellValueFactory(new PropertyValueFactory<BreakingBadPhrase, String>("series"));

        TableData.setItems(phrases.getPhrases());
    }
}