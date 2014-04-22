package com.example.cab;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Rules extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.howto);
		TextView tv=(TextView)findViewById(R.id.textView1);
		String s="\t\tRULES\n\n1. The Computer generates a 3-digit number without repetitions.\n2. The User is required to guess the number in minimum tries.\n3. A Cow indicates there is a digit in the guessed number that is present out of position.\n   Eg. If the computer's number is 123 and user inputs the number 345, then the value of Cow would be 1 because 3 is present in the number but out of position.\n4. A Bull indicates there is a digit in the guessed number that is present in its exact position of the Computer's number.\n   Eg. If the computer's number is 123 and user inputs the number 145, then the value of Bull would be 1 because 1 is present in the number in exact position.\n5. 3 bulls indicate you've guessed the number correct.\n6. The Score is maximum for minimum tries.\n7. A timer starts immediately after the user presses start game button.\n8. The score varies inversely with time, i.e. the more time u take to guess the number, lesser will be your score.\n9. After you have guessed the number, your score will be displayed to you. You can choose to save your score or go back to the main menu.\n10. You are now ready to play.\n11. ALL THE BEST.";
		tv.setText(s);
		
	}

}
