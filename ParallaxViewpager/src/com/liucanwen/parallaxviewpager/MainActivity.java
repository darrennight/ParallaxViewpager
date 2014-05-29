package com.liucanwen.parallaxviewpager;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * �򵥹����Ӳ����ҳ��
 * 
 * @author HalfmanHuang
 * @since SDK19 JDK7
 * @version 1.0.0
 */
public class MainActivity extends Activity {
	/** viewpager���� */
	private ViewPager pager;
	/** viewpager���������� */
	private SimpleAdapter adapter;
	/** ˮƽ���������󣨱���ͼƬ�������� */
	private HorizontalScrollView scroll;
	/** ����ͼƬ��ͼ */
	private ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ���ò���
		setContentView(R.layout.activity_main);
		// ��ȡ���������ʵ��
		pager = (ViewPager) findViewById(R.id.pager);
		scroll = (HorizontalScrollView) findViewById(R.id.scroll);
		image = (ImageView) findViewById(R.id.image);
		// ʵ����������
		adapter = new SimpleAdapter(this);

		// ��Ӳ�������
		addTestData();

		// ����������������viewpager
		pager.setAdapter(adapter);
		// ����viewpager�Ĺ�������
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				// ҳ�汻ѡ���ǵ���
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// viewpager����ʱ������

				// pager������ҳ����ܿ��
				float widthOfPagers = pager.getWidth() * adapter.getCount();
				// ����ͼƬ�Ŀ��
				float widthOfScroll = image.getWidth();

				// ViewPager�ɻ������ܳ���
				float moveWidthOfPagers = widthOfPagers - pager.getWidth();
				// ����ͼ�Ŀɻ����ܳ���
				float moveWidthOfScroll = widthOfScroll - pager.getWidth();

				// �ɻ����������
				float ratio = moveWidthOfScroll / moveWidthOfPagers;
				// ��ǰPager�Ļ�������
				float currentPosOfPager = arg0 * pager.getWidth() + arg2;

				// ������������Ӧλ��
				scroll.scrollTo((int) (currentPosOfPager * ratio),
						scroll.getScrollY());
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// ����״̬�ı�ʱ������
			}
		});
	}

	/**
	 * ��Ӳ�������
	 */
	private void addTestData() {
		for (int i = 1; i <= 10; i++) {
			adapter.addPager("��" + i + "��ҳ��");
		}
	}

	/**
	 * ҳ��viewpager��������
	 */
	public class SimpleAdapter extends PagerAdapter {
		/** ��ҳ���б�ע��˴�����Ϊҳ�������� */
		public ArrayList<SimpleHolder> views;
		/** ���ַ����� */
		private LayoutInflater inflater;

		public SimpleAdapter(Context context) {
			// ʵ�����б�
			views = new ArrayList<SimpleHolder>();
			// ͨ��ϵͳ�����ȡ���ַ�����ʵ��
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			// ������ҳ������
			return views.size();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// ���ٴ���
			container.removeView(views.get(position).root);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// ��ҳ�����ɴ���
			container.addView(views.get(position).root);
			return views.get(position).root;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// �ж����ɴ����ص�object��ĳ����ҳ��Ĺ�ϵ
			return arg0 == arg1;
		}

		/**
		 * ���һ����ҳ��
		 */
		public void addPager(String text) {
			// ����һ����ҳ�����������
			SimpleHolder holder = new SimpleHolder();
			// ͨ�����ַ�������pageitem_simple��ȡ���ָ���ͼ����
			holder.root = inflater.inflate(R.layout.pageitem, null);
			// ͨ������ͼ�����ȡ�����textview����ͼ
			holder.text = (TextView) holder.root.findViewById(R.id.text);
			// Ϊtextview��������
			holder.text.setText(text);
			// ����ҳ���б��Ѿ���ʼ��
			if (null != views) {
				// ������ҳ������������
				views.add(holder);
			}
		}
	}

	/**
	 * ҳ��������
	 */
	public class SimpleHolder {
		/** ����ͼ���� */
		public View root;
		/** ͣ���ڸ���ͼ�ײ���textview���� */
		public TextView text;
	}
}
