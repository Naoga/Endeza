package com.study.gaijuui;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.app.WindowDecorActionBar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Application;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.TypefaceProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Future;

public class GaijuActivity extends Activity implements View.OnClickListener {
    //widgetを格納するメンバ変数の宣言
    private BootstrapButton mBut_Data;
    private BootstrapButton mBut_Conf;
    private Button mButton;
    private TextView mUsrtxt;
    //private EditText mTestText ;
    //OnCreate()内に動作を記述する

    //トグルボタンの状態を保持するフィールド
    boolean tgsw_flagnotice;
    boolean tgsw_flagsave;
    //ユーザ名の初期値はnull
    String userName = null;
    //String taskText;
    String barGraphData,pictTmp;
    String[] notifArray=new String[2];
    int count=0;
    int routinHttpConFlag;
    int notifFlag;

    static HttpGetTask task_t = new HttpGetTask();

    Handler handler = new Handler();

    private final GaijuActivity self=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaiju);
        //遷移元のインテントを取得
        Intent intent = getIntent();
        //通知用トグルボタンの状態を取得
        tgsw_flagnotice = intent.getBooleanExtra("tgsw_flagnotice", true);
        //省電力用トグルボタンの状態を取得
        tgsw_flagsave = intent.getBooleanExtra("tgsw_flagsave", false);
        //ユーザ名を取得
        userName = intent.getStringExtra("strUsr");
        //userName=intent.getStringExtra("strUsr");

        routinHttpConFlag=intent.getIntExtra("routinHttpConFlag",0);
        //写真退避用文字列を取得
        pictTmp=intent.getStringExtra("pictTmp");
        //初期値を与える
        notifArray[1]="";
        //routinHttpConFlag=1;
        //定期通信用クラスのインスタンスを生成
        Timer timer1 = new Timer();
        //第3引数ミリ秒間隔で通信する
        timer1.schedule(new RoutineHttpCon(), 0, 60000);
        /*String testText="<html><body>false<br>http://40.74.135.223/pic/3/3/1472012650971.jpg<br></body></html>";
        String tmpText=brReplacer(testText);
        String noHtmlText=htmlTagRemover(tmpText);
        String[] testArray=noHtmlText.split(",",0);
        for(int i=0;i<testArray.length;i++)
            System.out.println(i+"番目の要素は"+testArray[i]);*/

        //イノシシ画像の出力
        /*ImageView imageView1 = (ImageView) findViewById(R.id.image_view_1);
        Uri uri = Uri.parse(testArray[1]);
        Uri.Builder builder = uri.buildUpon();
        HttpGetPict task = new HttpGetPict(imageView1);
        task.execute(builder);*/

        /*Timer timer1=new Timer();
        timer1.schedule(new RoutineHttpCon(),0,5000);*/

        /*try {
            //画像データの取得
            InputStream istream = getResources().getAssets().open("shishi_1.bmp");
            Bitmap bitmap = BitmapFactory.decodeStream(istream);
            imageView1.setImageBitmap(bitmap);
        } catch (IOException e) {//例外(エラー)処理
            Log.d("Asetts", "Error");
        }*/

        //絶対パスから画像を表示する
        /*File imgfile = new File("D:\\tnct\\shishi_1.bmp");
        if(imgfile.exists()) {

            Bitmap imgBitmap = BitmapFactory.decodeFile(imgfile.getAbsolutePath());

            imageView1.setImageBitmap(imgBitmap);
        }*/

        //ロゴの出力
        ImageView imageView2 = (ImageView) findViewById(R.id.image_view_2);
        try {
            InputStream istream = getResources().getAssets().open("hatakemamoru.jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(istream);
            imageView2.setImageBitmap(bitmap);
        } catch (IOException e) {//例外(エラー)処理
            Log.d("Asetts", "Error");
        }

        /*String path="src/main/assets/logo.png";
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        Bitmap bmp=BitmapFactory.decodeFile(path,options);
        int height=options.outHeight;
        options.inSampleSize=height/600;

        options.inJustDecodeBounds=false;
        bmp=BitmapFactory.decodeFile(path,options);
        imageView2.setImageBitmap(bmp);*/

        //メンバ変数の初期化
        mBut_Data = (BootstrapButton) findViewById(R.id.but_data);
        mBut_Conf = (BootstrapButton) findViewById(R.id.but_conf);
        mUsrtxt = (TextView) findViewById(R.id.Usrtxt);
        mButton = (Button) findViewById(R.id.button1);
        //mTestText=(EditText) findViewById(R.id.TestText);
        //OnClick()のリスナ設定
        mBut_Conf.setOnClickListener(this);
        mBut_Data.setOnClickListener(this);
        mButton.setOnClickListener(this);

        /*String test=mBut_Data.getText().toString();
        System.out.println(test);*/

        //ユーザ名の表示
        if (userName == null) {
            mUsrtxt.setText("設定画面でユーザ名を\n入力してください");
        } else {
            mUsrtxt.setText("あなたのユーザ名\n「 " + userName + " 」");
        }
    }


    //インターフェイスの実装 OnClickListner()
    @Override
    public void onClick(View v) { //vはどのViewが押されたかを受け取る
        if (v.equals(mBut_Data)) { //データボタンが押されたときの動作
            /*Timer timer1=new Timer();
            timer1.schedule(new RoutineHttpCon(),0,60000);*/
            //http通信用のインスタンス生成
            HttpGetTask task = new HttpGetTask();
            //URLを指定
            try {
                //task.setURL(new URL("http://www.drk7.jp/weather/xml/27.xml"));
                task.setURL(new URL("http://40.74.135.223:8080/test/mainServlet?from=0&requestID=1&Username="+userName));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            //http通信スレッドを立てる
            Thread thread = new Thread(task);
            //立てたスレッドを起動
            thread.start();
            try {
                //http通信が完了するまで待機する
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*Toast toast = Toast.makeText(GaijuActivity.this, taskText, Toast.LENGTH_LONG);
            toast.show();*/
            //通信結果はtaskTextに格納される
            /*String tmpText=brReplacer(taskText);
            //System.out.println(tmpText);
            String noHtmlText=htmlTagRemover(tmpText);
            barGraphData = noHtmlText;
            System.out.println(barGraphData);*/
            /*String[] strArray=taskText.split("\n",0);
            for(int i=0;i<strArray.length;i++)
                System.out.println("要素番号 "+i+" の時"+strArray[i]);*/
            /*String testStr="a\nba\ncy";
            String[] testArray=testStr.split("\n",0);
            for(int i=0;i<testArray.length;i++)
                System.out.println("要素番号"+i+" の時："+testArray[i]);*/


            /*try {
                new HttpGetTask().execute(new URL("http://40.74.135.223:8080/test/mainServlet?from=0&requestID=0&Username=root"));
            }catch (MalformedURLException e){
                e.printStackTrace();
            }*/

            //mTestText.setText("button push");　buttonデバッグ用
            //System.out.println(taskText);
            //task.getTaskText(barGraphData);
            //通信結果(グラフデータ)を取得
            barGraphData=task.getTaskText();
            //グラフ画面へのインテントを生成
            Intent intent = new Intent(this, DataActivity.class);
            //遷移先へ持ち込むパラメータの指定
            intent.putExtra("strUsrfg", userName);
            intent.putExtra("tgsw_flagnotice", tgsw_flagnotice);
            intent.putExtra("barGraphData", barGraphData);
            intent.putExtra("pictTmp",pictTmp);
            //データ画面へと遷移
            startActivity(intent);
        } else if (v.equals(mBut_Conf)) {//設定ボタンが押されたときの動作
            //トグルボタンの状態確認(デバッグ)
            /*Toast toast = Toast.makeText(GaijuActivity.this,"flagnotice's logic is"+tgsw_flagnotice,Toast.LENGTH_LONG);
            toast.show(); //デバッグ用のトースト*/
            //設定画面へのインテントを生成
            Intent intent = new Intent(this, ConfActivity.class);
            //設定画面へ持ち込むパラメータの指定
            intent.putExtra("tgsw_flagnotice", tgsw_flagnotice);
            intent.putExtra("tgsw_flagsave", tgsw_flagsave);
            intent.putExtra("strUsr", userName);
            intent.putExtra("routinHttpConFlag",routinHttpConFlag);
            intent.putExtra("pictTmp",pictTmp);
            //遷移する
            startActivity(intent);
        } else if (v.equals(mButton)) {//デバッグ用ボタン
            /*Toast toast = Toast.makeText(GaijuActivity.this, "button1が押されたよ", Toast.LENGTH_LONG);
            toast.show();*/
            if(tgsw_flagnotice==true) {
                notification();
                startService(new Intent(self, TimerIntentService.class));
            }
        }
    }

    //通知関数
    void notification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //クリックした時にintentを発行
        Intent notificationIntent = new Intent(this, GaijuActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_hatake_web) // アイコン
                .setTicker("Hello") // 通知バーに表示する簡易メッセージ
                .setWhen(System.currentTimeMillis()) // 時間
                .setContentTitle("通知") // 展開メッセージのタイトル
                .setContentText("害獣の出現を確認しました") // 展開メッセージの詳細メッセージ
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(contentIntent) // PendingIntent
                .build();
        //ダイアログの実装(不必要)
        AlertDialog.Builder ab =new AlertDialog.Builder(GaijuActivity.this);
        ab.setTitle("通知");
        ab.setMessage("害獣の出現を確認しました");
        ab.setPositiveButton("閉じる", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        //ab.create().show();
        notificationManager.notify(1, notification);
    }

    //取得文字列をStringに変換する関数
    /*static String InputStreamToString(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    static String htmlTagRemover(String str){
        return str.replaceAll("<.+?>","");
    }

    static String brReplacer(String str){
        return str.replaceAll("<br.+?",",");
    }

    //http通信用クラス
    public class HttpGetTask implements Runnable {

        private URL url;

        public void setURL(URL url1) {
            url = url1;
        }

        @Override
        public void run() {
            final StringBuilder result = new StringBuilder();
            try {
                //URL url = new URL("http://www.drk7.jp/weather/xml/27.xml");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                taskText = InputStreamToString(con.getInputStream());
                //Log.d("HTTP", taskText);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }*/

    //定期通信用クラス
    //HttpGetTaskインスタンスを生成しhttp通信を行う
    public class RoutineHttpCon extends TimerTask{
        @Override
        public void run() {
            count++;
            System.out.println(count+": Hello");
            //HttpGetTask task_t = new HttpGetTask();
            //URLを指定
            try {
                task_t.setURL(new URL("http://40.74.135.223:8080/test/mainServlet?from=0&requestID=0&Username="+userName));
                //task_t.setURL(new URL("http://inoshishi.etc64.com/image/inoshishi04.jp"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            //http通信スレッドを立てる
            Thread thread = new Thread(task_t);
            //立てたスレッドを起動
            thread.start();
            try {
                //http通信が完了するまでメインスレッドを停止する
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //通信結果(通知の決定と写真URL)を取得
            String notifText=task_t.getTaskText();
            //配列化
            notifArray=notifText.split(",",0);
            System.out.println(notifArray[0]);
            System.out.println(notifArray[1]);
            //サーバからの取得文字列がtrue・通知を設定しているなら
            if(notifArray[0].equals("true") && tgsw_flagnotice==true) {
                //notifFlag=1;
                //ポップアップ、ダイアログ通知
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        notification();
                        startService(new Intent(self,TimerIntentService.class));
                    }
                });
            }else{
                //退避した写真を戻す
                //notifArray[1]=pictTmp;
            }
            //写真表示
            //notifArray[1] = "http://cdn-ak.f.st-hatena.com/images/fotolife/f/fjswkun/20150927/20150927140905.jpg";
            ImageView imageView1 = (ImageView) findViewById(R.id.image_view_1);
            Uri uri = Uri.parse(notifArray[1]);
            /*Uri uri;
            if(count%2==0) {
                uri = Uri.parse("http://inoshishi.etc64.com/image/inoshishi04.jpg");
            }else{
                uri=Uri.parse("");
            }*/
            Uri.Builder builder = uri.buildUpon();
            HttpGetPict task_p = new HttpGetPict(imageView1);
            task_p.execute(builder);
            //System.out.println(taskText);

            //String notifText=htmlTagRemover(brReplacer(taskText));
            /*String notifText=task_t.getTaskText();
            /*String notifText="";
            task_t.getTaskText(notifText);*/

            //現状で表示している写真を退避
            //pictTmp=notifArray[1];
            //配列化
            /*notifArray=notifText.split(",",0);
            System.out.println(notifArray[0]);
            System.out.println(notifArray[1]);
            //サーバからの取得文字列がtrue・通知を設定しているなら
            if(notifArray[0].equals("true") && tgsw_flagnotice==true) {
                //notifFlag=1;
                //ポップアップ、ダイアログ通知
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        notification();
                    }
                });
            }else{
                //退避した写真を戻す
                //notifArray[1]=pictTmp;
            }
            //写真表示
            //notifArray[1] = "http://cdn-ak.f.st-hatena.com/images/fotolife/f/fjswkun/20150927/20150927140905.jpg";
            ImageView imageView1 = (ImageView) findViewById(R.id.image_view_1);
            Uri uri = Uri.parse(notifArray[1]);
            /*Uri uri;
            if(count%2==0) {
                uri = Uri.parse("http://inoshishi.etc64.com/image/inoshishi04.jpg");
            }else{
                uri=Uri.parse("");
            }*/
            /*Uri.Builder builder = uri.buildUpon();
            HttpGetPict task_p = new HttpGetPict(imageView1);
            task_p.execute(builder);*/
        }
    }
            /*handler.post(new Runnable() {
                @Override
                public void run() {
                    barGraphData=str;
                }
            });*/
                /*con = (HttpURLConnection) url.openConnection();
                int status = con.getResponseCode();
                if(status==HttpURLConnection.HTTP_OK) {
                    final InputStream in = con.getInputStream();
                    final String encoding = con.getContentEncoding();
                    final InputStreamReader inReader = new InputStreamReader(in);
                    final BufferedReader bufReader = new BufferedReader(inReader);
                    String line = null;
                    while ((line = bufReader.readLine()) != null) {
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
            }finally {
                if(con!=null){
                    con.disconnect();
                }
            }
            System.out.println(result);
        }
    }*/

    /*public final class HttpGetTask extends AsyncTask<URL,Void,String> {
        @Override
        protected String doInBackground(URL... urls) {
            //取得したテキストを格納する変数
            //final StringBuilder result = new StringBuilder();
            StringBuilder result=new StringBuilder();
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
                    InputStreamReader inReader=new InputStreamReader(in);
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
            barGraphData=result;
            System.out.println(barGraphData);
        }
    }*/
}
