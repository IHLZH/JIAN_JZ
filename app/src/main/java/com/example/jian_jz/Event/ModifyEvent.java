package com.example.jian_jz.Event;

public class ModifyEvent {
    private IncomeEvent incomeEvent;
    private MessageEvent messageEvent;

    public ModifyEvent() {
    }

    public ModifyEvent(IncomeEvent incomeEvent, MessageEvent messageEvent) {
        this.incomeEvent = incomeEvent;
        this.messageEvent = messageEvent;
    }

    public boolean getIncomeEvent() {
        return incomeEvent.isIncome();
    }

    public String getMessageEvent() {
        return messageEvent.getMessage();
    }
}
