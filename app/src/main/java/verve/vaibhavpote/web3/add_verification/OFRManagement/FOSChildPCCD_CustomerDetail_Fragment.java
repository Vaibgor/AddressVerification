package verve.vaibhavpote.web3.add_verification.OFRManagement;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import verve.vaibhavpote.web3.add_verification.DatabaseManagement.DatabaseClass;
import verve.vaibhavpote.web3.add_verification.Model_class.JSONParser;
import verve.vaibhavpote.web3.add_verification.Model_class.OFR_Allocation_model;
import verve.vaibhavpote.web3.add_verification.R;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Vaibhav_Pote on 16/6/16.
 */
public class FOSChildPCCD_CustomerDetail_Fragment extends Fragment {
    public FOSChildPCCD_CustomerDetail_Fragment() {
    }
    LinearLayout mLlPreHist;
    TextView mTVCustOE_EPOS, mTvCustImage, mTvCustStatus, mTvCustCAFNo, mTvCustDELno, mTvCustProduct, mTvType,
            mTvSubType, mTvCustTotalCount, mTvCustName, mTvCustCompName, mTvAddHouseFlat, mTvStreetAdd,
            mTvAddLocalArea, mTvCustPINCode, mTvAddLandMark, mTvAltNo1, mTvAltNo2, mTvZone, mTvArea, mTvCustPreNote,
            mTvSupeName, mTvHistSubDate, mTvHistFName, mTvHistSubRemark;
    ImageView mIvSegmentImage;
    SharedPreferences sharedpreferences;
    DatabaseClass databaseClass;
    JSONArray jsonArray = null;
    JSONParser jsonParser = new JSONParser();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_VALUES = "value";
    private static final String TAG_MESSAGE = "message";
    public static final String MyPREFERENCES = "MyPrefs";
    String strRec_id, strCust_name, strUserID, strPINCode, strAccNo;

    public FOSChildPCCD_CustomerDetail_Fragment(String strRec_id, String strCust_name, String strUserID, String strAccNo) {
        this.strRec_id = strRec_id;
        this.strCust_name = strCust_name;
        this.strUserID = strUserID;
        this.strAccNo = strAccNo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ofrchild_customerdetail_fragment, container, false);
        sharedpreferences = getActivity().getApplicationContext().getSharedPreferences(MyPREFERENCES, getActivity().MODE_PRIVATE);
        findViewByIDs(rootView);// call for ids
        getCustomerDetails();

