package br.edu.ifsp.droidship.webService;

import android.os.StrictMode;
import android.util.Log;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by Fernanda-PC on 08/06/2016.
 */
public class AcessoRest {

    //private int  TIMEOUT_MILLISEC = 3000;

    public String getScore(String url)
    {

        HttpClient httpclient = new DefaultHttpClient();
        HttpGet chamadaget = new HttpGet(url);
        String retorno = "";

        try {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(chamadaget,
                    responseHandler);

            retorno = responseBody;

        }
        catch (ClientProtocolException e) {
            e.printStackTrace();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Throwable t) {
            Log.i("erro", t.toString());
        }

        return retorno;
    }

    public String postScore(String url, String json) {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPOST = new HttpPost(url);
        String retorno = "";

        try {
            StringEntity se = new StringEntity(json);
            httpPOST.setEntity(se);
            httpPOST.setHeader("Accept", "application/json");
            httpPOST.setHeader("Content-type", "application/json");

            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(httpPOST,
                    responseHandler);

            retorno = responseBody;

            /*
            HttpResponse httpResponse = httpclient.execute(httpPOST);
            InputStream inputStream = httpResponse.getEntity().getContent();

            if(inputStream != null)
                retorno = convertStreamToString(inputStream);*/

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return retorno;
    }
}
