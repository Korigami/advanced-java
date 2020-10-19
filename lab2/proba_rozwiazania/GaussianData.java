import javafx.util.Pair;
import org.apache.commons.math3.special.Gamma;

import java.io.*;
import java.util.List;
import java.util.Vector;

public class GaussianData implements DistributionData
{
    double mean;
    double sd;

    List<Boolean> flag;
    List<Double> point;
    double x;
    double skip;
    double c;

    public GaussianData( double mean, double sd){
       this.mean = mean;
       this.sd = sd;
        this.c = 1/(sd * Math.sqrt(2 * Math.PI)) ;
    }

    public GaussianData( double mean, double sd, double x, double skip, List<Double> point, List<Boolean> flag){
        this.mean = mean;
        this.sd = sd;
        this.x = x;
        this.skip = skip;
        this.point = point;
        this.flag = flag;
        this.c = 1/(sd * Math.sqrt(2 * Math.PI)) ;
    }

    @Override
    public List<Pair<Double, Double>> points(double leftbound, double rightbound, int size){

        Vector< Pair<Double, Double>> result = new Vector <Pair<Double, Double>>();

        double step = (rightbound - leftbound)/ (size - 1);

        for (double x = leftbound; x<= rightbound; x+= step){
            Pair< Double, Double> point = new Pair(x, c * Math.exp( - (x - mean)*(x-mean)/(2*sd*sd))) ;
            result.add( point);
        }

        return result;
    }
    @Override
    public void run() {
        while (true){
            if(!flag.get(0)){}
            point.set(0, x);
            point.set(1, c * Math.exp( - (x - mean)*(x-mean)/(2*sd*sd)));
            flag.set(0,false);
            x += skip;
        }
    }

    @Override
    public void writeToFile(double leftbound, double rightbound, int size, String name) throws IOException {

        double step = (rightbound - leftbound)/ (size - 1);

        FileWriter resultFile = new FileWriter(name);

        Double y;
        resultFile.write("x y\n");
        for (double x = leftbound; x<= rightbound; x+= step){
            resultFile.write(String.format("%.2f ", x)  );
            resultFile.write(  String.format("%.2f", c * Math.exp( - (x - mean)*(x-mean)/(2*sd*sd))) );
            resultFile.write("\n");
        }

        resultFile.close();

    }


}
