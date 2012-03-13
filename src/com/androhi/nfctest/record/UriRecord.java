package com.androhi.nfctest.record;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;

/**
 * A parsed record containing a Uri.
 * @author androhi
 *
 */
public class UriRecord implements ParsedNdefRecord {

	private static final String TAG = "UriRecord";
	
	public static final String RECORD_TYPE = "UriRecord";
	
	/**
	 * NFC Forum "URI Record Type Definision"
	 * 
	 * This is a mapping of "URI Identifier Codes" to URI String prefixes,
	 * per section 3.2.2 of the NFC Forum URI Record Type Definision document.
	 */
	private static final BiMap<Byte, String> URI_PREFIX_MAP = ImmutableBiMap.<Byte, String>builder()
			.put((byte) 0x00, "")
			.put((byte) 0x01, "http://www.")
			.put((byte) 0x02, "https://www.")
			.put((byte) 0x03, "http://")
			.put((byte) 0x04, "https://")
			.put((byte) 0x05, "tel:")
			.put((byte) 0x06, "mailto:")
			.put((byte) 0x07, "ftp://anonymous:anonymous@")
			.put((byte) 0x08, "ftp://ftp.")
			.put((byte) 0x09, "ftps://")
			.put((byte) 0x0A, "sftp://")
			.put((byte) 0x0B, "smb://")
			.put((byte) 0x0C, "nfs://")
			.put((byte) 0x0D, "ftp://")
			.put((byte) 0x0E, "dav://")
			.put((byte) 0x0F, "news:")
			.put((byte) 0x10, "telnet://")
			.put((byte) 0x11, "imap:")
			.build();
	
	public View getView(Activity activity, LayoutInflater inflater,
			ViewGroup parent, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

}
