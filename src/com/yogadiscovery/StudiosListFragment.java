package com.yogadiscovery;

import com.loopj.android.http.*;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;
import android.os.Bundle;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class StudiosListFragment extends ListFragment {
	private static final String TAG = "CrimeListFragment";
	AsyncHttpClient httpClient = new AsyncHttpClient();
	ArrayList<Studio> mStudios;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mStudios = StudiosFinder.get(getActivity()).getStudios();
		
		StudioAdapter adapter = new StudioAdapter(mStudios);
		setListAdapter(adapter);
		
		httpClient.get("http://yoganow-api.herokuapp.com/api/yoga_classes/nearby.json?lat=37.7750&lng=-122.4167", null, new JsonHttpResponseHandler() {
		    public void onSuccess(JSONObject response) {
		    	System.out.println(response);
		    	JSONArray studios = null;
		    	Integer length = 0;
				try {
					studios = response.getJSONArray("yoga_classes");
					length = studios.length();
				} catch (JSONException e) {
					e.printStackTrace();
				}
		    	JSONObject s = null;
		    	Studio studio;
				try {
					for (int i =0; i < length -1; i++) {
						s = (JSONObject) studios.get(i);
						studio = new Studio(s.getString("name"));
				    	((StudioAdapter)getListAdapter()).insert(studio,0);
		 		    	((StudioAdapter)getListAdapter()).notifyDataSetChanged();

					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
		    }
		});
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Studio studio = (Studio)(getListAdapter()).getItem(position);
		//Intent i = new Intent(getActivity(), StudioActivity.class);
		//i.putExtra(StudioFragment.EXTRA_CRIME_ID, studio.getId());
		//startActivity(i);
	}
	
	public class StudioAdapter extends ArrayAdapter<Studio> {
		public StudioAdapter(ArrayList<Studio> studios) {
			super(getActivity(), 0, studios);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_studio, null);
			}
			
			Studio studio = getItem(position);
			
			TextView titleTextView = (TextView)convertView.findViewById(R.id.studios_list_item_titleTextView);
			titleTextView.setText(studio.getName());				
			
			return convertView;
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		((StudioAdapter)getListAdapter()).notifyDataSetChanged();
	}
}
