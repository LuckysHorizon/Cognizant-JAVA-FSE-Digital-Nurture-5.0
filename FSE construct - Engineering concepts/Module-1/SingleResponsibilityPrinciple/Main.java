public class Main {
    public static void main(String [] args)
    {
        Book book = new Book(
            "Java Basics",
            "Manikanta",
            "Java is an object-oriented language"
        );
        BookPrinter printer = new BookPrinter();
        printer.printToConsole(book.getText());

        BookRepository repository = new BookRepository();
        repository.save(book);
    }
}
