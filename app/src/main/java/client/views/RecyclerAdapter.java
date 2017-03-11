package client.views;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.erics.tickettoride.R;

import java.util.List;

import client.presenters.GameListPresenter;
import shared.model_classes.GameLobby;

/**
 * Created by rebeccaredd on 2/17/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.GameLobbyHolder> {

    private List<GameLobby> availableGames;
    ViewGroup p;
    TextView selectedGame;

    public static class GameLobbyHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public static TextView mGameName;
        TextView mGamePlayersNum;
        GameLobby mGameLobby;

        public GameLobbyHolder(View v)
        {
            super(v);
            mGameName = (TextView)v.findViewById(R.id.gameLobbyName);
            mGamePlayersNum = (TextView) v.findViewById(R.id.gameLobbyNumOfPlayers);
            v.setOnClickListener(this);
        }

        public void bindLobby(GameLobby gameLobby) {
            mGameLobby = gameLobby;
            mGameName.setText(gameLobby.getName());
            int playersInLobby = gameLobby.getPlayers().size();
            String s = "Players in this Game: "+ playersInLobby;
            mGamePlayersNum.setText(s);
        }

        @Override
        public void onClick(View view)
        {
            GameListView gameListView = GameListView.getInstance();
            gameListView.setSelectedGame(mGameLobby);
            mGameName.setTextColor(Color.RED);
        }
    }

    public RecyclerAdapter(List<GameLobby> myAvailableGames) {
        availableGames = myAvailableGames;
    }



    @Override
    public GameLobbyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        p = parent;
        return new GameLobbyHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(GameLobbyHolder holder, int position) {
        GameLobby itemLobby = availableGames.get(position);
        holder.bindLobby(itemLobby);
    }

    @Override
    public int getItemCount() {
        return availableGames.size();
    }

}
