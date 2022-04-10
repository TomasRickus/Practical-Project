package service;

import java.util.Scanner;

public class PrintingService {

    public char printMenuSelections(Scanner scanner) {
        char selection;
        System.out.println("Sveiki atvyke! :)");
        System.out.println("Pasirinkite ka norite atlikti: ");
        System.out.println("1. Uzsiregistruoti ");
        System.out.println("2. Prisijungti ");
        System.out.println("E. Iseiti/exit");
        System.out.println("____________________");
        selection = scanner.next().charAt(0);
        return selection;
    }

    public char printLoginMenu(Scanner scanner) {
        char selection;
        System.out.println("____________________");
        System.out.println("Pasirinkite norima operacija");
        System.out.println("1. Ideti preke ");
        System.out.println("2. Ieskoti prekiu ");
        System.out.println("3. Deti i krepseli ");
        System.out.println("4. Perziureti krepseli");
        System.out.println("E. Iseiti/exit");
        System.out.println("____________________");
        selection = scanner.next().charAt(0);
        return selection;
    }
}
