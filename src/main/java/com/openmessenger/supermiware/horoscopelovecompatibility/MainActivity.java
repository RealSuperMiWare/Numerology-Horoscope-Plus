package com.openmessenger.supermiware.horoscopelovecompatibility;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private CalendarView zodiacDatePickerView;
    private TextView signLabel;
    private TextView yourSignIsLabel;
    private Animation zodiacAnswerAnimation;
    private TextView bestSignsLabel;
    private Intent toButtonIntent;

    // Inittialzie

    // Zodiac Signs

    // January 20 - February 18
    private String Aquarius;
    private String aquariusCompatibilityString;

    // February 19 - March 20
    private String Pisces;
    private String piscesCompatibilityString;

    // March 21 - April 20
    private String Aries;
    private String ariesCompatibilityString;

    // April 21 - May 21
    private String Taurus;
    private String taurusCompatibilityString;

    // May 22 - June 21
    private String Gemini;
    private String geminiCompatibilityString;

    // June 22 - July 22
    private String Cancer;
    private String cancerCompatibilityString;

    // July 23 - August 23
    private String Leo;
    private String leoCompatibilityString;

    // August 24 - September 23
    private String Virgo;
    private String virgoCompatibilityString;

    // September 24 - October 23
    private String Libra;
    private String libraCompatibilityString;

    // October 24 - November 22
    private String Scorpio;
    private String scorpioCompatibilityString;

    // November 23 - December 21
    private String Sagittarius;
    private String sagittariusCompatibilityString;

    // December 22 - January 20
    private String Capricorn;
    private String capricornCompatibilityString;

    AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mAdView = findViewById(R.id.adView);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(MainActivity.this, "ca-app-pub-9656248427916586~9936868228");

        AdRequest adRequest = new AdRequest.Builder().addTestDevice("ca-app-pub-9656248427916586~9936868228").build();
        mAdView.loadAd(adRequest);


        // Alert informs the user that the year doesn't need to be searched through.
        // Just select the Month and Day of the user birth to find the zodiac sign.
        //
        //
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Use the calendar widget to select your birthdate. \nDon't worry " +
                "about the year, just select the Month and the Date of your birth.")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

        yourSignIsLabel = findViewById(R.id.yourSignIsLabel);
        zodiacDatePickerView = findViewById(R.id.zodiacDatePickerView);
        bestSignsLabel = findViewById(R.id.bestSignsLabel);
        signLabel = findViewById(R.id.signLabel);


        // Strings hold all values for the zodiac names
        //
        Aquarius = "Aquarius";
        Pisces = "Pisces";
        Aries = "Aries";
        Taurus = "Taurus";
        Gemini = "Gemini";
        Cancer = "Cancer";
        Leo = "Leo";
        Virgo = "Virgo";
        Libra = "Libra";
        Scorpio = "Scorpio";
        Sagittarius = "Sagittarius";
        Capricorn = "Capricorn";
        //

        // Strings holds all the values for zodiac best matches.
        //
        aquariusCompatibilityString = "Best Matches are \n" + Aries + " and " + Libra;
        piscesCompatibilityString = "Best Matches are \n" + Taurus + ", " + Cancer + ", \n" + Scorpio + " and " + Capricorn;
        ariesCompatibilityString = "Best Matches are \n" + Cancer + ", " + Aquarius + " and " + Pisces;
        taurusCompatibilityString = "Best Matches are \n" + Cancer + ", " + Libra + " and " + Pisces;
        geminiCompatibilityString = "Best Matches are \n" + Aries + ", " +  Aquarius + ", " + Leo + " and " + Libra;
        cancerCompatibilityString = "Best Matches are \n" + Taurus + ", " + Virgo + ", \n" + Scorpio + " and " + Pisces;
        leoCompatibilityString = "Best Matches are \n" + Aries + ", " + Libra + ", " + Gemini + " and " + Sagittarius;
        virgoCompatibilityString = "Best Matches are \n" + Taurus + ", " + Cancer + ", " + Scorpio + " and " + Capricorn;
        libraCompatibilityString = "Best Matches are \n" + Gemini + ", " + Leo + ", \n" + Sagittarius + " and " + Aquarius;
        scorpioCompatibilityString = "Best Matches are \n" + Cancer + ", " + Virgo + ", " + Capricorn + " and " + Pisces;
        sagittariusCompatibilityString = "Best Matches are \n" + Aries + ", " + Leo + ", " + Libra + " and " + Aquarius;
        capricornCompatibilityString = "Best Matches are \n" + Taurus + ", " + Virgo + ", " + Scorpio + " and " + Pisces;
        //
        //

        // _________________________________________________________________________________________


        // DATE PICKER METHOD | IF STATEMENT/CONDITIONS
        //
        // Once the user has selected there birth date
        // the app will display the users zodiac sign and will display
        // the best matches for that sign as well.
        //
        //


        zodiacDatePickerView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {


                // TRY CATCH CONVERSION
                //
                // The below try-catch is responsible for converting all of the strings to int
                // and then back into strings once they are passed into the if/else condition
                // statement.
                //
                //
                try {

                    String signDate = (month + 1) + "/" + dayOfMonth + "/" + year;
                    yourSignIsLabel.setVisibility(View.VISIBLE);
                    signLabel.setText(signDate);


                    // ZODIAC DESCRIPTION CONVERSION
                    //
                    // Each zodiac section below takes the dates for the zodiac sign in question and
                    // converts them from strings into int.
                    // They begin as strings so they can be compared to the value which prints the date
                    // which is a string via the signLabel TextView.
                    //
                    //


                    //
                    // Aquarius START
                    String aquariusStart = "1/21/" + year;
                    String aquariusEnd = "2/20/" + year;
                    String aquariusScopeStart = "2/1/" + year;
                    String aquariusScopeEnd = "2/9/" + year;
                    int aquariusStartInt = Integer.parseInt(aquariusStart.replace("/", ""));
                    int aquariusEndInt = Integer.parseInt(aquariusEnd.replace("/", ""));
                    int signDateAquariusInt = Integer.parseInt(signDate.replace("/", ""));
                    int aquariusScopeStartInt = Integer.parseInt(aquariusScopeStart.replace("/", ""));
                    int aquariusScopeEndInt = Integer.parseInt(aquariusScopeEnd.replace("/", ""));
                    // Aquarius END
                    //
                    //

                    //
                    // Pisces START
                    String piscesStart = "2/21/" + year;
                    String piscesEnd = "3/20/" + year;
                    String piscesScopeStart = "3/1/" + year;
                    String piscesScopeEnd = "3/9" + year;
                    int piscesStartInt = Integer.parseInt(piscesStart.replace("/", ""));
                    int piscesEndInt = Integer.parseInt(piscesEnd.replace("/", ""));
                    int signDatePiscesInt = Integer.parseInt(signDate.replace("/", ""));
                    int piscesScopeStartInt = Integer.parseInt(piscesScopeStart.replace("/", ""));
                    int piscesScopeEndInt = Integer.parseInt(piscesScopeEnd.replace("/", ""));
                    // Pisces END
                    //
                    //

                    //
                    // Aries START
                    String ariesStart = "3/21/" + year;
                    String ariesEnd = "4/20/" + year;
                    String ariesScopeStart = "4/1/" + year;
                    String ariesScopeEnd = "4/9/" + year;
                    int ariesStartInt = Integer.parseInt(ariesStart.replace("/", ""));
                    int ariesEndInt = Integer.parseInt(ariesEnd.replace("/", ""));
                    int signDateAriesInt = Integer.parseInt(signDate.replace("/", ""));
                    int ariesScopeStartInt = Integer.parseInt(ariesScopeStart.replace("/", ""));
                    int ariesScopeEndInt = Integer.parseInt(ariesScopeEnd.replace("/", ""));
                    // Aries END
                    //
                    //

                    //
                    // Taurus START
                    String TaurusStart = "4/21/" + year;
                    String TaurusEnd = "5/21/" + year;
                    String taurusScopeStart = "5/1/" + year;
                    String taurusScopeEnd = "5/9/" + year;
                    int taurusStartInt = Integer.parseInt(TaurusStart.replace("/", ""));
                    int taurusEndInt = Integer.parseInt(TaurusEnd.replace("/", ""));
                    int signDateTaurusInt = Integer.parseInt(signDate.replace("/", ""));
                    int taurusScopeStartInt = Integer.parseInt(taurusScopeStart.replace("/", ""));
                    int taurusScopeEndInt = Integer.parseInt(taurusScopeEnd.replace("/", ""));
                    // Taurus END
                    //
                    //

                    //
                    // Gemini START
                    String geminiStart = "5/22/" + year;
                    String geminiEnd = "6/21/" + year;
                    String geminiScopeStart = "6/1/" + year;
                    String geminiScopeEnd = "6/9/" + year;
                    int geminiStartInt = Integer.parseInt(geminiStart.replace("/", ""));
                    int geminiEndInt = Integer.parseInt(geminiEnd.replace("/", ""));
                    int signDateGeminiInt = Integer.parseInt(signDate.replace("/", ""));
                    int geminiScopeStartInt = Integer.parseInt(geminiScopeStart.replace("/", ""));
                    int geminiScopeEndInt = Integer.parseInt(geminiScopeEnd.replace("/", ""));
                    // Gemini END
                    //
                    //

                    //
                    // Cancer START
                    String cancerStart = "6/22/" + year;
                    String cancerEnd = "7/22" + year;
                    String cancerScopeStart = "7/1/" + year;
                    String cancerScopeEnd = "7/9" + year;
                    int cancerStartInt = Integer.parseInt(cancerStart.replace("/", ""));
                    int cancerEndInt = Integer.parseInt(cancerEnd.replace("/", ""));
                    int signDateCancerInt = Integer.parseInt(signDate.replace("/", ""));
                    int cancerScopeStartInt = Integer.parseInt(cancerScopeStart.replace("/", ""));
                    int cancerScopeEntInt = Integer.parseInt(cancerScopeEnd.replace("/", ""));
                    // Cancer END
                    //
                    //

                    //
                    // LEO START
                    String leoStart = "7/23/" + year;
                    String leoEnd = "8/23/" + year;
                    String leoScopeStart = "8/1/" + year;
                    String leoScopeEnd = "8/9/" + year;
                    int leoStartInt = Integer.parseInt(leoStart.replace("/", ""));
                    int leoEndInt = Integer.parseInt(leoEnd.replace("/", ""));
                    int leoScopeStartInt = Integer.parseInt(leoScopeStart.replace("/", ""));
                    int leoScopeEndInt = Integer.parseInt(leoScopeEnd.replace("/", ""));
                    int signDateLeoInt = Integer.parseInt(signDate.replace("/", ""));
                    // LEO END
                    //
                    //

                    //
                    // Virgo START
                    String virgoStart = "8/24/" + year;
                    String virgoEnd = "9/23/" + year;
                    String virgoScopeStart = "9/1" + year;
                    String virgoScopeEnd = "9/9" + year;
                    int virgoStartInt = Integer.parseInt(virgoStart.replace("/", ""));
                    int virgoEndInt = Integer.parseInt(virgoEnd.replace("/", ""));
                    int signDateVirgoInt = Integer.parseInt(signDate.replace("/", ""));
                    int virgoScopeStartInt = Integer.parseInt(virgoScopeStart.replace("/", ""));
                    int virgoScopeEndInt = Integer.parseInt(virgoScopeEnd.replace("/", ""));
                    // Virgo END
                    //
                    //

                    //
                    // Libra START
                    String libraStart = "9/24/" + year;
                    String libraEnd = "10/23/" + year;
                    String libraScopeStart = "10/1" + year;
                    String libraScopeEnd = "10/9" + year;
                    int libraStartInt = Integer.parseInt(libraStart.replace("/", ""));
                    int libraEndInt = Integer.parseInt(libraEnd.replace("/", ""));
                    int signDateLibraInt = Integer.parseInt(signDate.replace("/", ""));
                    int libraScopeStartInt = Integer.parseInt(libraScopeStart.replace("/", ""));
                    int libraScopeEndInt = Integer.parseInt(libraScopeEnd.replace("/", ""));
                    // Libra END
                    //
                    //

                    //
                    // Scorpio START
                    String scorpioStart = "10/24/" + year;
                    String scorpioEnd = "11/22/" + year;
                    String scorpioScopeStart = "11/1" + year;
                    String scorpioScopeEnd = "11/9/" + year;
                    int scorpioStartInt = Integer.parseInt(scorpioStart.replace("/", ""));
                    int scorpioEndInt = Integer.parseInt(scorpioEnd.replace("/", ""));
                    int signDateScorpioInt = Integer.parseInt(signDate.replace("/", ""));
                    int scorpioScopeStartInt = Integer.parseInt(scorpioScopeStart.replace("/", ""));
                    int scorpioScopeEndInt = Integer.parseInt(scorpioScopeEnd.replace("/", ""));
                    // Scorpio END
                    //
                    //

                    //
                    // Sagittarius START
                    String sagittariusStart = "11/23/" + year;
                    String sagittariusEnd = "12/21/" + year;
                    String sagittariusScopeStart = "12/1/" + year;
                    String sagittariusScopeEnd = "12/9" + year;
                    int sagittariusStartInt = Integer.parseInt(sagittariusStart.replace("/", ""));
                    int sagittariusEndInt = Integer.parseInt(sagittariusEnd.replace("/", ""));
                    int signDateSagittariusInt = Integer.parseInt(signDate.replace("/", ""));
                    int sagittariusScopeStartInt = Integer.parseInt(sagittariusScopeStart.replace("/", ""));
                    int sagittariusScopeEndInt = Integer.parseInt(sagittariusScopeEnd.replace("/", ""));
                    // Sagittarius END
                    //
                    //

                    //
                    // Capricorn START
                    String capricornStart = "12/22/" + year;
                    String CapricornEnd = "1/20/" + year;
                    String capricornScopeStart = "1/1/" + year;
                    String capricornScopeEnd = "1/9" + year;
                    int capricornStartInt = Integer.parseInt(capricornStart.replace("/", ""));
                    int capricornEndInt = Integer.parseInt(CapricornEnd.replace("/", ""));
                    int signDateCapricornInt = Integer.parseInt(signDate.replace("/", ""));
                    int capricornScopeStartInt = Integer.parseInt(capricornScopeStart.replace("/", ""));
                    int capricornScopeEndInt = Integer.parseInt(capricornScopeEnd.replace("/", ""));
                    // Capricorn END
                    //
                    //

                    // _____________________________________________________________________________

                    // ZODIAC SIGN IF CONDITIONS
                    //
                    // If statement figures out weather or not the date selected in the app
                    // corresponds with a particular zodiac sign.
                    // Once a particular zodiac sign is picked (via the user picking there birth date)
                    // the app will display the zodiac sign and will display the best matches for the
                    // users zodiac sign.
                    if((signDateLeoInt >= leoStartInt) & (signDateLeoInt <= leoEndInt)
                            || (signDateLeoInt >= leoScopeStartInt) & (signDateLeoInt <= leoScopeEndInt)) {

                        Log.d("LeoTestLog", "You've returned Leo");
                        Log.d("testLeo", leoStart + " " + leoStartInt);
                        Log.d("testLeo2", leoEnd + " " + leoEndInt);
                        Log.d("testSign", signDate + " " + signDateLeoInt);

                        zodiacAnswerAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.zodiacansweranimation);
                        yourSignIsLabel.startAnimation(zodiacAnswerAnimation);
                        yourSignIsLabel.setText(Leo);
                        bestSignsLabel.setVisibility(View.VISIBLE);
                        bestSignsLabel.setText(leoCompatibilityString);


                    }else if ((signDateAriesInt >= ariesStartInt) & (signDateAriesInt <= ariesEndInt)
                            || (signDateAriesInt >= ariesScopeStartInt) & (signDateAriesInt <= ariesScopeEndInt)){

                        Log.d("AriesTestLog", "You've returned Aries");

                        zodiacAnswerAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.zodiacansweranimation);
                        yourSignIsLabel.startAnimation(zodiacAnswerAnimation);
                        bestSignsLabel.setVisibility(View.VISIBLE);
                        bestSignsLabel.setText(ariesCompatibilityString);
                        yourSignIsLabel.setText(Aries);
                        bestSignsLabel.setText(ariesCompatibilityString);


                    }else if ((signDateCapricornInt >= capricornStartInt) & (signDateCapricornInt <= capricornEndInt)
                            || (signDateCancerInt >= capricornScopeStartInt) & (signDateCapricornInt <= capricornScopeEndInt)){

                        Log.d("CapricornTestLog", "You've returned Capricorn");
                        zodiacAnswerAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.zodiacansweranimation);
                        yourSignIsLabel.startAnimation(zodiacAnswerAnimation);
                        bestSignsLabel.setVisibility(View.VISIBLE);
                        yourSignIsLabel.setText(Capricorn);
                        bestSignsLabel.setText(capricornCompatibilityString);


                    }else if ((signDateVirgoInt >= virgoStartInt) & (signDateVirgoInt <= virgoEndInt)
                            || (signDateVirgoInt >= virgoScopeStartInt) & (signDateVirgoInt <= virgoScopeEndInt)){

                        Log.d("VirgoTestLog", "You've returned Virgo");
                        zodiacAnswerAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.zodiacansweranimation);
                        yourSignIsLabel.startAnimation(zodiacAnswerAnimation);
                        bestSignsLabel.setVisibility(View.VISIBLE);
                        yourSignIsLabel.setText(Virgo);
                        bestSignsLabel.setText(virgoCompatibilityString);


                    }else if ((signDateAquariusInt >= aquariusStartInt) & (signDateAquariusInt <= aquariusEndInt)
                            || (signDateAquariusInt >= aquariusScopeStartInt) & (signDateAquariusInt <= aquariusScopeEndInt)){

                        Log.d("AquariusTestLog", "You've returned Aquarius");
                        zodiacAnswerAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.zodiacansweranimation);
                        yourSignIsLabel.startAnimation(zodiacAnswerAnimation);
                        bestSignsLabel.setVisibility(View.VISIBLE);
                        yourSignIsLabel.setText(Aquarius);
                        bestSignsLabel.setText(aquariusCompatibilityString);


                    }else if ((signDateGeminiInt >= geminiStartInt) & (signDateGeminiInt <= geminiEndInt)
                            || (signDateGeminiInt >= geminiScopeStartInt) & (signDateGeminiInt <= geminiScopeEndInt)){

                        Log.d("GeminiTestLog", "You've returned Gemini");
                        Log.d("testGem", geminiStart);
                        Log.d("test", geminiEnd);
                        zodiacAnswerAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.zodiacansweranimation);
                        yourSignIsLabel.startAnimation(zodiacAnswerAnimation);
                        bestSignsLabel.setVisibility(View.VISIBLE);
                        yourSignIsLabel.setText(Gemini);
                        bestSignsLabel.setText(geminiCompatibilityString);


                    }else if ((signDateLibraInt >= libraStartInt) & (signDateLibraInt <= libraEndInt)
                            || (signDateLibraInt >= libraScopeStartInt) & (signDateLibraInt <= libraScopeEndInt)){

                        Log.d("LibraTestLog", "You've returned Libra");
                        zodiacAnswerAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.zodiacansweranimation);
                        yourSignIsLabel.startAnimation(zodiacAnswerAnimation);
                        bestSignsLabel.setVisibility(View.VISIBLE);
                        yourSignIsLabel.setText(Libra);
                        bestSignsLabel.setText(libraCompatibilityString);


                    }else if ((signDateTaurusInt >= taurusStartInt) & (signDateTaurusInt <= taurusEndInt)
                            || (signDateTaurusInt >= taurusScopeStartInt) & (signDateTaurusInt <= taurusScopeEndInt)){

                        Log.d("TaurusTestLog", "You've returned Taurus");
                        zodiacAnswerAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.zodiacansweranimation);
                        yourSignIsLabel.startAnimation(zodiacAnswerAnimation);
                        bestSignsLabel.setVisibility(View.VISIBLE);
                        yourSignIsLabel.setText(Taurus);
                        bestSignsLabel.setText(taurusCompatibilityString);


                    }else if ((signDateScorpioInt >= scorpioStartInt) & (signDateScorpioInt <= scorpioEndInt)
                            || (signDateScorpioInt >= scorpioScopeStartInt) & (signDateScorpioInt <= scorpioScopeEndInt)){

                        Log.d("ScorpioTestLog", "You've returned Scorpio");
                        zodiacAnswerAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.zodiacansweranimation);
                        yourSignIsLabel.startAnimation(zodiacAnswerAnimation);
                        bestSignsLabel.setVisibility(View.VISIBLE);
                        yourSignIsLabel.setText(Scorpio);
                        bestSignsLabel.setText(scorpioCompatibilityString);


                    }else if ((signDateSagittariusInt >= sagittariusStartInt) & (signDateSagittariusInt <= sagittariusEndInt)
                            || (signDateSagittariusInt >= sagittariusScopeStartInt) & (signDateSagittariusInt <= sagittariusScopeEndInt)){

                        Log.d("SagittariusTestLog", "You've returned Sagittarius");
                        zodiacAnswerAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.zodiacansweranimation);
                        yourSignIsLabel.startAnimation(zodiacAnswerAnimation);
                        bestSignsLabel.setVisibility(View.VISIBLE);
                        yourSignIsLabel.setText(Sagittarius);
                        bestSignsLabel.setText(sagittariusCompatibilityString);


                    }else if ((signDatePiscesInt >= piscesStartInt) & (signDatePiscesInt <= piscesEndInt)
                            || (signDatePiscesInt >= piscesScopeStartInt) & (signDatePiscesInt <= piscesScopeEndInt)){

                        Log.d("PiscesTestLog", "You've returned Pisces");
                        zodiacAnswerAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.zodiacansweranimation);
                        yourSignIsLabel.startAnimation(zodiacAnswerAnimation);
                        bestSignsLabel.setVisibility(View.VISIBLE);
                        yourSignIsLabel.setText(Pisces);
                        bestSignsLabel.setText(piscesCompatibilityString);


                    }else if ((signDateCancerInt >= cancerStartInt) & (signDateCancerInt <= cancerEndInt)
                            || (signDateCancerInt >= cancerScopeStartInt) & (signDateCancerInt <= cancerScopeEntInt)){

                        Log.d("CancerTestLog", "You've returned Cancer");
                        zodiacAnswerAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.zodiacansweranimation);
                        yourSignIsLabel.startAnimation(zodiacAnswerAnimation);
                        bestSignsLabel.setVisibility(View.VISIBLE);
                        yourSignIsLabel.setText(Cancer);
                        bestSignsLabel.setText(cancerCompatibilityString);


                    }else {

                        Log.d("badDate", "Outside of scope");

                    }

                } catch(NumberFormatException nfe) {

                    Log.d("Catch", "I caught an Error");
                }
            }
        });

        // _________________________________________________________________________________________


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_objects, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int getId = item.getItemId();

        //go Back
        if(getId == R.id.returnToMain){

            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("HEY...");
            alertDialog.setMessage("Hey... Hey, I know. You wan't more.  \n" +
                    "But give us just a bit more time.  We're \n" +
                    "working on more features right now. Okay?");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

            alertDialog.show();

        }

        //Go Forward
        if(getId == R.id.forwardOptions){

            Intent toNew = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(toNew);

        }

        return true;
    }
}