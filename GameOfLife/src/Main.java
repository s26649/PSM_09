import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initial parameters input extracted from psvm in old UI (now GameView) class (first half)
        Scanner scanner = new Scanner(System.in);
        System.out.println("Rozmiar siatki:");
        int size = scanner.nextInt();
        System.out.println("Zasady dla zycia (np. 23):");
        String liveRules = scanner.next();
        System.out.println("Zasady dla narodzin (np. 3):");
        String birthRules = scanner.next();

        UI ui = new UI(size, liveRules, birthRules);
    }
}
