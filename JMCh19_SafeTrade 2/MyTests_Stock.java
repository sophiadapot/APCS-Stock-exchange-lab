public class MyTests_Stock {

    public static void test() {

        System.out.println("\n===== Stock Tests =====");
        Stock google=new Stock("ggl", "google", 10.00);
        StockExchange ex=new StockExchange();
        Brokerage b=new Brokerage(ex);
        Trader test=new Trader(b,"test","123");
        Trader test2=new Trader(b,"test","123");
        TradeOrder buy=new TradeOrder(test,"ggl",true,true,5,10.00);
        TradeOrder sell=new TradeOrder(test2, "ggl", false, false, 4, 11.00);
        TradeOrder sell2=new TradeOrder(test2, "ggl", false, false, 4, 12.00);
        TradeOrder sell3=new TradeOrder(test2, "ggl", false, false, 4, 11.50);
        google.placeOrder(sell);
        google.placeOrder(sell2);
        google.placeOrder(sell3);
        google.placeOrder(buy);
        
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