/**
 * A price comparator for trade orders.
 */
public class PriceComparator implements java.util.Comparator<TradeOrder>
{

    boolean asc= true;
    PriceComparator()
    {
        this.asc=true;
    }

    PriceComparator(boolean asc)
    {
        this.asc=asc;
    }

    @Override
    public int compare(TradeOrder o1, TradeOrder o2) {
        {
            if (o1.isMarket()&&o2.isMarket())
                return 0;
            if (o1.isMarket()&&o2.isLimit())
                return -1;
            if (o1.isLimit()&&o2.isMarket())
                return 1;
            double cent1=o1.getPrice();
            double cent2=o2.getPrice();
            double diff;
                if (asc)
                {
                    diff=(cent1-cent2)*100;
                }
                else
                {
                    diff=(cent2-cent1)*100;
                }
            return (int)Math.round(diff);


        }
    }

}
