package org.ce.ap.com.company.server.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public interface ConnectionService {
    final Socket connectionSocket = null;
    final int clientNum = 0;
    InputStream in = null;
    OutputStream out = null;
    /**
     * this method will help us to use our clases and transfer details with users and client class
     * @return string for client side
     */
    public String inputStream();
    public void outputStream(String outputs);
}
