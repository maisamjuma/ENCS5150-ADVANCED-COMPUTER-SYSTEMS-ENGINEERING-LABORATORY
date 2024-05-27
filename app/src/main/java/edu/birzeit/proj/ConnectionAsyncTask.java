package edu.birzeit.proj;



import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;
public class ConnectionAsyncTask extends AsyncTask<String, String,
        String> {
    Activity activity;

    public ConnectionAsyncTask(Activity activity) {
        this.activity = activity;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ((MainActivity) activity).setProgress(true);
    }
    @Override
    protected String doInBackground(String... params) {
        String data = HttpManager.getData(params[0]);
        return data;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        ((MainActivity) activity).setProgress(false);
        if (s != null) {
            // Connection successful, proceed with intent
            Intent intent = new Intent(activity, secondMain.class);
            activity.startActivity(intent);
            activity.finish();
        } else {
            // Connection failed, show toast message
            Toast.makeText(activity, "Connection failed", Toast.LENGTH_SHORT).show();
        }

    }

}
