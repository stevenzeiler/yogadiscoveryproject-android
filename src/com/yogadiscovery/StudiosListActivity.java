package com.yogadiscovery;

import com.yogadiscovery.StudiosListFragment;
import com.yogadiscovery.SingleFragmentActivity;

public class StudiosListActivity extends SingleFragmentActivity {
	
	@Override
	protected android.support.v4.app.Fragment createFragment() {
		return new StudiosListFragment();
	}
}
