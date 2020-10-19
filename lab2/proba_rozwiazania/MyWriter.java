import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class MyWriter implements Runnable {
    List<List<Double>> buffer;
    List<Double> point;
    List<Boolean> flag;
    List<Boolean> endFlag;

    public MyWriter(List< List<Double>> buffer, List<Double> point, List<Boolean> flag, List<Boolean> endFlag){
        this.buffer = buffer;
        this.point = point;
        this.flag = flag;
        this.endFlag = endFlag;
    }

    @Override
    public void run() {
        while(true){
            for( int i=0; i< 100; i++){
                while( flag.get(0)){
                }
                buffer.add( new ArrayList<Double>(point));
                flag.set(0, true);
            }
            flag.set(0, false);
            endFlag.set(0,true);
            while(true){}
        }
    }
}
