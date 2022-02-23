# Empty Streams

### What is the motivation?

Heinz Kabutz once noticed with a colleague at a conference that empty streams create a useless stream pipeline. He wrote
about the problem in his blog on https://javaspecialists.eu/archive/Issue295-Faster-Empty-Streams.html
and proposed a solution. He submitted as a PR to the openjdk on https://github.com/openjdk/jdk/pull/6275 . The PR still
waits for more reviews and acceptance.    
I, Michael Mirwaldt, came the idea to mind a lazy build of the pipeline by using lambdas.

### What is the advantage of a lazy build of a pipeline?

A lazy build cannot only defer the build of the pipeline. It can also check whether building the pipeline is worth to be
done. Then the stream must not be Stream.empty(). It can also be the stream of an empty list. The build of the pipeline
can even be avoided in that case. If the list, however, gets filled before the terminal operation of the stream is called,
then the pipeline can be built and used as if the list had never been empty.

### How is this project structured?
This project has got two modules:
* core : contains the classes
* demos : contains demo classes which show the consumed memory and the number of objects used by a stream. It uses JOL.

### What are the next todos?
The following needs to be done for a further analysis:
* The tests of the openjdk should pass if possible
* Benchmarks needs to show that the performance doesn't suffer from a lazy build

### What if this approach turns out to be promising?
If the approach turns out to be promising, some options might be:
* Submit it as a PR for the openjdk 
* Offer StreamExt the code
* Create a new project "JStreamAdditions" and offer it as open source

### What if this approach turns out to be troublesome?
Then it was a good exercise how to deal with empty streams.