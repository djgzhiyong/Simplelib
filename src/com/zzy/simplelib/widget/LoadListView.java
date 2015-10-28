package com.zzy.simplelib.widget;

import com.zzy.simplelib.utils.StringUtil;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadListView extends ListView implements OnScrollListener {

	public LoadListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public LoadListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public LoadListView(Context context) {
		super(context);
		init(context);
	}

	private LinearLayout loadLinearLayout;
	private TextView loadPullTextView;
	private ProgressBar mProgressBar;

	private int first, visible, total;
	private loadMoreCallback loadMoreCallback;
	private boolean isLoading;
	private boolean isMore = true;
	private TextView emptyTextView;

	private ListAdapter adapter;
	private String noDataLabel;

	private void init(Context context) {
		setOnScrollListener(this);
		initLoadView(context);
		initEmptyView(context);
	}

	private void initLoadView(Context context) {
		loadLinearLayout = new LinearLayout(context);
		loadLinearLayout.setGravity(Gravity.CENTER);

		mProgressBar = new ProgressBar(context);
		loadLinearLayout.addView(mProgressBar);

		loadPullTextView = new TextView(context);
		loadPullTextView.setText("加载中...");
		loadLinearLayout.addView(loadPullTextView);
	}

	private void initEmptyView(Context context) {
		emptyTextView = new TextView(context);
		emptyTextView.setTextColor(Color.BLACK);
		emptyTextView.setLayoutParams(new LayoutParams(
				android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
				android.widget.LinearLayout.LayoutParams.MATCH_PARENT));
		emptyTextView.setGravity(Gravity.CENTER);
	}

	@Override
	public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt) {
		// 如果当前正在装载、或者总数据小于一屏、或者没有更多数据 直接返回
		if (total == visible || isLoading || !isMore) {
			return;
		}
		// 如果当前停止滑动，并且滑动到底部 则加载更多
		if (paramInt == SCROLL_STATE_IDLE && (first + visible) == total) {
			if (loadMoreCallback != null) {
				isLoading = true;
				setLoadStatus(LOADING);
				loadMoreCallback.onLoad();
			}
		}
	}

	@Override
	public void onScroll(AbsListView paramAbsListView, int paramInt1,
			int paramInt2, int paramInt3) {
		first = paramInt1;
		visible = paramInt2;
		total = paramInt3;
	}

	/** 设置装载更多数据监听 */
	public void setLoadMoreListener(loadMoreCallback loadMoreCallback) {
		this.loadMoreCallback = loadMoreCallback;
	}

	/** 设置装载数据完成 */
	public void setLoadDone() {
		isLoading = false;
		if (total == visible) {
			removeFooterView(loadLinearLayout);
		}
	}

	/** 设置是否可以加载更多数据 */
	public void setCanLoadMore(boolean isMore) {
		this.isMore = isMore;
		if (!isMore) {
			setNullData();
		}
	}

	/** 设置是否可以加载更多数据 */
	public void setCanLoadMore(boolean isMore, String noDataLabel) {
		this.noDataLabel = noDataLabel;
		this.isMore = isMore;
		if (!isMore) {
			setNullData();
		}
	}

	/** 必须继承此方法,在设置适配之前添加页脚视图,否则在移除时报类型转换错误 **/
	@Override
	public void setAdapter(ListAdapter adapter) {
		isLoading = false;
		isMore = true;
		this.adapter = adapter;
		super.setAdapter(adapter);
		setNullData();
	}

	public void setNullData() {
		if (adapter == null || adapter.getCount() == 0) {
			isMore = false;
			setEmptyView();
			if (StringUtil.isEmpty(noDataLabel)) {
				emptyTextView.setText("当前暂无数据");
			} else {
				emptyTextView.setText(noDataLabel);
			}
			removeFooterView(loadLinearLayout);
		} else {
			if (total == visible) {
				loadPullTextView.setVisibility(View.GONE);
			} else {
				setLoadStatus(LOAD_COMPLETE);
			}
		}
	}

	private void setEmptyView() {
		ViewGroup parent = (ViewGroup) getParent();
		if (parent != null) {
			parent.removeView(emptyTextView);
			parent.addView(emptyTextView);
			setEmptyView(emptyTextView);
		}
	}

	public interface loadMoreCallback {
		public void onLoad();
	}

	private void setLoadStatus(int status) {
		switch (status) {
		case LOADING:
			if (getFooterViewsCount() == 0) {
				addFooterView(loadLinearLayout, null, false);
			} else {
				loadPullTextView.setVisibility(View.GONE);
			}
			break;
		case LOAD_COMPLETE:
			loadPullTextView.setVisibility(View.VISIBLE);
			loadPullTextView.setText("----- END -----");
			mProgressBar.setVisibility(View.GONE);
			break;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		return super.onTouchEvent(ev);
	}

	/** 装载中 */
	private static final int LOADING = 0x1;

	/** 加载完成 */
	private static final int LOAD_COMPLETE = 0x3;

}
