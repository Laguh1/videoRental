package com.joanadantas.service.messages;

import java.util.ArrayList;
import java.util.List;

public class MultipleOperationMessage {

    private List<Messaging> operationMessageList;

    public MultipleOperationMessage(){
        this.operationMessageList = new ArrayList<>();
    }

    public List<Messaging> getOperationMessageList() {
        return operationMessageList;
    }

    public void insertMessageInOperationList(Messaging operationMessage){
        operationMessageList.add(operationMessage);
    }
}
