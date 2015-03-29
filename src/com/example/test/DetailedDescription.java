package com.example.test;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class DetailedDescription extends ActionBarActivity {

	List<String> li;
	ListView list;
	int count;
	// ArrayList mSelectedItems = new ArrayList();
	int mSelectedItems = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detailed_description);

		count = 0;
		final String[] patientResponseArray = {
				"P: I don’t know. I was just feeling so down, sad",
				"P: It was about 6:15. I had just gotten back from dinner. The dorm was pretty empty because I ate early. I was about to get my books out of my backpack so I could do my Chem homework . . .",
				"P: This is just too hard. I’ll never understand it."};
		li = new ArrayList<String>();
		li.add("T: So, you were alone in your room last night and you began feeling really upset?");
		li.add("P: Yes");

		AlertDialog.Builder builder = new AlertDialog.Builder(
				DetailedDescription.this);
		builder.setTitle("Eliciting a detailed description")
				.setMessage(
						"Here again the patient is not able to identify the NAT directly, so, we have to probe more. The technique used here is eliciting a detailed response to help jog the patient's memory. Your job is to ask insightful questions and guide the patient to identify the NAT")
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
					}
				});
		builder.create().show();

		final Button show = (Button) findViewById(R.id.button3);
		// final EditText et = (EditText) findViewById(R.id.editText1);
		list = (ListView) findViewById(R.id.listView3);

		add();
		final String[][] items = {
				{ "T: What was going through your mind?", "asdfasdfa",
						"how u doin" },
				{
						"T: Can you describe the scene for me? What time was it? Were you alone? What were you doing? What else was going on?",
						"asdfasdfa", "how u doin" },
				{
						"T: So you were about to do your homework and you were thinking . . .",
						"asdfasdfa", "how u doin" }};
		show.setOnClickListener(new View.OnClickListener() {
			// mSelectedItems = new ArrayList();
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (count == items.length) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							DetailedDescription.this);
					builder.setTitle("Select the question")
							.setMessage(
									"You have identified a NAT. Can you remember this technique we just discussed to identify NAT when just asking the question \"What was going through my mind?\" doesn't work?")
							.setPositiveButton("Yes",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog, int id) {
											Toast.makeText(
													DetailedDescription.this,
													"Great", Toast.LENGTH_SHORT)
													.show();

										}
									})
							.setNegativeButton("No, I need more practice",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog, int id) {
											Toast.makeText(
													DetailedDescription.this,
													"Coming right up",
													Toast.LENGTH_SHORT).show();
										}
									}).setCancelable(false);
					builder.create().show();
				} else {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							DetailedDescription.this);
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
	}

	public void add() {
		ArrayAdapter<String> adp = new ArrayAdapter<String>(getBaseContext(),
				R.layout.list, li);
		list.setAdapter(adp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detailed_description, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
