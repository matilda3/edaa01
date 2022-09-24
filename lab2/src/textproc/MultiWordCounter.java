package textproc;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MultiWordCounter implements TextProcessor {
	private Map<String, Integer> map;
	
	//constructor
	public MultiWordCounter(String[] words) {
		this.map = new TreeMap<String, Integer>();
		for(int i = 0; i < words.length; i++) {
			map.put(words[i], 0);
		}
	}

	@Override
	public void process(String w) {
		for(String key : map.keySet()) {
			if(w.equals(key)) {
				map.put(key,  map.get(key) + 1);
			}
		}
	}

	@Override
	public void report() {
		for(String key : map.keySet()) {
			System.out.println(key + ": " + map.get(key));
		}
	}

}
