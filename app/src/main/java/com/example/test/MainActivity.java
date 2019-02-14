package com.example.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum state {
        RESTAURANT, CUISINE, ITEM, DESCRIPTION
    }

    public static final String chinese = "Chinese";
    public static final String schezwan = "Schezwan";
    public static final String indian = "Indian";
    public static final String nonveg = "Non Veg";
    public static final String veg = "Veg";
    public static final String andhra = "Andhra";
    public static final String tn = "Tamil Nadu";

    String description = "";
    String price = "";
    String[] cuisines;
    String[] items;

    state current_state = state.RESTAURANT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup restaurantGroup = (RadioGroup) findViewById(R.id.restaurant);

        restaurantGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged( RadioGroup group, int checkedId ) {
                Log.w("Sai", "Here1");
                Log.w("Sai", String.valueOf(checkedId));
                switch(checkedId) {
                    case R.id.chettinad: {
                        Log.w("Sai", "Here3");
                        cuisines = new String[3];
                        cuisines[0] = chinese;
                        cuisines[1] = schezwan;
                        cuisines[2] = indian;
                        break;
                    }
                    case R.id.kfc: {
                        cuisines = new String[2];
                        cuisines[0] = nonveg;
                        cuisines[1] = veg;
                        break;
                    }
                    case R.id.sshyderabad: {
                        cuisines = new String[2];
                        cuisines[0] = andhra;
                        cuisines[1] = tn;
                        break;
                    }
                    default: {
                        cuisines = new String[0];
                    }
                }
                current_state = state.CUISINE;
                clearOthers();
                renderCuisines();
            }
        });

        RadioGroup cuisineGroup = (RadioGroup) findViewById(R.id.cuisineList);

        cuisineGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged( RadioGroup group, int checkedId ) {
                RadioButton button = (RadioButton) findViewById(checkedId);
                String type = (String) button.getTag();
                Log.w("Sai", type);
                items = new String[0];
                if (type.equals(chinese)) {
                    items = new String[1];
                    items[0] = "Chinese Noodles";
                } else if (type.equals(schezwan)) {
                    items = new String[1];
                    items[0] = "Schezwan Noodles";
                } else if (type.equals(indian)) {
                    items = new String[1];
                    items[0] = "Chapathi";
                } else if (type.equals(nonveg)) {
                    items = new String[1];
                    items[0] = "Chicken 65";
                } else if (type.equals(andhra)) {
                    items = new String[1];
                    items[0] = "Biriyani";
                } else if (type.equals(tn)) {
                    items = new String[1];
                    items[0] = "Sambar";
                } else if (type.equals(veg)) {
                    items = new String[1];
                    items[0] = "Paneer 65";
                }
                current_state = state.CUISINE;
                clearOthers();
                renderMenu();
            }
        });

        RadioGroup menuGroup = (RadioGroup) findViewById(R.id.menu);

        menuGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged( RadioGroup group, int checkedId ) {
                RadioButton button = (RadioButton) findViewById(checkedId);
                String type = (String) button.getTag();
                items = new String[0];
                if (type.equals("Chinese Noodles")) {
                    description = "Chicken noodles is a soup made from chicken, simmered in water, usually with various other ingredients.";
                    price = "100";
                } else if (type.equals("Schezwan Noodles")) {
                    description = "It has bold flavours, particularly the pungency and spiciness resulting from liberal use of garlic and chili peppers, as well as the unique flavour of Sichuan pepper.";
                    price = "200";
                } else if (type.equals("Chapathi")) {
                    description = "Chapatis are made of whole wheat flour known as atta, mixed into dough with water and optional salt in a mixing utensil called a parat, and is cooked on a tava";
                    price = "50";
                } else if (type.equals("Chicken 65")) {
                    description = "Chicken 65 is a spicy, deep-fried chicken dish originating from Hotel Buhari,[2] Chennai,[1] India, as an entrée, or quick snack.";
                    price = "120";
                } else if (type.equals("Biriryani")) {
                    description = "Biryani (pronounced [bɪr.jaːniː]), also known as biriyani, biriani, birani or briyani, is a mixed rice dish that originates from the Muslims of the Indian subcontinent.";
                    price = "210";
                } else if (type.equals("Sambar")) {
                    description = "Sambar, also spelled sambhar or sambaar, and pronounced saambaar, is a lentil-based vegetable stew or chowder, cooked with tamarind broth.";
                    price = "80";
                } else if (type.equals("Paneer 65")) {
                    description = "Paneer (pronounced [pəniːr]) is a fresh cheese common in the Indian subcontinent. It is an unaged, non-melting farmer cheese made by curdling milk with a vegetable-derived acid, such as lemon juice.";
                    price = "150";
                }
                current_state = state.ITEM;
                clearOthers();
                renderDescription();
            }
        });
    }

    public void clearOthers() {
        switch(current_state) {
            case RESTAURANT: {
                RadioGroup cuisinesGroup = (RadioGroup) findViewById(R.id.cuisineList);
                cuisinesGroup.removeAllViews();
            }
            case CUISINE: {
                RadioGroup menuGroup = (RadioGroup) findViewById(R.id.menu);
                menuGroup.removeAllViews();
            }
            case ITEM: {
                TextView description = (TextView) findViewById(R.id.description);
                description.setText("");
                TextView price = (TextView) findViewById(R.id.price);
                price.setText("");
                TableRow submitrow = (TableRow) findViewById(R.id.submitrow);
                submitrow.removeAllViews();
            }
        }
    }

    public void renderCuisines() {
        Log.w("Sai", "Here2");
        RadioGroup cuisinesGroup = (RadioGroup) findViewById(R.id.cuisineList);
        cuisinesGroup.removeAllViews();

        for (int i = 0; i < cuisines.length; i++) {
            RadioButton newCuisine = new RadioButton(this);
            newCuisine.setText(cuisines[i]);
            newCuisine.setTag(cuisines[i]);
            cuisinesGroup.addView(newCuisine, i);
        }
    }

    public void renderMenu() {
        Log.w("Sai", "Here2");
        RadioGroup menuGroup = (RadioGroup) findViewById(R.id.menu);
        menuGroup.removeAllViews();

        for (int i = 0; i < items.length; i++) {
            RadioButton newItem = new RadioButton(this);
            newItem.setText(items[i]);
            newItem.setTag(items[i]);
            menuGroup.addView(newItem, i);
        }
    }

    public void renderDescription() {
        TextView descriptionView = (TextView) findViewById(R.id.description);
        descriptionView.setText(description);
        TextView priceView = (TextView) findViewById(R.id.price);
        priceView.setText(price);

        TableRow submitrow = (TableRow) findViewById(R.id.submitrow);

        Button submitButton = new Button(this);
        submitButton.setText("Order");
        submitButton.setLayoutParams(new TableRow.LayoutParams(2));

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Toast.makeText(MainActivity.this, "Order Placed!",
                        Toast.LENGTH_LONG).show();
                current_state = state.RESTAURANT;
                clearOthers();
                RadioGroup restaurantGroup = (RadioGroup) findViewById(R.id.restaurant);
                restaurantGroup.clearCheck();
            }
        });

        submitrow.addView(submitButton);
    }
}
