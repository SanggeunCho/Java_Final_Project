import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int BOOK_NUM = 10;
        int book_count = 0;
        boolean exit = true;

        Scanner s = new Scanner(System.in);

        Book[] Books = new Book[BOOK_NUM];
        book_count = LoadFile(Books);
        Arrays.sort(Books, 0, book_count);

        System.out.println("------------------- Welcome JP Library -------------------");

        while(exit){
            System.out.println("\n\t\t\t\t\t\t\t<Menu>\n[1] Loan\t[2] Return\t[3] Add\t[4] Print\t[5] Save\t[6] Exit");
            System.out.print("Enter the number : ");
            int menu = s.nextInt();
            s.nextLine();

            switch (menu){
                case 1: // Loan
                    for(int i = 0; i < book_count; i++){
                        if(!Books[i].getLoaned()){
                            System.out.println("[" + (i + 1) + "]");
                            Books[i].Print();
                        }
                    }
                    System.out.println("Enter the book number");
                    while(true) {
                        int num = s.nextInt();
                        num = num - 1;

                        if (Books[num].getLoaned()) {
                            System.out.println("This book is already loaned");
                        } else {
                            System.out.println("Enter the name");
                            String st_name = s.next();
                            System.out.println("Enter the ID");
                            int st_id = s.nextInt();
                            Books[num].Loan(st_name, st_id);
                            break;
                        }
                    }
                    System.out.println("Loan Success");
                    break;
                case 2: // Return
                    for(int i = 0; i < book_count; i++){
                        if(Books[i].getLoaned()){
                            System.out.println("[" + (i + 1) + "]");
                            Books[i].Print();
                        }
                    }
                    System.out.println("Enter the book number");
                    while(true){
                        int num = s.nextInt();
                        num = num - 1;

                        if(!Books[num].getLoaned()){
                            System.out.println("This book is not loaned");
                        } else {
                            while(true){
                                System.out.println("Enter the ID");
                                int check_id = s.nextInt();
                                if(check_id == Books[num].getPersonID()){
                                    Books[num].UnLoan();
                                    break;
                                }
                                else System.out.println("ID is not matched");
                            }
                            break;
                        }
                    }
                    System.out.println("Return Success");
                    break;
                case 3: // Add
                    System.out.println("Enter the Book name");
                    String book = s.nextLine();
                    System.out.println("Enter the Author name");
                    String author = s.nextLine();
                    System.out.println("Enter the Genre (Novel, Textbook, Cartoon)");
                    String genre = s.nextLine();
                    genre = genre.toLowerCase();

                    switch(genre){
                        case "novel":
                            Books[book_count] = new Novel(book, author);
                            book_count++;
                            break;
                        case "textbook":
                            Books[book_count] = new Textbook(book, author);
                            book_count++;
                            break;
                        case "cartoon":
                            Books[book_count] = new Cartoon(book, author);
                            book_count++;
                            break;
                        default:
                            System.out.println("Enter the Genre in Novel, Textbook and Cartoon");
                    }
                    Arrays.sort(Books, 0, book_count);
                    System.out.println("Add Success");
                    break;
                case 4: // Print
                    for(int i = 0; i < book_count; i++){
                        System.out.println("[" + (i + 1) + "]");
                        Books[i].Print();
                    }
                    break;
                case 5: // Save
                    SaveFile(Books, book_count);
                    System.out.println("Save Success");
                    break;
                case 6: // Exit
                    System.out.println("Exit the Program");
                    exit = false;
                    break;
                default:
                    throw new IllegalArgumentException("Wrong Number");
            }
        }
    }
    private static int LoadFile(Book[] Books){
        int count = 0;
        String filename = "DefaultInfo.txt";
        Scanner input = null;
        try{
            input = new Scanner(new File(filename));
        } catch(FileNotFoundException e){
            System.out.println("Error opening the file " + filename);
            System.exit(0);
        }
        try{
            while(input.hasNextLine()){
                String[] dist = input.nextLine().split(",");
                if(dist.length < 3) throw new IllegalArgumentException("Insufficient arguments");
                String book = dist[0].trim();
                String author = dist[1].trim();
                String genre = dist[2].trim();
                genre = genre.toLowerCase();

                switch(genre){
                    case "novel":
                        Books[count] = new Novel(book, author);
                        count++;
                        break;
                    case "textbook":
                        Books[count] = new Textbook(book, author);
                        count++;
                        break;
                    case "cartoon":
                        Books[count] = new Cartoon(book, author);
                        count++;
                        break;
                    default:
                        System.out.println("Enter the Genre in Novel, Textbook and Cartoon");
                }
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return count;
    }

    private static void SaveFile(Book[] Books, int count){
        String filename = "BookList.txt";
        PrintWriter output = null;
        try{
            output = new PrintWriter(filename);
        } catch(FileNotFoundException e){
            System.out.println("Error opening the file " + filename);
            System.exit(0);
        }
        for(int i = 0; i < count; i++){
            output.println("[" + (i + 1) + "]");
            output.println(Books[i].StringOutput());
        }
        output.close();
    }
}