package com.android.eric.modernartui;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final String URL = "http://www.moma.org/collection/works/189162?locale=en";
    private static final int colors[][] = new int[][]{
            {0xFFC2185B, 0xFFD32F2F, 0xFFF44336, 0xFFFF4081, 0xFFFFCDD2, 0xFFA32924, 0xFF00CDCD, 0xFF008080, 0xFFE213EC, 0xFFF40CC2},
            {0xFF00CCFF, 0xFF00FFBB, 0xFFFFB8DC, 0xFFF6CDD3, 0xFFFAE4FF, 0xFF243949, 0xFF470019, 0xFFC7DCDF, 0xFF515B6F, 0xFF536878},
            {0xFF195F77, 0xFF243949, 0xFF506983, 0xFF00C1D1, 0xFF470019, 0xFF00CED1, 0xFFFFF9FE, 0xFFFFEDFC, 0xFF99B4FF, 0xFF4C7CFF},
            {0xFF001B66, 0xFF002FB2, 0xFF0044FF, 0xFF00FFBB, 0xFF00CCFF, 0xFF990033, 0xFFC7DCDF, 0xFF195F77, 0xFF75A8AE, 0xFF506983},
            {0xFF243949, 0xFF20B392, 0xFFFFBF00, 0xFFFFA500, 0xFFFFFFFF, 0xFF0000FF, 0xFF800080, 0xFFCDDC39, 0xFF0288D1, 0xFFE64A19}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateSeekBarChange(8);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateSeekBarChange(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void updateSeekBarChange(int progress) {
        findViewById(R.id.button1).setBackgroundColor(colors[0][progress % 10]);
        findViewById(R.id.button2).setBackgroundColor(colors[1][(progress + 1) % 10]);
        findViewById(R.id.button3).setBackgroundColor(colors[2][(progress + 2) % 10]);
        findViewById(R.id.button4).setBackgroundColor(colors[3][(progress + 3) % 10]);
        findViewById(R.id.button5).setBackgroundColor(colors[4][(progress + 4) % 10]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    private void showDialog() {
        final View dialogView = this.getLayoutInflater().inflate(R.layout.dialog, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).setView(dialogView).create();

        TextView explore = (TextView) dialogView.findViewById(R.id.explore);
        explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(URL));
                startActivity(intent);
            }
        });

        TextView notNow = (TextView) dialogView.findViewById(R.id.not_now);
        notNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }
}