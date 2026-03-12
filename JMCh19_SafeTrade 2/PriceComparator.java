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
            if (o1.isMarketOrder()&&o2.isMarketOrder())
            return 0;
            else if (o1.isMarketOrder()&&!o2.isMarketOrder())
                return -1;
            else if (!o1.isMarketOrder()&&o2.isMarketOrder())
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
