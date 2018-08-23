package io.dylan.antisporttery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.dylan.antisporttery.pojos.Odds;
import io.dylan.antisporttery.pojos.Proportion;
import io.dylan.antisporttery.utils.DateUtils;

public class SportteryRecyclerViewAdapter extends AbstractRecyclerViewAdapter {

    static final Pattern PATTERN_WEEK_DAY = Pattern.compile("(周[一|二|三|四|五|六|日])(.*)");
    static final Pattern PATTERN_LEAGE_RANKING = Pattern.compile("(.*)([0-9]+)");

    final SportteryFragment.OnSportterySelectedListener mListener;

    public SportteryRecyclerViewAdapter(Context context, SportteryFragment.OnSportterySelectedListener listener) {
        super(context);
        this.mListener = listener;
    }

    @Override
    protected AbstractViewHolder getViewHolder(@NonNull ViewGroup parent, int viewType) {
        AbstractViewHolder viewHolder = null;
        if (viewType == ItemObject.TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sporttery, parent, false);
            viewHolder = new ItemViewHolder(view);
            return viewHolder;
        } else if (viewType == ItemObject.TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_sporttery, parent, false);
            viewHolder = new HeaderViewHolder(view);
            return viewHolder;
        }
        throw new IllegalArgumentException("Not supported view type: " + viewType);
    }

    private class HeaderViewHolder extends AbstractRecyclerViewAdapter.AbstractViewHolder<SportteryHeader> {

        TextView header;

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void initViews() {
            header = itemView.findViewById(R.id.tv_header);
        }

        @Override
        protected void initItemView(@NonNull SportteryHeader sh, int position) {
            header.setText(DateUtils.formatToString(sh.getDeadLine(), "yyyy-MM-dd") + "\t共" + sh.getMatchCount() + "场比赛");
        }
    }

    private class ItemViewHolder extends AbstractRecyclerViewAdapter.AbstractViewHolder<Sporttery> {

        TextView matchVS;

        TextView matchInfo;

        TextView nonSpread;
        TextView win;
        TextView draw;
        TextView lose;

        TextView spread;
        TextView spreadWin;
        TextView spreadDraw;
        TextView spreadLose;


        public ItemViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void initViews() {
            matchVS = itemView.findViewById(R.id.tv_vs);
            matchInfo = itemView.findViewById(R.id.tv_match_info);

            nonSpread = itemView.findViewById(R.id.tv_none_spread);
            win = itemView.findViewById(R.id.tv_win);
            draw = itemView.findViewById(R.id.tv_draw);
            lose = itemView.findViewById(R.id.tv_lose);

            spread = itemView.findViewById(R.id.tv_spread);
            spreadWin = itemView.findViewById(R.id.tv_spread_win);
            spreadDraw = itemView.findViewById(R.id.tv_spread_draw);
            spreadLose = itemView.findViewById(R.id.tv_spread_lose);
        }


        @Override
        protected void initItemView(Sporttery s, int position) {
            Odds odds = s.getOdds();
            Proportion proportion = s.getProportion();
            Matcher matcher = null;
            matcher = PATTERN_WEEK_DAY.matcher(odds.getNum());
            if (matcher.find()) {
                String strNo = matcher.group(2);
                String strTime = odds.getTime().substring(0, odds.getTime().length() - 3);
                if (odds.getDeadLine().before(odds.getMatchDate())) {
                    strTime = "00:00";
                }
                matchInfo.setText(strNo + "\n" + odds.getLCnAbbr() + "\n" + strTime);
            }

            String hostRanking = "";
            String guestRanking = "";

            matcher = PATTERN_LEAGE_RANKING.matcher(odds.getHOrder());
            if (matcher.find()) {
                hostRanking = matcher.group(2);
            }
            matcher = PATTERN_LEAGE_RANKING.matcher(odds.getAOrder());
            if (matcher.find()) {
                guestRanking = matcher.group(2);
            }
            if (hostRanking != null && guestRanking != null) {
                matchVS.setText(odds.getHCn() + "[" + hostRanking + "]" + " vs " + odds.getACn() + "[" + guestRanking + "]");
            } else {
                matchVS.setText(odds.getHCn() + " vs " + odds.getACn());
            }

            OddsOnClickListener listener = new OddsOnClickListener(s);

            nonSpread.setText("0");
            win.setText(odds.getHad().getH());
            win.setOnClickListener(listener);

            draw.setText(odds.getHad().getD());
            draw.setOnClickListener(listener);

            lose.setText(odds.getHad().getA());
            lose.setOnClickListener(listener);

            String fixedOdds = odds.getHhad().getFixedodds();
            spread.setText(fixedOdds);
            if (Integer.valueOf(fixedOdds) < 0) {
                spread.setBackgroundResource(R.color.negative_spread_bg_color);
            } else {
                spread.setBackgroundResource(R.color.positive_spread_bg_color);
            }
            spreadWin.setText(odds.getHhad().getH());
            spreadWin.setOnClickListener(listener);

            spreadDraw.setText(odds.getHhad().getD());
            spreadDraw.setOnClickListener(listener);

            spreadLose.setText(odds.getHhad().getA());
            spreadLose.setOnClickListener(listener);
        }

        private class OddsOnClickListener implements View.OnClickListener {

            final Sporttery sporttery;

            OddsOnClickListener(final Sporttery s) {
                this.sporttery = s;
            }

            @Override
            public void onClick(View v) {
                boolean flag = mListener.onSportterySelected(sporttery, v.getId());
                v.setBackgroundResource(flag ? R.drawable.odds_bg_selected : R.drawable.odds_bg_default);
            }
        }
    }
}
