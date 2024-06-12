package dam2.practicapmdm.u2.practicar.peliculas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class PeliculasViewModel extends ViewModel {
    private MutableLiveData<Integer> misDatos;
    private final int DELAY=1000;
    private final int MAX_NUM=10000;

    public LiveData<Integer> getPelicula(){
        if(misDatos==null){
            misDatos=new MutableLiveData<Integer>();
            cargaPelicula();
        }
        return misDatos;
    }

    public void cargaPelicula() {
        Random random = new Random();
        new Thread(()->{
            try {
                Thread.sleep((long)(Math.random()*DELAY)+DELAY);
                int i=random.nextInt(MAX_NUM);
                misDatos.postValue(i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
