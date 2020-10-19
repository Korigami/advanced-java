//package mini.ap.lab1;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

    public class LineGraphics extends Application {

        @Override public void start(Stage stage) throws IOException {

            stage.setTitle("Gaussian Distribution Test");

            NumberAxis xAxis = new NumberAxis(-10, 10, 10);
            xAxis.setLabel("x");

            NumberAxis yAxis = new NumberAxis(0, 0.5, 0.01);
            yAxis.setLabel("y");

            LineChart lineChart = new LineChart(xAxis, yAxis);

            XYChart.Series series = new XYChart.Series();
            series.setName("Gaussian distribution");

            /* Gaussian
            double mean = 0.0; double sd = 1.0;
            DistributionData data = new GaussianData(mean, sd);
            */


            /* Gamma
            double alpha = 2;
            double beta = 2;
            DistributionData data = new GammaData(alpha, beta);
*/

            /* Uniform*/
            double begin = -5;
            double end = 5;
            DistributionData data = new GaussianData(0, 5);

            data.writeToFile(-10, 10, 100, "UniformData.txt");

            int size = 1000;
            double leftbound = -10;
            double rightbound = 10;


            List<Pair<Double, Double>> list = data.points(leftbound, rightbound, size);


            for (int i = 0; i < size; i++) {
                    series.getData().add(new XYChart.Data(list.get(i).getKey(), list.get(i).getValue()));
            }

            lineChart.getData().add(series);


            Scene scene = new Scene(lineChart, 1000, 800);
            stage.setScene(scene);
            stage.show();



            List< List<Double>> buffer = new ArrayList< List<Double>>(10);

            List<Boolean> flag = new ArrayList<Boolean>(1);
            flag.add(true);

            List<Double> point = new ArrayList<>(2);
            point.add(0.0);
            point.add(0.1);

            double x=-5;
            double skip = 0.1;
            List<Boolean> endFlag = new ArrayList<Boolean>(1);
            endFlag.add(false);

            Thread writer = new Thread( new MyWriter(buffer, point, flag, endFlag));
            Thread distribution = new Thread( new GaussianData(0, 1, x, skip, point, flag));
            Thread reader = new Thread( new MyReader(buffer, endFlag));

            distribution.start();
            writer.start();
            reader.start();

        }

        public static void main(String[] args) {

            launch(args);
        }
    }
