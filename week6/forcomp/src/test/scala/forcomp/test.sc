package forcomp

import Anagrams._

object test {
  def occurrenceAnagrams(occ: Occurrences): List[Sentence] = {
	  for {
		  comb <- combinations(occ)
		  if dictionaryByOccurrences contains comb
		  word <- dictionaryByOccurrences(comb)
		  possibleSentence <- List() :: occurrenceAnagrams(subtract(occ, comb))
		  if possibleSentence != List() || subtract(occ, comb) == List()
		} yield (word :: possibleSentence)
  }                                               //> occurrenceAnagrams: (occ: forcomp.Anagrams.Occurrences)List[forcomp.Anagrams
                                                  //| .Sentence]
  occurrenceAnagrams(wordOccurrences("yes"))      //> res0: List[forcomp.Anagrams.Sentence] = List(List(yes))
}