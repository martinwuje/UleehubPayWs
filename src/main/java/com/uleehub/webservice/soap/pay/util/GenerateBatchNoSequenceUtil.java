package com.uleehub.webservice.soap.pay.util;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;

import org.joda.time.DateTime;

/**
 * <p>User: mtwu
 * <p>Date: 2014-6-27
 * <p>Version: 1.0
 * <p>退款批次号生成器
 */
public class GenerateBatchNoSequenceUtil {
	
	private final static String dateFormat = "yyyyMMddhhmmssSSS";
	private static final FieldPosition HELPER_POSITION = new FieldPosition(0);
	private final static NumberFormat numberFormat = new DecimalFormat("0000");
	private static int seq = 1;
	private static final int MAX = 9999;
	
	public static synchronized String next() {
		StringBuffer sb = new StringBuffer();
		sb.append(DateTime.now().toString(dateFormat));
		numberFormat.format(seq, sb, HELPER_POSITION);
		if (seq == MAX) {
			seq = 0;
		}
		else{
			seq++;
		}
		return sb.toString();
	}
}
