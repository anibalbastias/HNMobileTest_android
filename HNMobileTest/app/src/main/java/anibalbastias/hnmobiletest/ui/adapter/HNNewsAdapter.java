package anibalbastias.hnmobiletest.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import anibalbastias.hnmobiletest.R;
import anibalbastias.hnmobiletest.model.HNNews;
import anibalbastias.hnmobiletest.ui.activity.NewsActivity;
import anibalbastias.hnmobiletest.util.Libcomun;

/**
 * Created by anibalbastias on 06-10-15.
 */
public class HNNewsAdapter extends ArrayAdapter<HNNews> {

    private final Activity c;

    // Filter
    private ArrayList<HNNews> originalData = null;
    private ArrayList<HNNews> filteredData = null;
    private LayoutInflater mInflater;

    public void updateValues(ArrayList<HNNews> lista) {
        this.filteredData = lista;
        this.originalData = lista;
        notifyDataSetChanged();
    }

    public HNNewsAdapter(Activity c, ArrayList<HNNews> lista) {
        super(c, R.layout.item_hn_news, lista);
        this.c = c;
        updateValues(lista);
        mInflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // A ViewHolder keeps references to children views to avoid unnecessary calls
        // to findViewById() on each row.
        ViewHolder holder;

        // When convertView is not null, we can reuse it directly, there is no need
        // to reinflate it. We only inflate a new View when the convertView supplied
        // by ListView is null.

        final HNNews item = filteredData.get(position);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_hn_news, null);

            // Creates a ViewHolder and store references to the two children views
            // we want to bind data to.
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.author = (TextView) convertView.findViewById(R.id.author);
            holder.created_at = (TextView) convertView.findViewById(R.id.created_at);

            // Bind the data efficiently with the holder.

            convertView.setTag(holder);
        } else {
            // Get the ViewHolder back to get fast access to the TextView
            // and the ImageView.
            holder = (ViewHolder) convertView.getTag();
        }

        // If weren't re-ordering this you could rely on what you set last time
        //holder.text.setText(filteredData.get(position).getTitle_image());

        // Title
        Libcomun.setFontTextViewRegular(holder.title, c);
        holder.title.setText(Libcomun.decodeHtmlEntitiesString(item.getTitle()));

        // Author
        Libcomun.setFontTextViewLight(holder.author, c);
        holder.author.setText(Libcomun.decodeHtmlEntitiesString(item.getAuthor()));

        // Created at
        Libcomun.setFontTextViewLight(holder.created_at, c);
        holder.created_at.setText(Libcomun.getPrettyTime(Libcomun.decodeHtmlEntitiesString(item.getCreated_at())));

        (convertView.findViewById(R.id.item_news)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(c, NewsActivity.class);
                intent5.putExtra("id_new", item.getId_new());
                c.startActivity(intent5);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView title;
        TextView author;
        TextView created_at;
    }
}
