package com.emrc_triagetag;

import java.io.*;

public class MainImageOutput extends Thread {
	File file = null;

	public MainImageOutput(File file) {
		this.file = file;
	}

	public void run() {
		try {
			FileInputStream fin = new FileInputStream(file);
			DataOutputStream dos = new DataOutputStream(MainActivity.skt.getOutputStream());
			int filesize = fin.available();
			byte[] data = new byte[filesize];
			fin.read(data);
			dos.writeInt(filesize);
			dos.write(data);
			fin.close();
		} catch (IOException e) {

		}
	}
}
