package vm;
import java.io.IOException;
import java.util.*;
 
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
 
public class VectorMatrixMulti {
 
    public static class Map extends Mapper<LongWritable, Text, Text, Text> {
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            Configuration conf = context.getConfiguration();
            int m = Integer.parseInt(conf.get("m"));
            int p = Integer.parseInt(conf.get("p"));
            System.out.println("processing: "+value.toString());
            System.out.println("m="+m+", p="+p);
            String[] values = value.toString().split(",");
            Text outputKey = new Text();
            
            if (values[0].equals("A"))
                for (int i = 1; i <= p; i++) {
                	outputKey.set(values[1] + "," + i);
                    context.write(outputKey, value);
                    System.out.println("wrote: key"+outputKey+", value="+value);
                }
            else 
            	for (int i = 1; i <= m; i++) {
                    outputKey.set(i + "," + values[1]);
                    context.write(outputKey, value);
                    System.out.println("wrote: key="+outputKey+", value="+value);
                }
        }
    }
 
    public static class Reduce extends Reducer<Text, Text, Text, Text> {
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            
        	int n = Integer.parseInt(context.getConfiguration().get("n"));
            double output = 0.0;
            System.out.println("n="+n);
            System.out.println("reducing key="+key.toString());
            
            String[] A = null, B = null;
            for ( Text vector : values ){
            	System.out.println("value="+vector.toString());
            	if ( vector.toString().startsWith("A") )
            		A = vector.toString().split(",");
            	else
            		B = vector.toString().split(",");
            }
            System.out.println(A[0]+", "+B[0]);
            
            for (int i = 0; i < n; i++){
            	double a = Double.valueOf(A[i+2]);
            	double b = Double.valueOf(B[i+2]);
            	System.out.println("a="+String.valueOf(a)+", b="+String.valueOf(b));
            	output += (a*b);
            }
            
            context.write(null, new Text("("+key+")," + String.valueOf(output)));
        }
    }
 
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        // A is an m-by-n matrix; B is an n-by-p matrix.
        conf.set("m", "3");
        conf.set("n", "5");
        conf.set("p", "4");
 
        Job job = new Job(conf, "VectorMatrixMultiplication");
        job.setJarByClass(VectorMatrixMulti.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
 
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);
 
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
 
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
 
        job.waitForCompletion(true);
    }
}