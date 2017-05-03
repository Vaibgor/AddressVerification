package com.example.web3.add_verification.OFRManagement;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.web3.add_verification.R;

public class CreditRating_Activity extends AppCompatActivity {

    Spinner spiContPerson, spiAddConfirm, spiConnApplied, spiSIMReceipt, spiOwnership, spiFmlyStruct,
            spiOrganization, spiProfession, spiYrsService, spiHouseApproach, spiHouseLocation,
            spiHouseLocality, spiResidencyType, spiThirdParty;
    ArrayAdapter adtContPerson, adtAddConfirm, adtConnApplied, adtCardReceipt, adtOwnership, adtFmlyStruct,
            adtOrganization, adtProfession, adtYrsService, adtHouseApproach, adtHouseLocation, adtHouseLocality, adtResidency, adtThirdParty;
    String[] strContPerson = {"--select--", "Self", "Spouse", "First Blood Relation", "Others"};
    String[] strAddConfirm = {"--select--", "Yes", "No", "Not Found"};
    String[] strConnApplied = {"--select--", "Yes", "No", "NA"};
    String[] strSIMReceipt = {"--select--", "Yes", "No", "NA"};
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
    int contPer, addConfirm, connApplied, cardReceipt, ownership, fmlyStruct, organization, profession,
            yrsService, houseApproach, houseLocation, houseLocality, residencyType, thirdParty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_rating);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void findViewById() {
        spiContPerson = (Spinner) findViewById(R.id.mSpiContPerson);
        spiAddConfirm = (Spinner) findViewById(R.id.mSpiAddConfirm);
        spiConnApplied = (Spinner) findViewById(R.id.mSpiConneApplied);
        spiSIMReceipt = (Spinner) findViewById(R.id.mSpiCardReceipt);
        spiOwnership = (Spinner) findViewById(R.id.mSpiOwnership);
        spiFmlyStruct = (Spinner) findViewById(R.id.mSpiFamilyStruct);
        spiOrganization = (Spinner) findViewById(R.id.mSpiOrganization);
        spiProfession = (Spinner) findViewById(R.id.mSpiProfession);
        spiYrsService = (Spinner) findViewById(R.id.mSpiYrsService);
        spiHouseApproach = (Spinner) findViewById(R.id.mSpiHouseApproach);
        spiHouseLocation = (Spinner) findViewById(R.id.mSpiHouseLocation);
        spiHouseLocality = (Spinner) findViewById(R.id.mSpiHouseLocality);
        spiResidencyType = (Spinner) findViewById(R.id.mSpiResidencyType);
        spiThirdParty = (Spinner) findViewById(R.id.mSpiThirdParty);
        spiAdapter();
    }

    public void spiAdapter() {
        adtContPerson = new ArrayAdapter(this, android.R.layout.simple_spinner_item, strContPerson);
        adtContPerson.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiContPerson.setAdapter(adtContPerson);
        //------------
        adtAddConfirm = new ArrayAdapter(this, android.R.layout.simple_spinner_item, strAddConfirm);
        adtAddConfirm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiAddConfirm.setAdapter(adtAddConfirm);
        //-----------
        adtConnApplied = new ArrayAdapter(this, android.R.layout.simple_spinner_item, strConnApplied);
        adtConnApplied.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiConnApplied.setAdapter(adtConnApplied);
        //----------------
        adtCardReceipt = new ArrayAdapter(this, android.R.layout.simple_spinner_item, strSIMReceipt);
        adtCardReceipt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiSIMReceipt.setAdapter(adtCardReceipt);
        //--------------
        adtOwnership = new ArrayAdapter(this, android.R.layout.simple_spinner_item, strOwnership);
        adtOwnership.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiOwnership.setAdapter(adtOwnership);
        //--------------
        adtFmlyStruct = new ArrayAdapter(this, android.R.layout.simple_spinner_item, strFmlyStruct);
        adtFmlyStruct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiFmlyStruct.setAdapter(adtFmlyStruct);
        //---------------
        adtOrganization = new ArrayAdapter(this, android.R.layout.simple_spinner_item, strOrganization);
        adtOrganization.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiOrganization.setAdapter(adtOrganization);
        //------------------
        adtProfession = new ArrayAdapter(this, android.R.layout.simple_spinner_item, strProfession);
        adtProfession.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiProfession.setAdapter(adtProfession);
        //-----------------
        adtYrsService = new ArrayAdapter(this, android.R.layout.simple_spinner_item, strYrsService);
        adtYrsService.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiYrsService.setAdapter(adtYrsService);
        //-------------------
        adtHouseApproach = new ArrayAdapter(this, android.R.layout.simple_spinner_item, strHouseApproach);
        adtHouseApproach.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiHouseApproach.setAdapter(adtHouseApproach);
        //---------
        adtHouseLocation = new ArrayAdapter(this, android.R.layout.simple_spinner_item, strHouseLocation);
        adtHouseLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiHouseLocation.setAdapter(adtHouseLocation);
        //------
        adtHouseLocality = new ArrayAdapter(this, android.R.layout.simple_spinner_item, strHouseLocality);
        adtHouseLocality.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiHouseLocality.setAdapter(adtHouseLocality);
        //--------------
        adtResidency = new ArrayAdapter(this, android.R.layout.simple_spinner_item, strResidencyType);
        adtResidency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiResidencyType.setAdapter(adtResidency);
        //----------------
        adtThirdParty = new ArrayAdapter(this, android.R.layout.simple_spinner_item, strThirdParty);
        adtThirdParty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiThirdParty.setAdapter(adtThirdParty);
        //getSpinnerItemAtPosition();
    }

    public void getSpinnerItemAtPosition() {
        spiContPerson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strContPerPos = parent.getItemAtPosition(position).toString();
                Log.i("POSITION Cont Person", strContPerPos);
                if (strContPerPos.equals("Self")) {
                    contPer = 5;
                } else if (strContPerPos.equals("Spouse")) {
                    contPer = 4;
                } else if (strContPerPos.equals("First Blood Relation")) {
                    contPer = 2;
                } else {
                    contPer = 0;
                }
                Log.i("VALUE For Contact", String.valueOf(contPer));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spiAddConfirm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strAddConfirmPos = parent.getItemAtPosition(position).toString();
                Log.i("POSITION Address",strAddConfirmPos);
                if (strAddConfirmPos.equals("Yes")) {
                    addConfirm = 5;
                } else {
                    addConfirm = 0;
                }
                Log.i("VALUE for Addr", String.valueOf(addConfirm));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spiConnApplied.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strConnAppliedPos = parent.getItemAtPosition(position).toString();
                Log.i("POSITION Connt Applied",strConnAppliedPos);
                if (strConnAppliedPos.equals("Yes")) {
                    connApplied = 5;
                } else if (strConnAppliedPos.equals("No")) {
                    connApplied = -10;
                } else if (strConnAppliedPos.equals("NA")) {
                    connApplied = -35;
                } else {
                    connApplied = 0;
                }
                Log.i("VALUE for Connection", String.valueOf(connApplied));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spiSIMReceipt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strCardReceiptPos = parent.getItemAtPosition(position).toString();
                Log.i("POSITION SIM CARD", strCardReceiptPos);
                if (strCardReceiptPos.equals("Yes")) {
                    cardReceipt = 5;
                } else if (strCardReceiptPos.equals("No")) {
                    cardReceipt = -25;
                } else if (strCardReceiptPos.equals("NA")) {
                    cardReceipt = -10;
                } else {
                    cardReceipt = 0;
                }
                Log.i("VALUE SIM card", String.valueOf(cardReceipt));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spiOwnership.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strOwnershipPos = parent.getItemAtPosition(position).toString();
                Log.i("Ownership POSITION",strOwnershipPos);
                if (strOwnershipPos.equals("Rented")) {
                    ownership = 3;
                } else if (strOwnershipPos.equals("Self")) {
                    ownership = 3;
                } else if (strOwnershipPos.equals("Company Provided")) {
                    ownership = 5;
                } else if (strOwnershipPos.equals("Govt Provided")) {
                    ownership = 5;
                } else if (strOwnershipPos.equals("Parents")) {
                    ownership = 5;
                } else if (strOwnershipPos.equals("Bachelor Accommodation / Hostel")) {
                    ownership = -20;
                } else if (strOwnershipPos.equals("Paying Guest")) {
                    ownership = -20;
                } else if (strOwnershipPos.equals("Relative")) {
                    ownership = -20;
                } else {
                    ownership = 0;
                }
                Log.i("VALUE ownership", String.valueOf(ownership));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spiFmlyStruct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strFmlyStructPos = parent.getItemAtPosition(position).toString();
                Log.i("POSITION FmlyStruct",strFmlyStructPos);
                if (strFmlyStructPos.equals("Single")) {
                    fmlyStruct = 3;
                } else if (strFmlyStructPos.equals("Joint Family")) {
                    fmlyStruct = 5;
                } else {
                    fmlyStruct = 0;
                }
                Log.i("VALUE FmlyStruct", String.valueOf(fmlyStruct));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spiOrganization.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strOrganizationPos = parent.getItemAtPosition(position).toString();
                Log.i("POSITION Organization",strOrganizationPos);
                if (strOrganizationPos.equals("Housewife")) {
                    organization = 4;
                } else if (strOrganizationPos.equals("Retired")) {
                    organization = 6;
                } else if (strOrganizationPos.equals("Self Employed")) {
                    organization = 6;
                } else if (strOrganizationPos.equals("Business Small")) {
                    organization = 8;
                } else if (strOrganizationPos.equals("Salaried-BPO/Call Center")) {
                    organization = 8;
                } else if (strOrganizationPos.equals("Salaried-Other")) {
                    organization = 8;
                } else if (strOrganizationPos.equals("Business Large")) {
                    organization = 10;
                } else if (strOrganizationPos.equals("Salaried-MNC/Public Ltd/Govt")) {
                    organization = 10;
                } else if (strOrganizationPos.equals("Student")) {
                    organization = -40;
                } else {
                    organization = 0;
                }
                Log.i("VALUE org", String.valueOf(organization));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spiProfession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strProfessionPos = parent.getItemAtPosition(position).toString();
                Log.i("POSITION Profession",strProfessionPos);
                if (strProfessionPos.equals("Junior")) {
                    profession = 2;
                } else if (strProfessionPos.equals("Middle")) {
                    profession = 6;
                } else if (strProfessionPos.equals("Professional")) {
                    profession = 6;
                } else if (strProfessionPos.equals("Partner/Proprietor")) {
                    profession = 8;
                } else if (strProfessionPos.equals("Senior")) {
                    profession = 8;
                } else if (strProfessionPos.equals("Director/MD")) {
                    profession = 10;
                } else {
                    profession = 0;
                }
                Log.i("VALUE profession", String.valueOf(profession));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spiYrsService.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strYrsServicePos = parent.getItemAtPosition(position).toString();
                Log.i("POSITION yrsService",strYrsServicePos);
                if (strYrsServicePos.equals("<1 Year")) {
                    yrsService = 2;
                } else if (strYrsServicePos.equals("01-02 Years")) {
                    yrsService = 4;
                } else if (strYrsServicePos.equals("02-03 Years")) {
                    yrsService = 6;
                } else if (strYrsServicePos.equals("03-04 Years")) {
                    yrsService = 8;
                } else if (strYrsServicePos.equals(">4 Years")) {
                    yrsService = 10;
                } else {
                    yrsService = 0;
                }
                Log.i("VALUE trsService", String.valueOf(yrsService));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spiHouseApproach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strHouseApproachPos = parent.getItemAtPosition(position).toString();
                Log.i("POSITION Approach",strHouseApproachPos);
                if (strHouseApproachPos.equals("Congested Street")) {
                    houseApproach = 2;
                } else if (strHouseApproachPos.equals("Small Street-2 wheeler Approach")) {
                    houseApproach = 3;
                } else if (strHouseApproachPos.equals("Tarmac Road-Car Approach")) {
                    houseApproach = 5;
                } else {
                    houseApproach = 0;
                }
                Log.i("VALUE Approached", String.valueOf(houseApproach));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spiHouseLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strHouseLocationPos = parent.getItemAtPosition(position).toString();
                Log.i("POSITION Location",strHouseLocationPos);
                if (strHouseLocationPos.equals("Difficult to find")) {
                    houseLocation = 2;
                } else if (strHouseLocationPos.equals("Needed Assistance")) {
                    houseLocation = 6;
                } else if (strHouseLocationPos.equals("Easy to locate")) {
                    houseLocation = 10;
                } else {
                    houseLocation = 0;
                }
                Log.i("VALUE Location", String.valueOf(houseLocation));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spiHouseLocality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strHouseLocalityPos = parent.getItemAtPosition(position).toString();
                Log.i("POSITION Locality", strHouseLocalityPos);
                if (strHouseLocalityPos.equals("Slum")) {
                    houseLocality = -20;
                } else if (strHouseLocalityPos.equals("Lower Middle Class")) {
                    houseLocality = 2;
                } else if (strHouseLocalityPos.equals("Commercial Area")) {
                    houseLocality = 4;
                } else if (strHouseLocalityPos.equals("Middle Class")) {
                    houseLocality = 6;
                } else if (strHouseLocalityPos.equals("Upper Middle Class")) {
                    houseLocality = 8;
                } else if (strHouseLocalityPos.equals("Posh")) {
                    houseLocality = 10;
                } else {
                    houseLocality = 0;
                }
                Log.i("VALUE Locality", String.valueOf(houseLocality));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spiResidencyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strResidencyPos = parent.getItemAtPosition(position).toString();
                Log.i("POSITION Residency",strResidencyPos);
                if (strResidencyPos.equals("Part of independent House")) {
                    residencyType = 2;
                } else if (strResidencyPos.equals("Flat")) {
                    residencyType = 3;
                } else if (strResidencyPos.equals("Independent House")) {
                    residencyType = 4;
                } else if (strResidencyPos.equals("Bungalow")) {
                    residencyType = 5;
                } else if (strResidencyPos.equals("Slum")) {
                    residencyType = -20;
                } else if (strResidencyPos.equals("Temporary Shed")) {
                    residencyType = -20;
                } else if (strResidencyPos.equals("Negative Area")) {
                    residencyType = -20;
                } else {
                    residencyType = 0;
                }
                Log.i("VALUE Residency", String.valueOf(residencyType));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spiThirdParty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strThirdPartyPos = parent.getItemAtPosition(position).toString();
                Log.i("POSITION thirdParty",strThirdPartyPos);
                if (strThirdPartyPos.equals("NA")) {
                    thirdParty = 8;
                } else if (strThirdPartyPos.equals("Positive")) {
                    thirdParty = 10;
                } else if (strThirdPartyPos.equals("Negative")) {
                    thirdParty = -20;
                } else {
                    thirdParty = 0;
                }
                Log.i("VALUE thirdParty", String.valueOf(thirdParty));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void submitCreditRating(){

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_creadit_rating, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_submit) {
            getSpinnerItemAtPosition();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
