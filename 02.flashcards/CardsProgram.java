package flashcards;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CardsProgram {
    private boolean isWorking;
    private DeckOfCards deck;

    public static final Scanner scanner = new Scanner(System.in);

    public CardsProgram() {
        this.isWorking = true;
        this.deck = new DeckOfCards();
    }

    public void runProgram() {
        while (this.isWorking) {
            askForAction();
        }
    }
    
    public void turnOff() {
        this.isWorking = false;
    }

    public void addCard() {
        System.out.printf("The card:\n");
        String card = scanner.nextLine();
        if (this.deck.getKeys().contains(card)) {
            System.out.printf("The card \"%s\" already exists.\n", card);
        } else {
            System.out.printf("The definition of the card:\n");
            String definition = scanner.nextLine();
            if (this.deck.getValues().contains(definition)) {
                System.out.printf("The definition \"%s\" already exists.\n", definition);
            } else {
                this.deck.putCard(card, definition);
                System.out.printf("The pair \"%s\":\"%s\" has been added.\n", card, definition);

            }
        }
    }

    public void addCard(String card, String def) {
        // value already exists
        if (this.deck.getValues().contains(def)) return;

        // need update
        if (this.deck.getKeys().contains(card)) {
            this.deck.removeCard(card);
        }
        // update or just add
        this.deck.putCard(card, def);
    }

    public void clearDeck() {
        for (Object card : this.deck.getKeys()) {
            removeCard((String) card);
        }
    }
    
    public void removeCard() {
        System.out.println("The card:");
        String answer = scanner.nextLine();

        if (this.deck.getKeys().contains(answer)) {
            this.deck.removeCard(answer);
            System.out.println("The card has been removed.");
        } else {
            System.out.printf("Can't remove \"%s\": there is no such card.\n", answer);
        }
    }

    public void removeCard(String card) {
        this.deck.removeCard(card);
    }

    public void checkCards() {
        System.out.println("How many times to ask?");
        int count = Integer.parseInt(scanner.nextLine());

        List<String> cards = new ArrayList<>(this.deck.getKeys());
        List<String> defs = new ArrayList<>(this.deck.getValues());
        Random random = new Random(1000);

        while (count-- > 0) {
            String randCard = cards.get(random.nextInt(cards.size()));

            System.out.printf("Print the definition of \"%s\":\n", randCard);
            String answer = scanner.nextLine();

            if (this.deck.getCardDef().get(randCard).equals(answer)) {
                System.out.println("Correct answer.");
            } else {
                if (defs.contains(answer)) {
                    System.out.printf("Wrong answer. The correct one is \"%s\", " +
                                    "you've just written the definition of \"%s\".\n",
                            this.deck.getCardDef().get(randCard),
                            this.deck.getDefCard().get(answer));
                } else {
                    System.out.printf("Wrong answer. The correct one is \"%s\".\n", this.deck.getCardDef().get(randCard));
                }
            }
        }
    }

    public void importDeck() {
        System.out.println("File name:");
        String fileName = "./Flashcards/task/src/flashcards/src/" + scanner.nextLine();

        File file = new File(fileName);
        try(Scanner fileScanner = new Scanner(file)) {
            String line = "";
            String card = "";
            String def = "";
            int counter = 0;
            while (fileScanner.hasNextLine()) {
                line = fileScanner.nextLine();
                card = line.split("\t:\t")[0];
                def = line.split("\t:\t")[1];
                addCard(card, def);
                counter++;
            }
            System.out.printf("%d cards have been loaded.\n", counter);
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    public void exportDeck() throws IOException{
        System.out.println("File name:");
        String fileName = "./Flashcards/task/src/flashcards/src/" + scanner.nextLine();
        File file = new File(fileName);
        File fold = new File("./Flashcards/task/src/flashcards/src");

        if (!fold.exists()) {
            fold.mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
        }

        try(FileWriter writer = new FileWriter(file)) {
            writer.write(this.deck.returnInfo());
            System.out.printf("%d cards have been saved.\n", this.deck.getKeys().size());
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void askForAction() {
        System.out.println("Input the action (add, remove, import, export, ask, exit):");
        String answer = scanner.next();
        scanner.nextLine();

        switch (answer) {
            case "add":
                addCard();
                break;
            case "remove":
                removeCard();
                break;
            case "import":
                importDeck();
                break;
            case "export":
                try {
                    exportDeck();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "ask":
                checkCards();
                break;
            case "exit":
                System.out.println("Bye bye!");
                turnOff();
                break;
            default:
                break;
        }
    }
}
