package eve.utils;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CommonUtilities {

	private static final Log appLog = LogFactory.getLog(CommonUtilities.class);
	
	//Minimum 8 characters at least 1 Uppercase Alphabet, 1 Lowercase Alphabet, 1 Number and 1 Special Character
	protected static String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&_#-])[A-Za-z\\d$@$!%*?&_#-]{8,}";
	protected static String USER_NAME_REGEX = "^\\w{5,}+$";
	
	private static final byte[] KEY = {0x56, 0x02, 0x7A, 0x5F,
		   0x60, 0x3B, 0x4A, 0x2F,
		   0x3F, 0x2C, 0x1B, 0x4F,
		   0x1A, 0x30, 0x65, 0x5B};

	public static String hexToString(byte[] pBytes) {
		StringBuffer lObjBuffer = new StringBuffer();

		for(byte lByte : pBytes) {
			String lConvByte = Integer.toHexString(lByte >= 0?lByte:256+lByte);
			lConvByte = (lConvByte.length() == 2)?lConvByte:"0" + lConvByte;
			lObjBuffer.append(lConvByte);
		}
		return lObjBuffer.toString();
	}

	public static byte[] stringToHex(String pString) {
		ByteArrayOutputStream lBytes = new ByteArrayOutputStream();

		for(int i = 0; i < pString.length(); i += 2) {
			String lPart = pString.substring(i, (i + 2));
			lBytes.write(Integer.parseInt(lPart, 16));
		}
		return lBytes.toByteArray();
	}

	public static String encrypt(String pValue) throws GeneralSecurityException {
		SecretKeySpec lObjKeySpec = new SecretKeySpec(KEY, "AES");
		Cipher lObjCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		lObjCipher.init(Cipher.ENCRYPT_MODE, lObjKeySpec, new IvParameterSpec(new byte[KEY.length]));
		return hexToString(lObjCipher.doFinal(pValue.getBytes(Charset.forName("US-ASCII"))));
	}

	public static String decrypt(byte[] pValue) throws GeneralSecurityException {
		SecretKeySpec lObjKeySpec = new SecretKeySpec(KEY, "AES");
		Cipher lObjCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		lObjCipher.init(Cipher.DECRYPT_MODE, lObjKeySpec, new IvParameterSpec(new byte[KEY.length]));
		byte[] lOrigText = lObjCipher.doFinal(pValue);
		return new String(lOrigText, Charset.forName("US-ASCII"));
	}

	public static String Sha256(byte[] pBytes) throws NoSuchAlgorithmException {
		if(null == pBytes || 0 == pBytes.length) {
			throw new IllegalArgumentException("Invalid data");
		}
		MessageDigest lObjDigest = MessageDigest.getInstance("SHA-256");
		return hexToString(lObjDigest.digest(pBytes));
	}

	protected String validatePassword(String pPassword, String pRetypePassword) {
		String lEncPwd = null;

		if(null == pPassword || !pPassword.matches(PASSWORD_REGEX)) {
			return lEncPwd;
		}

		if(!pPassword.equals(pRetypePassword)) {
			return lEncPwd;
		}

		try {
			lEncPwd = Sha256(pPassword.getBytes());
		} catch(Exception ex) {
			appLog.warn(ex.toString(), ex);
		}
		if(null == lEncPwd) {
			return lEncPwd;
		}
		return lEncPwd;
	}
}
