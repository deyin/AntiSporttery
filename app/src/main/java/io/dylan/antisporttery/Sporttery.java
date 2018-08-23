package io.dylan.antisporttery;

import io.dylan.antisporttery.ItemObject;
import io.dylan.antisporttery.pojos.Odds;
import io.dylan.antisporttery.pojos.Proportion;

public class Sporttery implements ItemObject {

    private Odds odds;

    private Proportion proportion;

    public Odds getOdds() {
        return odds;
    }

    public void setOdds(Odds odds) {
        this.odds = odds;
    }

    public Proportion getProportion() {
        return proportion;
    }

    public void setProportion(Proportion proportion) {
        this.proportion = proportion;
    }

    @Override
    public int getItemViewType() {
        return TYPE_ITEM;
    }
}
