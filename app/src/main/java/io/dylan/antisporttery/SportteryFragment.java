package io.dylan.antisporttery;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.dylan.antisporttery.pojos.Odds;
import io.dylan.antisporttery.pojos.Proportion;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SportteryFragment extends Fragment {

    private static String URL_GET_ODDS = "http://i.sporttery.cn/odds_calculator/get_odds";

    private static String URL_GET_PROPORTION = "http://i.sporttery.cn/odds_calculator/get_proportion";

    private static final String ARG_POSITION = "POSITION";
    private static final String ARG_NAME = "NAME";

    private String position;
    private String name;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView mRecyclerView;
    private AbstractRecyclerViewAdapter mRecyclerViewAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public SportteryFragment() {
        // Required empty public constructor
    }

    public static SportteryFragment newInstance(int position, String name) {
        SportteryFragment fragment = new SportteryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sporttery, container, false);

        setupSwipRefreshLayout(view);

        setupRecyclerView(view);

        return view;
    }

    private void setupSwipRefreshLayout(View view) {
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadSporttery();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void setupRecyclerView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerViewAdapter = new SportteryRecyclerViewAdapter(getContext(), (MainActivity) getContext());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    ((MainActivity)getActivity()).shownBottomNavigation(false);
                } else if (dy < 0 ) {
                    ((MainActivity)getActivity()).shownBottomNavigation(true);
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        loadSporttery();
    }

    private void loadSporttery() {

        new LoadSportteryTask().execute(URL_GET_ODDS, URL_GET_PROPORTION);
    }

    private class LoadSportteryTask extends AsyncTask<String, Integer, List<Sporttery>> {

        private OkHttpClient okHttpClient = new OkHttpClient();

        @Override
        protected List<Sporttery> doInBackground(String... urls) {

            List<Sporttery> list = new ArrayList<>();

            Map<String, Odds> oddsMap = loadOdds(urls[0]);

            Map<String, Proportion> proportionMap = loadProportion(urls[1]);

            Set<Map.Entry<String, Odds>> entries = oddsMap.entrySet();
            for (Map.Entry<String, Odds> entry : entries) {
                String id = entry.getKey();
                Odds odd = entry.getValue();
                Sporttery s = new Sporttery();
                s.setOdds(odd);
                s.setProportion(proportionMap.get(id));
                list.add(s);
            }
            Collections.sort(list, new Comparator<Sporttery>() {
                @Override
                public int compare(Sporttery o1, Sporttery o2) {
                    return o1.getOdds().getId().compareTo(o2.getOdds().getId());
                }
            });
            return list;
        }

        private Map<String, Odds> loadOdds(String url) {

            HttpUrl.Builder httpBuider = HttpUrl.parse(url).newBuilder();
            httpBuider.addQueryParameter("i_format", "json")
                    .addQueryParameter("i_callback", "getData")
                    .addQueryParameter("poolcode[]", "hhad")
                    .addQueryParameter("poolcode[]", "had")
            ;

            Request.Builder builder = new Request.Builder();
            builder.url(httpBuider.build())
                    .header("Accept-Language", "en-US,en;q=0.9")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36")
                    .header("Accept", "*/*")
                    .header("Referer", "http://info.sporttery.cn/football/hhad_list.php")
                    .header("Connection", "keep-alive");
            Request request = builder.build();
            try {
                Response response = okHttpClient.newCall(request).execute();
                String json = response.body().string();
                Pattern pattern = Pattern.compile("getData[\\(](.*)[\\)]");
                Matcher matcher = pattern.matcher(json);
                if (matcher.find()) {
                    String group = matcher.group(1);
                    return parseOdds(group);
                }
            } catch (Exception e) {
                Log.e("loadOdds", "error to get http response with okhttp from url: " + url);
            }

            return Collections.emptyMap();
        }

        private Map<String, Odds> parseOdds(@NonNull String json) {
            Map<String, Odds> map = new HashMap<>();
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONObject dataObject = jsonObject.getJSONObject("data");
                JSONArray names = dataObject.names();
                for (int i = 0; i < names.length(); i++) {
                    String id = names.get(i).toString();
                    JSONObject idObject = dataObject.getJSONObject(id);
                    Odds odds = gson.fromJson(idObject.toString(), Odds.class);
                    map.put(id, odds);
                }
            } catch (JSONException e) {
                Log.e("parseOdds", "error to parseOdds json: " + json);
            }
            return map;
        }

        private Map<String, Proportion> loadProportion(String url) {

            HttpUrl.Builder httpBuider = HttpUrl.parse(url).newBuilder();
            httpBuider.addQueryParameter("i_format", "json")
                    .addQueryParameter("i_callback", "getReferData1")
                    .addQueryParameter("poolcode[]", "hhad")
                    .addQueryParameter("poolcode[]", "had")
            ;

            Request.Builder builder = new Request.Builder();
            builder.url(httpBuider.build())
                    .header("Accept-Language", "en-US,en;q=0.9")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36")
                    .header("Accept", "*/*")
                    .header("Referer", "http://info.sporttery.cn/football/hhad_list.php")
                    .header("Connection", "keep-alive");
            Request request = builder.build();
            try {
                Response response = okHttpClient.newCall(request).execute();
                String json = response.body().string();
                Pattern pattern = Pattern.compile("getReferData1[\\(](.*)[\\)]");
                Matcher matcher = pattern.matcher(json);
                if (matcher.find()) {
                    String group = matcher.group(1);
                    return parseProportion(group);
                }
            } catch (Exception e) {
                Log.e("loadProportion", "error to get http response with okhttp from url: " + url);
            }

            return Collections.emptyMap();
        }

        private Map<String, Proportion> parseProportion(@NonNull String json) {
            Map<String, Proportion> map = new HashMap<>();
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONObject dataObject = jsonObject.getJSONObject("data");
                JSONArray names = dataObject.names();
                for (int i = 0; i < names.length(); i++) {
                    String id = names.get(i).toString();
                    JSONObject idObject = dataObject.getJSONObject(id);
                    Proportion proportion = gson.fromJson(idObject.toString(), Proportion.class);
                    map.put(id, proportion);
                }
            } catch (JSONException e) {
                Log.e("parseOdds", "error to parseOdds json: " + json);
            }
            return map;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<Sporttery> sportteries) {
            super.onPostExecute(sportteries);

            List<ItemObject> dataList = groupByDate(sportteries);

            mRecyclerViewAdapter.dataListChanged(dataList);
        }

        private List<ItemObject> groupByDate(@NonNull List<Sporttery> sportteries) {
            Map<Date, SportteryHeader> headerMap = new HashMap<>();
            Map<SportteryHeader, List<ItemObject>> map = new TreeMap<>();
            for (Sporttery s : sportteries) {
                Date deadLine = s.getOdds().getDeadLine();
                SportteryHeader header = headerMap.get(deadLine);
                if (header == null) {
                    header = new SportteryHeader(deadLine);
                }
                List<ItemObject> list = map.get(header);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(s);
                header.incraseMatchCount();

                headerMap.put(deadLine, header);
                map.put(header, list);
            }
            Set<Map.Entry<SportteryHeader, List<ItemObject>>> entries = map.entrySet();

            List<ItemObject> list = new ArrayList<>();
            for (Map.Entry<SportteryHeader, List<ItemObject>> entry : entries) {
                SportteryHeader header = entry.getKey();
                List<ItemObject> values = entry.getValue();
                list.add(header);
                list.addAll(values);
            }
            return list;
        }
    }

    private OnSportterySelectedListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSportterySelectedListener) {
            mListener = (OnSportterySelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSportterySelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    interface OnSportterySelectedListener {
        boolean onSportterySelected(Sporttery sporttery, int oddsId);
    }
}
