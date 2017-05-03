package verve.vaibhavpote.web3.add_verification.AOValidation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import verve.vaibhavpote.web3.add_verification.CAFPickup.caf_pickup_caf_details_activity;
import verve.vaibhavpote.web3.add_verification.Model_class.JSONParser;
import verve.vaibhavpote.web3.add_verification.Model_class.caf_pickup_dist_model;
import verve.vaibhavpote.web3.add_verification.R;
import verve.vaibhavpote.web3.add_verification.URLConfig;

/**
 * Created by Vaibhav_Pote on 5/30/2016.
 */
public class AOValidation_CAFDetails_Fragment extends Fragment {
    public AOValidation_CAFDetails_Fragment() {
    }

    FloatingActionButton fab_RunnerCancel, fab_RunnerSearch;
    AutoCompleteTextView mAutoCafSearch;
    String strAdminUserID, strAdminUsername, strAdminUserType, message, strCAFNumber, strRec_id, strACompCAF, strDistNamePos, strDistId;
    Spinner mSpiCAFPickupDist;
    ArrayAdapter<String> adapter;
    CustomSpinnerList customSpinnerList;
    JSONArray jsonArray = null;
    JSONParser jsonParser = new JSONParser();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_VALUES = "value";
    private static final String TAG_MESSAGE = "message";
    public static final String MyPREFERENCES = "MyPrefs";
    ArrayList<caf_pickup_dist_model> distributorList = new ArrayList<caf_pickup_dist_model>();

    public AOValidation_CAFDetails_Fragment(String strAdminUserID, String strAdminUsername, String strAdminUserType) {
        this.strAdminUserID = strAdminUserID;
        this.strAdminUserType = strAdminUserType;
        this.strAdminUsername = strAdminUsername;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.caf_pickup_allocation_fragment, container, false);
        findViewByIds(rootView);
        getDistributorList();

