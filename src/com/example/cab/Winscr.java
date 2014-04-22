package com.example.cab;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Winscr extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score);
		TextView tv2 = (TextView) findViewById(R.id.textView2);
		Button b1 = (Button) findViewById(R.id.button1);
		b1.setBackgroundColor(0xff55d5e5);
		b1.setTextColor(0xffffffff);
		b1.setTextSize(20);
		b1.setHeight(60);

		String user = "";
		int points = 0;
		final String[] uname = new String[1000];
		final int[] uscore = new int[1000];
		for (int i = 0; i < 1000; i++) {
			uname[i] = "";
			uscore[i] = 0;
		}
		String fout = "";
		try {
			FileInputStream fIn = openFileInput("scores.txt");
			InputStreamReader isr = new InputStreamReader(fIn);
			char[] inputBuffer = new char[2000];
			isr.read(inputBuffer);
			String sco = new String(inputBuffer);
			isr.close();
			String latest="";
			try {
				FileInputStream ufIn = openFileInput("lastuser.txt");
				InputStreamReader uisr = new InputStreamReader(ufIn);
				char[] inputBuffer2 = new char[2000];
				uisr.read(inputBuffer2);
				latest = new String(inputBuffer2);
				uisr.close();
			} catch (IOException ioe) {
				System.err.println("IOException: " + ioe.getMessage());
			}
			int i = 0, k1 = 0, flag = 0;
			for (i = 0; i < sco.length(); i++) {
				char ch = sco.charAt(i);
				if (ch == '|') {
					flag = 1;
					continue;
				}
				if (ch == '$') {
					k1++;
					flag = 0;
					continue;
				}
				if (flag == 0)
					uname[k1] = uname[k1] + Character.toString(ch);
				else
					uscore[k1] = uscore[k1] * 10 + ((int) ch - 48);

			}

			i = 0;

			int tmpi;
			String tmps;
			for (i = 0; i < k1; i++)
				for (int j = 0; j < k1 - i; j++) {
					if (uscore[j] < uscore[j + 1]) {
						tmpi = uscore[j];
						tmps = uname[j];
						uname[j] = uname[j + 1];
						uscore[j] = uscore[j + 1];
						uname[j + 1] = tmps;
						uscore[j + 1] = tmpi;
					}
				}
			fout = "\nRANK\t\tNAME\t\t\tSCORE\n";
			for (i = 0; i < k1 && i < 10; i++) {
				fout += Integer.toString(i + 1) + "\t\t\t" + uname[i]
						+ "\t\t\t" + Integer.toString(uscore[i]) + "\n";
			}
			if (latest.length() > 2) {
				i = 0;
				while (latest.charAt(i) != '|')
					user += Character.toString(latest.charAt(i++));
				i++;
				while (latest.charAt(i) != '$')
					points = points * 10 + ((int) latest.charAt(i++) - 48);
				if (points < uscore[(k1 < 9) ? k1 : 9]) {
					int bad;
					for (bad = 0; bad < 1000; bad++) {
						if (points == uscore[bad])
							break;
					}
					fout += "\n" + (bad + 1) + "\t\t\t" + user + "\t\t"
							+ points + "\n";

				}
				File dir = getFilesDir();
				File file = new File(dir, "lastuser.txt");
				file.delete();
			}
			tv2.setText(fout);

		} catch (IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage());
		}
		b1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
						Winscr.this);

				alertDialog2.setTitle("Confirm");
				alertDialog2
						.setMessage("Are you sure you want to reset the High Scores?");

				alertDialog2.setPositiveButton("YES",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// Write your code here to execute after dialog
								File dir = getFilesDir();
								File file = new File(dir, "scores.txt");
								file.delete();

								Toast.makeText(getApplicationContext(),
										"HIGH SCORES CLEARED",
										Toast.LENGTH_SHORT).show();
								startActivity(new Intent(Winscr.this,
										Winscr.class));
							}
						});
				alertDialog2.setNegativeButton("NO",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}
						});

				alertDialog2.show();
			}
		});

	}

}
