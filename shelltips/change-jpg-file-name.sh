 #!/bin/bash
 i=0
 for name in `ls *.jpg`
 do
 #	echo $name $((i++)).jpg
 	mv $name $((i++)).jpg
 done