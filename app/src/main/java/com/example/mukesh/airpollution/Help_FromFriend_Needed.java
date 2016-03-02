package com.example.mukesh.airpollution;

/**
 * Created by tarun on 13/2/16.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class Help_FromFriend_Needed extends AppCompatActivity {

        EditText User_Id,User_Name,User_Area,Post_Description;
        Button Upload_Pic,Submit;
        Spinner spinner;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.help_from_friend_needed);
            User_Id=(EditText)findViewById(R.id.User_Id);
            User_Name=(EditText)findViewById(R.id.User_Name);
            User_Area=(EditText)findViewById(R.id.User_Area);
            Post_Description=(EditText)findViewById(R.id.Post_Description);
            Upload_Pic=(Button)findViewById(R.id.Upload_Pic);
            Submit=(Button)findViewById(R.id.Submit);
            spinner=(Spinner)findViewById(R.id.User_Location);

            System.out.println(User_Id);
            System.out.println(User_Name);
            System.out.println(User_Area);
            System.out.println(Post_Description);


            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.Location, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new SpinnerActivity());

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