package machine;
import java.util.Scanner;

public class CoffeeMachine {

    static int water = 400;
    static int milk = 540;
    static int beans = 120;
    static int cups = 9;
    static int money = 550;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        action(scanner);
        scanner.close();
    }


    public static boolean noWater(int drink){
        boolean notEnough = true;
        switch (drink) {
            case 1:
                notEnough = CoffeeMachine.water < 250;
                break;
            case 2:
                notEnough = CoffeeMachine.water < 350;
                break;
            case 3:
                notEnough = CoffeeMachine.water < 200;
                break;
        }
        return notEnough;
    }
    public static boolean noMilk(int drink){
        boolean notEnough = true;
        switch (drink) {
            case 1:
                notEnough = false;
                break;
            case 2:
                notEnough = CoffeeMachine.milk < 75;
                break;
            case 3:
                notEnough = CoffeeMachine.milk < 100;
                break;
        }
        return notEnough;
    }
    public static boolean noBeans(int drink){
        boolean notEnough = true;
        switch (drink) {
            case 1:
                notEnough = CoffeeMachine.beans < 16;
                break;
            case 2:
                notEnough = CoffeeMachine.beans < 20;
                break;
            case 3:
                notEnough = CoffeeMachine.beans < 12;
                break;
        }
        return notEnough;
    }
    public static boolean noCups(int drink){
        boolean notEnough = CoffeeMachine.cups < 1;
        return notEnough;
    }

    public static void action(Scanner scanner){
        while (1==1) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("exit")) {
                return;
            }

            switch (answer) {
                case "buy":
                    buy(scanner);
                    break;
                case "fill":
                    fill(scanner);
                    break;
                case "take":
                    take();
                    break;
                case "remaining":
                    printInfo();
                    break;
            }
        }
    }

    public static void printInfo() {
        System.out.printf(
                "\nThe coffee machine has:\n"+
                "%d of water\n"+
                "%d of milk\n"+
                "%d of coffee beans\n"+
                "%d of disposable cups\n"+
                "$%d of money\n\n",
                CoffeeMachine.water,
                CoffeeMachine.milk,
                CoffeeMachine.beans,
                CoffeeMachine.cups,
                CoffeeMachine.money
        );
    }

    public static void fill(Scanner scan) {
        System.out.println("Write how many ml of water do you want to add:");
        CoffeeMachine.water += Integer.parseInt(scan.nextLine());

        System.out.println("Write how many ml of milk do you want to add:");
        CoffeeMachine.milk += Integer.parseInt(scan.nextLine());

        System.out.println("Write how many grams of coffee beans do you want to add:");
        CoffeeMachine.beans += Integer.parseInt(scan.nextLine());

        System.out.println("Write how many disposable cups of coffee do you want to add: ");
        CoffeeMachine.cups += Integer.parseInt(scan.nextLine());

    }

    public static void buy(Scanner scan){
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String input = scan.nextLine();

        if (input.equalsIgnoreCase("back")) {
            return;
        }

        int answer = Integer.parseInt(input);

        if (noWater(answer)){
            System.out.println("Sorry, not enough water!");
            return;
        }
        if (noMilk(answer)){
            System.out.println("Sorry, not enough milk!");
            return;
        }
        if (noBeans(answer)){
            System.out.println("Sorry, not enough coffee!");
            return;
        }
        if (noCups(answer)){
            System.out.println("Sorry, not enough cups!");
            return;
        }

        System.out.println("I have enough resources, making you a coffee!");
        switch (answer) {
            case 1:
                CoffeeMachine.water -= 250;
                CoffeeMachine.beans -= 16;
                CoffeeMachine.money += 4;
                break;
            case 2:
                CoffeeMachine.water -= 350;
                CoffeeMachine.milk -= 75;
                CoffeeMachine.beans -= 20;
                CoffeeMachine.money += 7;
                break;
            case 3:
                CoffeeMachine.water -= 200;
                CoffeeMachine.milk -= 100;
                CoffeeMachine.beans -= 12;
                CoffeeMachine.money += 6;
                break;
        }
        CoffeeMachine.cups--;
    }

    public static void take() {
        System.out.printf("I gave you $%d\n", CoffeeMachine.money);
        CoffeeMachine.money = 0;
    }
}


//
//    public static void countCoffee(Scanner scanner) {
//        System.out.println("Write how many cups of coffee you will need:");
//
//        int cups = scanner.nextInt();
//        int water = cups * 200;
//        int milk = cups * 50;
//        int beams = cups * 15;
//
//        System.out.printf("For %d cups of coffee you will need:\n", cups);
//        System.out.printf("%d ml of water\n", water);
//        System.out.printf("%d ml of milk\n", milk);
//        System.out.printf("%d g of coffee beans\n", beams);
//
//        scanner.close();
//    }
//
//    public static void isEnough(Scanner scanner) {
//        System.out.println("Write how many ml of water the coffee machine has:");
//        int water = scanner.nextInt();
//        System.out.println("Write how many ml of milk the coffee machine has:");
//        int milk = scanner.nextInt();
//        System.out.println("Write how many grams of coffee beans the coffee machine has:");
//        int beans = scanner.nextInt();
//        System.out.println("Write how many cups of coffee you will need:");
//        int cups = scanner.nextInt();
//
//        scanner.close();
//
//        int maxCups = water / 200;
//        int tmp = milk / 50;
//
//        if (maxCups > tmp) {
//            maxCups = tmp;
//        }
//        tmp = beans / 15;
//        if (maxCups > tmp) {
//            maxCups = tmp;
//        }
//
//        if (maxCups == cups) {
//            System.out.println("Yes, I can make that amount of coffee");
//        } else if (maxCups > cups) {
//            System.out.printf("Yes, I can make that amount of coffee, (and even %d more than that)\n", maxCups - cups);
//        } else {
//            System.out.printf("No, I can make only %d cup(s) of coffee\n", maxCups);
//        }
//    }
