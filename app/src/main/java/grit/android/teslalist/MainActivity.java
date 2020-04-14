package grit.android.teslalist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView myListView;
    String[] items;
    String[] descriptions;
    String [] prices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //saves typing
        Resources res = getResources();

        // assigns list and initialises arrays to fill it
        myListView = (ListView) findViewById(R.id.carListView);
        items = res.getStringArray(R.array.items);
        descriptions = res.getStringArray(R.array.description);
        prices =res.getStringArray(R.array.prices);

        //Merge listview with our string arrays
        ItemAdapter itemAdapter = new ItemAdapter(this, items, prices, descriptions);
        myListView.setAdapter(itemAdapter);

        // adds an onclicklistner to our list
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // sets new intent to open correct choice
                Intent showDetailActivity = new Intent(getApplicationContext(), DetailsActivity.class);

                // sends our choice to new activity
                showDetailActivity.putExtra("ITEM_INDEX", position);

                //open new chosen activity
                startActivity(showDetailActivity);
            }
        });
    }
}
