package teamday.is.tv2.dk.letsbeclients.tasks;

/**
 * Created by migo on 23/02/17.
 */

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class RestTask extends AsyncTask<String, Void, String> {
    private static final String TAG = "AARestTask";
    public static final String HTTP_RESPONSE = "httpResponse";

    private Context mContext;
    private String mAction;

    public RestTask(Context context, String action) {
        mContext = context;
        mAction = action;
    }


    @Override
    protected String doInBackground(String... params) {
        try {

            StringBuilder builder = new StringBuilder();

            HttpURLConnection request = (HttpURLConnection) new URL(params[0]).openConnection();
            request.setRequestProperty("Content-Type", "application/json");
            InputStream inputStream = request.getInputStream();

            byte[] buffer = new byte[2048];
            int read = 0;
            while(read != -1) {
                read = inputStream.read(buffer);
                if (read != -1) {
                    builder.append(new String(buffer,0,read));
                    buffer = new byte[2048];
                }
            }
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Log.i(TAG, "RESULT = " + result);
        Intent intent = new Intent(mAction);
        intent.putExtra(HTTP_RESPONSE, result);

        // broadcast the completion
        mContext.sendBroadcast(intent);
    }

}
