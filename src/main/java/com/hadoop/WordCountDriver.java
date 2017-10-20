package com.hadoop;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

public class WordCountDriver {

	public static void main(String[] args) {
		JobClient client = new JobClient();
		JobConf conf = new JobConf(WordCountDriver.class);
		
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(conf, new Path("/user/imrankhanshajahan/HDP/input/sample.txt"));
		FileOutputFormat.setOutputPath(conf, new Path("/user/imrankhanshajahan/HDP/output"));
		
		conf.setMapperClass(WordCountMapper.class);
		conf.setReducerClass(WordCountReducer.class);
		//conf.setCombinerClass(WordCountReducer.class);
		
		client.setConf(conf);
		
		try {
			JobClient.runJob(conf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
