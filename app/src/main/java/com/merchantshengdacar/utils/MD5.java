package com.merchantshengdacar.utils;
/* Copyright (c) 2001-2004, The HSQL Development Group
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the HSQL Development Group nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL HSQL DEVELOPMENT GROUP, HSQLDB.ORG, 
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5
{
	
	private static final String TAG = MD5.class.getSimpleName();
//	private static final L log = L.getLog(MD5.class);
	
	/** * The jce MD5 message digest generator. */
	private static MessageDigest md5;

	/**
	 * * Retrieves a hexidecimal character sequence representing the
	 * MD5 * digest of the specified character sequence, using the
	 * specified * encoding to first convert the character sequence
	 * into a byte sequence. * If the specified encoding is null, then
	 * ISO-8859-1 is assumed * *
	 * @param string the string to encode. *
	 * @param encoding the encoding used to convert the string into
	 *        the * byte sequence to submit for MD5 digest *
	 * @return a hexidecimal character sequence representing the MD5 *
	 *         digest of the specified string *
	 * @throws  if an MD5 digest *
	 *         algorithm is not available through the *
	 *         java.security.MessageDigest spi or the requested *
	 *         encoding is not available
	 */
	public static final String encodeString(String string, String encoding)
			throws RuntimeException
	{
		return StringConverter.byteToHex(digestString(string, encoding));
	}

	/**
	 * * Retrieves a byte sequence representing the MD5 digest of the *
	 * specified character sequence, using the specified encoding to *
	 * first convert the character sequence into a byte sequence. * If
	 * the specified encoding is null, then ISO-8859-1 is * assumed. * *
	 * @param string the string to digest. *
	 * @param encoding the character encoding. *
	 * @return the digest as an array of 16 bytes. *
	 * @throws  if an MD5 digest *
	 *         algorithm is not available through the *
	 *         java.security.MessageDigest spi or the requested *
	 *         encoding is not available
	 */
	public static byte[] digestString(String string, String encoding)
			throws RuntimeException
	{
		byte[] data;
		if (encoding == null)
		{
			encoding = "ISO-8859-1";
		}
		try
		{
			data = string.getBytes(encoding);
		}
		catch (UnsupportedEncodingException x)
		{
			throw new RuntimeException(x.toString());
		}
		return digestBytes(data);
	}

	/**
	 * * Retrieves a byte sequence representing the MD5 digest of the *
	 * specified byte sequence. * *
	 * @param data the data to digest. *
	 * @return the MD5 digest as an array of 16 bytes. *
	 * @throws  if an MD5 digest *
	 *         algorithm is not available through the *
	 *         java.security.MessageDigest spi
	 */
	public static final byte[] digestBytes(byte[] data) throws RuntimeException
	{
		synchronized (MD5.class)
		{
			if (md5 == null)
			{
				try
				{
					md5 = MessageDigest.getInstance("MD5");
				}
				catch (NoSuchAlgorithmException e)
				{
					throw new RuntimeException(e.toString());
				}
			}
			return md5.digest(data);
		}
	}
	
	/**
	 * ����Բʵ�MD5����
	 */
	public static String encryptStringWithMD5(String strData) {
        strData = strData.trim();
        String digest = "";
        try {
            MessageDigest currentAlgorithm = MessageDigest.getInstance("md5");
            currentAlgorithm.reset();
            byte[] mess = strData.getBytes();
            byte[] hash = currentAlgorithm.digest(mess);
            for (int i = 0; i < hash.length; i++) {
                int v = hash[i];
                if (v < 0) {
                    v = 256 + v;
                }
                if (v < 16) {
                    digest += "0";
                }
                digest += Integer.toString(v, 16).toUpperCase() + "";
            }
        } catch (NoSuchAlgorithmException e) {
        }
        return digest;
    }
	
	public static void main(String[] args) {
		String ss = encodeString("shoulashou0571jkjldzswshoulashou0571","GB2312");
		System.out.println(ss);
	}
}