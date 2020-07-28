package fu.prm391.sample.prm_krid.campaign_detail.campaigndetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fu.prm391.sample.prm_krid.R;

public class CampaignDetailViewFragment extends Fragment {

    LinearLayout title;
    LinearLayout content;
    Boolean isShow = false;

    public CampaignDetailViewFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_campaign_detail, container, false);
        title = view.findViewById(R.id.layoutInformationTitle);
        content = view.findViewById(R.id.layoutInformation);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShow) {
                    content.setVisibility(View.VISIBLE);
                    title.setBackgroundColor(getResources().getColor(R.color.gray));
                    isShow = true;
                } else {
                    content.setVisibility(View.GONE);
                    title.setBackgroundColor(getResources().getColor(R.color.white));
                    isShow = false;
                }
            }
        });
        return view;
    }
}
