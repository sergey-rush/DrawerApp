package com.asna.rush.data;

import com.asna.rush.core.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProductProvider {

    public ProductProvider() {

    }

    public List<Product> getProducts(int pharmacyId, String query) {

        List<Product> products = new ArrayList<Product>();
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuffer buffer = null;

        try {

            String address = String.format("http://192.168.0.19/data/search/%d?q=%s", pharmacyId, query);

            URL url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            String strJSON = buffer.toString();
            JSONArray jArray = (JSONArray) new JSONTokener(strJSON).nextValue();

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jObject = jArray.getJSONObject(i);

                int id = jObject.getInt("Id");
                String name = jObject.getString("Name");
                Product product = new Product(id, name);
                products.add(product);
            }


        } catch (MalformedURLException mex) {
            mex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }

            } catch (IOException ioex) {
                ioex.printStackTrace();
            }

        }

        return products;

    }
}
