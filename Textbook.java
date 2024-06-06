public class Textbook extends Book{
    private String Genre;

    public Textbook(String name, String author){
        super(name, author);
        Genre = "Text Book";
    }
    public String getGenre() {
        return Genre;
    }
    public void Print(){
        if(getLoaned()){
            System.out.println("Title\t: " + getBookName());
            System.out.println("Author\t: " + getBookAuthor());
            System.out.println("Genre\t: " + Genre);
            System.out.println("Name\t: " + getPersonName());
            System.out.println("ID\t\t: " + getPersonID());
            System.out.println("Date\t: " + getDate());
        }
        else {
            System.out.println("Title\t: " + getBookName());
            System.out.println("Author\t: " + getBookAuthor());
            System.out.println("Genre\t: " + Genre);
            System.out.println("Name\t: " + getPersonName());
        }
        System.out.println();
    }

    public String StringOutput(){
        if(getLoaned()) return "Title\t: " + getBookName() + "\nAuthor\t: " + getBookAuthor() + "\nGenre\t: " + Genre + "\nName\t: " + getPersonName() + "\nID\t\t: " + getPersonID() + "\nDate\t: " + getDate() + "\n";
        else return "Title\t: " + getBookName() + "\nAuthor\t: " + getBookAuthor() + "\nGenre\t: " + Genre + "\nName\t: " + getPersonName() + "\n";
    }
}