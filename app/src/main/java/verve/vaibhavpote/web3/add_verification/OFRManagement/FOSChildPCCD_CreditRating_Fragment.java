package verve.vaibhavpote.web3.add_verification.OFRManagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import verve.vaibhavpote.web3.add_verification.Model_class.Constant;
import verve.vaibhavpote.web3.add_verification.R;

/**
 * Created by Vaibhav_Pote on 5/30/2016.
 */
public class FOSChildPCCD_CreditRating_Fragment extends Fragment {
    public FOSChildPCCD_CreditRating_Fragment() {
    }

    Button button;
    SharedPreferences sharedpreferences;
    String strRec_id, strCust_name, strOfrUserId, strAccNo;
    public static final String MyPREFERENCES = "MyPrefs";
    FrameLayout mFmlAVStatus, mFmLNegative, mFmLReject;
    LinearLayout mLlAddConfLay, mLlAVStatus, mLlAVDescription, mLlContPersonLay, mLlConneAppLay, mLlSIMCardLay, mLlOwnerShipLay, mLlOrganizationLay, mLlProfServiceLay,
            mLlYearServiceLay, mLlFmlyStructLay, mLlThirdPartyLay, mLlException, mLlDocShown, mLlHouseApproachLay, mLlHouseLocationLay, mLlLocalityLay, mLlResidTypeLay;
    EditText mEtOtherContPerson, mEtDocShown, mEtDoorColor, mEtLandmark, mEtCRatingRemark;
    TextView mTvAVStatus, mTvAVDescription;
    Spinner spiContPerson, mSpiAVStatus, mSpiNegative, mSpiReject, spiAddConfirm, spiConnApplied, spiSIMReceipt, spiOwnership, spiFmlyStruct,
            spiOrganization, spiProfession, spiYrsService, spiHouseApproach, spiHouseLocation,
            spiHouseLocality, spiResidencyType, spiThirdParty, mSpiException;
    ArrayAdapter adtContPerson, adtNegDiscANF, adtNegDisCPother, adtAddConfirm, adtConnApplied, adtCardReceipt,
            adtOwnership, adtFmlyStruct, adtOrganization, adtProfession, adtYrsService, adtHouseApproach,
            adtHouseLocation, adtHouseLocality, adtResidency, adtThirdParty, adtException;
    String[] strException = {"--select--", "Multiple connection", "High Risk", "Negative Area"};
    String[] strContPerson = {"--select--", "Self", "FBR", "Spouse", "Others", "NA"};
    String[] strNegDiscANF = {"--select--", "NEGATIVE INCOMPLETE ADDRESS", "NEGATIVE ADDRESS UNTRACEABLE", "NEGATIVE ENTRY RESTRICTED"};
    String[] strNegDiscCPOther = {"--select--", "NEGATIVE CUSTOMER NOT MET", "NEGATIVE DOOR LOCK", "NEGATIVE OUT OF STATION"};
    String[] strAddConfirm = {"--select--", "Found", "Not Found", "Area Demolished", "Shifted", "No Such Person Found", "Out of Geo Limit", "Reject Risky"};//if yes AV +ve // demolished & shifted case rejected
    String[] strConnApplied = {"--select--", "Yes", "Different Mobile Number", "Not Applied", "Not Aware", "Cancellation"};// if yes AV +ve // if no case rejected
    String[] strSIMReceipt = {"--select--", "Yes", "No", "NA"};// if yes AV +VE
    String[] strOwnership = {"--select--", "NA", "No Info", "Rented", "Self", "Company Provided", "Govt Provided",
            "Parents", "Bachelor Accommodation / Hostel", "Paying Guest", "Relative"};
    String[] strFmlyStruct = {"--select--", "NA", "NO Information", "Single", "Joint Family"};
    String[] strOrganization = {"--select--", "NA", "Info Not Available", "Housewife", "Retired", "Self Employed",
            "Business Small", "Salaried-BPO/Call Center", "Salaried-Other", "Business Large", "Salaried-MNC/Public Ltd/Govt", "Student"};
    String[] strProfession = {"--select--", "NA", "Info Not Available", "Junior", "Middle", "Professional",
            "Partner/Proprietor", "Senior", "Director/MD"};
    String[] strYrsService = {"--select--", "NA", "Info Not Available", "<1 Year", "01-02 Years", "02-03 Years", "03-04 Years", ">4 Years"};
    String[] strHouseApproach = {"--select--", "NA", "Kuccha Road", "Congested Street",
            "Small Street-2 wheeler Approach", "Tarmac Road-Car Approach"};
    String[] strHouseLocation = {"--select--", "NA", "Not Found", "Difficult to find", "Needed Assistance", "Easy to locate"};
    String[] strHouseLocality = {"--select--", "NA", "Slum", "Lower Middle Class", "Commercial Area",
            "Middle Class", "Upper Middle Class", "Posh"};
    String[] strResidencyType = {"--select--", "NA", "Other", "Part of independent House", "Flat",
            "Independent House", "Bungalow", "Slum", "Temporary Shed", "Negative Area"};
    String[] strThirdParty = {"--select--", "NA", "Neutral", "Positive", "Negative"};
    int contPer = 0, addConfirm = 0, connApplied = 0, cardReceipt = 0, ownership = 0, fmlyStruct = 0, organization = 0, profession = 0,
            yrsService = 0, houseApproach = 0, houseLocation = 0, houseLocality = 0, residencyType = 0, thirdParty = 0;
    double calculateCredit;
    String strContPerPos = "", strTextAVStatus = "", strTextAVDesc = "", strAddConfirmPos = "", strConnAppliedPos = "", strCardReceiptPos = "", strOwnershipPos = "",
            strHouseApproachPos = "", strYrsServicePos = "", strProfessionPos = "", strOrganizationPos = "", strFmlyStructPos = "",
            strHouseLocationPos = "", strHouseLocalityPos = "", strResidencyPos = "", strThirdPartyPos = "", strExceptionPos = "";
    String strCaptureImage, strCaptureImage1, strOtherCont = "", strDoorColor = "", strLandmark = "", strCreditRemark = "", flag_verify;

    public FOSChildPCCD_CreditRating_Fragment(String strRec_id, String strCust_name, String strOfrUserId, String strAccNo, String flag_verify) {
        this.strRec_id = strRec_id;
        this.strCust_name = strCust_name;
        this.strOfrUserId = strOfrUserId;
        this.strAccNo = strAccNo;
        this.flag_verify = flag_verify;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_ofr_child_credit_rating, container, false);

