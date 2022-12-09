/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.thecatproject;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author herna
 */
public class GatoService {
    
    //
    public void despliegaImagen(Gato unGato,ImageIcon img) throws IOException{
        //crear menu para el Joptio
        String menu = "Opciones: \n"
                + "1. Ver Opciones \n"
                + "2. Regresar \n";
        
        String[] opciones = {"Ver otro gato", "Regresar"};
        
        String idGato = unGato.getId();
        String opcion = (String)JOptionPane.showInputDialog(null,menu,idGato, JOptionPane.INFORMATION_MESSAGE, img,opciones,opciones[0]);
        
        int seleccion = -1;
        for(int i=0; i<opciones.length;i++){
            if(opciones.equals(opciones[i])){
                seleccion = i;
            }
        }
        switch (seleccion){
            case 0 -> getGatos();
            default-> {
               break;
            }
        }
    }
    
    
    
    public void getGatos() throws IOException {
 OkHttpClient client = new OkHttpClient();
        MediaType mediaType = com.squareup.okhttp.MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();

//Crear un objeto con formato Json
        String gatoJson = response.body().string();
        //Quitar llave inicial y final
        gatoJson = gatoJson.substring(1, gatoJson.length());
        gatoJson = gatoJson.substring(0, gatoJson.length() - 1);

        System.out.println("gatoJson" + gatoJson);
        //Crear un objeto de la clase Gson
        Gson gson = new Gson();
        Gato gato = gson.fromJson(gatoJson, Gato.class);
        //Probando la informacion que este en el objeto gato
        System.out.println("Gato id: " + gato.getId());
        System.out.println("Gato url: " + gato.getUrl());

        Image image = null;
        try {
            URL url = new URL(gato.getUrl());
            image = ImageIO.read(url);

            //Rendimensionar la imagen obtenida de The Cat API 
            ImageIcon imgGato = new ImageIcon(image);
            if (imgGato.getIconWidth() > 800) {
                Image img = imgGato.getImage();
                Image imgModificada = img.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
                imgGato = new ImageIcon(imgModificada);
            }

            despliegaImagen(gato, imgGato);

        } catch (Exception e) {
            System.out.println("No se pudo crear el objeto Image");

        }

    }
}