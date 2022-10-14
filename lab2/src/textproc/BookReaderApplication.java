package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BookReaderApplication {
	
	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		
		JFileChooser chooser = new JFileChooser();
		File file = null;
		int returnVal = chooser.showOpenDialog(chooser);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
		}
		
		List<TextProcessor> list = new ArrayList<TextProcessor>();
		list.add(new SingleWordCounter("nils"));
		list.add(new SingleWordCounter("norge"));
		list.add(new MultiWordCounter(REGIONS));
		
		Scanner s = new Scanner(file);
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
		//GeneralWordCounter r = new GeneralWordCounter(stopwords);

		while (s.hasNext()) {
			String word = s.next().toLowerCase();

			for(TextProcessor a : list) {
				a.process(word);
			}
			
			r.process(word);
			
		}
		
		s.close();

		for(TextProcessor a : list) {
			a.report();
		}
		//p.report();
		r.report();
		
		//NEW BOOKREADERCONTROLLER
		BookReaderController controller = new BookReaderController((GeneralWordCounter) r);
		

	}

}
