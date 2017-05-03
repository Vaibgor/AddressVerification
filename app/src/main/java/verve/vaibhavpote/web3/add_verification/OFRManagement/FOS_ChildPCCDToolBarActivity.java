package verve.vaibhavpote.web3.add_verification.OFRManagement;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;

import verve.vaibhavpote.web3.add_verification.R;
import verve.vaibhavpote.web3.add_verification.Util_model.SignaturePad;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vaibhav_Pote on 5/30/2016.
 */
public class FOS_ChildPCCDToolBarActivity extends AppCompatActivity {
    private Toolbar toolbar_OFR;
    private TabLayout tabLayout_OFR;
    private ViewPager viewPager_OFR;
    AlertDialog dialog;
    SharedPreferences sharedpreferences;
    String picturePath, capturePath, strRec_id, strCust_name, strOfrUserId, strAccNo, strBase64, compString, compString1, flag_verify;
    public static final String MyPREFERENCES = "MyPrefs";
    private int[] tabIcons = {R.drawable.ic_g_translate_white_24dp, R.drawable.ic_list_white_24dp,
            R.drawable.ic_monochrome_photos_white_24dp};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofr_child_tool_bar);

        try {
            Bundle bundle = getIntent().getExtras();
            strRec_id = bundle.getString("rec_id");
            strCust_name = bundle.getString("cust_name");
            strOfrUserId = bundle.getString("userID");
            strAccNo = bundle.getString("CAF_no");
            flag_verify = bundle.getString("flag");
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        sharedpreferences = this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        toolbar_OFR = (Toolbar) findViewById(R.id.toolbar_childOFR);
        setSupportActionBar(toolbar_OFR);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager_OFR = (ViewPager) findViewById(R.id.viewpager_childOFR);
        setupOFRViewPager(viewPager_OFR);

        tabLayout_OFR = (TabLayout) findViewById(R.id.tabs_childOFR);
        tabLayout_OFR.setupWithViewPager(viewPager_OFR);
        setupOFRTabIcons();

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_ofrchild);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage1();
            }
        });
      /*  SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        // repeat many times:
        final ImageView itemIcon1 = new ImageView(this);
        itemIcon1.setImageResource(R.drawable.ic_add_a_photo_black_24dp);

        ImageView itemIcon2 = new ImageView(this);
        itemIcon2.setImageResource(R.drawable.ic_border_color_black_24dp);

        ImageView itemIcon3 = new ImageView(this);
        itemIcon3.setImageResource(R.drawable.ic_touch_app_black_24dp);

        SubActionButton button1 = itemBuilder.setContentView(itemIcon1).build();
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();
        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).build();

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .attachTo(floatingActionButton)
                .build();
        //attach the sub buttons to the main button
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawSignature();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage1();
            }
        });*/
    }

    private void setupOFRTabIcons() {
        tabLayout_OFR.getTabAt(0).setIcon(tabIcons[1]);
        tabLayout_OFR.getTabAt(1).setIcon(tabIcons[0]);
        //  tabLayout_OFR.getTabAt(2).setIcon(tabIcons[2]);
    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        viewPager_OFR.setCurrentItem(item, smoothScroll);
    }

    //...............Here we can add FragmentPagerAdapter for main Activity
    //...............FragmentPagerAdapter will bind Fragment Tab with (Fragment and String)
    private void setupOFRViewPager(ViewPager viewPager) {
        CustomOFR_ChildPagerAdapter adapter = new CustomOFR_ChildPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new OFRChild_CustomerDetail_Fragment(strRec_id, strCust_name, strOfrUserId, strAccNo), "Customer details");
        adapter.addFrag(new OFRChild_CreditRating_Fragment(strRec_id, strCust_name, strOfrUserId, strAccNo, flag_verify), "Credit Rating");
        //adapter.addFrag(new OFRChild_CreditRating_ServiceFragment(strRec_id,strCust_name,strOfrUserId,strAccNo), "Credit Rating");
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new RotateUpTransformer());
    }

    //...................Custom FragmentPagerAdapter
    class CustomOFR_ChildPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public CustomOFR_ChildPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    //.-------------------------------------
    //....>Capture camera Image
    //--------------------------------------
    private void selectImage() {

        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Add Photo!");
        builder.setIcon(R.drawable.ic_camera_icon);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void selectImage1() {

        final CharSequence[] options = {"Take Photo", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Add Photo!");
        builder.setIcon(R.drawable.ic_camera_icon);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

                    // viewImage.setImageBitmap(bitmap);
                    String path = Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "DCIM" + File.separator + "Camera";

                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    Log.w("picture path 1::", String.valueOf(file));
                    picturePath = String.valueOf(file);
                    insertDialogData(bitmap, picturePath);
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.w("path", path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {
                try {
                    // When an Image is picked
                    if (requestCode == 2 && resultCode == RESULT_OK && null != data) {
                        // Get the Image from data
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        // Get the cursor
                        Cursor cursor = this.getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        picturePath = cursor.getString(columnIndex);
                        cursor.close();
                        // Set the Image in ImageView after decoding the String
                        Bitmap bitThumbnail = BitmapFactory.decodeFile(picturePath);
                        Log.w("Bitmap thumbnail", String.valueOf(bitThumbnail));
                        insertDialogData(bitThumbnail, picturePath);
                    } else {
                        Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        }
    }

    private void upload(String strPath) {
        try {
            Log.e("path", "----------------" + strPath);
            Bitmap bm = BitmapFactory.decodeFile(strPath);
            Bitmap bMapScaled = Bitmap.createScaledBitmap(bm, 300, 350, true);
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            bMapScaled.compress(Bitmap.CompressFormat.JPEG, 90, bao);
            byte[] ba = bao.toByteArray();
            strBase64 = Base64.encodeToString(ba, Base64.NO_WRAP);
            compString = "data:image/gif;base64," + strBase64;
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    //upload second image
    private void upload2(String strPath1) {
        try {
            Log.e("path", "----------------" + strPath1);
            Bitmap bm = BitmapFactory.decodeFile(strPath1);
            Bitmap bMapScaled = Bitmap.createScaledBitmap(bm, 300, 350, true);
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            bMapScaled.compress(Bitmap.CompressFormat.JPEG, 90, bao);
            byte[] ba = bao.toByteArray();
            strBase64 = Base64.encodeToString(ba, Base64.NO_WRAP);
            compString1 = "data:image/gif;base64," + strBase64;
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    public void insertDialogData(Bitmap image, String pictureDir) {
        // public void insertDialogData() {
        AlertDialog.Builder builder;
        final String getPicturePath = pictureDir;
        Context mContext = FOS_ChildPCCDToolBarActivity.this;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.custom_alert_capture_image,
                (ViewGroup) findViewById(R.id.mLlImageDialog));
        ImageView image1 = null;
        image1 = (ImageView) layout.findViewById(R.id.mIvDialogImage1);
        ImageView image2 = (ImageView) layout.findViewById(R.id.mIvDialogImage2);
        final Button btnCancel = (Button) layout.findViewById(R.id.mBtnCancelDialog);
        final Button btnSubmit = (Button) layout.findViewById(R.id.mBtnSubmitDialog);
        final TextView mTvImagePath1 = (TextView) layout.findViewById(R.id.mTvImage1);
        final TextView mTvImagePath2 = (TextView) layout.findViewById(R.id.mTvImage2);

        mTvImagePath1.setText(pictureDir);
        image1.setImageBitmap(image);
        builder = new AlertDialog.Builder(mContext);
        builder.setView(layout);
        dialog = builder.create();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        dialog.show();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload(getPicturePath);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("captureImage", compString);
                editor.apply();
             /*   if (strBase64 == null) {

                } else if (strBase64 != null) {
                    upload2(getPicturePath);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("captureImage1", compString1);
                    editor.apply();
                }*/
                dialog.dismiss();
                Toast toast = Toast.makeText(FOS_ChildPCCDToolBarActivity.this, "Save image successfully", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }

    public void drawSignature() {
        // public void insertDialogData() {
        AlertDialog.Builder builder;
        final Context mContext = FOS_ChildPCCDToolBarActivity.this;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.custom_alert_drawsign,
                (ViewGroup) findViewById(R.id.signature_pad_container));
        ImageView image1 = null;
        image1 = (ImageView) layout.findViewById(R.id.mIvDialogImage1);
        ImageView image2 = (ImageView) layout.findViewById(R.id.mIvDialogImage2);
        final Button mClearButton = (Button) layout.findViewById(R.id.clear_button);
        final Button mSaveButton = (Button) layout.findViewById(R.id.save_button);
        final SignaturePad mSignaturePad = (SignaturePad) layout.findViewById(R.id.signature_pad);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                Toast.makeText(mContext, "OnStartSigning", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSigned() {
                mSaveButton.setEnabled(true);
                mClearButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                mSaveButton.setEnabled(false);
                mClearButton.setEnabled(false);
            }
        });

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignaturePad.clear();
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
                if (addJpgSignatureToGallery(signatureBitmap)) {
                    Toast.makeText(mContext, "Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "Unable to store the signature", Toast.LENGTH_SHORT).show();
                }
                if (addSvgSignatureToGallery(mSignaturePad.getSignatureSvg())) {
                    Toast.makeText(mContext, "SVG Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "Unable to store the SVG signature", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder = new AlertDialog.Builder(mContext);
        builder.setView(layout);
        dialog = builder.create();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        dialog.show();
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignaturePad.clear();
                dialog.dismiss();
            }
        });
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // upload();
                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
                if (addJpgSignatureToGallery(signatureBitmap)) {
                    Toast.makeText(mContext, "Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "Unable to store the signature", Toast.LENGTH_SHORT).show();
                }
                if (addSvgSignatureToGallery(mSignaturePad.getSignatureSvg())) {
                    Toast.makeText(mContext, "SVG Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "Unable to store the SVG signature", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
    }

    //.........Save signature bitmap
    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created");
        }
        return file;
    }

    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
    }

    public boolean addJpgSignatureToGallery(Bitmap signature) {
        boolean result = false;
        try {
            File photo = new File(getAlbumStorageDir("CustomerSign"), String.format("Signature_%d.jpg", System.currentTimeMillis()));
            saveBitmapToJPG(signature, photo);
            scanMediaFile(photo);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void scanMediaFile(File photo) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photo);
        mediaScanIntent.setData(contentUri);
        FOS_ChildPCCDToolBarActivity.this.sendBroadcast(mediaScanIntent);
    }

    public boolean addSvgSignatureToGallery(String signatureSvg) {
        boolean result = false;
        try {
            File svgFile = new File(getAlbumStorageDir("CustomerSign"), String.format("Signature_%d.svg", System.currentTimeMillis()));
            OutputStream stream = new FileOutputStream(svgFile);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            writer.write(signatureSvg);
            writer.close();
            stream.flush();
            stream.close();
            scanMediaFile(svgFile);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

   /* @Override
    protected void onPause() {
        super.onPause();
        finish();
    }*/
}