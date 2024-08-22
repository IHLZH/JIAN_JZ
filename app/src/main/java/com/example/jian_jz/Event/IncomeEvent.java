package com.example.jian_jz.Event;

public class IncomeEvent {
    private boolean income;

    public IncomeEvent(boolean income) {
        this.income = income;
    }

    public boolean isIncome() {
        return income;
    }

    public void setIncome(boolean income) {
        this.income = income;
    }
}
