package grit.android.teslalist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    String[] items;
    String[] descriptions;
    String [] prices;

    // Constructor
    public ItemAdapter(Context c, String [] i, String [] p, String [] d ){
        items = i;
        descriptions = d;
        prices = p;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Creates a customized layout
        View v = mInflater.inflate(R.layout.list_item_layout, null);

        // assigns the textviews in our customized listview
        TextView nameTextView = (TextView) v.findViewById(R.id.itemNameTxt);
        TextView descriptionTextView = (TextView) v.findViewById(R.id.itemDescriptionTxt);
        TextView priceTextView = (TextView) v.findViewById(R.id.itemPriceTxt);

        // initializes variables
        String name = items[position];
        String description = descriptions[position];
        String cost = prices[position];

        // Sets text into our listview
        nameTextView.setText(name);
        descriptionTextView.setText(description);
        priceTextView.setText(cost);

        return v;
    }
}
