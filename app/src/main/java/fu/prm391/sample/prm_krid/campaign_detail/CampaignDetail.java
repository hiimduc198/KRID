package fu.prm391.sample.prm_krid.campaign_detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;

import fu.prm391.sample.prm_krid.R;
import fu.prm391.sample.prm_krid.campaign_detail.adapter.ViewPager;

public class CampaignDetail extends AppCompatActivity {


    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_detail);
    }


    private void initViewPager(){
        viewPager = new ViewPager(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

    }
}
