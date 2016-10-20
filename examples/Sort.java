import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;

import java.util.ArrayList;
import java.util.List;

public class Sort {
   public static void main(String[] args) {
      SparkConf conf = new SparkConf().setAppName("Sort");
      JavaSparkContext jsc = new JavaSparkContext(conf);
      
      // EDIT HERE
      // Read randoms.txt file from HDFS
      JavaRDD<String> dataRdd = jsc.textFile("test.txt");

      JavaRDD<Integer> newRdd = dataRdd.map(new Function<String,Integer>() {
         public Integer call(String str) {
            return Integer.parseInt(str);
         }
      }); 

      // EDIT HERE
      // Create function object that receives an integer and simply returns
      //    that integer

      Function<Integer,Integer> identity = new Function<Integer,Integer>() {
         @Override
         public Integer call(Integer o) {
            return o;
         }
      };


      JavaRDD<Integer> sortedRdd = newRdd.sortBy(identity,true,4);

      sortedRdd.saveAsTextFile("/user/spark/output"); 
   
      jsc.stop();
   }

}
