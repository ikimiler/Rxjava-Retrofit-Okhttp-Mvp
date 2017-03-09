package com.merchantshengdacar.utils;

import java.io.IOException;
import java.io.OutputStream;

/**
 * ���ַ����һЩ�������ת��
 */
public class StringConverter
{

	private static final char[] HEXCHAR = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	private static final String HEXINDEX = "0123456789abcdef0123456789ABCDEF";

	/**
	 * ���ַ�ת����ʮ������ֽ����飬����big-endian���� Converts a String into a byte
	 * array by using a big-endian two byte representation of each
	 * char value in the string.
	 */
	byte[] stringToFullByteArray(String s)
	{

		int length = s.length();
		byte[] buffer = new byte[length * 2];
		int c;

		for (int i = 0; i < length; i++)
		{
			c = s.charAt(i);
			buffer[i * 2] = (byte) ((c & 0x0000ff00) >> 8);
			buffer[i * 2 + 1] = (byte) (c & 0x000000ff);
		}

		return buffer;
	}

	/**
	 * ��һ��ʮ������ַ�ת����byte array
	 * @param s ʮ������ַ�
	 * @return byte array for the hex string
	 * @throws IOException
	 */
	public static byte[] hexToByte(String s) throws IOException
	{

		int l = s.length() / 2;
		byte[] data = new byte[l];
		int j = 0;

		if (s.length() % 2 != 0)
		{
			throw new IOException("hexadecimal string with odd number of characters");
		}

		for (int i = 0; i < l; i++)
		{
			char c = s.charAt(j++);
			int n, b;

			n = HEXINDEX.indexOf(c);

			if (n == -1)
			{
				throw new IOException("hexadecimal string contains non hex character");
			}

			b = (n & 0xf) << 4;
			c = s.charAt(j++);
			n = HEXINDEX.indexOf(c);
			b += (n & 0xf);
			data[i] = (byte) b;
		}

		return data;
	}

	/**
	 * ��һ��byte arrayת����һ��ʮ������ַ�
	 * @param b byte array
	 * @return hex string
	 */
	public static String byteToHex(byte b[])
	{

		int len = b.length;
		char[] s = new char[len * 2];

		for (int i = 0, j = 0; i < len; i++)
		{
			int c = ((int) b[i]) & 0xff;

			s[j++] = HEXCHAR[c >> 4 & 0xf];
			s[j++] = HEXCHAR[c & 0xf];
		}

		return new String(s);
	}

	public static String byteToString(byte[] b, String charset)
	{

		try
		{
			return (charset == null) ? new String(b) : new String(b, charset);
		}
		catch (Exception e)
		{
		}

		return null;
	}

	/**
	 * unicodeת����Ascii
	 * @param b output stream to wite to
	 * @param s Java Unicode string
	 * @return number of bytes written out
	 */
	public static int unicodeToAscii(OutputStream b, String s, boolean doubleSingleQuotes) throws IOException
	{

		int count = 0;

		if ((s == null) || (s.length() == 0))
		{
			return 0;
		}

		int len = s.length();

		for (int i = 0; i < len; i++)
		{
			char c = s.charAt(i);

			if (c == '\\')
			{
				if ((i < len - 1) && (s.charAt(i + 1) == 'u'))
				{
					b.write(c); // encode the \ as unicode, so 'u' is
					// ignored
					b.write('u');
					b.write('0');
					b.write('0');
					b.write('5');
					b.write('c');

					count += 6;
				}
				else
				{
					b.write(c);

					count++;
				}
			}
			else if ((c >= 0x0020) && (c <= 0x007f))
			{
				b.write(c); // this is 99%

				count++;

				if (c == '\'' && doubleSingleQuotes)
				{
					b.write(c);

					count++;
				}
			}
			else
			{
				b.write('\\');
				b.write('u');
				b.write(HEXCHAR[(c >> 12) & 0xf]);
				b.write(HEXCHAR[(c >> 8) & 0xf]);
				b.write(HEXCHAR[(c >> 4) & 0xf]);
				b.write(HEXCHAR[c & 0xf]);

				count += 6;
			}
		}

		return count;
	}

	/**
	 * ascii��ת����Unicode��
	 * @param s ascii��
	 * @param offset ƫ��
	 * @param length ����
	 * @return Java Unicode string
	 */
	public static String asciiToUnicode(byte[] s, int offset, int length)
	{

		if (length == 0)
		{
			return "";
		}

		char[] b = new char[length];
		int j = 0;

		for (int i = 0; i < length; i++)
		{
			byte c = s[offset + i];

			if (c == '\\' && i < length - 5)
			{
				byte c1 = s[offset + i + 1];

				if (c1 == 'u')
				{
					i++;

					// 4 characters read should always return 0-15
					int k = HEXINDEX.indexOf(s[offset + (++i)]) << 12;

					k += HEXINDEX.indexOf(s[offset + (++i)]) << 8;
					k += HEXINDEX.indexOf(s[offset + (++i)]) << 4;
					k += HEXINDEX.indexOf(s[offset + (++i)]);
					b[j++] = (char) k;
				}
				else
				{
					b[j++] = (char) c;
				}
			}
			else
			{
				b[j++] = (char) c;
			}
		}

		return new String(b, 0, j);
	}

	/**
	 * ascii��ת����Unicode��
	 * @param s ascii��
	 * @return Java Unicode string
	 */
	public static String asciiToUnicode(String s)
	{

		if ((s == null) || (s.indexOf("\\u") == -1))
		{
			return s;
		}

		int len = s.length();
		char[] b = new char[len];
		int j = 0;

		for (int i = 0; i < len; i++)
		{
			char c = s.charAt(i);

			if (c == '\\' && i < len - 5)
			{
				char c1 = s.charAt(i + 1);

				if (c1 == 'u')
				{
					i++;

					// 4 characters read should always return 0-15
					int k = HEXINDEX.indexOf(s.charAt(++i)) << 12;

					k += HEXINDEX.indexOf(s.charAt(++i)) << 8;
					k += HEXINDEX.indexOf(s.charAt(++i)) << 4;
					k += HEXINDEX.indexOf(s.charAt(++i));
					b[j++] = (char) k;
				}
				else
				{
					b[j++] = c;
				}
			}
			else
			{
				b[j++] = c;
			}
		}
		return new String(b, 0, j);
	}
}