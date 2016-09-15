package com.study.gaijuui;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Naoga on 2016/08/01.
 */




//http通信用クラス
public class HttpGetTask implements Runnable {

    private URL url;
    String taskText;
    //URLのセッタ
    public void setURL(URL url1) {
        url = url1;
    }
    //通信結果の取得
    String getTaskText(){
        return htmlTagRemover(brReplacer(taskText));
    }

    @Override
    public void run() {
        //final StringBuilder result = new StringBuilder();
        try {
            //URL url = new URL("http://www.drk7.jp/weather/xml/27.xml");
            //コネクションの確保
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //ストリームから文字列型へ変換して格納
            taskText = InputStreamToString(con.getInputStream());
            //ログの出力
            Log.d("HTTP", taskText);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //ストリームから文字列へ変換するメソッド
    static String InputStreamToString(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
    //htmlタグを消去するメソッド
    static String htmlTagRemover(String str){
        return str.replaceAll("<.+?>","");
    }
    //<br>を「,」に変換するメソッド
    static String brReplacer(String str){
        return str.replaceAll("<br.+?",",");
    }
}
/*public class HttpGetTask extends AsyncTask<URL,Void,String> {
    @Override
    protected String doInBackground(URL... urls) {
        //取得したテキストを格納する変数
        final StringBuilder result = new StringBuilder();
        //アクセス先URL
        final URL url=urls[0];
        HttpURLConnection con =null;
        try{
            con=(HttpURLConnection)url.openConnection();
            con.connect();

            final int status=con.getResponseCode();
            if(status==HttpURLConnection.HTTP_OK){
                //通信成功
                //テキストの取得
                final InputStream in =con.getInputStream();
                final String encoding=con.getContentEncoding();
                final InputStreamReader inReader=new InputStreamReader(in,encoding);
                final BufferedReader bufReader=new BufferedReader(inReader);
                String line=null;
                //1行ずつテキストを読み込む
                while((line=bufReader.readLine())!=null){
                    result.append(line);
                }
                bufReader.close();
                inReader.close();
                in.close();
            }
        }catch (MalformedURLException e1){
            e1.printStackTrace();
        }catch (ProtocolException e1){
            e1.printStackTrace();
        }catch (IOException e1){
            e1.printStackTrace();
        }finally{
            if(con!=null){
                //コネクションを切断
                con.disconnect();
            }
        }
        return result.toString();
    }
    @Override
    protected void onPostExecute(String result){

    }
}*/

//別スレッドを立ててhttp通信(通信できる/mainスレッドへの値の受け渡し?)
            /*new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL("http://www.drk7.jp/weather/xml/27.xml");
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        str = InputStreamToString(con.getInputStream());
                        Log.d("HTTP", str);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast toast = Toast.makeText(GaijuActivity.this,barGraphData,Toast.LENGTH_LONG);
                            toast.show();
                        }
                    });
                }
            }).start();*/

/*private class HttpGetTask extends AsyncTask<URL,Void,String> {
        @Override
        protected String doInBackground(URL... urls) {
            try {
                final URL url=urls[0];
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                str = InputStreamToString(con.getInputStream());
                Log.d("HTTP", str);
            } catch (Exception ex) {
                System.out.println(ex);
            }
            return str;
        }
        @Override
        protected void onPostExecute(String str){
            barGraphData=str;
            Toast toast = Toast.makeText(GaijuActivity.this,str,Toast.LENGTH_LONG);
            toast.show();
        }
    }*/

//AsyncTask(非同期処理)を用いたhttp通信(未実装)
            /*try {
                new HttpGetTask().execute(new URL("http://www.drk7.jp/weather/xml/27.xml"));
            }catch (MalformedURLException e){
                e.printStackTrace();
            }*/

/*public class HttpGetTask implements Runnable{
    private URL url;
    String taskText;
    public void setURL(URL url1){
        url=url1;
    }
    @Override
    public void run() {
        try {
            //URL url = new URL("http://www.drk7.jp/weather/xml/27.xml");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            taskText = InputStreamToString(con.getInputStream());
            Log.d("HTTP", taskText);
        } catch (Exception ex) {
            System.out.println(ex);
        }
            /*handler.post(new Runnable() {
                @Override
                public void run() {
                    barGraphData=str;
                }
            });
    }

    static String InputStreamToString(InputStream is) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb=new StringBuilder();
        String line;
        while((line=br.readLine())!=null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}*/
