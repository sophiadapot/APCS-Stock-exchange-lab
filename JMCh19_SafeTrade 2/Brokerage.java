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

    // TODO complete class

    public Brokerage(StockExchange ex){
        traders = new TreeMap<String, Trader>();
        loggedTraders = new TreeSet<Trader>();
        exchange = ex;
    }

    public void register(String name, String password){
        if(traders.containsKey(name) == false){
            Trader trader = new Trader(this, name, password);
            traders.put(name, trader);
        }
        else{
            System.out.println("Already registered");
        }
    }

    public void Login(String name, String password){
        Trader t = traders.get(name);
        if (t!= null && t.getPassword().equals(password) && !loggedTraders.contains(t))
            loggedTraders.add(t);
        
        else
            System.out.println("Something went wrong in Logging in");
    }

    public void logout(Trader t){
        if (loggedTraders.contains(t)){
            loggedTraders.remove(t);
        }
    }

    public void forwardOrder(TradeOrder trOr){
        exchange.placeOrder(trOr);
    }

    public String forwardQuote(String symbol)
    {
        return exchange.getQuote(symbol);
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
