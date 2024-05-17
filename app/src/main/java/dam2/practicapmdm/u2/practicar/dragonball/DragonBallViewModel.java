package dam2.practicapmdm.u2.practicar.dragonball;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class DragonBallViewModel extends ViewModel {
    private MutableLiveData<Integer> misDatos;
    private final int DELAY=1000;
    private final int MAX_NUM=10000;

    public LiveData<Integer> getPersonaje(){
        if(misDatos==null){
            misDatos=new MutableLiveData<Integer>();
            cargaPersonaje();
        }
        return misDatos;
    }

    public void cargaPersonaje() {
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
