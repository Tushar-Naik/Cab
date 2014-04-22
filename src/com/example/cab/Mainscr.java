package com.example.cab;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Mainscr extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button b1=(Button)findViewById(R.id.button1);
		Button b2=(Button)findViewById(R.id.button2);
		Button b3=(Button)findViewById(R.id.button3);
		Button b4=(Button)findViewById(R.id.button4);
		
		b1.setBackgroundColor(0xff66e5e5);
		b1.setTextColor(0xffffffff);
		b1.setTextSize(25);
		
		b2.setBackgroundColor(0xff55d5e5);
		b2.setTextColor(0xffffffff);
		b2.setTextSize(25);
		
		b3.setBackgroundColor(0xff44c5e5);
		b3.setTextColor(0xffffffff);
		b3.setTextSize(25);
		
		b4.setBackgroundColor(0xff33b5e5);
		b4.setTextColor(0xffffffff);
		b4.setTextSize(25);
		
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Mainscr.this,MainActivity.class));
			}
		});
		
		b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Mainscr.this,Winscr.class));			
			}
		});
		
		b3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Mainscr.this,Rules.class));			
			}
		});
		
		b4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
						Mainscr.this);

				alertDialog2.setTitle("Exit");
				alertDialog2
						.setMessage("Are you sure you want to exit?");

				alertDialog2.setPositiveButton("YES",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// Write your code here to execute after dialog
								System.exit(0);
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
