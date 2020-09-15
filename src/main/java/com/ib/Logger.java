package com.ib;

import com.ib.controller.ApiConnection;

public class Logger implements ApiConnection.ILogger{

    @Override
    public void log(String valueOf) {
        System.out.print(valueOf);
    }

}
