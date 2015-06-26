package com.appointphoto.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.example.appointphoto.R;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.special.ResideMenu.ResideMenu;

/**
 * User: special Date: 13-12-22 Time: 下午1:33 Mail: specialcyci@gmail.com
 */
public class HomeFragment extends Fragment {

	private View parentView;// ��������
	private ResideMenu resideMenu;// ����
	private PullToRefreshListView mPullRefreshListView;// ��ˢ��listview

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.homelistview, container, false);
		setUpViews();
		return parentView;
	}

	private void setUpViews() {
		MenuActivity parentActivity = (MenuActivity) getActivity();
		resideMenu = parentActivity.getResideMenu();

		// add gesture operation's ignored views ˮƽ�������������˵�����
		HorizontalScrollView ignored_view = (HorizontalScrollView) parentView
				.findViewById(R.id.header_ScrollView);
		resideMenu.addIgnoredView(ignored_view);

		// ��ʼ��ˢ��
		mPullRefreshListView = (PullToRefreshListView) parentView
				.findViewById(R.id.pull_refresh_list);
		// ��������ˢ��
		mPullRefreshListView.setMode(Mode.BOTH);
		ILoadingLayout startLabels = mPullRefreshListView.getLoadingLayoutProxy(true, false);
		startLabels.setPullLabel("�ͷ�ˢ��...");// ������ʱ����ʾ����ʾ
		startLabels.setRefreshingLabel("����ˢ��...");// ˢ��ʱ
		startLabels.setReleaseLabel("�ͷ�ˢ��...");// �����ﵽһ������ʱ����ʾ����ʾ

		ILoadingLayout endLabels = mPullRefreshListView.getLoadingLayoutProxy(false, true);
		endLabels.setPullLabel("���ظ���...");// ������ʱ����ʾ����ʾ
		endLabels.setRefreshingLabel("��������...");// ˢ��ʱ
		endLabels.setReleaseLabel("���ظ���...");// �����ﵽһ������ʱ����ʾ����ʾ
		mPullRefreshListView
				.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2() {

					@Override
					public void onPullDownToRefresh(
							PullToRefreshBase refreshView) {
						new PullRefresh().execute();
					}

					@Override
					public void onPullUpToRefresh(PullToRefreshBase refreshView) {
						new PullupGetMore().execute();
					}

				});
	}

	// ����ˢ��
	private class PullRefresh extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(1000);
				// �����ݿ��ȡ��������

			} catch (InterruptedException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			mPullRefreshListView.onRefreshComplete();
		}
	}

	// �������ظ���
	private class PullupGetMore extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(1000);
				// �����ݿ���ظ�������

			} catch (InterruptedException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			mPullRefreshListView.onRefreshComplete();
		}
	}
}
