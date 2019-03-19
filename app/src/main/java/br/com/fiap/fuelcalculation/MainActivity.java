package br.com.fiap.fuelcalculation;

import android.media.Image;
import android.opengl.Visibility;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private NumberFormat currencyFormat = null;

    private SeekBar oilSeedBar;
    private SeekBar ethanolSeedBar;
    private TextView oilTextView;
    private TextView ethanolTextView;
    private TextInputEditText betterTextInput;
    private ImageView betterImage;

    private double oil;
    private double ethanol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currencyFormat = NumberFormat.getCurrencyInstance();

        oilSeedBar = findViewById(R.id.oilSeekBar);
        ethanolSeedBar = findViewById(R.id.ethanolSeekBar);
        oilTextView = findViewById(R.id.oilTextView);
        ethanolTextView = findViewById(R.id.ethanolTextView);
        betterTextInput = findViewById(R.id.betterTextInput);
        betterImage = findViewById(R.id.betterImage);

        oilSeedBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress == 0) {
                    oilTextView.setText("");
                }else {
                    oilTextView.setText(currencyFormat.format(Double.valueOf(progress) / 100));
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(seekBar.getProgress() != 0) {
                    oil = Double.valueOf(seekBar.getProgress()) / 100;
                    //String texto = R.string.gasoline + " " + currencyFormat.format(oil);
                    calculate();
                   // Toast.makeText(MainActivity.this, texto, Toast.LENGTH_SHORT).show();

                }else {
                    oil = 0;
                    calculate();
                }

            }
        });
        ethanolSeedBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if(progress == 0) {
                            ethanolTextView.setText("");
                        }else {
                            ethanolTextView.setText(currencyFormat.format(Double.valueOf(progress) / 100));

                        }

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        if(seekBar.getProgress() != 0) {
                            ethanol = Double.valueOf(seekBar.getProgress()) / 100;
                          //  String texto = R.string.ethanol + " "+ currencyFormat.format(ethanol);
                            calculate();
                          // Toast.makeText(MainActivity.this, texto, Toast.LENGTH_SHORT).show();
                        }else {
                            ethanol = 0;
                            calculate();
                        }
                    }
                });

    }

    private void calculate() {

        if(oil != 0 && ethanol != 0)  {
            betterImage.setVisibility(View.VISIBLE);
            if((ethanol / oil) >= 0.7) {
                betterTextInput.setText(R.string.gasoline);
                betterImage.setImageResource(R.drawable.oil);
            }else {
                betterTextInput.setText(R.string.ethanol);
                betterImage.setImageResource(R.drawable.ethanol);
            }
        }else {
                betterImage.setVisibility(View.INVISIBLE);
        }

    }



}
