package ca.klapstein.nklapste_feelsbook;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ModifyFeelDialog.OnSaveButtonClickListener {
    private static final String TAG = "MainActivity";
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new FeelsBookFragmentPagerAdapter(getSupportFragmentManager()));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * Get the active FeelTab from the MainActivities ViewPager.
     *
     * @return {@code FeelTab}
     */
    private FeelTab getFeelingsTab() {
        int index = mViewPager.getCurrentItem();
        FeelsBookFragmentPagerAdapter adapter = ((FeelsBookFragmentPagerAdapter) mViewPager.getAdapter());
        return (FeelTab) adapter.getFragment(index);
    }

    /**
     * Interface hooks to pass the result from ModifyFeelDialog back to the FeelTab.
     * <p>
     * This allows for inter-DialogFragment communication.
     *
     * @param feel {@code Feel}
     */
    @Override
    public void onSaveButtonClick(Feel feel) {
        FeelTab feelTab = getFeelingsTab();
        feelTab.addFeel(feel);
    }

    /**
     * Interface hooks to pass the result from ModifyFeelDialog back to the FeelTab.
     * <p>
     * This allows for inter-DialogFragment communication.
     *
     * @param feel     {@code Feel}
     * @param position {@code int}
     */
    @Override
    public void onSaveButtonClick(Feel feel, final int position) {
        FeelTab feelTab = getFeelingsTab();
        feelTab.editFeel(feel, position);
    }
}
