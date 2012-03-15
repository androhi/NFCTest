package com.androhi.nfctest;

import java.util.ArrayList;
import java.util.List;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;

import com.androhi.nfctest.record.ParsedNdefRecord;
import com.androhi.nfctest.record.SmartPoster;
import com.androhi.nfctest.record.TextRecord;
import com.androhi.nfctest.record.UriRecord;

/**
 * Utility class for creating {@link ParsedNdefMessage}s.
 * @author androhi
 *
 */
public class NdefMessageParser {

	// Utility class
	public NdefMessageParser() {
		
	}
	
	/** Parse an NdefMessage **/
	public static List<ParsedNdefRecord> parse(NdefMessage message) {
		return getRecords(message.getRecords());
	}
	
	public static List<ParsedNdefRecord> getRecords(NdefRecord[] records) {
		List<ParsedNdefRecord> elements = new ArrayList<ParsedNdefRecord>();
		for(NdefRecord record : records) {
			if(UriRecord.isUri(record)) {
				elements.add(UriRecord.parse(record));
			} else if(TextRecord.isText(record)) {
				elements.add(TextRecord.parse(record));
			} else if(SmartPoster.isPoster(record)) {
				elements.add(SmartPoster.parse(record));
			}
		}
		return elements;
	}
}
