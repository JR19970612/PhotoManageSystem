package com.ydb;

import org.junit.Test;

import java.io.IOException;
import java.util.Base64;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class WorkApplicationTests {

	@Test
	public void contextLoads() {
		String base64String="图片";
		byte[] result = Base64.getEncoder().encode(base64String.getBytes());
		System.out.println(new String(result));
	}
	public static byte[] decode(String source) {
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			return decoder.decodeBuffer(source);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
