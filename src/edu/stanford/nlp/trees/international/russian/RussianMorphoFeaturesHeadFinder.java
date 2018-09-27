package edu.stanford.nlp.trees.international.russian;

import edu.stanford.nlp.trees.AbstractCollinsHeadFinder;
import edu.stanford.nlp.trees.HeadFinder;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.util.Generics;

/**
 * @author Ivan Shilin
 * @author Liubov Kovriguina
 */

public class RussianMorphoFeaturesHeadFinder extends AbstractCollinsHeadFinder {

  private static final long serialVersionUID = 3837231832443310386L;

  public RussianMorphoFeaturesHeadFinder() {
    this(new RussianTreebankLanguagePack());
  }

  public RussianMorphoFeaturesHeadFinder(TreebankLanguagePack tlp) {
    super(tlp);

    nonTerminalInfo = Generics.newHashMap();

    // noun phrases
    nonTerminalInfo.put("NP", new String[][] {{"left", "NOUN", "PNOUN", "PRON", "NP", "ADJP"}});

    // verbal nucleus
    nonTerminalInfo.put("VN", new String[][] {{"left", "VERB"}, {"right", "ADJ", "ADJP"}});

    // verb phrase
    nonTerminalInfo.put("VP", new String[][] {{"left", "VN", "VP"}});

    // adjectival phrases
    nonTerminalInfo.put("ADJP", new String[][] {{"left", "ADJ", "VP"}});

    // adverbial phrases
    nonTerminalInfo.put("ADVP", new String[][] {{"left", "ADV"}});

    // compound numerals
    nonTerminalInfo.put("NumP", new String[][] {{"left", "NUM"}});

    // root
    nonTerminalInfo.put(tlp.startSymbol(), new String[][] {{"left", "VP", "VN"/* , "VERB" */},
        {"rightdis", "NOUN", "PNOUN", "ADJ", "NP"}});

  }

  // test
  public static void main(String[] args) {
    HeadFinder hf = new RussianHeadFinder();
    // Tree h = hf.determineHead(Tree.valueOf("((NOUN Кошка) (VERB лезет) (ADP на) (NOUN забор))"));
    Tree h = hf.determineHead(
        Tree.valueOf("(NP (ADJ темно-синем) (NOUN лесу) (VERB трепещут) (NOUN осины))"));
    // Tree h = hf.determineHead(Tree.valueOf("((PNOUN Петя) (NOUN доктор))"));
    System.out.println(h);
  }

}


