import java.time.LocalDate;

public class Book implements Comparable {
    private String BookName;
    private String BookAuthor;
    private String PersonName;
    private int PersonID;
    private int Date;
    private boolean Loaned;

    public String getBookName(){
        return BookName;
    }
    public String getBookAuthor(){
        return BookAuthor;
    }
    public String getPersonName(){
        return PersonName;
    }
    public int getPersonID(){
        return PersonID;
    }
    public int getDate(){
        return Date;
    }
    public boolean getLoaned(){
        return Loaned;
    }


    public Book(String name, String author){
        BookName = name;
        BookAuthor = author;
        PersonName = "Not Yet Borrowed";
        PersonID = 0;
        Loaned = false;
    }

    public void Loan(String name, int ID){
        PersonName = name;
        PersonID = ID;
        Loaned = true;

        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        Date = year * 10000 + month * 100 + day;
    }

    public void UnLoan(){
        PersonName = "Not Yet Borrowed";
        PersonID = 0;
        Loaned = false;
        Date = 0;
    }

    public void Print(){
        if(Loaned){
            System.out.println("Title\t: " + BookName);
            System.out.println("Author\t: " + BookAuthor);
            System.out.println("Name\t: " + PersonName);
            System.out.println("ID\t\t: " + PersonID);
            System.out.println("Date\t: " + Date);
        }
        else {
            System.out.println("Title\t: " + BookName);
            System.out.println("Author\t: " + BookAuthor);
            System.out.println("Name\t: " + PersonName);
        }
        System.out.println();
    }

    public String StringOutput(){
        if(Loaned) return "Title\t: " + BookName + "\nAuthor\t: " + BookAuthor + "\nName\t: " + PersonName + "\nID\t\t: " + PersonID + "\nDate\t: " + Date + "\n";
        else return "Title\t: " + BookName + "\nAuthor\t: " + BookAuthor + "\nName\t: " + PersonName + "\n";
    }

    public int compareTo(Object o){
        if((o != null) && (o instanceof Book)){
            Book otherBook = (Book) o;
            return (BookName.compareTo(otherBook.BookName));
        }
        return -1;
    }
}
