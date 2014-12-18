package com.example.androidunit13_jsontest;

import java.util.List;

import net.tsz.afinal.FinalBitmap;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements android.widget.AdapterView.OnItemClickListener{

	private NewAdapter newadapter;
	private FinalBitmap bm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Ҫ����Final����  
		bm=FinalBitmap.create(this);
		ListView listview=(ListView) findViewById(R.id.simplelist);
		//�½�һ���࣬�����첽����,��listview����ȥ
		newadapter=new NewAdapter();
		NetUtil1.getDataFromServer(MainActivity.this,listview,newadapter);
		listview.setOnItemClickListener(this);
		
//		List<shop> list=new ArrayList<shop>();
//		shop s=new shop("��Ʒ1","������Ʒ","ͼƬ");
//		list.add(s);
//		NewAdapter newadapter=new NewAdapter(list);
//		listview.setAdapter(newadapter);
	}

	public class NewAdapter extends BaseAdapter{
	private List<shop> data;
	//������
	public List<shop> getData() {
		return data;
	}
	public void setData(List<shop> data) {
		this.data = data;
	}
	public NewAdapter(){
		super();
	}	
	public NewAdapter(List<shop> data) {
		this.data=data;
	}
	/**
	 * ͳ��item����,˵��listview�ж��ٸ���Ŀ
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}
	/**
	 * ˵��positionָ������Ŀ���������ݶ���
	 * position��Ŀid�ǹ̶��ġ�getItem�޷�����Ŀ��ָ�������ݶ���
	 */
	@Override
	public Object getItem(int position) {
		//���ﲻ��ȱ��������Ҳ�������
		return data.get(position);
	}
	/**
	 * ��Ŀ��id
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	/**
	 * View convertView��Ϊ����Ķ���
	 * ˵��ÿ����Ŀ�Ĳ���
	 * convertView �������Ŀ
	 * parent ����listView
	 * ����ֵ��ΪlistView��һ����Ŀ
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//��������
		shop s=data.get(position);
		//����convertView
		if(convertView==null){
		LayoutInflater inflater=LayoutInflater.from(MainActivity.this);
		//R.layout.simpleitem �����ļ�
		convertView=inflater.inflate(R.layout.simpleitem, null);
		}
		//Ҫ��convertView �Ҷ��󣬷���ر���ָ��
		ImageView iv_shop=(ImageView) convertView.findViewById(R.id.iv_pic);
		TextView tv_shopname=(TextView) convertView.findViewById(R.id.tv_book);
		TextView tv_shopsome=(TextView) convertView.findViewById(R.id.tv_name);
		
		//��ȡͼƬҪע�⣬��Ҫд��setId
		//setText������Ӵ����֣�Ҫ������ǰ����ַ���������Ӹ�����
		bm.display(iv_shop, s.getShoppic());
//		iv_shop.setImageResource(s.getShoppic());
		tv_shopname.setText(s.getShopname());
		tv_shopsome.setText(s.getShopsomething());
		return convertView;
	}
	
	
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		shop s=new shop();
		MyApplication M=(MyApplication) this.getApplication();
		List<shop> list=M.getList_shop();
		s=list.get(position);
		String a=s.getShopname();
		String b=s.getShoppic();
		String c=s.getShopsomething();
		Toast.makeText(this, "a="+a+"b="+b+"c="+c, Toast.LENGTH_SHORT).show();
	}
}
