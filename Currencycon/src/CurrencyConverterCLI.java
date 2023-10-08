import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CurrencyConverterCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CurrencyConverter currencyConverter = new CurrencyConverter("7467cce9449f12402bb7a0ed");
        List<String> favoriteCurrencies = new ArrayList<>();

        System.out.println("Welcome to the Currency Converter!");

        System.out.println("Welcome to the Currency Converter!");

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Convert Currency");
            System.out.println("2. Add Favorite Currency");
            System.out.println("3. View Favorite Currencies");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the source currency code (e.g., USD): ");
                    String fromCurrency = scanner.next();

                    System.out.print("Enter the target currency code (e.g., AED): ");
                    String toCurrency = scanner.next();

                    System.out.print("Enter the amount to convert: ");
                    double amount = scanner.nextDouble();

                    double convertedAmount = currencyConverter.convert(fromCurrency, toCurrency, amount);

                    if (convertedAmount >= 0) {
                        System.out.println(amount + " " + fromCurrency + " is equivalent to " + convertedAmount + " " + toCurrency);
                    } else {
                        System.out.println("Currency conversion failed.");
                    }
                    break;

                   
                case 2:
                    System.out.print("Enter the currency code you want to add to favorites (e.g., USD): ");
                    String favoriteCurrency = scanner.next();

                    // Add the favorite currency to the list (you'll need to initialize the list outside the loop)
                    favoriteCurrencies.add(favoriteCurrency);
                    
                    System.out.println(favoriteCurrency + " has been added to your favorite currencies.");
                    break;
                case 3:
          
                    if (favoriteCurrencies.isEmpty()) {
                        System.out.println("You have no favorite currencies yet.");
                    } else {
                        System.out.println("Your favorite currencies:");
                        for (String currency : favoriteCurrencies) {
                            System.out.println(currency);
                        }
                    }
                    break;

                case 4:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
