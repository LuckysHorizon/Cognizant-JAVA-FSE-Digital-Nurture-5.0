import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Product[] products = {
            new Product("P001", "Wireless Mouse", "Electronics"),
            new Product("P002", "Mechanical Keyboard", "Electronics"),
            new Product("P003", "Ergonomic Chair", "Furniture"),
            new Product("P004", "Desk Lamp", "Furniture"),
            new Product("P005", "Noise Cancelling Headphones", "Electronics")
        };

        System.out.println("--- Linear Search ---");
        Product foundLinear = EcommerceSearch.linearSearch(products, "Desk Lamp");
        System.out.println("Found: " + foundLinear);

        System.out.println("\n--- Binary Search ---");
        Arrays.sort(products);
        
        System.out.println("Sorted Products:");
        for (Product p : products) {
            System.out.println("  " + p);
        }
        
        Product foundBinary = EcommerceSearch.binarySearch(products, "Mechanical Keyboard");
        System.out.println("Found: " + foundBinary);
        
        Product notFound = EcommerceSearch.binarySearch(products, "Smartphone");
        System.out.println("Searching for 'Smartphone': " + (notFound != null ? notFound : "Not found"));
    }
}
