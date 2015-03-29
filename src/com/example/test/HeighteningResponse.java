package com.example.test;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class HeighteningResponse extends ActionBarActivity {
	
	List<String> li;
	ListView list;
	int count;
	// ArrayList mSelectedItems = new ArrayList();
	int mSelectedItems = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_heightening_response);
		
		count = 0;
		final String[] patientResponseArray = { "P: Anxious, I think",
				"P: In the pit of my stomach.", "(Nods.)",
				"P: If I say something, it won’t come out right. People will judge me." };
		li = new ArrayList<String>();
		li.add("T: Sally, when you were thinking about volunteering in class, what was just going through your mind?");
		li.add("P: I’m not sure");
		
		AlertDialog.Builder builder = new AlertDialog.Builder(
				HeighteningResponse.this);
		builder.setTitle("Heightening the patient's Emotional Response ")
				.setMessage(
						"Here the patient is not able to identify the NAT directly, so, we have to probe more. The technique used here is heightening the patient's emotional response. Your job is to ask insightful questions and guide the patient to identify the NAT")
				.setPositiveButton("Ok",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(
									DialogInterface dialog, int id) {
							}
						});
		builder.create().show();
		
		final Button show = (Button) findViewById(R.id.button2);
		// final EditText et = (EditText) findViewById(R.id.editText1);
		list = (ListView) findViewById(R.id.listView2);

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
							HeighteningResponse.this);
					builder.setTitle("Select the question")
							.setMessage(
									"You have identified a NAT. Can you remember this technique we just discussed to identify NAT when just asking the question \"What was going through my mind?\" doesn't work?")
							.setPositiveButton("Next Technique",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog, int id) {
											Toast.makeText(HeighteningResponse.this, "Great", Toast.LENGTH_SHORT).show();
											Intent intent = new Intent(HeighteningResponse.this, DetailedDescription.class);
											HeighteningResponse.this.startActivity(intent);
										}
									})
							.setNegativeButton("No, I need more practice",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog, int id) {
											Toast.makeText(HeighteningResponse.this, "Coming right up", Toast.LENGTH_SHORT).show();
										}
									}).setCancelable(false);
					builder.create().show();
				} else {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							HeighteningResponse.this);
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
		getMenuInflater().inflate(R.menu.heightening_response, menu);
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
