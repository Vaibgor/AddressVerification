package verve.vaibhavpote.web3.add_verification.WirelineLogin;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import verve.vaibhavpote.web3.add_verification.Services.BackgroundService;
import verve.vaibhavpote.web3.add_verification.Model_class.CSM_Complaint_status_model;
import verve.vaibhavpote.web3.add_verification.Model_class.JSONParser;
import verve.vaibhavpote.web3.add_verification.R;
import verve.vaibhavpote.web3.add_verification.URLConfig;

/**
 * Created by Vaibhav_Pote on 5/30/2016.
 */
public class Wireline_ViewRaisedComplaint_Fragment extends Fragment {
    public Wireline_ViewRaisedComplaint_Fragment() {
    }

    ListView mLvCompView;
    TextView totalCount;
    private SwipeRefreshLayout swipeContainer;
    CustomCSMComplaintList customCSMComplaintList;
    String strCSMUserID, strUser_name, strCSMUserType;
    JSONParser jsonParser3 = new JSONParser();
    JSONArray jsonArray3 = null;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_VALUES = "value";
    private static final String TAG_MESSAGE = "message";
    public static final String MyPREFERENCES = "MyPrefs";
    ArrayList<CSM_Complaint_status_model> csmArray = new ArrayList<CSM_Complaint_status_model>();

