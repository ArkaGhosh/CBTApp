package com.example.test;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	List<String> li;
	ListView list;
	int count;
	// ArrayList mSelectedItems = new ArrayList();
	int mSelectedItems = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		count = 0;
		final String[] patientResponseArray = { "P: I'll never make it here" };
		li = new ArrayList<String>();
		li.add("T: When is the last time you can remember you felt depressed?");
		li.add("P: Yesterday in the class");
		/*
		 * Toast.makeText( this,
		 * "Here the patient is not able to identify the NAT directly, so, we have to probe more. The technique used here is heightening the patient's emotional response"
		 * , Toast.LENGTH_LONG).show();
		 */

		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

		builder.setTitle("Eliciting a NAT")
				.setMessage(
						"The patient was sitting in a classroom and started feeling sad. Your job is to try to figure out what the patient was thinking at that time.")
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
					}
				});
		builder.create().show();

		final Button show = (Button) findViewById(R.id.button1);
		// final EditText et = (EditText) findViewById(R.id.editText1);
		list = (ListView) findViewById(R.id.listView1);

		add();
		final String[][] items = { { "T: What was going through your mind?",
				"asdfasdfa", "how u doin" } };
		show.setOnClickListener(new View.OnClickListener() {
			// mSelectedItems = new ArrayList();
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (count == items.length) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							MainActivity.this);
					builder.setTitle("Select the question")
							.setMessage(
									"You have identified a NAT. This is sort of the cardinal question used in CBT to elicit a NAT. Remember to ask this to yourself the next time you realize that you have started to feel sad. Unfortunately, this question doesn't always work. Some NATs are very elusive and needs further probing. Shall we go on to the next technique to elicit a NAT or do you need a few more examples of this sort?")
							.setPositiveButton("Next Technique",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog, int id) {
											Toast.makeText(MainActivity.this,
													"Great", Toast.LENGTH_SHORT)
													.show();
											Intent intent = new Intent(
													MainActivity.this,
													HeighteningResponse.class);
											MainActivity.this
													.startActivity(intent);
										}
									})
							.setNegativeButton("No, I need more practice",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog, int id) {
											Toast.makeText(MainActivity.this,
													"Coming right up",
													Toast.LENGTH_SHORT).show();
										}
									}).setCancelable(false);
					builder.create().show();
				} else {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							MainActivity.this);
					int a = 0;
					builder.setTitle("Select the question")
							.setSingleChoiceItems(
									items[count],
									a,
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											/*
											if (isChecked) {
												// If the user checked the item,
												// add
												// it to
												// the selected items
												// mSelectedItems.add(which);
												// questionSelected =
												// items[which];
												// mSelectedItems.add(which);
												
											}
											*/
											mSelectedItems = which;
										}
									})
							.setPositiveButton("Ok",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog, int id) {

											li.add(items[mSelectedItems + count][0]);
											li.add(patientResponseArray[count++]);
											add();
										}
									})
							.setNegativeButton("Cancel",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog, int id) {

										}
									}).setCancelable(false);
					builder.create().show();
				}

			}
		});
		// Toast.makeText(MainActivity.this,
		// "Reaching here "+count+" "+items.length, Toast.LENGTH_SHORT).show();

	}

	public void add() {
		ArrayAdapter<String> adp = new ArrayAdapter<String>(getBaseContext(),
				R.layout.list, li);
		list.setAdapter(adp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}