package org.kd.main.server.rest.controller;

import org.kd.main.server.TraderServer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class AdminController {

    @PostMapping(path = "/stop")
    public void stop() {
        TraderServer.getInstance().stop();
    }

    @PostMapping(path = "/start-currency-updates")
    public void startCurrencyListener() {
        TraderServer.getInstance().startMessageListener();
    }

    @PostMapping(path = "/stop-currency-updates")
    public void stopCurrencyListener() {
        TraderServer.getInstance().stopMessageListener();
    }

}
