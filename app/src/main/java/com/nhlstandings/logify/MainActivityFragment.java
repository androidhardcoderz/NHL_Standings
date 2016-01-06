package com.nhlstandings.logify;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.json.JSONException;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private final String TAG = "Main Activity Fragment";
    private NestedScrollView scrollView;
    private LinearLayout conferenceLayout;

    LoadDivisions iLoadDivisions = new LoadDivisions() {
        @Override
        public void loadDivisions(Division division) {
            //load conference layout to main linear layout in scrollview
        }

        @Override
        public void loadConference(Conference conference) {
            //load single conference into linear layout
            System.out.println(conference.getName());
            conferenceLayout.addView(new ConferenceLayout(getActivity(),conference),conferenceLayout.getChildCount() - 1);
        }
    };

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        scrollView = (NestedScrollView) view.findViewById(R.id.nestedScrollView);
        conferenceLayout = (LinearLayout) view.findViewById(R.id.conferenceLayout);
    }

    public void updateStandings(String string){
        System.out.println(string);

        JSONParser parser = new JSONParser(iLoadDivisions);

        try{
            if(string == null || string.equals("")){
                parser.parseJsonData(SavedData.getJSONData(getActivity()));
            }else{
                parser.parseJsonData(string);
            }
        }catch(JSONException jse){
            jse.printStackTrace();
        }



    }
}
