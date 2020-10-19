import javafx.util.Pair;

import java.util.List;

public class MyReader implements Runnable{

    List<List<Double>> buffer;
    List<Boolean> endFlag;

    public MyReader(List<List<Double>> buffer, List<Boolean> endFlag){
        this.buffer = buffer;
        this.endFlag = endFlag;
    }


    @Override
    public void run() {
        while (true){
            System.out.println();
            while(!endFlag.get(0)){}

            for( int i=0; i<100; i++){
                System.out.println( String.format( "%.2f", buffer.get(i).get(0)) + String.format(" %.2f", buffer.get(i).get(1)));
                System.out.println("\n");
            }
            while(endFlag.get(0)){}
        }
    }
}
