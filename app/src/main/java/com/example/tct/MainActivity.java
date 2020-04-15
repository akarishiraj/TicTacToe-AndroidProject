package com.example.tct;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
     private Button[][] buttons=new Button[3][3];
     private int roundcount;
     private int player1points;
    private boolean player1turn=true;
    private int player2points;
     private TextView textview_player1,textview_player2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview_player1=findViewById(R.id.text_view_p1);
        textview_player2=findViewById(R.id.text_view_p2);
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                String id="button_"+i+j;
                int resid=getResources().getIdentifier(id,"id",getPackageName());
                buttons[i][j]=findViewById(resid);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button reset=findViewById(R.id.button_reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1points=0;
                player2points=0;
                player1turn=true;
                scoreboard();
                for(int i=0;i<3;i++) {
                    for (int j = 0; j < 3; j++)
                    {
                        buttons[i][j].setText("");
                    }
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        roundcount++;
        if(!((Button)v).getText().toString().equals(""))
        {
            return;
        }
        if(player1turn)
            ((Button) v).setText("X");
        else ((Button) v).setText("O");
        if(checkforwin())
        { if(player1turn)
        {
            player1wins();
        }
        else
        {
            player2wins();
        }

        }
        else if(roundcount==9)
        {
            draw();
            resetBoard();
        }
        else
        {
            player1turn=!player1turn;
        }

    }
      boolean checkforwin()
      {
          String[][] buttonstring=new String[3][3];
          for(int i=0;i<3;i++)
          {
              for(int j=0;j<3;j++)
              {
                  buttonstring[i][j]=buttons[i][j].getText().toString();
              }
          }
          for(int i=0;i<3;i++)
          {
              if(buttonstring[i][0].equals(buttonstring[i][1])
                      && buttonstring[i][0].equals(buttonstring[i][2])
                      && !(buttonstring[i][0].equals("")))
              {
                  return true;
              }
          }
          for(int i=0;i<3;i++)
          {
              if(buttonstring[0][i].equals(buttonstring[1][i])
                      && buttonstring[0][i].equals(buttonstring[2][i])
                      && !(buttonstring[0][i].equals("")))
              {
                  return true;
              }
          }
          if(buttonstring[0][0].equals(buttonstring[1][1])
                  && buttonstring[0][0].equals(buttonstring[2][2])
                  && !(buttonstring[0][0].equals("")))
          {
              return true;
          }
          if(buttonstring[0][2].equals(buttonstring[1][1])
                  && buttonstring[0][2].equals(buttonstring[2][0])
                  && !(buttonstring[0][2].equals("")))
          {
              return true;
          }
          return false;
      }
      public void player1wins()
      {
          Toast.makeText(this, "PLAYER 1 WINS ;)", Toast.LENGTH_SHORT).show();
           player1points++;
           resetBoard();
           scoreboard();
      }
    public void player2wins()
    {
        Toast.makeText(this, "PLAYER 2 WINS ;)", Toast.LENGTH_SHORT).show();
        player2points++;
        resetBoard();
        scoreboard();
    }
    public void draw()
    {
        Toast.makeText(this, "Match Drawn :(", Toast.LENGTH_SHORT).show();
        resetBoard();

    }
    public void resetBoard()
    {
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++)
            {
                buttons[i][j].setText("");
            }
        }
        roundcount=0;
        player1turn=true;
    }
    public void scoreboard()
    {
        textview_player1.setText("PLAYER 1:"+player1points);
        textview_player2.setText("PLAYER 2:"+player2points);
    }
}
