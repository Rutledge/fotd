package com.colloquium.fotd;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;

public class FOTD extends Activity {
    TextView tv;
    Button fightButton;
    EditText fighterText1;
    EditText fighterText2;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv = (TextView)findViewById(R.id.displayText);
        fighter1Text = (EditText)findViewById(R.id.fighter1);
        fighter2Text = (EditText)findViewById(R.id.fighter2);
        fightButton = (Button)findViewById(R.id.fightButton);
        fightButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                commenceFight();
            }
        })

    }

    public void commenceFight() {
        String fighter1 = fighterText1.getText().toString();
        String fighter2 = fighterText2.getText().toString();

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://www.google.com");
        try {
            HttpResponse response = client.execute(post);

            InputStream is = response.getEntity().getContent();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayBuffer baf = new ByteArrayBuffer(20);

            //convert byte array to string
            int current = 0;
            while((current = bis.read()) != -1) {
                baf.append((byte)current);
            }
            String text = new String(baf.toByteArray());
            tv.setText(text);


        } catch(Exception e) {
            e.printStackTrace();
            tv.setText(getStackTrace(e));
        }
    }



 // this allows us to turn error traces into text
    // so we can display them on the android
    public  String getStackTrace(Exception t)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        t.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }
}




