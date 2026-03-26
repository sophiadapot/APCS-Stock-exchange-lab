import java.lang.reflect.*;
import java.util.*;

/**
 * Represents a brokerage.
 */
public class Brokerage implements Login
{
    private Map<String, Trader> traders;
    private Set<Trader> loggedTraders;
    private StockExchange exchange;


    public Brokerage(StockExchange ex){
        traders = new TreeMap<String, Trader>();
        loggedTraders = new TreeSet<Trader>();
        exchange = ex;
    }

    public int addUser(String name, String password){

        if (name.length()<4||name.length()>10)
            return -1;
        if (password.length()<2||password.length()>10)
            return -2;
        if(traders.containsKey(name) == false){
            Trader trader = new Trader(this, name, password);
            traders.put(name, trader);
            return 0;
        }

        else
            return -3;

        
        
    }

    public int login(String name, String password){
        Trader t = traders.get(name);
        if (t==null)
            return -1;
        if (!t.getPassword().equals(password))
            return -2;
        if (t!= null && t.getPassword().equals(password) && !loggedTraders.contains(t))
        {
            loggedTraders.add(t);
            if(!t.hasMessages())
                t.receiveMessage("Welcome to SafeTrade!");
            return 0;
        }
        
        else
            return -3;
    }

    public void logout(Trader t){
        loggedTraders.remove(t);
    }

    public void placeOrder(TradeOrder trOr){
        exchange.placeOrder(trOr);
    }

    public void getQuote(String symbol, Trader trader)
    {
        trader.receiveMessage(exchange.getQuote(symbol));
    }

    //
    // The following are for test purposes only
    //
    protected Map<String, Trader> getTraders()
    {
        return traders;
    }

    protected Set<Trader> getLoggedTraders()
    {
        return loggedTraders;
    }

    protected StockExchange getExchange()
    {
        return exchange;
    }

    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Brokerage.
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
