import java.util.Scanner;
import java.util.Random;

public class HangMan {
    public static void main(String[] args) {


        // Word picker.
        String word = randomWord();
        Scanner scanner = new Scanner(System.in);
        // Word picker.

        //Max lifes
        int life = 6;
        //Max lifes

        // System life, like boolean, 0-1.
        int systemLife = 0;
        // System life, like boolean, 0-1.

        // Changing word  string to char.
        char[] tab = word.toCharArray();
        // Changing word  string to char.

        int whereIsThisLetterInWord;

        // This is changing letters for 'x' and ignoring space.
        for (int i = 0; i < word.length(); i++) {
            if (!(tab[i] == ' ')){
                tab[i] = 'x';
            }
            }
        // This is changing letters for 'x' and ignoring space.


        // Creating 'new' word for comparing if there is any x left to finish.
        String word1 = String.valueOf(tab);
        tab = word1.toCharArray();
        // Creating 'new' word for comparing if there is any x left to finish.

        do {
            System.out.println("Write a single letter:");
            char letter2 = scanner.next().charAt(0);

            // Now uppercase letters are also ok.
            if (letter2 >='A' && letter2 <='Z')
            {
                letter2 = (char)(letter2 + 32);
            }
            // Now uppercase letters are also ok.

            for (int i = 0; i < word.length(); i++) {
                whereIsThisLetterInWord = word.indexOf(letter2, i);

                if (whereIsThisLetterInWord == i) {
                    tab[i] = letter2;
                    systemLife = systemLife + 1;
                }
            }
            if (systemLife == 0) {
                life = (life - 1);
                
                // It will give you a word when you failed.
                if (life == 0){
                    System.out.println("Your word was: "+ word);
                }
                // It will give you a word when you failed.
                HaningMan(life);
                System.out.println(tab);
                System.out.println("You have " + life + " more tries!");

            } else {
                systemLife = 0;
                HaningMan(life);
                System.out.println(tab);

            }

            // Comparing if there is any x left to finish.
            word1 = String.valueOf(tab);
            if (!word1.contains("x")){
                System.out.println("Congratulations!");
                System.exit(1);
            }
            // Comparing if there is any x left to finish.

        }
        while (true);


    }


    public static void HaningMan(int howManyLifesLeft) {
// Stick man graph lifes left.

        if (howManyLifesLeft == 5) {
            System.out.println("  ");
            System.out.println("       ");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("__");
            System.out.println();

        }

        if (howManyLifesLeft == 4) {
            System.out.println("  ");
            System.out.println(" |      ");
            System.out.println(" |");
            System.out.println(" |");
            System.out.println(" |");
            System.out.println("_|_");
            System.out.println();
        }

        if (howManyLifesLeft == 3) {
            System.out.println("  _______ ");
            System.out.println(" |      | ");
            System.out.println(" |");
            System.out.println(" |");
            System.out.println(" |");
            System.out.println("_|_");
            System.out.println();
        }

        if (howManyLifesLeft == 2) {
            System.out.println("  _______ ");
            System.out.println(" |      | ");
            System.out.println(" |    (ʘ‸ʘ) ");
            System.out.println(" |");
            System.out.println(" |");
            System.out.println("_|_");
            System.out.println();
                 }

        if (howManyLifesLeft == 1) {
            System.out.println("  _______ ");
            System.out.println(" |      | ");
            System.out.println(" |    (ʘ‸ʘ) ");
            System.out.println(" |   /|  |\\");
            System.out.println(" |");
            System.out.println("_|_");
            System.out.println();
                }

        if (howManyLifesLeft == 0) {
            System.out.println("  _______ ");
            System.out.println(" |      | ");
            System.out.println(" |    (ʘ‸ʘ) ");
            System.out.println(" |   /|  |\\");
            System.out.println(" |    /  \\ ");
            System.out.println("_|_");
            System.out.println();
            System.out.println(" GAME OVER");
            System.exit(1);

                    }

        // Stick man graph lifes left.


    }

    public static String randomWord(){


        // Random picker from 0-20

        String[] fromWhereYouCanPickWord = {"misia", "ania", "stefan syn stefana", "aksamit", "forum", "paweł", "okoń to ryba", "telefon", "pudełko", "słowo",
                "prywatnie", "wygrywanie", "obciążanie", "szklanka wody", "monitor panoramiczny", "zeszyt w kratkę", "github", "link", "fraza", "London"
        };

        Random selector = new Random();
        int randomSelector = selector.nextInt(20);
        return fromWhereYouCanPickWord[randomSelector];

        // Random picker from 0-20
    }
}

