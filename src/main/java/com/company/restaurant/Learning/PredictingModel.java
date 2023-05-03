package com.company.restaurant.Learning;

import com.company.restaurant.Models.Food;
import com.company.restaurant.Models.OrderFood;
import org.springframework.stereotype.Component;
import weka.classifiers.trees.RandomForest;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToNominal;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

@Component
public class PredictingModel {
    private static final int NUMBER_OF_COOK = 3;
    private final RandomForest model = new RandomForest();
    private Instances dataset;
    public static final String DIRECTORY = "src/main/resources/data/foodPrepTimeDataset.arff";


    protected Instances getFilteredInstances() throws Exception {
        StringToNominal stringToNominal = new StringToNominal();

        String[] options = new String[2];
        options[0] = "-R";
        options[1] = "first";

        stringToNominal.setOptions(options);

        BufferedReader datafile = readDataFile(DIRECTORY);
        Instances instances = new Instances(datafile);
        stringToNominal.setInputFormat(instances);

        Instances dataset = Filter.useFilter(instances, stringToNominal);

        //set class index to the last attribute
        dataset.setClassIndex(dataset.numAttributes() - 1);

        return dataset;
    }

    @PostConstruct
    public void init() throws Exception {
        long start = System.currentTimeMillis();

        dataset = getFilteredInstances();

        model.buildClassifier(dataset);


        int index = getRandomNumberUsingInts(0, dataset.numInstances());
        System.out.println("Index : " + index);

        Instance myHouse = dataset.get(index);
        System.out.println(myHouse.toString());
        int price = (int) model.classifyInstance(myHouse);
        System.out.println("-------------------------");
        System.out.println("Predicting the time in seconds : " + price);

        long end = System.currentTimeMillis();

        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.println("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
    }

    public int predictFood(OrderFood orderFood, int numOfOrdersBefore, int numOfCooks) throws Exception {
        Food food = orderFood.getProduct();
        Instance instance = dataset.lastInstance();
        String foodName = orderFood.getProduct().getName();
        String characterFilter = "[^\\p{L}\\p{M}\\p{N}\\p{P}\\p{Z}\\p{Cf}\\p{Cs}\\s]";
        String emotionless = foodName.replaceAll(characterFilter, "");

        instance.setValue(0, emotionless);
        instance.setValue(1, orderFood.getQuantity());
        instance.setValue(2, food.getEstimatedTimeByQuantity(orderFood.getQuantity()));
        instance.setValue(3, (double) numOfCooks / numOfOrdersBefore);
        instance.setValue(4, 0);

        int classifyInstance = (int) model.classifyInstance(instance);
        System.out.println(instance.toString());
        System.out.println(foodName + " : " + classifyInstance);

        return classifyInstance;

    }

    public static int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }

    public static BufferedReader readDataFile(String filename) {
        BufferedReader inputReader = null;

        try {
            inputReader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + filename);
        }

        return inputReader;
    }
}
