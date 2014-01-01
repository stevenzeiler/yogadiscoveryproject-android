package com.yogadiscovery;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class StudiosFinder {
  private ArrayList<Studio> mStudios;
  private static StudiosFinder sStudiosFinder;
  private Context mAppContext;
  
  private StudiosFinder(Context appContext) {
	  mAppContext = appContext;
	  mStudios = new ArrayList<Studio>();
  }
  
  public ArrayList<Studio> getStudios() {
	  return mStudios;
  }
  
  public Studio getStudio(UUID id) {
	  for(Studio studio : mStudios) {
		  if (studio.getId().equals(id)) {
			  return studio;
		  }
	  }
	  return null;
  }
  
  public static StudiosFinder get(Context c) {
	  if (sStudiosFinder== null) {
		  sStudiosFinder = new StudiosFinder(c.getApplicationContext());
	  }
	  return sStudiosFinder;
  }
}
