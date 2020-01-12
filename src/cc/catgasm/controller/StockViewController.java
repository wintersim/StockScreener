package cc.catgasm.controller;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import cc.catgasm.model.StockView;

import java.util.EnumMap;

public class StockViewController {
    @FXML
    private WebView webView;

    private WebEngine webEngine;
    private StockView stockView;
    private EnumMap<KeyCode, String> keyCodes;

    public void initialize(){
        stockView = new StockView();
        keyCodes = new EnumMap<>(KeyCode.class);
        webEngine = webView.getEngine();
        webEngine.loadContent(stockView.getTicker("BITSTAMP:BTCUSD"), "text/html");
        fillKeyCodes();
    }

    private void fillKeyCodes() {
        keyCodes.put(KeyCode.F1, "BITSTAMP:BTCUSD");
        keyCodes.put(KeyCode.F2, "BITFINEX:XMRUSD");
        keyCodes.put(KeyCode.F3, "FOREXCOM:XAUUSD");
        keyCodes.put(KeyCode.F4, "NASDAQ:TSLA");
    }

    public void updateTicker(KeyCode keyCode) {
        webEngine.loadContent(stockView.getTicker(keyCodes.getOrDefault(keyCode, "BITSTAMP:BTCUSD")), "text/html");
    }
}
