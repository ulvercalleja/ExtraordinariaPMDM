package dam2.practicapmdm.u2.examenes.examen2Trimestre.ejercicio1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class PoemaViewModel extends ViewModel {
    private MutableLiveData<String> misDatos;
    String poema [] = {
            "Si", "hay", "hombres", "que", "contienen", "un", "alma", "sin", "fronteras",
            "una", "esparcida", "frente", "de", "mundiales", "cabellos, ", "cubierta",
            "de", "horizontes, ", "barcos", "y", "cordilleras." , "FIN"
    };
    private final int DELAY=140;
    private final int MAX_NUM=400;

    public LiveData<String> getPoema(){
        if(misDatos==null){
            misDatos=new MutableLiveData<>();
        }
        return misDatos;
    }

    public void cargaPoema() {
        Random random = new Random();
        new Thread(()->{
            try {
                for (String palabra: poema){
                    Thread.sleep((long)(Math.random()*DELAY)+DELAY);
                    misDatos.postValue(palabra);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
