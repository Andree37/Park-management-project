package Statistics;


import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import model.Gestor.Place;
import model.Gestor.ResultadoPercurso;

public class ChartBar {
    
    private ArrayList<ResultadoPercurso> results;
    private HashMap<ResultadoPercurso,Integer> repetitions;

    public ChartBar() {
        results = new ArrayList<>();
        repetitions = new HashMap<>();
    }
    
    public void start(Stage stage) {
        stage.setTitle("Bar Chart Most Visited Places");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc
                = new BarChart<>(xAxis, yAxis);
        bc.setTitle("Most Visited Places");
        xAxis.setLabel("Places");
        yAxis.setLabel("Times");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Parque");
        for (ResultadoPercurso r : getResults()) {
            for (Place p : r.getListPlacesCopy()) {
                series1.getData().add(new XYChart.Data(p.getName(),repetitions.get(p)));
            }
        }

        Scene scene = new Scene(bc, 800, 600);
        bc.getData().add(series1);
        stage.setScene(scene);
        stage.show();
    }
    
    public ArrayList<ResultadoPercurso> getResults() {
        return results;
    }
    
    public void addResult(ResultadoPercurso result) {   
        if(results.contains(result)) {
            int repetition = repetitions.get(result);
            repetition++;
            repetitions.put(result, repetition);
        }
        results.add(result);
    }
}