        mSpiCAFPickupDist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                long pos = parent.getItemIdAtPosition(position);
                try {
                    caf_pickup_dist_model spinner_model = (caf_pickup_dist_model) parent.getItemAtPosition(position);
                    strDistNamePos = spinner_model.getDistName();
                    strDistId = spinner_model.getDistId();
                    if (pos != 0) {
                        getCAFNumberDetails();
                        Log.w("Dist Pos", String.valueOf(pos));
                        Log.w("Dist ID", strDistId);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mAutoCafSearch.setText("");
            }
        });
        return rootView;
    }

    public void findViewByIds(View rootView) {
        mAutoCafSearch = (AutoCompleteTextView) rootView.findViewById(R.id.mAutoCafSearch);
        mSpiCAFPickupDist = (Spinner) rootView.findViewById(R.id.mSpiCAFPickupDist);
        fab_RunnerCancel = (FloatingActionButton) rootView.findViewById(R.id.fab_RunnerCancel);//btn 1
        fab_RunnerSearch = (FloatingActionButton) rootView.findViewById(R.id.fab_RunnerSearch);//btn 2jvvv

        fab_RunnerSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //.....for hide keyboard on button click
                InputMethodManager inputManager = (InputMethodManager) getActivity().getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                strACompCAF = mAutoCafSearch.getText().toString();
                Log.w("strACompCAF", strACompCAF);
                if (!strACompCAF.equals("")) {
                    Intent intent= new Intent(getActivity(),caf_pickup_caf_details_activity.class);
                    intent.putExtra("Dist_Name",strDistNamePos);
                    intent.putExtra("CAF_Number",strACompCAF);
                    startActivity(intent);
                    mAutoCafSearch.setText("");
                    mSpiCAFPickupDist.setSelection(0);
                } else if (strACompCAF.equals("") || strACompCAF == null) {
                    mAutoCafSearch.requestFocus();
                    mAutoCafSearch.setError("Please enter CAF number to get status update");
                }
            }
        });
        fab_RunnerCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAutoCafSearch.setText("");
                mSpiCAFPickupDist.setSelection(0);
            }
        });
    }

    //.................open progress dialog on button click
    private void getCAFNumberDetails() {
        // TODO Auto-generated method stub
        final ProgressDialog progDailog = ProgressDialog.show(getActivity(), "Testing", "Please wait...", true);

        new Thread() {
            public void run() {
                try {
                    new AsyncGetCAFNumber().execute();
                } catch (Exception e) {
                }
                progDailog.dismiss();
            }
        }.start();
    }

    private void getDistributorList() {
        // TODO Auto-generated method stub
        final ProgressDialog progDailog = ProgressDialog.show(getActivity(), "Testing", "Please wait...", true);

        new Thread() {
            public void run() {
                try {
                    new AsyncgetDistributorList().execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progDailog.dismiss();
            }
        }.start();
    }

    public class AsyncGetCAFNumber extends AsyncTask<String, String, String> {
        List<String> responseList;
        int success;
        String response;

        @Override
        protected String doInBackground(String... params) {
            try {
                responseList = new ArrayList<String>();
                List<NameValuePair> paramCSMCAF = new ArrayList<NameValuePair>();
                paramCSMCAF.add(new BasicNameValuePair("dealer_disritutor_code", strDistId));
                //paramCSMCAF.add(new BasicNameValuePair("type", strAdminUserType));
                JSONObject jsonObject = jsonParser.makeHttpRequest(URLConfig.GET_CAF_PICKUP_CAF_NO, "POST", paramCSMCAF);
                if (jsonObject == null) {
                    Log.d("Login data", "Connection Failed");
                } else {
                    response = jsonObject.toString();
                    Log.w("CSM CAF number", jsonObject.toString());
                    success = jsonObject.getInt(TAG_SUCCESS);
                    message = jsonObject.getString(TAG_MESSAGE);
                    if (success == 1) {
                        jsonArray = jsonObject.getJSONArray(TAG_VALUES);
                        if (responseList.size() == 0) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject c = jsonArray.getJSONObject(i);
                                strCAFNumber = c.getString("caf_no");//csm caf_no
                                //trRec_id = c.getString("rec_id");//user rec_id
                                responseList.add(strCAFNumber);
                            }
                        }

                    } else if (success == 0) {
                        Log.w("Success 0", message);
                    }
                }

            } catch (NullPointerException ex) {
                ex.printStackTrace();
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                if (success == 1) {
                    Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                    adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, responseList);
                    mAutoCafSearch.setThreshold(1);
                    mAutoCafSearch.setAdapter(adapter);
                } else if (success == 0) {
                    Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                }
            }
        }
    }

    //........to get distributor list
    public class AsyncgetDistributorList extends AsyncTask<String, String, ArrayList<caf_pickup_dist_model>> {
        int success;
        String distributor_name, dealer_disritutor_code;

        @Override
        protected ArrayList<caf_pickup_dist_model> doInBackground(String... params) {
            try {
                distributorList = new ArrayList<caf_pickup_dist_model>();
                List<NameValuePair> paramCSMCAF = new ArrayList<NameValuePair>();
                JSONObject jsonObject = jsonParser.makeHttpRequest(URLConfig.GET_CAF_PICKUP_DIST_LIST, "POST", paramCSMCAF);
                if (jsonObject == null) {
                    Log.d("CAF Pickup", "Connection Failed");
                } else {
                    Log.w("CAF Pickup Dist list", jsonObject.toString());
                    success = jsonObject.getInt(TAG_SUCCESS);
                    message = jsonObject.getString(TAG_MESSAGE);
                    if (distributorList.size() <= 1) {
                        if (success == 1) {
                            distributorList.add(new caf_pickup_dist_model("--Select--", "0"));
                            jsonArray = jsonObject.getJSONArray(TAG_VALUES);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject c = jsonArray.getJSONObject(i);

                                distributor_name = c.getString("distributor_name");//csm caf_no
                                dealer_disritutor_code = c.getString("dealer_disritutor_code");//user rec_id
                                distributorList.add(new caf_pickup_dist_model(distributor_name, dealer_disritutor_code));
                            }
                        }
                    }
                }
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<caf_pickup_dist_model> distributorList1) {
            super.onPostExecute(distributorList1);
            if (success == 1) {
                Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
                customSpinnerList = new CustomSpinnerList(getActivity(), R.layout.custom_spinner_text, distributorList);
                mSpiCAFPickupDist.setAdapter(customSpinnerList);
            } else if (success == 0) {
                Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
        }
    }


    public class CustomSpinnerList extends ArrayAdapter<caf_pickup_dist_model> {
        ArrayList<caf_pickup_dist_model> data;

        public CustomSpinnerList(Context ctx, int txtViewResourceId, ArrayList<caf_pickup_dist_model> data) {
            super(ctx, txtViewResourceId, data);
            this.data = data;
        }

        @Override
        public View getDropDownView(int position, View cnvtView, ViewGroup prnt) {
            return getCustomView(position, cnvtView, prnt);
        }

        @Override
        public View getView(int pos, View cnvtView, ViewGroup prnt) {
            return getCustomView(pos, cnvtView, prnt);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View mySpinner = inflater.inflate(R.layout.custom_spinner_text, parent, false);
            TextView main_text = (TextView) mySpinner
                    .findViewById(R.id.mTvSpinParam);
            caf_pickup_dist_model spinner_model = data.get(position);

            main_text.setText(spinner_model.getDistName());

          /*  TextView subSpinner = (TextView) mySpinner
                    .findViewById(R.id.mTvSpiScore);
            subSpinner.setText(spinner_model.getScore());*/
            return mySpinner;
        }
    }
}
