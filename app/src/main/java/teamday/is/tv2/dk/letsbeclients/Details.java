package teamday.is.tv2.dk.letsbeclients;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


import teamday.is.tv2.dk.letsbeclients.models.Program;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView title = (TextView) findViewById(R.id.title);
        TextView description = (TextView) findViewById(R.id.description);


        Program details = (Program) getIntent().getSerializableExtra("Details");
        if (details != null) {
            title.setText(details.getTitle());
            description.setText(details.getDescription());
        }


    }

}
