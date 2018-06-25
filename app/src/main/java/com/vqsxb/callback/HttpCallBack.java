package com.vqsxb.callback;

import org.xutils.common.Callback.CommonCallback;


public abstract class HttpCallBack<ResultType> implements CommonCallback<ResultType> {

	@Override
	public void onCancelled(CancelledException arg0) {
		
	}

	@Override
	public void onFinished() {
		// TODO Auto-generated method stub
	}
}
