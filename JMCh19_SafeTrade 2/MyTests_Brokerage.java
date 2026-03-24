public class MyTests_Brokerage {

    public static void test() {

        System.out.println("\n===== Brokerage Tests =====");
        // TODO - add tests for Brokerage class
        StockExchange stoExc = new StockExchange();
        Brokerage br = new Brokerage(stoExc);

        br.addUser("admin", "test");
        br.addUser("@admin", "test1234");
        br.login("@admin", "test1234");
        br.login("@admin", "test");
        br.login("@admin", "test");

        // iterate through, find trader
        Map<String, Trader> ma = br.getTraders();
        Trader trader;
        for(Trader t : ma){
            if (t.getName().equals("admin"))
                if (t.getPassword().equals("test")){
                    trader = t;
                    break;
                }
        }
        TradeOrder trOr = new TradeOrder(trader, "boi", false, true, 40, 10.00)
        br.placeOrder(trOr);

        System.out.println(br.getQuote("boi"));
        System.out.println(br.getQuote("bosi"));

        br.logout(trader);

        System.out.println(br.getTraders());
        System.out.println(br.getLoggedTraders());
        System.out.println(br.getExchange());


    }
}