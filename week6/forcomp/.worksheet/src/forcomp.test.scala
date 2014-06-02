package forcomp

import Anagrams._

object test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(420); 
  def occurrenceAnagrams(occ: Occurrences): List[Sentence] = {
	  for {
		  comb <- combinations(occ)
		  if dictionaryByOccurrences contains comb
		  word <- dictionaryByOccurrences(comb)
		  possibleSentence <- List() :: occurrenceAnagrams(subtract(occ, comb))
		  if possibleSentence != List() || subtract(occ, comb) == List()
		} yield (word :: possibleSentence)
  };System.out.println("""occurrenceAnagrams: (occ: forcomp.Anagrams.Occurrences)List[forcomp.Anagrams.Sentence]""");$skip(45); val res$0 = 
  occurrenceAnagrams(wordOccurrences("yes"));System.out.println("""res0: List[forcomp.Anagrams.Sentence] = """ + $show(res$0))}
}
