import java.util.Comparator;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;


public class Gamark {
    public static void main(String[] args) {

        Comparator<Unit> comparator = new Comparator<Unit>() {
            public int compare(Unit g1, Unit g2) {
                if (g1.getScore() < g2.getScore()) {
                    return 1;
                }
                else if (g1.getScore() > g2.getScore()) {
                    return -1;
                }
                else {
                    return 0;
                }
            }
        };

        Scanner in = new Scanner(System.in);
        int choice = 0;
        float mark_input;
        boolean check = true;
        String name_input;
        ArrayList<Unit> list = new ArrayList<Unit>();
        System.out.println("Welcome to Gamark, the ultimate tool for keeping track of all your games. Choose option:\n");
        while (choice != 7) {
            System.out.println("1. Show the list");
            System.out.println("2. Add to list");
            System.out.println("3. Delete position");
            System.out.println("4. Find position");
            System.out.println("5. Save list");
            System.out.println("6. Load list");
            System.out.println("7. Exit");
            choice = in.nextInt();
            switch (choice) {
                case 1:
                    if (list.isEmpty()) {
                        System.out.println("Nothing is in the list");
                    } else {
                        System.out.println("Total amount: " + list.size() + "\n");
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println((i + 1) + ". " + list.get(i).displayInfo() + "\n");
                        }
                    }
                    break;
                case 2:
                    in.nextLine();
                    System.out.print("Name: ");
                    name_input = in.nextLine();
                    System.out.print("Mark: ");
                    mark_input = in.nextFloat();
                    Unit newUnit = new Unit(name_input, mark_input);
                    check = true;
                    for(int i = 0; i < list.size() && check; i++) {
                        if(list.get(i).getName().toLowerCase().equals(name_input.toLowerCase())) {
                            check = false;
                        }
                    }
                    if(check) {
                        list.add(newUnit);
                        list.sort(comparator);
                    }
                    else {
                        System.out.println("The position is already exists");
                    }
                    break;
                case 3:
                    System.out.println("Enter the number of position: ");
                    int pos = in.nextInt();
                    list.remove(pos - 1);
                    break;
                case 4:
                    in.nextLine();
                    System.out.print("Enter name of position: ");
                    name_input = in.nextLine();
                    for(int i = 0; i < list.size(); i++) {
                        if(list.get(i).getName().toLowerCase().contains(name_input.toLowerCase())) {
                            System.out.println((i + 1) + ". " + list.get(i).displayInfo() + "\n");
                        }
                    }
                    break;
                case 5:
                    int saveChoice = 1;
                    if (list.isEmpty()) {
                        System.out.println("List is empty. Are you sure you want to save it? (press 1 to confirm)");
                        saveChoice = in.nextInt();
                    }
                    if (saveChoice == 1) {
                        try (FileWriter writer = new FileWriter("list.txt", false)) {
                            for (int i = 0; i < list.size(); i++) {
                                writer.write(list.get(i).displayInfo() + "\n");
                            }
                            writer.flush();
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    break;
                case 6:
                    list.clear();
                    try {
                        File file = new File("list.txt");
                        FileReader fr = new FileReader(file);
                        BufferedReader reader = new BufferedReader(fr);
                        String line = reader.readLine();
                        while (line != null) {
                            int divider = line.indexOf('/');
                            Unit addToList = new Unit(line.substring(0, divider), Float.parseFloat(line.substring(divider + 1)));
                            list.add(addToList);
                            line = reader.readLine();
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 7:
                    break;
                case 8:
                    list.clear();
                    try {
                        File file = new File("list.txt");
                        FileReader fr = new FileReader(file);
                        BufferedReader reader = new BufferedReader(fr);
                        String line = reader.readLine();
                        while (line != null) {
                            int divider = line.indexOf('/');
                            Unit addToList = new Unit(line.substring(0, divider), Float.parseFloat(line.substring(divider + 1)));
                            list.add(addToList);
                            line = reader.readLine();
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    do {
                        System.out.println("Total amount: " + list.size() + "\n");
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println((i + 1) + ". " + list.get(i).displayInfo() + "\n");
                        }
                        in.nextLine();
                        System.out.print("Name: ");
                        name_input = in.nextLine();
                        if(name_input.equals("exit")) {
                            break;
                        }
                        System.out.print("Mark: ");
                        mark_input = in.nextFloat();
                        Unit newUnit2 = new Unit(name_input, mark_input);
                        check = true;
                        for(int i = 0; i < list.size() && check; i++) {
                            if(list.get(i).getName().toLowerCase().equals(name_input.toLowerCase())) {
                                check = false;
                            }
                        }
                        if(check) {
                            list.add(newUnit2);
                            list.sort(comparator);
                        }
                        else {
                            System.out.println("The position is already exists");
                        }
                    } while (true);
                    try (FileWriter writer = new FileWriter("list.txt", false)) {
                        for (int i = 0; i < list.size(); i++) {
                            writer.write(list.get(i).displayInfo() + "\n");
                        }
                        writer.flush();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                default:
                    System.out.print("Invalid option!");
            }
        }
        in.close();
    }
}