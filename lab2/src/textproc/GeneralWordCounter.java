package textproc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor {
	private Map<String, Integer> m;
	private Set<String> set;
	
	//constructor
	public GeneralWordCounter(Set<String> u) {
		this.m = new HashMap<String, Integer>();
		this.set = u;
	}

	@Override
	public void process(String w) {
		//as long as the word isn't in the set
		if(!(set.contains(w))) {
			//check if the map already has the word as a key
			if(m.containsKey(w)) {
				//in that case just incr val
				m.put(w, m.get(w) + 1);
			}else {
				//add the key to map
				m.put(w, 1);
			}
		}
	}

	@Override
	public void report() {
		/*for(String key : m.keySet()) {
			if(m.get(key) >= 200) {
				System.out.println(key + ": " + m.get(key));
			}			
		}*/
		
		Set<Map.Entry<String, Integer>> wordSet = m.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		
		//check fatt innan sjalv
		Collections.sort(wordList, (map1, map2) -> {
			//System.out.println(map1.getValue() + " " + map2.getValue());
			if(map2.getValue().equals(map1.getValue()) ){ //USE .equals, not ==
				//System.out.println(map1.getKey() + " " + map2.getKey());
				//System.out.println(map1.getKey().compareTo(map2.getKey()));
				return map1.getKey().compareTo(map2.getKey());
			}else {
				return map2.getValue().compareTo(map1.getValue());
			}
		});
		
		for(int i = 0; i < 5; i++) {
			System.out.println(wordList.get(i));
		}
	}

}
