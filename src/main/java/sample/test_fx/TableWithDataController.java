package sample.test_fx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

        String jsonString = jsonGetter.jsonIn;

        Object obj = null;
        try {
            obj = new JSONParser().parse(jsonString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println();

        JSONArray jsonArray = (JSONArray) obj;

        Phrases phrases = new Phrases();

        for (Object jsonObject : jsonArray) {
            JSONObject current = (JSONObject) jsonObject;
            int quoteId = Integer.parseInt(String.valueOf(current.get("quote_id")));
            String quote = (String) current.get("quote");
            String author = (String) current.get("author");
            String series = (String) current.get("series");
            BreakingBadPhrase bbp = new BreakingBadPhrase(quoteId, quote, author, series);
            phrases.add(bbp);
        }

        quoteId.setCellValueFactory(new PropertyValueFactory<BreakingBadPhrase, Integer>("quoteId"));
        quote.setCellValueFactory(new PropertyValueFactory<BreakingBadPhrase, String>("quote"));
        author.setCellValueFactory(new PropertyValueFactory<BreakingBadPhrase, String>("author"));
        series.setCellValueFactory(new PropertyValueFactory<BreakingBadPhrase, String>("series"));

        TableData.setItems(phrases.getPhrases());
    }
}