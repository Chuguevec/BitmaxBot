package model;

public class Message {
    private String op;
    private StringBuilder args;

    public Message() {
        args = new StringBuilder();
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public StringBuilder getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return "{"
                + " \"op\":\"" + op + "\""
                + ", \"args\":" + args
                + "}";
    }
}
