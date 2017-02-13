package client.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.erics.tickettoride.R;

import java.util.ArrayList;
import java.util.List;

import shared.model_classes.GameLobby;

/**
 * Created by rebeccaredd on 2/12/17.
 */

public class AvaliableGamesAdapter extends BaseExpandableListAdapter{

        private Context context;

        public String[] headers = {"Avaliable Games"};

        ArrayList <GameLobby> availableGames = new ArrayList<>();

        public AvaliableGamesAdapter(Context context, List<GameLobby> availableGames)
        {
            this.context = context;

            //casting the list to an array list? shouldn't be a problem right??
            this.availableGames =(ArrayList) availableGames;
        }

        @Override
        public int getGroupCount() {
            return 2;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            if(groupPosition == 0)
            {return availableGames.size();}
            else
            {return 0;}
        }

        @Override
        public Object getGroup(int groupPosition) {return headers[groupPosition];}

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            if(groupPosition ==0)
            {return availableGames.get(childPosition);}
            else
            {return null;}
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            String headerTitle = (String) getGroup(groupPosition);
            if(convertView == null) {
                LayoutInflater inflaterInflater = LayoutInflater.from(this.context);
                convertView = inflaterInflater.inflate(R.layout.availableGamesHeader, null);
            }

            TextView labelListHeader = (TextView) convertView.findViewById(R.id.header);
            labelListHeader.setText(headerTitle);

            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if(groupPosition == 0)
            {
                GameLobby gameLobby = availableGames.get(childPosition);

                String line1 = gameLobby.getName().toString();

                int MaxPlayers = gameLobby.getMax_players();
                StringBuilder line2 = new StringBuilder();
                line2.append("Max number of players: ");
                line2.append(MaxPlayers);


                if(convertView == null) {
                    LayoutInflater inflaterInflater = LayoutInflater.from(this.context);
                    //another xml...
                    convertView = inflaterInflater.inflate(R.layout.avaliableGamesChild, null);
                }

                //find 3 items in xml...
                TextView label1 = (TextView) convertView.findViewById(R.id.line1);
                label1.setText(line1);

                TextView label2 = (TextView) convertView.findViewById(R.id.line2);
                label2.setText(line2.toString());

            }
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

}
