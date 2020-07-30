import scala.collection.immutable.Stack

def backspaceCompare(S: String, T: String): Boolean = {
  val lettersOfS = S.toSeq
  val lettersOfT = T.toSeq


  def recurse(lettersOfS: Seq[Char], lettersOfT: Seq[Char], sStack: Stack[Char], tStack: Stack[Char]): (String, String) = {
    if(lettersOfS.isEmpty && lettersOfT.isEmpty) (sStack.mkString, tStack.mkString)
    else {
      val currS = lettersOfS.head
      val currT = lettersOfT.head
      val newSStack = if(currS == '#') sStack.pop else sStack.push(currS)
      val newTStack = if(currT == '#') tStack.pop else tStack.push(currT)
      recurse(lettersOfS.tail, lettersOfT.tail, newSStack, newTStack)
    }
  }

  val (newS, newT) = recurse(lettersOfS, lettersOfT, Stack.empty[Char], Stack.empty[Char])

  (newS == newT)
}

//println(backspaceCompare("ab#c", "ad#c"))