        return rootView;
    }//

    public void findViewByIDs(View rootView) {
        //...bind ReVerification History...
        mLlPreHist = (LinearLayout) rootView.findViewById(R.id.mLlPreHist);
        mTvSupeName = (TextView) rootView.findViewById(R.id.mTvSupeName);
        mTvHistSubDate = (TextView) rootView.findViewById(R.id.mTvHistSubDate);
        mTvHistFName = (TextView) rootView.findViewById(R.id.mTvHistFName);
        mTvHistSubRemark = (TextView) rootView.findViewById(R.id.mTvHistSubRemark);
        //................................................
        mTvCustCAFNo = (TextView) rootView.findViewById(R.id.mTvCustCAFNo);
        mTvCustDELno = (TextView) rootView.findViewById(R.id.mTvCustDELno);
        mTvCustProduct = (TextView) rootView.findViewById(R.id.mTvCustProduct);
        mTvType = (TextView) rootView.findViewById(R.id.mTvType);
        mTvSubType = (TextView) rootView.findViewById(R.id.mTvSubType);
        mTvCustTotalCount = (TextView) rootView.findViewById(R.id.mTvCustTotalCount);
        mTvCustName = (TextView) rootView.findViewById(R.id.mTvCustName);
        mTvCustCompName = (TextView) rootView.findViewById(R.id.mTvCustCompName);
        mTvAddHouseFlat = (TextView) rootView.findViewById(R.id.mTvAddHouseFlat);
        mTvStreetAdd = (TextView) rootView.findViewById(R.id.mTvStreetAdd);
        mTvAddLocalArea = (TextView) rootView.findViewById(R.id.mTvAddLocalArea);
        mTvCustPINCode = (TextView) rootView.findViewById(R.id.mTvCustPINCode);
        mTvAddLandMark = (TextView) rootView.findViewById(R.id.mTvAddLandMark);
        mTvAltNo1 = (TextView) rootView.findViewById(R.id.mTvAltNo1);
        mTvAltNo2 = (TextView) rootView.findViewById(R.id.mTvAltNo2);
        mTvZone = (TextView) rootView.findViewById(R.id.mTvZone);
        mTvArea = (TextView) rootView.findViewById(R.id.mTvArea);

        mTVCustOE_EPOS = (TextView) rootView.findViewById(R.id.mTVCustOE_EPOS);
        mTvCustPreNote = (TextView) rootView.findViewById(R.id.mTvCustPreNote);
        mTvCustImage = (TextView) rootView.findViewById(R.id.mTvCustImage);
        mTvCustStatus = (TextView) rootView.findViewById(R.id.mTvCustStatus);
        mIvSegmentImage = (ImageView) rootView.findViewById(R.id.mIvSegmentImage);
        mTvAltNo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String strNumber = mTvAltNo1.getText().toString();
                    Intent dial = new Intent(Intent.ACTION_DIAL);
                    dial.setData(Uri.parse("tel:" + strNumber));
                    dial.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(dial);
                }catch (ActivityNotFoundException e){
                    e.printStackTrace();
                }
            }
        });
        mTvAltNo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String strNumber1 = mTvAltNo1.getText().toString();
                    Intent dial = new Intent(Intent.ACTION_DIAL);
                    dial.setData(Uri.parse("tel:" + strNumber1));
                    dial.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(dial);
                }catch (ActivityNotFoundException e){
                    e.printStackTrace();
                }
            }
        });
    }

    //.............for customer details....
    private void getCustomerDetails() {
        // TODO Auto-generated method stub
        final ProgressDialog progDailog = ProgressDialog.show(getActivity(), "Getting details", "Please wait...", true);

        new Thread() {
            public void run() {
                try {
                    new AsyncGetCustomerDetails().execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progDailog.dismiss();
            }
        }.start();
    }
    //......................................
    public class AsyncGetCustomerDetails extends AsyncTask<String, String, String> {
        String message,response, aternate_no_one, pin_code, del_no, cust_name,date1, delaler_code, product, type, Subtype, no_of_connections, company_name, add_line1, add_line2,
                add_line3, landmark, alternate_no2, ao_status, ao_remarks, oe_epos_status, cms_number, oe_epos_remarks,
                oe_epos_date, account_num, upload_datetime, area, zone;
        int success;
        @Override
        protected String doInBackground(String... params) {
            databaseClass= new DatabaseClass(getActivity());
            ArrayList<OFR_Allocation_model> arrayModelList = new ArrayList<OFR_Allocation_model>();
            try {
                arrayModelList= databaseClass.getCustomerDetails(strRec_id);
                response= String.valueOf(arrayModelList.size());
                for (int i=0;i<arrayModelList.size();i++) {
                    OFR_Allocation_model model = arrayModelList.get(i);
                    del_no = model.getDel_no();
                    product = model.getProduct();
                    type = model.getType();
                    Subtype = model.getSubtype();
                    no_of_connections = model.getStrNo_of_connection();
                    cust_name = model.getCust_name();
                    company_name = model.getStrComp_name();
                    add_line1 = model.getAdd_line1();
                    add_line2 = model.getAdd_line2();
                    add_line3 = model.getAdd_line3();
                    pin_code = model.getPin_code();
                    landmark = model.getStrLandmark();
                    aternate_no_one = model.getAternate_no_one();
                    alternate_no2 = model.getStrAternate_no_two();
                    oe_epos_date = model.getStrOE_epos_done_date();
                    zone = model.getZone();
                    area = model.getArea();
                }
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            setTextValue(strAccNo, del_no, product, type, Subtype, no_of_connections, strCust_name, company_name, add_line1, add_line2,
                    add_line3, pin_code, landmark, aternate_no_one, alternate_no2, zone, area, oe_epos_date);
            if (result != null) {
                setTextValue(strAccNo, del_no, product, type, Subtype, no_of_connections, strCust_name, company_name, add_line1, add_line2,
                        add_line3, pin_code, landmark, aternate_no_one, alternate_no2, zone, area, oe_epos_date);
            }else {
                Toast toast = Toast.makeText(getActivity(), "Connection failed", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
    }
    public void setTextValue(String strCAFNo, String strDELno, String strProduct, String strType, String strSubtype,
                             String strCount, String strCustName, String strCompName, String strHouseNo,
                             String strStreetAdd, String strLocality, String strpinCode, String strLandmark,
                             String strAltNo1, String strAltNo2, String strZone, String strArea, String strOE_epos) {
        mTvCustCAFNo.setText(strCAFNo);
        mTvCustDELno.setText(strDELno);
        mTvCustProduct.setText(strProduct);
        mTvType.setText(strType);
        mTvSubType.setText(strSubtype);
        mTvCustTotalCount.setText(strCount);
        mTvCustName.setText(strCustName);
        mTvCustCompName.setText(strCompName);
        mTvAddHouseFlat.setText(strHouseNo);
        mTvStreetAdd.setText(strStreetAdd);
        mTvAddLocalArea.setText(strLocality);
        mTvCustPINCode.setText(strpinCode);
        mTvAddLandMark.setText(strLandmark);
        mTvAltNo1.setText(strAltNo1);
        mTvAltNo2.setText(strAltNo2);
        mTvZone.setText(strZone);
        mTvArea.setText(strArea);
        mTVCustOE_EPOS.setText(strOE_epos);
    }

}