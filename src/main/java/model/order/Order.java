package model.order;

public class Order {
    public Order(String symbol, String side, double orderQty, double price, String ordType) {
        this.symbol = symbol;
        this.side = side;
        this.orderQty = orderQty;
        this.price = price;
        this.ordType = ordType;
    }

    private   String orderID;

    private String clOrdID;

    private  String clOrdLinkID;

    private  double account;

    private  String symbol;

    private   String side;

    private  double simpleOrderQty;

    private  double orderQty;

    private   double price;

    private  double displayQty;

    private   double stopPx;

    private   double pegOffsetValue;

    private  String pegPriceType;

    private  String currency;

    private  String settlCurrency;

    private  String ordType;

    private String timeInForce;

    private  String execInst;

    private String contingencyType;

    private  String exDestination;

    private String ordStatus;

    private  String triggered;

    private  double workingIndicator;

    private  String ordRejReason;

    private  double simpleLeavesQty;

    private   double leavesQty;

    private  double simpleCumQty;

    private  double cumQty;

    private  double avgPx;

    private  String multiLegReportingType;

    private String text;

    private String transactTime;

    private String timestamp;


    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    public String getClOrdID() {
        return clOrdID;
    }

    public void setClOrdLinkID(String clOrdLinkID) {
        this.clOrdLinkID = clOrdLinkID;
    }

    public String getClOrdLinkID() {
        return clOrdLinkID;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public double getAccount() {
        return account;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getSide() {
        return side;
    }

    public void setSimpleOrderQty(double simpleOrderQty) {
        this.simpleOrderQty = simpleOrderQty;
    }

    public double getSimpleOrderQty() {
        return simpleOrderQty;
    }

    public void setOrderQty(double orderQty) {
        this.orderQty = orderQty;
    }

    public double getOrderQty() {
        return orderQty;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setDisplayQty(double displayQty) {
        this.displayQty = displayQty;
    }

    public double getDisplayQty() {
        return displayQty;
    }

    public void setStopPx(double stopPx) {
        this.stopPx = stopPx;
    }

    public double getStopPx() {
        return stopPx;
    }

    public void setPegOffsetValue(double pegOffsetValue) {
        this.pegOffsetValue = pegOffsetValue;
    }

    public double getPegOffsetValue() {
        return pegOffsetValue;
    }

    public void setPegPriceType(String pegPriceType) {
        this.pegPriceType = pegPriceType;
    }

    public String getPegPriceType() {
        return pegPriceType;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setSettlCurrency(String settlCurrency) {
        this.settlCurrency = settlCurrency;
    }

    public String getSettlCurrency() {
        return settlCurrency;
    }

    public void setOrdType(String ordType) {
        this.ordType = ordType;
    }

    public String getOrdType() {
        return ordType;
    }

    public void setTimeInForce(String timeInForce) {
        this.timeInForce = timeInForce;
    }

    public String getTimeInForce() {
        return timeInForce;
    }

    public void setExecInst(String execInst) {
        this.execInst = execInst;
    }

    public String getExecInst() {
        return execInst;
    }

    public void setContingencyType(String contingencyType) {
        this.contingencyType = contingencyType;
    }

    public String getContingencyType() {
        return contingencyType;
    }

    public void setExDestination(String exDestination) {
        this.exDestination = exDestination;
    }

    public String getExDestination() {
        return exDestination;
    }

    public void setOrdStatus(String ordStatus) {
        this.ordStatus = ordStatus;
    }

    public String getOrdStatus() {
        return ordStatus;
    }

    public void setTriggered(String triggered) {
        this.triggered = triggered;
    }

    public String getTriggered() {
        return triggered;
    }

    public void setWorkingIndicator(double workingIndicator) {
        this.workingIndicator = workingIndicator;
    }

    public double getWorkingIndicator() {
        return workingIndicator;
    }

    public void setOrdRejReason(String ordRejReason) {
        this.ordRejReason = ordRejReason;
    }

    public String getOrdRejReason() {
        return ordRejReason;
    }

    public void setSimpleLeavesQty(double simpleLeavesQty) {
        this.simpleLeavesQty = simpleLeavesQty;
    }

    public double getSimpleLeavesQty() {
        return simpleLeavesQty;
    }

    public void setLeavesQty(double leavesQty) {
        this.leavesQty = leavesQty;
    }

    public double getLeavesQty() {
        return leavesQty;
    }

    public void setSimpleCumQty(double simpleCumQty) {
        this.simpleCumQty = simpleCumQty;
    }

    public double getSimpleCumQty() {
        return simpleCumQty;
    }

    public void setCumQty(double cumQty) {
        this.cumQty = cumQty;
    }

    public double getCumQty() {
        return cumQty;
    }

    public void setAvgPx(double avgPx) {
        this.avgPx = avgPx;
    }

    public double getAvgPx() {
        return avgPx;
    }

    public void setMultiLegReportingType(String multiLegReportingType) {
        this.multiLegReportingType = multiLegReportingType;
    }

    public String getMultiLegReportingType() {
        return multiLegReportingType;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setTransactTime(String transactTime) {
        this.transactTime = transactTime;
    }

    public String getTransactTime() {
        return transactTime;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "{"
                + " \"symbol\":\"" + symbol + "\""
                + ", \"side\":\"" + side + "\""
                + ", \"orderQty\":" + orderQty
                + ", \"price\":" + price
                + ", \"ordType\":\"" + ordType + "\""
                + "}";
    }
}
