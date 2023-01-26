package service.builder;

import model.Message;

public interface MessageBuilder {
  MessageBuilder  addOp(String op);
  MessageBuilder addArgs(String arg);
  Message build ();


}
