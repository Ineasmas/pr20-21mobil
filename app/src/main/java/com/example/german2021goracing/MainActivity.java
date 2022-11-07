package com.example.german2021goracing;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    TextView twresult;
    Button btdrive;
    Button btstart;
    Button btretry;
    ImageView iwplayercar;
    ImageView iwenemycar;
    float playerY=1418;
    float enemyY=1418;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        twresult = (TextView) findViewById(R.id.twresult);
        btdrive = (Button) findViewById(R.id.btdrive);
        btstart = (Button) findViewById(R.id.btstart);
        btretry = (Button) findViewById(R.id.btretry);
        iwplayercar=(ImageView) findViewById(R.id.iwplayercar);
        iwenemycar=(ImageView) findViewById(R.id.iwenemycar);
        btdrive.setOnClickListener(this);
        btstart.setOnClickListener(this);
        btretry.setOnClickListener(this);
        btdrive.setVisibility(View.INVISIBLE);
        btretry.setVisibility(View.INVISIBLE);
        twresult.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btstart:
                btstart.setVisibility(View.INVISIBLE);
                twresult.setVisibility(View.VISIBLE);
                iwplayercar.setY(playerY);
                iwenemycar.setY(enemyY);
                new CountDownTimer(3000,1000){
                    @Override
                    public void onTick(long l)
                    {
                        twresult.setText(Long.toString(1+l/1000));
                    }
                    @Override
                    public void onFinish()
                    {
                        twresult.setText("GO!");
                        btdrive.setVisibility(View.VISIBLE);
                    }
                }.start();

                new CountDownTimer(14000,500){
                    @Override
                    public void onTick(long l)
                    {
                        if (enemyY!=318)
                        {
                            if(l<=11000)
                            {
                                enemyY=enemyY-50;
                                iwenemycar.setY(enemyY);
                            }
                        }
                    }
                    @Override
                    public void onFinish()
                    { if(twresult.getText()!="You've won!")
                    {
                        twresult.setText("You've lost!");
                    }
                        btretry.setVisibility(View.VISIBLE);
                    }
                }.start();
                break;


            case R.id.btdrive:
              playerY=playerY-50;
              iwplayercar.setY(playerY);
              if(playerY==318)
              {
                  if(twresult.getText()!="You've lost!")
                  {
                      twresult.setText("You've won!");
                  }
                btdrive.setVisibility(View.INVISIBLE);
                btretry.setVisibility(View.VISIBLE);
              }
              break;


            case R.id.btretry:
                btretry.setVisibility(View.INVISIBLE);
                btstart.setVisibility(View.VISIBLE);
                playerY=1418;
                enemyY=1418;
                iwplayercar.setY(playerY);
                iwenemycar.setY(enemyY);
                twresult.setText("");
                break;
        }
    }

}
