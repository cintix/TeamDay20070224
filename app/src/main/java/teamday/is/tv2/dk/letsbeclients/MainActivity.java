package teamday.is.tv2.dk.letsbeclients;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import teamday.is.tv2.dk.letsbeclients.models.Program;
import teamday.is.tv2.dk.letsbeclients.models.ProgramSearch;
import teamday.is.tv2.dk.letsbeclients.tasks.RestTask;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    private static final String ACTION_FOR_INTENT_CALLBACK = "THIS_IS_A_UNIQUE_KEY_WE_USE_TO_COMMUNICATE";
    private ArrayAdapter<String> adapter;
    private ListView listview;
    private Gson gson = new Gson();
    private ProgressDialog progress;


    private ProgramSearch search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList());
        listview = (ListView) findViewById(R.id.locaList);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);

        addItems();

    }


    private void addItems() {
        progress = ProgressDialog.show(this, "Getting Data ...", "Waiting For Results...", true);
        search = getContent("http://r7.tv2.dk/api/3/programs/ApplicationCode-play_android/order-popularity/limit-25.json", ProgramSearch.class);
        if (search != null)
        for(Program program :search.getPrograms()) {
            adapter.add(program.getTitle());
        }

        adapter.notifyDataSetChanged();
        Log.d(this.getLocalClassName(), "Added items");
        progress.dismiss();

    }


    private <T> T  getContent(String url, Class<?> instance) {

        try {
            RestTask task = new RestTask(this, ACTION_FOR_INTENT_CALLBACK);
            String jsonResult = task.execute(url).get();
            Log.d(getLocalClassName(), jsonResult);
            return (T) gson.fromJson(jsonResult, instance);
        } catch (Exception e) {
            Log.e(getLocalClassName(), e.getMessage());
        }
        return null;
    }


    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Log.d(this.getLocalClassName(), "Selected " + adapter.getItem(position));
        if (search != null) {
            Intent intent = new Intent(this, Details.class);
            intent.putExtra("Details", search.getPrograms().get(position));
            startActivity(intent);
        }


    }

}
