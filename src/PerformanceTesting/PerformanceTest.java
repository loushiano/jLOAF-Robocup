package PerformanceTesting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jLOAF.casebase.CaseBase;
import org.jLOAF.performance.PerformanceEvaluator;
import org.jLOAF.preprocessing.filter.CaseBaseFilter;
import org.jLOAF.preprocessing.filter.casebasefilter.Sampling;
import org.jLOAF.preprocessing.filter.featureSelection.WeightsSeperatorFilter;
import org.jLOAF.preprocessing.standardization.Standardization;

import AgentModules.RoboCupAgent;
import CasebaseCreation.LogFile2CaseBase;

/***
 * This will create an agent with one caseBase and use a testBase to measure its performance. It will output all the performance measures such as
 * accuracy, recall, precision, f-measure.
 * @author Sacha Gunaratne 
 * @since 2017 May 
 ***/
public class PerformanceTest extends PerformanceEvaluator {
	
	public static void main(String a[]) throws IOException{
		String [] filenames = {"Data/Carleton_1_wstate.lsf","Data/University_1_wstate.lsf"};
		String output_filename = "Results/kordered20_state.csv";
		
		
		CaseBaseFilter WSF = new WeightsSeperatorFilter(null);
		CaseBaseFilter standardize = new Standardization(WSF);
		//CaseBaseFilter sample = new Sampling(standardize);
		PerformanceTest pt = new PerformanceTest();
		pt.PerformanceEvaluatorMethod(filenames, standardize, output_filename);
	}

	@Override
	public RoboCupAgent createAgent() {
		RoboCupAgent agent = new RoboCupAgent();
		return agent;
	}

	@Override
	public String[] createArrayOfCasebaseNames(String[] filenames) throws IOException {
		LogFile2CaseBase lg2cb = new LogFile2CaseBase();
		int count = 0;
		String [] cbnames = new String [filenames.length];
		
		for(String s: filenames){
			String str = "Data/cb"+count+".cb";
			cbnames[count]=str;
			lg2cb.logParser(s,str);
			count++;
		}
		return cbnames;
	}
}
