import javafx.util.Pair;
import org.apache.commons.math3.special.Gamma;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

interface DistributionData extends Runnable {

    public List<Pair<Double, Double>> points(double leftbound, double rightbound, int size);
    public void writeToFile(double leftbound, double rightbound, int size, String name) throws IOException;
}
