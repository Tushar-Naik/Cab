package com.example.cab;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;

import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public int num, tries = 0, points = 1000, pen = 0;

	String record = "", user = "Tushar";
	long st, end;
	int[] b = new int[3];
	int[] a = new int[3];
	int x, y, z, cow = 0, bull = 0;
	String s = "";
	boolean flag = false;
	String fout = "";

	void set_number() {

		Random randomGenerator = new Random();
		x = randomGenerator.nextInt(10);
		y = randomGenerator.nextInt(10);
		z = randomGenerator.nextInt(10);
		while (y == x)
			y = randomGenerator.nextInt(10);
		while (z == x || z == y)
			z = randomGenerator.nextInt(10);
		a[0] = x;
		a[1] = y;
		a[2] = z;
		// System.out.println("Computer's number : "+a[0]+" "+a[1]+" "+a[2]);
	}

	void check() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				if (a[i] == b[j]) {
					if (i == j)
						bull++;
					else
						cow++;
				}
			}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d("com.example.cab", "Hello");
		final TextView tv2 = (TextView) findViewById(R.id.textView2);
		TextView tv1 = (TextView) findViewById(R.id.textView1);
		final EditText et = (EditText) findViewById(R.id.editText1);
		Button b1 = (Button) findViewById(R.id.button1);
		b1.setBackgroundColor(0xff55d5e5);
		b1.setTextColor(0xffffffff);
		et.setHint("Enter the number");

		// final Button b2=(Button)findViewById(R.id.button2);
		// b2.setText("0     0");
		
		set_number();
		st = SystemClock.elapsedRealtime();
		Toast toast = Toast.makeText(getApplicationContext(),
				"Start Guessing. Time has started !!", Toast.LENGTH_SHORT);
		toast.show();
		
		/*int i = a[0] * 100 + a[1] * 10 + a[2];
		s = s + Integer.toString(i);
		tv1.setText(s);*/

		
		b1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// penalty variable flag1
				int flag1 = 0;
				if (bull < 3) {
					cow = 0;
					bull = 0;
					tries++;
					flag1 = 0;
					if (et.getText().length() != 3) {
						System.out.println("Enter only 3 digit numbers");
						points -= 5;
						pen += 5;
						tries--;
						flag1 = 1;
						Toast toast = Toast.makeText(getApplicationContext(),
								"Enter 3 Digit nos", Toast.LENGTH_SHORT);
						toast.show();
						et.setText("");
					}
					if (flag1 == 0) {
						b[0] = (int) et.getText().charAt(0) - 48;
						b[1] = (int) et.getText().charAt(1) - 48;
						b[2] = (int) et.getText().charAt(2) - 48;
						if (b[0] == b[1] || b[0] == b[2] || b[1] == b[2]) {
							System.out.println("Enter only 3 unique numbers");
							points -= 5;
							pen += 5;
							tries--;
							flag1 = 1;
							Toast toast = Toast
									.makeText(getApplicationContext(),
											"Enter 3 Unique digits",
											Toast.LENGTH_SHORT);
							toast.show();
							et.setText("");
						}
						check();
						if (bull == 3)
							flag = true;
					}

					if (!flag && flag1 == 0) {
						String s1;
						s1 = Integer.toString(b[0]) + Integer.toString(b[1])
								+ Integer.toString(b[2]);
						s1 += "\t\tB=" + Integer.toString(bull) + " C="
								+ Integer.toString(cow);
						s = s1 + "\n" + s;
						// b2.setText(cow+"     "+bull);
						// for penalty

						// to calculate the score of the user
						if (tries <= 6)
							points -= 50;
						else if (tries <= 12)
							points -= 25;
						else
							points -= 10;

						tv2.setText(s);
						et.setText("");
					}

				}
				if (flag) {

					setContentView(R.layout.win);
					final TextView tv22 = (TextView) findViewById(R.id.textView2);
					final TextView tv21 = (TextView) findViewById(R.id.textView1);
					final EditText et21 = (EditText) findViewById(R.id.editText1);
					final Button b21 = (Button) findViewById(R.id.button1);
					Button b22 = (Button) findViewById(R.id.button2);
					b21.setBackgroundColor(0xff55d5e5);
					b21.setTextColor(0xffffffff);
					b21.setTextSize(20);
					b21.setHeight(60);
					b22.setBackgroundColor(0xff44c5e5);
					b22.setTextColor(0xffffffff);
					b22.setTextSize(20);
					b22.setHeight(60);
					
					
					et21.setText("");
					end = SystemClock.elapsedRealtime();
					points -= (end - st) / 1000;
					tv22.setText("BULLSEYE\n\nYou took " + tries
							+ " attempts\nand took " + (end - st) / 1000
							+ " seconds\nTOTAL POINTS=" + points);
					final String[] uname = new String[1000];
					final int[] uscore = new int[1000];
					for (int i = 0; i < 1000; i++) {
						uname[i] = "";
						uscore[i] = 0;
					}
					et21.setHint("Enter your name ..");
					b21.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							user = et21.getText().toString();
							if (user.length()==0) {
								Toast toast = Toast.makeText(getApplicationContext(),
										"Enter a valid name", Toast.LENGTH_SHORT);
								toast.show();
								
							} else {
								record = user + "|" + Integer.toString(points);
								try {
									FileOutputStream fOut = openFileOutput(
											"scores.txt", MODE_APPEND);
									OutputStreamWriter osw = new OutputStreamWriter(
											fOut);
									osw.write(record + "$");
									osw.flush();
									osw.close();
									FileOutputStream uOut = openFileOutput("lastuser.txt", MODE_APPEND);
									OutputStreamWriter usw = new OutputStreamWriter(uOut);
									usw.write(record+"$");
									usw.flush();
									usw.close();

									startActivity(new Intent(MainActivity.this,Winscr.class));
									MainActivity.this.finish();
									
								} catch (IOException ioe) {
									System.err.println("IOException: "
											+ ioe.getMessage());
								}
							}
						}
					});

					b22.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub

							startActivity(new Intent(MainActivity.this,
									Mainscr.class));

							MainActivity.this.finish();
						}

					});
				}

			}
		});
		
	}

}
