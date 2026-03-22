import java.util.*;
import java.lang.reflect.*;
import java.text.DecimalFormat;

/**
 * Represents a stock in the SafeTrade project
 */
public class Stock
{
    public static DecimalFormat money = new DecimalFormat( "0.00" );

    private String stockSymbol;
    private String companyName;
    private double loPrice, hiPrice, lastPrice;
    private int volume;
    private PriorityQueue<TradeOrder> buyOrders, sellOrders;

    public Stock(String symbol, String name, double price)
    {
        stockSymbol=symbol;
        companyName=name;
        loPrice=price;
        hiPrice=price;
        lastPrice=price;
        volume=0;
        sellOrders=new PriorityQueue<>(new PriceComparator(true));
        buyOrders=new PriorityQueue<>(new PriceComparator(false));
    }

    public String getQuote()
    {
        String ask=" none";
        String buy=" none";
        if (!sellOrders.isEmpty())
        {
            ask=" "+money.format(sellOrders.peek().getPrice())+" size: "+sellOrders.peek().getShares();
        }
        if (!buyOrders.isEmpty())
        {
            buy="  "+money.format(buyOrders.peek().getPrice())+" size: "+buyOrders.peek().getShares();
        }

        return (companyName+" ("+stockSymbol+")\nPrice: "+lastPrice+"  hi: "+hiPrice+"  lo: "+loPrice+". vol: "+volume+"\nAsk:"+ask+"  Bid:"+buy);
    }

    public void placeOrder(TradeOrder order)
    {
        //need to add to priority queue
        Trader trader=order.getTrader();
        String price="";
        if (order.isMarket())
        {
            price="market";
        }
        else if (order.isLimit())
        {
            price="$"+money.format(order.getPrice());
        }
        if (order.isBuy())
        {
            buyOrders.add(order);
            String msg="New order:  Buy"+stockSymbol+" ("+companyName+")\n"+order.getShares()+" shares at "+price;
            trader.receiveMessage(msg);
        }
        else
        {
            sellOrders.add(order);
            String msg="New order:  Sell"+stockSymbol+" ("+companyName+")\n"+order.getShares()+" shares at "+price;
            trader.receiveMessage(msg);
        }
        executeOrders();
    }

    public void executeOrders()
    {
        while(!buyOrders.isEmpty()&&!sellOrders.isEmpty())
        {
        TradeOrder sell=sellOrders.peek();
        TradeOrder buy=buyOrders.peek();
        double sellprice=sell.getPrice();
        double buyprice=buy.getPrice();
        double price=0;

        if (sell.isLimit()&&buy.isLimit())
        {
            if (buy.getPrice()>=sell.getPrice())
            price=sell.getPrice();
            else
                break;
        }
        else if (sell.isLimit()&&!buy.isLimit())
        {
            price=sellprice;
        }
        else if(buy.isLimit()&&!sell.isLimit())
        {
            price=buyprice;
        }
        else if(buy.isMarket()&&sell.isMarket())
        {
            price=lastPrice;
        }
        else
        {
            return;
        }
        int shares=0;
        if (sell.getShares()<buy.getShares())
            shares=sell.getShares();
        else
            shares=buy.getShares();
        sell.subtractShares(shares);
        buy.subtractShares(shares);
        if (sell.getShares()==0)
            sellOrders.remove();
        if (buy.getShares()==0)
            buyOrders.remove();

        lastPrice=price;
        volume+=shares;
        if (price<loPrice)
            loPrice=price;
        if (price>hiPrice)
            hiPrice=price;
        Trader seller=sell.getTrader();
        Trader buyer=buy.getTrader();
        seller.receiveMessage("You sold: "+shares+" "+stockSymbol+" at "+money.format(price)+" amt "+money.format(shares*price));
        buyer.receiveMessage("You bought: "+shares+" "+stockSymbol+" at "+money.format(price)+" amt "+money.format(shares*price));
        }
    }


    
    //
    // The following are for test purposes only
    //
    
    protected String getStockSymbol()
    {
        return stockSymbol;
    }
    
    protected String getCompanyName()
    {
        return companyName;
    }
    
    protected double getLoPrice()
    {
        return loPrice;
    }
    
    protected double getHiPrice()
    {
        return hiPrice;
    }

    protected double getLastPrice()
    {
        return lastPrice;
    }
    
    protected int getVolume()
    {
        return volume;
    }

    protected PriorityQueue<TradeOrder> getBuyOrders()
    {
        return buyOrders;
    }
    
    protected PriorityQueue<TradeOrder> getSellOrders()
    {
        return sellOrders;
    }
    
    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Stock.
     */
    public String toString()
    {
        String str = this.getClass().getName() + "[";
        String separator = "";

        Field[] fields = this.getClass().getDeclaredFields();

        for ( Field field : fields )
        {
            try
            {
                str += separator + field.getType().getName() + " "
                    + field.getName() + ":" + field.get( this );
            }
            catch ( IllegalAccessException ex )
            {
                System.out.println( ex );
            }

            separator = ", ";
        }

        return str + "]";
    }
}
