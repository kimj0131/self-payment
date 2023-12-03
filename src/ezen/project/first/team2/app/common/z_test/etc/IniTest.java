package ezen.project.first.team2.app.common.z_test.etc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class IniTest {

	static void write() {
		var props = new Properties();
		props.setProperty("host", "192.168.0.64");
		props.setProperty("port", "1521");
		props.setProperty("id", "hr");
		props.setProperty("pw", "1234");

		try (
				FileOutputStream fos = new FileOutputStream("test.ini")) {
			props.store(fos, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void read() {
		try (
				FileInputStream fis = new FileInputStream("test.ini")) {

			var props = new Properties();
			props.load(fis);

			var host = props.getProperty("host");
			var port = props.getProperty("port");
			var id = props.getProperty("id");
			var pw = props.getProperty("pw");
			var unknown = props.getProperty("unknown", "?");

			System.out.printf("host: %s, port: %s, id: %s, pw: %s, unknown: %s \n",
					host, port, id, pw, unknown);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// write();
		read();
	}
}
