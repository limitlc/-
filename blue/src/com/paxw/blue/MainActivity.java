package com.paxw.blue;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

import com.google.gson.Gson;
import com.paxw.blue.MainActivity.Bean.A;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.graphics.SumPathEffect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;



public class MainActivity extends Activity {
	private int column =2;

	private ListView listView;
	private List<A> list;
	private Bean bean;
	private int width;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.listview);
		WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
		width =wm.getDefaultDisplay().getWidth();
		readAssets();
		if (null!=bean) {
			Log.e("------", bean.getDataArr().size()+"");
			list = bean.getDataArr();
		}
		listView.setAdapter(new MyAdapter());
		
		
		
	}
	private void readAssets(){
		 try {
			InputStream is = getAssets().open("a");
			int size = is.available();
			byte[] buffer = new byte[size];  
			is.read(buffer);
			is.close();
			String content = new String(buffer, "utf-8");
			Gson gson = new Gson();
		
			bean = gson.fromJson(content, Bean.class);
		
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			if (list.size()%column==0) {
				return list.size()/column;
			}else {
				return list.size()/column+1;
			}
		}

		@Override
		public Object getItem(int position) {
			List<A> ls = new ArrayList<>();
			if (position*column+column>list.size()) {
				
				for (int i = 0; i <column; i++) {
					
					ls.add(list.get(position*column+i ));
				}
			}else {
				for (int i = position*column; i < list.size(); i++) {
					ls.add(list.get(i));
				}
			}
			
			
			return ls;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LinearLayout rootLayout;
			
			if (null==convertView) {
				rootLayout = (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.mylistitem_view, null);
			}else {
				rootLayout = (LinearLayout) convertView;
			}
			rootLayout.removeAllViews();
			float sumR = 0f;
			for (int i = 0; i < column; i++) {
				if (!(position*column+i<list.size())) {
					break;
				}
				A a = list.get(position*column+i);
				float h = Float.valueOf(a.getImageHeight());
				float w = Float.valueOf(a.getImageWidth());
				sumR+=(w/h);
				
			}
			int height = (int) (width/sumR);
			for (int i = 0; i < column; i++) {
				if (!(position*column+i<list.size())) {
					break;
				}
				A a = list.get(position*column+i);
				float h = Float.valueOf(a.getImageHeight());
				float w = Float.valueOf(a.getImageWidth());
				ImageView tempView = new ImageView(MainActivity.this);
				LayoutParams lp = new LayoutParams((int)(height*(w/h)), height);
				tempView.setLayoutParams(lp);
				Picasso.with(MainActivity.this).load(a.getImageUrl()).into(tempView);
				rootLayout.addView(tempView);
			}
			Log.e("----------", position+"");
			return rootLayout;
		}
		
	}
	
	
	class Bean{
		private List<A> dataArr;
		
		
		
		public List<A> getDataArr() {
			return dataArr;
		}



		public void setDataArr(List<A> dataArr) {
			this.dataArr = dataArr;
		}



		class A{
			private String itemId;
			private String index;
			private String largeImageUrl;
			private String itemType;
			private String title;
			
			private String color;
			private String gender;
			private String imageUrl;
			private String createTs;
			private String likdCount;
			private String style;
			private String season;
			private String isLiked;
			private String imageHeight;
			private String region;
			private String imageWidth;
			public String getItemId() {
				return itemId;
			}
			public void setItemId(String itemId) {
				this.itemId = itemId;
			}
			public String getIndex() {
				return index;
			}
			public void setIndex(String index) {
				this.index = index;
			}
			public String getLargeImageUrl() {
				return largeImageUrl;
			}
			public void setLargeImageUrl(String largeImageUrl) {
				this.largeImageUrl = largeImageUrl;
			}
			public String getItemType() {
				return itemType;
			}
			public void setItemType(String itemType) {
				this.itemType = itemType;
			}
			public String getTitle() {
				return title;
			}
			public void setTitle(String title) {
				this.title = title;
			}
			public String getColor() {
				return color;
			}
			public void setColor(String color) {
				this.color = color;
			}
			public String getGender() {
				return gender;
			}
			public void setGender(String gender) {
				this.gender = gender;
			}
			public String getImageUrl() {
				return imageUrl;
			}
			public void setImageUrl(String imageUrl) {
				this.imageUrl = imageUrl;
			}
			public String getCreateTs() {
				return createTs;
			}
			public void setCreateTs(String createTs) {
				this.createTs = createTs;
			}
			public String getLikdCount() {
				return likdCount;
			}
			public void setLikdCount(String likdCount) {
				this.likdCount = likdCount;
			}
			public String getStyle() {
				return style;
			}
			public void setStyle(String style) {
				this.style = style;
			}
			public String getSeason() {
				return season;
			}
			public void setSeason(String season) {
				this.season = season;
			}
			public String getIsLiked() {
				return isLiked;
			}
			public void setIsLiked(String isLiked) {
				this.isLiked = isLiked;
			}
			public String getImageHeight() {
				return imageHeight;
			}
			public void setImageHeight(String imageHeight) {
				this.imageHeight = imageHeight;
			}
			public String getRegion() {
				return region;
			}
			public void setRegion(String region) {
				this.region = region;
			}
			public String getImageWidth() {
				return imageWidth;
			}
			public void setImageWidth(String imageWidth) {
				this.imageWidth = imageWidth;
			}
			
			
		}
	}
	


}
