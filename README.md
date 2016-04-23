# Markov-Model-for-Text-Prediction
These files are part of a java project to make text prediction based on Markov Model with varing order.
It will randomly produce a new character by using the preceding n characters (n is the number of characters to be used as reference, which can be specified by the user).  For the code to work, one need to first feed the code with a String. The algorithm will learn from the string that for each possible combination of consecutive n characters, what characters appear after these n characters. These information is stored in a HashMap.

To implement the code, you may refer to the following function. 
###
        Before call the 'runModel', enter the following codes:
        EfficientMarkovModel eMarkovModel = new EfficientMarkovModel(5);
		String text = ""; // You need to specify what text paradigm you want to input here
		                  // or you can change this to read string from a file
		int size = 200;   // Just an example here
		int seed = 615;
		runModel(eMarkovModel, text, size, seed);

###
        public void runModel(IMarkovModel markov, String text, int size, int seed) 
        {
                markov.setTraining(text);
                markov.setRandom(seed);
                System.out.println("running with " + markov);
                String s= markov.getRandomText(size);
		        String[] words = s.split("\\s+");
        		int psize = 0;
        		System.out.println("----------------------------------");
        		for(int k=0; k < words.length; k++){
        			System.out.print(words[k]+ " ");
        			psize += words[k].length() + 1;
        			if (psize > 60) {
        				System.out.println();
        				psize = 0;
        			}
        		}
        		System.out.println("\n----------------------------------");
        }