    public Wireline_ViewRaisedComplaint_Fragment(String strCSMUserID, String strUser_name, String strCSMUserType) {
        this.strCSMUserID = strCSMUserID;
        this.strUser_name = strUser_name;
        this.strCSMUserType = strCSMUserType;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_csm_viewcomplaint_list, container, false);
        findViewByIds(rootView);// call for ids
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    if (isConnectingToInternet(getActivity())) {
                        getComplaintStatusListData();
                    } else
                        Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }
            }
        });
        swipeContainer.post(new Runnable() {
                                @Override
                                public void run() {
                                    swipeContainer.setRefreshing(true);
                                    getComplaintStatusListData();
                                }
                            }
        );
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        return rootView;
    }

    public void findViewByIds(View rootView) {
        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        totalCount = (TextView) rootView.findViewById(R.id.mTvRowCount);
        mLvCompView = (ListView) rootView.findViewById(R.id.mLvCsmCompView);
    }

    private void getComplaintStatusListData() {
        // TODO Auto-generated method stub
        final ProgressDialog progDailog = ProgressDialog.show(getActivity(), "Getting details", "Please wait...", true);
        new Thread() {
            public void run() {
                try {
                    new getComplaintStatusListData().execute();
                } catch (Exception e) {
                }
                progDailog.dismiss();
            }
        }.start();
    }

    private class getComplaintStatusListData extends AsyncTask<String, String, ArrayList<CSM_Complaint_status_model>> {
        int success, count;
        String cafno, csmDateTime, complaint_type, querySubType, csmQueryRemark, resolveDateTime,
                resolveBy, submittedBy, resolveRemark, status;
        private String rec_id, rec_main = " ";
        String message;
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("Loading:", "ReVerification Data");
        }

        protected ArrayList<CSM_Complaint_status_model> doInBackground(String... param) {
            ArrayList<CSM_Complaint_status_model> arrayCSMComp = new ArrayList<CSM_Complaint_status_model>();
            List<NameValuePair> params3 = new ArrayList<NameValuePair>();
            try {
                params3.add(new BasicNameValuePair("user_id", strCSMUserID));
                JSONObject jsonObject3 = jsonParser3.makeHttpRequest(URLConfig.GET_CSM_COMPLAINT_STATUS, "POST", params3);
                Log.w("CSM Submitted Comp", jsonObject3.toString());
                success = jsonObject3.getInt(TAG_SUCCESS);
                message = jsonObject3.getString(TAG_MESSAGE);
                if (success == 1) {
                    jsonArray3 = jsonObject3.getJSONArray(TAG_VALUES);
                    for (int i1 = 0; i1 < jsonArray3.length(); i1++) {
                        JSONObject c = jsonArray3.getJSONObject(i1);
                        cafno = c.getString("cafno");
                        csmDateTime = c.getString("csm_date");
                        complaint_type = c.getString("complaint_type");
                        querySubType = c.getString("scenario");
                        csmQueryRemark = c.getString("complaint_remark");
                        resolveRemark = c.getString("resolution_remark");
                        resolveBy = c.getString("csm_updated_by");
                        resolveDateTime = c.getString("csm_update_date");
                        status = c.getString("status");
                        arrayCSMComp.add(new CSM_Complaint_status_model(cafno, "Request", complaint_type, querySubType, csmQueryRemark,
                                csmDateTime, resolveDateTime, resolveBy, strUser_name, resolveRemark, status));
                    }
                } else if (success == 0) {
                    Log.w("Success message", message);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
            return arrayCSMComp;
        }
        @Override
        protected void onPostExecute(ArrayList<CSM_Complaint_status_model> arrayCSMComp) {
            super.onPostExecute(arrayCSMComp);
            try {
                swipeContainer.setRefreshing(false);
                if (arrayCSMComp.size()>0){
                    if (success == 1) {
                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
                        String formattedDate = df.format(c.getTime());
                        customCSMComplaintList = new CustomCSMComplaintList(getActivity(), R.layout.csm_custom_list_adapter, arrayCSMComp);
                        mLvCompView.setAdapter(customCSMComplaintList);
                        totalCount.setText(String.valueOf(arrayCSMComp.size()));
                        if (!resolveBy.equals("0") && resolveBy != null) {
                            if (CheckDates(formattedDate, resolveDateTime)){
                                Log.w("Notify on ResolveDate",resolveDateTime);
                                if (!isNotificationServiceRunning()) {
                                    Log.w("Notify on ResolveDate","Working");
                                    getActivity().startService(new Intent(getActivity(), BackgroundService.class));
                                }
                            }
                        }
                    } else if (success == 0) {
                        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.show();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
   public static boolean CheckDates(String startDate, String endDate) {

        SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");

        boolean b = false;

        try {
            if (dfDate.parse(startDate).before(dfDate.parse(endDate))) {
                b = true;  // If start date is before end date.
            } else if (dfDate.parse(startDate).equals(dfDate.parse(endDate))) {
                b = true;  // If two dates are equal.
            } else {
                b = false; // If start date is after the end date.
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return b;
    }
    //.......bind customo Adapter through service
    public class CustomCSMComplaintList extends ArrayAdapter<CSM_Complaint_status_model> {
        private LayoutInflater inflater = null;
        Context context;
        int layoutResourceId;
        // String agency_id;
        ArrayList<CSM_Complaint_status_model> csmData = new ArrayList<CSM_Complaint_status_model>();

        public CustomCSMComplaintList(Context context, int layoutResourceId, ArrayList<CSM_Complaint_status_model> data) {
            super(context, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.context = context;
            this.csmData = data;
        }

        public class Holder {
            ImageView mIvProcessIcon;
            LinearLayout mLlCsmResolver, mLlReV1ResolveCase;
            TextView mTvCAFno, mTvCsmDate, mTvCsmQuery, mTvCsmComplaint, mTvCsmRemark, mTvCsmResolveBy,
                    mTvCsmResolveDate, mTvCsmSubmitted_By, mTvCsmResolveRemark, mTvCsmAddStatus;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View list = convertView;
            Holder holder = new Holder();
            if (list == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                list = inflater.inflate(layoutResourceId, parent, false);

                holder.mLlCsmResolver = (LinearLayout) list.findViewById(R.id.mLlCsmResolver);
                holder.mIvProcessIcon = (ImageView) list.findViewById(R.id.mIvCSMCompIcon);
                holder.mTvCAFno = (TextView) list.findViewById(R.id.mTvCsmCAFNumber);
                holder.mTvCsmDate = (TextView) list.findViewById(R.id.mTvCsmDate);
                holder.mTvCsmQuery = (TextView) list.findViewById(R.id.mTvCsmQuery);
                holder.mTvCsmComplaint = (TextView) list.findViewById(R.id.mTvCsmComplaint);
                holder.mTvCsmRemark = (TextView) list.findViewById(R.id.mTvCsmRemark);
                holder.mTvCsmResolveBy = (TextView) list.findViewById(R.id.mTvCsmResolveBy);
                holder.mTvCsmResolveDate = (TextView) list.findViewById(R.id.mTvCsmResolveDate);
                holder.mTvCsmSubmitted_By = (TextView) list.findViewById(R.id.mTvCsmSubmitted_By);
                holder.mTvCsmResolveRemark = (TextView) list.findViewById(R.id.mTvCsmResolveRemark);
                holder.mTvCsmAddStatus = (TextView) list.findViewById(R.id.mTvCsmAddStatus);
                list.setTag(holder);
            } else {
                holder = (Holder) list.getTag();
            }
            CSM_Complaint_status_model csmDetail = csmData.get(position);

            final String strCAFno = csmDetail.getCafno();
            final String strCsmDate = csmDetail.getCsmDateTime();
            final String strCsmType = csmDetail.getQueryType();
            final String strCsmSubType = csmDetail.getQuerySubType();
            final String strCsmRemark = csmDetail.getCsmQueryRemark();
            final String strResolveBy = csmDetail.getResolveBy();
            final String strSubmitBy = csmDetail.getSubmittedBy();
            final String strResolveRemark = csmDetail.getResolveRemark();
            final String strStatus = csmDetail.getStatus();
            final String strResolveDate = csmDetail.getResolveDateTime();
            if (strResolveBy.equals("0") || strResolveBy == null) {
                holder.mLlCsmResolver.setVisibility(View.GONE);
                holder.mIvProcessIcon.setImageResource(R.drawable.ic_pie_chart);
                holder.mTvCAFno.setText(strCAFno);
                holder.mTvCsmDate.setText(strCsmDate);
                holder.mTvCsmQuery.setText(strCsmType);
                holder.mTvCsmComplaint.setText(strCsmSubType);
                holder.mTvCsmRemark.setText(strCsmRemark);
               /* holder.mTvCsmResolveBy.setText(strResolveBy);
                holder.mTvCsmResolveDate.setText(strResolveDate);
                holder.mTvCsmSubmitted_By.setText(strSubmitBy);
                holder.mTvCsmResolveRemark.setText(strResolveRemark);*/
                holder.mTvCsmAddStatus.setText(strStatus);
            } else if (!strResolveBy.equals("0") && strResolveBy != null) {
                holder.mLlCsmResolver.setVisibility(View.VISIBLE);
                holder.mIvProcessIcon.setImageResource(R.drawable.ic_pie_yellow);
                holder.mTvCAFno.setText(strCAFno);
                holder.mTvCsmDate.setText(strCsmDate);
                holder.mTvCsmQuery.setText(strCsmType);
                holder.mTvCsmComplaint.setText(strCsmSubType);
                holder.mTvCsmRemark.setText(strCsmRemark);
                holder.mTvCsmResolveBy.setText(strResolveBy);
                holder.mTvCsmResolveDate.setText(strResolveDate);
                holder.mTvCsmSubmitted_By.setText(strSubmitBy);
                holder.mTvCsmResolveRemark.setText(strResolveRemark);
                holder.mTvCsmAddStatus.setText(strStatus);
            }
            return list;
        }
    }

    // updated by vaibhav pote on 16/05/2016
    private boolean isConnectingToInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            Toast toast = Toast.makeText(getActivity(), "Unable to connect", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
            toast.show();
            return false;
        } else
            return true;
    }

    private boolean isNotificationServiceRunning() {
        ActivityManager manager = (ActivityManager) getActivity().getSystemService(getActivity().ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager
                .getRunningServices(Integer.MAX_VALUE)) {
            if ("verve.vaibhavpote.web3.add_verification.Services.BackgroundService"
                    .equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
