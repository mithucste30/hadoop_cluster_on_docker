package com.hirw.maxcloseprice;

import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxClosePriceReducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {

  @Override
  public void reduce(Text key, Iterable<FloatWritable> values, Context context)
      throws IOException, InterruptedException{

    Float maxClosePrice = Float.MIN_VALUE;

    //iterate all close prices for a year and calculate maximum
    for (FloatWritable value: values){
      maxClosePrice = Math.max(maxClosePrice, value.get());
    }

    // write output
    context.write(key, new FloatWritable(maxClosePrice));
  }
}
