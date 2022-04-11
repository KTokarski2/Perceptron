import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String [] args) {

        List<MyVector> trainSetList = new ArrayList<>();
        List<MyVector> testSetList = new ArrayList<>();
        int vectorLength = 0;
        List<String> typesList = new ArrayList<>();


        String trainingSetPath = "data.txt";
        String testSetPath = "testdata.txt";

        //loading training set
        String [] line_Training;
        List<String> fileLines_Training;
        double [] vector;
        String [] vectorS;
        String type;

        try{
            fileLines_Training = new BufferedReader(new FileReader(trainingSetPath))
                    .lines()
                    .collect(Collectors.toList());
            for (int i = 0; i < fileLines_Training.size(); i++) {
                line_Training = fileLines_Training.get(i).split(",");
                type = line_Training[line_Training.length - 1];
                vectorS = Arrays.copyOfRange(line_Training,0,line_Training.length - 1);
                vector = Arrays.stream(vectorS).mapToDouble(Double::parseDouble).toArray();
                vectorLength = vector.length;
                MyVector myVector = new MyVector(vector,type);
                trainSetList.add(myVector);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //loading test set

        String [] line_Test;
        List<String> fileLines_Test;
        double [] vector1;
        String [] vector1S;
        String type1;

        try{
            fileLines_Test = new BufferedReader(new FileReader(testSetPath))
                    .lines()
                    .collect(Collectors.toList());
            for(int i = 0; i < fileLines_Test.size(); i++) {
                line_Test = fileLines_Test.get(i).split(",");
                type1 = line_Test[line_Test.length - 1];
                vector1S = Arrays.copyOfRange(line_Test,0,line_Test.length - 1);
                vector1 = Arrays.stream(vector1S).mapToDouble(Double::parseDouble).toArray();
                MyVector myVector1 = new MyVector(vector1,type1);
                testSetList.add(myVector1);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < testSetList.size(); i++) {
            typesList.add(testSetList.get(i).type);
        }
        Set<String> set = new HashSet<>(typesList);
        typesList.clear();
        typesList.addAll(set);

        Perceptron perceptron = new Perceptron(0.01,typesList);
        perceptron.generateWeightsAndTheta(vectorLength);

        for(int i = 0; i < 100000; i++) {
            perceptron.trainPerceptron(trainSetList);
        }

        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");

        perceptron.usePerceptron(testSetList);





    }

}
