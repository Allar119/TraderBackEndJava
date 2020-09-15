package com.ib;
import com.ib.controller.ApiController;

import java.util.List;

public class API implements ApiController.IConnectionHandler {

    static API INSTANCE = new API();
    Logger m_inLogger = new Logger();
    Logger m_outLogger = new Logger();
    ApiController m_controller = new ApiController( this, m_inLogger, m_outLogger);

    public static void main(String[] args) {
        INSTANCE.run();
    }

    void run() {
        // make initial connection to local host, port 7496, client id 0
        m_controller.connect( "127.0.0.1", 7400, 0, "");

        // Your implementation
    }


    @Override
    public void connected() {

    }

    @Override
    public void disconnected() {

    }

    @Override
    public void accountList(List<String> list) {

    }

    @Override
    public void error(Exception e) {

    }

    @Override
    public void message(int id, int errorCode, String errorMsg) {

    }

    @Override
    public void show(String string) {

    }
}
