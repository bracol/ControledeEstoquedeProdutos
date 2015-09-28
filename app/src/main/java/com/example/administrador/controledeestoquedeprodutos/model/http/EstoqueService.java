package com.example.administrador.controledeestoquedeprodutos.model.http;

import android.content.Entity;
import android.net.http.HttpResponseCache;
import android.util.Log;

import com.example.administrador.controledeestoquedeprodutos.model.entidade.Estoque;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 28/09/2015.
 */
public class EstoqueService {
    private static final String URL = "http://10.11.21.193:4000/api/v1/products";

    private EstoqueService() {
        super();
    }

    public static List<Estoque> getEstoques() {
        List<Estoque> estoques = new ArrayList<>();


        try {
            //url que sera buscada na conexão, podendo ser concatenada com o id da da aplicação
            URL url = new URL(URL);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //método de request, podendo ser get, post, put ou delete
            conn.setRequestMethod("GET");
            //tipo de resquest e tipo de aplicação, podendo ser xml ou json
            conn.setRequestProperty("Accept", "application/json");

            //codigo corresponde a ação, 200 para sucesso, 400 para não encontrado
            int responseCode = conn.getResponseCode();

            Log.i("getAdressByZipCode", "Codigo de retorno de requisição de cep: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                //throw new RuntimeException("Error code " + responseCode);
                //InputStream manipulador de arquivo e contem o json
                //tem que adicionar 3 dependencias do jackson tbm
                InputStream inputStream = conn.getInputStream();

                //lendo json
                //ObjectMapper é que da acesso a biblioteca do jackson
                ObjectMapper objectMapper = new ObjectMapper();
                CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Estoque.class);
                estoques = objectMapper.readValue(inputStream, collectionType);
            }
            conn.disconnect();

        } catch (Exception e) {
            //faz aparecer mensagem no logcat do android
            Log.e(EstoqueService.class.getName(), e.getMessage());
        }


        return estoques;
    }


    public static Estoque postEstoque(Estoque estoque) {
        Estoque estoque2 = null;
        try {
            //url que sera buscada na conexão, podendo ser concatenada com o id da da aplicação
            URL url = new URL(URL);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //método de request, podendo ser get, post, put ou delete
            conn.setRequestMethod("POST");
            //falando que vai ser do tipo output
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //tipo de resquest e tipo de aplicação, podendo ser xml ou json
            conn.setRequestProperty("CONTENT-TYPE", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            //throw new RuntimeException("Error code " + responseCode);
            //OutputStream manipulador para escrita de  arquivo e contem o json
            //tem que adicionar 3 dependencias do jackson tbm
            OutputStream outputStream = conn.getOutputStream();
            InputStream inputStream = conn.getInputStream();

            //lendo json
            //ObjectMapper é que da acesso a biblioteca do jackson
            ObjectMapper objectMapper = new ObjectMapper();
            estoque2 = objectMapper.readValue(inputStream, Estoque.class);
            objectMapper.writeValue(outputStream, estoque);





            outputStream.flush();
            outputStream.close();

            //codigo corresponde a ação, 200 para sucesso, 400 para não encontrado
            int responseCode = conn.getResponseCode();

            conn.disconnect();

        } catch (Exception e) {
            //faz aparecer mensagem no logcat do android
            Log.e(EstoqueService.class.getName(), e.getMessage());
        }

        return estoque2;

    }
}
