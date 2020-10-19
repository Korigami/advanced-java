import javafx.util.Pair;
import org.apache.commons.math3.special.Gamma;

import java.io.*;
import java.util.List;
import java.util.Vector;

public class GammaData implements DistributionData
{
    double alpha;
    double beta;
    List<Boolean> flag;
    List<Double> point;
    double x;
    double skip;
    double c;

    public GammaData( double alpha, double beta){
        this.alpha = alpha;
        this.beta = beta;
        this.c = Math.pow(beta, alpha) / Gamma.gamma(alpha);
    }

    public GammaData( double alpha, double beta, double x, double skip, List<Double> point, List<Boolean> flag){
        this.alpha = alpha;
        this.beta = beta;
        this.x = x;
        this.skip = skip;
        this.point = point;
        this.flag = flag;
        this.c = Math.pow(beta, alpha) / Gamma.gamma(alpha);
    }

    @Override
    public void run() {
        while (true){
            if(!flag.get(0)){}
            point.set(0, x);
            point.set(1, c * Math.pow(x, alpha - 1) * Math.exp( - beta*x));
            flag.set(0,false);
            x += skip;
        }
    }

    @Override
    public List<Pair<Double, Double>> points(double leftbound, double rightbound, int size){

        Vector< Pair<Double, Double>> result = new Vector <Pair<Double, Double>>();



        double step = (rightbound - leftbound)/ (size - 1);

        for (double x = leftbound; x<= rightbound; x+= step){
            Pair< Double, Double> point = new Pair(x, c * Math.pow(x, alpha - 1) * Math.exp( - beta*x)) ;
            result.add( point);
        }

        return result;
    }

    @Override
    public void writeToFile(double leftbound, double rightbound, int size, String name) throws IOException {

        double step = (rightbound - leftbound)/ (size - 1);

        FileWriter resultFile = new FileWriter(name);

        resultFile.write("x y\n");
        double y;
        for (double x = leftbound; x<= rightbound; x+= step){
            resultFile.write(String.format("%.2f ", x)  );
            resultFile.write( String.format("%.2f", x > 0 ? c * Math.pow(x, alpha - 1) : 0));
            resultFile.write("\n");
        }


        resultFile.close();


    }


}
