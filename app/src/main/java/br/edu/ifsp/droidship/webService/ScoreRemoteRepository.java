package br.edu.ifsp.droidship.webService;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.droidship.dataBase.ScoreModel;

/**
 * Created by Fernanda-PC on 08/06/2016.
 */
public class ScoreRemoteRepository {

    //private static final String IP = "192.168.0.12"; //Fernanda
    private static final String IP = "192.168.0.128"; //Daniel

    public List<ScoreModel> listAll() {

        List<ScoreModel> list = new ArrayList<ScoreModel>();
        AcessoRest acessoRest = new AcessoRest();

        String chamadaWS;
        chamadaWS = "http://" + IP + ":8080/ScoreWebService/webresources/score/Score/get";

        String resultado = acessoRest.getScore(chamadaWS);
        Log.i("JSON", resultado);

        try {
            JSONArray json = new JSONArray(resultado);

            for(int i = 0; i < json.length(); i++)
            {
                JSONObject jo = json.getJSONObject(i);

                ScoreModel sm = new ScoreModel();
                sm.setName(jo.getString("nm_jogador"));
                sm.setScore(String.valueOf(jo.getInt("tot_pontuacao")));

                list.add(sm);
            }
        }
        catch(Exception ex){
            Log.i("JSON-ERROR", ex.getMessage());
        }

        return list;
    }

    public String insert(String nome, Integer score) {

        String chamadaWS;
        chamadaWS = "http://" + IP + ":8080/ScoreWebService/webresources/score/Score/post";
        String resultado = "";

        try{
            JSONObject json = new JSONObject();

            json.put("id_jogo", 1);
            json.put("nm_jogador", nome);
            json.put("tot_pontuacao", score);

            AcessoRest acessoRest = new AcessoRest();

            resultado = acessoRest.postScore(chamadaWS, json.toString());
            Log.i("JSONPOST", resultado);
        }
        catch(Exception ex){
            Log.i("JSON-ERROR", ex.getMessage());
        }

        return resultado;
    }

}
