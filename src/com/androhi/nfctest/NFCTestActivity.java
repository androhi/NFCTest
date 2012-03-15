package com.androhi.nfctest;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androhi.nfctest.record.ParsedNdefRecord;

public class NFCTestActivity extends Activity {
	static final String TAG = "NFCTestActivity";
	static final int ACTIVITY_TIMEOUT_MS = 1 * 1000;
	
	TextView mTitle;
	LinearLayout mTagContent;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mTagContent = (LinearLayout)findViewById(R.id.list);
        mTitle = (TextView)findViewById(R.id.title);
        
        resolveIntent(getIntent());
    }
    
    void resolveIntent(Intent intent) {
    	// parse the intent
    	String action = intent.getAction();
    	if(NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
    		Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
    		NdefMessage[] msgs;
    		if(rawMsgs != null) {
    			msgs = new NdefMessage[rawMsgs.length];
    			for(int i = 0; i < rawMsgs.length; i++) {
    				msgs[i] = (NdefMessage) rawMsgs[i];
    			}
    		} else {
    			// Unknown tag type
    			byte[] empty = new byte[] {};
    			NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN, empty, empty, empty);
    			NdefMessage msg = new NdefMessage(new NdefRecord[] {record});
    			msgs = new NdefMessage[] {msg};
    		}
    		// setup the views
    		setTitle(R.string.title_scanned_tag);
    		buildTagViews(msgs);
    	} else {
    		Log.e(TAG, "Unknown intent " + intent);
    		finish();
    		return;
    	}
    }
    
    void buildTagViews(NdefMessage[] msgs) {
    	if(msgs == null || msgs.length == 0) {
    		return;
    	}
    	LayoutInflater inflater = LayoutInflater.from(this);
    	LinearLayout content = mTagContent;
    	// Clear out any old views in the content area,
    	// for example if you scan two tags in a row.
    	content.removeAllViews();
    	// Parse the first message in the list.
    	// Build views for all of the sub records.
    	List<ParsedNdefRecord> records = NdefMessageParser.parse(msgs[0]);
    	final int size = records.size();
    	for(int i = 0; i < size; i++) {
    		ParsedNdefRecord record = records.get(i);
    		content.addView(record.getView(this, inflater, content, i));
    		inflater.inflate(R.layout.tag_divider, content, true);
    	}
    }
    
    @Override
    public void onNewIntent(Intent intent) {
    	setIntent(intent);
    	resolveIntent(intent);
    }
    
    @Override
    public void setTitle(CharSequence title) {
    	mTitle.setText(title);
    }
}