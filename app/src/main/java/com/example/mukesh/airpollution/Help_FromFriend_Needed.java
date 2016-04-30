package com.example.mukesh.airpollution;

/**
 * Created by tarun on 13/2/16.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class Help_FromFriend_Needed extends AppCompatActivity implements View.OnClickListener{

        EditText User_Id,User_Name,User_Area,Post_Description;
        Button Upload_Pic,Submit;
        Spinner spinner;
        Bitmap bitmap;
        String Image_String;
    String post_person_id;
    String person_name;
    String person_location;


        UserLocalStore userLocalStore;
    public static final String SP_NAME = "userDetails";
        private int PICK_IMAGE_REQUEST = 1;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.help_from_friend_needed);
            SharedPreferences userLocalDataBase= this.getSharedPreferences(SP_NAME, 0);
            post_person_id = userLocalDataBase.getString("post_person_id", "");

             person_name = userLocalDataBase.getString("person_name", "");
            person_location = userLocalDataBase.getString("person_location","");

           // User user=userLocalStore.getLoggedInUser();
            System.out.println("ooohh"+post_person_id+person_name+person_location);

            User_Id=(EditText)findViewById(R.id.User_Id);
            User_Name=(EditText)findViewById(R.id.User_Name);
            User_Area=(EditText)findViewById(R.id.User_Area);
            Post_Description=(EditText)findViewById(R.id.Post_Description);

            Upload_Pic=(Button)findViewById(R.id.Upload_Pic);

            Submit=(Button)findViewById(R.id.Submit);

            Submit.setOnClickListener(this);
            Upload_Pic.setOnClickListener(this);

            spinner=(Spinner)findViewById(R.id.User_Location);




            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.Location, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new SpinnerActivity());

        }

        @Override
        public void onClick(View v) {
            switch(v.getId())
            {
                case R.id.Submit:

                    try {
                        send_data();
                        Intent intent=new Intent(this,Submit_application.class);
                        startActivity(intent);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    break;

                case R.id.Upload_Pic:
                    showFileChooser();
                    break;

            }
        }
        private void showFileChooser() {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

        }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

                Uri uri = data.getData();

                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    // Log.d(TAG, String.valueOf(bitmap));
                    ByteArrayOutputStream bao = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 95, bao);
                    byte[] ba = bao.toByteArray();
                    Image_String = Base64.encodeToString(ba,
                            Base64.NO_WRAP);

                    ImageView imageView = (ImageView) findViewById(R.id.imageView);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



        public  void send_data() throws IOException{
            String user_id=User_Id.getText().toString();
            String user_name=User_Name.getText().toString();
            String user_area=User_Area.getText().toString();
            String post_description=Post_Description.getText().toString();


            ArrayList<NameValuePair> dataToSend=new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("post_person_id", post_person_id));
            dataToSend.add(new BasicNameValuePair("post_heading", user_name));
            dataToSend.add(new BasicNameValuePair("post_area", user_area));
            dataToSend.add(new BasicNameValuePair("post_location","INDIA"));
            dataToSend.add(new BasicNameValuePair("post_details", post_description));
            dataToSend.add(new BasicNameValuePair("Image_String",Image_String));
            Server_communication ser=new Server_communication();
            System.out.println("ja rha hu");
            System.out.println(dataToSend.size());
            ser.execute(dataToSend.toArray(new NameValuePair[dataToSend.size()]));

        }

}
class SpinnerActivity extends Activity implements OnItemSelectedListener {


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Object item = parent.getItemAtPosition(pos);
        System.out.println(item.toString());
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


}