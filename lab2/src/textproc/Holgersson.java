package textproc;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		
		long t0 = System.nanoTime();
		
		//TextProcessor p = new SingleWordCounter("nils");
		List<TextProcessor> list = new ArrayList<TextProcessor>();
		list.add(new SingleWordCounter("nils"));
		list.add(new SingleWordCounter("norge"));
		list.add(new MultiWordCounter(REGIONS));
		
		//TextProcessor p = new MultiWordCounter(REGIONS);

		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		
		while(scan.hasNext()) {
			String word = scan.next().toLowerCase();
			stopwords.add(word); //add to set
		}
		scan.close();
		
		TextProcessor r = new GeneralWordCounter(stopwords);

		while (s.hasNext()) {
			String word = s.next().toLowerCase();

			for(TextProcessor a : list) {
				a.process(word);
			}
			
			r.process(word);
			
			
			//p.process(word);
		}

		s.close();

		for(TextProcessor a : list) {
			a.report();
		}
		//p.report();
		r.report();
		
		long t1 = System.nanoTime();
		System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");
	}
}