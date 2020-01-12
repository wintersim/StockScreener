package cc.catgasm.model;

public class StockView {

    private static final String TRADING_VIEW_TICKER = "<body style=\"margin: 0;\">" +
            "<!-- TradingView Widget BEGIN -->\n" +
            "<div class=\"tradingview-widget-container\">\n" +
            "  <div id=\"tradingview_db7ca\"></div>\n" +
            "  <script type=\"text/javascript\" src=\"https://s3.tradingview.com/tv.js\"></script>\n" +
            "  <script type=\"text/javascript\">\n" +
            "  new TradingView.widget(\n" +
            "  {\n" +
            "  \"autosize\": true,\n" +
            "  \"symbol\": \"%s\",\n" +
            "  \"interval\": \"240\",\n" +
            "  \"timezone\": \"Etc/UTC\",\n" +
            "  \"theme\": \"Dark\",\n" +
            "  \"style\": \"1\",\n" +
            "  \"locale\": \"en\",\n" +
            "  \"toolbar_bg\": \"#f1f3f6\",\n" +
            "  \"enable_publishing\": false,\n" +
            "  \"save_image\": false,\n" +
            "  \"container_id\": \"tradingview_db7ca\"\n" +
            "}\n" +
            "  );\n" +
            "  </script>\n" +
            "</div>\n" +
            "<!-- TradingView Widget END -->" +
            "</body>";

    public String getTicker(String symbol){
        return String.format(TRADING_VIEW_TICKER, symbol);
    }
}
