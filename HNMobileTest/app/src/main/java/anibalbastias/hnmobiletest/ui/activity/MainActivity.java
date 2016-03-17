package anibalbastias.hnmobiletest.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.OnItemMovedListener;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.OnDismissCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import anibalbastias.hnmobiletest.R;
import anibalbastias.hnmobiletest.model.HNNews;
import anibalbastias.hnmobiletest.model.response.HNNewsResponse;
import anibalbastias.hnmobiletest.model.storage.StorageAPI;
import anibalbastias.hnmobiletest.network.NetworkAPI;
import anibalbastias.hnmobiletest.network.eventbus.events.ApiErrorEvent;
import anibalbastias.hnmobiletest.network.eventbus.events.HNMobileEvents;
import anibalbastias.hnmobiletest.ui.adapter.HNNewsAdapter;
import anibalbastias.hnmobiletest.ui.view.pulltorefresh_loadmore.PullAndLoadListView;
import anibalbastias.hnmobiletest.ui.view.pulltorefresh_loadmore.PullToRefreshListView;
import anibalbastias.hnmobiletest.util.Libcomun;

public class MainActivity extends BaseActivity {

    private int page_news;
    private ArrayList<HNNews> hnNews;
    private DynamicListView listView;
    private HNNewsAdapter adapter_news;
    private boolean is_refresh = false;
    private StorageAPI storageAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_main);

        storageAPI = new StorageAPI(MainActivity.this);
        setXML();
    }

    private void setXML() {
        actionGetListView();
        is_refresh = false;
        page_news = 0;

        // ListView HNNews
        listView = (DynamicListView) findViewById(R.id.items);

        Libcomun.setFontTextViewRegular((TextView) findViewById(R.id.button_refresh), MainActivity.this);
        (findViewById(R.id.button_refresh)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionGetListView();
            }
        });
    }

    @Subscribe
    public void getHNNews(HNMobileEvents.HNNewsEvent hnNews_items) {
        setAdapterListView(hnNews_items);

        // Set a listener to be invoked when the list should be refreshed.
        ((PullAndLoadListView) listView)
                .setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {
                    public void onRefresh() {
                        // Do work to refresh the list here.
                        page_news = 0;
                        is_refresh = true;
                        new NetworkAPI(MainActivity.this).getHNNews(0, getString(R.string.android), page_news);
                    }
                });

        // set a listener to be invoked when the list reaches the end
        ((PullAndLoadListView) listView)
                .setOnLoadMoreListener(new PullAndLoadListView.OnLoadMoreListener() {
                    public void onLoadMore() {
                        // Do the work to load more items at the end of list
                        // here
                        is_refresh = false;
                        ++page_news;
                        new NetworkAPI(MainActivity.this).getHNNews(2, getString(R.string.android), page_news);
                    }
                });

        if (is_refresh) {
            // We need notify the adapter that the data have been changed
            adapter_news.notifyDataSetChanged();

            // Call onLoadMoreComplete when the LoadMore task, has finished
            ((PullAndLoadListView) listView).onRefreshComplete();
        }

        // Save news in offline mode and persistence data
        storageAPI.saveHNNews(hnNews_items);
    }

    @Subscribe
    public void getHNNewsLoadMore(HNMobileEvents.HNNewsLoadMoreEvent hnNews_items) {
        // Harcode Creator
        ArrayList<HNNews> hnNews_more = new ArrayList<>();

        for (int i = 0; i < hnNews_items.getHits().size(); i++) {
            HNNews itemP1 = new HNNews();
            if (hnNews_items.getHits().get(i).getTitle() != null)
                itemP1.setTitle(hnNews_items.getHits().get(i).getTitle());
            else if (hnNews_items.getHits().get(i).getStory_title() != null)
                itemP1.setTitle(hnNews_items.getHits().get(i).getStory_title());
            itemP1.setAuthor(hnNews_items.getHits().get(i).getAuthor());
            itemP1.setCreated_at(hnNews_items.getHits().get(i).getCreated_at());
            itemP1.setId_new("" + hnNews_items.getHits().get(i).getCreated_at_i());

            // Insert objects
            hnNews_more.add(itemP1);
        }

        hnNews.addAll(hnNews_more);

        // We need notify the adapter that the data have been changed
        adapter_news.notifyDataSetChanged();

        // Call onLoadMoreComplete when the LoadMore task, has finished
        ((PullAndLoadListView) listView).onLoadMoreComplete();

        // Save and add new items of news in offline mode and persistence data
        HNNewsResponse hnNewsResponse = storageAPI.getHNNews();
        ArrayList<HNNewsResponse.Hits> hits = hnNewsResponse.getHits();
        hits.addAll(hnNews_items.getHits());

        storageAPI.saveHNNews(hnNews_items);
    }

    private void setAdapterListView(HNNewsResponse hnNews_items) {
        // Set Initial filter
        hnNews = new ArrayList<>();

        // Harcode Creator
        for (int i = 0; i < hnNews_items.getHits().size(); i++) {
            HNNews itemP1 = new HNNews();
            if (hnNews_items.getHits().get(i).getTitle() != null)
                itemP1.setTitle(hnNews_items.getHits().get(i).getTitle());
            else if (hnNews_items.getHits().get(i).getStory_title() != null)
                itemP1.setTitle(hnNews_items.getHits().get(i).getStory_title());
            itemP1.setAuthor(hnNews_items.getHits().get(i).getAuthor());
            itemP1.setCreated_at(hnNews_items.getHits().get(i).getCreated_at());
            itemP1.setId_new("" + hnNews_items.getHits().get(i).getCreated_at_i());

            // Insert objects
            hnNews.add(itemP1);
        }

        adapter_news = new HNNewsAdapter(MainActivity.this, hnNews);

        //SwipeDismissAdapter swipeDismissAdapter = new SwipeDismissAdapter(adapter_news, new MyOnDismissCallback(adapter_news));
        AlphaInAnimationAdapter animAdapter = new AlphaInAnimationAdapter(adapter_news);
        animAdapter.setAbsListView(listView);
        assert animAdapter.getViewAnimator() != null;
        animAdapter.getViewAnimator().setInitialDelayMillis(300);
        listView.setAdapter(animAdapter);

         /* Enable swipe to dismiss */

        //listView.enableSimpleSwipeUndo();
        listView.setOnItemMovedListener(new MyOnItemMovedListener(adapter_news));
        listView.setAdapter(animAdapter);

        /* Enable swipe to dismiss */
        listView.enableSwipeToDismiss(new MyOnDismissCallback(adapter_news));

        try {
            adapter_news = (HNNewsAdapter) listView.getAdapter();
            adapter_news.getFilter().filter("");

        } catch (Exception e) {

        }
    }


    private class MyOnDismissCallback implements OnDismissCallback {

        private final HNNewsAdapter mAdapter;

        MyOnDismissCallback(final HNNewsAdapter adapter) {
            mAdapter = adapter;
        }

        @Override
        public void onDismiss(@NonNull final ViewGroup listView, @NonNull final int[] reverseSortedPositions) {
            for (int position : reverseSortedPositions) {
                hnNews.remove(position);
                adapter_news.notifyDataSetChanged();
            }
        }
    }

    private class MyOnItemMovedListener implements OnItemMovedListener {

        private final HNNewsAdapter mAdapter;
        private Toast mToast;

        MyOnItemMovedListener(final HNNewsAdapter adapter) {
            mAdapter = adapter;
        }

        @Override
        public void onItemMoved(final int originalPosition, final int newPosition) {
            if (mToast != null) {
                mToast.cancel();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    private void actionGetListView() {
        // Call data
        if (Libcomun.isNetworkAvailable(MainActivity.this)) {
            (findViewById(R.id.error_internet_layout)).setVisibility(View.GONE);
            new NetworkAPI(MainActivity.this).getHNNews(0, getString(R.string.android), page_news);
        } else {
            if (storageAPI.getHNNews() != null) {
                (findViewById(R.id.error_internet_layout)).setVisibility(View.GONE);
                HNNewsResponse hnNewsResponse = storageAPI.getHNNews();
                setAdapterListView(hnNewsResponse);
            } else {
                (findViewById(R.id.error_internet_layout)).setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
        //closing transition animations
        overridePendingTransition(R.anim.activity_open_scale, R.anim.activity_close_translate);
    }

    @Subscribe
    public void onError(ApiErrorEvent error) {
        ((PullAndLoadListView) listView).onRefreshComplete();
    }
}
