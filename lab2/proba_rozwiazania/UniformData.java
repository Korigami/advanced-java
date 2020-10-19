import javafx.util.Pair;
import org.apache.commons.math3.special.Gamma;

import java.io.*;
import java.nio.DoubleBuffer;
import java.util.List;
import java.util.Vector;

public class UniformData implements DistributionData{

    double begin;
    double end;
    List<Boolean> flag;
    List<Double> point;
    double x;
    double skip;
    double c;

    public UniformData( double begin, double end){
        this.begin = begin;
        this.end = end;
        this.c = 1/(end-begin);
    }

    public UniformData( double begin, double end, double x, double skip, List<Double> point, List<Boolean> flag){
        this.begin = begin;
        this.end = end;
        this.x = x;
        this.skip = skip;
        this.c = 1/(end-begin);
        this.flag = flag;
        this.point = point;
    }

    @Override
    public void run() {
       while (true){
           while(!flag.get(0)){ }
           point.set(0, x);
           point.set(1, x >= begin && x <= end ? c : 0);
           flag.set(0,false);
           x += skip;
       }
    }

    @Override
    public List<Pair<Double, Double>> points(double leftbound, double rightbound, int size){

        Vector< Pair<Double, Double>> result = new Vector <Pair<Double, Double>>();

        double step = (rightbound - leftbound)/ (size - 1);

        for (double x = leftbound; x<= rightbound; x+= step){
            Pair< Double, Double> point = new Pair(x, x >= begin && x <= end ? c : 0) ;
            result.add( point);
        }

        return result;
    }

    @Override
    public void writeToFile( double leftbound, double rightbound, int size, String name) throws IOException {

        double c = 1/(end - begin);
        double step = (rightbound - leftbound)/ (size - 1);

        FileWriter file = new FileWriter(name);

        file.write("x y\n");

        for (double x = leftbound; x<= rightbound; x+= step){
            file.write( String.format("%.2f ", x)  );
            file.write( String.format( "%.2f", x >= begin && x <= end ? c : 0));
            file.write("\n");
        }
        file.close();
    }
}
