package com.example.web3.demofragment.GPS;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class GPSTrackingService extends Service {
    public GPSTrackingService() {
    }
    LocationManager lm;
    String provider;
    Location l;
    double lng;
    double lat;
    String ts;
    GPSTracker gps;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
    @Override
    public void onStart(Intent intent, int startId) {
        // For time consuming an long tasks you can launch a new thread here...
        new SenderThread().start();
    }

    public class SenderThread extends Thread {
        SenderThread() {
        }

        public void run() {
            sentGPSLocation();
            for (; ; ) {
                try {
                  //  sentGPSLocation();
                    Thread.sleep(50000);
                } catch (Exception e) {
                }
            }
        }
    }
    public void sentGPSLocation() {
        try{
            gps = new GPSTracker(GPSTrackingService.this);
            if (gps.canGetLocation()) {

                lat = gps.getLatitude();
                lng = gps.getLongitude();

                // \n is for new line
                Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + lat + "\nLong: " + lng, Toast.LENGTH_LONG).show();
            } else {
                // can't get location
                // GPS or Network is not enabled
                // Ask user to enable GPS/network in settings
                gps.showSettingsAlert();
            }
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }

      /*  AsynchSentGPSLocation task = new AsynchSentGPSLocation();
        task.execute();*/
    }

    public class AsynchSentGPSLocation extends AsyncTask<Void, Void, Void> {
        String result;

        @Override
        protected Void doInBackground(Void... params) {
            // check if GPS enabled
            if (gps.canGetLocation()) {

                lat = gps.getLatitude();
                lng = gps.getLongitude();

                // \n is for new line
                //  Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            } else {
                // can't get location
                // GPS or Network is not enabled
                // Ask user to enable GPS/network in settings
                //gps.showSettingsAlert();
            }

           /* HttpPost request = new HttpPost("");
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");
            try {
                Calendar c1 = Calendar.getInstance();
                System.out.println("Current time => " + c1.getTime());

                //String strTime=c1.getTime().toString();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
                String formattedDate = df.format(c1.getTime());
                String formattedtime = tf.format(c1.getTime());
                JSONStringer item = new JSONStringer().object()
                        .key("USER_ID").value(String.valueOf(userId)).key("DATE").value(formattedDate)
                        .key("TIME").value(formattedtime)
                        .key("LONGITUDE").value(String.valueOf(lng))
                        .key("LATITUDE").value(String.valueOf(lat)).endObject();
                Log.i("Sending data", item.toString());
                StringEntity entity = new StringEntity(item.toString());
                request.setEntity(entity);
                // Set timeout values to
                BasicHttpParams httpParameters = new BasicHttpParams();
                // Set the timeout in milliseconds until a connection is
                // established.
                // The default value is zero, that means the timeout is not
                // used.
                int timeoutConnection = 4000;
                HttpConnectionParams.setConnectionTimeout(httpParameters,
                        timeoutConnection);
                // Set the default socket timeout (SO_TIMEOUT)
                // in milliseconds which is the timeout for waiting for data.
                int timeoutSocket = 6000;
                HttpConnectionParams
                        .setSoTimeout(httpParameters, timeoutSocket);

                // Send request to WCF service with timeout values
                DefaultHttpClient httpClient = new DefaultHttpClient(
                        httpParameters);
                HttpResponse response = httpClient.execute(request);

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    String responseBody = EntityUtils.toString(response
                            .getEntity());

                    // Read response data into buffer
                    result = responseBody.trim();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            return null;
        }
    }
}
