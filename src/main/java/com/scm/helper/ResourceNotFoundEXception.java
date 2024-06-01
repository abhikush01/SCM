package com.scm.helper;

public class ResourceNotFoundEXception extends RuntimeException{
  public ResourceNotFoundEXception(String msg){
    super(msg);
  }

  public ResourceNotFoundEXception(){
    super("Resource not Found");
  }
}
