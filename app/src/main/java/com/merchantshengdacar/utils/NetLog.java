package com.merchantshengdacar.utils;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.merchantshengdacar.BuildConfig;
import com.merchantshengdacar.common.Constant;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * 日志打印工具类
 * 
 * @author tanping
 * 
 */
public class NetLog {
    // 打印日志开关,测试环境开启(true)，正式环境关闭(false)
    public static boolean IS_DEBUG = BuildConfig.DEBUG_TOGGLE;
    // DEBUG_LOG 是否打印系统异常信息
    private static boolean DEBUG_LOG = BuildConfig.DEBUG_TOGGLE;
    // 是否输出日志到手机sd卡上的一个文件,测试环境开启(true)，正式环境关闭(false)
    public static boolean isWriteToFile = BuildConfig.DEBUG_TOGGLE;
    private static File mLogFile;
    private final static long LOGFILE_LIMIT = 1000000L;
    private static String PATH = "";
    private static String LOG_FILENAME = "net_log";
    private static String LOG_FILEEXT = ".txt";

    public static String FIRST_TAG = Constant.NETLOG_TAG;
    
    public static final void openDebutLog(boolean enable) {
	IS_DEBUG = enable;
	DEBUG_LOG = enable;
    }

    public static void i(String tag, String msg) {
		if(TextUtils.isEmpty(msg))
			return;
		checkLog();
		if (IS_DEBUG) {
		    Log.i(FIRST_TAG+tag, msg);
		}
		writeLogFile("INFO", tag, msg);
    }

    public static void i(String tag, String msg, Object... args) {
		checkLog();
		if (IS_DEBUG) {
		    if (args.length > 0) {
			msg = String.format(msg, args);
		    }
		    Log.i(FIRST_TAG+tag, msg);
		}
		writeLogFile("INFO", tag, msg);
    }

    public static void i(String tag, String msg, Throwable throwable) {
		if(TextUtils.isEmpty(msg))
			return;
		checkLog();
		if (IS_DEBUG) {
		    Log.i(FIRST_TAG+tag, msg, throwable);
		}
		writeLogFile("INFO", tag, msg);
    }

    public static final void d(String tag, String msg) {
		if(TextUtils.isEmpty(msg))
			return;
		checkLog();
		if (IS_DEBUG) {
		    Log.d(FIRST_TAG+tag, msg);
		}
		writeLogFile("DEBUG", tag, msg);
    }

    public static final void d(String tag, String msg, Throwable tr) {
	checkLog();
	if (IS_DEBUG) {
	    Log.d(FIRST_TAG+tag, msg, tr);
	}
	writeLogFile("DEBUG", tag, msg);
    }

    public static void e(String tag, String msg) {
		if(TextUtils.isEmpty(msg))
			return;
		checkLog();
		if (IS_DEBUG) {
		    Log.e(tag, msg);
		}
		writeLogFile("DEBUG", tag, msg);
    }

    public static void e(String tag, String msg, Object... args) {
	checkLog();
	if (IS_DEBUG) {
	    if (args.length > 0) {
		msg = String.format(msg, args);
	    }
	    Log.e(tag, msg);
	}
	writeLogFile("DEBUG", tag, msg);
    }

    public static void e(String tag, String msg, Throwable throwable) {
		if(TextUtils.isEmpty(msg))
			return;
		checkLog();
		if (IS_DEBUG) {
		    Log.e(tag, msg, throwable);
		}
		writeLogFile("DEBUG", tag, msg);
    }

    public static final void exception(Exception e) {
	checkLog();
	if (DEBUG_LOG) {
	    e.printStackTrace();
	}
	writeLogFile("Exception", "error", e.getMessage());
    }

    private static void checkLog() {
	createLogFile();
    }

    private static void createLogFile() {
	if (isWriteToFile) {
	    synchronized (LOG_FILENAME) {
		if (mLogFile == null) {
		    try {
			if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			    return;
			}

			PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
			mLogFile = new File(PATH + LOG_FILENAME + LOG_FILEEXT);
			if (!mLogFile.exists()) {
			    mLogFile.createNewFile();
			}

		    } catch (Exception e) {
			e.printStackTrace();
		    }
		} else {
		    if (mLogFile.isFile()) {
			if (mLogFile.length() > LOGFILE_LIMIT) {
			    StringBuffer sb = new StringBuffer(PATH);
			    // sb.append(LOG_FILEPATH);
			    sb.append(LOG_FILENAME);
			    sb.append(new Date().toLocaleString());
			    sb.append(LOG_FILEEXT);
			    mLogFile.renameTo(new File(sb.toString()));
			    sb = null;
			    sb = new StringBuffer(PATH);
			    // sb.append(LOG_FILEPATH);
			    sb.append(LOG_FILENAME);
			    sb.append(LOG_FILEEXT);
			    mLogFile = new File(sb.toString());
			    sb = null;
			    if (!mLogFile.exists()) {
				try {
				    mLogFile.createNewFile();
				} catch (IOException e) {
				    e.printStackTrace();
				}
			    }
			}
		    }
		}
	    }
	}
    }

    private static void writeLogFile(String level, String tag, String msg) {
	if (isWriteToFile) {
	    synchronized (LOG_FILENAME) {
		if (mLogFile != null) {

		    StringBuffer sb = new StringBuffer();
		    sb.append(new Date().toLocaleString());
		    sb.append(": ");
		    sb.append(level);
		    sb.append(": ");
		    sb.append(tag);
		    sb.append(": ");
		    sb.append(msg);
		    sb.append("\n");
		    RandomAccessFile raf = null;
		    try {
			raf = new RandomAccessFile(mLogFile, "rw");
			raf.seek(mLogFile.length());
			raf.write(sb.toString().getBytes("UTF-8"));
		    } catch (UnsupportedEncodingException e) {
			e.printStackTrace();

		    } catch (IOException e) {
			e.printStackTrace();

		    } finally {
			sb = null;
			if (raf != null) {
			    try {
				raf.close();
			    } catch (IOException e) {
				e.printStackTrace();
			    }
			}

		    }
		}
	    }
	}
    }
}
