import java.util.ArrayList;
import java.util.Scanner;

public class ECommercePlatform {
    public static void main(String[] args) {
        ECommerceSystem system = new ECommerceSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add product");
            System.out.println("2. View products");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter product name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter product price:");
                    double price = scanner.nextDouble();
                    system.addProduct(new Product(name, price));
                    break;
                case 2:
                    system.viewProducts();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + "}";
    }
}

class ECommerceSystem {
    private ArrayList<Product> products;

    public ECommerceSystem() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void viewProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
