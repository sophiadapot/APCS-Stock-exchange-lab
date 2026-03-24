public class MyTests_PriceComparator {

    public static void test() {

        System.out.println("\n===== PriceComparator Tests =====");
        PriceComparator comp=new PriceComparator();
        PriceComparator comp2=new PriceComparator(false);

        TradeOrder o1= new TradeOrder(null, "ggl", true, true, 120,67.00 );
        TradeOrder o2= new TradeOrder(null, "ggl", true, true, 120,67.00 );
        System.out.println("want 0, actual: "+ comp.compare(o1,o2));

        TradeOrder o3= new TradeOrder(null, "ggl", true, true, 120,67.00 );
        TradeOrder o4= new TradeOrder(null, "ggl", true, false, 120,67.00 );
        System.out.println("want -1, actual: "+ comp.compare(o3,o4));

        TradeOrder o5= new TradeOrder(null, "ggl", true, false, 120,67.00 );
        TradeOrder o6= new TradeOrder(null, "ggl", true, true, 120,67.00 );
        System.out.println("want 0, actual: "+ comp.compare(o5,o6));

        TradeOrder o7= new TradeOrder(null, "ggl", true, false, 120,67.00 );
        TradeOrder o8= new TradeOrder(null, "ggl", true, false, 120,69.00 );
        System.out.println("want -, actual: "+ comp.compare(o7,o8));
        System.out.println("want 1, actual: "+ comp2.compare(o7,o8));

    }
}