        findViewByIds(rootView);// call for ids
        getSpinnerItemAtPosition();
        button.setOnClickListener(new ButtonEvent());
        ((OFR_ChildToolBarActivity) getActivity()).setCurrentItem(0, true);
        return rootView;
    }

    public void findViewByIds(View rootView) {
        TextView sliding_text_marquee = (TextView) rootView.findViewById(R.id.sliding_text_marquee);
        sliding_text_marquee.setSelected(true);
        button = (Button) rootView.findViewById(R.id.mBtnCreditRating);
        mEtOtherContPerson = (EditText) rootView.findViewById(R.id.mEtOtherContPerson);
        mEtDocShown = (EditText) rootView.findViewById(R.id.mEtDocShown);
        mEtDoorColor = (EditText) rootView.findViewById(R.id.mEtDoorColor);
        mEtLandmark = (EditText) rootView.findViewById(R.id.mEtLandmark);
        mEtCRatingRemark = (EditText) rootView.findViewById(R.id.mEtCRatingRemark);
        mTvAVStatus = (TextView) rootView.findViewById(R.id.mTvAVStatus);
        mTvAVDescription = (TextView) rootView.findViewById(R.id.mTvAVDescription);

        mFmlAVStatus = (FrameLayout) rootView.findViewById(R.id.mFmlAVStatus);
        mFmLNegative = (FrameLayout) rootView.findViewById(R.id.mFmLNegative);
        mFmLReject = (FrameLayout) rootView.findViewById(R.id.mFmLReject);

        mLlAVStatus = (LinearLayout) rootView.findViewById(R.id.mLlAVStatus);
        mLlAVDescription = (LinearLayout) rootView.findViewById(R.id.mLlAVDescription);
        mLlContPersonLay = (LinearLayout) rootView.findViewById(R.id.mLlContPersonLay);
        mLlAddConfLay = (LinearLayout) rootView.findViewById(R.id.mLlAddConfLay);
        mLlConneAppLay = (LinearLayout) rootView.findViewById(R.id.mLlConneAppLay);
        mLlSIMCardLay = (LinearLayout) rootView.findViewById(R.id.mLlSIMCardLay);
        mLlOwnerShipLay = (LinearLayout) rootView.findViewById(R.id.mLlOwnerShipLay);
        mLlOrganizationLay = (LinearLayout) rootView.findViewById(R.id.mLlOrganizationLay);
        mLlProfServiceLay = (LinearLayout) rootView.findViewById(R.id.mLlProfServiceLay);
        mLlYearServiceLay = (LinearLayout) rootView.findViewById(R.id.mLlYearServiceLay);
        mLlFmlyStructLay = (LinearLayout) rootView.findViewById(R.id.mLlFmlyStructLay);
        mLlThirdPartyLay = (LinearLayout) rootView.findViewById(R.id.mLlThirdPartyLay);
        mLlException = (LinearLayout) rootView.findViewById(R.id.mLlException);
        mLlDocShown = (LinearLayout) rootView.findViewById(R.id.mLlDocShown);
        mLlHouseApproachLay = (LinearLayout) rootView.findViewById(R.id.mLlHouseApproachLay);
        mLlHouseLocationLay = (LinearLayout) rootView.findViewById(R.id.mLlHouseLocationLay);
        mLlLocalityLay = (LinearLayout) rootView.findViewById(R.id.mLlLocalityLay);
        mLlResidTypeLay = (LinearLayout) rootView.findViewById(R.id.mLlResidTypeLay);

        spiContPerson = (Spinner) rootView.findViewById(R.id.mSpiContPerson);
        mSpiAVStatus = (Spinner) rootView.findViewById(R.id.mSpiAVStatus);
        mSpiNegative = (Spinner) rootView.findViewById(R.id.mSpiNegative);
        mSpiReject = (Spinner) rootView.findViewById(R.id.mSpiReject);
        spiAddConfirm = (Spinner) rootView.findViewById(R.id.mSpiAddConfirm);
        spiConnApplied = (Spinner) rootView.findViewById(R.id.mSpiConneApplied);
        spiSIMReceipt = (Spinner) rootView.findViewById(R.id.mSpiCardReceipt);
        spiOwnership = (Spinner) rootView.findViewById(R.id.mSpiOwnership);
        spiFmlyStruct = (Spinner) rootView.findViewById(R.id.mSpiFamilyStruct);
        spiOrganization = (Spinner) rootView.findViewById(R.id.mSpiOrganization);
        spiProfession = (Spinner) rootView.findViewById(R.id.mSpiProfession);
        spiYrsService = (Spinner) rootView.findViewById(R.id.mSpiYrsService);
        spiHouseApproach = (Spinner) rootView.findViewById(R.id.mSpiHouseApproach);
        spiHouseLocation = (Spinner) rootView.findViewById(R.id.mSpiHouseLocation);
        spiHouseLocality = (Spinner) rootView.findViewById(R.id.mSpiHouseLocality);
        spiResidencyType = (Spinner) rootView.findViewById(R.id.mSpiResidencyType);
        spiThirdParty = (Spinner) rootView.findViewById(R.id.mSpiThirdParty);
        mSpiException = (Spinner) rootView.findViewById(R.id.mSpiException);

        mLlAVStatus.setVisibility(View.GONE);
        mLlAVDescription.setVisibility(View.GONE);
        mFmlAVStatus.setVisibility(View.GONE);
        mFmLNegative.setVisibility(View.GONE);
        mFmLReject.setVisibility(View.GONE);
        mTvAVStatus.setVisibility(View.GONE);
        mTvAVDescription.setVisibility(View.GONE);

        mLlContPersonLay.setVisibility(View.VISIBLE);
        mLlConneAppLay.setVisibility(View.VISIBLE);
        mLlSIMCardLay.setVisibility(View.GONE);
        mLlOwnerShipLay.setVisibility(View.GONE);
        mLlOrganizationLay.setVisibility(View.GONE);
        mLlProfServiceLay.setVisibility(View.GONE);
        mLlYearServiceLay.setVisibility(View.GONE);
        mLlFmlyStructLay.setVisibility(View.GONE);
        mLlThirdPartyLay.setVisibility(View.GONE);
        mLlException.setVisibility(View.GONE);
        mLlDocShown.setVisibility(View.GONE);
        mLlHouseApproachLay.setVisibility(View.GONE);
        mLlHouseLocationLay.setVisibility(View.GONE);
        mLlLocalityLay.setVisibility(View.GONE);
        mLlResidTypeLay.setVisibility(View.GONE);
        mEtOtherContPerson.setVisibility(View.GONE);
        spiAdapter();
    }

    private class ButtonEvent implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, getActivity().MODE_PRIVATE);
            strCaptureImage = sharedpreferences.getString("captureImage", null);
            strCaptureImage1 = sharedpreferences.getString("captureImage1", null);
            String strDocShown = mEtDocShown.getText().toString();
            strDoorColor = mEtDoorColor.getText().toString();
            strOtherCont = mEtOtherContPerson.getText().toString();
            strLandmark = mEtLandmark.getText().toString();
            strCreditRemark = mEtCRatingRemark.getText().toString();
            String remark = "Visited the given address it was " + strAddConfirmPos + " met " + strContPerPos + " confirmed connection " + strConnAppliedPos + " & Sim card is " + strCardReceiptPos + ", document shown " + strDocShown + ", door colour " + strDoorColor + " landmark " + strLandmark + ", exception remarks if any " + strExceptionPos;
            calculateCredit = (contPer) + (addConfirm) + (connApplied) + (cardReceipt) + (ownership) + (fmlyStruct) + (organization) + (profession) +
                    (yrsService) + (houseApproach) + (houseLocation) + (houseLocality) + (residencyType) + (thirdParty);
            if (strCreditRemark == null || strCreditRemark.equals("")) {
                Toast.makeText(getActivity(), "Comment is mandatory to submit the report", Toast.LENGTH_SHORT).show();
            } else if (strCreditRemark != null || !strCreditRemark.equals("")) {
                Intent intent = new Intent(getActivity(), OFRChild_CreditRating_Validation.class);
                intent.putExtra("Rating", calculateCredit);
                intent.putExtra("AddressConfirm", strAddConfirmPos);
                intent.putExtra("Pre_AV_Status", strTextAVStatus);
                intent.putExtra("Pre_AV_Desc", strTextAVDesc);
                //intent.putExtra("Pre_AV_reject",strTextAVDesc);
                intent.putExtra("PersonMet", strContPerPos);
                intent.putExtra("ConnectionStatus", strConnAppliedPos);
                intent.putExtra("SIMStatus", strCardReceiptPos);
                intent.putExtra("Ownership", strOwnershipPos);
                intent.putExtra("HouseApproach", strHouseApproachPos);
                intent.putExtra("YrsService", strYrsServicePos);
                intent.putExtra("Profession", strProfessionPos);
                intent.putExtra("Organization", strOrganizationPos);
                intent.putExtra("FmlyStruct", strFmlyStructPos);
                intent.putExtra("HouseLocation", strHouseLocationPos);
                intent.putExtra("HouseLocality", strHouseLocalityPos);
                intent.putExtra("Residency", strResidencyPos);
                intent.putExtra("ThirdParty", strThirdPartyPos);
                intent.putExtra("Exception", strExceptionPos);

                intent.putExtra("Rec_id", strRec_id);
                intent.putExtra("Cust_name", strCust_name);
                intent.putExtra("OfrUserId", strOfrUserId);
                intent.putExtra("CAF_number", strAccNo);
                intent.putExtra("creditRemark", strCreditRemark);
                intent.putExtra("strOtherCont", strOtherCont);
                intent.putExtra("DocShown", strDocShown);
                intent.putExtra("DoorColor", strDoorColor);
                intent.putExtra("CaptureImage", strCaptureImage);
                intent.putExtra("CaptureImage1", strCaptureImage1);
                intent.putExtra("flag_verify", flag_verify);
                intent.putExtra("remark", remark);
                startActivity(intent);
            }
        }
    }

    public void spiAVNegANF() {
        //adtReject
        adtNegDiscANF = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, strNegDiscANF);
        adtNegDiscANF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpiNegative.setAdapter(adtNegDiscANF);
    }

    public void spiAdapter() {
        //............for contact person................
        adtContPerson = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, strContPerson);
        adtContPerson.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiContPerson.setAdapter(adtContPerson);
        //------------
        adtAddConfirm = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, strAddConfirm);
        adtAddConfirm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiAddConfirm.setAdapter(adtAddConfirm);

        //-----------ul
        adtConnApplied = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, strConnApplied);
        adtConnApplied.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiConnApplied.setAdapter(adtConnApplied);
        //----------------
        adtCardReceipt = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, strSIMReceipt);
        adtCardReceipt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiSIMReceipt.setAdapter(adtCardReceipt);
        //--------------
        adtOwnership = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, strOwnership);
        adtOwnership.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiOwnership.setAdapter(adtOwnership);
        //--------------
        adtFmlyStruct = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, strFmlyStruct);
        adtFmlyStruct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiFmlyStruct.setAdapter(adtFmlyStruct);
        //---------------
        adtOrganization = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, strOrganization);
        adtOrganization.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiOrganization.setAdapter(adtOrganization);
        //------------------
        adtProfession = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, strProfession);
        adtProfession.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiProfession.setAdapter(adtProfession);
        //-----------------
        adtYrsService = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, strYrsService);
        adtYrsService.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiYrsService.setAdapter(adtYrsService);
        //-------------------
        adtHouseApproach = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, strHouseApproach);
        adtHouseApproach.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiHouseApproach.setAdapter(adtHouseApproach);
        //---------
        adtHouseLocation = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, strHouseLocation);
        adtHouseLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiHouseLocation.setAdapter(adtHouseLocation);
        //------
        adtHouseLocality = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, strHouseLocality);
        adtHouseLocality.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiHouseLocality.setAdapter(adtHouseLocality);
        //--------------
        adtResidency = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, strResidencyType);
        adtResidency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiResidencyType.setAdapter(adtResidency);
        //----------------
        adtThirdParty = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, strThirdParty);
        adtThirdParty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiThirdParty.setAdapter(adtThirdParty);
        //----------------
        adtException = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, strException);
        adtException.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpiException.setAdapter(adtException);

    }

    public void spiAVNegCPOther() {
        //adtReject
        adtNegDisCPother = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, strNegDiscCPOther);
        adtNegDisCPother.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpiNegative.setAdapter(adtNegDisCPother);
    }

    public void getSpinnerItemAtPosition() {

        spiAddConfirm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mLlConneAppLay.setVisibility(View.GONE);
                strAddConfirmPos = parent.getItemAtPosition(position).toString();
                if (strAddConfirmPos.equals(Constant.TAG_FOUND)) {
                    mLlContPersonLay.setVisibility(View.VISIBLE);
                    mLlAVStatus.setVisibility(View.GONE);
                    mLlAVDescription.setVisibility(View.GONE);
                    mLlConneAppLay.setVisibility(View.GONE);
                    mLlSIMCardLay.setVisibility(View.GONE);
                    mLlOwnerShipLay.setVisibility(View.GONE);
                    mLlOrganizationLay.setVisibility(View.GONE);
                    mLlProfServiceLay.setVisibility(View.GONE);
                    mLlYearServiceLay.setVisibility(View.GONE);
                    mLlFmlyStructLay.setVisibility(View.GONE);
                    mLlThirdPartyLay.setVisibility(View.GONE);
                    mLlException.setVisibility(View.GONE);
                    mLlDocShown.setVisibility(View.GONE);
                    mLlHouseApproachLay.setVisibility(View.GONE);
                    mLlHouseLocationLay.setVisibility(View.GONE);
                    mLlLocalityLay.setVisibility(View.GONE);
                    mLlResidTypeLay.setVisibility(View.GONE);
                    addConfirm = 5;
                } else if (strAddConfirmPos.equals(Constant.TAG_NOT_FOUND)) {
                    mLlAVStatus.setVisibility(View.VISIBLE);
                    mTvAVStatus.setVisibility(View.VISIBLE);
                    mTvAVStatus.setText("PRE-AV NEGATIVE");
                    strTextAVStatus = mTvAVStatus.getText().toString();
                    spiAVNegANF();
                    mTvAVDescription.setVisibility(View.GONE);
                    mLlAVDescription.setVisibility(View.VISIBLE);
                    mFmLNegative.setVisibility(View.VISIBLE);

                    mLlContPersonLay.setVisibility(View.GONE);
                    mLlConneAppLay.setVisibility(View.GONE);
                    mLlSIMCardLay.setVisibility(View.GONE);
                    mLlOwnerShipLay.setVisibility(View.GONE);
                    mLlOrganizationLay.setVisibility(View.GONE);
                    mLlProfServiceLay.setVisibility(View.GONE);
                    mLlYearServiceLay.setVisibility(View.GONE);
                    mLlFmlyStructLay.setVisibility(View.GONE);
                    mLlThirdPartyLay.setVisibility(View.GONE);
                    mLlException.setVisibility(View.GONE);
                    mLlDocShown.setVisibility(View.GONE);
                    mLlHouseApproachLay.setVisibility(View.GONE);
                    mLlHouseLocationLay.setVisibility(View.GONE);
                    mLlLocalityLay.setVisibility(View.GONE);
                    mLlResidTypeLay.setVisibility(View.GONE);
                    addConfirm = 0;
                } else if (strAddConfirmPos.equals(Constant.TAG_DEMOLISHED)) {
                    mLlAVStatus.setVisibility(View.VISIBLE);
                    mLlAVDescription.setVisibility(View.VISIBLE);
                    mTvAVStatus.setVisibility(View.VISIBLE);
                    mTvAVStatus.setText("PRE-AV REJECT");
                    strTextAVStatus = mTvAVStatus.getText().toString();
                    mTvAVDescription.setVisibility(View.VISIBLE);
                    mTvAVDescription.setText("REJECTED AREA DEMOLISHED");
                    strTextAVDesc = mTvAVDescription.getText().toString();
                    mFmlAVStatus.setVisibility(View.GONE);
                    mFmLNegative.setVisibility(View.GONE);
                    mFmLReject.setVisibility(View.GONE);

                    mLlContPersonLay.setVisibility(View.GONE);
                    mLlConneAppLay.setVisibility(View.GONE);
                    mLlSIMCardLay.setVisibility(View.GONE);
                    mLlOwnerShipLay.setVisibility(View.GONE);
                    mLlOrganizationLay.setVisibility(View.GONE);
                    mLlProfServiceLay.setVisibility(View.GONE);
                    mLlYearServiceLay.setVisibility(View.GONE);
                    mLlFmlyStructLay.setVisibility(View.GONE);
                    mLlThirdPartyLay.setVisibility(View.GONE);
                    mLlException.setVisibility(View.GONE);
                    mLlDocShown.setVisibility(View.GONE);
                    mLlHouseApproachLay.setVisibility(View.GONE);
                    mLlHouseLocationLay.setVisibility(View.GONE);
                    mLlLocalityLay.setVisibility(View.GONE);
                    mLlResidTypeLay.setVisibility(View.GONE);
                    addConfirm = 0;
                } else if (strAddConfirmPos.equals(Constant.TAG_SHIFTED)) {
                    mLlAVStatus.setVisibility(View.VISIBLE);
                    mLlAVDescription.setVisibility(View.VISIBLE);
                    mTvAVStatus.setVisibility(View.VISIBLE);
                    mTvAVStatus.setText("PRE-AV REJECT");
                    strTextAVStatus = mTvAVStatus.getText().toString();
                    mTvAVDescription.setVisibility(View.VISIBLE);
                    mTvAVDescription.setText("REJECTED SHIFTED");
                    strTextAVDesc = mTvAVDescription.getText().toString();
                    mFmlAVStatus.setVisibility(View.GONE);
                    mFmLNegative.setVisibility(View.GONE);
                    mFmLReject.setVisibility(View.GONE);

                    mLlContPersonLay.setVisibility(View.GONE);
                    mLlSIMCardLay.setVisibility(View.GONE);
                    mLlOwnerShipLay.setVisibility(View.GONE);
                    mLlOrganizationLay.setVisibility(View.GONE);
                    mLlProfServiceLay.setVisibility(View.GONE);
                    mLlYearServiceLay.setVisibility(View.GONE);
                    mLlFmlyStructLay.setVisibility(View.GONE);
                    mLlThirdPartyLay.setVisibility(View.GONE);
                    mLlException.setVisibility(View.GONE);
                    mLlDocShown.setVisibility(View.GONE);
                    mLlHouseApproachLay.setVisibility(View.GONE);
                    mLlHouseLocationLay.setVisibility(View.GONE);
                    mLlLocalityLay.setVisibility(View.GONE);
                    mLlResidTypeLay.setVisibility(View.GONE);
                    addConfirm = 0;
                } else if (strAddConfirmPos.equals(Constant.TAG_NO_SUCH_PERSON)) {
                    mLlAVStatus.setVisibility(View.VISIBLE);
                    mLlAVDescription.setVisibility(View.VISIBLE);
                    mTvAVStatus.setVisibility(View.VISIBLE);
                    mTvAVStatus.setText("PRE-AV REJECT");
                    strTextAVStatus = mTvAVStatus.getText().toString();
                    mTvAVDescription.setVisibility(View.VISIBLE);
                    mTvAVDescription.setText("REJECT NO SUCH PERSON");
                    strTextAVDesc = mTvAVDescription.getText().toString();
                    mFmlAVStatus.setVisibility(View.GONE);
                    mFmLNegative.setVisibility(View.GONE);
                    mFmLReject.setVisibility(View.GONE);

                    mLlContPersonLay.setVisibility(View.GONE);
                    mLlConneAppLay.setVisibility(View.GONE);
                    mLlSIMCardLay.setVisibility(View.GONE);
                    mLlOwnerShipLay.setVisibility(View.GONE);
                    mLlOrganizationLay.setVisibility(View.GONE);
                    mLlProfServiceLay.setVisibility(View.GONE);
                    mLlYearServiceLay.setVisibility(View.GONE);
                    mLlFmlyStructLay.setVisibility(View.GONE);
                    mLlThirdPartyLay.setVisibility(View.GONE);
                    mLlException.setVisibility(View.GONE);
                    mLlDocShown.setVisibility(View.GONE);
                    mLlHouseApproachLay.setVisibility(View.GONE);
                    mLlHouseLocationLay.setVisibility(View.GONE);
                    mLlLocalityLay.setVisibility(View.GONE);
                    mLlResidTypeLay.setVisibility(View.GONE);
                    addConfirm = 0;
                } else if (strAddConfirmPos.equals(Constant.TAG_OUT_OF_GEO_LIMIT)) {
                    mLlAVStatus.setVisibility(View.VISIBLE);
                    mLlAVDescription.setVisibility(View.VISIBLE);
                    mTvAVStatus.setVisibility(View.VISIBLE);
                    mTvAVStatus.setText("PRE-AV REJECT");
                    strTextAVStatus = mTvAVStatus.getText().toString();
                    mTvAVDescription.setVisibility(View.VISIBLE);
                    mTvAVDescription.setText("REJECTED OUT OF GEO LIMIT");
                    strTextAVDesc = mTvAVDescription.getText().toString();
                    mFmlAVStatus.setVisibility(View.GONE);
                    mFmLNegative.setVisibility(View.GONE);
                    mFmLReject.setVisibility(View.GONE);

                    mLlContPersonLay.setVisibility(View.GONE);
                    mLlConneAppLay.setVisibility(View.GONE);
                    mLlSIMCardLay.setVisibility(View.GONE);
                    mLlOwnerShipLay.setVisibility(View.GONE);
                    mLlOrganizationLay.setVisibility(View.GONE);
                    mLlProfServiceLay.setVisibility(View.GONE);
                    mLlYearServiceLay.setVisibility(View.GONE);
                    mLlFmlyStructLay.setVisibility(View.GONE);
                    mLlThirdPartyLay.setVisibility(View.GONE);
                    mLlException.setVisibility(View.GONE);
                    mLlDocShown.setVisibility(View.GONE);
                    mLlHouseApproachLay.setVisibility(View.GONE);
                    mLlHouseLocationLay.setVisibility(View.GONE);
                    mLlLocalityLay.setVisibility(View.GONE);
                    mLlResidTypeLay.setVisibility(View.GONE);
                    addConfirm = 0;//TAG_OUT_OF_GEO_LIMIT
                } else if (strAddConfirmPos.equals(Constant.TAG_REJECT_RISKY)) {
                    mLlAVStatus.setVisibility(View.VISIBLE);
                    mLlAVDescription.setVisibility(View.VISIBLE);
                    mTvAVStatus.setVisibility(View.VISIBLE);
                    mTvAVStatus.setText("PRE-AV REJECT");
                    strTextAVStatus = mTvAVStatus.getText().toString();
                    mTvAVDescription.setVisibility(View.VISIBLE);
                    mTvAVDescription.setText("REJECT RISKY");
                    strTextAVDesc = mTvAVDescription.getText().toString();
                    mFmlAVStatus.setVisibility(View.GONE);
                    mFmLNegative.setVisibility(View.GONE);
                    mFmLReject.setVisibility(View.GONE);

                    mLlContPersonLay.setVisibility(View.GONE);
                    mLlConneAppLay.setVisibility(View.GONE);
                    mLlSIMCardLay.setVisibility(View.GONE);
                    mLlOwnerShipLay.setVisibility(View.GONE);
                    mLlOrganizationLay.setVisibility(View.GONE);
                    mLlProfServiceLay.setVisibility(View.GONE);
                    mLlYearServiceLay.setVisibility(View.GONE);
                    mLlFmlyStructLay.setVisibility(View.GONE);
                    mLlThirdPartyLay.setVisibility(View.GONE);
                    mLlException.setVisibility(View.GONE);
                    mLlDocShown.setVisibility(View.GONE);
                    mLlHouseApproachLay.setVisibility(View.GONE);
                    mLlHouseLocationLay.setVisibility(View.GONE);
                    mLlLocalityLay.setVisibility(View.GONE);
                    mLlResidTypeLay.setVisibility(View.GONE);
                    addConfirm = 0;//TAG_OUT_OF_GEO_LIMIT
                } else {
                    mLlAVStatus.setVisibility(View.GONE);
                    mLlAVDescription.setVisibility(View.GONE);
                    mLlContPersonLay.setVisibility(View.GONE);
                    mLlConneAppLay.setVisibility(View.GONE);
                    mLlSIMCardLay.setVisibility(View.GONE);
                    mLlOwnerShipLay.setVisibility(View.GONE);
                    mLlOrganizationLay.setVisibility(View.GONE);
                    mLlProfServiceLay.setVisibility(View.GONE);
                    mLlYearServiceLay.setVisibility(View.GONE);
                    mLlFmlyStructLay.setVisibility(View.GONE);
                    mLlThirdPartyLay.setVisibility(View.GONE);
                    mLlException.setVisibility(View.GONE);
                    mLlDocShown.setVisibility(View.GONE);
                    mLlHouseApproachLay.setVisibility(View.GONE);
                    mLlHouseLocationLay.setVisibility(View.GONE);
                    mLlLocalityLay.setVisibility(View.GONE);
                    mLlResidTypeLay.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                strAddConfirmPos = "";
            }
        });
        spiContPerson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                long pos = parent.getItemIdAtPosition(position);
                if (pos == 0) {
                    strContPerPos = " ";
                } else if (pos > 0) {
                    strContPerPos = parent.getItemAtPosition(position).toString();
                }

                if (strContPerPos.equals(Constant.TAG_SELF)) {
                    mLlAVStatus.setVisibility(View.GONE);
                    mLlAVDescription.setVisibility(View.GONE);
                    mLlConneAppLay.setVisibility(View.VISIBLE);
                    mLlSIMCardLay.setVisibility(View.GONE);
                    mLlOwnerShipLay.setVisibility(View.GONE);
                    mLlOrganizationLay.setVisibility(View.GONE);
                    mLlProfServiceLay.setVisibility(View.GONE);
                    mLlYearServiceLay.setVisibility(View.GONE);
                    mLlFmlyStructLay.setVisibility(View.GONE);
                    mLlThirdPartyLay.setVisibility(View.GONE);
                    mLlException.setVisibility(View.GONE);
                    mLlDocShown.setVisibility(View.GONE);
                    mLlHouseApproachLay.setVisibility(View.GONE);
                    mLlHouseLocationLay.setVisibility(View.GONE);
                    mLlLocalityLay.setVisibility(View.GONE);
                    mLlResidTypeLay.setVisibility(View.GONE);
                    mEtOtherContPerson.setVisibility(View.GONE);
                    contPer = 5;
                } else if (strContPerPos.equals(Constant.TAG_SPOUSE)) {
                    mLlConneAppLay.setVisibility(View.VISIBLE);
                    mLlSIMCardLay.setVisibility(View.GONE);
                    mLlOwnerShipLay.setVisibility(View.GONE);
                    mLlOrganizationLay.setVisibility(View.GONE);
                    mLlProfServiceLay.setVisibility(View.GONE);
                    mLlYearServiceLay.setVisibility(View.GONE);
                    mLlFmlyStructLay.setVisibility(View.GONE);
                    mLlThirdPartyLay.setVisibility(View.GONE);
                    mLlException.setVisibility(View.GONE);
                    mLlDocShown.setVisibility(View.GONE);
                    mLlHouseApproachLay.setVisibility(View.GONE);
                    mLlHouseLocationLay.setVisibility(View.GONE);
                    mLlLocalityLay.setVisibility(View.GONE);
                    mLlResidTypeLay.setVisibility(View.GONE);
                    mEtOtherContPerson.setVisibility(View.GONE);
                    contPer = 4;
                } else if (strContPerPos.equals(Constant.TAG_First_Blood_Relation)) {
                    mLlConneAppLay.setVisibility(View.VISIBLE);
                    mLlSIMCardLay.setVisibility(View.GONE);
                    mLlOwnerShipLay.setVisibility(View.GONE);
                    mLlOrganizationLay.setVisibility(View.GONE);
                    mLlProfServiceLay.setVisibility(View.GONE);
                    mLlYearServiceLay.setVisibility(View.GONE);
                    mLlFmlyStructLay.setVisibility(View.GONE);
                    mLlThirdPartyLay.setVisibility(View.GONE);
                    mLlException.setVisibility(View.GONE);
                    mLlDocShown.setVisibility(View.GONE);
                    mLlHouseApproachLay.setVisibility(View.GONE);
                    mLlHouseLocationLay.setVisibility(View.GONE);
                    mLlLocalityLay.setVisibility(View.GONE);
                    mLlResidTypeLay.setVisibility(View.GONE);
                    mEtOtherContPerson.setVisibility(View.GONE);
                    contPer = 2;
                } else if (strContPerPos.equals(Constant.TAG_OTHER)) {
                    mLlAVStatus.setVisibility(View.VISIBLE);
                    mLlAVDescription.setVisibility(View.VISIBLE);
                    mTvAVStatus.setVisibility(View.VISIBLE);
                    mTvAVStatus.setText("PRE-AV NEGATIVE");
                    strTextAVStatus = mTvAVStatus.getText().toString();
                    spiAVNegCPOther();
                    mTvAVDescription.setVisibility(View.GONE);
                    mFmlAVStatus.setVisibility(View.GONE);
                    mFmLNegative.setVisibility(View.VISIBLE);
                    mFmLReject.setVisibility(View.GONE);

                    mLlConneAppLay.setVisibility(View.GONE);
                    mLlSIMCardLay.setVisibility(View.GONE);
                    mLlOwnerShipLay.setVisibility(View.GONE);
                    mLlOrganizationLay.setVisibility(View.GONE);
                    mLlProfServiceLay.setVisibility(View.GONE);
                    mLlYearServiceLay.setVisibility(View.GONE);
                    mLlFmlyStructLay.setVisibility(View.GONE);
                    mLlThirdPartyLay.setVisibility(View.GONE);
                    mLlException.setVisibility(View.GONE);
                    mLlDocShown.setVisibility(View.GONE);
                    mLlHouseApproachLay.setVisibility(View.GONE);
                    mLlHouseLocationLay.setVisibility(View.GONE);
                    mLlLocalityLay.setVisibility(View.GONE);
                    mLlResidTypeLay.setVisibility(View.GONE);
                    mEtOtherContPerson.setVisibility(View.VISIBLE);
                    contPer = 0;
                } else if (strContPerPos.equals(Constant.TAG_NA)){
                    mLlConneAppLay.setVisibility(View.GONE);
                    mLlSIMCardLay.setVisibility(View.GONE);
                    mLlOwnerShipLay.setVisibility(View.GONE);
                    mLlOrganizationLay.setVisibility(View.GONE);
                    mLlProfServiceLay.setVisibility(View.GONE);
                    mLlYearServiceLay.setVisibility(View.GONE);
                    mLlFmlyStructLay.setVisibility(View.GONE);
                    mLlThirdPartyLay.setVisibility(View.GONE);
                    mLlException.setVisibility(View.GONE);
                    mLlDocShown.setVisibility(View.GONE);
                    mLlHouseApproachLay.setVisibility(View.GONE);
                    mLlHouseLocationLay.setVisibility(View.GONE);
                    mLlLocalityLay.setVisibility(View.GONE);
                    mLlResidTypeLay.setVisibility(View.GONE);
                    mEtOtherContPerson.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                strContPerPos = "";
            }
        });
        spiConnApplied.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                long pos1 = parent.getItemIdAtPosition(position);
                if (pos1 == 0) {
                    strConnAppliedPos = " ";
                } else if (pos1 > 0) {
                    strConnAppliedPos = parent.getItemAtPosition(position).toString();
                }

                if (strConnAppliedPos.equals(Constant.TAG_YES)) {
                    mLlAVStatus.setVisibility(View.GONE);
                    mLlAVDescription.setVisibility(View.GONE);
                    mLlSIMCardLay.setVisibility(View.VISIBLE);
                    mLlOwnerShipLay.setVisibility(View.VISIBLE);
                    mLlOrganizationLay.setVisibility(View.VISIBLE);
                    mLlProfServiceLay.setVisibility(View.VISIBLE);
                    mLlYearServiceLay.setVisibility(View.VISIBLE);
                    mLlFmlyStructLay.setVisibility(View.VISIBLE);
                    mLlThirdPartyLay.setVisibility(View.VISIBLE);
                    mLlException.setVisibility(View.VISIBLE);
                    mLlDocShown.setVisibility(View.VISIBLE);
                    mLlHouseApproachLay.setVisibility(View.VISIBLE);
                    mLlHouseLocationLay.setVisibility(View.VISIBLE);
                    mLlLocalityLay.setVisibility(View.VISIBLE);
                    mLlResidTypeLay.setVisibility(View.VISIBLE);
                    connApplied = 5;
                } else if (strConnAppliedPos.equals(Constant.TAG_NOT_AWARE)) {
                    mLlAVStatus.setVisibility(View.VISIBLE);
                    mLlAVDescription.setVisibility(View.VISIBLE);
                    mTvAVStatus.setVisibility(View.VISIBLE);
                    mTvAVStatus.setText("PRE-AV REJECT");
                    strTextAVStatus = mTvAVStatus.getText().toString();
                    mTvAVDescription.setVisibility(View.VISIBLE);
                    mTvAVDescription.setText("REJECTED NOT AWARE");
                    strTextAVDesc = mTvAVDescription.getText().toString();
                    mFmlAVStatus.setVisibility(View.GONE);
                    mFmLNegative.setVisibility(View.GONE);
                    mFmLReject.setVisibility(View.GONE);
                    mLlSIMCardLay.setVisibility(View.GONE);
                    mLlOwnerShipLay.setVisibility(View.GONE);
                    mLlOrganizationLay.setVisibility(View.GONE);
                    mLlProfServiceLay.setVisibility(View.GONE);
                    mLlYearServiceLay.setVisibility(View.GONE);
                    mLlFmlyStructLay.setVisibility(View.GONE);
                    mLlThirdPartyLay.setVisibility(View.GONE);
                    mLlException.setVisibility(View.GONE);
                    mLlDocShown.setVisibility(View.GONE);
                    mLlHouseApproachLay.setVisibility(View.GONE);
                    mLlHouseLocationLay.setVisibility(View.GONE);
                    mLlLocalityLay.setVisibility(View.GONE);
                    mLlResidTypeLay.setVisibility(View.GONE);
                    connApplied = -35;
                } else if (strConnAppliedPos.equals(Constant.TAG_NOT_APPLIED)) {
                    mLlAVStatus.setVisibility(View.VISIBLE);
                    mLlAVDescription.setVisibility(View.VISIBLE);
                    mTvAVStatus.setVisibility(View.VISIBLE);
                    mTvAVStatus.setText("PRE-AV REJECT");
                    strTextAVStatus = mTvAVStatus.getText().toString();
                    mTvAVDescription.setVisibility(View.VISIBLE);
                    mTvAVDescription.setText("REJECTED NOT APPLIED");
                    strTextAVDesc = mTvAVDescription.getText().toString();
                    mFmlAVStatus.setVisibility(View.GONE);
                    mFmLNegative.setVisibility(View.GONE);
                    mFmLReject.setVisibility(View.GONE);

                    mLlSIMCardLay.setVisibility(View.GONE);
                    mLlOwnerShipLay.setVisibility(View.GONE);
                    mLlOrganizationLay.setVisibility(View.GONE);
                    mLlProfServiceLay.setVisibility(View.GONE);
                    mLlYearServiceLay.setVisibility(View.GONE);
                    mLlFmlyStructLay.setVisibility(View.GONE);
                    mLlThirdPartyLay.setVisibility(View.GONE);
                    mLlException.setVisibility(View.GONE);
                    mLlDocShown.setVisibility(View.GONE);
                    mLlHouseApproachLay.setVisibility(View.GONE);
                    mLlHouseLocationLay.setVisibility(View.GONE);
                    mLlLocalityLay.setVisibility(View.GONE);
                    mLlResidTypeLay.setVisibility(View.GONE);
                    connApplied = -10;
                } else if (strConnAppliedPos.equals(Constant.TAG_CANCELLATION)) {
                    mLlAVStatus.setVisibility(View.VISIBLE);
                    mLlAVDescription.setVisibility(View.VISIBLE);
                    mTvAVStatus.setVisibility(View.VISIBLE);
                    mTvAVStatus.setText("PRE-AV REJECT");
                    strTextAVStatus = mTvAVStatus.getText().toString();
                    mTvAVDescription.setVisibility(View.VISIBLE);
                    mTvAVDescription.setText("REJECTED CANCELLATION");
                    strTextAVDesc = mTvAVDescription.getText().toString();
                    mFmlAVStatus.setVisibility(View.GONE);
                    mFmLNegative.setVisibility(View.GONE);
                    mFmLReject.setVisibility(View.GONE);

                    mLlSIMCardLay.setVisibility(View.GONE);
                    mLlOwnerShipLay.setVisibility(View.GONE);
                    mLlOrganizationLay.setVisibility(View.GONE);
                    mLlProfServiceLay.setVisibility(View.GONE);
                    mLlYearServiceLay.setVisibility(View.GONE);
                    mLlFmlyStructLay.setVisibility(View.GONE);
                    mLlThirdPartyLay.setVisibility(View.GONE);
                    mLlException.setVisibility(View.GONE);
                    mLlDocShown.setVisibility(View.GONE);
                    mLlHouseApproachLay.setVisibility(View.GONE);
                    mLlHouseLocationLay.setVisibility(View.GONE);
                    mLlLocalityLay.setVisibility(View.GONE);
                    mLlResidTypeLay.setVisibility(View.GONE);
                    connApplied = 0;
                } else if (strConnAppliedPos.equals(Constant.TAG_DIFF_MO_NUMBER)) {
                    mLlAVStatus.setVisibility(View.VISIBLE);
                    mLlAVDescription.setVisibility(View.VISIBLE);
                    mTvAVStatus.setVisibility(View.VISIBLE);
                    mTvAVStatus.setText("PRE-AV REJECT");
                    strTextAVStatus = mTvAVStatus.getText().toString();
                    mTvAVDescription.setVisibility(View.VISIBLE);
                    mTvAVDescription.setText("REJECTED DIFFERENT MOBILE NUMBER");
                    strTextAVDesc = mTvAVDescription.getText().toString();
                    mFmlAVStatus.setVisibility(View.GONE);
                    mFmLNegative.setVisibility(View.GONE);
                    mFmLReject.setVisibility(View.GONE);

                    mLlSIMCardLay.setVisibility(View.GONE);
                    mLlOwnerShipLay.setVisibility(View.GONE);
                    mLlOrganizationLay.setVisibility(View.GONE);
                    mLlProfServiceLay.setVisibility(View.GONE);
                    mLlYearServiceLay.setVisibility(View.GONE);
                    mLlFmlyStructLay.setVisibility(View.GONE);
                    mLlThirdPartyLay.setVisibility(View.GONE);
                    mLlException.setVisibility(View.GONE);
                    mLlDocShown.setVisibility(View.GONE);
                    mLlHouseApproachLay.setVisibility(View.GONE);
                    mLlHouseLocationLay.setVisibility(View.GONE);
                    mLlLocalityLay.setVisibility(View.GONE);
                    mLlResidTypeLay.setVisibility(View.GONE);
                    connApplied = 0;
                } else {
                    mLlSIMCardLay.setVisibility(View.GONE);
                    mLlOwnerShipLay.setVisibility(View.GONE);
                    mLlOrganizationLay.setVisibility(View.GONE);
                    mLlProfServiceLay.setVisibility(View.GONE);
                    mLlYearServiceLay.setVisibility(View.GONE);
                    mLlFmlyStructLay.setVisibility(View.GONE);
                    mLlThirdPartyLay.setVisibility(View.GONE);
                    mLlException.setVisibility(View.GONE);
                    mLlDocShown.setVisibility(View.GONE);
                    mLlHouseApproachLay.setVisibility(View.GONE);
                    mLlHouseLocationLay.setVisibility(View.GONE);
                    mLlLocalityLay.setVisibility(View.GONE);
                    mLlResidTypeLay.setVisibility(View.GONE);
                    connApplied = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                strConnAppliedPos = " ";
            }
        });
        spiSIMReceipt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                long pos2 = parent.getItemIdAtPosition(position);
                if (pos2 == 0) {
                    strCardReceiptPos = " ";
                } else if (pos2 > 0) {
                    strCardReceiptPos = parent.getItemAtPosition(position).toString();
                }
                if (strCardReceiptPos.equals(Constant.TAG_YES)) {
                    cardReceipt = 5;
                } else if (strCardReceiptPos.equals(Constant.TAG_NO)) {
                    cardReceipt = -25;
                } else if (strCardReceiptPos.equals(Constant.TAG_NA)) {
                    cardReceipt = -10;
                } else {
                    cardReceipt = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                strCardReceiptPos = "";
            }
        });
        spiOwnership.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                long pos3 = parent.getItemIdAtPosition(position);
                if (pos3 == 0) {
                    strOwnershipPos = " ";
                } else if (pos3 > 0) {
                    strOwnershipPos = parent.getItemAtPosition(position).toString();
                }
                Log.i("Ownership POSITION", strOwnershipPos);
                if (strOwnershipPos.equals(Constant.TAG_RENTED)) {
                    ownership = 3;
                } else if (strOwnershipPos.equals(Constant.TAG_SELF)) {
                    ownership = 3;
                } else if (strOwnershipPos.equals(Constant.TAG_Company_Provided)) {
                    ownership = 5;
                } else if (strOwnershipPos.equals(Constant.TAG_Govt_Provided)) {
                    ownership = 5;
                } else if (strOwnershipPos.equals(Constant.TAG_PARENTS)) {
                    ownership = 5;
                } else if (strOwnershipPos.equals(Constant.TAG_Bachelor_Accommodation_Hostel)) {
                    ownership = -20;
                } else if (strOwnershipPos.equals(Constant.TAG_Paying_Guest)) {
                    ownership = -20;
                } else if (strOwnershipPos.equals(Constant.TAG_RELATIVE)) {
                    ownership = -20;
                } else {
                    ownership = 0;
                }
                Log.i("VALUE ownership", String.valueOf(ownership));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                strOwnershipPos = " ";
            }
        });
        spiFmlyStruct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                long pos4 = parent.getItemIdAtPosition(position);
                if (pos4 == 0) {
                    strFmlyStructPos = " ";
                } else if (pos4 > 0) {
                    strFmlyStructPos = parent.getItemAtPosition(position).toString();
                }
                Log.i("POSITION FmlyStruct", strFmlyStructPos);
                if (strFmlyStructPos.equals(Constant.TAG_SINGLE)) {
                    fmlyStruct = 3;
                } else if (strFmlyStructPos.equals(Constant.TAG_Joint_Family)) {
                    fmlyStruct = 5;
                } else {
                    fmlyStruct = 0;
                }
                Log.i("VALUE FmlyStruct", String.valueOf(fmlyStruct));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                strFmlyStructPos = "";
            }
        });
        spiOrganization.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                long pos5 = parent.getItemIdAtPosition(position);
                if (pos5 == 0) {
                    strOrganizationPos = " ";
                } else if (pos5 > 0) {
                    strOrganizationPos = parent.getItemAtPosition(position).toString();
                }
                Log.i("POSITION Organization", strOrganizationPos);
                if (strOrganizationPos.equals(Constant.TAG_HOUSEWIFE)) {
                    organization = 4;
                } else if (strOrganizationPos.equals(Constant.TAG_RETIRED)) {
                    organization = 6;
                } else if (strOrganizationPos.equals(Constant.TAG_Self_Employed)) {
                    organization = 6;
                } else if (strOrganizationPos.equals(Constant.TAG_Self_Business_Small)) {
                    organization = 8;
                } else if (strOrganizationPos.equals(Constant.TAG_Salaried_BPO_Call_Center)) {
                    organization = 8;
                } else if (strOrganizationPos.equals(Constant.TAG_Salaried_Other)) {
                    organization = 8;
                } else if (strOrganizationPos.equals(Constant.TAG_Self_Business_Large)) {
                    organization = 10;
                } else if (strOrganizationPos.equals(Constant.TAG_Salaried_MNC_Public_Ltd_Govt)) {
                    organization = 10;
                } else if (strOrganizationPos.equals(Constant.TAG_STUDENT)) {
                    organization = -40;
                } else {
                    organization = 0;
                }
                Log.i("VALUE org", String.valueOf(organization));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                strOrganizationPos = "";
            }
        });
        spiProfession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                long pos6 = parent.getItemIdAtPosition(position);
                if (pos6 == 0) {
                    strProfessionPos = " ";
                } else if (pos6 > 0) {
                    strProfessionPos = parent.getItemAtPosition(position).toString();
                }
                Log.i("POSITION Profession", strProfessionPos);
                if (strProfessionPos.equals(Constant.TAG_JUNIOR)) {
                    profession = 2;
                } else if (strProfessionPos.equals(Constant.TAG_MIDDLE)) {
                    profession = 6;
                } else if (strProfessionPos.equals(Constant.TAG_Professional)) {
                    profession = 6;
                } else if (strProfessionPos.equals(Constant.TAG_Partner_Proprietor)) {
                    profession = 8;
                } else if (strProfessionPos.equals(Constant.TAG_SENIOR)) {
                    profession = 8;
                } else if (strProfessionPos.equals(Constant.TAG_Director_MD)) {
                    profession = 10;
                } else {
                    profession = 0;
                }
                Log.i("VALUE profession", String.valueOf(profession));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                strProfessionPos = " ";
            }
        });
        spiYrsService.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                long pos7 = parent.getItemIdAtPosition(position);
                if (pos7 == 0) {
                    strYrsServicePos = " ";
                } else if (pos7 > 0) {
                    strYrsServicePos = parent.getItemAtPosition(position).toString();
                }
                Log.i("POSITION yrsService", strYrsServicePos);
                if (strYrsServicePos.equals(Constant.TAG_LESS_1_Year)) {
                    yrsService = 2;
                } else if (strYrsServicePos.equals(Constant.TAG_01_02_Years)) {
                    yrsService = 4;
                } else if (strYrsServicePos.equals(Constant.TAG_02_03_Years)) {
                    yrsService = 6;
                } else if (strYrsServicePos.equals(Constant.TAG_03_04_Years)) {
                    yrsService = 8;
                } else if (strYrsServicePos.equals(Constant.TAG_Greater_4_Years)) {
                    yrsService = 10;
                } else {
                    yrsService = 0;
                }
                Log.i("VALUE trsService", String.valueOf(yrsService));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                strYrsServicePos = "";
            }
        });
        spiHouseApproach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                long pos8 = parent.getItemIdAtPosition(position);
                if (pos8 == 0) {
                    strHouseApproachPos = " ";
                } else if (pos8 > 0) {
                    strHouseApproachPos = parent.getItemAtPosition(position).toString();
                }
                Log.i("POSITION Approach", strHouseApproachPos);
                if (strHouseApproachPos.equals(Constant.TAG_Congested_Street)) {
                    houseApproach = 2;
                } else if (strHouseApproachPos.equals(Constant.TAG_SmallStreet_2_wheeler_Approach)) {
                    houseApproach = 3;
                } else if (strHouseApproachPos.equals(Constant.TAG_Tarmac_Road_Car_Approach)) {
                    houseApproach = 5;
                } else {
                    houseApproach = 0;
                }
                Log.i("VALUE Approached", String.valueOf(houseApproach));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                strHouseApproachPos = "";
            }
        });
        spiHouseLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                long pos9 = parent.getItemIdAtPosition(position);
                if (pos9 == 0) {
                    strHouseLocationPos = " ";
                } else if (pos9 > 0) {
                    strHouseLocationPos = parent.getItemAtPosition(position).toString();
                }
                Log.i("POSITION Location", strHouseLocationPos);
                if (strHouseLocationPos.equals(Constant.TAG_Difficult_to_find)) {
                    houseLocation = 2;
                } else if (strHouseLocationPos.equals(Constant.TAG_Needed_Assistance)) {
                    houseLocation = 6;
                } else if (strHouseLocationPos.equals(Constant.TAG_Easy_to_locate)) {
                    houseLocation = 10;
                } else {
                    houseLocation = 0;
                }
                Log.i("VALUE Location", String.valueOf(houseLocation));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                strHouseLocationPos = "";
            }
        });
        spiHouseLocality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                long pos10 = parent.getItemIdAtPosition(position);
                if (pos10 == 0) {
                    strHouseLocalityPos = " ";
                } else if (pos10 > 0) {
                    strHouseLocalityPos = parent.getItemAtPosition(position).toString();
                }
                Log.i("POSITION Locality", strHouseLocalityPos);
                if (strHouseLocalityPos.equals(Constant.TAG_SLUM)) {
                    houseLocality = -20;
                } else if (strHouseLocalityPos.equals(Constant.TAG_Lower_Middle_Class)) {
                    houseLocality = 2;
                } else if (strHouseLocalityPos.equals(Constant.TAG_Commercial_Area)) {
                    houseLocality = 4;
                } else if (strHouseLocalityPos.equals(Constant.TAG_Middle_Class)) {
                    houseLocality = 6;
                } else if (strHouseLocalityPos.equals(Constant.TAG_Upper_Middle_Class)) {
                    houseLocality = 8;
                } else if (strHouseLocalityPos.equals(Constant.TAG_POSH)) {
                    houseLocality = 10;
                } else {
                    houseLocality = 0;
                }
                Log.i("VALUE Locality", String.valueOf(houseLocality));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                strHouseLocalityPos = "";
            }
        });
        spiResidencyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                long pos11 = parent.getItemIdAtPosition(position);
                if (pos11 == 0) {
                    strResidencyPos = " ";
                } else if (pos11 > 0) {
                    strResidencyPos = parent.getItemAtPosition(position).toString();
                }
                Log.i("POSITION Residency", strResidencyPos);
                if (strResidencyPos.equals(Constant.TAG_Part_of_independent_House)) {
                    residencyType = 2;
                } else if (strResidencyPos.equals(Constant.TAG_FLAT)) {
                    residencyType = 3;
                } else if (strResidencyPos.equals(Constant.TAG_Independent_House)) {
                    residencyType = 4;
                } else if (strResidencyPos.equals(Constant.TAG_Bungalow)) {
                    residencyType = 5;
                } else if (strResidencyPos.equals(Constant.TAG_SLUM)) {
                    residencyType = -20;
                } else if (strResidencyPos.equals(Constant.TAG_Temporary_Shed)) {
                    residencyType = -20;
                } else if (strResidencyPos.equals(Constant.TAG_Negative_Area)) {
                    residencyType = -20;
                } else {
                    residencyType = 0;
                }
                Log.i("VALUE Residency", String.valueOf(residencyType));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                strResidencyPos = "";
            }
        });
        spiThirdParty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                long pos12 = parent.getItemIdAtPosition(position);
                if (pos12 == 0) {
                    strThirdPartyPos = " ";
                } else if (pos12 > 0) {
                    strThirdPartyPos = parent.getItemAtPosition(position).toString();
                }
                Log.i("POSITION thirdParty", strThirdPartyPos);
                if (strThirdPartyPos.equals(Constant.TAG_NA)) {
                    thirdParty = 8;
                } else if (strThirdPartyPos.equals(Constant.TAG_POSITIVE)) {
                    thirdParty = 10;
                } else if (strThirdPartyPos.equals(Constant.TAG_NEGATIVE)) {
                    thirdParty = -20;
                } else {
                    thirdParty = 0;
                }
                Log.i("VALUE thirdParty", String.valueOf(thirdParty));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                strThirdPartyPos = "";
            }
        });
        mSpiNegative.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strTextAVDesc = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mSpiException.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                long pos13 = parent.getItemIdAtPosition(position);
                if (pos13 == 0) {
                    strExceptionPos = " ";
                } else if (pos13 > 0) {
                    strExceptionPos = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                strExceptionPos = "";
            }
        });
    }
}
