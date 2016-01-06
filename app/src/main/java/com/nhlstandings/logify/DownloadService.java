package com.nhlstandings.logify;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import java.io.IOException;

public class DownloadService extends IntentService {

    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;
    public static final int STATUS_CONFERENCE = 3;
    public static final int STATUS_DIVISION = 4;
    public static final String DATA_PACKAGE = "datapackage";
    private static final String TAG = "DownloadService";


    public DownloadService() {
        super(DownloadService.class.getName());
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d(TAG, "Service Started!");

        final ResultReceiver receiver = intent.getParcelableExtra("receiver");

        Bundle bundle = new Bundle();

        /* Update UI: Download Service is Running */
        receiver.send(STATUS_RUNNING, Bundle.EMPTY);

        //download data from URL in @String folder
        //convert stream provided into JSON String
        DownloadURLData downloadURLData = new DownloadURLData();
        InputStreamConverter inputStreamConverter = new InputStreamConverter(getApplicationContext());

        try {
            String result = inputStreamConverter.convertInputStreamToString(
                    downloadURLData.downloadData(getApplicationContext().getString(R.string.API_FILE_URL)));
            if(result != null || !result.equals("")){
                SavedData.saveJSONData(getApplicationContext(),result);
            }
            bundle.putString(Intent.EXTRA_TEXT,result);
            Log.d(TAG, result);
            receiver.send(STATUS_FINISHED, bundle);
        } catch (IOException e) {
            e.printStackTrace();
            bundle.putString(Intent.EXTRA_TEXT,e.getMessage().toString());
            receiver.send(STATUS_ERROR,bundle);
        } catch (DownloadException e) {
            e.printStackTrace();
            bundle.putString(Intent.EXTRA_TEXT,e.getMessage().toString());
            receiver.send(STATUS_ERROR, bundle);
        }

        Log.d(TAG, "Service Stopping!");
        this.stopSelf();
    }
}
