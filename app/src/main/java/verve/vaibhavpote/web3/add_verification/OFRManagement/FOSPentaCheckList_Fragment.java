package verve.vaibhavpote.web3.add_verification.OFRManagement;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import verve.vaibhavpote.web3.add_verification.DatabaseManagement.DatabaseClass;
import verve.vaibhavpote.web3.add_verification.Model_class.OFR_Allocation_model;
import verve.vaibhavpote.web3.add_verification.R;

import java.util.ArrayList;

/**
 * Created by Vaibhav_Pote on 5/30/2016.
 */
public class FOSPentaCheckList_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {
    public FOSPentaCheckList_Fragment() {
    }

    ListView mLvAllocatedList;
    private SwipeRefreshLayout swipeContainer;
    TextView totalCount;
    CustomAllocationList allocationList;
    DatabaseClass databaseClass;
    String strOFRUserID;
    public static final String MyPREFERENCES = "MyPrefs";
    private BroadcastReceiver mReceiver;
    private IntentFilter mIntentFilter;

    public FOSPentaCheckList_Fragment(String strOFRUserID) {
        this.strOFRUserID = strOFRUserID;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.allocation_list_fragment, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        totalCount = (TextView) rootView.findViewById(R.id.mTvRowCount);
        mLvAllocatedList = (ListView) rootView.findViewById(R.id.mLvAllocationList);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    getFOSAllocationList();
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }
            }
        });
        swipeContainer.post(new Runnable() {
                                @Override
                                public void run() {
                                    swipeContainer.setRefreshing(true);
                                    getFOSAllocationList();
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    //.....call background method for bind related ids data to on detail view

    private void getFOSAllocationList() {
        // TODO Auto-generated method stub
        new Thread() {
            public void run() {
                try {
                    new getAllocatedListData().execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private class getAllocatedListData extends AsyncTask<String, String, ArrayList<OFR_Allocation_model>> {
        @Override
        protected ArrayList<OFR_Allocation_model> doInBackground(String... param) {

            databaseClass = new DatabaseClass(getActivity());
            ArrayList<OFR_Allocation_model> arrayAllocation = new ArrayList<OFR_Allocation_model>();
            try {
                arrayAllocation = databaseClass.getAllocationOpenListData();
                Log.w("Allocation Data From Db", String.valueOf(arrayAllocation));
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
            return arrayAllocation;
        }

        @Override
        protected void onPostExecute(ArrayList<OFR_Allocation_model> arrayAllocation) {
            super.onPostExecute(arrayAllocation);
            try {
                swipeContainer.setRefreshing(false);
                if (arrayAllocation.size() > 0) {
                    String count = String.valueOf(arrayAllocation.size());
                    allocationList = new CustomAllocationList(getActivity(), R.layout.custom_ofr_allocatedlist, arrayAllocation);
                    mLvAllocatedList.setAdapter(allocationList);
                    totalCount.setText(count);
                } else {
                    Toast toast = Toast.makeText(getActivity(), "Try Again", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.show();
                    Log.w("Fos Allocation", "Connection failed");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    //...........custom list adapter for allocation data
    public class CustomAllocationList extends ArrayAdapter<OFR_Allocation_model> {
        private LayoutInflater inflater = null;
        Context context;
        int layoutResourceId;
        // String agency_id;
        ArrayList<OFR_Allocation_model> data = new ArrayList<OFR_Allocation_model>();

        public CustomAllocationList(Context context, int layoutResourceId, ArrayList<OFR_Allocation_model> data) {
            super(context, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.context = context;
            this.data = data;
        }

        public class Holder {
            ImageView mIvProcessIcon;
            TextView mTvCAFno, mTvDelNo, mTvCustName, mTvPIN, mTvAddress, mTvOFRAllocDate;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View list = convertView;
            Holder holder = null;
            if (list == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                list = inflater.inflate(layoutResourceId, parent, false);
                holder = new Holder();
                //holder.image = (ImageView) list.findViewById(R.id.imageView4);
                holder.mIvProcessIcon = (ImageView) list.findViewById(R.id.mIvProcessIcon);
                holder.mTvCAFno = (TextView) list.findViewById(R.id.mTvOFRCAFNumber);
                holder.mTvPIN = (TextView) list.findViewById(R.id.mTvOFRCustPin);
                holder.mTvCustName = (TextView) list.findViewById(R.id.mTvOFRCustName);
                holder.mTvDelNo = (TextView) list.findViewById(R.id.mTvOFRDelNumber);
                holder.mTvAddress = (TextView) list.findViewById(R.id.mTvOFRAddress);
                holder.mTvOFRAllocDate = (TextView) list.findViewById(R.id.mTvOFRAllocDate);
                list.setTag(holder);
            } else {
                holder = (Holder) list.getTag();
            }
            OFR_Allocation_model member = data.get(position);
            final String strRec_id = member.getRec_id();
            final String strCAFno = member.getCaf_no();
            final String strCust_name = member.getCust_name();
            final String strUserID = member.getStrUserId();

            holder.mTvCAFno.setText(member.getCaf_no());
            holder.mTvPIN.setText(member.getPin_code());
            holder.mTvCustName.setText(member.getCust_name());
            holder.mTvDelNo.setText(member.getDel_no());
            holder.mTvAddress.setText(member.getStrLandmark());
            holder.mTvOFRAllocDate.setText(member.getUpload_datetime());
            holder.mIvProcessIcon.setImageResource(R.drawable.ic_circle_red_icon);
            // onClick listRow get affected and data pass to the next activity
            list.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent op = new Intent(context, OFR_ChildToolBarActivity.class);
                    op.putExtra("rec_id", strRec_id);
                    op.putExtra("userID", strUserID);
                    op.putExtra("cust_name", strCust_name);
                    op.putExtra("CAF_no", strCAFno);
                    op.putExtra("flag", "0");
                    op.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(op);
                }
            });
            return list;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //........Broadcast Receiver call to get data from service with intent filter for specific data
        mIntentFilter = new IntentFilter("action.allocation.openfile");
        mReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                //extract our message from intent
                ArrayList<OFR_Allocation_model> model_array = (ArrayList<OFR_Allocation_model>) intent.getSerializableExtra("openList");
                String count = String.valueOf(model_array.size());
                allocationList = new CustomAllocationList(getActivity(), R.layout.custom_ofr_allocatedlist, model_array);
                mLvAllocatedList.setAdapter(allocationList);
                totalCount.setText(count);
                swipeContainer.setRefreshing(false);
            }
        };
        getActivity().registerReceiver(mReceiver, mIntentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(mReceiver);
    }
}
