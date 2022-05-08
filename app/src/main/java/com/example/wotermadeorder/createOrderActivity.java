package com.example.wotermadeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.StringBufferInputStream;
import java.util.ArrayList;

public class createOrderActivity extends AppCompatActivity {


    private final int AMOUNT_OF_EQUIPMENT = 21;
    private final EditText[] countAmount = new EditText[AMOUNT_OF_EQUIPMENT];
    private final Spinner[] selectedItems = new Spinner[AMOUNT_OF_EQUIPMENT];
    private ArrayList<EquipmentData> equipmentData;
    private RadioButton buttonManual;
    private RadioButton buttonAuto;
    private RadioButton buttonCoeff15;
    private RadioButton buttonCoeff17;
    private CheckBox checkBoxFitting;
    private EditText editTextManualCost;

    private String name;
    private String phone;
    private String address;
    private String costWorkForOrderView;
    private int costWork = 0;
    private int costEquipment;

    private StringBuilder builderOrder;

    private TextView helloCustomer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order_activiti);
        Intent intent = getIntent();
        if (intent.hasExtra("name") && intent.hasExtra("phone") && intent.hasExtra("address")) {
            name = intent.getStringExtra("name");
            phone = intent.getStringExtra("phone");
            address = intent.getStringExtra("address");
            Log.i("show","3");
        } else {
            name = getString(R.string.default_name);
            phone = getString(R.string.default_number_of_phone);
            address = getString(R.string.default_address);
            Log.i("show","4");
        }

        helloCustomer = findViewById(R.id.textViewHello);
        String hello = String.format(getString(R.string.hello_user), name);
        helloCustomer.setText(hello);





