package com.openmessenger.supermiware.horoscopeplus02;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

// RET = "Return"
// *** = The annotation in that case is to explain an item that's just for testing.
//
// SUPERMIWARE
//
//

public class Main2Activity extends AppCompatActivity {


    // Animations
    private Animation leftToRightAnimation;

    // Dates
    private Date date;

    // TextViews
    private TextView dateString;

    // Layouts
    private LinearLayout dateHouseLayout;
    private LinearLayout totalLayout;

    // Buttons
    private Button dayNumberButton;
    private Button monthNumberButton;
    private Button yearNumberButton;
    private Button questionMarkButton;

    // Intents
    private Intent returnIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        passDatesToZodiacFormula();

        numberButtonOnClick();

        // Initializes all variables in the Main2Activity layout
        loadAllUIVars();

        questionMarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(Main2Activity.this).create();
                alertDialog.setTitle("Let me answer your questions");
                alertDialog.setMessage("Below you'll find 3 numbers.  They are labeled Day, Month and Year.  " +
                        "\nYou can touch any of those three numbers and an explanation of that number" +
                        "\nwill be given.  Each number is the addition" +
                        "\nof the corresponding time(day, month or year) which automatically updates" +
                        "\neveryday.  The best advice for your Day, Month and year will be given" +
                        "\nbased on the numbers generated from the current date.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }
        });

        // Initializes the array index for the all the for the arrayData array
        arrayOfNumerExplanations();

        // RET DATE AND DISPLAY
        //
        // Below section of code RET current date and displays that current value to the
        // TextView below the navigation bars.
        //
        //

        date = Calendar.getInstance().getTime();
        Log.d("testDateCatch", "" + date);
        dateString.setText("" + date);
        dateHouseLayout.startAnimation(leftToRightAnimation);


    }


    // VAR LOADER
    //
    // The below method will load all variables that will/can exist outside of the inner portion of
    // the general app('s onCreateMethod)
    //
    //

    private Void loadAllUIVars(){

        // Animations
        leftToRightAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.lefttorightanimation);

        // Layouts
        dateHouseLayout = findViewById(R.id.dateHouseLayout);

        // Strings
        dateString = findViewById(R.id.dateTextView);

        // Buttons
        dayNumberButton = findViewById(R.id.dayNumber);
        yearNumberButton = findViewById(R.id.yearNumber);
        monthNumberButton = findViewById(R.id.monthNumber);
        questionMarkButton = findViewById(R.id.questionMarkButton);

        return null;
    }

    private Void passDatesToZodiacFormula(){

        loadAllUIVars();

        arrayOfNumerExplanations();

        // PORTION NOTE:
        //
        // Each part of the date must be separated and calculated individually.
        // For example: MM/DD//YYYY - M + M THEN D + D Then Y + Y + Y + Y

        // First separation
        monthForLoops();

        // Second Separation
        dayOfMonthForLoops();

        // Third Separation
        yearForLoops();

        return null;
    }


    private String[] arrayOfNumerExplanations(){

        String[] arrayData = {

                "\n1. Positive Characteristics: " +
                        "\nIndividualistic and independent, showing leadership and drive. " +
                        "\nThe 1 is masculine\n" +
                        "" +
                        "\nNegative Characteristics: " +
                        "\n1s can be stubborn, selfish, weak and undisciplined, or a pariah.\n" +
                        "" +
                        "What this means for this time: \n" +
                        "" +
                        "It may be a good idea to focus on getting things done for yourself.\n" +
                        "Depending on your zodiac sign, this may be difficult.  But I'm sure some\n" +
                        "netflix or a bit of gardening may help you out a bit.",

                "\n2. Positive Characteristics: " +
                        "\nSensitive, tactful, diplomatic and cooperative." +
                        "\nA 2 may express many musical or dual qualities and " +
                        "\nalso tends intuitive.\n" +
                        "" +
                        "\nNegative Characteristics: \n" +
                        "\n2s are often discontent and can be seen as spoiled or lazy. They can be" +
                        "\ncareless, particularly with the truth, but when criticized for their faults.\n" +
                        "" +
                        "\nWhat this means for this time:\n" +
                        "" +
                        "\nThis is a time to focus on both that masculine and the feminine energies." +
                        "\nYou may be out of balance in some way, shape or fashion; find that thing" +
                        "\nand return it to its' proper place.",

                "\n3. Positive Characteristics: " +
                        "\n3s are imaginative, expressive communicators and artists.\n" +
                        "" +
                        "\nNegative Characteristics: " +
                        "\nFor as inspirational as 3s are, there is a price: they are often vain, extravagant and prone " +
                        "\nto complaining.\n" +
                        "" +
                        "\nWhat this means for this time:\n" +
                        "" +
                        "\nYou may have left something incomplete.  3 is the number of completion." +
                        "\nwhat ever that thing is make it right, or perhaps something of that nature" +
                        "\nis coming your way. Be aware of everything for this time.",

                "\n4. Positive Characteristics: " +
                        "\n4s are disciplined, strong, stable, pragmatic, down-to-earth, reliable, dependable and hard-working\n" +
                        "" +
                        "\nNegative Characteristics: " +
                        "\n4s pay for their stability and pragmatism by tending toward the boring side. This may express " +
                        "\nitself with a lack of imagination, emotions and/or empathy. 4s may not bother to put much care into" +
                        "\ntheir appearance, and their social awkwardness can make them seem vulgar, crude or jealous.\n" +
                        "" +
                        "\nWhat this time means for you:\n" +
                        "" +
                        "\nIt's okay to be concerned with the emotional affairs of other people from time to time" +
                        "\nIt's also okay to step outside your comfort zone.  This may be a time where you must" +
                        "\ndo something different, challenge your self.  See what your capable of.",

                "\n5. Positive Characteristics:" +
                        "\n5s are energetic, adventurous, daring and freedom-loving. They also tend to be versatile, " +
                        "\nflexible, adaptable, curious, social, sensual, quick-thinking, witty, courageous and worldly.\n" +
                        "" +
                        "\nNegative Characteristics: " +
                        "\nOn the flip side, 5s can be unstable, chaotic, self-indulgent, irresponsible or careless. " +
                        "\nThey should beware the consequences of drug abuse and unhealthy sexual tendencies.\n" +
                        "" +
                        "\nWhat this time means for you:\n" +
                        "" +
                        "\n5 represents ego.  Everything you have an urge to do that you maybe shouldn't." +
                        "\nHowever, this is also the first of the creation stages.  Because cautious who you're" +
                        "\nspending time with but also, if you're thinking of some creative thing.  Now" +
                        "\nmay be a good time to start planing it.",

                "\n6. Positive Characteristics: " +
                        "\n5s are energetic, adventurous, daring and freedom-loving. They also tend to be versatile, " +
                        "\nflexible, adaptable, curious, social, sensual, quick-thinking, witty, courageous and worldly.\n" +
                        "" +
                        "\nNegative Characteristics: " +
                        "\nOn the flip side, 5s can be unstable, chaotic, self-indulgent, irresponsible or careless. " +
                        "\nThey should beware the consequences of drug abuse and unhealthy sexual tendencies.",

                "\n7. Positive Characteristics: " +
                        "\n7 isn't just a lucky number. It's also spiritual, intelligent, analytical and focused\n" +
                        "" +
                        "\nNegative Characteristics: " +
                        "\n7s can be aloof, distant, sarcastic, socially awkward, melancholic, cowardly and, when " +
                        "\nthey're at their worst, back-stabbers.\n" +
                        "" +
                        "\nWhat this time means for you:\n" +
                        "" +
                        "\nIt's time to make your dreams come true.  Hopefully you took advantage" +
                        "\nof the 6 energy you've had previously.  Now you're project should be seeing its" +
                        "\nfruit.  In any case the most important thing for you right now is to focus" +
                        "\non your focus.  Don't be so reluctant to improve.",

                "\n8. Positive Characteristics: " +
                        "\n8s are authoritative, business-minded leaders. They value control and tend to be powerful, " +
                        "\nbut are also balanced, materially detached, successful and realistic. They end up in management " +
                        "\npositions, are efficient, capable, street-smart and good judges of character.\n" +
                        "" +
                        "\nNegative Characteristics: " +
                        "\nThe dark side of the 8 can be cruel, insensitive, violent, bullish or greedy. At their worst, " +
                        "\n8s can become intolerant religious zealots.\n" +
                        "" +
                        "What this means for this time:\n" +
                        "" +
                        "\nThings can go in any direction right now.  8 = Chaos in the numerology world." +
                        "\nBe wise about your decisions during this time.  It may be a good idea to relax" +
                        "\nwhere you are for the time being.  If you're out of balance/order be prepared" +
                        "\nto be forced back into order.",

                "\n9. Positive Characteristics: " +
                        "\n9s are helpful, compassionate, aristocratic, sophisticated, charitable, and proud.\n" +
                        "" +
                        "\nNegative Characteristics: " +
                        "\n9s can end up being egocentric, arrogant, self-pitying, sentimental, discontent, fickle, cold " +
                        "\nor mentally unstable.\n" +
                        "" +
                        "What this means for this time:\n" +
                        "" +
                        "\n9 is full completion.  What ever the result of you're 8 time was, it will" +
                        "\namplify during the 9 time.  If you made good decisions during the 8 season" +
                        "\nsuch will be the same during the 9 time.  In the usual case.",

                "\n11. Positive Characteristics: " +
                        "\nA Master number, the 11 is the most intuitive of all numbers. It is instinctual, charismatic, " +
                        "\ndynamic and capable when its sights are set on a concrete goal. The 11 is the number associated with faith and psychics.\n" +
                        "" +
                        "\nNegative Characteristics: " +
                        "\nThe 11 can be anxious, shy, stressed, conflicted and scattered. When focus is not applied toward a goal, " +
                        "\nthe 11 can be extremely self-sabotaging. As a Master number, the positive " +
                        "\ncharacteristics will turn into obstacles when not understood or used properly.\n" +
                        "" +
                        "\nWhat this means for this time:\n" +
                        "" +
                        "\nThis time for you will be a great time for self reflection and study as it relates" +
                        "\nto the world around you.  Set out to master a skill you have, learn a trade or " +
                        "\nor go back for your Masters, Bachelors or get started in school as soon as you can.",

                "\n22. Positive Characteristics: " +
                        "\nThe 22 is the most powerful of all numbers, able to turn lofty dreams into realities. It is confident, " +
                        "\npragmatic, ambitious and disciplined.\n" +
                        "" +
                        "\nNegative Characteristics: " +
                        "\nImpracticality and self-imposed pressure can get the better of the 22. When unaware of its own potential, " +
                        "\nthe 22 will miss out on or shy away from necessary opportunities.  ",

                "\n33. Positive Characteristics: " +
                        "\nThe 33 is a humanitarian. It is understanding and knowledgeable, a mover and a shaker.\n" +
                        "" +
                        "\nNegative Characteristics: " +
                        "\nAs a Master number, when the positive potential of this number is not used properly, " +
                        "\none tends to become preachy and care less about others, putting too much focus on one's personal agenda."

        };
        return arrayData;
    }

    private void monthForLoops(){

        // RET CURRENT DATE
        //
        //

        // RET CURRENT MONTH
        //
        // The below section of code will retrieve the current month and display it in the linear
        // view in the 0 position.  However if the cur.Month is a 2 digit number, both digits
        // will be added together and displayed as a single digit - as per the rules of numerology.
        //
        //

        int sum = 0; // int sum will hold value 0 and will be added to monthToInt variable at the end of each iteration
        int monthDate = Calendar.getInstance().get(Calendar.MONTH) + 1; // Holds the value for the month as an integer
        Log.d("testMonthRet", "" + monthDate); // *** FOR TESTING: Checks monthDate value
        String monthDateToString = Integer.toString(monthDate); // Turns monthDate variable into a string
        String[] monthSplit = monthDateToString.split(""); // Splits monthDate value into separate individual char/int


        // For loop converts monthSplit back to int and adds the individual ints' together in succession.
        // int i seems to only work when the software begins at index 1 as a posed to index 0.  I believe
        // the technology might be adding one or more of the splits in the monthSplit variable into the index
        // and as such the .length value and the int i index/iteration isn't the same amount.  However, staring at 1
        // makes the iteration go through smoothly.
        //
        for (int i = 1; i <= monthDateToString.length(); i++){

            int monthDateBackToInt = Integer.parseInt(monthDateToString);
            Log.d("logI", "" + monthDateBackToInt);

            if(monthDateBackToInt <= 9){

                String monthDateNewString = monthDateToString + " Month";
                monthNumberButton.setText(monthDateNewString);

            }else{

                Log.d("sumTest0: ", "" + monthDateToString);

                Log.d("Higher", "Was higher than 9");

            }
        }
        int monthDateBackToInt = Integer.parseInt(monthDateToString);
        Log.d("logI", "" + monthDateBackToInt);

        if(monthDateBackToInt > 9){

            for(int i01 = 1; i01 < monthSplit.length; i01++){

                int backToInt = Integer.parseInt(monthSplit[i01]);
                Log.d("backtoin: ", "" + backToInt);
                sum = sum + backToInt;

                String sumToString = Integer.toString(sum) + " Month";
                monthNumberButton.setText(sumToString);

            }

        }else{
            Log.d("Higher", "Was higher than 9");

        }

        Log.d("sumTest1: ", "" + sum);

    }

    private void dayOfMonthForLoops(){

        // RET CURRENT DAY OF THE MONTH
        //
        // The below section of code will retrieve the current day of the
        // month and display it in the linear view in index position 1.
        // However if the cur.DayOfTheMonth is a 2 digit number, both digits
        // will be added together and displayed as a single digit - as per the rules of numerology.
        //
        //

        int sum2 = 0;
        int dayOfTheMonthDate = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        String dayOfMonthToString = Integer.toString(dayOfTheMonthDate);
        String[] domSplit = dayOfMonthToString.split(""); // Splits monthDate value into separate individual char/int

        // int[] number = new int[]{dayOfTheMonthDate}; -> // *** Was set up as a possible solution.  However ... it had no value here.

        for (int i = 1; i <= dayOfMonthToString.length(); i++){

            int domBackToInt = Integer.parseInt(dayOfMonthToString);
            Log.d("logI", "" + domBackToInt);

            if(domBackToInt <= 9){

                String dayOfMonthString = dayOfMonthToString + " Day";
                dayNumberButton.setText(dayOfMonthString);

            }else{

                Log.d("Higher", "Was higher than 9");
            }

        }
        int domBackToInt = Integer.parseInt(dayOfMonthToString);
        Log.d("logI", "" + domBackToInt);

        if(domBackToInt > 9){

            for(int i02 = 1; i02 < domSplit.length; i02++){

                int backToInt2 = Integer.parseInt(domSplit[i02]);
                Log.d("backtoin: ", "" + backToInt2);
                sum2 = sum2 + backToInt2;

                String sumToString = Integer.toString(sum2) + " Day";
                dayNumberButton.setText(sumToString);

            }

        }else{

            Log.d("Nope", "Whoo, Nope.");
        }

        Log.d("sumTest2: ", "" + sum2);
    }
    private void yearForLoops() {

        // RET CURRENT YEAR TO DATE
        //
        // The below section of code will retrieve the current year to date
        // and will display it in the linear view in index position 2.
        // However if the cur.YEAR is a 4 digit number, all 4 digits will be added together
        // and displayed as a single digit or a double digit - as per the rules of numerology.
        //
        //

        int sum3 = 0;
        int yearDate = Calendar.getInstance().get(Calendar.YEAR);
        String yearDateToString = Integer.toString(yearDate);
        String[] yearSplit = yearDateToString.split(""); // Splits monthDate value into separate individual char/int

        for (int i03 = 4; i03 <= yearDateToString.length(); i03++) {
/*
            int yearBackToInt = Integer.parseInt(yearDateToString);
            Log.d("logI", "" + yearBackToInt);

            if (yearBackToInt <= 9) {

                String yearDateNewString = yearDateToString + " Year";
                yearTextView.setText(yearDateNewString);

            }*/
            int yearBackToInt2 = Integer.parseInt(yearDateToString);
            Log.d("logI", "" + yearBackToInt2);

            if (yearBackToInt2 > 9) {

                for (int i003 = 1; i003 < yearSplit.length; i003++) {

                    int backToInt3 = Integer.parseInt(yearSplit[i003]);
                    Log.d("backtoin: ", "" + yearBackToInt2);
                    sum3 = sum3 + backToInt3;

                    String sumToString = Integer.toString(sum3) + " Year";
                    yearNumberButton.setText(sumToString);

                }

            } else {
                Toast.makeText(this, "Woooh Nope", Toast.LENGTH_SHORT).show();
            }

            Log.d("sumTest2: ", "" + sum3);
        }
    }

    private void numberButtonOnClick(){


        loadAllUIVars();
        arrayOfNumerExplanations();

        dayNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String dayNumStr = dayNumberButton.getText().toString().replace(" ", "").replaceAll("[a-zA-Z]", "");
                Log.d("testReplace", "" + dayNumStr);
                int dayNumStrToInt = Integer.parseInt(dayNumStr) -1;

                if(dayNumStrToInt != 10){

                    AlertDialog alertDialog = new AlertDialog.Builder(Main2Activity.this).create();
                    alertDialog.setTitle("Your Days Number Explained");
                    alertDialog.setMessage(arrayOfNumerExplanations()[dayNumStrToInt]);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }else if(dayNumStrToInt == 10){

                    AlertDialog alertDialog = new AlertDialog.Builder(Main2Activity.this).create();
                    alertDialog.setTitle("Your Days Number Explained");
                    alertDialog.setMessage(arrayOfNumerExplanations()[0]);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }


            }
        });

        monthNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String monthNumStr = monthNumberButton.getText().toString().replace(" ", "").replaceAll("[a-zA-Z]", "");
                Log.d("testReplace", "" + monthNumStr);
                int monthNumStrToInt = Integer.parseInt(monthNumStr) -1;

                if(monthNumStrToInt != 10){

                    AlertDialog alertDialog = new AlertDialog.Builder(Main2Activity.this).create();
                    alertDialog.setTitle("Your Days Number Explained");
                    alertDialog.setMessage(arrayOfNumerExplanations()[monthNumStrToInt]);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }else if(monthNumStrToInt == 10){

                    AlertDialog alertDialog = new AlertDialog.Builder(Main2Activity.this).create();
                    alertDialog.setTitle("Your Days Number Explained");
                    alertDialog.setMessage(arrayOfNumerExplanations()[0]);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }
            }
        });

        yearNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String yearNumStr = yearNumberButton.getText().toString().replace(" ", "").replaceAll("[a-zA-Z]", "");
                Log.d("testReplace", "" + yearNumStr);
                int yearNumStrToInt = Integer.parseInt(yearNumStr) -2;

                if(yearNumStrToInt != 10){

                    AlertDialog alertDialog = new AlertDialog.Builder(Main2Activity.this).create();
                    alertDialog.setTitle("Your Days Number Explained");
                    alertDialog.setMessage(arrayOfNumerExplanations()[yearNumStrToInt]);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }else if(yearNumStrToInt == 10){

                    AlertDialog alertDialog = new AlertDialog.Builder(Main2Activity.this).create();
                    alertDialog.setTitle("Your Days Number Explained");
                    alertDialog.setMessage(arrayOfNumerExplanations()[0]);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }
            }
        });
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

            Intent toNew = new Intent(Main2Activity.this, MainActivity.class);
            startActivity(toNew);

        }

        //Go Forward
        if(getId == R.id.forwardOptions){

            AlertDialog alertDialog = new AlertDialog.Builder(Main2Activity.this).create();
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


        return true;
    }
}
