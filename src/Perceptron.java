import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Perceptron {


    double learningConstance;
    double [] weights;
    double theta;
    List<String> typesList;
    int fnet;

    public Perceptron (double learningConstance, List<String> typesList) {
        this.learningConstance = learningConstance;
        this.typesList = typesList;

    }

    public void generateWeightsAndTheta(int weightsLength) {
        this.weights = new double[weightsLength];
        for(int i = 0; i < weights.length; i++) {
            weights[i] = Math.random();
        }
        this.weights = normalization(weights);
        this.theta = Math.random();
    }

    public void trainPerceptron(List<MyVector> list) {

        String classified;
        MyVector listVector;
        double net;

        int quantity = list.size();
        int correct = 0;

        for(int i = 0; i < list.size(); i++) {
           listVector = list.get(i);
           net = countHyperPlanes(weights, listVector.vector, theta);
           if(net >= 0) {
               fnet = 1;
           }else
               fnet = 0;
           if(fnet == 1) {
               classified = typesList.get(0);
               if(classified.equals(listVector.type)) {
                   //System.out.println("Classification succesful");
                   //System.out.println("Classified as: " + typesList.get(0));
                   correct++;
               }else if(!classified.equals(listVector.type)) {
                   //System.out.println("Unsuccesful classification");
                   //System.out.println("Classified as: " + typesList.get(0) + ", " + " in fact is: " + listVector.type);
                   updateWeights(0,1);
                   updateTheta(0,1);
               }
           }else if(fnet == 0) {
               classified = typesList.get(1);
               if(classified.equals(listVector.type)) {
                   //System.out.println("Classification succesful");
                   //System.out.println("Classified as: " + typesList.get(1));
                   correct++;
               }else if (!classified.equals(listVector.type)) {
                   //System.out.println("Unsuccesful classification");
                   //System.out.println("Classified as: " + typesList.get(1) + ", " + " in fact is: " + listVector.type);
                   updateWeights(1,0);
                   updateTheta(1,0);
               }
           }
        }
        double acc = ((double)correct / quantity) * 100;
        System.out.println("Acc after iteration: " + acc + "%");

    }

    public void usePerceptron(List<MyVector> list) {
       String classified;
       MyVector listVector;
       double net;
       int quantity = list.size();
       int correct = 0;

       for(int i = 0; i < list.size(); i++) {
           listVector = list.get(i);
           net = countHyperPlanes(weights,listVector.vector,theta);
           if(net >= 0) {
               fnet = 1;
           }else
               fnet = 0;
           if(fnet == 1) {
               classified = typesList.get(0);
               if(classified.equals(listVector.type)) {
                   correct++;
               }else if (!classified.equals(listVector.type)) {
                   System.out.println("Unsuccesful classification");
               }
           }else if(fnet == 0) {
               classified = typesList.get(1);
               if(classified.equals(listVector.type)) {
                   correct++;
               }else if(!classified.equals(listVector.type)) {
                   System.out.println("Unsuccesful classification");
               }

           }
       }

       double acc = ((double)correct / quantity) * 100;
        System.out.println("Accuracy after iteration: " + acc);

    }

    public void updateWeights(int expected, int fnet) {
        double [] nWeights = new double[weights.length];
        for(int i = 0; i < weights.length; i++) {
            nWeights[i] = weights[i] + learningConstance * (expected - fnet);
        }
        this.weights = normalization(nWeights); //wartosc dyskretna
    }

    public void updateTheta(int expected, int fnet) {
        double nTheta;
        nTheta = theta - learningConstance * (expected - fnet);
        this.theta = nTheta; //wartosc dyskretna
    }

    public double countHyperPlanes(double [] weights, double [] vector, double theta) {
        double sum = 0.0;
        double net;
        for(int i = 0; i < weights.length; i++) {
            sum += weights[i] * vector[i];
        }
        net = sum - theta;
        return net;
    }

    public double [] normalization(double [] entry) {
        double value = 0.0;
        for (int i = 0; i < entry.length; i++) {
            value += Math.pow(entry[i],2);
        }
        value = Math.sqrt(value);
        for(int i = 0; i < entry.length; i++) {
            entry[i] = entry[i] / value;
        }
        return entry;
    }

    @Override
    public String toString() {
        return "Perceptron{" +
                "learningConstance=" + learningConstance +
                ", weights=" + Arrays.toString(weights) +
                ", theta=" + theta +
                '}';
    }
}