//  (Block 1) In this block you need to add new price list position.

        countAmount[0] = findViewById(R.id.editTextCountController);
        countAmount[1] = findViewById(R.id.editTextCountRainSensor);
        countAmount[2] = findViewById(R.id.editTextCountSprinklers);
        countAmount[3] = findViewById(R.id.editTextCountNozzleMP);
        countAmount[4] = findViewById(R.id.editTextCountNozzleSpray);
        countAmount[5] = findViewById(R.id.editTextCountNozzleRotor);
        countAmount[6] = findViewById(R.id.editTextCountValve);
        countAmount[7] = findViewById(R.id.editTextCountBoxValve);
        countAmount[8] = findViewById(R.id.editTextCountKneeAdjustable);
        countAmount[9] = findViewById(R.id.editTextCountHydrant);
        countAmount[10] = findViewById(R.id.editTextCountHydrantKey);
        countAmount[11] = findViewById(R.id.editTextCountValveDrop);
        countAmount[12] = findViewById(R.id.editTextCountTubeDrop);
        countAmount[13] = findViewById(R.id.editTextCountFittingDrop);
        countAmount[14] = findViewById(R.id.editTextCountPump);
        countAmount[15] = findViewById(R.id.editTextCountAutomaticsPump);
        countAmount[16] = findViewById(R.id.editTextCountElectricCable);
        countAmount[17] = findViewById(R.id.editTextCountElectricRelay);
        countAmount[18] = findViewById(R.id.editTextCountBarrel);
        countAmount[19] = findViewById(R.id.editTextCountFloatBarrel);
        countAmount[20] = findViewById(R.id.editTextCountClipsMaterials);

        selectedItems[0] = findViewById(R.id.spinnerController);
        selectedItems[1] = findViewById(R.id.spinnerRainSensor);
        selectedItems[2] = findViewById(R.id.spinnerSprinklers);
        selectedItems[3] = findViewById(R.id.spinnerNozzleMP);
        selectedItems[4] = findViewById(R.id.spinnerNozzleSpray);
        selectedItems[5] = findViewById(R.id.spinnerNozzleRotor);
        selectedItems[6] = findViewById(R.id.spinnerValve);
        selectedItems[7] = findViewById(R.id.spinnerBoxValve);
        selectedItems[8] = findViewById(R.id.spinnerKneeAdjustable);
        selectedItems[9] = findViewById(R.id.spinnerHydrant);
        selectedItems[10] = findViewById(R.id.spinnerHydrantKey);
        selectedItems[11] = findViewById(R.id.spinnerValveDrop);
        selectedItems[12] = findViewById(R.id.spinnerTubeDrop);
        selectedItems[13] = findViewById(R.id.spinnerFittingDrop);
        selectedItems[14] = findViewById(R.id.spinnerPump);
        selectedItems[15] = findViewById(R.id.spinnerAutomaticsPump);
        selectedItems[16] = findViewById(R.id.spinnerElectricCable);
        selectedItems[17] = findViewById(R.id.spinnerElectricRelay);
        selectedItems[18] = findViewById(R.id.spinnerBarrel);
        selectedItems[19] = findViewById(R.id.spinnerFloatBarrel);
        selectedItems[20] = findViewById(R.id.spinnerClipsMaterials);

        buttonAuto = findViewById(R.id.radioButtonAuto);
        buttonManual = findViewById(R.id.radioButtonManual);
        buttonCoeff15 = findViewById(R.id.radioButtonCoefficient15);
        buttonCoeff17 = findViewById(R.id.radioButtonCoefficient17);
        editTextManualCost = findViewById(R.id.editTextManualCost);

        checkBoxFitting = findViewById(R.id.checkBoxFitting);

        builderOrder = new StringBuilder();
        editTextManualCost.setText(null);

    }

    public void onClickSendOrder(View view) {

        EquipmentData equipmentElement;
        float fullPricePosition = 0;
        float orderPriceEquipmentPlusWork = 0;
        int fullPriceAccordingPositions = 0;
        int count = 0;
        int countFull = 0;
        int priceInThePriceList;
        int fullPriceWithCount;
        int priceFitting = 0;
        String equipmentDescription;
        String equipmentCount;
        String price;
        String orderFullPrice;
        String orderCostWork;



//    Added to arrow equipment order materials

        for (int i = 0; i < AMOUNT_OF_EQUIPMENT; i++) {
            if (isEquipmentCountEmpty(i)) {
                count++;
            }
        }
        if (count > 0) {
            equipmentData = new ArrayList<EquipmentData>();
              for (int i = 0; i < AMOUNT_OF_EQUIPMENT; i++) {
                if (isEquipmentCountEmpty(i)) {
                    priceInThePriceList = getPriseByPosition(i, selectedItems[i].getSelectedItemPosition());
                    fullPriceWithCount = priceInThePriceList * Integer.parseInt(countAmount[i].getText().toString());
                    fullPriceAccordingPositions = fullPriceAccordingPositions + fullPriceWithCount;


                    equipmentData.add(new EquipmentData(selectedItems[i].getSelectedItem().toString(),
                            Integer.toString(priceInThePriceList),
                            countAmount[i].getText().toString(),
                            Integer.toString(fullPriceWithCount)));
                    countFull++;
                }
            }
        }


        //     calculation of the cost fittings

        if (checkBoxFitting.isChecked()) {
            priceFitting = 10;

        } else {
            priceFitting =0;
        }
        Log.i("showfitting",Integer.toString(priceFitting));

        //      Calculation of the cost of works


//        costEquipment = fullPriceAccordingPositions;
        if (costWork != 0 && editTextManualCost.getText().toString().isEmpty()) {
           costWork = costWork*fullPriceAccordingPositions/1000;
        }
        if (!editTextManualCost.getText().toString().isEmpty() && costWork == 0) {
            costWork = Integer.parseInt(editTextManualCost.getText().toString());
        }



//      Send and build text order to next Activity

            if ( countFull > 0 ) {
                builderOrder.setLength(0);
                for (int i = 0; i < countFull ; i++) {
                    String nameEquipment = equipmentData.get(i).getEquipmentName();
                    String subName = nameEquipment.length()>28 ? nameEquipment.substring(0,28) : nameEquipment;
                    builderOrder.append(subName + "\n");
                }
            } else {
                builderOrder.append("0");
            }
            if (builderOrder.length() > 0) {
                equipmentDescription = builderOrder.toString();
                } else {
                    equipmentDescription = "";
                }

        if ( countFull > 0 ) {
            builderOrder.setLength(0);
            for (int i = 0; i < countFull ; i++) {
                builderOrder.append(equipmentData.get(i).getEquipmentCount() + "\n");
            }
        } else {
            builderOrder.append("0");
        }
        if (builderOrder.length() > 0) {
            equipmentCount = builderOrder.toString();
        } else {
            equipmentCount = "";
        }

        if ( countFull > 0 ) {
            builderOrder.setLength(0);
            for (int i = 0; i < countFull ; i++) {
                builderOrder.append(Integer.parseInt(equipmentData.get(i).getEquipmentFullPriceCurrentPosition())/100 + "\n");
            }
        }

        if (builderOrder.length() > 0) {
            price = builderOrder.toString();
        } else {
            price = "";
        }

        fullPricePosition = (float) fullPriceAccordingPositions/100;

        orderCostWork = Float.toString((float) costWork);

        orderPriceEquipmentPlusWork = fullPricePosition + costWork;


        if (fullPricePosition > 0) {
            orderFullPrice = String.format(getString(R.string.order_detail), Float.toString(fullPricePosition), orderCostWork, Float.toString(orderPriceEquipmentPlusWork));
        } else {
            orderFullPrice = "0";
        }

            String fullOrder = String.format(getString(R.string.order), name, address, phone);

            Intent intent = new Intent(this, OrderDetailActivity.class);
            intent.putExtra("fullOrder", fullOrder);
            intent.putExtra("orderList",equipmentDescription);
            intent.putExtra("equipmentCount", equipmentCount);
            intent.putExtra("price", price);
            intent.putExtra("orderFullPrice", orderFullPrice);
            intent.putExtra("orderCostWork", orderCostWork);
//            intent.putExtra("arrayEqu", equipmentData);
            startActivity(intent);



    }


        private int getPriseByPosition(int numberOfBlock, int position) {

// (Block 2) If you added in block 1 new price list position you must add this position in block 2!

        if (numberOfBlock == 0) {
           int[] arrayPrice0 = getResources().getIntArray(R.array.controller_price);
                return arrayPrice0[position];
            }
            if (numberOfBlock == 1) {
                int[] arrayPrice1 = getResources().getIntArray(R.array.rain_sensor_price);
                return arrayPrice1[position];
            }
            if (numberOfBlock == 2) {
                int[] arrayPrice2 = getResources().getIntArray(R.array.sprinklers_price);
                return arrayPrice2[position];
            }
            if (numberOfBlock == 3) {
                int[] arrayPrice3 = getResources().getIntArray(R.array.nozzle_mp_price);
                return arrayPrice3[position];
            }
            if (numberOfBlock == 4) {
                int[] arrayPrice4 = getResources().getIntArray(R.array.nozzle_spray_price);
                return arrayPrice4[position];
            }
            if (numberOfBlock == 5) {
                int[] arrayPrice5 = getResources().getIntArray(R.array.nozzle_rotor_price);
                return arrayPrice5[position];
            }
            if (numberOfBlock == 6) {
                int[] arrayPrice6 = getResources().getIntArray(R.array.valve_price);
                return arrayPrice6[position];
            }
            if (numberOfBlock == 7) {
                int[] arrayPrice7 = getResources().getIntArray(R.array.box_price);
                return arrayPrice7[position];
            }
            if (numberOfBlock == 8) {
                int[] arrayPrice8 = getResources().getIntArray(R.array.knee_adjustable_price);
                return arrayPrice8[position];
            }
            if (numberOfBlock == 9) {
                int[] arrayPrice9 = getResources().getIntArray(R.array.hydrant_price);
                return arrayPrice9[position];
            }
            if (numberOfBlock == 10) {
                int[] arrayPrice10 = getResources().getIntArray(R.array.hydrant_key_price);
                return arrayPrice10[position];
            }
            if (numberOfBlock == 11) {
                int[] arrayPrice11 = getResources().getIntArray(R.array.valve_drop_price);
                return arrayPrice11[position];
            }
            if (numberOfBlock == 12) {
                int[] arrayPrice12 = getResources().getIntArray(R.array.tube_drop_price);
                return arrayPrice12[position];
            }
            if (numberOfBlock == 13) {
                int[] arrayPrice13 = getResources().getIntArray(R.array.fitting_drop_price);
                return arrayPrice13[position];
            }
            if (numberOfBlock == 14) {
                int[] arrayPrice14 = getResources().getIntArray(R.array.pump_price);
                return arrayPrice14[position];
            }
            if (numberOfBlock == 15) {
                int[] arrayPrice15 = getResources().getIntArray(R.array.pump_automatics_price);
                return arrayPrice15[position];
            }
            if (numberOfBlock == 16) {
                int[] arrayPrice16 = getResources().getIntArray(R.array.electric_cable_price);
                return arrayPrice16[position];
            }
            if (numberOfBlock == 17) {
                int[] arrayPrice17 = getResources().getIntArray(R.array.electric_relay_price);
                return arrayPrice17[position];
            }
            if (numberOfBlock == 18) {
                int[] arrayPrice18 = getResources().getIntArray(R.array.barrel_price);
                return arrayPrice18[position];
            }
            if (numberOfBlock == 19) {
                int[] arrayPrice19 = getResources().getIntArray(R.array.float_barrel_price);
                return arrayPrice19[position];
            }
            if (numberOfBlock == 20) {
                int[] arrayPrice20 = getResources().getIntArray(R.array.clips_material_price);
                return arrayPrice20[position];
            }


            return 0;
        }
        private boolean isEquipmentCountEmpty(int position) {
                String textInPosition = countAmount[position].getText().toString();
                if (!textInPosition.isEmpty() && Integer.parseInt(textInPosition)>0) {
                    return true;
                }
                return false;

        }


    public void onClickCoefficient(View view) {
        RadioButton button = (RadioButton) view;
        int id = button.getId();

        if (id == R.id.radioButtonCoefficient15) {
            costWork =  5 ;
        } else if (id == R.id.radioButtonCoefficient17) {
            costWork = 7;
        }


    }

    public void onClickAutoOrManual(View view) {
        RadioButton button = (RadioButton) view;
        int id = button.getId();

        if (id == R.id.radioButtonAuto) {
            buttonCoeff15.setVisibility(View.VISIBLE);
            buttonCoeff17.setVisibility(View.VISIBLE);
            editTextManualCost.setVisibility(View.INVISIBLE);
            costWork = 7;
            editTextManualCost.setText(null);

        }   else if (id == R.id.radioButtonManual)   {
            costWork = 0;
            buttonCoeff15.setVisibility(View.INVISIBLE);
            buttonCoeff17.setVisibility(View.INVISIBLE);
            editTextManualCost.setVisibility(View.VISIBLE);

        }

    }
}