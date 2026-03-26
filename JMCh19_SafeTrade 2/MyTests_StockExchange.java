public class MyTests_StockExchange {

    public static void test() {

        System.out.println("\n===== StockExchange Tests =====");
        StockExchange ex=new StockExchange();
        ex.listStock("ggl", "google", 10.00);
        Brokerage b1= new Brokerage(ex);
        Trader t1=new Trader(b1, "t1", "1234");
        TradeOrder o1=new TradeOrder(t1, "ggl", false, false, 10, 12);
        ex.placeOrder(o1);
        ex.getQuote("ggl");
        ex.getListedStocks();
        System.out.println(ex);


    }
}