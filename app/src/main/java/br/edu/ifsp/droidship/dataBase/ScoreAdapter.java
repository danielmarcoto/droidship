package br.edu.ifsp.droidship.dataBase;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.ifsp.droidship.R;

/**
 * Created by danielmarcoto on 16/05/16.
 */
public class ScoreAdapter extends BaseAdapter {

    private Activity context;
    private List<ScoreModel> list;

    public ScoreAdapter(Activity context, List<ScoreModel> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long)i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ScoreModel scoreModel = list.get(i);

        View item = context.getLayoutInflater().inflate(R.layout.score_list, null);
        TextView nameTextView = (TextView)item.findViewById(R.id.nameTextView);
        TextView scoreTextView = (TextView)item.findViewById(R.id.scoreTextView);

        String name = scoreModel.getName();
        //if (name.length() > 30)
        //    name = name.substring(0, 30) + "...";

        nameTextView.setText(name);
        scoreTextView.setText(scoreModel.getScore());
        return item;
    }
}
