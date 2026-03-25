public class MyTests_Stock {

    public static void test() {

        System.out.println("\n===== Stock Tests =====");
        Stock google=new Stock("ggl", "google", 10.00);
        StockExchange ex=new StockExchange();
        Brokerage b=new Brokerage(ex);
        Trader test=new Trader(b,"test","123");
        Trader test2=new Trader(b,"test","123");
        TradeOrder buy2=new TradeOrder(test,"ggl",true,true,5,10.00);
        TradeOrder buy=new TradeOrder(test,"ggl",true,true,5,10.00);
        TradeOrder sell=new TradeOrder(test2, "ggl", false, false, 4, 11.00);
        TradeOrder sell2=new TradeOrder(test2, "ggl", false, false, 4, 12.00);
        TradeOrder sell3=new TradeOrder(test2, "ggl", false, false, 4, 11.50);
        google.placeOrder(sell);
        google.placeOrder(sell2);
        google.placeOrder(sell3);
        google.placeOrder(buy);
        google.placeOrder(buy2);
        
        printvalues(google);

        System.out.println("test 2 with one buy limited (apple,2)");

        Stock apple=new Stock("appl", "apple", 20.00);
        StockExchange exa=new StockExchange();
        Brokerage ba=new Brokerage(exa);
        Trader testa=new Trader(ba,"test","123");
        Trader test2a=new Trader(ba,"test","123");

        TradeOrder buy22=new TradeOrder(testa,"appl",true,false,5,12.00);
        TradeOrder buy_2=new TradeOrder(testa,"appl",true,true,5,10.00);
        TradeOrder sell_2=new TradeOrder(test2a, "appl", false, false, 4, 11.00);
        TradeOrder sell22=new TradeOrder(test2a, "appl", false, false, 4, 12.00);
        TradeOrder sell32=new TradeOrder(test2a, "appl", false, false, 4, 11.50);
        apple.placeOrder(sell_2);
        apple.placeOrder(sell22);
        apple.placeOrder(sell32);
        apple.placeOrder(buy_2);
        apple.placeOrder(buy22);
        
        printvalues(apple);

        System.out.println("both market (tesla,3)");
         Stock tesla=new Stock("tsla", "tesla", 20.00);
        StockExchange ext=new StockExchange();
        Brokerage bt=new Brokerage(ext);
        Trader testt=new Trader(bt,"test","123");
        Trader test2t=new Trader(bt,"test","123");

        TradeOrder buy23=new TradeOrder(testt,"tsla",true,false,5,12.00);
        TradeOrder buy_3=new TradeOrder(testt,"tsla",true,true,5,10.00);
        TradeOrder sell_3=new TradeOrder(test2t, "tsla", false, false, 4, 11.00);

        tesla.placeOrder(sell_3);
        tesla.placeOrder(buy_3);
        tesla.placeOrder(buy23);

        printvalues(tesla);

        System.out.println("all market including sell (meta,4)");
         Stock meta=new Stock("met", "meta", 10.00);
        Trader testm=new Trader(b,"test","123");
        Trader test2m=new Trader(b,"test","123");

        TradeOrder buy24=new TradeOrder(testm,"met",true,true,5,12.00);
        TradeOrder buy_4=new TradeOrder(test2m,"met",true,true,5,10.00);
        TradeOrder sell_4=new TradeOrder(test2, "met", false, true, 4, 11.00);

        meta.placeOrder(sell_4);
        meta.placeOrder(buy_4);
        meta.placeOrder(buy24);

        printvalues(meta);

        System.out.println("sell is market buy is limit (nflx,4)");
         Stock netflix=new Stock("met", "meta", 10.00);
        Trader testn=new Trader(b,"test","123");
        Trader test2n=new Trader(b,"test","123");

        TradeOrder buy25=new TradeOrder(testn,"met",true,true,5,12.00);
        TradeOrder buy_5=new TradeOrder(test2n,"met",true,true,5,10.00);
        TradeOrder sell_5=new TradeOrder(test2, "met", false, false, 4, 11.00);

        netflix.placeOrder(sell_5);
        netflix.placeOrder(buy25);
        netflix.placeOrder(buy_5);

        printvalues(netflix);

        System.out.println("get line coverage:" + netflix.getStockSymbol()+netflix.getCompanyName()+netflix.getLoPrice()+netflix.getVolume());

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