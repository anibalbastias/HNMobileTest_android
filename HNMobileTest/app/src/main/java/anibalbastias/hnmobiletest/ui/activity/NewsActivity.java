package anibalbastias.hnmobiletest.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import anibalbastias.hnmobiletest.R;
import anibalbastias.hnmobiletest.model.response.HNNewsResponse;
import anibalbastias.hnmobiletest.model.storage.StorageAPI;
import anibalbastias.hnmobiletest.util.Libcomun;

public class NewsActivity extends BaseActivity {
    private WebView mWebView;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_news);

        // Set data from Main
        Bundle extras = getIntent().getExtras();

        String id_new = extras.getString("id_new");

        StorageAPI storageAPI = new StorageAPI(NewsActivity.this);
        HNNewsResponse news = storageAPI.getHNNews();

        mWebView = (WebView) this.findViewById(R.id.web_view);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        TextView title_txt = (TextView) findViewById(R.id.title);
        TextView author_txt = (TextView) findViewById(R.id.author);
        TextView created_txt = (TextView) findViewById(R.id.created_at);
        TextView story_text_txt = (TextView) findViewById(R.id.story_text);

        String url_story = "";
        String title = "";
        String author = "";
        String created_at = "";
        String story_text = "";

        for (int i = 0; i < news.getHits().size(); i++) {
            if (news.getHits().get(i).getCreated_at_i() == Integer.parseInt(id_new)) {
                if (news.getHits().get(i).getStory_url() != null)
                    url_story = news.getHits().get(i).getStory_url();
                else if (news.getHits().get(i).getUrl() != null)
                    url_story = news.getHits().get(i).getUrl();

                if (news.getHits().get(i).getTitle() != null)
                    title = news.getHits().get(i).getTitle();
                else if (news.getHits().get(i).getStory_title() != null)
                    title = news.getHits().get(i).getStory_title();

                if (news.getHits().get(i).getAuthor() != null)
                    author = news.getHits().get(i).getAuthor();

                if (news.getHits().get(i).getCreated_at() != null)
                    created_at = news.getHits().get(i).getCreated_at();

                if (news.getHits().get(i).getStory_text() != null)
                    story_text = news.getHits().get(i).getStory_text();
                else if (news.getHits().get(i).getComment_text() != null)
                    story_text = news.getHits().get(i).getComment_text();
            }
        }

        if (url_story.equals("")) {
            mWebView.setVisibility(View.GONE);
            progress.setVisibility(View.GONE);
            title_txt.setVisibility(View.VISIBLE);
            author_txt.setVisibility(View.VISIBLE);
            created_txt.setVisibility(View.VISIBLE);
            story_text_txt.setVisibility(View.VISIBLE);

            // Load Title and story text in Android native
            // Title
            Libcomun.setFontTextViewRegular(title_txt, NewsActivity.this);
            title_txt.setText(Libcomun.decodeHtmlEntitiesString(title));

            // Author
            Libcomun.setFontTextViewLight(author_txt, NewsActivity.this);
            author_txt.setText(Libcomun.decodeHtmlEntitiesString(author));

            // Created at
            Libcomun.setFontTextViewLight(created_txt, NewsActivity.this);
            created_txt.setText(Libcomun.getPrettyTime(Libcomun.decodeHtmlEntitiesString(created_at)));

            // Story Text
            Libcomun.setFontTextViewLight(story_text_txt, NewsActivity.this);
            story_text_txt.setText(Libcomun.getPrettyTime(Libcomun.decodeHtmlEntitiesString(story_text)));
        } else {
            mWebView.setVisibility(View.VISIBLE);
            progress.setVisibility(View.VISIBLE);
            title_txt.setVisibility(View.GONE);
            author_txt.setVisibility(View.GONE);
            created_txt.setVisibility(View.GONE);
            story_text_txt.setVisibility(View.GONE);

            // Load WebView
            mWebView.setWebChromeClient(new MyWebViewClient());

            progress.setProgress(0);
            progress.setMax(100);

            // Enable Javascript
            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.loadUrl(url_story);

            mWebView.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    // do your handling codes here, which url is the requested url
                    // probably you need to open that url rather than redirect:
                    view.loadUrl(url);
                    return false; // then it is not handled by default action
                }
            });
        }

        (findViewById(R.id.back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class MyWebViewClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            setValue(newProgress);
            if (newProgress == 100)
                progress.setVisibility(View.INVISIBLE);

            super.onProgressChanged(view, newProgress);
        }
    }

    public void setValue(int progress) {
        this.progress.setProgress(progress);
    }
}
