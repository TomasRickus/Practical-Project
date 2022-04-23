package service;

import java.util.Scanner;

public class PrintingService {

    public char printMenuSelections(Scanner scanner) {
        char selection;
        System.out.println("Sveiki atvyke! :)");
        System.out.println("Pasirinkite ka norite atlikti: ");
        System.out.println("1. Uzsiregistruoti ");
        System.out.println("2. Prisijungti ");
        System.out.println("3. Paleisti kaip System Admin");
        System.out.println("E. Iseiti/exit");
        System.out.println("____________________");
        selection = scanner.next().charAt(0);
        return selection;
    }

    public char printBuyerMenu(Scanner scanner) {
        char selection;
        System.out.println("____________________");
        System.out.println("Pasirinkite norima operacija");
        System.out.println("1. Ieskoti prekiu ");
        System.out.println("2. Deti preke i krepseli ");
        System.out.println("3. Perziureti krepseli");
        System.out.println("E. Iseiti/exit");
        System.out.println("____________________");
        selection = scanner.next().charAt(0);
        return selection;
    }

    public char printSellerMenu(Scanner scanner) {
        char selection;
        System.out.println("____________________");
        System.out.println("Pasirinkite norima operacija");
        System.out.println("1. Ideti parduodama preke ");
        System.out.println("E. Iseiti/exit");
        System.out.println("____________________");
        selection = scanner.next().charAt(0);
        return selection;
    }

    public char printAdminMenu(Scanner scanner) {
        char selection;
        System.out.println("____________________");
        System.out.println("Pasirinkite norima operacija");
        System.out.println("1. Ieskoti prekiu ");
        System.out.println("2. Deti preke i krepseli ");
        System.out.println("3. Ideti parduodama preke ");
        System.out.println("4. Perziureti krepseli");
        System.out.println("5. Istrinti preke ");
        System.out.println("6. Koreguoti preke");
        System.out.println("E. Iseiti/exit");
        System.out.println("____________________");
        selection = scanner.next().charAt(0);
        return selection;
    }

    public void printFindProduct() {
        System.out.println("Pasirinkite ieskomos prekes tipa \n 1 Drabuziai \n 2 Batai \n 3 Aksesuarai \n 4 Rodyti visas prekes " +
                "\n Arba spauskite 5 ir iveskite ieskomos prekes pavadinima");
    }
}
