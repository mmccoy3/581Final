/*
** Authored by Timothy Gerard Endres
** <mailto:time@gjt.org>  <http://www.trustice.com>
** 
** This work has been placed into the public domain.
** You may use this work in any way and for any purpose you wish.
**
** THIS SOFTWARE IS PROVIDED AS-IS WITHOUT WARRANTY OF ANY KIND,
** NOT EVEN THE IMPLIED WARRANTY OF MERCHANTABILITY. THE AUTHOR
** OF THIS SOFTWARE, ASSUMES _NO_ RESPONSIBILITY FOR ANY
** CONSEQUENCE RESULTING FROM THE USE, MODIFICATION, OR
** REDISTRIBUTION OF THIS SOFTWARE. 
** 
*/

package p2;

import java.io.File;
import java.util.Date;

/**
 * This class encapsulates the Tar Entry Header used in Tar Archives.
 * The class also holds a number of tar constants, used mostly in headers.
 */

public class
TarHeader extends Object
	{
	/**
	 * The length of the name field in a header buffer.
	 */
	public static final int		NAMELENGTH = 100;
	/**
	 * The length of the mode field in a header buffer.
	 */
	public static final int		MODELENGTH = 8;
	/**
	 * The length of the user id field in a header buffer.
	 */
	public static final int		USERIDLENGTH = 8;
	/**
	 * The length of the group id field in a header buffer.
	 */
	public static final int		GROUPIDLENGTH = 8;
	/**
	 * The length of the checksum field in a header buffer.
	 */
	public static final int		CHECKSUMLENGTH = 8;
	/**
	 * The length of the size field in a header buffer.
	 */
	public static final int		SIZELEN = 12;
	/**
	 * The length of the magic field in a header buffer.
	 */
	public static final int		MAGICLEN = 8;
	/**
	 * The length of the modification time field in a header buffer.
	 */
	public static final int		MODTIMELEN = 12;
	/**
	 * The length of the user name field in a header buffer.
	 */
	public static final int		USERNAMELENGTH = 32;
	/**
	 * The length of the group name field in a header buffer.
	 */
	public static final int		GROUPNAMELENGTH = 32;
	/**
	 * The length of the devices field in a header buffer.
	 */
	public static final int		DEVICELENGTH = 8;

	/**
	 * LF_ constants represent the "link flag" of an entry, or more commonly,
	 * the "entry type". This is the "old way" of indicating a normal file.
	 */
	public static final byte	LF_OLDNORM	= 0;
	/**
	 * Normal file type.
	 */
	public static final byte	LF_NORMAL	= (byte) '0';
	/**
	 * Link file type.
	 */
	public static final byte	LF_LINK		= (byte) '1';
	/**
	 * Symbolic link file type.
	 */
	public static final byte	LF_SYMLINK	= (byte) '2';
	/**
	 * Character device file type.
	 */
	public static final byte	LF_CHR		= (byte) '3';
	/**
	 * Block device file type.
	 */
	public static final byte	LF_BLK		= (byte) '4';
	/**
	 * Directory file type.
	 */
	public static final byte	LF_DIR		= (byte) '5';
	/**
	 * FIFO (pipe) file type.
	 */
	public static final byte	LF_FIFO		= (byte) '6';
	/**
	 * Contiguous file type.
	 */
	public static final byte	LF_CONTIG	= (byte) '7';

	/**
	 * The magic tag representing a POSIX tar archive.
	 */
	public static final String	TMAGIC		= "ustar";

	/**
	 * The magic tag representing a GNU tar archive.
	 */
	public static final String	GNU_TMAGIC	= "ustar  ";

	/**
	 * The entry's name.
	 */
	private StringBuffer		name;
	/**
	 * The entry's permission mode.
	 */
	private int				mode;
	/**
	 * The entry's user id.
	 */
	private int				userId;
	/**
	 * The entry's group id.
	 */
	private int				groupId;
	/**
	 * The entry's size.
	 */
	private long				size;
	/**
	 * The entry's modification time.
	 */
	private long				modTime;
	/**
	 * The entry's checksum.
	 */
	private int				checkSum;
	/**
	 * The entry's link flag.
	 */
	private byte				linkFlag;
	/**
	 * The entry's link name.
	 */
	private StringBuffer		linkName;
	/**
	 * The entry's magic tag.
	 */
	private StringBuffer		magic;
	/**
	 * The entry's user name.
	 */
	private StringBuffer		userName;
	/**
	 * The entry's group name.
	 */
	private StringBuffer		groupName;
	/**
	 * The entry's major device number.
	 */
	private int				devMajor;
	/**
	 * The entry's minor device number.
	 */
	private int				devMinor;


	public
	TarHeader()
		{
		this.magic = new StringBuffer( TarHeader.TMAGIC );

		this.name = new StringBuffer();
		this.linkName = new StringBuffer();

		String user =
			System.getProperty( "user.name", "" );

		if ( user.length() > 31 )
			user = user.substring( 0, 31 );

		this.userId = 0;
		this.groupId = 0;
		this.userName = new StringBuffer( user );
		this.groupName = new StringBuffer( "" );
		}

	/**
	 * TarHeaders can be cloned.
	 */
	public Object
	clone()
		{
		TarHeader hdr = null;

		try {
			hdr = (TarHeader) super.clone();

			hdr.name =
				(this.name == null ) ? null
					: new StringBuffer( this.name.toString() );
			hdr.mode = this.mode;
			hdr.userId = this.userId;
			hdr.groupId = this.groupId;
			hdr.size = this.size;
			hdr.modTime = this.modTime;
			hdr.checkSum = this.checkSum;
			hdr.linkFlag = this.linkFlag;
			hdr.linkName =
				(this.linkName == null ) ? null
					: new StringBuffer( this.linkName.toString() );
			hdr.magic =
				(this.magic == null ) ? null
					: new StringBuffer( this.magic.toString() );
			hdr.userName =
				(this.userName == null ) ? null
					: new StringBuffer( this.userName.toString() );
			hdr.groupName =
				(this.groupName == null ) ? null
					: new StringBuffer( this.groupName.toString() );
			hdr.devMajor = this.devMajor;
			hdr.devMinor = this.devMinor;
			}
		catch ( CloneNotSupportedException ex )
			{
			ex.printStackTrace();
			}

		return hdr;
		}

	/**
	 * Get the name of this entry.
	 *
	 * @return The entry's name.
	 */
	public String getName()
		{
		return this.name.toString();
		}
	
	public void setName(StringBuffer name) {
		this.name = name;
	}
	/**
	 * Parse an octal string from a header buffer. This is used for the
	 * file permission mode value.
	 *
	 * @param header The header buffer from which to parse.
	 * @param offset The offset into the buffer from which to parse.
	 * @param length The number of header bytes to parse.
	 * @return The long value of the octal string.
	 */
	public static long
	parseOctal( byte[] header, int offset, int length )
		throws InvalidHeaderException
		{
		long result = 0;
		boolean stillPadding = true;

		int end = offset + length;
		for ( int i = offset ; i < end ; ++i )
			{
			if ( header[i] == 0 )
				break;

			if ( header[i] == (byte) ' ' || header[i] == '0' )
				{
				if ( stillPadding )
					continue;

				if ( header[i] == (byte) ' ' )
					break;
				}
			
			stillPadding = false;

			result =
				(result << 3)
					+ (header[i] - '0');
			}

		return result;
		}

	/**
	 * Parse an entry name from a header buffer.
	 *
	 * @param header The header buffer from which to parse.
	 * @param offset The offset into the buffer from which to parse.
	 * @param length The number of header bytes to parse.
	 * @return The header's entry name.
	 */
	public static StringBuffer
	parseName( byte[] header, int offset, int length )
		throws InvalidHeaderException
		{
		StringBuffer result = new StringBuffer( length );

		int end = offset + length;
		for ( int i = offset ; i < end ; ++i )
			{
			if ( header[i] == 0 )
				break;
			result.append( (char)header[i] );
			}

		return result;
		}

	/**
	 * Determine the number of bytes in an entry name.
	 *
	 * @param header The header buffer from which to parse.
	 * @param offset The offset into the buffer from which to parse.
	 * @param length The number of header bytes to parse.
	 * @return The number of bytes in a header's entry name.
	 */
	public static int
	getNameBytes( StringBuffer name, byte[] buf, int offset, int length )
		{
		int i;

		for ( i = 0 ; i < length && i < name.length() ; ++i )
			{
			buf[ offset + i ] = (byte) name.charAt( i );
			}

		for ( ; i < length ; ++i )
			{
			buf[ offset + i ] = 0;
			}

		return offset + length;
		}

	/**
	 * Parse an octal integer from a header buffer.
	 *
	 * @param header The header buffer from which to parse.
	 * @param offset The offset into the buffer from which to parse.
	 * @param length The number of header bytes to parse.
	 * @return The integer value of the octal bytes.
	 */
	public static int
	getOctalBytes( long value, byte[] buf, int offset, int length )
		{
		byte[] result = new byte[ length ];

		int idx = length - 1;

		buf[ offset + idx ] = 0;
		--idx;
		buf[ offset + idx ] = (byte) ' ';
		--idx;

		if ( value == 0 )
			{
			buf[ offset + idx ] = (byte) '0';
			--idx;
			}
		else
			{
			for ( long val = value ; idx >= 0 && val > 0 ; --idx )
				{
				buf[ offset + idx ] = (byte)
					( (byte) '0' + (byte) (val & 7) );
				val = val >> 3;
				}
			}

		for ( ; idx >= 0 ; --idx )
			{
			buf[ offset + idx ] = (byte) ' ';
			}

		return offset + length;
		}

	/**
	 * Parse an octal long integer from a header buffer.
	 *
	 * @param header The header buffer from which to parse.
	 * @param offset The offset into the buffer from which to parse.
	 * @param length The number of header bytes to parse.
	 * @return The long value of the octal bytes.
	 */
	public static int
	getLongOctalBytes( long value, byte[] buf, int offset, int length )
		{
		byte[] temp = new byte[ length + 1 ];
		TarHeader.getOctalBytes( value, temp, 0, length + 1 );
		System.arraycopy( temp, 0, buf, offset, length );
		return offset + length;
		}

	/**
	 * Parse the checksum octal integer from a header buffer.
	 *
	 * @param header The header buffer from which to parse.
	 * @param offset The offset into the buffer from which to parse.
	 * @param length The number of header bytes to parse.
	 * @return The integer value of the entry's checksum.
	 */
	public static int
	getCheckSumOctalBytes( long value, byte[] buf, int offset, int length )
		{
		TarHeader.getOctalBytes( value, buf, offset, length );
		buf[ offset + length - 1 ] = (byte) ' ';
		buf[ offset + length - 2 ] = 0;
		return offset + length;
		}

	/**
	 * Parse an entry's TarHeader information from a header buffer.
	 * @param header  The tar entry header buffer to get information from.
	 */
	public void parseTarHeader(byte[] header) throws InvalidHeaderException {
		int offset = 0;
		this.name = TarHeader.parseName(header, offset, TarHeader.NAMELENGTH);
		offset += TarHeader.NAMELENGTH;
		this.mode = (int) TarHeader.parseOctal(header, offset, TarHeader.MODELENGTH);
		offset += TarHeader.MODELENGTH;
		this.userId = (int) TarHeader.parseOctal(header, offset, TarHeader.USERIDLENGTH);
		offset += TarHeader.USERIDLENGTH;
		this.groupId = (int) TarHeader.parseOctal(header, offset, TarHeader.GROUPIDLENGTH);
		offset += TarHeader.GROUPIDLENGTH;
		this.size = TarHeader.parseOctal(header, offset, TarHeader.SIZELEN);
		offset += TarHeader.SIZELEN;
		this.modTime = TarHeader.parseOctal(header, offset, TarHeader.MODTIMELEN);
		offset += TarHeader.MODTIMELEN;
		this.checkSum = (int) TarHeader.parseOctal(header, offset, TarHeader.CHECKSUMLENGTH);
		offset += TarHeader.CHECKSUMLENGTH;
		this.linkFlag = header[offset++];
		this.linkName = TarHeader.parseName(header, offset, TarHeader.NAMELENGTH);
		offset += TarHeader.NAMELENGTH;
		this.magic = TarHeader.parseName(header, offset, TarHeader.MAGICLEN);
		offset += TarHeader.MAGICLEN;
		this.userName = TarHeader.parseName(header, offset, TarHeader.USERNAMELENGTH);
		offset += TarHeader.USERNAMELENGTH;
		this.groupName = TarHeader.parseName(header, offset, TarHeader.GROUPNAMELENGTH);
		offset += TarHeader.GROUPNAMELENGTH;
		this.devMajor = (int) TarHeader.parseOctal(header, offset, TarHeader.DEVICELENGTH);
		offset += TarHeader.DEVICELENGTH;
		this.devMinor = (int) TarHeader.parseOctal(header, offset, TarHeader.DEVICELENGTH);
	}

	/**
	 * Fill in a TarHeader given only the entry's name.
	 * @param name  The tar entry name.
	 */
	public void nameTarHeader(String name) {
		boolean isDir = name.endsWith("/");
		this.checkSum = 0;
		this.devMajor = 0;
		this.devMinor = 0;
		this.name = new StringBuffer(name);
		this.mode = isDir ? 040755 : 0100644;
		this.userId = 0;
		this.groupId = 0;
		this.size = 0;
		this.checkSum = 0;
		this.modTime = new Date().getTime() / 1000;
		this.linkFlag = isDir ? TarHeader.LF_DIR : TarHeader.LF_NORMAL;
		this.linkName = new StringBuffer("");
		this.userName = new StringBuffer("");
		this.groupName = new StringBuffer("");
		this.devMajor = 0;
		this.devMinor = 0;
	}

	/**
	 * Write an entry's header information to a header buffer.
	 * @param outbuf  The tar entry header buffer to fill in.
	 * @param tarEntry
	 */
	public void writeEntryHeader(byte[] outbuf, TarEntry tarEntry) {
		int offset = 0;
		offset = TarHeader.getNameBytes(this.name, outbuf, offset, TarHeader.NAMELENGTH);
		offset = TarHeader.getOctalBytes(this.mode, outbuf, offset, TarHeader.MODELENGTH);
		offset = TarHeader.getOctalBytes(this.userId, outbuf, offset, TarHeader.USERIDLENGTH);
		offset = TarHeader.getOctalBytes(this.groupId, outbuf, offset, TarHeader.GROUPIDLENGTH);
		long size = this.size;
		offset = TarHeader.getLongOctalBytes(size, outbuf, offset, TarHeader.SIZELEN);
		offset = TarHeader.getLongOctalBytes(this.modTime, outbuf, offset, TarHeader.MODTIMELEN);
		int csOffset = offset;
		for (int c = 0; c < TarHeader.CHECKSUMLENGTH; ++c)
			outbuf[offset++] = (byte) ' ';
		outbuf[offset++] = this.linkFlag;
		offset = TarHeader.getNameBytes(this.linkName, outbuf, offset, TarHeader.NAMELENGTH);
		offset = TarHeader.getNameBytes(this.magic, outbuf, offset, TarHeader.MAGICLEN);
		offset = TarHeader.getNameBytes(this.userName, outbuf, offset, TarHeader.USERNAMELENGTH);
		offset = TarHeader.getNameBytes(this.groupName, outbuf, offset, TarHeader.GROUPNAMELENGTH);
		offset = TarHeader.getOctalBytes(this.devMajor, outbuf, offset, TarHeader.DEVICELENGTH);
		offset = TarHeader.getOctalBytes(this.devMinor, outbuf, offset, TarHeader.DEVICELENGTH);
		for (; offset < outbuf.length;)
			outbuf[offset++] = 0;
		long checkSum = tarEntry.computeCheckSum(outbuf);
		TarHeader.getCheckSumOctalBytes(checkSum, outbuf, csOffset, TarHeader.CHECKSUMLENGTH);
	}

	public void initializeHeader(File file, String name) {
		this.linkName = new StringBuffer("");
		this.name = new StringBuffer(name);
		if (file.isDirectory()) {
			this.mode = 040755;
			this.linkFlag = TarHeader.LF_DIR;
			if (this.name.charAt(this.name.length() - 1) != '/')
				this.name.append("/");
		} else {
			this.mode = 0100644;
			this.linkFlag = TarHeader.LF_NORMAL;
		}
		this.size = file.length();
		this.modTime = file.lastModified() / 1000;
		this.checkSum = 0;
		this.devMajor = 0;
		this.devMinor = 0;
	}

	public void setUserId(int id) {
		this.userId = id;
	}
	public int getUserId() {
		return this.userId;
	}
	public void setGroupId(int id) {
		this.groupId = id;
	}
	public int getGroupId() {
		return this.groupId;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public long getSize() {
		return this.size;
	}
	public void setModTime(long time) {
		this.modTime = time;
	}
	public long getModTime() {
		return this.modTime;
	}
	public byte getLinkFlag() {
		return this.linkFlag;
	}
	public void setUserName(StringBuffer name) {
		this.userName = name;
	}
	public String getUserName() {
		return this.userName.toString();
	}
	public void setGroupName(StringBuffer name) {
		this.groupName = name;
	}
	public String getGroupName() {
		return this.groupName.toString();
	}
}
