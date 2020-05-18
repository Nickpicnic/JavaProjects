package flashcards;

import java.util.*;

public class DeckOfCards {
    private Map<String, String> cardDef;
    private Map<String, String> defCard;

    public DeckOfCards(){
        this.cardDef = new LinkedHashMap<>();
        this.defCard = new LinkedHashMap<>();
    }

    public Map getCardDef() {
        return this.cardDef;
    }

    public Map getDefCard() {
        return this.defCard;
    }

    public Set getKeys() {
        return this.cardDef.keySet();
    }

    public Set getValues() {
        return this.defCard.keySet();
    }

    public void putCard(String card, String definition) {
        this.cardDef.put(card, definition);
        this.defCard.put(definition, card);
    }

    public void removeCard(String card) {
        String def = this.cardDef.get(card);
        this.cardDef.remove(card);
        this.defCard.remove(def);
    }

    public void printInfo() {
        for (var entry : cardDef.entrySet()) {
            System.out.printf("%s : %s\n", entry.getKey(), entry.getValue());
        }
    }

    public String returnInfo() {
        String out = "";
        for (var entry : cardDef.entrySet()) {
            out += entry.getKey() + "\t:\t" + entry.getValue() + "\n";
        }
        return out;
    }
}
