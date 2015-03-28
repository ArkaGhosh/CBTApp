package com.example.test;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
		final String[] patientResponseArray = { "P: Anxious, I think",
				"P: In the pit of my stomach.", "(Nods.)",
				"P: If I say something, it won’t come out right. People will judge me." };
		li = new ArrayList<String>();
		li.add("T: Sally, when you were thinking about volunteering in class, what was just going through your mind?");
		li.add("P: I’m not sure");
		Toast.makeText(
				this,
				"Here the patient is not able to identify the NAT directly, so, we have to probe more. The technique used here is heightening the patient's emotional response",
				Toast.LENGTH_LONG).show();
		final Button show = (Button) findViewById(R.id.button1);
		// final EditText et = (EditText) findViewById(R.id.editText1);
		list = (ListView) findViewById(R.id.listView1);

		add();
		final String[][] items = {
				{ "T: How were you feeling? ", "asdfasdfa", "how u doin" },
				{ "T: Where did you feel the anxiety?", "asdfasdfa",
						"how u doin" },
				{ "T: Can you feel the same feeling now?", "asdfasdfa",
						"how u doin" },
				{
						"T: So you’re sitting in class, thinking about volunteering, and you feel that anxiety in the pit of your stomach . . . What’s going through your mind?",
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
									"T: You have identified a NAT. So, can you remember this technique we just discussed to identify NAT when just asking the question \"What was going through my mind?\" doesn't work?")
							.setPositiveButton("Yes",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog, int id) {
										}
									})
							.setNegativeButton("No, I need more practice",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog, int id) {

										}
									});
					builder.create().show();
				} else {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							MainActivity.this);
					builder.setTitle("Select the question")
							.setMultiChoiceItems(
									items[count],
									null,
									new DialogInterface.OnMultiChoiceClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which, boolean isChecked) {
											if (isChecked) {
												// If the user checked the item,
												// add
												// it to
												// the selected items
												// mSelectedItems.add(which);
												// questionSelected =
												// items[which];
												// mSelectedItems.add(which);
												mSelectedItems = which;
											}
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
									});
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