package pollub.ism.kolko_krzyzyk_;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {


    private Button[][] buttons = new Button[3][3];

    private boolean gracz1Kolej=true;
    private int sprawdzCzyRemis;
    private int gracz1Punkty;
    private int gracz2Punkty;
    private TextView textViewGracz1;
    private TextView textViewGracz2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewGracz1 = findViewById(R.id.text_view_p1);
        textViewGracz2 = findViewById(R.id.text_view_p2);

        for (int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                String buttonID="button_"+i+j;
                int resID=getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j]=findViewById(resID);
                buttons[i][j].setOnClickListener((View.OnClickListener) this);
            }
        }

        Button buttonReset=findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                resetGame();
            }
        });
    }

    private boolean czyWygrana(){
        String[][] field = new String[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                field[i][j]=buttons[i][j].getText().toString();
            }
        }

        for(int i=0;i<3;i++){
            if(field[i][0].equals(field[i][1])
                && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for(int i=0;i<3;i++){
            if(field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if(field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if(field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }
private void gracz1Wygrana(){
        gracz1Punkty++;
        Toast.makeText(this, "Gracz nr 1 wygrywa!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
}
private void gracz2Wygrana(){
    gracz2Punkty++;
    Toast.makeText(this, "Gracz nr 2 wygrywa!", Toast.LENGTH_SHORT).show();
    updatePointsText();
    resetBoard();
}
private void remis(){
        Toast.makeText(this, "Remis!", Toast.LENGTH_SHORT).show();
        resetBoard();
}

private void updatePointsText(){
        textViewGracz1.setText("Player 1: "+ gracz1Punkty);
        textViewGracz2.setText("Player 2: "+ gracz2Punkty);
}

private void resetBoard(){
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                buttons[i][j].setText("");
            }
        }
        sprawdzCzyRemis=0;
        gracz1Kolej=true;
}
private void resetGame(){
        gracz1Punkty=0;
        gracz2Punkty=0;
        updatePointsText();
        resetBoard();
}

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putInt("sprawdzCzyRemis", sprawdzCzyRemis);
        outState.putInt("gracz1Punkty", gracz1Punkty);
        outState.putInt("gracz2Punkty", gracz2Punkty);
        outState.putBoolean("gracz1Kolej", gracz1Kolej);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        sprawdzCzyRemis=savedInstanceState.getInt("sprawdzCzyRemis");
        gracz1Punkty=savedInstanceState.getInt("gracz1Punkty");
        gracz2Punkty=savedInstanceState.getInt("gracz2Punkty");
        gracz1Kolej=savedInstanceState.getBoolean("gracz1Kolej");

    }

}