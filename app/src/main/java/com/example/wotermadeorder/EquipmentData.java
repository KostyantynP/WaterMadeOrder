package com.example.wotermadeorder;

import androidx.annotation.NonNull;

public class EquipmentData {


    private String equipmentName;
    private String equipmentPrice;
    private String equipmentCount;
    private String equipmentFullPriceCurrentPosition;

    public EquipmentData(String equipmentName, String equipmentPrice, String equipmentCount, String equipmentFullPriceCurrentPosition) {

        this.equipmentName = equipmentName;
        this.equipmentCount = equipmentCount;
        this.equipmentPrice = equipmentPrice;
        this.equipmentFullPriceCurrentPosition = equipmentFullPriceCurrentPosition;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public String getEquipmentPrice() {
        return equipmentPrice;
    }

    public String getEquipmentCount() {
        return equipmentCount;
    }

    public String getEquipmentFullPriceCurrentPosition() {
        return equipmentFullPriceCurrentPosition;
    }

    @NonNull
    @Override
    public String toString() {
        return equipmentName;
    }
}
