package com.hirw.maxcloseprice;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class MaxClosePrice {

  public static void main(String[] args) throws Exception{
    if(args.length != 2){
      System.err.println("Usage: MaxClosePrice <input path> <output path>");
      System.exit(-1);
    }

    // define MapReduce job
    Job job = new Job();
    job.setJarByClass(MaxClosePrice.class);
    job.setJobName("MaxClosePrice");

    // set input and output locations
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

    // Set input and output formats
    job.setInputFormatClass(TextInputFormat.class);
    job.setOutputFormatClass(TextOutputFormat.class);

    // set mapper and reducer class
    job.setMapperClass(MaxClosePriceMapper.class);
    job.setReducerClass(MaxClosePriceReducer.class);

    //output types
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(FloatWritable.class);

    //submit job
    System.exit(job.waitForCompletion(true) ? 0 : 1);

  }
}
