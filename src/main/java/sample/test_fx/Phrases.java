package sample.test_fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Date;

public class Phrases {
    private ObservableList<BreakingBadPhrase> phrases;

    public ObservableList<BreakingBadPhrase> getPhrases() {
        return phrases;
    }

    public Phrases(ObservableList<BreakingBadPhrase> phrases) {
        this.phrases = phrases;
    }

    public void setPhrases(ObservableList<BreakingBadPhrase> phrases) {
        this.phrases = phrases;
    }

    public Phrases() {
        phrases = FXCollections.observableArrayList();
    }

    public void add(BreakingBadPhrase bbp) {
        this.phrases.add(bbp);
    }

    public Phrases filterByAuthor(String str) {
        Phrases tempObj = new Phrases();
        for (BreakingBadPhrase phrase : this.phrases) {
            if (phrase.getAuthor().toLowerCase().contains(str.toLowerCase()))
                tempObj.add(phrase);
        }
        return tempObj;
    }

    @Override
    public String toString() {
        String result = "Phrases (" + (new Date()).toLocaleString() + ") " + System.lineSeparator();
        for (BreakingBadPhrase bbp : phrases) {
            result += bbp + System.lineSeparator();
        }
        return result;
    }
}