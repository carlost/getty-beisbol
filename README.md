This was my entry in a IO read & XML parse bakeoff against Node and Goliath.  Netty didn't preform as well as I had hoped.  It matched goliath but got crushed by Node.  My entry took longer to read the stream than Node took to read & parse.  However, I suspect that is a result of:

* I don't know what i was doing
* I bakeoff only required the entries to read a parse a single stream.  I suspect that Netty may have really shined if we had to read multiple stream concurrently.