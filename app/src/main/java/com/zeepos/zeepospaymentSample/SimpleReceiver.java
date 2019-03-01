package com.zeepos.zeepospaymentSample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

@SuppressWarnings("rawtypes")
public abstract class SimpleReceiver<T> extends BroadcastReceiver {
	private static final Map<Context, List<SimpleReceiver>> receiverMap = new WeakHashMap<Context, List<SimpleReceiver>>();
	private static String DATA = "DATA";
	protected transient WeakReference<Context> ctx = new WeakReference<Context>(null);
	protected String key;

	public SimpleReceiver() {
		super();
	}
	
	public SimpleReceiver(Context ctx, String key) {
		register(ctx, key);
	}
	

	public SimpleReceiver(String key) {
		this.key = key;
	}
	

	public void register(Context ctx){
		if(this.ctx.get() == ctx)	return;
		if(this.ctx.get() != null)	unregister();
		register(ctx, key);
	}

	public void register(Context ctx, String key){
		this.ctx = new WeakReference<Context>(ctx);
		this.key = key;
		LocalBroadcastManager.getInstance(ctx).registerReceiver(this, new IntentFilter(key));
		List<SimpleReceiver> item = receiverMap.get(ctx);
		if(item == null){
			item = new ArrayList<SimpleReceiver>();
			receiverMap.put(ctx, item);
		}
		item.add(this);
	}
	
	public static void unregister(Context ctx){
		List<SimpleReceiver> item = receiverMap.get(ctx);
		if(item != null){
			while(!item.isEmpty())	item.get(0).unregister();
		}
	}
	
	public void unregister(){
		if(ctx.get() != null){
			LocalBroadcastManager.getInstance(ctx.get()).unregisterReceiver(this);
			List<SimpleReceiver> item = receiverMap.get(ctx.get());
//		Log.w("SR:"+key, "UNREGISTER");
			if(item != null)	item.remove(this);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle b = intent.getExtras();
		Serializable obj = b.getSerializable(DATA);
		Log.w("SR:"+key+":R:", "S:"+obj);
		try{
			if(obj != null){
				onReceive((T) obj);
			} else {
				Parcelable parcel = b.getParcelable(DATA);
				if(parcel != null){
					Log.w("SR:"+key+":R:", "P:"+parcel);
					onReceive((T) parcel);
				}
			}
		}catch (Exception e){}
	}

	public Context getContext(){
		return ctx.get();
	}
	
	public static <A extends Parcelable> boolean broadcast(Context ctx, String key, A obj){
		Bundle b= new Bundle();
		b.putParcelable(DATA, (Parcelable) obj);
		Log.w("SR:"+key+":S:", "P:"+obj);
		return LocalBroadcastManager.getInstance(ctx).sendBroadcast(new Intent(key).putExtras(b));
	}
	
	public static <A extends Serializable> boolean broadcast(Context ctx, String key, A obj){
		Bundle b= new Bundle();
		b.putSerializable(DATA, (Serializable) obj);
//		Log.w("SR:"+key+":S:", "S:"+obj);
		return LocalBroadcastManager.getInstance(ctx).sendBroadcast(new Intent(key).putExtras(b));
	}
	
	
	@Override
	public String toString() {
		return key != null?"L:"+key:super.toString();
	}
	
	public abstract void onReceive(T obj);
}
