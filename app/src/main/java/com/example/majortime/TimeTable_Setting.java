package com.example.majortime;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.majortime.VO.Schedule_VO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TimeTable_Setting extends AppCompatActivity {

    private static final String INTENT_ACTION = "arabiannight.tistory.com.alarmmanager";
    int hour_start, min_start, hour_end, min_end, total_minute, colorfill, id;
    String gyosi = "";
    MyDBHelper helper;
    SQLiteDatabase database;
    String Subject, Classroom, Professor;
    String dayselect;
    Spinner ClockSpinner;
    ArrayAdapter arrayAdapter;

    public static String Rsubject = "", Rclassroom = "", Rprofessor = "", Rday = "", Rhour_start = "", Rhour_end = "", Rmin_start = "", Rmin_end = "", Rtotal_minute = "", Rcolorfill = "", Rgyosi = "";

    public Context context = this;

    NotificationManager manager;
    NotificationCompat.Builder builder;
    private static String CHANNEL_ID = "channel1";
    private static String CHANEL_NAME = "Channel1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable_add_activity);

        helper = new MyDBHelper(this);
        database = helper.getWritableDatabase();
        final ContentValues cvalues = new ContentValues();

//        String[] items = getResources().getStringArray(R.array.timetable_days);
        final EditText subject = (EditText) findViewById(R.id.subject);
        final EditText classroom = (EditText) findViewById(R.id.classroom);
        final EditText professor = (EditText) findViewById(R.id.professor_name);
        final TextView dayTV = (TextView) findViewById(R.id.dayTV);

        final RadioButton RB_Mon = (RadioButton) findViewById(R.id.RBMon);
        final RadioButton RB_Tue = (RadioButton) findViewById(R.id.RBTue);
        final RadioButton RB_Wed = (RadioButton) findViewById(R.id.RBWed);
        final RadioButton RB_Thu = (RadioButton) findViewById(R.id.RBThu);
        final RadioButton RB_Fri = (RadioButton) findViewById(R.id.RBFri);
        final RadioGroup RBgroup = (RadioGroup) findViewById(R.id.RBgroup);

        ClockSpinner = (Spinner) findViewById(R.id.spinnerClock);

        final Button Time_Set_Complete = (Button) findViewById(R.id.timetable_setting_complete);
        final Button DaySelect = (Button) findViewById(R.id.DaySelect);
//        final ToggleButton AlarmSetBtn = (ToggleButton) findViewById(R.id.AlarmSetBtn);


//        AlarmSetBtn.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
//
//                        return true;
//                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
//
//                        AlarmSetBtn.setBackgroundColor(Color.parseColor("#c7c7c7"));
//                        AlarmSetBtn.setText("?????? (OFF)");
//                        Toast.makeText(getApplicationContext(), "????????? ?????????????????????.", Toast.LENGTH_SHORT).show();
//                        return true;
//                    }
//                return false;
//            }
//        });

        //                new AlarmHATT(getApplicationContext()).Alarm();

//        AlarmSetBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (AlarmSetBtn.isChecked()) {
//                    showNoti(Subject);
//                    Toast.makeText(getApplicationContext(), "????????? ?????????????????????. (?????? ?????? 30??? ???)", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "????????? ?????????????????????.", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });



        ClockSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (ClockSpinner.getSelectedItem().toString().equals("1??????")) {
                    hour_start = 9;
                    min_start = 00;
                    hour_end = 9;
                    min_end = 50;
                    total_minute = 50;
                    colorfill = 10;
                    gyosi = "1??????";
                } else if (ClockSpinner.getSelectedItem().toString().equals("2??????")) {
                    hour_start = 9;
                    min_start = 55;
                    hour_end = 10;
                    min_end = 45;
                    total_minute = 50;
                    colorfill = 10;
                    gyosi = "2??????";
                } else if (ClockSpinner.getSelectedItem().toString().equals("3??????")) {
                    hour_start = 10;
                    min_start = 50;
                    hour_end = 11;
                    min_end = 40;
                    total_minute = 50;
                    colorfill = 10;
                    gyosi = "3??????";
                } else if (ClockSpinner.getSelectedItem().toString().equals("4??????")) {
                    hour_start = 11;
                    min_start = 55;
                    hour_end = 12;
                    min_end = 45;
                    total_minute = 50;
                    colorfill = 10;
                    gyosi = "4??????";
                } else if (ClockSpinner.getSelectedItem().toString().equals("5??????")) {
                    hour_start = 12;
                    min_start = 50;
                    hour_end = 13;
                    min_end = 40;
                    total_minute = 50;
                    colorfill = 10;
                    gyosi = "5??????";
                } else if (ClockSpinner.getSelectedItem().toString().equals("6??????")) {
                    hour_start = 13;
                    min_start = 45;
                    hour_end = 14;
                    min_end = 35;
                    total_minute = 50;
                    colorfill = 10;
                    gyosi = "6??????";
                } else if (ClockSpinner.getSelectedItem().toString().equals("7??????")) {
                    hour_start = 14;
                    min_start = 40;
                    hour_end = 15;
                    min_end = 30;
                    total_minute = 50;
                    colorfill = 10;
                    gyosi = "7??????";
                } else if (ClockSpinner.getSelectedItem().toString().equals("8??????")) {
                    hour_start = 15;
                    min_start = 35;
                    hour_end = 16;
                    min_end = 25;
                    total_minute = 50;
                    colorfill = 10;
                    gyosi = "8??????";
                } else if (ClockSpinner.getSelectedItem().toString().equals("9??????")) {
                    hour_start = 16;
                    min_start = 30;
                    hour_end = 17;
                    min_end = 20;
                    total_minute = 50;
                    colorfill = 10;
                    gyosi = "9??????";
                } else if (ClockSpinner.getSelectedItem().toString().equals("1?????? ~ 2??????")) {
                    hour_start = 9;
                    min_start = 00;
                    hour_end = 10;
                    min_end = 45;
                    total_minute = 105;
                    colorfill = 21;
                    gyosi = "1?????? ~ 2??????";
                } else if (ClockSpinner.getSelectedItem().toString().equals("2?????? ~ 3??????")) {
                    hour_start = 9;
                    min_start = 55;
                    hour_end = 11;
                    min_end = 40;
                    total_minute = 105;
                    colorfill = 21;
                    gyosi = "2?????? ~ 3??????";
                } else if (ClockSpinner.getSelectedItem().toString().equals("3?????? ~ 4??????")) {
                    hour_start = 10;
                    min_start = 50;
                    hour_end = 12;
                    min_end = 45;
                    total_minute = 105;
                    colorfill = 21;
                    gyosi = "3?????? ~ 4??????";
                } else if (ClockSpinner.getSelectedItem().toString().equals("4?????? ~ 5??????")) {
                    hour_start = 11;
                    min_start = 55;
                    hour_end = 13;
                    min_end = 40;
                    total_minute = 105;
                    colorfill = 21;
                    gyosi = "4?????? ~ 5??????";
                } else if (ClockSpinner.getSelectedItem().toString().equals("5?????? ~ 6??????")) {
                    hour_start = 12;
                    min_start = 50;
                    hour_end = 13;
                    min_end = 40;
                    total_minute = 105;
                    colorfill = 21;
                    gyosi = "5?????? ~ 6??????";
                } else if (ClockSpinner.getSelectedItem().toString().equals("6?????? ~ 7??????")) {
                    hour_start = 13;
                    min_start = 45;
                    hour_end = 15;
                    min_end = 30;
                    total_minute = 105;
                    colorfill = 21;
                    gyosi = "6?????? ~ 7??????";
                } else if (ClockSpinner.getSelectedItem().toString().equals("7?????? ~ 8??????")) {
                    hour_start = 14;
                    min_start = 40;
                    hour_end = 16;
                    min_end = 25;
                    total_minute = 105;
                    colorfill = 21;
                    gyosi = "7?????? ~ 8??????";
                } else if (ClockSpinner.getSelectedItem().toString().equals("8?????? ~ 9??????")) {
                    hour_start = 15;
                    min_start = 35;
                    hour_end = 17;
                    min_end = 20;
                    total_minute = 105;
                    colorfill = 21;
                    gyosi = "8?????? ~ 9??????";
                } else if (ClockSpinner.getSelectedItem().toString().equals("1?????? ~ 3??????")) {
                    hour_start = 9;
                    min_start = 00;
                    hour_end = 11;
                    min_end = 40;
                    total_minute = 160;
                    colorfill = 32;
                    gyosi = "1?????? ~ 3??????";
                } else if (ClockSpinner.getSelectedItem().toString().equals("2?????? ~ 4??????")) {
                    hour_start = 9;
                    min_start = 55;
                    hour_end = 12;
                    min_end = 45;
                    total_minute = 160;
                    colorfill = 32;
                    gyosi = "2?????? ~ 4??????";
                } else if (ClockSpinner.getSelectedItem().toString().equals("3?????? ~ 5??????")) {
                    hour_start = 10;
                    min_start = 50;
                    hour_end = 13;
                    min_end = 40;
                    total_minute = 160;
                    colorfill = 32;
                    gyosi = "3?????? ~ 5??????";
                } else if (ClockSpinner.getSelectedItem().toString().equals("4?????? ~ 6??????")) {
                    hour_start = 11;
                    min_start = 55;
                    hour_end = 14;
                    min_end = 35;
                    total_minute = 160;
                    colorfill = 32;
                    gyosi = "4?????? ~ 6??????";
                } else if (ClockSpinner.getSelectedItem().toString().equals("5?????? ~ 7??????")) {
                    hour_start = 12;
                    min_start = 50;
                    hour_end = 15;
                    min_end = 30;
                    total_minute = 160;
                    colorfill = 32;
                    gyosi = "5?????? ~ 7??????";
                } else if (ClockSpinner.getSelectedItem().toString().equals("6?????? ~ 8??????")) {
                    hour_start = 13;
                    min_start = 45;
                    hour_end = 16;
                    min_end = 25;
                    total_minute = 160;
                    colorfill = 32;
                    gyosi = "6?????? ~ 8??????";
                } else if (ClockSpinner.getSelectedItem().toString().equals("7?????? ~ 9??????")) {
                    hour_start = 14;
                    min_start = 40;
                    hour_end = 17;
                    min_end = 20;
                    total_minute = 160;
                    colorfill = 32;
                    gyosi = "7?????? ~ 9??????";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        DaySelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = RBgroup.getCheckedRadioButtonId();
                if (id == -1) {
                    Toast.makeText(getApplicationContext(), "????????? ???????????????", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "?????? ?????? ??????", Toast.LENGTH_SHORT).show();
            }
        });


        setViews();

        Time_Set_Complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Subject = subject.getText().toString();
                Classroom = classroom.getText().toString();
                Professor = professor.getText().toString();
                int color_random = new Random().nextInt(21);

                if (id == -1) {
                    Toast.makeText(getApplicationContext(), "????????? ???????????????", Toast.LENGTH_SHORT).show();
                }
                final RadioButton rb = (RadioButton) findViewById(id);
                dayselect = rb.getText().toString();

                try {
                    if (Subject.equals("") || Classroom.equals("") || Professor.equals("") || ClockSpinner.getSelectedItem().toString().equals("?????? ????????? ???????????????")) {
                        Toast.makeText(getApplicationContext(), "?????? ?????? ?????? ???????????????.", Toast.LENGTH_SHORT).show();

                    } else {
//                        database.execSQL("INSERT INTO majortimetable(NUM AUTOINCREMENT, SUBJECT, CLASSROOM, PROFESSOR, DAY, HOUR_START, MIN_START, HOUR_END, MIN_END,TOTAL_MINUTE,COLORFILL, GYOSI) values('" + num + "' , '" + Subject + "' , '" + Classroom + "' , '" + Professor + "' , '" + dayselect + "' , '" + hour_start + "' , '" + min_start + "' , '" + hour_end + "' , '" + min_end + "','" + total_minute + "', '" + colorfill + "', '" + gyosi + "')");
//                        cvalues.put("NUM", num);
                        cvalues.put("SUBJECT", Subject);
                        cvalues.put("CLASSROOM", Classroom);
                        cvalues.put("PROFESSOR", Professor);
                        cvalues.put("DAY", dayselect);
                        cvalues.put("HOUR_START", hour_start);
                        cvalues.put("MIN_START", min_start);
                        cvalues.put("HOUR_END", hour_end);
                        cvalues.put("MIN_END", min_end);
                        cvalues.put("GYOSI", gyosi);
                        database.insert("majortimetable", null, cvalues);
                        ////////????????? ???????????? ?????? ???????????? ?????? ?????? ///////////



                        Toast.makeText(getApplicationContext(), "????????? ?????? ??????.", Toast.LENGTH_SHORT).show();
//                        where SUBJECT = '" + Subject + "'
                        Cursor cursor;
                        List<Schedule_VO> list = new ArrayList<Schedule_VO>();

                        cursor = database.rawQuery("select SUBJECT, CLASSROOM, PROFESSOR, DAY, HOUR_START, MIN_START, HOUR_END, MIN_END,GYOSI from majortimetable", null);
                        while (cursor.moveToNext()) {
                            Schedule_VO schedule_vo = new Schedule_VO();

                            schedule_vo.setSubject(cursor.getString(0));
                            schedule_vo.setClassroom(cursor.getString(1));
                            schedule_vo.setProfessor(cursor.getString(2));
                            schedule_vo.setDay(cursor.getString(3));
                            schedule_vo.setHour_start(cursor.getString(4));
                            schedule_vo.setMin_start(cursor.getString(5));
                            schedule_vo.setHour_end(cursor.getString(6));
                            schedule_vo.setMin_end(cursor.getString(7));
                            schedule_vo.setGyosi(cursor.getString(8));

                            list.add(schedule_vo);

//                            String num__ = cursor.getString(0);
                            String subject__ = cursor.getString(0);
                            String classroom__ = cursor.getString(1);
                            String professor__ = cursor.getString(2);
                            String day__ = cursor.getString(3);
                            String hour_start__ = cursor.getString(4);
                            String min_start__ = cursor.getString(5);
                            String hour_end__ = cursor.getString(6);
                            String min_end__ = cursor.getString(7);
                            String gyosi__ = cursor.getString(8);

                            Rsubject = subject__;
                            Rclassroom = classroom__;
                            Rprofessor = professor__;
                            Rday = day__;
                            Rhour_start = hour_start__;
                            Rhour_end = hour_end__;
                            Rmin_start = min_start__;
                            Rmin_end = min_end__;
                            Rgyosi = gyosi__;

//                            Intent intent = new Intent(getApplicationContext(), Home_Activity.class);
//                            intent.putExtra("color_fill_R", Integer.parseInt(Rcolorfill));

//                            cursor.close();
//                            helper.close();
                        }
                        ///////////home activity??? ?????? ??????????????? ?????? ??????//////////
                        Intent intent = new Intent(getApplicationContext(), Home_Activity.class);


                        intent.putExtra("list", (Serializable) list);

                        intent.putExtra("hour_start_R", Integer.parseInt(Rhour_start));
                        intent.putExtra("hour_end_R", Integer.parseInt(Rhour_end));
                        intent.putExtra("min_start_R", Integer.parseInt(Rmin_start));
                        intent.putExtra("min_end_R", Integer.parseInt(Rmin_end));
                        intent.putExtra("subject_R", Rsubject);
                        intent.putExtra("Rgyosi", Rgyosi);
                        intent.putExtra("Rday", Rday);
                        intent.putExtra("classroom", Rclassroom);
                        intent.putExtra("professor", Rprofessor);

//                        replaceFragment(TimeTable_Fragment);
                        finish();
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setViews() {
        ClockSpinner = (Spinner) findViewById(R.id.spinnerClock);

        arrayAdapter = ArrayAdapter.createFromResource(this, R.array.clockstart_list, android.R.layout.simple_spinner_dropdown_item);

        ClockSpinner.setAdapter(arrayAdapter);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_frag, fragment);
        fragmentTransaction.commit();
    }

//    public class AlarmHATT {
//        private Context context;
//        public AlarmHATT(Context context) {
//            this.context=context;
//        }
//        public void Alarm() {
//            AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//            Intent intent = new Intent(getApplicationContext(), BroadcastD.class);
//
//            PendingIntent sender = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
//
//            Calendar calendar = Calendar.getInstance();
//            //???????????? calendar??? set?????????
//
//            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 23, 12, 0);
//
//            //?????? ??????
//            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
//        }
//    }

    public void showNoti(String name) {
        builder = null;
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(new NotificationChannel(CHANNEL_ID, CHANEL_NAME, NotificationManager.IMPORTANCE_DEFAULT));
            builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        } else {
            builder = new NotificationCompat.Builder(this);
        }
        Intent intent = new Intent(this, TimeTable_Fragment.class);
        intent.putExtra("subject", Subject);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 101, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //????????? ??????
        builder.setContentTitle(Subject);

        //????????? ?????????
        builder.setContentText(gyosi);

        //????????? ?????????
        builder.setSmallIcon(R.drawable.majorlogo);

        //????????? ????????? ?????? ????????????????????? ????????? ???????????? ???????????? ???.
        builder.setAutoCancel(true);

        //pendingIntent??? builder??? ?????? ??????. //????????? ????????? ???????????? ????????? ??? ????????? ??????.
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();

        //????????? ??????
        manager.notify(1, notification);

    }

    public void onToggleClicked(View v) {
        boolean on = ((ToggleButton) v).isChecked();

        if (on) {
//            AlarmSetBtn.setBackgroundColor(Color.parseColor("#83dcb7"));
            Toast.makeText(getApplicationContext(), "????????? ?????????????????????. (?????? ?????? 30??? ???)", Toast.LENGTH_SHORT).show();
        }
    }

    // ?????? ??????
    private void setAlarm(Context context, long second) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent Intent = new Intent(INTENT_ACTION);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, Intent, 0);

        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + second, pIntent);
    }

    // ?????? ??????
    private void releaseAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent Intent = new Intent(INTENT_ACTION);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, Intent, 0);
        alarmManager.cancel(pIntent);
    }
}