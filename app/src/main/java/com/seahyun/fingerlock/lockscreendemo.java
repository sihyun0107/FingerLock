package com.seahyun.fingerlock;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class lockscreendemo extends AppCompatActivity implements OnClickListener {

	private static int password_size = 4;
	private static int count = 0;
	private int input_arr[] = new int[password_size];
	private int user_password[] = new int[password_size];
	private boolean select_mode = false;
	private int application_number = 0;


	ArrayList<String> arGeneral;
	List<ApplicationInfo> list;
	ArrayList<String> pacageNm;

	//List <ApplicationInfo> mPackageApps = new ArrayList<ApplicationInfo>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.screendemo);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
		for(int i=0; i<input_arr.length; i++){
			input_arr[i] = 0;
		}

		arGeneral = new ArrayList<String>();
		pacageNm = new ArrayList<String>();

		user_password[0] = 1;
		user_password[1] = 2;
		user_password[2] = 3;
		user_password[3] = 4;


		Button button1 = (Button) findViewById(R.id.button1);
		Button button2 = (Button) findViewById(R.id.button2);
		Button button3 = (Button) findViewById(R.id.button3);
		Button button4 = (Button) findViewById(R.id.button4);


		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);

		//check_application();


	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.button1:
				Toast.makeText(this, "1클릭", Toast.LENGTH_SHORT).show();
				if(select_mode == false){
					for(int i=0; i<input_arr.length; i++){
						if(input_arr[i]==0){
							input_arr[i] = 1;
							i = input_arr.length;
						}
					}
					count++;
					Log.d("count 값 : ", String.valueOf(count));
				}
				else{
					application_number = 1;
					execute_application(application_number);
				}
				break;

			case R.id.button2:
				Toast.makeText(this, "2클릭", Toast.LENGTH_SHORT).show();
				if(select_mode == false) {
					for (int i = 0; i < input_arr.length; i++) {
						if (input_arr[i] == 0) {
							input_arr[i] = 2;
							i = input_arr.length;
						}
					}
					count++;
					Log.d("count 값 : ", String.valueOf(count));
				}
				else{
					application_number = 2;
					execute_application(application_number);
				}
				break;

			case R.id.button3:
				Toast.makeText(this, "3클릭", Toast.LENGTH_SHORT).show();
				if(select_mode == false) {
					for (int i = 0; i < input_arr.length; i++) {
						if (input_arr[i] == 0) {
							input_arr[i] = 3;
							i = input_arr.length;
						}
					}
					count++;
					Log.d("count 값 : ", String.valueOf(count));
				}
				else{
					application_number = 3;
					execute_application(application_number);
				}
				break;

			case R.id.button4:
				Toast.makeText(this, "4클릭", Toast.LENGTH_SHORT).show();
				if(select_mode == false) {
					for (int i = 0; i < input_arr.length; i++) {
						if (input_arr[i] == 0) {
							input_arr[i] = 4;
							i = input_arr.length;
						}
					}
					count++;
					Log.d("count 값 : ", String.valueOf(count));
				}
				else{
					application_number = 4;
					execute_application(application_number);
				}
				break;
		}

		if(count == password_size){
			input_handler();
		}
	}

	public void input_handler(){
		int cnt=0;
		for(int i=0; i< input_arr.length; i++){
			if(user_password[i] == input_arr[i])
				Log.d("패스워드 값 비교 : ", "user : "+String.valueOf(user_password[i])+" input : "+String.valueOf(input_arr[i]));
				cnt++;
		}
		Log.d("cnt 값 : ", String.valueOf(cnt));
		if(cnt == password_size){
			Toast.makeText(this, "비밀번호가 동일합니다. 바로가기 원하는 어플 번호를 터치해주세요", Toast.LENGTH_SHORT).show();
			select_mode = true;
		}
		else{
			Toast.makeText(this, "비밀번호를 다시 입력해 주세요", Toast.LENGTH_SHORT).show();
		}
		count = 0;
	}

	public void execute_application(int num) {
		Toast.makeText(this, "선택한 어플리케이션 번호는 "+ String.valueOf(application_number), Toast.LENGTH_SHORT).show();
		PackageManager packageManager = this.getPackageManager();

		if(num == 1){
			getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

			Intent intent = packageManager.getLaunchIntentForPackage("com.kakao.talk");
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}
	}

	public void check_application(){
		Toast.makeText(this, "설치된 앱 확인", Toast.LENGTH_SHORT).show();

		final PackageManager pm = getPackageManager();
		List<ApplicationInfo> list = pm.getInstalledApplications(0);
		for (ApplicationInfo applicationInfo : list) {
			String name = String.valueOf(applicationInfo.loadLabel(pm));    // 앱 이름
			String pName = applicationInfo.packageName;   // 앱 패키지
			Log.d("설치된 앱 이름>> ", name);
			Log.d("설치된 앱 패키지>> ", pName);
		}

	}

}