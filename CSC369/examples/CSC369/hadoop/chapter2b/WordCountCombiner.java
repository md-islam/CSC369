

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

/**
 * A combiner class that just emits the sum of the input values.
 * 
 * Note that a combiner can be used since addition is a monoid 
 * over a set of integer numbers.
 *
 * @author Mahmoud Parsian
 *
 */
public class WordCountCombiner
    extends Reducer<Text, IntWritable, Text, IntWritable> {
   
    // This method is called once for each key. Most applications will 
    // define their reduce class by overriding this method. The default 
    // implementation is an identity function.
    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context)
       throws IOException, InterruptedException {
       int sum = 0;
       for (IntWritable count : values) {
           if (count != null) {
                sum += count.get();
           }
       }
       context.write(key, new IntWritable(sum));
    }
    
}


