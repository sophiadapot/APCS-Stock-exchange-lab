import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Represents a stock trader.
 */
public class Trader implements Comparable<Trader>
{
    private Brokerage brokerage;
    private String screenName, password;
    private TraderView myView;
    private Queue<String> mailbox;

public Trader(Brokerage brokerage, String screenName, String password)
{
    this.brokerage = brokerage;
    this.screenName = screenName;
    this.password = password;
    mailbox = new LinkedList<String>();
    //this.myView = new TraderWindow(this);
}

public boolean hasMessages()
{
    return(mailbox.isEmpty());
}

public void receiveMessage(String message)
{
    if(message != null)
    {
        mailbox.add(message);
        while (!mailbox.isEmpty())
    {
        while (this.myView!=null)
        {myView.showMessage(mailbox.poll());
        }
        if (this.myView==null)
            break;
    }
    }
}

public int compareTo(Trader t)
{
    return screenName.compareTo(t.screenName);
}

public String getName()
{
    return screenName;
}

public String getPassword()
{
    return password;
}

public void getQuote(String symbol)
{
    brokerage.getQuote(symbol,this);
}

public void placeOrder(TradeOrder trOr)
{
    brokerage.placeOrder(trOr);
}

public void quit()
{
    brokerage.logout(this);
}

public void setView(TraderView v)
{
    myView = v;
    while (!mailbox.isEmpty())
    {
        while (this.myView!=null)
        {myView.showMessage(mailbox.poll());
        }
        if (this.myView==null)
            break;
    }
}

    //
    // The following are for test purposes only
    //
    protected Queue<String> mailbox()
    {
        return mailbox;
    }
    
    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Trader.
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
                if ( field.getType().getName().equals( "Brokerage" ) )
                    str += separator + field.getType().getName() + " "
                        + field.getName();
                else
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
