# Lazy build streams - save memory with streams

### What is the motivation?

Heinz Kabutz once noticed with a colleague at a conference that empty streams create useless stream pipelines. He wrote
about the problem in his blog on https://javaspecialists.eu/archive/Issue295-Faster-Empty-Streams.html
and proposed a solution. He submitted it as a PR to the openjdk on https://github.com/openjdk/jdk/pull/6275 . The PR
still waits for more reviews and acceptance.    
This inspired me, Michael Mirwaldt, to think of a lazy build of the pipeline of a stream by using lambdas.

### What is the advantage of the approach of a lazy build of the pipeline?

This approach cannot only defer the build of the pipeline. It can also check whether building the pipeline is worth to
be done when it is needed. The stream must not be Stream.empty(). It can also be the stream of an empty list. The build
of the pipeline can even be avoided in that case. If the list, however, gets filled before the terminal operation of the
stream is called, then the pipeline can be built and used as if the list had never been empty.

### How much memory can be saved by using lazy build streams?

Creating a LazyBuildStream object takes only 24 bytes if the stream is always empty. That's the case if Stream.empty()
is used. It won't take more than 24 bytes even if intermediate operations are called on that lazy build empty stream. If
the stream can be empty but must not be empty, it starts with 56 bytes and stays fewer than an ordinary stream when
intermediate operations are called on the lazy build stream. This is the case when a stream of a list is used where the
list is empty when it is declared and elements are added after the creation of a stream of the list. You can compare the
memory footprints of the streams in the demos. The demos contain a comment at the end of the file with output which
tells you how much memory is used.

### How is this project structured?

This project has got two modules:

* core : contains the classes
* demos : contains demo classes which show the consumed memory and the number of objects used by a stream. It uses JOL.

The demos can be executed but don't need to executed because their outputs were pasted at the ends of the source files
as a comments. **You must add the VM option -Djol.magicFieldOffset=true for JOL.**

### Who owns the copyright for this project?

Michael Mirwaldt owns the copyright (c) for this project since 2022. All rights reserved to him.

### How is this project licensed?

<a rel="license" href="http://creativecommons.org/licenses/by-nd/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nd/4.0/88x31.png" /></a><br /><span xmlns:dct="http://purl.org/dc/terms/" property="dct:title">
Lazy build streams</span> is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nd/4.0/">
Creative Commons Attribution-NoDerivatives 4.0 International License</a>.
