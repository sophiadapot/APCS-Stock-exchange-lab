import java.lang.reflect.*;
import java.util.*;

public class MyTests_Brokerage {

    public static void test() {

        System.out.println("\n===== Brokerage Tests =====");
        StockExchange stoExc = new StockExchange();
        Brokerage br = new Brokerage(stoExc);

        br.addUser("admin", "test");
        br.addUser("admin", "test");
        br.addUser("pog","asd");
        br.addUser("poggler", "s");

        br.login("@admin", "test1234");
        br.login("admin", "test");
        br.login("@admin", "test");
        br.login("@admin", "test");

        // iterate through, find trader
        Map<String, Trader> ma = br.getTraders();
        List<Trader> traderList = new ArrayList<>(ma.values());
        Trader trader=new Trader(br, null, null);
        
        for(Trader t :traderList){
            if (t.getName().equals("admin"))
                if (t.getPassword().equals("test")){
                    trader = t;
                    break;
                }
        }
        TradeOrder trOr = new TradeOrder(trader, "boi", false, true, 40, 10.00);
        br.placeOrder(trOr);

        
        br.getQuote("boi",trader);

        br.logout(trader);

        System.out.println(br.getTraders());
        System.out.println(br.getLoggedTraders());
        System.out.println(br.getExchange());

        br.toString();


    }
}