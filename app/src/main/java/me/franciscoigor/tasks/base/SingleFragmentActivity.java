package me.franciscoigor.tasks.base;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import me.franciscoigor.tasks.R;

public abstract class SingleFragmentActivity extends android.support.v4.app.FragmentActivity {



    public SingleFragmentActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        // Restore instance state
        Storage.loadStorage(savedInstanceState, this);
        // Setup database
        addSchemas();
        DatabaseHelper.getDatabase(this.getApplicationContext());
        //Load fragment content
        loadFragment(createFragment());

    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // Save instance
        Storage.saveStorage(savedInstanceState);
    }

    @Override
    protected void onStop() {
        Storage.saveStorage(null);
        super.onStop();
    }

    protected void loadFragment(Fragment newFragment){
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = newFragment;
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    protected abstract Fragment createFragment();

    protected abstract void addSchemas();

}
