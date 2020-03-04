import java.util.*;

public class Main {
    static String[] words= new String[] {
            "cat",
            "dog",
            "rat",
            "bat",
            "mouse",
            "rabbit",
            "shamos",
            "monkey",
            "bokoblin",
            "chocobo"
    };
    static Random rand = new Random();
    static List<String> miss = new ArrayList<String>();
    static String[] ans = new String[]{};
    static String[] guess = new String[]{};
    static Set<String> missedLetters = new HashSet<String>(0);
    static int count = 0;
    static boolean isRight = false;
    static boolean isDone = false;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
	// write your code here
        playHangman();
    }

    public static void initializeHangman() {
        ans = words[rand.nextInt(10)].split("");
        guess = new String[ans.length];
        for (int i = 0; i < guess.length; i++) {
            guess[i] = "_";
        }
        count = 0;
        missedLetters = new HashSet<String>(0);
        isRight = false;
    }

    public static void printHangman() {
        switch (count) {
            case 0:
                System.out.println(
                        """
                                 H A N G M A N
                                 +---+
                                       |
                                       |
                                       |
                                     ===
                                """
                );
                break;
            case 1:
                System.out.println(
                        """
                                 H A N G M A N
                                 +---+
                                 O     |
                                       |
                                       |
                                     ===
                                """
                );
                break;
            case 2:
                System.out.println(
                        """
                                 H A N G M A N
                                 +---+
                                 O     |
                                 |     |
                                       |
                                     ===
                                """
                );
                break;
            case 3:
                System.out.println(
                        """
                                 H A N G M A N
                                 +---+
                                 O     |
                                 |     |
                                 |     |
                                     ===
                                """
                );
                break;
            case 4:
                System.out.println(
                        """
                                 H A N G M A N
                                 +---+
                                 O     |
                                 |     |
                                 |     |
                                /    ===
                                """
                );
                break;
            case 5:
                System.out.println(
                        """
                                 H A N G M A N
                                 +---+
                                 O     |
                                 |     |
                                 |     |
                                / \\  ===
                                """
                );
                break;
            case 6:
                System.out.println(
                        """
                                 H A N G M A N
                                 +---+
                                 O     |
                                /|     |
                                 |     |
                                / \\  ===
                                """
                );
                break;
            case 7:
                System.out.println(
                        """
                                 H A N G M A N
                                 +---+
                                 O     |
                                /|\\    |
                                 |     |
                                / \\  ===
                                """
                );
                break;
            default:
                System.out.println(count);
                break;
        }
        System.out.println("Missed letters: " + collectionToString(missedLetters));
        System.out.println(collectionToString(Arrays.asList(guess)));
    }

    public static String collectionToString(Collection<String> c) {
        String result = "";
        for (String s: c) {
            result += s;
        }
        return result;
    }

    public static void checkLetter(String l){
        boolean isPresent = false;
        for(int j = 0; j < ans.length; j++){
            if(l.equals(ans[j])){
                guess[j] = l;
                isPresent = true;
            }
        }
        if(!isPresent) {
            missedLetters.add(l);
            count++;
        }
    }

    public static void checkAnswer(){
        isRight = true;
        for(int k = 0; k < ans.length; k++){
            if(!guess[k].equals(ans[k])) isRight = false;
        }
    }

    public static void playHangman() {

        do{
            initializeHangman();
            while(!isRight && count < 7){
                printHangman();
                System.out.println("Guess a letter.");
                checkLetter(input.next());
                checkAnswer();
            }
            printHangman();
            if(!isRight)System.out.println("Sorry, you're all out of tries!");
            else System.out.println(String.format("Yes! The secret word is \"%s\"! You have won!",collectionToString(Arrays.asList(guess))));
            System.out.println("Do you want to play again? (yes or no)");
            if(input.next().equals("no")) isDone = true;
        }while(!isDone);
    }
}

