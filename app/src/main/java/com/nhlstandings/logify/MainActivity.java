package com.nhlstandings.logify;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements DownloadResultReceiver.Receiver {

    private DownloadResultReceiver mReceiver;
    private MainActivityFragment fragment;

    public final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //Starting Download Service
        mReceiver = new DownloadResultReceiver(new Handler());
        mReceiver.setReceiver(this);
        Intent intent = new Intent(Intent.ACTION_SYNC, null, this, DownloadService.class);

        // Send optional extras to Download IntentService
        intent.putExtra("receiver", mReceiver);
        intent.putExtra("requestId", 101);

        startService(intent);

        fragment = (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
    }

    /*
        Used to receive messages from Download Service
     */
    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case DownloadService.STATUS_RUNNING:
                setProgressBarIndeterminateVisibility(true);
                break;
            case DownloadService.STATUS_FINISHED:
                /* Hide progress & extract result from bundle */
                setProgressBarIndeterminateVisibility(false);
                //throw new divisonal standings to attachment
                fragment.updateStandings(resultData.getString(Intent.EXTRA_TEXT));
                break;
            case DownloadService.STATUS_ERROR:
                /* Handle the error */
                String error = resultData.getString(Intent.EXTRA_TEXT);
                //Toast.makeText(this, error, Toast.LENGTH_LONG).show();
                showErrorDialog(error);
                break;
            case DownloadService.STATUS_CONFERENCE:

                break;
            case DownloadService.STATUS_DIVISION:
                break;
        }
    }

    private void showErrorDialog(String error){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this,
                android.support.v7.appcompat.R.style.AlertDialog_AppCompat);

        // set title
        alertDialogBuilder.setTitle("Your Title");

        // set dialog message
        alertDialogBuilder
                .setTitle("ERROR")
                .setMessage("Error Code: " + error + "\n" + "Make sure you are connected to a network and try again")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        MainActivity.this.finish();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "ON DESTROY");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "ON PAUSE");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"ON RESUME");
    }
}
