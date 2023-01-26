package service.builder;

import model.Message;

//делает model.message для connect to WebSocket в правильно формате
public class BitmexMessageBuilder implements MessageBuilder {
    private Message message;
    private int countArgs;

    public BitmexMessageBuilder() {
        message = new Message();
        message.getArgs().append("[");
        countArgs = 0;
    }

    public BitmexMessageBuilder addOp (String op){
       message.setOp(op);
       return this;
   }
   public BitmexMessageBuilder addArgs(String arg){
        if(countArgs > 0){
            message.getArgs().append(", ");
        }
       message.getArgs().append("\"").append(arg).append("\"");
       countArgs++;
       return this;
   }
    public BitmexMessageBuilder addArgs(long arg){
        if(countArgs > 0){
            message.getArgs().append(", ");
        }
        message.getArgs().append(arg);
        countArgs++;
        return this;
    }
   public Message build(){
        message.getArgs().append("]");
        return message;
   }
}
