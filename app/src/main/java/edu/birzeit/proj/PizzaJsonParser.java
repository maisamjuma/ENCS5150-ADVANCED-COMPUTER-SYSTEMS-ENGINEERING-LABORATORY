package edu.birzeit.proj;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class PizzaJsonParser {


        public static List<Pizza> getObjectFromJson(String json) {
            List<Pizza> Pizzas;
            try {
                Pizzas = new ArrayList<>();
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("types");
                for (int i = 0; i < jsonArray.length(); i++) {
                    String name = jsonArray.getString(i);
                    Pizza pizza = new Pizza(name);
                    Pizzas.add(pizza);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
            return Pizzas;
        }}

