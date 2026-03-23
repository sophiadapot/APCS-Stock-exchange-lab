public class MyTests_Stock {

    public static void test() {

        System.out.println("\n===== Stock Tests =====");
        Stock google=new Stock("ggl", "google", 10.00);
        
        printvalues(google);

    }
    public static void printvalues(Stock stock)
    {
        System.out.println(stock.getBuyOrders());
        System.out.println(stock.getSellOrders());
        System.out.println(stock.getHiPrice());
        System.out.println(stock.getLastPrice());
        System.out.println(stock.getQuote());
    }
}