/**
 * A price comparator for trade orders.
 */
public class PriceComparator implements java.util.Comparator<TradeOrder>
{
    boolean asc= true;
    PriceComparator()
    {

    }

    PriceComparator(boolean asc)
    {
        this.asc=asc;
    }

    @Override
    public int compare(TradeOrder o1, TradeOrder o2) {
        {
            if (o1.isMarketOrder()&&o2.isMarketOrder())
            return 0;
            if (01.isMarketOrder()&&!o2.isMarketOrder())
                return -1;

        }
    }

    

}
