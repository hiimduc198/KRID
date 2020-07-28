package fu.prm391.sample.prm_krid.campaign_detail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import fu.prm391.sample.prm_krid.R;
import fu.prm391.sample.prm_krid.campaign_detail.adapter.ViewPagerAdapter;
import fu.prm391.sample.prm_krid.campaign_detail.campaigndetail.CampaignDetailViewFragment;
import fu.prm391.sample.prm_krid.campaign_detail.campaigndetail.InfluencerViewFragment;

public class CampaignDetail extends AppCompatActivity {

    private ViewPager viewpager;
    private TabLayout tabLayout;
    private NestedScrollView nestedScrollView;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_detail);
        anhxa();
        initViewPager();
        nestedScrollView.setFillViewport (true);
    }

    private void anhxa() {
        viewpager = findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayoutCampaign);
        nestedScrollView = findViewById(R.id.linearLayout1);
    }

    private void initViewPager() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CampaignDetailViewFragment());
        adapter.addFragment(new InfluencerViewFragment());
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.getTabAt(0).setText("Chi tiet chien dich");
        tabLayout.getTabAt(1).setText("Influencer");
    }
}
